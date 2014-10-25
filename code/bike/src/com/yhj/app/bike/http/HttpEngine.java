package com.yhj.app.bike.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.Iterator;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.ByteArrayBuffer;

import android.content.Context;

import com.yhj.app.bike.boss.SpeedTest;
import com.yhj.app.bike.command.BaseHttpRequest;
import com.yhj.app.bike.command.HttpCryptPostRequest;
import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.model.pojo.HttpResult;
import com.yhj.app.bike.system.Application;
import com.yhj.app.bike.utils.ApnUtil;
import com.yhj.app.bike.utils.FileUtil;
import com.yhj.app.bike.utils.SLog;

public abstract class HttpEngine {
	private static final int RETRY_ONCE = 1;
	private static final int RETRY_MORE = 2;
	protected BaseHttpRequest baseRequest;
	protected HttpRequestBase requestBase;

	/** unit by milliseconds */
	private static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 30000;
	/** unit by milliseconds */
	private static final int DEFAULT_HTTP_SO_TIMEOUT = 30000;
	/** unit by milliseconds */
	private static final long DEFAULT_HTTP_CONNMGR_TIMEOUT = 30000L;

	/** 每次读取数据数目 */
	protected final static int MAX_READ = 4096;

	/** BUFFER大小 */
	protected final static int BUFFER_SIZE = 1024;

	protected String TAG = "";

	/**
	 * 图片和数据请求返回的状态码 服务器返回错误、JSON错误都以ERROR_NET_ACCESS返回，通过msg区分
	 * 
	 * @author haiyandu
	 * 
	 */
	public enum HttpCode {
		/** 网络访问成功 */
		STATUS_OK(1),
		/** 当前无网络 */
		ERROR_NO_CONNECT(2),
		/** 需要用户登录 */
		ERROR_NO_REGISTER(3),
		/** 网络连接失败 */
		ERROR_NET_ACCESS(4),
		/** 网络连接网络超时 */
		ERROR_NET_TIMEOUT(5),
		/** 请求被取消 */
		USER_CANCELLED(6), SYSTEM_CANCELLED(7), ERROR_SERVICE_ACCESS(8);

		HttpCode(int ni) {
			this.nativeInt = ni;
		}

		final int nativeInt;
	}

	public HttpEngine(BaseHttpRequest request) {
		// Auto-generated constructor stub
		this.baseRequest = request;
		initTag();
	}

	protected HttpResult doRequest() {
		// TODO Auto-generated method stub
		HttpResult httpResult = new HttpResult();
		HttpCode code;

		InputStream entityStream = null;
		HttpClient httpClient = null;
		try {
			initRequest();

			if (baseRequest.isGzip()) {
				requestBase.setHeader("Accept-Encoding", "gzip,deflate");
			}
			requestBase.addHeader("User-Agent", Constants.USER_AGENT);
			/**
			 * 添加头信息
			 */
			if (baseRequest.getHeadParams() != null) {
				Iterator<String> iterator = baseRequest.getHeadParams().keySet().iterator();
				while (iterator.hasNext()) {
					String key = iterator.next();
					String value = baseRequest.getHeadParams().get(key);
					requestBase.addHeader(key, value);
				}
			}

			setRequestParams();

			httpClient = HttpConnManager.getHttpClient();

			long startTime = System.currentTimeMillis();

			HttpResponse httpResponse = httpClient.execute(requestBase);
			int responseCode = httpResponse.getStatusLine().getStatusCode();

			SLog.e("HTTP response code=============================" + responseCode);

			if (responseCode != HttpStatus.SC_OK) {

				SpeedTest.aspect(baseRequest, httpResponse, 0, 0, 0);

				code = HttpCode.ERROR_SERVICE_ACCESS;
				httpResult.setResultCode(code);
				return httpResult;
			} else {
				code = HttpCode.STATUS_OK;
				httpResult.setResultCode(code);
			}

			Header[] headers = httpResponse.getAllHeaders();
			long headerTime = System.currentTimeMillis();
			baseRequest.setGzip(false);
			for (int i = 0; i < headers.length; i++) {
				Header header = headers[i];
				if (header.getName().equals("Content-Encoding") && header.getValue().equals("gzip")) {
					baseRequest.setGzip(true);
				}
			}

			byte[] data = new byte[MAX_READ];
			ByteArrayBuffer byteBuf = new ByteArrayBuffer(MAX_READ);
			entityStream = httpResponse.getEntity().getContent();
			while (!baseRequest.isCancelled()) {
				int bytesRead = entityStream.read(data);

				if (bytesRead == -1) {
					break;
				}
				byteBuf.append(data, 0, bytesRead);
			}

			long dataTime = System.currentTimeMillis();

			if (baseRequest.isGzip()) {
				byte[] retData = FileUtil.gzipDecoder(byteBuf.toByteArray());
				httpResult.setData(retData);
			} else {
				byte[] retData = byteBuf.toByteArray();
				httpResult.setData(retData);
			}

			SpeedTest.aspect(baseRequest, httpResponse, startTime, headerTime, dataTime);

		} catch (SocketTimeoutException e) {
			code = HttpCode.ERROR_NET_TIMEOUT;
			httpResult.setResultCode(code);
			SLog.e(TAG, e.toString(), e);
		} catch (Exception e) {
			code = HttpCode.ERROR_NET_ACCESS;
			httpResult.setResultCode(code);
			SLog.e(TAG, e.toString(), e);
		} catch (OutOfMemoryError e) {
			code = HttpCode.ERROR_NET_ACCESS;
			httpResult.setResultCode(code);
			SLog.e(TAG, e.toString(), e);
		} finally {
			if (entityStream != null) {
				try {
					entityStream.close();
				} catch (IOException e) {
					code = HttpCode.ERROR_NET_ACCESS;
					httpResult.setResultCode(code);
					SLog.e(TAG, e.toString(), e);
				}
			}
			if (httpClient != null) {
				ClientConnectionManager ccm = httpClient.getConnectionManager();
				if (ccm != null) {
					ccm.closeExpiredConnections();
				}
			}
		}

		return httpResult;
	}

	public HttpResult execute() {
		int retryTime = RETRY_ONCE;
		/**
		 * 修改重试次数
		 */
		if (baseRequest.isRetry()) {
			retryTime = RETRY_MORE;
		}
		HttpResult result = new HttpResult();
		/**
		 * 检查，准备并整理所有请求的参数
		 */
		HttpCode code = baseRequest.prepareRequest();
		if (code != HttpCode.STATUS_OK) {
			result.setResultCode(code);
			return result;
		}
		while (retryTime > 0) {
			result = doRequest();
			if (result != null && result.getResultCode() == HttpCode.STATUS_OK) {
				break;
			}
			--retryTime;
		}
		return result;
	}

	protected HttpClient getHttpClient() {
		HttpClient client = null;
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

		HttpParams localHttpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(localHttpParams, DEFAULT_HTTP_CONNECT_TIMEOUT);
		HttpConnectionParams.setSoTimeout(localHttpParams, DEFAULT_HTTP_SO_TIMEOUT);
		ConnManagerParams.setTimeout(localHttpParams, DEFAULT_HTTP_CONNMGR_TIMEOUT);

		ThreadSafeClientConnManager tsccm = new ThreadSafeClientConnManager(localHttpParams, schemeRegistry);
		client = new DefaultHttpClient(tsccm, localHttpParams);

		Context context = Application.getInstance().getApplicationContext();
		HttpHost httpHost = ApnUtil.getHttpHost(context);
		client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, httpHost);

		return client;
	}
	
	
	public static HttpEngine getHttpEngine(BaseHttpRequest req) {
		String sort = req.getSort();
		HttpEngine httpEngine = null;
		
		if (sort.equalsIgnoreCase(Constants.REQUEST_METHOD_GET)) {
			httpEngine = new HttpGetEngine(req);
		} else if (sort.equalsIgnoreCase(Constants.REQUEST_METHOD_POST)) {
			if(req instanceof HttpCryptPostRequest) 
			{
				httpEngine = new HttpCryptPostEngine(req);
			}else{
				httpEngine = new HttpPostEngine(req);
			}
		} else if (sort.equalsIgnoreCase(Constants.REQUEST_METHOD_PUT)) {
			httpEngine = new HttpPutEngine(req);
		} else if (sort.equalsIgnoreCase(Constants.REQUEST_METHOD_DELETE)) {
			httpEngine = new HttpDeleteEngine(req);
		}
		return httpEngine;
	}

	protected abstract void initTag();

	protected abstract void initRequest();

	protected abstract void setRequestParams() throws UnsupportedEncodingException;

}

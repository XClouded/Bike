package com.yhj.app.bike.download;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.client.methods.HttpGet;

import com.yhj.app.bike.system.Application;


/**
 * 网络连接工具类
 * 
 * */

public class DownloadConnectionHelper {
	private final static String TAG = DownloadConnectionHelper.class.getName();

	private static DownloadConnectionHelper instance;

	public static DownloadConnectionHelper getInstance() {
		if (instance == null)
			instance = new DownloadConnectionHelper();
		return instance;
	}

	public DownloadConnectionHelper() {
	}

	public static HttpURLConnection getHttpConnection(String url,
			boolean doInput, boolean allowUserInteraction, int connectTimeout,
			int readTimeout, String range, boolean toRetry) {
		HttpURLConnection uc = null;
		try {
			// 代理
			boolean useProxy = DownloadAPNUtil.hasProxy(Application.getInstance().getApplicationContext());
			if (useProxy) {
				final int hostIndex = "http://".length();
				String proxyIP = DownloadAPNUtil.getApnProxy(Application.getInstance().getApplicationContext());
				String proxyPort = DownloadAPNUtil.getApnPort(Application.getInstance().getApplicationContext());
				String host = null;
				String path = null;
				int pathIndex = url.indexOf('/', hostIndex);
				if (pathIndex < 0) {
					host = url.substring(hostIndex);
					path = "";
				} else {
					host = url.substring(hostIndex, pathIndex);
					path = url.substring(pathIndex);
				}
				URL u = new URL("http://" + proxyIP + ":" + proxyPort + path);
				uc = (HttpURLConnection) u.openConnection();
				uc.setRequestProperty("X-Online-Host", host);
			} else {
				URL u = new URL(url);
				uc = (HttpURLConnection) u.openConnection();
			}
			uc.setRequestMethod(HttpGet.METHOD_NAME);
			uc.setDoInput(doInput);// 设置是否从httpUrlConnection读入
			uc.setAllowUserInteraction(allowUserInteraction);
			uc.setConnectTimeout(connectTimeout);
			uc.setReadTimeout(readTimeout);
			if (range != null)
				uc.setRequestProperty("Range", range);
			uc.setConnectTimeout(2 * 60 * 1000);
			int reponseCode = uc.getResponseCode();
			//Toast.makeText(Application.getInstance().getApplicationContext(), "网络错误："+reponseCode, 0);
			if (reponseCode == HttpURLConnection.HTTP_MOVED_TEMP
					|| reponseCode == HttpURLConnection.HTTP_MOVED_PERM) {
				// 302代表暂时性转移,301代表永久性转移
				String loc = uc.getHeaderField("Location");
				disConnect(uc);
				uc = null;
				if (loc != null) {
					uc = getHttpConnection(loc, doInput, allowUserInteraction,
							connectTimeout, readTimeout, range, false);
				}
			} else if (reponseCode == HttpsURLConnection.HTTP_OK
					|| reponseCode == HttpsURLConnection.HTTP_PARTIAL) {
				// 206 Partial
				// Content服务器已经接受请求GET请求资源的部分。请求必须包含一个Range头信息以指示获取范围可能必须包含If-Range头信息以成立请求条件
				String contentType = uc.getContentType();
				contentType = contentType == null ? "" : contentType
						.toLowerCase();
				boolean isTxtType = (contentType.indexOf(HttpMsg.TYPE_WML) != -1 || contentType
						.indexOf(HttpMsg.TYPE_WMLC) != -1);
				if (isTxtType && toRetry) {
					
					uc = getHttpConnection(url, doInput, allowUserInteraction,
							connectTimeout, readTimeout, range, false);

				} else {
					// 当不用重试时，再判断下是否是文本类型，如果是则关闭连接
					if (isTxtType || contentType.indexOf("text") != -1) {
						disConnect(uc);
						uc = null;
					}
				}
			} else {
				disConnect(uc);
				uc = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			disConnect(uc);
			uc = null;
			return null;
		}
		return uc;
	}

	private static void disConnect(HttpURLConnection uc) {
		try {
			if (uc != null) {
				uc.disconnect();
				uc = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

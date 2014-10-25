package com.yhj.app.bike.utils;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
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

import android.content.Context;

import com.yhj.app.bike.system.Application;

public class HttpClientUtil {

	/** unit by milliseconds */
	public static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 30000;
	/** unit by milliseconds */
	public static final int DEFAULT_HTTP_SO_TIMEOUT = 30000;
	/** unit by milliseconds */
	public static final long DEFAULT_HTTP_CONNMGR_TIMEOUT = 30000L;

	static HttpClient httpClient;

	static {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

		HttpParams localHttpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(localHttpParams, DEFAULT_HTTP_CONNECT_TIMEOUT);
		HttpConnectionParams.setSoTimeout(localHttpParams, DEFAULT_HTTP_SO_TIMEOUT);
		ConnManagerParams.setTimeout(localHttpParams, DEFAULT_HTTP_CONNMGR_TIMEOUT);

		ThreadSafeClientConnManager tsccm = new ThreadSafeClientConnManager(localHttpParams, schemeRegistry);

		httpClient = new DefaultHttpClient(tsccm, localHttpParams);
	}

	public static HttpClient getHttpClient() {

		Context context = Application.getInstance().getApplicationContext();
		HttpHost httpHost = ApnUtil.getHttpHost(context);
		httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, httpHost);

		return httpClient;
	}

	public static void closeExpiredConn() {
		ClientConnectionManager ccm = getHttpClient().getConnectionManager();
		if (ccm != null) {
			ccm.closeExpiredConnections();
		}
	}
}

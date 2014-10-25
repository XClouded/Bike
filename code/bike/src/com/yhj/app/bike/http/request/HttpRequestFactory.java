package com.yhj.app.bike.http.request;

import com.yhj.app.bike.command.HttpCryptPostRequest;
import com.yhj.app.bike.command.HttpDataRequest;
import com.yhj.app.bike.command.HttpPostRequest;
import com.yhj.app.bike.command.HttpTagDispatch.HttpTag;
import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.model.BaseReq;

public class HttpRequestFactory {
	
	//开发环境
	public static String READ_BASE_URL = "http://192.168.1.100:8080/bike/app";
	
	private static HttpRequestFactory requestFactory = null;
	
	/**
	 * 获取单例HttpRequestFactory实例
	 * @return
	 */
	public static HttpRequestFactory getInstance() {
		if (null == requestFactory) {
			requestFactory = new HttpRequestFactory();
		}
		
		return requestFactory;
	}
	
	/**
	 * @param url 具体地址
	 * @return
	 */
	private HttpPostRequest post(String url)
	{
		HttpPostRequest request = new HttpCryptPostRequest();
		request.setGzip(false);
		request.setNeedAuth(false);
		request.setRetry(false);
		request.setSort(Constants.REQUEST_METHOD_POST);
		request.setUrl(READ_BASE_URL + url);
		return request;
	}
	
	/**
	 * @param reqObj 请求对象
	 * @param url   具体url地址
	 * @return
	 */
	public HttpDataRequest post(BaseReq reqObj,String url) {
		
		HttpPostRequest request = post(url);
		if(reqObj.needAuth())
		{
			request.setNeedAuth(true);
		}
		request.setTag(HttpTag.fromString(reqObj.getCmd()));
		request.setBody(String.valueOf(reqObj));
		return request;
	}
	
/*
	public HttpDataRequest request(String body, String sign) {
		HttpPostRequest request = new HttpPostRequest();
		request.setBody(body);
		
		request.setGzip(false);
		request.setNeedAuth(false);
		request.setRetry(false);
		request.setTag(HttpTag.NONE);
		request.setUrl(READ_BASE_URL);
		request.setSort(Constants.REQUEST_METHOD_POST);

		request.addUrlParams("sign", sign);
		return request;

	}*/
	
}

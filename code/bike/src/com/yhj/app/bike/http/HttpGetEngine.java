package com.yhj.app.bike.http;

import org.apache.http.client.methods.HttpGet;

import com.yhj.app.bike.command.BaseHttpRequest;

public class HttpGetEngine extends HttpEngine {

	public HttpGetEngine(BaseHttpRequest request) {
		super(request);
	}

	@Override
	protected void initTag() {
		TAG = "HttpGetEngine";
	}

	@Override
	protected void initRequest() {
		requestBase = new HttpGet(baseRequest.getUrl());
	}

	@Override
	protected void setRequestParams() {
	}

}

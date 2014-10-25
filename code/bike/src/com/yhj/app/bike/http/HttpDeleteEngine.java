package com.yhj.app.bike.http;

import org.apache.http.client.methods.HttpDelete;

import com.yhj.app.bike.command.BaseHttpRequest;

public class HttpDeleteEngine extends HttpEngine {

	public HttpDeleteEngine(BaseHttpRequest request) {
		super(request);
	}


	@Override
	protected void initTag() {
		TAG = "HttpDeleteEngine";
	}

	@Override
	protected void initRequest() {
		requestBase = new HttpDelete(baseRequest.getUrl());
	}

	@Override
	protected void setRequestParams() {
	}

}

package com.yhj.app.bike.http;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPut;

import com.yhj.app.bike.command.BaseHttpRequest;

public class HttpPutEngine extends HttpEngine {

	public HttpPutEngine(BaseHttpRequest request) {
		super(request);
	}

	@Override
	protected void initTag() {
		TAG = "HttpPutEngine";
	}

	@Override
	protected void initRequest() {
		requestBase = new HttpPut(baseRequest.getUrl());
	}

	@Override
	protected void setRequestParams() throws UnsupportedEncodingException {
	}

}

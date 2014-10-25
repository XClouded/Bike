package com.yhj.app.bike.http;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;

import com.yhj.app.bike.command.BaseHttpRequest;
import com.yhj.app.bike.command.HttpPostRequest;

public class HttpPostMutipleEngine extends HttpEngine {

	public HttpPostMutipleEngine(BaseHttpRequest request) {
		super(request);
	}

	@Override
	protected void initTag() {
		TAG = "HttpPostMutipleEngine";
	}

	@Override
	protected void initRequest() {
		requestBase = new HttpPost(baseRequest.getUrl());
	}

	@Override
	protected void setRequestParams() {
		HttpPostRequest postRequest = (HttpPostRequest)baseRequest;
    	AbstractHttpEntity entity = new ByteArrayEntity(postRequest.getBytes());
		entity.setContentType("multipart/form-data; boundary=" + HttpPostRequest.BOUNDARY);
    	((HttpPost) requestBase).setEntity(entity); 
	}
	
}

package com.yhj.app.bike.http;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.StringEntity;

import com.yhj.app.bike.command.BaseHttpRequest;
import com.yhj.app.bike.command.HttpPostRequest;

public class HttpPostEngine extends HttpEngine {

	public HttpPostEngine(BaseHttpRequest request) {
		super(request);
	}

	@Override
	protected void initTag() {
		TAG = HttpPostEngine.class.getSimpleName();
	}

	@Override
	protected void initRequest() {
		requestBase = new HttpPost(baseRequest.getUrl());
	}

	@Override
	protected void setRequestParams() throws UnsupportedEncodingException {
		HttpPostRequest postRequest = (HttpPostRequest)baseRequest;
		
		if(postRequest.getBody() != null)
		{
			String entityString = postRequest.getBody();
			AbstractHttpEntity entity = new StringEntity(entityString);
			entity.setContentType("application/octet-stream");
	    	((HttpPost) requestBase).setEntity(entity); 
		}
		else
		{
			String entityString = postRequest.getString();
			AbstractHttpEntity entity = new StringEntity(entityString);
			entity.setContentType("application/x-www-form-urlencoded");
	    	((HttpPost) requestBase).setEntity(entity); 
		}
	}
}

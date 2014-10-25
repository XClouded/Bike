package com.yhj.app.bike.http;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yhj.app.bike.command.BaseHttpRequest;
import com.yhj.app.bike.command.HttpCryptPostRequest;
import com.yhj.app.bike.command.HttpPostRequest;
import com.yhj.app.bike.model.pojo.HttpCryptResult;
import com.yhj.app.bike.model.pojo.HttpResult;

/**
 * 
 * @author wilson.song
 *
 */
public class HttpCryptPostEngine extends HttpPostEngine {

	public HttpCryptPostEngine(BaseHttpRequest request) {
		super(request);
	}
	
	@Override
	public HttpResult execute() {
		return new HttpCryptResult(super.execute());
	}
	
	
	@Override
	protected void setRequestParams() throws UnsupportedEncodingException {
		HttpPostRequest postRequest = (HttpPostRequest)baseRequest;
		
		String entityString = postRequest.getBody();
		
		if (entityString != null) {
			Map<String,String> paramsMap = new Gson().fromJson(entityString, new TypeToken<HashMap<String,String>>(){}.getType());
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			if (paramsMap != null && paramsMap.size() > 0) {
				Set<String> keySet = paramsMap.keySet();
				
				Iterator<String> keyIt = keySet.iterator();
				
				while (keyIt.hasNext()) {
					String name = keyIt.next();
					params.add(new BasicNameValuePair(name, paramsMap.get(name)));
				}
			}
			
			 UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params); 
			 
			 entity.setContentType("application/x-www-form-urlencoded");
			
			((HttpPost)requestBase).setEntity(entity);
		}
	}
}

package com.yhj.app.bike.command;

import org.json.JSONException;
import org.json.JSONObject;

import com.yhj.app.bike.http.session.SessionData;
import com.yhj.app.bike.utils.Crypt;

public class HttpCryptPostRequest extends HttpPostRequest {
private String org_body  ; 
	
	public void setBody(String body)
	{
		if(org_body == null)
		{
			org_body = body ; 
		}
		super.setBody(body);
		addUrlParams("sign", Crypt.getSign(body));
	}
	
	public String getBody()
	{
		return super.getBody();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getOrgBody()
	{
		return org_body;
	}
	
	protected void addUserVerifyInfo() {
		
		String userid = (String)SessionData.get().getVal(SessionData.KEY_USER_ID);
		String token = (String)SessionData.get().getVal(SessionData.KEY_TOKEN);
		
		try {
			JSONObject json = new JSONObject(org_body);
			json.put(SessionData.KEY_USER_ID, userid);
			json.put(SessionData.KEY_TOKEN, token);
			setBody(json.toString());
		} catch (JSONException ignore) {
		}
		
	}
}

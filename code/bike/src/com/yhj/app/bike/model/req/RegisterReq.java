package com.yhj.app.bike.model.req;

import com.yhj.app.bike.model.BaseReq;

public class RegisterReq extends BaseReq {
	private String username;
	private String passwd;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
}

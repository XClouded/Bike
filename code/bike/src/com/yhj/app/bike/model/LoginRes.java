package com.yhj.app.bike.model;

import java.io.Serializable;

public class LoginRes implements Serializable {
	private String code;
	private String token;
	private String status;
	private String cust_flag;
	private String userid;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCust_flag() {
		return cust_flag;
	}
	public void setCust_flag(String cust_flag) {
		this.cust_flag = cust_flag;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}

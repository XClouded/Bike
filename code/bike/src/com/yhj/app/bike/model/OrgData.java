package com.yhj.app.bike.model;

import java.io.Serializable;

public class OrgData implements Serializable {
	private String code;
	private String msg;
	private String crypt;
	private Object data;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCrypt() {
		return crypt;
	}
	public void setCrypt(String crypt) {
		this.crypt = crypt;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}

package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class Video implements Serializable{
	private static final long serialVersionUID = -1663085985772895882L;
	private String code;
	private String url;
	private String msg;
	
	public void setCode(String code){
		this.code = code;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}
	
	public String getMsg(){
		return this.msg;
	}
}

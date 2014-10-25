package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

public class SimpleRet implements Serializable {
	
	private static final long serialVersionUID = -3833771304624617450L;

	private String ret;
	private String info;
	
	public SimpleRet(){
		
	}
	
	public String getReturnValue(){
		return this.ret;
	}
	
	public String getReturnInfo(){
		return this.info;
	}
}

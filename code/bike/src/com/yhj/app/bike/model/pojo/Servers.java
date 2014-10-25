package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

public class Servers implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6851942603812493866L;
	private String host;
	private String port;
	
	public void setIP(String host){
		this.host = host;
	}
	
	public 	String getIP(){
		return this.host;
	}
	
	public void setPort(String port){
		this.port = port;
	}
	
	public String getPort(){
		return this.port;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}

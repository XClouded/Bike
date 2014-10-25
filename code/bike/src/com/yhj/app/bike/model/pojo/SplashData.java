package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

public class SplashData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6206000174874558540L;
	private String ret;
	private String version;
	private Pic [] pics;
	
	public void setRet(String ret){
		this.ret = ret;
	}
	
	public String getRet(){
		return this.ret;
	}
	
	public void setVersion(String version){
		this.version = version;
	}
	
	public String getVersion(){
		return this.version;
	}
	
	public void setPics(Pic [] pics){
		this.pics = pics;
	}
	
	public Pic [] getPics(){
		return this.pics;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new Gson().toJson(this);
	}
}

package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

public class Channel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3253236462725093482L;
	private String chlid;
	private String chlname;
	private String recommend;
	private String count;
	
	public void setChlid(String chlid){
		this.chlid = chlid;
	}
	
	public String getChlid(){
		return this.chlid;
	}
	
	public void setChlname(String chlname){
		this.chlname = chlname;
	}
	
	public String getChlname(){
		return this.chlname;
	}
	
	public void setRecomment(String recomment){
		this.recommend = recomment;
	}
	
	public String getRecomment(){
		return this.recommend;
	}
	
	public void setCount(String count){
		this.count = count;
	}
	
	public String getCount(){
		return this.count;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}

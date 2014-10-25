package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

public class HotChannel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8863008855150606159L;
	private String chlid;
	private String weight;
	
	public void setChild(String chlid){
		this.chlid = chlid;
	}
	
	public String getChild(){
		return this.chlid;
	}
	
	public void setWeight(String weight){
		this.weight = weight;
	}
	
	public String getWeight(){
		return this.weight;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}

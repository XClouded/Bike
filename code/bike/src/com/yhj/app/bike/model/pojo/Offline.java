package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;
/**
 *注：这是谁命名的offline和我实现的离线下载无关 
 *
 */
public class Offline implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3462497096493706761L;
	private String chlid;
	private String chlname;
	private String count;
	private String downloadcount;
	
	public void setChild(String child){
		this.chlid = child;
	}
	
	public String getChild(){
		return this.chlid;
	}
	
	public void setChlname(String chlname){
		this.chlname = chlname;
	}
	
	public String getChlname(){
		return this.chlname;
	}
	
	public void setCount(String count){
		this.count = count;
	}
	
	public String getCount(){
		return this.count;
	}
	
	public void setDownLoadCount(String downloadcount){
		this.downloadcount = downloadcount;
	}
	
	public String getDownLoadCount(){
		return this.downloadcount;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}

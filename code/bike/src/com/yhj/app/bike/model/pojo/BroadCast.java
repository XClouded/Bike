package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class BroadCast implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3402124084613117541L;
	private String url;
	private String starttime;
	private String endtime;
	private String progid;
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public void setStartTime(String starttime){
		this.starttime = starttime;
	}
	
	public String getStartTime(){
		return this.starttime;
	}
	
	public void setEndTime(String endtime){
		this.endtime = endtime;
	}
	
	public String getEndTime(){
		return this.endtime;
	}
	
	public void setProgid(String progid){
		this.progid = progid;
	}
	
	public String getProgid(){
		return this.progid;
	}
}

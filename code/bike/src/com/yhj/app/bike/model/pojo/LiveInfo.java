package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class LiveInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7611434018317833733L;
	private String alive;
	private String progid;
	private String qtype;
	private LiveTime time;
	
	public LiveInfo() {}
	
	public void setAlive(String alive){
		this.alive = alive;
	}
	
	public String getAlive(){
		return this.alive;
	}
	
	public void setProgid(String progid){
		this.progid = progid;
	}
	
	public String getProgid(){
		return this.progid;
	}
	
	public void setQtype(String qtype){
		this.qtype = qtype;
	}
	
	public String getQttype(){
		return this.qtype;
	}
	
	public void setLiveTime(LiveTime time){
		this.time = time;
	}
	
	public LiveTime getLiveTime(){
		return this.time;
	}
}

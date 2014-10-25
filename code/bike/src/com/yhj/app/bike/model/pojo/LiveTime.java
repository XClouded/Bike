package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class LiveTime implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6982998211516406175L;
	private String time_start;
	private String time_end;
	private String time_curr;
	
	public LiveTime() {}
	
	public void setTimeStart(String time_start){
		this.time_start = time_start;
	}
	
	public String getTimeStart(){
		return this.time_start;
	}
	
	public void setTimeEnd(String time_end){
		this.time_end = time_end;
	}
	
	public String getTimeEnd(){
		return this.time_end;
	}
	
	public void setTimeCurr(String time_curr){
		this.time_curr = time_curr;
	}
	
	public String getTimeCurr(){
		return this.time_curr;
	}
}

package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class LiveStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6806343624821421486L;
	private String retcode;
	private LiveInfo info;
	private String error;
	
	public LiveStatus() {}
	
	public void setRetCode(String retcode){
		this.retcode = retcode;
	}
	
	public String getRetCode(){
		return this.retcode;
	}
	
	public void setLiveInfo(LiveInfo info){
		this.info = info;
	}
	
	public LiveInfo getLiveInfo(){
		return this.info;
	}
	
	public void setError(String error){
		this.error = error;
	}
	
	public String getError(){
		return this.error;
	}
}

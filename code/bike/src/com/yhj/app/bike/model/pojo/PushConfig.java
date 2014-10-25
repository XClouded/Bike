package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

public class PushConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 164318592531352411L;
	private String code;
	private String auth;
	private String period;
	private Servers servers[];
	private String flag;
	private String seq;
	private String msg;
	
	public void setCode(String code){
		this.code = code;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public void setAuth(String auth){
		this.auth = auth;
	}
	
	public String getAuth(){
		return this.auth;
	}
	
	public void setPeriod(String period){
		this.period = period;
	}
	
	public String getPeriod(){
		return this.period;
	}
	
	public void setServers(Servers mServers[]){
		this.servers = mServers;
	}
	
	public Servers [] getServers(){
		return this.servers;
	}
	
	public void setFlag(String flag){
		this.flag = flag;
	}
	
	public String getFlag(){
		return this.flag;
	}
	
	public void setSeq(String seq){
		this.seq = seq;
	}
	
	public String getSeq(){
		return this.seq;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}
	
	public String getMsg(){
		return this.msg;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}

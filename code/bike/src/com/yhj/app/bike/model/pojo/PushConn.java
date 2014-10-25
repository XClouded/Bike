package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class PushConn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4878481721161023815L;
	private String code;
	private String flag;
	private String seq;
	private Msg msg;
	
	public void setCode(String code){
		this.code = code;
	}
	
	public String getCode(){
		return this.code;
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
	
	public void setMsg(Msg msg){
		this.msg = msg;
	}
	
	public Msg getMsg(){
		return this.msg;
	}
}

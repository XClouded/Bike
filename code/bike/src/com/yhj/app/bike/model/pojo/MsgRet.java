package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class MsgRet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3566427924551337573L;
	private String ret;
	private String ruin;
	private String msg;
	private String time;
	
	
	
	public MsgRet(){
		
	}
	
	public String getRet() {
		return this.ret;
	}
	
	public void setRet(String ret) {
		this.ret = ret;
	}
	
	public String getRuin() {
	    return this.ruin;
	}
	
	public void setRuin(String ruin) {
	    this.ruin = ruin;
	}
	
	public String getMsg() {
	    return this.msg;
	}
	
	public void setMsg(String msg) {
	    this.msg = msg;
	}
	
	public void setTime(String time) {
	    this.time = time;
	}
	
	public String getTime() {
	    return this.time;
	}
}

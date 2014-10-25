package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class NewsMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7142364239180012671L;
	private String nick;
	private String uin;
	private String head;
	private String isvip;
	private String msg;
	private String time;
	private String newCount;
	
	public NewsMsg(){
		
	}
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public String getUin() {
		return uin;
	}
	
	public void setUin(String uin) {
		this.uin = uin;
	}
	
	public String getHead() {
		return head;
	}
	
	public void setHead(String head) {
		this.head = head;
	}
	
	public String getIsvip() {
		return isvip;
	}
	
	public void setIsvip(String isvip) {
		this.isvip = isvip;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getNewCount() {
	    return this.newCount;
	}
	
	public void setNewCount(String newCount) {
	    this.newCount = newCount;
	}
}

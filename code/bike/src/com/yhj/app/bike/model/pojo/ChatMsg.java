package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class ChatMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5816710997375147509L;
	private String msg;
	private String msgType;
	private String time;
	private String sender;
	private String uin;
	private String isvip;
	private String senderHead;
	private String reciever;
	private String ruin;
	private String risvip;
	private String recieverHead;
	private boolean isTimeSection = false;
	

	public ChatMsg() {
		
	}
	
	public boolean isTimeSection() {
		return isTimeSection;
	}
	
	public void setTimeSection(boolean isTimeSection) {
		this.isTimeSection = isTimeSection;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getMsgType() {
		return msgType;
	}
	
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getUin() {
		return uin;
	}
	
	public void setUin(String uin) {
		this.uin = uin;
	}
	
	public String getIsvip() {
		return isvip;
	}
	
	public void setIsvip(String isvip) {
		this.isvip = isvip;
	}
	
	public String getSenderHead() {
		return senderHead;
	}
	
	public void setSenderHead(String senderHead) {
		this.senderHead = senderHead;
	}
	
	public String getReciever() {
		return reciever;
	}
	
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	
	public String getRuin() {
		return ruin;
	}
	
	public void setRuin(String ruin) {
		this.ruin = ruin;
	}
	
	public String getRisvip() {
		return risvip;
	}
	
	public void setRisvip(String risvip) {
		this.risvip = risvip;
	}
	
	public String getRecieverHead() {
		return recieverHead;
	}
	
	public void setRecieverHead(String recieverHead) {
		this.recieverHead = recieverHead;
	}
}

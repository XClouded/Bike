package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class Editor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6448317260310660640L;
	private String id;
	private String nick;
	private String flag;
	private String headimg;
	private String title;
	
	public Editor(){
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public String getFlag() {
		return flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getHeadimg() {
		return headimg;
	}
	
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

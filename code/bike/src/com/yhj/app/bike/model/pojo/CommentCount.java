package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.HashMap;


public class CommentCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6954601188327969717L;
	private String ret;
	private HashMap<String, Object> info = new HashMap<String, Object>();
	
	public String getRet() {
		return ret;
	}
	
	public void setRet(String ret) {
		this.ret = ret;
	}
	
	public HashMap<String, Object> getInfo() {
		return info;
	}
	
	public void setInfo(HashMap<String, Object> info) {
		this.info = info;
	}

}

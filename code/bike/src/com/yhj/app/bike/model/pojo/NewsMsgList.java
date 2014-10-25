package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NewsMsgList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8152402469832733128L;
	private String ret;
	private List<ChatMsg> data;
	private String anymore;
	
	public NewsMsgList(){
		
	}
	
	public String getRet() {
		return ret;
	}
	
	public void setRet(String ret) {
		this.ret = ret;
	}
	
	public List<ChatMsg> getData() {
		return data;
	}
	
	public void setData(List<ChatMsg> data) {
		this.data = data;
	}
	
	public String getAnymore() {
		return anymore;
	}
	
	public void setAnymore(String anymore) {
		this.anymore = anymore;
	}
}

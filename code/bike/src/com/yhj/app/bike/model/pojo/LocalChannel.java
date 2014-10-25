package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class LocalChannel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4783406692826072629L;
	private String chlid;
	private String chlname;
	private String recommend;
	private String count;
	private String selected;
	private String head_letter;
	
	public LocalChannel(){
		
	}
	
	public String getChlid() {
		return chlid;
	}
	
	public void setChlid(String chlid) {
		this.chlid = chlid;
	}
	
	public String getChlname() {
		return chlname;
	}
	
	public void setChlname(String chlname) {
		this.chlname = chlname;
	}
	
	public String getRecommend() {
		return recommend;
	}
	
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	
	public String getCount() {
		return count;
	}
	
	public void setCount(String count) {
		this.count = count;
	}
	
	public String getSelected() {
		return selected;
	}
	
	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	public String getHead_letter() {
		return head_letter;
	}
	
	public void setHead_letter(String head_letter) {
		this.head_letter = head_letter;
	}

}

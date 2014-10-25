package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class DynamicChannel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4604575705565445390L;
	private String chlid;
	private String chlname;
	private String type;
	private String url;
	
	public DynamicChannel(){
		
	}
	
	public String getChlid() {
		return this.chlid;
	}
	public void setChlid(String chlid) {
		this.chlid = chlid;
	}
	public String getChlname() {
		return this.chlname;
	}
	public void setChlname(String chlname) {
		this.chlname = chlname;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

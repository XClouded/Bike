package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class NewsVersion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3284251733844041391L;
	private String version;
	private String message;
	private String url;
	
	public void setVersion(String version){
		this.version = version;
	}
	
	public String getVersion(){
		return this.version;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	@Override
	public String toString() {
		return "NewsVersion:" + "version=" + version + "message=" + message + "url=" + url;
	}
}

package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class Content implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5120076065269306828L;
	private String type;
	private String value;
	private String width;
	private String height;
	private VideoValue video;
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public void setWidth(String width){
		this.width = width;
	}
	
	public String getWidth(){
		return this.width;
	}
	
	public void setHeight(String height){
		this.height = height;
	}
	
	public String getHeight(){
		return this.height;
	}
	
	public void setVideoValue(VideoValue video){
		this.video = video;
	}
	
	public VideoValue getVideoValue(){
		return this.video;
	}
}

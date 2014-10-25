package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class VideoValue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9132030652641159810L;
	private String videosourcetype;
	private String playmode;
	private String playurl;
	private String vid;
	private String desc;
	private String img;
	private String width;
	private String height;
	private BroadCast broadcast = null;
	
	public VideoValue() {
		
	}
	
	public void setVideoSourceType(String videosourcetype){
		this.videosourcetype = videosourcetype;
	}
	
	public String getVideoSourceType(){
		return this.videosourcetype;
	}
	
	public void setPlayMode(String playmode){
		this.playmode = playmode;
	}
	
	public String getPlayMode(){
		return this.playmode;
	}
	
	public void setPlayUrl(String playurl){
		this.playurl = playurl;
	}
	
	public String getPlayUrl(){
		return this.playurl;
	}
	
	public void setVid(String vid){
		this.vid = vid;
	}
	
	public String getVid(){
		return this.vid;
	}
	
	public void setDesc(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
	public void setImg(String img){
		this.img = img;
	}
	
	public String getImg(){
		return this.img;
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
	
	public void setBroadCast(BroadCast broadcast){
		this.broadcast = broadcast;
	}
	
	public BroadCast getBroadCast(){
		return this.broadcast;
	}
}

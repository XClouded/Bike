package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.List;

public class NewsDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3129202635211799245L;
	private String ret;
	private String id;
	private String url;
	private String topic;
	private String surl;
	private List<Content> ContentList;
	
	public void setRet(String ret){
		this.ret = ret;
	}
	
	public String getRet(){
		return this.ret;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getId(){
		return this.id;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public void setSurl(String surl){
		this.surl = surl;
	}
	
	public String getSurl(){
		return this.surl;
	}
	
	public void setTopic(String topic){
		this.topic = topic;
	}
	
	public String getTopic(){
		return this.topic;
	}
	
	public void setContentList(List<Content> ContentList){
		this.ContentList = ContentList;
	}
	
	public List<Content> getContentList(){
		return this.ContentList;
	}
}

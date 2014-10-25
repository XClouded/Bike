package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class ShareQQWeiboResponse implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4779642914716406796L;
	private String publish;
	private String ret;
	private String retmsg;
	private String id;
	private String time;
	
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public String getRetmsg() {
		return retmsg;
	}
	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	
}

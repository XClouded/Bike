package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class ShareQzoneResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8212256136071339440L;
	private String publish;
	private String ret;
	private String retmsg;
	
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
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
	

	
}

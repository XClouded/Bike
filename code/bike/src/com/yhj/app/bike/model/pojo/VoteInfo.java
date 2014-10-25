package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class VoteInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6523432160895835511L;

	@SerializedName("RETCODE")
	private String retCode;
	@SerializedName("INFO")
	private Info info;
	
	public VoteInfo() {
		
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "VoteInfo [retCode=" + retCode + ", info=" + info + "]";
	}

	
	

}

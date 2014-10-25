package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class ImgTxtLiveSummary implements Serializable {

	private static final long serialVersionUID = 40227298831352869L;

	String version;
	String cnt;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

}

package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class App implements Serializable {

	private static final long serialVersionUID = 9214043736004276134L;
	private String ico3;
	private String ico4;
	private String url;
	private String txt;

	public void setIco3(String ico3) {
		this.ico3 = ico3;
	}

	public String getIco3() {
		return this.ico3;
	}

	public void setIco4(String ico4) {
		this.ico4 = ico4;
	}

	public String getIco4() {
		return this.ico4;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getTxt() {
		return this.txt;
	}
}

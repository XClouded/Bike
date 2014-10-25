package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

public class SpecialReportImage implements Serializable {
	private static final long serialVersionUID = -36305219563137095L;

	private String url;
	private String height;
	private String width;

	public String getHeight() {
		return height;
	}

	public String getUrl() {
		return url;
	}

	public String getWidth() {
		return width;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
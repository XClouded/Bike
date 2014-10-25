package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 * 离线下载的压缩包
 * 
 * @author jackiecheng
 * 
 */
public class OfflineNewslist implements Serializable {

	private static final long serialVersionUID = -5120076265269306828L;

	Item listitems;
	SpecialReport content;

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public Item getListitems() {
		return listitems;
	}

	public void setListitems(Item listitems) {
		this.listitems = listitems;
	}

	public SpecialReport getContent() {
		return content;
	}

	public void setContent(SpecialReport content) {
		this.content = content;
	}

}

package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

public class ItemsByLoadMore implements Serializable {
	private static final long serialVersionUID = 1836508984334224239L;
	String ret;
	Item newslist[];

	public ItemsByLoadMore() {

	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public Item[] getNewslist() {
		return newslist;
	}

	public void setNewslist(Item[] newslist) {
		this.newslist = newslist;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}

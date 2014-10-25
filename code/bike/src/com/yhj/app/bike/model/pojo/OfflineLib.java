package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 * 离线下载的压缩包
 * 
 * @author jackiecheng
 * 
 */
public class OfflineLib implements Serializable {

	private static final long serialVersionUID = -5120076265269306828L;
	private String ret;
	private Id[] ids;
	private OfflineNewslist[] newslist;

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public Id[] getIds() {
		return ids;
	}

	public void setIds(Id[] ids) {
		this.ids = ids;
	}

	public OfflineNewslist[] getNewslist() {
		return newslist;
	}

	public void setNewslist(OfflineNewslist[] newslist) {
		this.newslist = newslist;
	}

}

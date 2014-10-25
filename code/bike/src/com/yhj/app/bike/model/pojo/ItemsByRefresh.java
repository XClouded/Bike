package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;
/**
 * 下拉刷新的列表数据
 * @author jackiecheng
 *
 */
public class ItemsByRefresh implements Serializable {
	private static final long serialVersionUID = -1596514872740655467L;

	String ret;
	IdsAndItems idlist[];

	public ItemsByRefresh() {

	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public IdsAndItems[] getIdlist() {
		return idlist;
	}

	public void setIdlist(IdsAndItems[] idlist) {
		this.idlist = idlist;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}

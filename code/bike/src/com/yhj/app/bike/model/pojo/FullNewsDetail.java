package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class FullNewsDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8634632931444152488L;
	private Item mItem;
	private SimpleNewsDetail mDetail;
	
	
	public Item getmItem() {
		return mItem;
	}
	
	public SimpleNewsDetail getmDetail() {
		return mDetail;
	}

	public void setmDetail(SimpleNewsDetail mDetail) {
		this.mDetail = mDetail;
	}

	public void setmItem(Item mItem) {
		this.mItem = mItem;
	}
}

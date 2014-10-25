package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class CommentCountItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1893983944199483839L;
	private String ret;
	private int count;

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}

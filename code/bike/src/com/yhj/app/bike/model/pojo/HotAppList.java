package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class HotAppList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6361900338021250967L;

	private String bannerURL;
	private String bannerIMG;
	private ArrayList<HotAppListItem> listItem;
	private String remain;

	public String getBannerURL() {
		return bannerURL;
	}

	public void setBannerURL(String bannerURL) {
		this.bannerURL = bannerURL;
	}

	public String getBannerIMG() {
		return bannerIMG;
	}

	public void setBannerIMG(String bannerIMG) {
		this.bannerIMG = bannerIMG;
	}

	public ArrayList<HotAppListItem> getListItem() {
		return listItem;
	}

	public void setListItem(ArrayList<HotAppListItem> listItem) {
		this.listItem = listItem;
	}

	public String getRemain() {
		return remain;
	}

	public void setRemain(String remain) {
		this.remain = remain;
	}

}

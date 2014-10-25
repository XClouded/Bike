package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.List;

public class RssCatListCat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8943616111240888154L;
	private String catId;
	private String catName;
	private String icon;
	private String icon_hl;
	private String micon;
	private String recommend;
	private List<RssCatListItem> channels;

	public RssCatListCat() {

	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon_hl() {
		return icon_hl;
	}

	public void setIcon_hl(String icon_hl) {
		this.icon_hl = icon_hl;
	}

	public String getMicon() {
		return micon;
	}

	public void setMicon(String micon) {
		this.micon = micon;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public List<RssCatListItem> getChannels() {
		return channels;
	}

	public void setChannels(List<RssCatListItem> channels) {
		this.channels = channels;
	}

}

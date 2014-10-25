package com.yhj.app.bike.model.pojo;

public class Share {

	private int id;
	private int logo;
	private String shareName;

	public Share(int id, int logo, String name) {
		this.id = id;
		this.logo = logo;
		this.shareName = name;
	}

	public int getLogo() {
		return logo;
	}

	public void setLogo(int logo) {
		this.logo = logo;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

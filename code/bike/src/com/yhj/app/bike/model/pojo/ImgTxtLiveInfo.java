package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class ImgTxtLiveInfo implements Serializable {

	private static final long serialVersionUID = 40227298831352869L;

	int newSeq = 0;
	long localTime = 0L;

	String id;
	String time;
	String race_time;
	String content;
	ImgTxtLiveImage[] image;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ImgTxtLiveImage[] getImage() {
		return image;
	}

	public void setImage(ImgTxtLiveImage[] image) {
		this.image = image;
	}

	public int getNewSeq() {
		return newSeq;
	}

	public void setNewSeq(int newSeq) {
		this.newSeq = newSeq;
	}

	public String getRace_time() {
		return race_time;
	}

	public void setRace_time(String race_time) {
		this.race_time = race_time;
	}

	public long getLocalTime() {
		return localTime;
	}

	public void setLocalTime(long localTime) {
		this.localTime = localTime;
	}

}

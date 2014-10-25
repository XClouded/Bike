package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class ImgTxtLiveIntro implements Serializable {

	private static final long serialVersionUID = 40227298831352869L;

	String atlogo;
	String begintime;
	String desc;
	String endtime;
	String htlogo;
	String score;
	String status;
	String status_code;
	String tflag;
	String topic;
	String type;
	String channel;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAtlogo() {
		return atlogo;
	}

	public String getBegintime() {
		return begintime;
	}

	public String getDesc() {
		return desc;
	}

	public String getEndtime() {
		return endtime;
	}

	public String getHtlogo() {
		return htlogo;
	}

	public String getScore() {
		return score;
	}

	public String getStatus() {
		return status;
	}

	public String getStatus_code() {
		return status_code;
	}

	public String getTflag() {
		return tflag;
	}

	public String getTopic() {
		return topic;
	}

	public String getType() {
		return type;
	}

	public void setAtlogo(String atlogo) {
		this.atlogo = atlogo;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public void setHtlogo(String htlogo) {
		this.htlogo = htlogo;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public void setTflag(String tflag) {
		this.tflag = tflag;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setType(String type) {
		this.type = type;
	}

}

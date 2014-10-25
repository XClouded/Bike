package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

public class UserInfoFromServerJsonFormat implements Serializable {

	private static final long serialVersionUID = 3047259347808508713L;

	public UserInfoFromServerJsonFormat() {

	}

	private String fansnum;
	private String head;
	private String idolnum;
	private String MBStat;
	private String name;
	private String nick;
	private String qqnick;
	private String QZoneStat;
	private String retcode;
	private String retpass;
	private String tweetnum;
	private String WeiXinStat;
	private String mediaId; //媒体ID，用来标识是否是媒体人，订阅功能用到
	private String encodeduin;
	private String qqhead;
	

	public String getMediaID(){
		return this.mediaId;
	}
	
	public void setMediaID(String strMediaId){
		this.mediaId = strMediaId;
	}
	
	public String getEnUin(){
		return this.encodeduin;
	}
	
	public void setEnUin(String enUin){
		this.encodeduin = enUin;
	}
	public String getQQHead(){
		return this.qqhead;
	}
	
	public void setQQHead(String strQQHead){
		this.qqhead = strQQHead;
	}
	
	public String getFansnum() {
		return fansnum;
	}

	public String getHead() {
		return head;
	}

	public String getIdolnum() {
		return idolnum;
	}

	public String getMBStat() {
		return MBStat;
	}

	public String getName() {
		return name;
	}

	public String getNick() {
		return nick;
	}

	public String getQqnick() {
		return qqnick;
	}

	public String getQZoneStat() {
		return QZoneStat;
	}

	public String getRetcode() {
		return retcode;
	}

	public String getRetpass() {
		return retpass;
	}

	public String getTweetnum() {
		return tweetnum;
	}

	public String getWeiXinStat() {
		return WeiXinStat;
	}

	public void setFansnum(String fansnum) {
		this.fansnum = fansnum;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public void setIdolnum(String idolnum) {
		this.idolnum = idolnum;
	}

	public void setMBStat(String mBStat) {
		MBStat = mBStat;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setQqnick(String qqnick) {
		this.qqnick = qqnick;
	}

	public void setQZoneStat(String qZoneStat) {
		QZoneStat = qZoneStat;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public void setRetpass(String retpass) {
		this.retpass = retpass;
	}

	public void setTweetnum(String tweetnum) {
		this.tweetnum = tweetnum;
	}

	public void setWeiXinStat(String weiXinStat) {
		WeiXinStat = weiXinStat;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}

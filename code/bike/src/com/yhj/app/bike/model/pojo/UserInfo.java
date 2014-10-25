package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7283443657237597790L;

	String account;

	String sex;
	String uin;
	String skey;
	String luin;
	String lskey;

	String name;
	String nick;
	String headurl;
	String msgTotal;
	String following;
	String follower;
	String qqnick;
	
	String mediaId; //媒体ID，用来标识是否是媒体人，订阅功能用到
	String encodeduin;
	String qqhead;

	boolean isOpenQZone;
	boolean isOpenMBlog;
	boolean isOpenWeiXin;
	
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
	
	public String getUin() {
		return uin;
	}

	public void setUin(String uin) {
		this.uin = uin;
	}

	public String getLuin() {
		return luin;
	}

	public void setLuin(String luin) {
		this.luin = luin;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getLskey() {
		return lskey;
	}

	public void setLskey(String lskey) {
		this.lskey = lskey;
	}

	public String getSkey() {
		return skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getHeadurl() {
		return headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

	public String getMsgTotal() {
		return msgTotal;
	}

	public void setMsgTotal(String msgTotal) {
		this.msgTotal = msgTotal;
	}

	public String getFollowing() {
		return following;
	}

	public void setFollowing(String following) {
		this.following = following;
	}

	public String getFollower() {
		return follower;
	}

	public void setFollower(String follower) {
		this.follower = follower;
	}

	public String getQqnick() {
		return qqnick;
	}

	public void setQqnick(String qqnick) {
		this.qqnick = qqnick;
	}

	public boolean isOpenQZone() {
		return isOpenQZone;
	}

	public void setOpenQZone(boolean isOpenQZone) {
		this.isOpenQZone = isOpenQZone;
	}

	public boolean isOpenMBlog() {
		return isOpenMBlog;
	}

	public void setOpenMBlog(boolean isOpenMBlog) {
		this.isOpenMBlog = isOpenMBlog;
	}

	public boolean isOpenWeiXin() {
		return isOpenWeiXin;
	}

	public void setOpenWeiXin(boolean isOpenWeiXin) {
		this.isOpenWeiXin = isOpenWeiXin;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public String creatCookieStr() {
		String formatUin = "o" + String.format("%010d", Long.parseLong(uin));
		return " lskey=" + lskey + "; " + "skey=" + skey + "; " + "luin=" + formatUin + "; " + "uin=" + formatUin + "; ";
	}
}

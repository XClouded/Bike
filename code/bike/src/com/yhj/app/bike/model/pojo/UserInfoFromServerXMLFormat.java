package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class UserInfoFromServerXMLFormat implements Serializable {

	// <result>
	// <retcode>0</retcode>
	// <retmsg>成功</retmsg>
	// <retpass>0</retpass>
	// </result>
	// <user>
	// <name><![CDATA[cjz010]]></name>
	// <nick><![CDATA[写啥呢]]></nick>
	// <headurl>http://app.qlogo.cn/mbloghead/c2bf0f24632de039f012/100</headurl>
	// <msgTotal>454</msgTotal>
	// <following>103</following>
	// <follower>63</follower>
	// <qqnick><![CDATA[ 写啥呢]]></qqnick>
	// </user>

	private static final long serialVersionUID = 3047259347808508713L;

	// 0成功 1失败
	String retcode;

	String name;
	String nick;
	String headurl;
	String msgToal;
	String following;
	String follower;
	String qqnick;

	boolean isOpenQZone;
	boolean isOpenMBlog;
	boolean isOpenWeiXin;

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
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

	public String getMsgToal() {
		return msgToal;
	}

	public void setMsgToal(String msgToal) {
		this.msgToal = msgToal;
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

}

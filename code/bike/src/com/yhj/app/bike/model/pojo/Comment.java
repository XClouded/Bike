package com.yhj.app.bike.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Comment implements Parcelable {

	public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {
		public Comment createFromParcel(Parcel in) {
			return new Comment(in);
		}

		public Comment[] newArray(int size) {
			return new Comment[size];
		}
	};

	private boolean[] booleans;

	private String agree_count;
	private String char_name;
	private String head_url;
	private boolean isHadUp;
	private boolean isHot;
	private String isOpenMb;
	private String mb_head_url;
	private String mb_nick_name;
	private String nick;
	private String province_city;
	private String pub_time;
	private String reply_content;
	private String reply_id;
	private String sex;
	private String uin;

	private String mb_isvip;
	private String mb_isgroupvip;
	
	private String mb_usr_desc;
	private String mb_usr_desc_detail;
	
	//以下几项为“我的评论”接口新增的（接口地址http://inews.qq.com/getMyComments?reply_id=&pub_time）
	private String commentid;
	private String status;
	private String cattr;
	private String article_title;
	private String article_id;
	private String mediaid;
	private String url;

	public String getIsOpenMb() {
		return isOpenMb;
	}

	public void setIsOpenMb(String isOpenMb) {
		this.isOpenMb = isOpenMb;
	}

	public Comment() {

	}

	private Comment(Parcel in) {
		reply_id = in.readString();
		uin = in.readString();
		nick = in.readString();
		pub_time = in.readString();
		province_city = in.readString();
		reply_content = in.readString();
		agree_count = in.readString();
		head_url = in.readString();
		sex = in.readString();
		isOpenMb = in.readString();
		booleans = new boolean[2];
		in.readBooleanArray(booleans);
		isHot = booleans[0];
		isHadUp = booleans[1];
		char_name = in.readString();
		mb_head_url = in.readString();
		mb_nick_name = in.readString();
		mb_isvip=in.readString();
		mb_isgroupvip=in.readString();
		mb_usr_desc=in.readString();
		mb_usr_desc_detail=in.readString();
		
		commentid = in.readString();
		status = in.readString();
		cattr = in.readString();
		article_title = in.readString();
		article_id = in.readString();
		mediaid = in.readString();
		url = in.readString();
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(reply_id);
		out.writeString(uin);
		out.writeString(nick);
		out.writeString(pub_time);
		out.writeString(province_city);
		out.writeString(reply_content);
		out.writeString(agree_count);
		out.writeString(head_url);
		out.writeString(sex);
		out.writeString(isOpenMb);
		booleans = new boolean[2];
		booleans[0] = isHot;
		booleans[1] = isHadUp;
		out.writeBooleanArray(booleans);
		out.writeString(char_name);
		out.writeString(mb_head_url);
		out.writeString(mb_nick_name);
		out.writeString(mb_isvip);
		out.writeString(mb_isgroupvip);
		out.writeString(mb_usr_desc);
		out.writeString(mb_usr_desc_detail);
		
		out.writeString(commentid);
		out.writeString(status);
		out.writeString(cattr);
		out.writeString(article_title);
		out.writeString(article_id);
		out.writeString(mediaid);
		out.writeString(url);
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	public String getAgreeCount() {
		return this.agree_count;
	}

	public String getChar_name() {
		return char_name;
	}

	public String getHeadUrl() {
		return this.head_url;
	}

	public String getMb_head_url() {
		return mb_head_url;
	}

	public String getMb_nick_name() {
		return mb_nick_name;
	}

	public String getNick() {
		return this.nick;
	}

	public String getProvinceCity() {
		return this.province_city;
	}

	public String getPubTime() {
		return this.pub_time;
	}

	public String getReplyContent() {
		return this.reply_content;
	}

	public String getReplyId() {
		return this.reply_id;
	}

	public String getSex() {
		return this.sex;
	}

	public String getUin() {
		return this.uin;
	}

	public boolean isHadUp() {
		return isHadUp;
	}

	public boolean isHot() {
		return isHot;
	}

	// public boolean isOpenMb() {
	// return isOpenMb;
	// }

	public void setAgreeCount(String agree_count) {
		this.agree_count = agree_count;
	}

	public void setChar_name(String char_name) {
		this.char_name = char_name;
	}

	public void setHadUp(boolean isHadUp) {
		this.isHadUp = isHadUp;
	}

	public void setHeadUrl(String head_url) {
		this.head_url = head_url;
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}

	public void setMb_head_url(String mb_head_url) {
		this.mb_head_url = mb_head_url;
	}

	public void setMb_nick_name(String mb_nick_name) {
		this.mb_nick_name = mb_nick_name;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	// public void setOpenMb(boolean isOpenMb) {
	// this.isOpenMb = isOpenMb;
	// }

	public void setProvinceCity(String province_city) {
		this.province_city = province_city;
	}

	public void setPubTime(String pub_time) {
		this.pub_time = pub_time;
	}

	public void setReplyContent(String reply_content) {
		this.reply_content = reply_content;
	}

	public void setReplyId(String reply_id) {
		this.reply_id = reply_id;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setUin(String uin) {
		this.uin = uin;
	}

	public boolean isOpenMb() {
		return "1".equals(this.isOpenMb);
	}

	public boolean isVip(){
		if(null==mb_isvip){
			return false;
		}
		return "1".equals(this.mb_isvip);
	}
	
	public boolean isGroupVip(){
		if(null==mb_isgroupvip){
			return false;
		}
		return "1".equals(this.mb_isgroupvip);
	}

	public String getMb_usr_desc() {
		return mb_usr_desc;
	}

	public void setMb_usr_desc(String mb_usr_desc) {
		this.mb_usr_desc = mb_usr_desc;
	}

	public String getMb_usr_desc_detail() {
		return mb_usr_desc_detail;
	}

	public void setMb_usr_desc_detail(String mb_usr_desc_detail) {
		this.mb_usr_desc_detail = mb_usr_desc_detail;
	}

	//kiddyliu 20130620 为“我的评论”新加的方法
	public String getCommentID(){
		return this.commentid;
	}
	
	public String getStatus(){
		return this.status;
	}

	public String getCattr(){
		return this.cattr;
	}
	
	public String getArticleTitle(){
		return this.article_title;
	}
	
	public String getArticleID(){
		return this.article_id;
	}
	
	public String getMediaID(){
		return this.mediaid;
	}
	
	public String getUrl(){
		return this.url;
	}
		
	public void setCommentID(String commentid){
		this.commentid = commentid;
	}
	
	public void setStatus(String status){
		this.status = status;
	}

	public void setCattr(String cattr){
		this.cattr = cattr;
	}
	
	public void setArticleTitle(String title){
		this.article_title = title;
	}
	
	public void setArticleID(String articleID){
		this.article_id = articleID;
	}
	
	public void setMediaID(String mediaID){
		this.mediaid = mediaID;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	//kiddyliu 20130620 为“我的评论”新加的方法  结束
}

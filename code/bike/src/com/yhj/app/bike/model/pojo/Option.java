package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Option implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7201371820028795840L;
	
	@SerializedName("ID")
	private String id;
	@SerializedName("OP_TITLE")
	private String op_title;
	@SerializedName("OP_BRIEF")
	private String op_brief;
	@SerializedName("OTHER")
	private String other;
	@SerializedName("OP_PLACE")
	private String op_place;
	@SerializedName("MIN_LEN")
	private String min_len;
	@SerializedName("MAX_LEN")
	private String max_len;
	@SerializedName("OP_IMG")
	private String op_img;
	@SerializedName("OP_LINK_URL")
	private String op_link_url;
	@SerializedName("OP_COUNT")
	private String op_count;
	@SerializedName("OP_PERCENT")
	private String op_percent;
	@SerializedName("IS_RIGHT")
	private String is_right;
	public Option() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOp_title() {
		return op_title;
	}

	public void setOp_title(String op_title) {
		this.op_title = op_title;
	}

	public String getOp_brief() {
		return op_brief;
	}

	public void setOp_brief(String op_brief) {
		this.op_brief = op_brief;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getOp_place() {
		return op_place;
	}

	public void setOp_place(String op_place) {
		this.op_place = op_place;
	}

	public String getMin_len() {
		return min_len;
	}

	public void setMin_len(String min_len) {
		this.min_len = min_len;
	}

	public String getMax_len() {
		return max_len;
	}

	public void setMax_len(String max_len) {
		this.max_len = max_len;
	}

	public String getOp_img() {
		return op_img;
	}

	public void setOp_img(String op_img) {
		this.op_img = op_img;
	}

	public String getOp_link_url() {
		return op_link_url;
	}

	public void setOp_link_url(String op_link_url) {
		this.op_link_url = op_link_url;
	}

	public String getOp_count() {
		return op_count;
	}

	public void setOp_count(String op_count) {
		this.op_count = op_count;
	}

	public String getOp_percent() {
		return op_percent;
	}

	public void setOp_percent(String op_percent) {
		this.op_percent = op_percent;
	}

	@Override
	public String toString() {
		return "Option [id=" + id + ", op_title=" + op_title + ", op_brief="
				+ op_brief + ", other=" + other + ", op_place=" + op_place
				+ ", min_len=" + min_len + ", max_len=" + max_len + ", op_img="
				+ op_img + ", op_link_url=" + op_link_url + ", op_count="
				+ op_count + ", op_percent=" + op_percent + "]";
	}

	public String getIs_right() {
		return is_right;
	}

	public void setIs_right(String is_right) {
		this.is_right = is_right;
	}

	
}

package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SubProject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 245141813205298140L;
	
	@SerializedName("ID")
	private String id;
	@SerializedName("VOTE_TYPE")
	private String vote_type;
	@SerializedName("SBJ_TITLE")
	private String sbj_title;
	@SerializedName("BRIEF")
	private String brief;
	@SerializedName("SBJ_TYPE")
	private String sbj_type;
	@SerializedName("PLACE")
	private String place;
	@SerializedName("IS_NEED")
	private String is_need;
	@SerializedName("SELECT_MAX")
	private String select_max;
	@SerializedName("LINK_URL")
	private String link_url;
	@SerializedName("ALLVOTES")
	private String allVotes; 
	private List< List< Option > > optionList; 
	
	public SubProject() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSbj_title() {
		return sbj_title;
	}

	public void setSbj_title(String sbj_title) {
		this.sbj_title = sbj_title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getSbj_type() {
		return sbj_type;
	}

	public void setSbj_type(String sbj_type) {
		this.sbj_type = sbj_type;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getIs_need() {
		return is_need;
	}

	public void setIs_need(String is_need) {
		this.is_need = is_need;
	}

	public String getSelect_max() {
		return select_max;
	}

	public void setSelect_max(String select_max) {
		this.select_max = select_max;
	}

	public String getLink_url() {
		return link_url;
	}

	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}

	public String getAllVotes() {
		return allVotes;
	}

	public void setAllVotes(String allVotes) {
		this.allVotes = allVotes;
	}

	
	public List<List<Option>> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<List<Option>> optionList) {
		this.optionList = optionList;
	}

	@Override
	public String toString() {
		return "SubProject [id=" + id + ", sbj_title=" + sbj_title + ", brief="
				+ brief + ", sbj_type=" + sbj_type + ", place=" + place
				+ ", is_need=" + is_need + ", select_max=" + select_max
				+ ", link_url=" + link_url + ", allVotes=" + allVotes
				+ ", optionList=" + optionList + "]";
	}

	public String getVote_type() {
		return vote_type;
	}

	public void setVote_type(String vote_type) {
		this.vote_type = vote_type;
	}

	

	
}

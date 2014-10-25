package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Project implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1124998285296291900L;
	
	@SerializedName("ID")
	private String id;
	@SerializedName("PJT_TITLE")
	private String pjt_title;
	@SerializedName("DETAIL")
	private String detail;
	@SerializedName("SORT")
	private String sort;
	@SerializedName("PJT_TYPE")
	private String pjt_type;
	@SerializedName("PUB_STATUS")
	private String pub_status;
	@SerializedName("CREATE_TIME")
	private String create_time;
	@SerializedName("BEGIN_TIME")
	private String begin_time;
	@SerializedName("END_TIME")
	private String end_time;
	@SerializedName("SHOW_RES")
	private String show_res;
	@SerializedName("VOTE_INFO")
	private String vote_info;
	@SerializedName("RULE_ID")
	private String rule_id;
	@SerializedName("LIMIT_OBJ")
	private String limit_obj;
	@SerializedName("CYCLE_TIME")
	private String cycle_time;
	@SerializedName("VOTE_ITEM")
	private String vote_item;
	@SerializedName("VOTE_TOTAL")
	private String vote_total;
	@SerializedName("START_STATUS")
	private String start_status;
	@SerializedName("SERVER_TIME")
	private String server_time;
	private List<SubProject> subProjectList;
	

	public Project() {
		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPjt_title() {
		return pjt_title;
	}


	public void setPjt_title(String pjt_title) {
		this.pjt_title = pjt_title;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public String getSort() {
		return sort;
	}


	public void setSort(String sort) {
		this.sort = sort;
	}


	public String getPjt_type() {
		return pjt_type;
	}


	public void setPjt_type(String pjt_type) {
		this.pjt_type = pjt_type;
	}


	public String getPub_status() {
		return pub_status;
	}


	public void setPub_status(String pub_status) {
		this.pub_status = pub_status;
	}


	public String getCreate_time() {
		return create_time;
	}


	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}


	public String getBegin_time() {
		return begin_time;
	}


	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}


	public String getEnd_time() {
		return end_time;
	}


	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}


	public String getShow_res() {
		return show_res;
	}


	public void setShow_res(String show_res) {
		this.show_res = show_res;
	}


	public String getVote_info() {
		return vote_info;
	}


	public void setVote_info(String vote_info) {
		this.vote_info = vote_info;
	}


	public String getRule_id() {
		return rule_id;
	}


	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
	}


	public String getLimit_obj() {
		return limit_obj;
	}


	public void setLimit_obj(String limit_obj) {
		this.limit_obj = limit_obj;
	}


	public String getCycle_time() {
		return cycle_time;
	}


	public void setCycle_time(String cycle_time) {
		this.cycle_time = cycle_time;
	}


	public String getVote_item() {
		return vote_item;
	}


	public void setVote_item(String vote_item) {
		this.vote_item = vote_item;
	}


	public String getVote_total() {
		return vote_total;
	}


	public void setVote_total(String vote_total) {
		this.vote_total = vote_total;
	}


	public List<SubProject> getSubProjectList() {
		return subProjectList;
	}


	public void setSubProjectList(List<SubProject> subProjectList) {
		this.subProjectList = subProjectList;
	}

	public String getStart_status() {
		return start_status;
	}


	public void setStart_status(String start_status) {
		this.start_status = start_status;
	}


	public String getServer_time() {
		return server_time;
	}


	public void setServer_time(String server_time) {
		this.server_time = server_time;
	}
	
	@Override
	public String toString() {
		return "Project [id=" + id + ", pjt_title=" + pjt_title + ", detail="
				+ detail + ", sort=" + sort + ", pjt_type=" + pjt_type
				+ ", pub_status=" + pub_status + ", create_time=" + create_time
				+ ", begin_time=" + begin_time + ", end_time=" + end_time
				+ ", show_res=" + show_res + ", vote_info=" + vote_info
				+ ", rule_id=" + rule_id + ", limit_obj=" + limit_obj
				+ ", cycle_time=" + cycle_time + ", vote_item=" + vote_item
				+ ", vote_total=" + vote_total + ",start_status=" + start_status
				+ ",server_time=" + server_time + ", subProjectList=" + subProjectList + "]";
	}
}

package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.Gson;

public class ImgTxtLive implements Serializable {

	private static final long serialVersionUID = 402272231352869L;

	private String ret;
	private String server_time;
	private String refresh_time;
	private ImgTxtLiveIntro intro;
	private ImgTxtLiveSummary summary;
	private ImgTxtLiveInfo[] info;

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getRefresh_time() {
		return refresh_time;
	}

	public void setRefresh_time(String refresh_time) {
		this.refresh_time = refresh_time;
	}

	public ImgTxtLiveIntro getIntro() {
		return intro;
	}

	public void setIntro(ImgTxtLiveIntro intro) {
		this.intro = intro;
	}

	public ImgTxtLiveSummary getSummary() {
		return summary;
	}

	public void setSummary(ImgTxtLiveSummary summary) {
		this.summary = summary;
	}

	public ImgTxtLiveInfo[] getInfo() {
		return info;
	}

	public void setInfo(ImgTxtLiveInfo[] info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public String getFirstId() {
		if (info != null && info.length > 0) {
			return info[0].getId();
		} else {
			return null;
		}
	}

	public String getLastId() {
		if (info != null && info.length > 0) {
			return info[info.length - 1].getId();
		} else {
			return null;
		}
	}

	public ArrayList<ImgTxtLiveInfo> array2List(int newSeq) {
		if (info != null && info.length > 0) {
			ArrayList<ImgTxtLiveInfo> al = new ArrayList<ImgTxtLiveInfo>();
			for (ImgTxtLiveInfo itli : info) {
				itli.setNewSeq(newSeq);
				al.add(itli);
			}
			return al;
		} else {
			return new ArrayList<ImgTxtLiveInfo>();
		}
	}

	public String getServer_time() {
		return server_time;
	}

	public void setServer_time(String server_time) {
		this.server_time = server_time;
	}

}

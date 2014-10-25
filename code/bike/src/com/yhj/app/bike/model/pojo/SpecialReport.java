package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;
import com.yhj.app.bike.utils.SLog;

public class SpecialReport implements Serializable {
	private static final long serialVersionUID = -3630515651956337095L;

	private String ret;
	private String intro;


	private String origtitle;
	private SpecialReportImage thumbnails;
	private IdsAndItems[] idlist;

	public IdsAndItems[] getIdlist() {
		return idlist;
	}
	
	public String getOrigtitle() {
		return origtitle;
	}

	public void setOrigtitle(String origtitle) {
		this.origtitle = origtitle;
	}
	public String getIntro() {
		return intro;
	}

	public String getRet() {
		return ret;
	}

	public SpecialReportImage getThumbnails() {
		return thumbnails;
	}

	public void setIdlist(IdsAndItems[] idlist) {
		this.idlist = idlist;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public void setThumbnails(SpecialReportImage thumbnails) {
		this.thumbnails = thumbnails;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	/**
	 * 获取专题里Item所有的id,为了新旧专题对象差分合并准备的方法
	 */
	@Deprecated
	public String getAllIds() {
		if (this.idlist != null && this.idlist.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0, j = this.idlist.length; i < j; i++) {
				if (i != 0) {
					sb.append(",");
				}
				sb.append(this.idlist[i].getAllItem());
			}
			SLog.i("SPECIALREPORT", "ALL local ids are--->" + sb.toString());
			return sb.toString();
		}
		return "";
	}
}
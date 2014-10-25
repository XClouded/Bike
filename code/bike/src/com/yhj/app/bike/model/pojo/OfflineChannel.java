package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 * 离线下载频道包路径，不包括ret和list俩key
 * 
 * @author jackiecheng
 * 
 */
public class OfflineChannel implements Serializable {

	private static final long serialVersionUID = -5120076265269306828L;
	private String chlid;
	private String path;
	private String size;
	private String count;
	private String file_count;// 包里文件数
	private String version;
	private String flag;
	private Id[] ids;

	public String getChlid() {
		return chlid;
	}

	public void setChlid(String chlid) {
		this.chlid = chlid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Id[] getIds() {
		return ids;
	}

	public void setIds(Id[] ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public String getFile_count() {
		return file_count;
	}

	public void setFile_count(String file_count) {
		this.file_count = file_count;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		OfflineChannel other = (OfflineChannel) obj;
		if (chlid == null) {
			if (other.chlid != null){
				return false;
			}
		} else if (!chlid.equals(other.chlid)) {
			return false;
		}
		return true;
	}

}

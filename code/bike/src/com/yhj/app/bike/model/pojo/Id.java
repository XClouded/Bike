package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

public class Id implements Serializable {

	private static final long serialVersionUID = 2092362228879871217L;
	String id;
	String exist;
	String comments;
	String video_hits;
	String hidepic;

	public Id() {

	}

	public Id(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExist() {
		return exist;
	}

	public void setExist(String exist) {
		this.exist = exist;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getHidepic() {
		return hidepic;
	}

	public void setHidepic(String hidepic) {
		this.hidepic = hidepic;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o instanceof Id) {
			return ((Id) o).getId().equals(this.id);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public String getVideo_hits() {
		return video_hits;
	}

	public void setVideo_hits(String video_hits) {
		this.video_hits = video_hits;
	}

}

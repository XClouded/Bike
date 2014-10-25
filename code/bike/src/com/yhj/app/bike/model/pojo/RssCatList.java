package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class RssCatList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8943616111240888154L;

	private String ret;
	private String version;
	private List<RssCatListCat> cats;
	private HashMap<String, String> subCounts;
    private List<RssCatListItem> selected;
    

    public RssCatList() {

    }

	public String getRet() {
		return ret;
	}

	public HashMap<String, String> getSubCounts() {
        return subCounts;
    }

    public void setSubCounts(HashMap<String, String> subCounts) {
        this.subCounts = subCounts;
    }

    public List<RssCatListItem> getSelected() {
        return selected;
    }

    public void setSelected(List<RssCatListItem> selected) {
        this.selected = selected;
    }

    public void setRet(String ret) {
		this.ret = ret;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<RssCatListCat> getCats() {
		return cats;
	}

	public void setCats(List<RssCatListCat> cats) {
		this.cats = cats;
	}
}

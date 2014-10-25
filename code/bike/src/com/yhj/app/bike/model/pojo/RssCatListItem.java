package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class RssCatListItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2004281196449290092L;
	private String chlid;
	private String chlname;
	private String icon;
	private String desc;
	private String sicon;
	private String subCount;
	private String keywords;
    private String uin;
	private String intro;
    private String recommend;
    private RssCatListItemSearch[] alias;
    private boolean isEmpty = false;

	public RssCatListItem() {

	}
	

	public RssCatListItemSearch[] getAlias() {
        return alias;
    }


    public void setAlias(RssCatListItemSearch[] alias) {
        this.alias = alias;
    }


    public String getChlid() {
		return chlid;
	}

	public void setChlid(String chlid) {
		this.chlid = chlid;
	}

	public String getChlname() {
		return chlname;
	}

	public void setChlname(String chlname) {
		this.chlname = chlname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSicon() {
		return sicon;
	}

	public void setSicon(String sicon) {
		this.sicon = sicon;
	}

	public String getSubCount() {
		return subCount;
	}

	public void setSubCount(String subCount) {
		this.subCount = subCount;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	
	public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    private boolean equals(String s1, String s2) {
		if (s1 == null && s2 == null) {
			return true;
		} else if (s1 != null) {
			return s1.equals(s2);
		} else {
			return false;
		}
	}
}

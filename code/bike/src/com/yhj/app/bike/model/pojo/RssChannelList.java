package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.List;

public class RssChannelList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8048409392261191351L;
	private String ret;
	private String version;
	private List<RssCatListItem> channellist;

	public RssChannelList() {

	}

    public String getRet() {
        return ret;
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

    public List<RssCatListItem> getChannellist() {
        return channellist;
    }

    public void setChannellist(List<RssCatListItem> channellist) {
        this.channellist = channellist;
    }
	

}

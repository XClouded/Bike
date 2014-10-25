package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class RssSubItem implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = -8099509796068320812L;

	private String ret;
    private RssCatListItem channelInfo;

    public RssSubItem() {

    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public RssCatListItem getChannelInfo() {
        return channelInfo;
    }

    public void setChannelInfo(RssCatListItem channelInfo) {
        this.channelInfo = channelInfo;
    }

}

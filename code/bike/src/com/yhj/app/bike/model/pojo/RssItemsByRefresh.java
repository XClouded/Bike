
package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.List;

import com.google.gson.Gson;

/**
 * @author jianhu
 * @version 创建时间：2013-6-12 下午2:19:28 
 * 类说明
 */
public class RssItemsByRefresh implements Serializable {

    private static final long serialVersionUID = 7069722491253443170L;
    private String ret;
    private String version;
	private List<RssCatListItem> userSubList;
    private RssId ids[];
    private Item newslist[];

    public RssItemsByRefresh() {

    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public RssId[] getIds() {
        return ids;
    }

    public void setIds(RssId[] ids) {
        this.ids = ids;
    }

    public Item[] getNewslist() {
        return newslist;
    }

    public void setNewslist(Item[] newslist) {
        this.newslist = newslist;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<RssCatListItem> getUserSubList() {
		return userSubList;
	}

	public void setUserSubList(List<RssCatListItem> userSubList) {
		this.userSubList = userSubList;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public void setDataIsRss() {
        if (newslist != null) {
            for (Item  item : newslist) {
                item.setIsRss(true);
            }
        }
    }

}

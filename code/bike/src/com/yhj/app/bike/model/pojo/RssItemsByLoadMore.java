
package com.yhj.app.bike.model.pojo;

import com.google.gson.Gson;

import java.io.Serializable;

/**
* @author jianhu
* @version 创建时间：2013-6-12 下午2:29:11
* 类说明
*/
public class RssItemsByLoadMore implements Serializable {

    private static final long serialVersionUID = -1977276456282738940L;
    private String ret;
    private Item newslist[];

    public RssItemsByLoadMore() {

    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public Item[] getNewslist() {
        return newslist;
    }

    public void setNewslist(Item[] newslist) {
        this.newslist = newslist;
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

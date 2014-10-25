
package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 * @author jianhu
 * @version 创建时间：2013-6-12 下午2:19:28 类说明
 */
public class FavorItemsByRefresh implements Serializable {

    private static final long serialVersionUID = 7125843012142820267L;
    private String ret;
    private String index_count;
    private FavorId index[];
    private Item listitems[];
    private String listitems_lost[];

    public FavorItemsByRefresh() {

    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public FavorId[] getIndex() {
        return index;
    }

    public void setIndex(FavorId[] index) {
        this.index = index;
    }

    public Item[] getListitems() {
        return listitems;
    }

    public void setListitems(Item[] listitems) {
        this.listitems = listitems;
    }

    public String[] getListitems_lost() {
        return listitems_lost;
    }

    public void setListitems_lost(String[] listitems_lost) {
        this.listitems_lost = listitems_lost;
    }

    public String getIndex_count() {
        return index_count;
    }

    public void setIndex_count(String index_count) {
        this.index_count = index_count;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

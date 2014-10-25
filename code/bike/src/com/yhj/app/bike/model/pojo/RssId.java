
package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 * @author jianhu
 * @version 创建时间：2013-6-12 下午2:03:41 
 * 类说明
 */
public class RssId implements Serializable {

    private static final long serialVersionUID = 5749541212643646768L;
    private String id;
    private String comments;

    public RssId() {

    }

    public RssId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof RssId) {
            return ((RssId) o).getId().equals(this.id);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

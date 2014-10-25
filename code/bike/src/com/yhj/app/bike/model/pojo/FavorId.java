
package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 * @author jianhu
 * @version 创建时间：2013-6-14 上午11:09:27 
 * 类说明
 */
public class FavorId implements Serializable {

    private static final long serialVersionUID = -7939572285545478058L;
    private String id;
    private String id_type;
    private String time;

    public FavorId() {
    }

    public FavorId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof FavorId) {
            return ((FavorId) o).getId().equals(this.id);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

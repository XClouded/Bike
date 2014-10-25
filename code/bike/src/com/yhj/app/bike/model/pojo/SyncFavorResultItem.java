package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class SyncFavorResultItem implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7883909338522819496L;

    private String id;
    private String ret;

    public SyncFavorResultItem() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }
}

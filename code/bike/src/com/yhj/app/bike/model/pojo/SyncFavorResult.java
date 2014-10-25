package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.List;

public class SyncFavorResult implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2563071781055231022L;

    private String ret;
    private List<SyncFavorResultItem> add;
    private List<SyncFavorResultItem> del;

    public SyncFavorResult() {

    }
    
    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }


    public List<SyncFavorResultItem> getAdd() {
        return add;
    }

    public void setAdd(List<SyncFavorResultItem> add) {
        this.add = add;
    }


    public List<SyncFavorResultItem> getDel() {
        return del;
    }

    public void setDel(List<SyncFavorResultItem> del) {
        this.del = del;
    }
}

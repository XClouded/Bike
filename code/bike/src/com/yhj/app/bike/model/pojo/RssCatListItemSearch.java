package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class RssCatListItemSearch implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 813633024362966443L;

    private String            flag;
    private String            token;

    public RssCatListItemSearch() {

    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

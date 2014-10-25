package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.List;

public class RssFirstSubscriptionList implements Serializable {

    private static final long serialVersionUID = 2420927265988898027L;

    private String ret;
    private String isRecomm;
    private List<RssCatListItem> userSubList;

    public RssFirstSubscriptionList() {

    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getIsRecomm() {
        return isRecomm;
    }

    public void setIsRecomm(String isRecomm) {
        this.isRecomm = isRecomm;
    }

    public List<RssCatListItem> getUserSubList() {
        return userSubList;
    }

    public void setUserSubList(List<RssCatListItem> userSubList) {
        this.userSubList = userSubList;
    }

}

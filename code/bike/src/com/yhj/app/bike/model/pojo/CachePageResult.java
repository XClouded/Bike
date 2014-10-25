
package com.yhj.app.bike.model.pojo;

import java.util.List;

public class CachePageResult<T> {

    public static final int RES_OK = 1;
    public static final int RES_FAIL = -1;
    private int resultCode = -1;
    private String needLoadIds;
    private List<T> cachePageData;

    public void setResultOK() {
        resultCode = RES_OK;
    }

    public boolean isResultFail() {
        return (resultCode == RES_FAIL);
    }

    public void setResultFail() {
        resultCode = RES_FAIL;
    }

    public boolean isResultOK() {
        return (resultCode == RES_OK);
    }

    public String getNeedLoadIds() {
        return needLoadIds;
    }

    public void setNeedLoadIds(String needLoadIds) {
        this.needLoadIds = needLoadIds;
    }

    public List<T> getCachePageData() {
        return cachePageData;
    }

    public void setCachePageData(List<T> cachePageData) {
        this.cachePageData = cachePageData;
    }

}

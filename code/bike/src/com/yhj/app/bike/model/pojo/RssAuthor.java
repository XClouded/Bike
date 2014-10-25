
package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

/**
 * @author jianhu
 * @version 创建时间：2013-6-12 下午1:59:03 类说明
 */
public class RssAuthor implements Serializable {

    private static final long serialVersionUID = -2512734834005304334L;

    private String authorName;
    private String authorUrl;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

}

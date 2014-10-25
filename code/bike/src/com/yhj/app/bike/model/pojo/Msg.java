
package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class Msg implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 366317158469328701L;
    private String t;
    private String a;
    private String i;
    private String s;

    public void setMsg(String a) {
        this.a = a;
    }

    public String getMsg() {
        return this.a;
    }

    public void setNewsId(String i) {
        this.i = i;
    }

    public String getNewsId() {
        return this.i;
    }

    public void setChlid(String s) {
        this.s = s;
    }

    public String getChlid() {
        return this.s;
    }

    public String getTitle() {
        return t;
    }

    public void setTitle(String t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "[" + "s:" + this.s + " i:" + this.i + " a:" + this.a + "]";
    }

}

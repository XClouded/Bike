package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class CheckSubscribe implements Serializable{
	private class Num {
		private String at;
		private String mail;
	}
	private static final long serialVersionUID = -3397840399787347749L;

	private Num num;
	private String ret;
	private String updateFlag;
	
	public CheckSubscribe(){
	}
	
	public String getRet() {
		return ret;
	}
	
	public void setRet(String ret) {
		this.ret = ret;
	}
	
	public String getUpdateFlag() {
		return updateFlag;
	}
	
	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	
	public void setMail(String mail){
	    this.num.mail = mail;
	}
	
	public String getMail(){
        return num.mail;
    }
	
	public void setAt(String atNum){
		this.num.at = atNum;
	}
	
	public String getAt(){
		return this.num.at;
	}
}

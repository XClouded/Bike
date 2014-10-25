package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.tencent.mm.sdk.openapi.SendAuth;

public class WXUserInfo implements Serializable {

	private static final long serialVersionUID = 7283443657237597790L;

	String token;
	int errCode;
	String errStr;
	int expireDate;
	String resultUrl;
	String state;
	String userName;
	String transaction;

	public WXUserInfo() {

	}

	public WXUserInfo(SendAuth.Resp r) {
		this.token = r.token;
		this.errCode = r.errCode;
		this.errStr = r.errStr;
		this.expireDate = r.expireDate;
		this.resultUrl = r.resultUrl;
		this.state = r.state;
		this.userName = r.userName;
		this.transaction = r.transaction;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrStr() {
		return errStr;
	}

	public void setErrStr(String errStr) {
		this.errStr = errStr;
	}

	public int getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(int expireDate) {
		this.expireDate = expireDate;
	}

	public String getResultUrl() {
		return resultUrl;
	}

	public void setResultUrl(String resultUrl) {
		this.resultUrl = resultUrl;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
}

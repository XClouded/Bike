package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class BetaApkUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7224799662882938861L;
	private String ret;
	private String loginApp;
	
	public BetaApkUser(){
		
	}
	
	public String getLoginApp() {
		return loginApp;
	}
	
	public void setLoginApp(String loginApp) {
		this.loginApp = loginApp;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}
	
}

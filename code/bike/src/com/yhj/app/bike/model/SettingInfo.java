package com.yhj.app.bike.model;

import com.yhj.app.bike.model.pojo.UserInfo;

/**
 * 用于存放设置信息
 */
public class SettingInfo extends BaseData {

	private UserInfo userInfo;

	private boolean ifPush;
	private boolean ifAutoLoadMore;
	private boolean ifTextMode;	
	private int textSize;	
	//private int themeSetting; //模式设置, 0:日间模式（默认） 1:夜间模式
	
	/*public int getThemeSetting(){
		return this.themeSetting;		
	}

	public void setTheme(int iTheme) {
		this.themeSetting = iTheme;
	}*/	
	
	public boolean isIfTextMode() {
		return ifTextMode;
	}

	public void setIfTextMode(boolean ifTextMode) {
		this.ifTextMode = ifTextMode;
	}	

	@Override
	public boolean realEquals(Object o) {
		return false;
	}

	public boolean isIfPush() {
		return ifPush;
	}

	public void setIfPush(boolean ifPush) {
		this.ifPush = ifPush;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}

	public boolean isIfAutoLoadMore() {
		return ifAutoLoadMore;
	}

	public void setIfAutoLoadMore(boolean ifAutoLoadMore) {
		this.ifAutoLoadMore = ifAutoLoadMore;
	}

}

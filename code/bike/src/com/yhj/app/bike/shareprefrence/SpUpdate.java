package com.yhj.app.bike.shareprefrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.system.Application;
import com.yhj.app.bike.utils.MobileUtil;

public class SpUpdate {

	public static final int START_FROM_NORMAL = 0;
	public static final int START_FROM_NEW_INSTALL = 1;
	public static final int START_FROM_UPDATE = 2;

	/**
	 * 保存版本号
	 * @param code
	 */
	public static void saveVer(int code) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_UPDATE_VERSION_CODE, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt(Constants.SP_UPDATE_VERSION_CODE, code);
		editor.commit();
	}

	private static int getVer() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_UPDATE_VERSION_CODE, Context.MODE_PRIVATE);
		return sp.getInt(Constants.SP_UPDATE_VERSION_CODE, START_FROM_NEW_INSTALL);
	}

	/**
	 * 从啥玩意儿地方启动的<br/>
	 * 
	 * @return 正常启动 START_FROM_NORMAL = 0;<br/>
	 *         全新安装 START_FROM_NEW_INSTALL = 1;<br/>
	 *         更新启动 START_FROM_UPDATE = 2;
	 * 
	 */
	public static int startFromWhat() {
		if (getVer() == START_FROM_NEW_INSTALL) {
			return START_FROM_NEW_INSTALL;
		}
		if (MobileUtil.getVersionCode() > getVer()) {
			return START_FROM_UPDATE;
		}
		return START_FROM_NORMAL;
	}

}

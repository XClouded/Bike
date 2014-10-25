package com.yhj.app.bike.shareprefrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.yhj.app.bike.cache.UserDBHelper;
import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.model.SettingInfo;
import com.yhj.app.bike.system.Application;

public class SpSetting {
	public static SharedPreferences getSettingSharedPreferences() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_SETTING, Context.MODE_PRIVATE);
		sp.edit().commit();
		return sp;
	}

	public static void saveSetting(SettingInfo setting) {

		if (setting.getUserInfo() != null) {
			UserDBHelper.getInstance().saveUserInfo(setting.getUserInfo());
		} else {
			UserDBHelper.getInstance().logoutUserInfo();
		}

		Editor editor = getSettingSharedPreferences().edit();
		editor.putBoolean(Constants.SETTING_KEY_IF_AUTO_LOAD_MORE, setting.isIfAutoLoadMore());
		editor.putBoolean(Constants.SETTING_KEY_IF_TEXT_MODE, setting.isIfTextMode());
		editor.putBoolean(Constants.SETTING_KEY_IF_PUSH, setting.isIfPush());
		editor.putInt(Constants.SETTING_KEY_TEXT_SIZE, setting.getTextSize());
		//editor.putInt(Constants.SETTING_THEME, setting.getThemeSetting());		
		editor.commit();
	}
}

package com.yhj.app.bike.system.observable;

import com.yhj.app.bike.cache.UserDBHelper;
import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.model.SettingInfo;
import com.yhj.app.bike.shareprefrence.SpSetting;
import com.yhj.app.bike.system.observer.SettingObserver;
import com.yhj.app.bike.utils.SLog;

public class SettingObservable extends BaseObservable<SettingInfo, SettingObserver> {
	private static SettingObservable instance = new SettingObservable();

	public static synchronized SettingObservable getInstance() {
		return instance;
	}

	@Override
	protected void update(SettingObserver o, SettingInfo t) {
		// TODO Auto-generated method stub
		if (o != null) {
			o.updateSetting(t);
		}
	}

	/**
	 * setting信息单独做初始化处理
	 * 
	 */
	public synchronized SettingInfo getData() {
		if (data != null) {
			return data;
		} else {
			data = new SettingInfo();
			getSettingFromSharedPreferences();
			return data;
		}
	}

	/**
	 * 若数据为空，则从本地文件或本地默认值中取数据
	 */
	private void getSettingFromSharedPreferences() {

		data.setUserInfo(UserDBHelper.getInstance().getUserInfo());
		data.setIfPush(SpSetting.getSettingSharedPreferences().getBoolean(Constants.SETTING_KEY_IF_PUSH, Constants.SETTING_IF_PUSH));
		data.setIfAutoLoadMore(SpSetting.getSettingSharedPreferences().getBoolean(Constants.SETTING_KEY_IF_AUTO_LOAD_MORE, Constants.SETTING_IF_AUTO_LOAD_MORE));
		data.setTextSize(SpSetting.getSettingSharedPreferences().getInt(Constants.SETTING_KEY_TEXT_SIZE, Constants.SETTING_MID_TEXT_SIZE));
		data.setIfTextMode(SpSetting.getSettingSharedPreferences().getBoolean(Constants.SETTING_KEY_IF_TEXT_MODE, Constants.SETTING_IF_TEXT_MODE));
		//data.setTheme(SpSetting.getSettingSharedPreferences().getInt(Constants.SETTING_THEME, Constants.SETTING_THEME_DEFAULT_MODE));
		
		SLog.v("SettingObservable get setting data is");
		SLog.v("info.isIfPush = " + data.isIfPush());
		SLog.v("info.isIfAutoloadMore = " + data.isIfAutoLoadMore());
		SLog.v("info.isIfTextMode = " + data.isIfTextMode());
		//SLog.v("info.isIfNightMode = " + data.getThemeSetting());
		SLog.v("info.textSize = " + data.getTextSize());
	}
}

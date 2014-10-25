package com.yhj.app.bike.shareprefrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.system.Application;

public class SpUserHelp {
	//文章底层help
	public static void setNewsDetailHelp(boolean bFlag) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(
				Constants.SP_HELP, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean(Constants.NEWS_DETAIL_HELP, bFlag);
		editor.commit();
	}
	
	public static boolean getNewsDetailHelp() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(
				Constants.SP_HELP, Context.MODE_PRIVATE);
		return sp.getBoolean(Constants.NEWS_DETAIL_HELP,true);
	}
	
	//图片底层help
	public static void setImageDetailHelp(boolean bFlag) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(
				Constants.SP_HELP, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean(Constants.IMAGE_DETAIL_HELP, bFlag);
		editor.commit();
	}
	
	public static boolean getImageDetailHelp() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(
				Constants.SP_HELP, Context.MODE_PRIVATE);
		return sp.getBoolean(Constants.IMAGE_DETAIL_HELP,true);
	}
	
	//视频底层
	public static void setVideoDetailHelp(boolean bFlag) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(
				Constants.SP_HELP, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean(Constants.VIDEO_DETAIL_HELP, bFlag);
		editor.commit();
	}
	
	public static boolean getVideoDetailHelp() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(
				Constants.SP_HELP, Context.MODE_PRIVATE);
		return sp.getBoolean(Constants.VIDEO_DETAIL_HELP,true);
	}

	// 订阅列表页help
    public static void setRssListHelp(boolean bFlag) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(
                Constants.SP_HELP, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(Constants.RSS_LIST_HELP, bFlag);
        editor.commit();
    }
    
    public static boolean getRssListHelp() {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(
                Constants.SP_HELP, Context.MODE_PRIVATE);
        return sp.getBoolean(Constants.RSS_LIST_HELP,true);
    }
    
	public static void delAllHelp() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_HELP, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}
}

package com.yhj.app.bike.shareprefrence;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.system.Application;

public class SpForbidenCommentNews {

	/**
	 * 保存id新闻为已被禁止
	 * 
	 * @param id
	 */
	public static void saveForbidenCommentNews(String id) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_FORBID_COMMENT_NEWS, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean(id, true);
		editor.commit();
	}

	/**
	 * 
	 * @param id
	 * @return false该id的新闻可以被评论,true表示该id的新闻禁止评论
	 */
	public static boolean getForbidenCommentNews(String id) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_FORBID_COMMENT_NEWS, Context.MODE_PRIVATE);
		return sp.getBoolean(id, false);
	}

	public static void delForbidenCommentNews(String id) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_FORBID_COMMENT_NEWS, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove(id);
		editor.commit();
	}

	public static void delForbidenCommentNews(List<String> delIds) {
		if (delIds != null && delIds.size() > 0) {
			SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_FORBID_COMMENT_NEWS, Context.MODE_PRIVATE);
			Editor editor = sp.edit();
			for (String id : delIds) {
				editor.remove(id);
			}
			editor.commit();
		}
	}

	public static void delForbidenCommentNews(String[] delIds) {
		if (delIds != null && delIds.length > 0) {
			SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_FORBID_COMMENT_NEWS, Context.MODE_PRIVATE);
			Editor editor = sp.edit();
			for (String id : delIds) {
				editor.remove(id);
			}
			editor.commit();
		}
	}

	public static void delAll() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_FORBID_COMMENT_NEWS, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}
}

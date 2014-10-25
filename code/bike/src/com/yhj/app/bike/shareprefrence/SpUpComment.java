package com.yhj.app.bike.shareprefrence;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.system.Application;

/**
 * 顶一下,本地模拟
 * 
 * @author jackiecheng
 * 
 */
public class SpUpComment {

	/**
	 * 保存顶过的评论id
	 * 
	 * @param id
	 */
	public static void saveUpCommentId(String replayId) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_UP_COMMENT, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean(replayId, true);
		editor.commit();
	}

	/**
	 * 
	 * @param id
	 * @return false该id的评论没有被顶过,true表示顶过该id的评论
	 */
	public static boolean getUpComment(String id) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_UP_COMMENT, Context.MODE_PRIVATE);
		return sp.getBoolean(id, false);
	}

	public static void delUpCommentId(List<String> delIds) {
		if (delIds != null && delIds.size() > 0) {
			SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_UP_COMMENT, Context.MODE_PRIVATE);
			Editor editor = sp.edit();
			for (String id : delIds) {
				editor.remove(id);
			}
			editor.commit();
		}
	}

	public static void delUpCommentId(String[] delIds) {
		if (delIds != null && delIds.length > 0) {
			SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_UP_COMMENT, Context.MODE_PRIVATE);
			Editor editor = sp.edit();
			for (String id : delIds) {
				editor.remove(id);
			}
			editor.commit();
		}
	}

}

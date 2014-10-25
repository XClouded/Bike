package com.yhj.app.bike.shareprefrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.system.Application;

public class SpTopicHadVoted {
	// 保存投票观点id
	public static void saveVoteOptid(String subid, String optid) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_VOTE_OPTID, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(subid, optid);
		editor.commit();
	}
	
	public static String getVoteOptid(String subid) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_VOTE_OPTID, Context.MODE_PRIVATE);
		return sp.getString(subid, "");
	}
	
	public static void delVoteOptidAll() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_VOTE_OPTID, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}
	
	public static void delVoteOptid(String subid) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_VOTE_OPTID, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove(subid);
		editor.commit();
	}
}

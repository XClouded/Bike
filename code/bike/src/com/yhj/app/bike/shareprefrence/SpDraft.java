package com.yhj.app.bike.shareprefrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.system.Application;

public class SpDraft {
	public static void saveDraft(String idAndReplayId, String input, String saveTo) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(saveTo, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(idAndReplayId, input);
		editor.commit();
	}

	public static void delDraft(String idAndReplayId, String saveTo) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(saveTo, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove(idAndReplayId);
		editor.commit();
	}

	private static void delAll(String saveTo) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(saveTo, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}
	public static void delAll() {
		delAll(Constants.SP_DRAFT);
		delAll(Constants.SHARE_DRAFT);
	}

	public static String getDraft(String idAndReplayId, String saveTo) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(saveTo, Context.MODE_PRIVATE);
		return sp.getString(idAndReplayId, "");
	}
}

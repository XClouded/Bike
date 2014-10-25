package com.yhj.app.bike.shareprefrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.model.pojo.OfflineChannel;
import com.yhj.app.bike.system.Application;

public class SpOfflineProgress {

	public static int getChannelProgress(OfflineChannel channel) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_OFFLINE_PROGRESS, Context.MODE_PRIVATE);
		if (channel != null) {
			return sp.getInt(channel.getPath(), 0);
		} else {
			return 0;
		}
	}

	public static void setChannelProgress(OfflineChannel channel, int progress) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_OFFLINE_PROGRESS, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		if (channel != null) {
			editor.putInt(channel.getPath(), progress);
		}
		editor.commit();
	}

	public static String getChannelProgressID(OfflineChannel channel) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_OFFLINE_PROGRESS, Context.MODE_PRIVATE);
		if (channel != null) {
			return sp.getString(channel.getChlid() + channel.getPath(), "");
		} else {
			return "";
		}
	}

	public static void setChannelProgressID(OfflineChannel channel, String id) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_OFFLINE_PROGRESS, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		if (channel != null) {
			editor.putString(channel.getChlid() + channel.getPath(), id);
		}
		editor.commit();
	}

	public static void delAll() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_OFFLINE_PROGRESS, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}

}

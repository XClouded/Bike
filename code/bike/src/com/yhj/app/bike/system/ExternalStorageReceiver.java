package com.yhj.app.bike.system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import com.yhj.app.bike.utils.SLog;

public class ExternalStorageReceiver extends BroadcastReceiver {
	public static boolean isSDCardMounted = false;

	@Override
	public void onReceive(Context context, Intent intent) {
		// Auto-generated method stub
		if (intent.getAction().equals("android.intent.action.MEDIA_MOUNTED")) {
			isSDCardMounted = true;
		} else if (intent.getAction().equals("android.intent.action.MEDIA_REMOVED")
				|| intent.getAction().equals("android.intent.action.ACTION_MEDIA_UNMOUNTED")
				|| intent.getAction().equals("android.intent.action.ACTION_MEDIA_BAD_REMOVAL")) {
			isSDCardMounted = false;
		} else {
			ExternalStorageReceiver.isSDCardMounted = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
		}
		
		SLog.v(SLog.BIKE , "isSDCardMounted = " + isSDCardMounted);
	}
}

package com.yhj.app.bike.system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.yhj.app.bike.config.Constants;

public class ChannelLoadReceiver extends BroadcastReceiver {
	private Handler mHandler = null;
	
	public ChannelLoadReceiver(Handler mHandler){
		this.mHandler = mHandler;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		//Message msg = Message.obtain();
		mHandler.sendEmptyMessage(Constants.WHAT_CHANNEL_LOAD);
	}
}

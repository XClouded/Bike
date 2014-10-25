package com.yhj.app.bike.download;
/*package com.tencent.news.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DownloadPackageReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		String packageName = "";
		
		if(intent.getAction().equals("android.intent.action.PACKAGE_ADDED")){
			packageName = intent.getDataString();
			Log.v("vincesun", "程序安装了:" + packageName);
			DownloadManager.appInstalled(packageName);
			
		}
		
		if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) { 
            packageName = intent.getDataString();  
            Log.i("vincesun", "卸载了 :" + packageName);  
            DownloadManager.appUnInstalled(packageName);
        }  
		
	}

}
*/
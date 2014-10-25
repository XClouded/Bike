package com.yhj.app.bike.utils;

import java.io.File;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;

import com.yhj.app.bike.R;


public class ApkUtil  {

	public static final String FILE_PATH_HEADER = "file://";

	public static final String APP_PACKAGE_ARCHIVE = "application/vnd.android.package-archive";

	public static final String APK_ACTION_DELETE = "android.intent.action.DELETE";

	public static final String EXTRA_INSTALLER_PACKAGE_NAME = "android.intent.extra.INSTALLER_PACKAGE_NAME";
	
	public static final String SELF_APPLICATION = "com.tencent.news";
	
	private static final String PREFERENCE_KEY_SHORTCUT_EXISTS = "IsShortCutExists";
	
	private static final String CREATE_SHORTCUT_ACTION = "com.android.launcher.action.INSTALL_SHORTCUT";
	
	private static final String DROP_SHORTCUT_ACTION = "com.android.launcher.action.UNINSTALL_SHORTCUT";
	
	private static SharedPreferences sharedPreferences;
	private static boolean exists;

	public static void installApk(Context context, String apkPath) {
		if(new File(apkPath).exists()) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setDataAndType(Uri.parse(FILE_PATH_HEADER + apkPath),
					APP_PACKAGE_ARCHIVE);
			intent.putExtra(EXTRA_INSTALLER_PACKAGE_NAME, apkPath);
			context.startActivity(intent);
		} else {
		//	TipsToast.getInstance().showTipsWarning(Application.getInstance().getString(R.string.file_not_exists));
		}
	}
	
	
	public static void createShortcut(Context context) {
		
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
       exists = sharedPreferences.getBoolean(PREFERENCE_KEY_SHORTCUT_EXISTS, false);
        
       if (!exists) {
        	Intent shortcutIntent = null;
    		shortcutIntent = new Intent(Intent.ACTION_MAIN);
    		shortcutIntent.setClassName(context, context.getClass().getName());
    		shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
    		shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

    		final Intent intent = new Intent();
    		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
    		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME,
    				context.getResources().getString(R.string.app_name));
    		/*intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
    				Intent.ShortcutIconResource.fromContext(
    						context,R.drawable.icon_test));*/
    		intent.putExtra("duplicate", false);
    		intent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
    		// sendBroadcast(intent);
    		context.sendOrderedBroadcast(intent, null);
//    		if (hasShortCut(context)) {
//   			return;
//    		}

    		intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
    		// sendBroadcast(intent);
    		context.sendOrderedBroadcast(intent, null);
    		
    		Editor editor = sharedPreferences.edit();
            editor.putBoolean(PREFERENCE_KEY_SHORTCUT_EXISTS, true);
            editor.commit();
        }
		
		

	}

	
	public static boolean hasShortCut(Context context) {
		String url = "";
		// System.out.println(getSystemVersion());
		if (android.os.Build.VERSION.SDK_INT < 8) {
			url = "content://com.android.launcher.settings/favorites?notify=true";
			SLog.i("HOT","<8");
		} else {
			url = "content://com.android.launcher2.settings/favorites?notify=true";
			SLog.i("HOT",">8");
		}
		
		
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(Uri.parse(url), null, "title=?",
				new String[] { context.getString(R.string.app_name) }, null);
		if (cursor != null) {
			
		    boolean b = cursor.moveToFirst();
		    SLog.i("HOT","!null : "+b);
			cursor.close();
			return b;
		}
		SLog.i("HOT","null");
		return false;
	}
	
	
	
	public static void shortcuts(Context context){
		
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        exists = sharedPreferences.getBoolean(PREFERENCE_KEY_SHORTCUT_EXISTS, false);
        if (!exists) {
        	delShortcuts(context);
        	
        	Intent shortcut = new Intent(CREATE_SHORTCUT_ACTION);  
    	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getString(R.string.app_name));  
    	    shortcut.putExtra("duplicate", false);  
    	    String action = "com.tencent.news.activity";  
    	  /*  Intent respondIntent = new Intent(context, SplashActivity.class);  
    	    respondIntent.setAction(action);  
    	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, respondIntent);  */
    	    ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(context, R.drawable.ic_launcher);  
    	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);  
    	    context.sendOrderedBroadcast(shortcut,null);
    	    Editor editor = sharedPreferences.edit();
            editor.putBoolean(PREFERENCE_KEY_SHORTCUT_EXISTS, true);
            editor.commit();
        }
        
	}
	
//	public static boolean shortCutInstalled(Context cx)
//	{
//		boolean result = false;
//	    // 获取当前应用名称
//	    String title = null;
//	    try {
//	        final PackageManager pm = cx.getPackageManager();
//	        title = pm.getApplicationLabel(
//	                pm.getApplicationInfo(cx.getPackageName(),
//	                        PackageManager.GET_META_DATA)).toString();
//	    } catch (Exception e) {
//	    }
//
//	    final String uriStr;
//	    if (android.os.Build.VERSION.SDK_INT < 8) {
//	        uriStr = "content://com.android.launcher.settings/favorites?notify=true";
//	    } else {
//	        uriStr = "content://com.android.launcher2.settings/favorites?notify=true";
//	    }
//	    final Uri CONTENT_URI = Uri.parse(uriStr);
//	    final Cursor c = cx.getContentResolver().query(CONTENT_URI, null,
//	            "title=?", new String[] { title }, null);
//	    if (c != null && c.getCount() > 0) {
//	        result = true;
//	    }
//	    return result;
//	}
	
	
	
	
	public static void delShortcuts(Context context)
	{
		Intent shortcut = new Intent(DROP_SHORTCUT_ACTION);    
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getString(R.string.app_name));    
	    String action = "com.tencent.news.activity";  
	   /* Intent respondIntent = new Intent(context, SplashActivity.class);  
	    respondIntent.setAction(action);  
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, respondIntent);   */
	    context.sendOrderedBroadcast(shortcut,null);  
	}	

//	@Override
//	public void onReceive(Context context, Intent intent) 
//	{
//		if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) 
//		{   
//			
//			//delShortcuts(context);
//        }
//		
//	}

}

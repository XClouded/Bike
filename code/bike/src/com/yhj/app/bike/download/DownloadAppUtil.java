package com.yhj.app.bike.download;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.widget.Toast;

import com.yhj.app.bike.system.Application;

public class DownloadAppUtil {

	private static Map<String, String> sMapPackageAndActivity = new HashMap<String, String>();

	/**
	 * 判断应用是否已安装
	 * 
	 */
	public static boolean appIsExists(String packName) {
		List<ApplicationInfo> installedAppList = Application.getInstance().getPackageManager().getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		int allAppSize = installedAppList.size();
		for (int i = 0; i < allAppSize; i++) {
			ApplicationInfo applicationInfo = installedAppList.get(i);
			String packagename = applicationInfo.packageName;
			if (packagename.equals(packName)) {
				return true;
			}
		}
		return false;
	}

	public static Intent getStartPackage(Context context, String packageName) {
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		if (!sMapPackageAndActivity.containsKey(packageName)) {
			final List<ResolveInfo> apps = context.getPackageManager().queryIntentActivities(mainIntent, 0);
			for (ResolveInfo info : apps) {
				if (!sMapPackageAndActivity.containsKey(info.activityInfo.applicationInfo.packageName)) {
					sMapPackageAndActivity.put(info.activityInfo.applicationInfo.packageName,info.activityInfo.name);
				}
			}
		}

		String name = sMapPackageAndActivity.get(packageName);
		if (name != null) {
			// Intent intent = new Intent(Intent.ACTION_MAIN);
			// mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
			mainIntent.setComponent(new ComponentName(packageName, name));
			return mainIntent;
		}
		return null;
	}

	public static String getApkName(Context context, String localAPKPath) {
		// 安装包路径
		String archiveFilePath = localAPKPath;
		String packageName = null;
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo info = pm.getPackageArchiveInfo(archiveFilePath,PackageManager.GET_ACTIVITIES);
			if (info != null) {
				ApplicationInfo appInfo = info.applicationInfo;
				// 得到安装包名称
				packageName = appInfo.packageName; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return packageName;
	}

	public static boolean installApp(Context context, String localAPKPath) {
		try {

			File f = new File(localAPKPath);
			if (!f.exists()) {
				return false;
			}
			// 本地安装
			Intent i = new Intent(Intent.ACTION_VIEW); 
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.setDataAndType(Uri.fromFile(new File(localAPKPath)),
					"application/vnd.android.package-archive");
			context.startActivity(i);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}


	public static void startOpenApp(final Activity ac, final String appId,
			final String packName, final Dialog publishDialog) {
		/*
		 * Intent intent = new Intent(); intent.setPackage(packName);
		 * intent.addCategory(Intent.CATEGORY_DEFAULT);
		 * intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		 * ac.startActivity(intent);
		 */

		Intent LaunchIntent = ac.getPackageManager().getLaunchIntentForPackage(
				packName);
		if (LaunchIntent == null) {
			LaunchIntent = new Intent(packName);
			LaunchIntent.addCategory(Intent.CATEGORY_DEFAULT);
		}

		try {
			LaunchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			ac.startActivity(LaunchIntent);
		} catch (Exception e) {
			Toast.makeText(ac, "启动失败", Toast.LENGTH_SHORT).show();
		}
	}

}

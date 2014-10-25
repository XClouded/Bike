package com.yhj.app.bike.download;
/*package com.tencent.news.download;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.widget.Toast;

import com.tencent.news.system.Application;

*//**
 * 下载列表初始化检测
 * @author vincesun
 * 
 * *//*

public class DownloadDataCheck {
	private static DownloadDataCheck instance;
	
	private ArrayList<MobileAPKInfo> mMobileAPKInfo;
	private int state = 0;
	private boolean isDeleteLocalFile;
	
	
	public DownloadDataCheck(){}
	
	public static DownloadDataCheck getInstance(){
		if(instance == null)
			instance = new DownloadDataCheck();	
		return instance;
	}
	
	//检测本机是否安装此应用
	public int checkPackageByLocal(String appId,String packageName,String versionCode) {
		int state = DownloadConstants.T_DOWNLOAD;
		PackageInfo packageInfo = null;
		String mVersionCode = null;
		try {
			Context context = Application.getInstance().getApplicationContext();
			packageInfo =context.getPackageManager().getPackageInfo(packageName, 0);
			if(packageInfo != null){
				//启动
				state = DownloadConstants.T_OPEN;
				mVersionCode = packageInfo.versionName;
				if(CompareVersion(mVersionCode,versionCode)) {
					state = DownloadConstants.T_UPDATE;
				}
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return state;
	}
	
	private boolean CompareVersion(String versionByLocal, String versionByNet) {
		boolean isUpdated = false;
		String temp = "";
		String letter_pattern = "[^0-9]";
		versionByLocal = versionByLocal.replaceAll(letter_pattern, "");
		versionByNet = versionByNet.replaceAll(letter_pattern, "");
		versionByLocal = versionByLocal.replace(".", "");
		versionByNet = versionByNet.replace(".", "");
		int len = 0;
		if(versionByLocal.length() > versionByNet.length()) {
			len = versionByLocal.length() - versionByNet.length();
			temp = versionByNet;
			if(len != 0) {
				for(int i = 0;i < len;i++) {
					temp += "0";
				}
				if(Long.valueOf(versionByLocal) < Long.valueOf(temp)) {
					isUpdated = true;
				}
				
			}
		}else if(versionByLocal.length() == versionByNet.length()){
			if(Long.valueOf(versionByNet) > Long.valueOf(versionByLocal)) {
				isUpdated = true;
			}
		}else {
			len = versionByNet.length() - versionByLocal.length();
			temp = versionByLocal;
			if(len != 0) {
				for(int i = 0;i < len;i++) {
					temp += "0";
				}
				if(Long.valueOf(versionByNet) > Long.valueOf(temp)) {
					isUpdated = true;
				}
			}
		}
		
		return isUpdated;
	}
	
	*//**
     * 判断应用是否已安装
     * 
     * @param packName
     * @return
     *//*
    public static boolean appIsExists(String packName) {
        List<ApplicationInfo> installedAppList = Application.getInstance()
                .getPackageManager().getInstalledApplications(
                        PackageManager.GET_UNINSTALLED_PACKAGES);
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
	 
	// 检测本地下载该应用
    public int checkDBOption(String appId,String version,String url) {
		Log.v("vincesun", "check db");
		int state = 0 ;
		// 获取数据库数据
		DownloadDBHelper deHelper = DownloadDBHelper.getInstance();
		
		List<DownloadDataInfo> infos = deHelper.getInfosByAppId(appId);
		Log.v("vincesun",String.valueOf(infos.size()));
		if(infos != null && infos.size() > 0) {
			Log.v("vincesun", infos.get(0).getPackageName());
			state = DownloadConstants.T_PAUSE;
			long curCompleteSize = infos.get(0).getCompeleteSize();
			long fileSize = infos.get(0).getEndPos();
			String mVersion = infos.get(0).getPushContent();
			if(mVersion != null && mVersion.length() > 0){
				if(CompareVersion(mVersion,version)){
					state = DownloadConstants.T_DOWNLOAD;
					deHelper.deleteInfoByAppId(appId);
					delFile(appId,url);
					return state;
				}
			}
			
			if(curCompleteSize >= fileSize){
				state = DownloadConstants.T_INSTALL;
				return state;
			}
			return state;
		}else{
			state = DownloadConstants.T_DOWNLOAD;
			return state;
		}
	}
    
    public void delFile(String appId, String  urlstr) {
    	String fileName = getLocalfileName(urlstr);
    	String localTempFilePath = getFilePath(appId, fileName, ".temp");
		String localRealFilePath = getFilePath(appId, fileName, "");
    	
		// 删掉真实安装包
		File f = new File(localRealFilePath);
		if (f.exists()) {
			f.delete();
		}

		// 删掉零时文件
		f = new File(localTempFilePath);
		if (f.exists()) {
			f.delete();
		}
	}
    
    private String getFilePath(String appid, String fileName, String tmp) {
		return Downloader.DOWNLOAD_PATH + "_" + appid + "_" + fileName + tmp;
	}

    
    private String getLocalfileName(String downloadUrl) {
		int endN = downloadUrl.indexOf("?");
		if (endN != -1) {
			downloadUrl = downloadUrl.substring(0, endN);
		}
		int startN = downloadUrl.lastIndexOf("/");
		String fileName = downloadUrl.substring(startN + 1,
				downloadUrl.length());
		return fileName;
	}
    
    
    
    
    
    
    
    //获取update进度
    public int getUpdateProgress(String appId){
    	int progress = 0;
    	DownloadDBHelper deHelper = DownloadDBHelper.getInstance();
		List<DownloadDataInfo> infos = deHelper.getInfosByAppId(appId);
		if(infos != null && infos.size() > 0) {
			long curCompleteSize = infos.get(0).getCompeleteSize();
			long fileSize = infos.get(0).getEndPos();
			progress = Downloader.getDownloadPercent(fileSize, curCompleteSize);
		}
    	return progress;
    }
	

	
	
	//打开安装在本地得应用
	public void openLocalAPP(String packageName){
		Context context = Application.getInstance().getApplicationContext();
//		try {
//			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
//			ActivityInfo[] activites = packageInfo.activities;
//			if(activites == null || activites.length == 0) {
//				Toast.makeText(context, "该应用程序不能被启动", 0).show();
//			}else{
//				 ActivityInfo activityInfo = activites[0];
//				 Intent start_intent = new Intent();
//				 start_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
//				 String name = activityInfo.name;
//				 ComponentName component = new ComponentName(packageName, name);
//				 start_intent.setComponent(component);
//				 context.startActivity(start_intent);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			Toast.makeText(context, "该应用程序不能被启动", 0).show();
//		}
		
        Intent LaunchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (LaunchIntent == null) {
            LaunchIntent = new Intent(packageName);
            LaunchIntent.addCategory(Intent.CATEGORY_DEFAULT);
        }

        try {
            LaunchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(LaunchIntent);
        } catch (Exception e) {
            Toast.makeText(context, "启动失败", Toast.LENGTH_SHORT).show();
        }
	}
	
	
	
	
}
*/
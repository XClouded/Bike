package com.yhj.app.bike.download;
/*package com.tencent.news.download;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.widget.RemoteViews;

import com.tencent.news.R;
import com.tencent.news.system.Application;

*//**
 * 通知管理
 * @author vincesun
 * 	
 *//*

public class DownloadNotificationManager {
	
	private static DownloadNotificationManager instance;
	
	public static final int APP_TYPE = 0X201;
	public static final int VINSION_UPDATE_TYPE = 0x202;
	public static final int OFFLINE_TYPE = 0X203;
	
	
	private static final String DOWNLOAD_COMPLETED_TXT = "已下载成功,点击进入下载管理";
	private static final String DOWNLOAD_RUNNING_TXT = "下载中,点击进入下载管理";//最新版本
	private static final String DOWNLOAD_TICKER_TXT = "下载中，点击进入";
	private static final String DOWNLOAD_STOP_TXT = "停止下载";
	private static final String DOWNLOAD_ERROR_TXT = "下载出错，已停止下载";
	
	private static int DOWNLOAD_ICON = R.drawable.icon;
	private Context mContext;
	private NotificationManager nm;
	private Notification notification;
	private int nID;
	private String nTickerTxt;
	private String nPackageName;
	private int nCode;
	private String nRunningTxt;
	private String nCompletedTxt;
	private String nErrorTxt;
	private String nStopTxt;
	private int nType;
	

	public static DownloadNotificationManager getInstance() {
		if(instance == null) {
			instance = new DownloadNotificationManager();
		}
		return instance;
	}
	
	public void showNotification(int id,int code,int type,String appName,String packageName) {
		nType = type;
		switch(nType) {
			case APP_TYPE:
				nID = id;
				nTickerTxt = appName + DOWNLOAD_TICKER_TXT;
				nPackageName = packageName;
				nCode = code;
				nRunningTxt = appName + DOWNLOAD_RUNNING_TXT;
				nCompletedTxt = appName + DOWNLOAD_COMPLETED_TXT;
				nErrorTxt = appName + DOWNLOAD_ERROR_TXT;
				nStopTxt = appName + DOWNLOAD_STOP_TXT;
				break;
			
			case VINSION_UPDATE_TYPE:
				nID = id;
				nTickerTxt = "最新版本" + DOWNLOAD_TICKER_TXT;
				nCode = code;
				nPackageName = packageName;
				nRunningTxt = "最新版本" + DOWNLOAD_RUNNING_TXT;
				nCompletedTxt = "最新版本" + DOWNLOAD_COMPLETED_TXT;
				nErrorTxt = "最新版本" + DOWNLOAD_ERROR_TXT;
				nStopTxt = "最新版本" + DOWNLOAD_STOP_TXT;
				break;
				
			case OFFLINE_TYPE:
				nID = id;
				nTickerTxt = "点击进入离线阅读";
				nCode = code;
				nPackageName = packageName;
				nRunningTxt = "点击进入离线阅读";
				nCompletedTxt = "点击进入离线阅读";
				nErrorTxt = "点击进入离线阅读";
				nStopTxt = "点击进入离线阅读";
				break;
			
			default:
				break;
		}
		
		startNotification();
	}
	
	public void kill(int id){
		mContext = Application.getInstance().getApplicationContext();
		nm = (NotificationManager)mContext.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		nm.cancel(id);
	}
	
	private void startNotification() {
		mContext = Application.getInstance().getApplicationContext();
		nm = (NotificationManager)mContext.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		
		//创建Notifcation
		notification = new Notification(DOWNLOAD_ICON , nTickerTxt,System.currentTimeMillis());
		
		RemoteViews contentView = new RemoteViews("com.tencent.news",R.layout.download_notification_layout);
		if(nCode == DownloadConstants.T_INSTALL) {
			//完成
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			
			contentView.setImageViewResource(R.id.download_icon, R.drawable.push_download_complete_icon);
			contentView.setTextViewText(R.id.download_notification_txt, nCompletedTxt );
			
		}else if(nCode == DownloadConstants.T_UPDATE_PROGRESS) {
			//下载中
			notification.flags |= Notification.FLAG_NO_CLEAR;
			notification.flags |= Notification.FLAG_ONGOING_EVENT;
			contentView.setImageViewResource(R.id.download_icon, R.drawable.push_download_icon);
			contentView.setTextViewText(R.id.download_notification_txt, nRunningTxt );
			//notification.contentView = contentView;
		}else if(nCode == DownloadConstants.T_ERROR){
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			contentView.setImageViewResource(R.id.download_icon, R.drawable.push_download_icon);
			contentView.setTextViewText(R.id.download_notification_txt, nErrorTxt );
		}else{
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			contentView.setImageViewResource(R.id.download_icon, R.drawable.push_download_icon);
			contentView.setTextViewText(R.id.download_notification_txt, nStopTxt );
		}
		
		if(nType == APP_TYPE) {
			//跳转页面
			Intent notificationIntent = new Intent(mContext, HotAppListActivity.class);
			notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,notificationIntent, 0);
			notification.contentIntent = contentIntent;
			notification.contentView = contentView;
		}else if(nType == OFFLINE_TYPE){
			Intent notificationIntent = new Intent(mContext, OfflineActivity.class);
			notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,notificationIntent, 0);
			notification.contentIntent = contentIntent;
			notification.contentView = contentView;
		}else {
			//跳转页面
			Intent notificationIntent = new Intent(mContext, SettingActivity.class);
	        Bundle mBundle = new Bundle();  
	        mBundle.putInt(Constants.CHECK_UPDATE, Constants.FROM_SETTING);
	        notificationIntent.putExtras(mBundle);  
			notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,notificationIntent, 0);
			notification.contentIntent = contentIntent;
			notification.contentView = contentView;
		}
		
		nm.notify(nID, notification);
		
	}
	
}
*/
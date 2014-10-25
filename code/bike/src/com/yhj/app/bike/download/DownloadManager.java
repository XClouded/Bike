package com.yhj.app.bike.download;
/*package com.tencent.news.download;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.tencent.news.boss.EventId;
import com.tencent.news.download.DownloadNetworkState.NetworkStateListener;
import com.tencent.news.system.Application;
import com.tencent.omg.webdev.WebDev;

*//**
 * 下载器管理
 * 
 * @author vincesun
 * 
 *//*

public class DownloadManager implements NetworkStateListener, DownloadListener {
	private static DownloadManager instance;
	private final ConcurrentHashMap<String, DownloadTaskInfo> downloaders = new ConcurrentHashMap<String, DownloadTaskInfo>();
	private final ConcurrentHashMap<String, APPDownloadListener> appListeners = new ConcurrentHashMap<String, APPDownloadListener>();
	private final ArrayList<ConcurrentHashMap<String, DownloadTaskInfo>> waitDonwloader = new ArrayList<ConcurrentHashMap<String, DownloadTaskInfo>>();
	private static DownloadNetworkState networkState = null;
	private boolean networkCneted = true;
	private boolean isStart;
	private boolean nType;

	public static DownloadManager getInstance() {
		if (instance == null) {
			instance = new DownloadManager();
		}
		return instance;
	}

	*//**
	 * 网络监听
	 *//*
	public void initListener() {
		if (networkState == null) {
			networkState = DownloadNetworkState.g();
			networkState.setContext(Application.getInstance().getApplicationContext());
			networkState.addListener(this);
		}
	}

	public Downloader getDownloader(String appid) {
		Downloader downloader = null;
		DownloadTaskInfo dt = DownloadManager.getInstance().getDownloadTask(appid);
		if (dt != null) {
			downloader = dt.getDownloader();
		}
		return downloader;
	}

	*//**
	 * 创建下载任务
	 * 
	 *//*
	public void createDownloaderTask(String appid, String url, String packageName, String appName, DownloadListener listenter, String version, boolean nType) {
		this.nType = nType;
		initListener();
		Downloader downloader = null;
		DownloadDBHelper dbHelper = DownloadDBHelper.getInstance();
		DownloadTaskInfo dt = DownloadManager.getInstance().getDownloadTask(appid);
		if (dt != null) {
			downloader = dt.getDownloader();
		}
		if (downloader == null) {
			downloader = new Downloader(appid, url, packageName, dbHelper, appName, listenter, version, "");
			dt = new DownloadTaskInfo(downloader, listenter);

		}

		addDownloader(appid, dt);
		if (downloader.isDownloading()) {
			downloader.pauseDownload(true);
		} else {
			downloader.setState(DownloadConstants.DOWNLOAD_BEGAIN);
			downloader.startDownload();
			String id = downloader.getAppId();
			APPDownloadListener listener = getAppListener(id);
			int n_progress = DownloadDataCheck.getInstance().getUpdateProgress(id);
			String progress = String.valueOf(n_progress) + "%";
			listener.downloadStateChanged(id, DownloadConstants.T_UPDATE_PROGRESS, n_progress, progress);
			if (nType) {
				int type;
				if (packageName == "com.tencent.news") {
					type = DownloadNotificationManager.VINSION_UPDATE_TYPE;
				} else {
					type = DownloadNotificationManager.APP_TYPE;
				}
				if (nType) {
					DownloadNotificationManager.getInstance().showNotification(Integer.parseInt(appid), DownloadConstants.T_UPDATE_PROGRESS, type, appName, packageName);
				}
			}

			// Statistics.getInstance().saveStatistics(Statistics.REPORTED_DATA_SHOWING_WHAT,
			// StatisticsUtil.generateCustomField(new String[] { "", "",
			// "downloadbegin", appName, packageName, version, "" }));

			Properties pts = new Properties();
			pts.setProperty(EventId.KEY_VERSION, version);
			pts.setProperty(EventId.KEY_PACKAGENAME, packageName);
			pts.setProperty(EventId.KEY_APPNAME, appName);
			WebDev.trackCustomEvent(Application.getInstance().getApplicationContext(), EventId.BOSS_DOWNLOADER_BEGIN, pts);

		}

	}

	public int getAppDownloadState(String appid) {
		int state = 0;
		DownloadTaskInfo dt = downloaders.get(appid);
		if (dt != null) {
			Downloader downloader = dt.getDownloader();
			if (!downloader.isDownloading()) {
				state = DownloadConstants.T_PAUSE;
			} else {
				state = DownloadConstants.T_UPDATE_PROGRESS;
			}
		} else {
			state = DownloadConstants.T_DOWNLOAD;
		}

		return state;
	}

	*//**
	 * 根据appid获取下载任务
	 *//*
	private DownloadTaskInfo getDownloadTask(String appid) {
		return appid == null ? null : downloaders.get(appid);
	}

	private void addDownloader(String appid, DownloadTaskInfo dt) {
		if (getDownloadTask(appid) == null) {
			downloaders.put(appid, dt);
		}
	}

	private void startWaitDownloader() {
		int len = waitDonwloader.size();
		if (len > 0) {
			ConcurrentHashMap<String, DownloadTaskInfo> ch = waitDonwloader.get(0);
			downloaders.putAll(ch);
			Iterator<Entry<String, DownloadTaskInfo>> it = ch.entrySet().iterator();
			Object key = it.next();
			DownloadTaskInfo dt = downloaders.get(key);
			if (dt != null) {
				dt.getDownloader().startDownload();
				waitDonwloader.remove(0);
			}

		}
	}

	private void autoStartDownload() {
		Iterator<Entry<String, DownloadTaskInfo>> it = downloaders.entrySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			DownloadTaskInfo dt = downloaders.get(key);
			dt.getDownloader().startDownload();
		}
	}

	*//**
	 * 根据appid删除下载任务
	 * *//*
	public void removeDownloaderTask(boolean isShowNotification) {
		Iterator<Entry<String, DownloadTaskInfo>> it = downloaders.entrySet().iterator();
		while (it.hasNext()) {
			
			 * Object key = it.next(); DownloadTaskInfo dt =
			 * downloaders.get(key); if(dt != null) {
			 * dt.getDownloader().pauseDownload(); downloaders.remove(key); }
			 
			Entry<String, DownloadTaskInfo> item = it.next();
			DownloadTaskInfo dt = item.getValue();
			if (dt != null) {
				dt.getDownloader().pauseDownload(isShowNotification);
			}
		}
		downloaders.clear();
	}

	*//**
	 * 删除所有下载任务
	 **//*
	public void removeAllDownloaderTask() {

		downloaders.clear();
	}

	public DownloadDBHelper getDownloadDBHelper() {
		return DownloadDBHelper.getInstance();
	}

	@Override
	public void onNetworkConnect(boolean connected) {
		Log.v("vincesun", "网络连接状态=========" + String.valueOf(connected));
		if (!connected) {
			// TipsToast.getInstance().showTipsError("网络异常");
		}

		if (connected && getNetworkType() != DownloadNetworkState.NETWORK_TYPE_WIFI) {
			// downloadAlert();
		}

	}

	// private void downloadAlert(final Downloader downloader) {
	//
	// Dialog dialog = new AlertDialog.Builder(this)
	// .setTitle("下载提示")//
	// .setMessage("非wifi情况下，您是否继续下载?")//
	// .setPositiveButton("是", new OnClickListener() {//
	// @Override
	// public void onClick(DialogInterface dialog,int which) {
	// downloader.startDownload();
	// }
	// }).setNegativeButton("稍后再说", new OnClickListener() {
	//
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// dialog.cancel();
	// }
	// }).create();
	// dialog.show();
	// }

	public int getNetworkType() {
		if (networkState != null) {
			return networkState.getNetworkType();
		}
		return -1;
	}

	public boolean isNetworkCneted() {
		return networkCneted;
	}

	public void doStateAction(String appid, int state, String url, String packageName, String appName, APPDownloadListener appListener, String version, boolean ntype) {
		switch (state) {
		case DownloadConstants.T_DOWNLOAD:
			// 下载
		case DownloadConstants.T_PAUSE:
			// 继续下载
		case DownloadConstants.T_UPDATE:
			// 更新下载
			Log.v("vincesun", "=================下载||继续下载||更新下载===============");
			createDownloaderTask(appid, url, packageName, appName, this, version, ntype);
			addAppListener(appid, appListener);
			break;
		case DownloadConstants.T_OPEN:
			// 打开应用
			Log.v("vincesun", "=================打开应用===============");
			openLocalAPP(packageName);
			break;
		case DownloadConstants.T_INSTALL:
			// 启动安装
			Log.v("vincesun", "=================启动安装===============");
			openInstallAPK(appid, url);
			break;

		case DownloadConstants.T_UPDATE_PROGRESS:
			// 停止
			Log.v("vincesun", "=================停止下载===============");
			pauseDownloadTask(appid);
			break;
		default:
			break;
		}
	}

	public void addAppListener(String appid, APPDownloadListener appListener) {
		appListeners.put(appid, appListener);
	}

	private void removeAppListener(String appid) {
		appListeners.remove(appid);
	}

	public APPDownloadListener getAppListener(String appid) {

		return appListeners.get(appid);
	}

	*//**
	 * App完成安装
	 *//*
	public static void appInstalled(String packageName) {
		String[] p = packageName.split(":");
		DownloadDBHelper dbHelper = DownloadDBHelper.getInstance();
		List<DownloadDataInfo> l = dbHelper.getInfosByAppPackageName(p[1]);
		if (l != null && l.size() > 0) {
			String appid = l.get(0).getAppId();
			String filename = l.get(0).getPushTitle();
			String packagename = l.get(0).getPackageName();
			String version = l.get(0).getPushContent();
			dbHelper.deleteInfoByAppId(appid);
//			HotAppListAdapter.packageStateChanged(packageName, DownloadConstants.T_OPEN, 0, "");

			// Statistics.getInstance().saveStatistics(Statistics.REPORTED_DATA_SHOWING_WHAT,
			// StatisticsUtil.generateCustomField(new String[] { "", "",
			// "downloadinstalled", filename, packagename, version, "" }));

			Properties pts = new Properties();
			pts.setProperty(EventId.KEY_VERSION, version);
			pts.setProperty(EventId.KEY_PACKAGENAME, packagename);
			pts.setProperty(EventId.KEY_APPNAME, filename);
			WebDev.trackCustomEvent(Application.getInstance().getApplicationContext(), EventId.BOSS_DOWNLOADER_INSTALLED, pts);

		} else {
			return;
		}

	}

	*//**
	 * App完成卸载
	 *//*
	public static void appUnInstalled(String packageName) {
	}

	*//**
	 * 打开安装在本地得应用
	 *//*
	public void openLocalAPP(String packageName) {
		Context context = Application.getInstance().getApplicationContext();
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

	private void openInstallAPK(String appid, String url) {
		String fileName = Downloader.getLocalfileName(url);
		String localAPKPath = Downloader.DOWNLOAD_PATH + "_" + appid + "_" + fileName;

		// 包名为空时表示安装包无效
		String apkPackageName = DownloadAppUtil.getApkName(Application.getInstance().getApplicationContext(), localAPKPath);
		if (apkPackageName != null) {
			DownloadAppUtil.installApp(Application.getInstance().getApplicationContext(), localAPKPath);
		} else {
			// 手动删除文件
			APPDownloadListener listener = getAppListener(appid);
			listener.downloadStateChanged(appid, DownloadConstants.T_DOWNLOAD, 0, "");
			DownloadDBHelper.getInstance().deleteInfoByAppId(appid);
		}

	}

	private void pauseDownloadTask(String appid) {
		Log.v("vincesun", "DM:::::::::::pauseDownloadTask");
		DownloadTaskInfo info = downloaders.get(appid);
		info.getDownloader().pauseDownload(true);
		APPDownloadListener listener = getAppListener(appid);
		listener.downloadStateChanged(appid, DownloadConstants.T_PAUSE, 0, "");
	}

	@Override
	public void onDownloadGetSizeFinish(Downloader downloader) {
		String id = downloader.getAppId();
		APPDownloadListener listener = getAppListener(id);
		listener.downloadStateChanged(id, DownloadConstants.T_DOWNLOAD, 0, "");

	}

	@Override
	public void onDownloadInitFileError(Downloader downloader) {
		DownloadTaskInfo info = downloaders.get(downloader.appId);

	}

	@Override
	public void onDownloadBegin(Downloader downloader) {
		Log.v("vincesun", "下载开始");

	}

	@Override
	public void onDownloadPause(Downloader downloader, int n_progress) {
		Log.v("vincesun", "下载停止");
		String id = downloader.getAppId();
		APPDownloadListener listener = getAppListener(id);
		listener.downloadStateChanged(id, DownloadConstants.T_PAUSE, n_progress, "");

		if (nType) {
			int type;
			if (downloader.getPackageName() == "com.tencent.news") {
				type = DownloadNotificationManager.VINSION_UPDATE_TYPE;
			} else {
				type = DownloadNotificationManager.APP_TYPE;
			}
			DownloadNotificationManager.getInstance()
					.showNotification(Integer.parseInt(downloader.getAppId()), DownloadConstants.T_PAUSE, type, downloader.getPushTitle(), downloader.getPackageName());
		}

		// pauseProgressingTask(downloader);
		// startWaitDownloader();
	}

	private void pauseProgressingTask(Downloader downloader) {
		String id = downloader.getAppId();
		DownloadTaskInfo info = downloaders.get(id);
		ConcurrentHashMap<String, DownloadTaskInfo> ch = new ConcurrentHashMap<String, DownloadTaskInfo>();
		ch.put(id, info);
		waitDonwloader.add(ch);
		downloaders.remove(id);
	}

	@Override
	public void onDownloadWait(Downloader downloader) {

	}

	@Override
	public void onDownloadUpdate(Downloader downloader, String progress, int n_progress) {
		// Log.v("vincesun", progress);
		if (downloader.isPause()) {
			return;
		}
		String id = downloader.getAppId();
		APPDownloadListener listener = getAppListener(id);
		listener.downloadStateChanged(id, DownloadConstants.T_UPDATE_PROGRESS, n_progress, progress);
	}

	@Override
	public void onDownloadUpdate(List<Downloader> downloaders) {

	}

	@Override
	public void onDownloadError(Downloader downloader, int n_progress) {
		downloader.setState(DownloadConstants.DOWNLOAD_ERROR);
		String id = downloader.getAppId();
		APPDownloadListener listener = getAppListener(id);
		listener.downloadStateChanged(id, DownloadConstants.T_PAUSE, n_progress, "");
		if (nType) {
			int type;
			if (downloader.getPackageName() == "com.tencent.news") {
				type = DownloadNotificationManager.VINSION_UPDATE_TYPE;
			} else {
				type = DownloadNotificationManager.APP_TYPE;
			}
			DownloadNotificationManager.getInstance()
					.showNotification(Integer.parseInt(downloader.getAppId()), DownloadConstants.T_ERROR, type, downloader.getPushTitle(), downloader.getPackageName());
		}

	}

	@Override
	public void onDownloadFinish(Downloader downloader) {
		Log.v("vincesun", "========================完成=========================");
		String id = downloader.getAppId();
		APPDownloadListener listener = getAppListener(id);
		listener.downloadStateChanged(id, DownloadConstants.T_INSTALL, 0, "");
		if (nType) {
			openInstallAPK(downloader.getAppId(), downloader.getUrlStr());
			int type;
			if (downloader.getPackageName() == "com.tencent.news") {
				type = DownloadNotificationManager.VINSION_UPDATE_TYPE;
			} else {
				type = DownloadNotificationManager.APP_TYPE;
			}
			DownloadNotificationManager.getInstance().showNotification(Integer.parseInt(downloader.getAppId()), DownloadConstants.T_INSTALL, type, downloader.getPushTitle(),
					downloader.getPackageName());
		}

		// Statistics.getInstance().saveStatistics(Statistics.REPORTED_DATA_SHOWING_WHAT,
		// StatisticsUtil.generateCustomField(new String[] { "", "",
		// "downloadcompleted", downloader.getPushTitle(),
		// downloader.getPackageName(), downloader.getPushContent(), "" }));

		Properties pts = new Properties();
		pts.setProperty(EventId.KEY_VERSION, downloader.getPushContent());
		pts.setProperty(EventId.KEY_PACKAGENAME, downloader.getPackageName());
		pts.setProperty(EventId.KEY_APPNAME, downloader.getPushTitle());
		WebDev.trackCustomEvent(Application.getInstance().getApplicationContext(), EventId.BOSS_DOWNLOADER_COMPLETED, pts);

		// remove
		downloaders.remove(id);
		startWaitDownloader();
	}

	@Override
	public void onDownloadDelete(Downloader downloader) {

	}

	@Override
	public void downloadUrlChangeError(Downloader downloader) {

	}

	@Override
	public void installError(Downloader downloader) {

	}

	@Override
	public void installSucceed(String appid, String packageName, Downloader downloader) {
		APPDownloadListener listener = getAppListener(appid);
		listener.downloadStateChanged(appid, DownloadConstants.T_OPEN, 0, "");
	}

	@Override
	public void uninstallSucceed(String appid, String packageName) {

	}

}
*/
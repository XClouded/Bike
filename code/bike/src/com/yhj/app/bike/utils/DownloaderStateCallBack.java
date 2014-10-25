package com.yhj.app.bike.utils;

import com.yhj.app.bike.download.APPDownloadListener;
import com.yhj.app.bike.system.Application;

/** 
 * @author jianhu
 * @version 创建时间：2013-5-27 下午3:22:33
 * 类说明 
 */
public class DownloaderStateCallBack implements APPDownloadListener{

	private static DownloaderStateCallBack instance;

	public static synchronized DownloaderStateCallBack getInstance() {
		if (instance == null) {
			instance = new DownloaderStateCallBack();
		}
		return instance;
	}
	/* (non-Javadoc)
	 * @see com.tencent.news.download.APPDownloadListener#downloadStateChanged(java.lang.String, int, int, java.lang.String)
	 */
	@Override
	public void downloadStateChanged(String TAG, int state, int n_progress,
			String t_progress) {
		// TODO Auto-generated method stub
		
		SLog.d("hj", "DownloaderStateCallBack state:" + Integer.toHexString(state));		
		Application.getInstance().setDownloadState(state);
	}

}

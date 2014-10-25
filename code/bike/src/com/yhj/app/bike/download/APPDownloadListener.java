package com.yhj.app.bike.download;
/**
 * APP下载状态监听
 * @author vincesun
 * 
 * **/
public interface APPDownloadListener {
	/*下载状态改变
	 * @param TAG:下载器id
	 * @param state:状态
	 * @param n_progress:下载进度（数字形式）
	 * @param t_progress:下载进度 (文字百分比形式)
	 * **/
	void downloadStateChanged(String TAG,int state,int n_progress,String t_progress);
}

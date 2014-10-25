package com.yhj.app.bike.download;

import com.yhj.app.bike.model.pojo.OfflineChannel;

/**
 * 离线下载监听器
 * @author vincesun
 */

public interface OffLineDownloadListener {
	/*下载状态改变
	 * @param state:状态:开始，下载中，下载完成
	 * @param n_progress:下载进度 (数字形式)
	 * @param channal 频道编号
	 * **/
	void downloadProgressChanged(int state,int n_progress, OfflineChannel channal,int surplus);
	
	void afterParse();
}

package com.yhj.app.bike.download;
/*package com.tencent.news.download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;

public class OffLineDownloader {
	private String url;
	private String appid;
	private String channelId;
	private long fileSize;
	private String localTempFilePath;
	private String localRealFilePath;
	private int downloadState;
	private long curCompleteSize;
	private int curSNProgress;
	
	public OffLineDownloader(String appid,String url,String channelId,long fileSize){
		this.url = url;
		this.appid = appid;
		this.channelId = channelId;
		this.fileSize = fileSize;
		String fileName = getLocalfileName(url);
		this.localTempFilePath = getFilePath(appid, fileName, ".temp");
		this.localRealFilePath = getFilePath(appid, fileName, "");
	}
	
	public static String getFilePath(String appid, String fileName, String tmp) {
		return Downloader.DOWNLOAD_PATH + "_" + appid + "_" + fileName + tmp;
	}
	
	*//**
	 * 通过下载url生成一个文件名
	 * 
	 * @param downloadUrl
	 * @return
	 *//*
	public static String getLocalfileName(String downloadUrl) {
		int endN = downloadUrl.indexOf("?");
		if (endN != -1) {
			downloadUrl = downloadUrl.substring(0, endN);
		}
		int startN = downloadUrl.lastIndexOf("/");
		String fileName = downloadUrl.substring(startN + 1,
				downloadUrl.length());
		return fileName;
	}
	
	
	public void startDownload(){
		if(url != null && fileSize != 0) {
			DownloadThread dt = new DownloadThread(fileSize,this);
		}
	}
	
	public void pauseDownload(){
		clearThread();
		downloadState = DownloadConstants.DOWNLOAD_PAUSE;
		//notifyListener(DownloadConstants.DOWNLOAD_PAUSE, this);
	}
	
	private void clearThread() {
//		if (getCurThreadCount() > 0) {
//			try {
//				for (DownloadThread mt : getDownloadThreads()) {
//					mt.exit();
//					mt = null;
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	*//**
	 * 判断是否正在下载
	 *//*
	public synchronized boolean isDownloading() {
		return downloadState == DownloadConstants.DOWNLOAD_BEGAIN || downloadState == DownloadConstants.DOWNLOAD_UPDATE;
	}

	*//**
	 * 判断是否暂停
	 *//*
	public synchronized boolean isPause() {
		return downloadState == DownloadConstants.DOWNLOAD_PAUSE
				|| downloadState == DownloadConstants.DOWNLOAD_ERROR
				|| downloadState == DownloadConstants.SDCARD_ERROR;
	}
	
	
	public void complete(){}
	
	public void downloadError(){}
	
	public static int getDownloadPercent(long totalBytes, long currentBytes) {
		if (totalBytes <= 0) {
			return 0;
		}

		if (currentBytes > totalBytes) {
			currentBytes = totalBytes;
		}
		int percent = (int) (currentBytes * 100 / totalBytes);
		return percent;
	}
	
	
	public class DownloadThread extends Thread {
	
		private long compeleteSize;
		private OffLineDownloader downloader;
		private int startPos = 0;

		private HttpURLConnection connection = null;
		private RandomAccessFile randomAccessFile = null;
		private InputStream is = null;

		public DownloadThread(long compeleteSize, OffLineDownloader downloader) {
			this.compeleteSize = compeleteSize;
			this.downloader = downloader;
		}

		@Override
		public void run() {
			try {

				if (isPause()) {
					return;
				}

				connection = DownloadConnectionHelper.getHttpConnection(
						url,
						true, 
						true, 
						10000, 2 * 60 * 1000, 
						"bytes="+ startPos + "-",
						true);


				randomAccessFile = new RandomAccessFile(localTempFilePath,"rwd");
				randomAccessFile.seek(startPos + compeleteSize);
				// 将要下载的文件写到保存在保存路径下的文件中

				is = connection.getInputStream();
				byte[] buffer = new byte[4096];
				int length = -1;
				while ((length = is.read(buffer)) != -1 && !this.isInterrupted()) {
					randomAccessFile.write(buffer, 0, length);
					compeleteSize += length;
					// 更新数据库中的下载信息
					curCompleteSize += length;
					curSNProgress = getDownloadPercent(fileSize, compeleteSize);
//					notifyListener(DownloadConstants.DOWNLOAD_UPDATE,downloader);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				downloadError();
			} finally {
				try {
					disconnect();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (curCompleteSize >= fileSize
					&& downloadState != DownloadConstants.DOWNLOAD_COMPLETED) {// 下载完成
				File tempFile = new File(localTempFilePath);
				if (tempFile.exists()) {
					File f = new File(localRealFilePath);
					if (f.exists()) {
						f.delete();
					}
					if (tempFile.renameTo(f)) { // 文件重命名,将temp文件修改
						complete();
					} else {
						downloadError();
					}
				}
				return;
			}
		}

		public void exit() {

			try {
				if (Thread.currentThread().isAlive()) {
					downloadState = DownloadConstants.DOWNLOAD_PAUSE;
					this.interrupt();
					disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void disconnect() {
			try {
				if (is != null) {
					is.close();
					is = null;
				}

				if (randomAccessFile != null) {
					randomAccessFile.close();
					randomAccessFile = null;
				}

				if (connection != null) {
					connection.disconnect();
					connection = null;
				}


			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
*/
package com.yhj.app.bike.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * 下载线程池
 * 
 * @author vincesun
 */


public class DownloadThreadPool {
	private static DownloadThreadPool instance;
	
	private ConcurrentLinkedQueue<DownloadThread> downloadThreads;
	
	public static final String localTempFilePath = "" ;// 临时文件保存路径
	public static final String localRealFilePath = "";// 真实文件保存路径
	
	
	public static DownloadThreadPool getInstance(){
		if( instance == null ) {
			instance = new DownloadThreadPool();
		}
		return instance;
	}
	
	public void addDownloadTask(int threadId, long startPos, long endPos, long compeleteSize) {
		DownloadThread dt = new DownloadThread();
		downloadThreads.add(dt);
	}
	
	public void removeDownloadTask() {
		
	}
	
	public void removeAllDownloadTask() {
		
	}
	
	
	
	
	private class DownloadThread extends Thread{
		private int threadId;
		private long startPos;
		private long endPos;
		private long compeleteSize;
		private String urlStr;
		
		private HttpURLConnection connection = null;
		private RandomAccessFile randomAccessFile = null;
		private InputStream is = null;
		
		
		@Override
		public void run() {
			try {
				randomAccessFile = new RandomAccessFile(localTempFilePath,	"rwd");	
				randomAccessFile.seek(startPos + compeleteSize);
				
				connection = DownloadConnectionHelper.getHttpConnection(urlStr, true, true, 10000, 2 * 60 * 1000, "bytes=" + (startPos + compeleteSize) + "-" + endPos, true);
				is = connection.getInputStream();
				byte[] buffer = new byte[20240];
				int length = -1;
				while ((length = is.read(buffer)) != -1 && !this.isInterrupted()) {
					randomAccessFile.write(buffer, 0, length);
					//curCompleteSize += length;
					
				}
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}



package com.yhj.app.bike.cache;

import java.io.File;

import com.yhj.app.bike.api.JsonParse;
import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.model.pojo.NewsMsgGroup;
import com.yhj.app.bike.utils.FileUtil;

public class NewsMsgGroupCache {
	
	private File cacheFile;
	
	private NewsMsgGroup cacheData;
	
	private static Thread writeThread = null;
	
	public NewsMsgGroupCache() {
		
	}
	
	// id: Group uin
	public NewsMsgGroupCache(String id){
		cacheFile = new File(Constants.CACHE_NEWS_MSG_PATH + id);
	}
	
	public void setCacheData(NewsMsgGroup cacheData) {
		this.cacheData = cacheData;
		write();
	}
	
	public NewsMsgGroup getCacheData() {
		return read();
	}
	
	public File getCacheFile() {
		return cacheFile;
	}

	public void setCacheFile(File cacheFile) {
		this.cacheFile = cacheFile;
	}
	
	private NewsMsgGroup read() {
		Object o = FileUtil.readCache(cacheFile);
		if (o == null) {
			String json = (String) FileUtil.readString(Constants.CACHE_NEWS_MSG_PATH + cacheFile.getName());
			if (json != null && json.length() > 0) {
				try {
					o = JsonParse.parseNewsMsgGroup(json);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (o instanceof NewsMsgGroup) {
			cacheData = (NewsMsgGroup) o;
		}
		return cacheData;
	}
	
	private void write() {
		writeThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// 向文件写入数据
				FileUtil.saveCache(cacheFile, cacheData);
			}
		});
		writeThread.start();
	}

	public static void waitExit() {
		try {
			if (writeThread != null) {
				writeThread.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

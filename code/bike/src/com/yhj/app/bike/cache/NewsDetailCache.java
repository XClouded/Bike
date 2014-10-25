package com.yhj.app.bike.cache;

import java.io.File;

import com.yhj.app.bike.api.JsonParse;
import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.model.pojo.Item;
import com.yhj.app.bike.model.pojo.SimpleNewsDetail;
import com.yhj.app.bike.utils.FileUtil;
import com.yhj.app.bike.utils.SLog;

public class NewsDetailCache {

	File cacheFile;

	SimpleNewsDetail cacheDetailData;
	
	String mcahceType;
	
	

	public String getMcahceType() {
		return mcahceType;
	}

//	public void setMcahceType(String mcahceType) {
//		this.mcahceType = mcahceType;
//	}

	public void setCacheDetailData(SimpleNewsDetail cacheDetailData) {
		this.cacheDetailData = cacheDetailData;
	}

	public File getCacheFile() {
		return cacheFile;
	}

	public void setCacheFile(File cacheFile) {
		this.cacheFile = cacheFile;
	}

	public NewsDetailCache() {
		mcahceType=CacheType.NEWS_CACHE;
	}

//	public NewsDetailCache(String id) {
//		int idLength=id.length();
//		if(idLength>3 && "00".equals(id.substring(idLength-2, idLength))){
//			cacheFile = new File(Constants.CACHE_FAVOR_PATH + File.separator + "detail" + File.separator + id);//如果有收藏，就使用收藏的内容
//			if (!cacheFile.exists()) {
//				cacheFile = new File(Constants.CACHE_DETAIL_PATH + id);
//			}
//		}else{
//			cacheFile = new File(Constants.CACHE_DETAIL_PATH + id);
//		}
//	}
	
	/**
	 * 构造函数
	 * @param id 新闻id
	 * @param flag  新闻类型 默认:普通新闻 favor:收藏
	 */
	public NewsDetailCache(String id, String flag) {
		initDetailCache(id, flag);
	}
	
	public NewsDetailCache(Item mItem){
		if(mItem.getFavorTimestamp() !=null && mItem.getFavorTimestamp().length()>0){
			initDetailCache(mItem.getId(),CacheType.FAVOR_CACHE);
		} else if(mItem.getChlid() !=null && mItem.getChlname() != null) {
		    initDetailCache(mItem.getId(),CacheType.RSS_CACHE);
		} else{
			initDetailCache(mItem.getId(),CacheType.NEWS_CACHE);
		}
	}
	
	public void initDetailCache(String id, String flag){
		if(flag==null){
			flag="";
		}
		mcahceType=flag;
		if(CacheType.FAVOR_CACHE.equals(flag)){
			cacheFile = new File(Constants.CACHE_FAVOR_PATH + File.separator + "detail" + File.separator + id);//如果有收藏，就使用收藏的内容
		}else if(CacheType.NEWS_CACHE.equals(flag)){
			cacheFile = new File(Constants.CACHE_DETAIL_PATH + id);//普通新闻
		}else{
			cacheFile = new File(Constants.CACHE_DETAIL_PATH + id);//普通新闻
		}
	}

	/**
	 * 从文件读取数据
	 * 
	 * @param file
	 * @return
	 */
	private SimpleNewsDetail read() {

		// 从文件读取数据

		Object o = FileUtil.readCache(cacheFile);

		SLog.i("offline", "name----->" + cacheFile.getName());

		if (o == null) {
			String json = (String) FileUtil.readString(Constants.CACHE_DETAIL_OFFLINE_PATH + cacheFile.getName());
			if (json != null && json.length() > 0) {
				try {
					o = JsonParse.parseSimpleHtmlContent(json);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (o instanceof SimpleNewsDetail) {
			cacheDetailData = (SimpleNewsDetail) o;
		}

		return cacheDetailData;
	}

	/**
	 * 向缓存文件写数据
	 */
	private void write() {
		writeThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// 向文件写入数据
				FileUtil.saveCache(cacheFile, cacheDetailData);
			}
		});
		writeThread.start();
	}

	public SimpleNewsDetail getNewsDetail() {
		return read();
	}

	public void saveNewsDetail() {
		write();
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

	private static Thread writeThread = null;
	
	public static class CacheType{
		public static final String NEWS_CACHE = "news";//普通新闻缓存
		public static final String FAVOR_CACHE = "favor";//收藏缓存
		public static final String RSS_CACHE  = "rss"; //订阅缓存
	}
}

package com.yhj.app.bike.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.model.pojo.ChannelList;
import com.yhj.app.bike.model.pojo.RemoteConfig;

/**
 * @author      haiyandu
 * @description    
 * @date        2012-7-23
 */
public class TempManager {
	public static final String TEMP_NAME = "newscont.html";
	public static final String CSS_NAME = "template.css";
	private static final String CONFIG = "config";
	private static final String REMOTE = "remote.dat";
	private static final String CHANNEL = "channel.dat";
	private static final String SCENE_ID = "channel.ini";
	private static TempManager tempManager = null;
	private String mTmeplate = null;
	private String mCss = null;
	private String sceneid = null;
	
	private TempManager() {
	}
	
	public static synchronized TempManager getManager() {
		if (tempManager == null) {
			tempManager = new TempManager();
		}
		SLog.i("TempManager", "tempManager:" + tempManager.toString());
		return tempManager;
	}
	
	private String readAssetsFile(Context context, String name) {
		ByteArrayOutputStream byteStream = null;
		InputStream stream = null;
		try {
			byteStream = new ByteArrayOutputStream();
			stream = context.getApplicationContext().getAssets().open(name);
			byte[] buffer = new byte[256];
			int readLen = -1;
			while ((readLen = stream.read(buffer)) != -1) {
				byteStream.write(buffer, 0, readLen);
				buffer = new byte[256];
			}
			buffer = null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (byteStream != null) {
					byteStream.close();
				}
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (byteStream == null) ? null : new String(byteStream.toByteArray());
	}
	
	public String getTemplate(Context context, String name) {
		if (mTmeplate == null) {
			mTmeplate = readAssetsFile(context, name);
		}
		SLog.i("TempManager", "mTmeplate:" + mTmeplate.hashCode());
		return mTmeplate;
	}
	
	public String getCss(Context context, String name) {
		if (mCss == null) {
			mCss = readAssetsFile(context, name);
		}
		SLog.i("TempManager", "mCss:" + mTmeplate.hashCode());
		return mCss;
	}
	
	public RemoteConfig getRemoteConfigInfo(Context context) {
		InputStream stream = null;
		RemoteConfig config = null;
		try {
			stream = context.getAssets().open(CONFIG + File.separator + REMOTE);
			config = (RemoteConfig) FileUtil.readSerObjectFromInStream(stream);
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
		return config;
	}

	public ChannelList getChannelListInfo(Context context) {
		InputStream stream = null;
		ChannelList channel = null;
		try {
			stream = context.getAssets().open(CONFIG + File.separator + CHANNEL);
			channel = (ChannelList) FileUtil.readSerObjectFromInStream(stream);
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
		return channel;
	}
	
	public String getSceneIdInfo(Context context){
		if (sceneid == null) {
			sceneid = readAssetsFile(context, SCENE_ID);
		}
		return sceneid;
		
	}

	/**
	 * 获取初始配置文件中的文件对象
	 * @param context
	 * @param fileName
	 * @return
	 */
	public Object getObjectFile(Context context, String fileName) {
		if(fileName == null || fileName.equals("")){
			return null;
		}
		InputStream stream = null;
		Object channel = null;
		try {
			stream = context.getAssets().open(CONFIG + File.separator + fileName);
			channel = (Object) FileUtil.readSerObjectFromInStream(stream);
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
		return channel;
	}
	
	/**
	 * 获取初始配置的图片频道信息
	 * @param context
	 * @return
	 */
	public ChannelList getPhotoChannelListInfo(Context context) {
		return (ChannelList)getObjectFile(context, Constants.CONFIG_PHOTO_CHANNEL+".dat");
	}
	
	public ChannelList getVideoChannelListInfo(Context context) {
		return (ChannelList)getObjectFile(context, Constants.CONFIG_VIDEO_CHANNEL+".dat");
	}
}

package com.yhj.app.bike.shareprefrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.model.pojo.SinaAccountsInfo;
import com.yhj.app.bike.model.pojo.WXUserInfo;
import com.yhj.app.bike.system.Application;
import com.yhj.app.bike.utils.FileUtil;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 全局参数管理类
 */
public class SpConfig {

	/**
	 * 保存频道名
	 * @param imei
	 * @author jackiecheng
	 */
	public static void putChannelIdAndName(String channelId, String channelName) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(channelId, channelName);
		editor.commit();
	}

	/**
	 * 获取频道名
	 * @param imei
	 * @author jackiecheng
	 */
	public static String getChannelNameById(String channelId) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getString(channelId, "");
	}
	
	/**
	 * 保存IMEI
	 * 
	 * @param imei
	 */
	public static void setIMEI(String imei) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(Constants.CONFIG_IMEI, imei);
		editor.commit();
	}

	/**
	 * 获取IMEI
	 * 
	 * @return
	 */
	public static String getIMEI() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getString(Constants.CONFIG_IMEI, "");
	}

	/**
	 * 保存MAC地址
	 * 
	 * @param imei
	 */
	public static void setMAC(String mac) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(Constants.CONFIG_MAC, mac);
		editor.commit();
	}

	public static String getMAC() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getString(Constants.CONFIG_MAC, "");
	}

	/**
	 * 保存型号
	 * 
	 * @param imei
	 */
	public static void setProductType(String productType) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(Constants.CONFIG_PRODUCTTYPE, productType);
		editor.commit();
	}

	/**
	 * 获取型号
	 * 
	 * @return
	 */
	public static String getProductType() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getString(Constants.CONFIG_PRODUCTTYPE, "");
	}

	/**
	 * 保存RemoteConfig对象的base64字符串
	 * 
	 * @param imei
	 */
	public static void setRemoteConfig(String config) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(Constants.REMOTE_CONFIG, config);
		editor.commit();
	}

	/**
	 * 获取RemoteConfig对象的base64字符串
	 * 
	 * @return
	 */
	public static String getRemoteConfig() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getString(Constants.REMOTE_CONFIG, "");
	}

	/**
	 * 保存SubChannel对象的base64字符串
	 * 
	 * @param imei
	 */
	public static void setSubChannel(String channel) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(Constants.SUB_CHANNEL, channel);
		editor.commit();
	}

	/**
	 * 获取SubChannel对象的base64字符串
	 * 
	 * @return
	 */
	public static String getSubChannel() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getString(Constants.SUB_CHANNEL, "");
	}

	/**
	 * 获取splash对象的base64字符串
	 * 
	 * @return
	 */
	public static void setSplashData(String splash) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(Constants.SPLASH_DATA, splash);
		editor.commit();
	}

	/**
	 * 获取SubChannel对象的base64字符串
	 * 
	 * @return
	 */
	public static String getSplashData() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getString(Constants.SPLASH_DATA, "");
	}

	/**
	 * 保存applist对象的base64字符串
	 * 
	 * @return
	 */
	public static void setAppList(String applist) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(Constants.APPLIST_DATA, applist);
		editor.commit();
	}

	/**
	 * 获取applist对象的base64字符串
	 * 
	 * @return
	 */
	public static String getAppList() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getString(Constants.APPLIST_DATA, "");
	}

	/**
	 * 保存pushconfig对象的base64字符串
	 * 
	 * @return
	 */
	public static void setPushConfig(String pushconfig) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(Constants.PUSH_CONFIG, pushconfig);
		editor.commit();
	}

	/**
	 * 获取pushconfig对象的base64字符串
	 * 
	 * @return
	 */
	public static String getPushConfig() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getString(Constants.PUSH_CONFIG, "");
	}

	/**
	 * 保存push消息号的号
	 * 
	 * @return
	 */

	public static void setPushSeq(String seq) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(Constants.PUSH_SEQ, seq);
		editor.commit();
	}

	/**
	 * 获取push消息号的号
	 * 
	 * @return
	 */

	public static String getPushSeq() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getString(Constants.PUSH_SEQ, "");
	}

	/**
	 * 保存下次更新安装提醒的时间
	 * 
	 * @return
	 */

	public static void setNextUpdataTime(long time) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putLong(Constants.UPDATA_TIME, time);
		editor.commit();
	}

	/**
	 * 获得下次更新安装提醒的时间
	 * 
	 * @return
	 */
	public static long getNextUpdataTime() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getLong(Constants.UPDATA_TIME, 0);
	}

	/**
	 * 保存动态tab
	 * 
	 * @return
	 */

	public static void setExtendedChannels(String extended) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(Constants.EXTENDED_CHANNELS, extended);
		editor.commit();
	}

	/**
	 * 获得动态tab
	 * 
	 * @return
	 */
	public static String getExtendedChannels() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getString(Constants.EXTENDED_CHANNELS, "");
	}

	/**
	 * 保存新浪微博账户信息
	 * 
	 * @return
	 */
	public static void setSinaAccountInfo(Object info) {
		try {
			String base64 = FileUtil.getSeriString(info);
			SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
			Editor editor = sp.edit();
			editor.putString(Constants.SINA_ACCOUNTS_INFO, base64);
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得新浪微博账户信息
	 * 
	 * @return
	 */
	public static SinaAccountsInfo getSinaAccountInfo() {
		SinaAccountsInfo info = null;
		try {
			SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
			String base64 = sp.getString(Constants.SINA_ACCOUNTS_INFO, "");
			info = (SinaAccountsInfo) FileUtil.getObjectFromBytes(base64);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return info;
	}

	public static void delSinaAccountsInfo() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove(Constants.SINA_ACCOUNTS_INFO);
		editor.commit();
	}

	/**
	 * 保存微信授权信息
	 * 
	 * @return
	 */
	public static void saveWXSendAuthResp(WXUserInfo info) {
		try {
			String base64 = FileUtil.getSeriString(info);
			SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
			Editor editor = sp.edit();
			editor.putString(Constants.WX_ACCOUNTS_INFO, base64);
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得微信授权信息
	 * 
	 * @return
	 */
	public static WXUserInfo loadWXSendAuthResp() {
		WXUserInfo info = null;
		
		try {
			SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
			String base64 = sp.getString(Constants.WX_ACCOUNTS_INFO, "");
			info = (WXUserInfo) FileUtil.getObjectFromBytes(base64);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return info;
	}

	public static void delWXSendAuthResp() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove(Constants.WX_ACCOUNTS_INFO);
		editor.commit();
	}

	public static void delCheckUpdateFlag(){
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove(Constants.CHECK_UPDATE);
		editor.commit();
	}
	
	public static void delSilentDownloadFlag(){
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove(Constants.SILENT_DOWNLOAD);
		editor.commit();
	}
	
	public static void delAll() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}

	/**
	 * 保存Config属性对象的base64字符串
	 * 
	 * @param configName
	 * @param configValue
	 */
	public static void setConfig(String configName, String configValue) {
		if(configName==null || configName.equals("")){
			return;
		}
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(configName, configValue);
		editor.commit();
	}

	/**
	 * 获取Config属性对象的base64字符串
	 * 
	 * @param configName
	 * @return
	 */
	public static String getConfig(String configName) {
		if(configName==null || configName.equals("")){
			return "";
		}
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getString(configName, "");
	}
	
	/**
	 * 保存是否点击检查更新
	 * 
	 * @return
	 */

	public static void setCheckUpdateFlag(boolean flag) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean(Constants.CHECK_UPDATE, flag);
		editor.commit();
	}

	/**
	 * 获得是否点击检查更新
	 * 
	 * @return
	 */
	public static boolean getCheckUpdateFlag() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getBoolean(Constants.CHECK_UPDATE, false);
	}
	
	/**
	 * 保存是否静默下载状态
	 * 
	 * @return
	 */

	public static void setSilentDownloadFlag(int flag) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt(Constants.SILENT_DOWNLOAD, flag);
		editor.commit();
	}

	/**
	 * 获得是是否静默下载完毕的安装包
	 * 
	 * @return
	 */
	public static int getSilentDownloadFlag() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getInt(Constants.SILENT_DOWNLOAD, Constants.SILENT_DEFAULT);
	}
	
    /**
     * 添加订阅时，是否已经弹出过登录对话框
     * @param show
     */
    public static void setRssLoginAlert(boolean show) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(Constants.SP_RSS_LOGIN_ALERT, show);
        editor.commit();
    }

    public static boolean getRssLoginAlert() {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
        return sp.getBoolean(Constants.SP_RSS_LOGIN_ALERT, false);
    }

    /**
     * 订阅频道的版本号。服务器下发。
     */
    public static void setRssChannelVersion(String version) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(Constants.SP_RSS_CHANNEL_VERSION, version);
        editor.commit();
    }

    public static String getRssChannelVersion() {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
        return sp.getString(Constants.SP_RSS_CHANNEL_VERSION, "0");
    }

    /**
     * 订阅频道cover的参数
     */
    public static void setRssCoverParam(String param) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(Constants.SP_RSS_COVER_PARAM, param);
        editor.commit();
    }

    public static String getRssCoverParam() {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
        return sp.getString(Constants.SP_RSS_COVER_PARAM, "");
    }
    
	/**
	 * 保存下次更新安装提醒的时间
	 * 
	 * @return
	 */

	public static void setRSSRefreshTime(long time) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putLong(Constants.SP_RSS_CHECK_TIME, time);
		editor.commit();
	}

	/**
	 * 获得下次更新安装提醒的时间
	 * 
	 * @return
	 */
	public static long getRSSRefreshTime() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getLong(Constants.SP_RSS_CHECK_TIME, 0);
	}
	
	public static String getUUID() {
        String uuid = null;
	    SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
	    uuid = sp.getString(Constants.SP_UUID, null);
        if (uuid == null || uuid.length() == 0) {
            uuid = randomUUID();
            Editor editor = sp.edit();
            editor.putString(Constants.SP_UUID, uuid);
            editor.commit();
        }
        return uuid;
	}

    private static String randomUUID() {
        SecureRandom ng = new SecureRandom();
        byte[] randomBytes = new byte[16];
        ng.nextBytes(randomBytes);
        randomBytes[6] &= 0x0f; /* clear version */
        randomBytes[6] |= 0x40; /* set to version 4 */
        randomBytes[8] &= 0x3f; /* clear variant */
        randomBytes[8] |= 0x80; /* set to IETF variant */

        long msb = 0;
        long lsb = 0;
        assert randomBytes.length == 16 : "data must be 16 bytes in length";
        for (int i = 0; i < 8; i++) {
            msb = (msb << 8) | (randomBytes[i] & 0xff);
        }
        for (int i = 8; i < 16; i++) {
            lsb = (lsb << 8) | (randomBytes[i] & 0xff);
        }

        UUID uuid = new UUID(msb, lsb);
        return uuid.toString();
    }
    


    /**
     * 内容页获得是否第一次显示媒体名片
     * 
     * @return
     */    
    public static void setFristRssMedia(boolean hasNew) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(Constants.SP_RSS_FIRST_RSS_MEDIA, hasNew);
        editor.commit();
    }
    
    public static boolean getFristRssMedia() {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
        return sp.getBoolean(Constants.SP_RSS_FIRST_RSS_MEDIA, false);
    }
    
    
    
	public static void setHasNewRSS(boolean hasNew) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean(Constants.SP_RSS_HAS_UPDATE, hasNew);
		editor.commit();
	}

	/**
	 * 获得是否有新的RSS
	 * 
	 * @return
	 */
	public static boolean getHasNewRSS() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
		return sp.getBoolean(Constants.SP_RSS_HAS_UPDATE, false);
	}
    
    /**
     * set the value for whether Rss guide had run
     * 
     * @return
     */

    public static void setRSSGuideAlreadyRun(boolean bRun) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(Constants.SP_RSS_GUIDE_ALREADY_RUN, bRun);
        editor.commit();
    }

    /**
     * check whether rss guide had run.
     * 
     * @return
     */
    public static boolean getRSSGuideAlreadyRun() {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
        return sp.getBoolean(Constants.SP_RSS_GUIDE_ALREADY_RUN, false);
    }
}

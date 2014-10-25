package com.yhj.app.bike.download;

/**
 * 下载块信息模型
 * */

public class DownloadDataInfo {
	
	private String appId;
	private String packageName;//下载包包名
	private String urlStr;//下载URL地址
	private String phonyPercent;
	private int networkType;
	
	private int threadId;// 下载线程id
	private long startPos;// 该下载线程的起始位置,类似数组下标,从0开始
	private long endPos;// 该下载线程的结束位置
	private long compeleteSize;// 该下载线程的下载完成大小 ,类似数组长度,从1开始
	
	private String pushTitle = "";
	private String pushContent = "";
	private int pushType;
	private String pushIcon = "";	
	private String sendTime = "";

	
	
	public DownloadDataInfo(int threadId, long startPos, long endPos,
			long compeleteSize, String urlStr, String appId,String packageName,String phonyPercent, int networkType
			,String pushTitle, String pushContent, int pushType, String pushIcon, String sendTime){
		this.threadId = threadId;
		this.startPos = startPos;
		this.endPos = endPos;
		this.compeleteSize = compeleteSize;
		this.urlStr = urlStr;
		this.appId = appId;
		this.packageName = packageName;
		this.phonyPercent = phonyPercent;
		this.networkType = networkType;
		this.pushTitle = pushTitle;
		this.pushContent = pushContent;
		this.pushType = pushType;
		this.pushIcon = pushIcon;
		this.sendTime = sendTime;
		
		
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getUrlStr() {
		return urlStr;
	}

	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}

	public String getPhonyPercent() {
		return phonyPercent;
	}

	public void setPhonyPercent(String phonyPercent) {
		this.phonyPercent = phonyPercent;
	}

	public int getNetworkType() {
		return networkType;
	}

	public void setNetworkType(int networkType) {
		this.networkType = networkType;
	}

	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}

	public long getStartPos() {
		return startPos;
	}

	public void setStartPos(long startPos) {
		this.startPos = startPos;
	}

	public long getEndPos() {
		return endPos;
	}

	public void setEndPos(long endPos) {
		this.endPos = endPos;
	}

	public long getCompeleteSize() {
		return compeleteSize;
	}

	public void setCompeleteSize(long compeleteSize) {
		this.compeleteSize = compeleteSize;
	}

	public String getPushTitle() {
		return pushTitle;
	}

	public void setPushTitle(String pushTitle) {
		this.pushTitle = pushTitle;
	}

	public String getPushContent() {
		return pushContent;
	}

	public void setPushContent(String pushContent) {
		this.pushContent = pushContent;
	}

	public int getPushType() {
		return pushType;
	}

	public void setPushType(int pushType) {
		this.pushType = pushType;
	}

	public String getPushIcon() {
		return pushIcon;
	}

	public void setPushIcon(String pushIcon) {
		this.pushIcon = pushIcon;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	

	

}

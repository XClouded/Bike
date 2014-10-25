package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.List;

public class RemoteConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5765038261979657922L;
	private String ret;
	private String version;
	private String BoutiqueVersion;
	private String SubMenuVersion;
	private String SubMenuExtendVersion;
	private String SplashVersion;
	private String QQADVersion;
	private String needAudioInM3u8;
	private String ListMode;
	private String AppMode;
	private String AppModeEx;
	private String cleanid;
	private List<Offline> OfflineList;
	private List<HotChannel> HotChannelList;
	private NewsVersion newsVersion;
	
	public RemoteConfig(){
		
	}
	
	public String getSubMenuExtendVersion() {
		return this.SubMenuExtendVersion;
	}

	public void setSubMenuExtendVersion(String subMenuExtendVersion) {
		this.SubMenuExtendVersion = subMenuExtendVersion;
	}

	public String getAppModeEx() {
		return this.AppModeEx;
	}

	public void setAppModeEx(String appModeEx) {
		this.AppModeEx = appModeEx;
	}

	public void setRet(String ret){
		this.ret = ret;
	}
	
	public String getRet(){
		return this.ret;
	}
	
	public void setVersion(String version){
		this.version = version;
	}
	
	public String getVersion(){
		return this.version;
	}
	
	public void setBoutiqueVersion(String BoutiqueVersion){
		this.BoutiqueVersion = BoutiqueVersion;
	}
	
	public String getBoutiqueVersion(){
		return this.BoutiqueVersion;
	}
	
	public void setSubMenuVersion(String SubMenuVersion){
		this.SubMenuVersion = SubMenuVersion;
	}
	
	public String getSubMenuVersion(){
		return this.SubMenuVersion;
	}
	
	public void setSplashVersion(String SplashVersion){
		this.SplashVersion = SplashVersion;
	}
	
	public String getSplashVersion(){
		return this.SplashVersion;
	}
	
	public void setQQADVersion(String QQADVersion){
		this.QQADVersion = QQADVersion;
	}
	
	public String getQQADVersion(){
		return this.QQADVersion;
	}
	
	public void setNeedAudioInM3u8(String needAudioInM3u8){
		this.needAudioInM3u8 = needAudioInM3u8;
	}
	
	public String getNeedAudioInM3u8(){
		return this.needAudioInM3u8;
	}
	
	public void setListMode(String ListMode){
		this.ListMode = ListMode;
	}
	
	public String getListMode(){
		return this.ListMode;
	}
	
	public void setAppMode(String AppMode){
		this.AppMode = AppMode;
	}
	
	public String getAppMode(){
		return this.AppMode;
	}
	
	public void setOfflineList(List<Offline> OfflineList){
		this.OfflineList = OfflineList;
	}
	
	public List<Offline> getOfflineList(){
		return this.OfflineList;
	}
	
	public void setHotChannel(List<HotChannel> HotChannelList){
		this.HotChannelList = HotChannelList;
	}
	
	public List<HotChannel> getHotChannel(){
		return this.HotChannelList;
	}
	
	public void setNewsVersion(NewsVersion newsVersion){
		this.newsVersion = newsVersion;
	}
	
	public NewsVersion getNewsVersion(){
		return this.newsVersion;
	}
	
	public void setCleanId(String cleanid){
		this.cleanid = cleanid;
	}
	
	public String getCleanId(){
		return this.cleanid;
	}
}

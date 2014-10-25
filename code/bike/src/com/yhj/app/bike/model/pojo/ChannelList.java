package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.List;

public class ChannelList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8405807497858620517L;
	private String ret;
	private String version;
	private List<Channel> Channels;
	private List<LocalChannel> LocalChannels;
	
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
	
	public void setChannelList(List<Channel> Channels){
		this.Channels = Channels;
	}
	
	public List<Channel> getChannelList(){
		return this.Channels;
	}
	

	public List<LocalChannel> getLocalChannels() {
		return LocalChannels;
	}

	public void setLocalChannels(List<LocalChannel> localChannels) {
		LocalChannels = localChannels;
	}
	
	@Override
	public String toString() {
		return "ChannelList"+"ret=" + ret + "version=" + version + "Channels" + Channels.toString();
	}
}

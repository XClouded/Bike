package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class ExtendedChannels implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2991241677446246226L;
	private String ret;
	private String version;
	private String name;
	private String chlname;
	private String icon;
	private String icon_hl;
	private DynamicChannel[] channellist;
	
	public ExtendedChannels(){
		
	}
	
	public String getRet() {
		return this.ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public String getVersion() {
		return this.version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChlname() {
		return this.chlname;
	}
	public void setChlname(String chlname) {
		this.chlname = chlname;
	}
	public String getIcon() {
		return this.icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIcon_hl() {
		return this.icon_hl;
	}
	public void setIcon_hl(String icon_hl) {
		this.icon_hl = icon_hl;
	}
	public DynamicChannel[] getChannellist() {
		return this.channellist;
	}
	public void setChannellist(DynamicChannel[] channellist) {
		this.channellist = channellist;
	}
}

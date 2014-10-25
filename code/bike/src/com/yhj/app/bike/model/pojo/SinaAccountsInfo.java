package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

public class SinaAccountsInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long expires;
	private String token;
	private String sina_id ;
	private String sina_screen_name;
	private String sina_name;
	private String sina_domain;
	private String sina_profile_image_url;
	
	public SinaAccountsInfo(){
		
	}
	
	public long getExpires() {
		return expires;
	}
	
	public void setExpires(long expires) {
		this.expires = expires;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getSina_id() {
		return sina_id;
	}
	
	public void setSina_id(String sina_id) {
		this.sina_id = sina_id;
	}
	
	public String getSina_screen_name() {
		return sina_screen_name;
	}
	
	public void setSina_screen_name(String sina_screen_name) {
		this.sina_screen_name = sina_screen_name;
	}
	
	public String getSina_name() {
		return sina_name;
	}
	
	public void setSina_name(String sina_name) {
		this.sina_name = sina_name;
	}
	
	public String getSina_domain() {
		return sina_domain;
	}
	
	public void setSina_domain(String sina_domain) {
		this.sina_domain = sina_domain;
	}
	
	public String getSina_profile_image_url() {
		return sina_profile_image_url;
	}
	
	public void setSina_profile_image_url(String sina_profile_image_url) {
		this.sina_profile_image_url = sina_profile_image_url;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "expires " + expires + "token "+token+"sina_id "+
				sina_id + "sina_screen_name "+sina_screen_name + 
				"sina_name "+sina_name + "sina_domain "+sina_domain+
				"sina_profile_image_url "+sina_profile_image_url;
	}
}

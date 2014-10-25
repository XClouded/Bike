package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ListMapData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8310928118921451647L;
	
	private String version = "0";
	private ArrayList<HashMap<String, Object>> menuDataMap = new ArrayList<HashMap<String, Object>>();
	private HashMap<String, Object> mHashMap = new HashMap<String, Object>();
	
	public ArrayList<HashMap<String, Object>> getMenuDataMap() {
		return menuDataMap;
	}
	public void setMenuDataMap(ArrayList<HashMap<String, Object>> menuDataMap) {
		this.menuDataMap = menuDataMap;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public HashMap<String, Object> getmHashMap() {
		return mHashMap;
	}
	public void setmHashMap(HashMap<String, Object> mHashMap) {
		this.mHashMap = mHashMap;
	}

}

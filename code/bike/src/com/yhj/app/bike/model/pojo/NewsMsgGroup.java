package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.List;

public class NewsMsgGroup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3036916305094779672L;
	private String ret;
	private List<NewsMsg> data;
	private String anymore; //0:还有数据要继续拉 1:数据已拉完，不必要再拉了
	private String isRefresh; // 当请求mtime传0时isrefresh返回1， 否则isrefresh返回0
	
	public NewsMsgGroup(){
		
	}
	
	public String getIsRefresh() {
		return isRefresh;
	}
	
	public void setIsRefresh(String isRefresh) {
		this.isRefresh = isRefresh;
	}
	
	public String getRet() {
		return ret;
	}
	
	public void setRet(String ret) {
		this.ret = ret;
	}
	
	public List<NewsMsg> getData() {
		return data;
	}
	
	public void setData(List<NewsMsg> data) {
		this.data = data;
	}
	
	public String getAnymore() {
		return anymore;
	}
	
	public void setAnymore(String anymore) {
		this.anymore = anymore;
	}
}

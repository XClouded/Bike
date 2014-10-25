package com.yhj.app.bike.model.dataloader;

import com.yhj.app.bike.command.HttpDataRequest;
import com.yhj.app.bike.command.HttpDataResponse;
import com.yhj.app.bike.http.HttpEngine.HttpCode;
import com.yhj.app.bike.model.OrgData;
import com.yhj.app.bike.task.TaskManager;
import com.yhj.app.bike.utils.SLog;

public abstract class BaseDataLoader implements HttpDataResponse {
	public interface DataLoaderCallback {
		
		public void onQueryError(BaseDataLoader dataloader, HttpCode retCode, String msg);

		public void onQueryComplete(BaseDataLoader dataloader, boolean cache);

		public void onStartQuery(BaseDataLoader dataloader);

		public void onQuerying(BaseDataLoader dataloader);
		
		public void onQueryCanceled(BaseDataLoader dataloader);
	}

	public static final int LOADER_FROM_CACHE = 1;
	public static final int LOADER_FROM_SRV = 2;
	public static final int LOADER_FROM_SRV_AND_CACHE = 3;

	private String m_strTag;
	private boolean m_bLoading;

	private DataLoaderCallback m_callBack;

	public BaseDataLoader(String tag, DataLoaderCallback callBack) {
		this.m_strTag = tag;
		this.m_callBack = callBack;
		m_bLoading = false;
	}
	
	public BaseDataLoader(DataLoaderCallback callBack) {
		this.m_strTag = "0";
		this.m_callBack = callBack;
		m_bLoading = false;
	}

	public String getTag() {
		return this.m_strTag;
	}

	public void loadData(int loadType, HttpDataRequest request) {
		
		if (m_bLoading) {
			m_callBack.onQuerying(this);
			return;
		}
		
		m_bLoading = true;
		m_callBack.onStartQuery(this);
		// load from cache
		if ((loadType & LOADER_FROM_CACHE) > 0) {
			if (needLoadFromCache()) {
				loadFromCache(request);
				m_callBack.onQueryComplete(this, true);
			} else {

			}
		}
		// only load from cache 
		if(loadType == LOADER_FROM_CACHE)		
		{
			m_bLoading = false; 
			return ;
		}
		// load from server 
		if ((loadType & LOADER_FROM_SRV) > 0) {
			if (needLoadFromSrv()) {
				loadFrmSrv(request);
			} else {

			}
		}
	}

	public  void loadFromCache(HttpDataRequest request)
	{
		
	}

	public  void loadFrmSrv(HttpDataRequest request) 
	{
		TaskManager.startHttpDataRequset(request, this);
	}
	
	/*public  void setData(Object result) 
	{
		
	}
	
	public  Object getData() 
	{
		return null;
	}*/
	
	public void setOrgData(OrgData res) {
		
	}
	
	public OrgData getOrgData() {
		return null;
	}
	
	public boolean needLoadFromSrv() {
		return true;
	}

	public boolean needLoadFromCache() {
		return true;
	}

	public String getCachePath(HttpDataRequest request) {
		return "";
	}
	
	public boolean parseItem(String data)
	{
		
	
		return true;
	}
	

	public void onHttpRecvOK(HttpDataRequest request, Object result) {
		SLog.i("onrecvok");
		SLog.e(request.getUrl(),result.toString());
		
		setOrgData((OrgData)result);
		
		m_callBack.onQueryComplete(this,false);
		m_bLoading = false;
	}

	public void onHttpRecvError(HttpDataRequest request, HttpCode retCode,String msg) {
		SLog.e("onHttpRecvError HttpCode="+ retCode );
		m_callBack.onQueryError(this, retCode, msg);
		m_bLoading = false;
	}

	public void onHttpRecvCancelled(HttpDataRequest request) {
		SLog.e("onHttpRecvCancelled ");
		m_callBack.onQueryCanceled(this);
		m_bLoading = false;
	}
}

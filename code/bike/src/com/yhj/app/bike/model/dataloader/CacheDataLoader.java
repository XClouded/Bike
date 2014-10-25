package com.yhj.app.bike.model.dataloader;

import com.tencent.mm.algorithm.MD5;
import com.yhj.app.bike.command.HttpCryptPostRequest;
import com.yhj.app.bike.command.HttpDataRequest;
import com.yhj.app.bike.command.HttpPostRequest;
import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.http.HttpEngine.HttpCode;
import com.yhj.app.bike.http.session.SessionData;
import com.yhj.app.bike.model.OrgData;
import com.yhj.app.bike.task.TaskManager;
import com.yhj.app.bike.utils.FileUtil;
import com.yhj.app.bike.utils.SLog;

public class CacheDataLoader extends BaseDataLoader {
	
	private OrgData data;
	
	public CacheDataLoader(String tag, DataLoaderCallback callBack) {
		super(tag, callBack);
	}

	@Override
	public void loadFromCache(HttpDataRequest request) {
		String cachefile = getCachePath(request);
		data = (OrgData) FileUtil.readSerObjectFromFile(cachefile);
	}
	
	@Override
	public void loadFrmSrv(HttpDataRequest request) {
		TaskManager.startHttpDataRequset(request, this);
	}
	
	public OrgData getOrgData() {
		return data;
	}

	public void setOrgData(OrgData orgData) {
		this.data = orgData;
	}

	public String getCachePath(HttpDataRequest request) {
		
		String userid = (String) SessionData.get().getVal(SessionData.KEY_USER_ID);
		
		String tag = String.valueOf(request.getTag());
		String file;
		if(request instanceof HttpCryptPostRequest ){
			String body = ((HttpCryptPostRequest)request).getOrgBody();
			file = MD5.getMD5(body);
		}else if(request instanceof HttpPostRequest ){
			String body = ((HttpPostRequest)request).getBody();
			file = MD5.getMD5(body);
		}else{
			file = MD5.getMD5(request.getUrl());
		}
		
		if (userid == null) {
			SLog.e("Can't get userid from session file");
//			throw new RuntimeException("Can't get userid from session file");
			return null;
		}
		
		return Constants.CACHE_PATH+ tag + "/" + userid + "/" + file;
	}

	@Override
	public void onHttpRecvOK(HttpDataRequest request, Object result) {
		super.onHttpRecvOK(request, result);
		
		String cachefile = getCachePath(request);
		if (cachefile != null) {
			FileUtil.saveSerObjectToFile(this.data, cachefile);
		}
	}

	@Override
	public void onHttpRecvError(HttpDataRequest request, HttpCode retCode,
			String msg) {
		super.onHttpRecvError(request, retCode, msg);
	}

	@Override
	public void onHttpRecvCancelled(HttpDataRequest request) {
		super.onHttpRecvCancelled(request);
	}
}

package com.yhj.app.bike.command;

import com.yhj.app.bike.http.HttpEngine.HttpCode;
import com.yhj.app.bike.system.NetStatusReceiver;
import com.yhj.app.bike.utils.SLog;

public class GetImageRequest extends BaseHttpRequest {
	private Object tag;
	private String filePath;

	public Object getTag() {
		return tag;
	}

	public void setTag(Object tag) {
		this.tag = tag;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public HttpCode prepareRequest() {
		// TODO Auto-generated method stub
		HttpCode code;

		if (NetStatusReceiver.netStatus == NetStatusReceiver.NETSTATUS_INAVAILABLE) {
			code = HttpCode.ERROR_NO_CONNECT;
			return code;
		}

		/**
		 * 查看是否已取消
		 */
		if (isCancelled()) {
			code = HttpCode.USER_CANCELLED;
			return code;
		}

		/**
		 * 检查URL是否正确，已经是否有用户信息
		 */
		code = checkUrlParams();

		if (code == HttpCode.STATUS_OK) {
			/**
			 * 加入用户身份信息
			 */
			if (isNeedAuth()) {
				addUserVerifyInfo();
			}

			/**
			 * 已经在图片请求初期就在generateImageID()中调用，不需要再去执行。
			 */
		//	makeUrlWithSystemInfo();
		}

		SLog.d(SLog.BIKE, "[System(图片请求)]: URL = " + getUrl());
		return code;
	}
}

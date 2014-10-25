package com.yhj.app.bike.command;

import com.yhj.app.bike.http.HttpEngine.HttpCode;

public interface HttpDataResponse {

	void onHttpRecvOK(HttpDataRequest request, Object result);

	void onHttpRecvError(HttpDataRequest request, HttpCode retCode, String msg);

	void onHttpRecvCancelled(HttpDataRequest request);
}

package com.yhj.app.bike.download;


public interface IErrorProcessor {
	void handleError(HttpMsg request, HttpMsg response);
}

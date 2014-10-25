package com.yhj.app.bike.command;

import android.graphics.Bitmap;

import com.yhj.app.bike.model.pojo.ImageType;

public interface GetImageResponse {
	void onImageRecvOK(ImageType imageType,Object tag, Bitmap bm, String path);
	void onImageRecvError(ImageType imageType, Object tag, int retCode);
}

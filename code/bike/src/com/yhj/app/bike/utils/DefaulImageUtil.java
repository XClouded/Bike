package com.yhj.app.bike.utils;

import android.graphics.Bitmap;

import com.yhj.app.bike.R;
import com.yhj.app.bike.system.Application;

public class DefaulImageUtil {
	private static Bitmap defaultNewsListIcon = null;
	private static Bitmap defaultListHeadIcon = null;
	private static Bitmap defaultPhotoListIcon = null;
	private static Bitmap defaultVideoDetailIcon = null;
	private static Bitmap defaultPhotoDetailIcon = null;
	private static Bitmap defaultTopicListIcon = null;
	private static Bitmap defaultImgTxtImage = null;
	//private static Bitmap defaultBitmapImg = null;
	//以下为夜间模式使用的bitmap
	private static Bitmap nightDefaultNewsListIcon = null;
	private static Bitmap nightDefaultListHeadIcon = null;
	private static Bitmap nightDefaultPhotoListIcon = null;
	private static Bitmap nightDefaultVideoDetailIcon = null;
	private static Bitmap nightDefaultPhotoDetailIcon = null;
	private static Bitmap nightDefaultTopicListIcon = null;
	private static Bitmap nightDefaultImgTxtImage = null;
	
	/*public static Bitmap getBitMapImageByResID(int resID){		
		defaultBitmapImg = ImageUtil.FromResToBitmap(Application.getInstance().getResources(), resID);
		return defaultBitmapImg;
	}*/
	
	public static Bitmap getDefaultTimelineImage() {
		if(defaultNewsListIcon == null || defaultNewsListIcon.isRecycled()){
			//defaultNewsListIcon = ImageUtil.FromResToBitmap(Application.getInstance().getResources(), R.drawable.list_default_image);
		}
		return defaultNewsListIcon;
	}
	
	public static Bitmap getDefaultListHeadImage(){
		if(defaultListHeadIcon == null || defaultListHeadIcon.isRecycled()){
			//defaultListHeadIcon = ImageUtil.FromResToBitmap(Application.getInstance().getResources(),R.drawable.list_head_default_image);
		}
		return defaultListHeadIcon;
	}
	
	public static Bitmap getDefaultPhotoListImage(){
		if(defaultPhotoListIcon == null || defaultPhotoListIcon.isRecycled()){
			//defaultPhotoListIcon = ImageUtil.FromResToBitmap(Application.getInstance().getResources(),R.drawable.list_photo_default_image);
		}
		return defaultPhotoListIcon;
	}
	
	public static Bitmap getDefaultVideoDetailImage(){
		if(defaultVideoDetailIcon == null || defaultVideoDetailIcon.isRecycled()){
			//defaultVideoDetailIcon = ImageUtil.FromResToBitmap(Application.getInstance().getResources(),R.drawable.video_default_image);
		}
		return defaultVideoDetailIcon;
	}
	
	public static Bitmap getDefaultTopicListImage(){
		if(defaultTopicListIcon == null || defaultTopicListIcon.isRecycled()){
			//defaultTopicListIcon = ImageUtil.FromResToBitmap(Application.getInstance().getResources(),R.drawable.list_topic_default_image);
		}
		return defaultTopicListIcon;
	}
	
	public static Bitmap getDefaultPhotoDetailImage(){
		if(defaultPhotoDetailIcon == null || defaultPhotoDetailIcon.isRecycled()){
			//defaultPhotoDetailIcon = ImageUtil.FromResToBitmap(Application.getInstance().getResources(),R.drawable.photo_detail_default_image);
		}
		return defaultPhotoDetailIcon;
	}
	
	public static Bitmap getDefaultImgTxtImage(){
		if(null == defaultImgTxtImage || defaultImgTxtImage.isRecycled()){
			//defaultImgTxtImage = ImageUtil.FromResToBitmap(Application.getInstance().getResources(),R.drawable.default_live_placehold);
		}
		return defaultImgTxtImage;
	}
	
	//夜间模式使用的函数
	public static Bitmap getNightDefaultTimelineImage() {
		if(nightDefaultNewsListIcon == null || nightDefaultNewsListIcon.isRecycled()){
			//nightDefaultNewsListIcon = ImageUtil.FromResToBitmap(Application.getInstance().getResources(), R.drawable.night_list_default_image);
		}
		return nightDefaultNewsListIcon;
	}
	
	public static Bitmap getNightDefaultListHeadImage(){
		if(nightDefaultListHeadIcon == null || nightDefaultListHeadIcon.isRecycled()){
			//nightDefaultListHeadIcon = ImageUtil.FromResToBitmap(Application.getInstance().getResources(),R.drawable.night_list_head_default_image);
		}
		return nightDefaultListHeadIcon;
	}
	
	public static Bitmap getNightDefaultPhotoListImage(){
		if(nightDefaultPhotoListIcon == null || nightDefaultPhotoListIcon.isRecycled()){
			//nightDefaultPhotoListIcon = ImageUtil.FromResToBitmap(Application.getInstance().getResources(),R.drawable.night_list_photo_default_image);
		}
		return nightDefaultPhotoListIcon;
	}
	
	public static Bitmap getNightDefaultVideoDetailImage(){
		if(nightDefaultVideoDetailIcon == null || nightDefaultVideoDetailIcon.isRecycled()){
			//nightDefaultVideoDetailIcon = ImageUtil.FromResToBitmap(Application.getInstance().getResources(),R.drawable.night_video_default_image);
		}
		return nightDefaultVideoDetailIcon;
	}
	
	public static Bitmap getNightDefaultTopicListImage(){
		if(nightDefaultTopicListIcon == null || nightDefaultTopicListIcon.isRecycled()){
			//nightDefaultTopicListIcon = ImageUtil.FromResToBitmap(Application.getInstance().getResources(),R.drawable.night_list_topic_default_image);
		}
		return nightDefaultTopicListIcon;
	}
	
	/*
	public static Bitmap getNightDefaultPhotoDetailImage(){
		if(nightDefaultPhotoDetailIcon == null || nightDefaultPhotoDetailIcon.isRecycled()){
			nightDefaultPhotoDetailIcon = ImageUtil.FromResToBitmap(Application.getInstance().getResources(),R.drawable.night_photo_detail_default_image);
		}
		return nightDefaultPhotoDetailIcon;
	}*/
	
	public static Bitmap getNightDefaultImgTxtImage(){
		if(null == nightDefaultImgTxtImage || nightDefaultImgTxtImage.isRecycled()){
			//nightDefaultImgTxtImage = ImageUtil.FromResToBitmap(Application.getInstance().getResources(),R.drawable.night_default_live_placehold);
		}
		return nightDefaultImgTxtImage;
	}
}

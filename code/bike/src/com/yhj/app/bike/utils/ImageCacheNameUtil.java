package com.yhj.app.bike.utils;

import java.io.File;

import android.content.Context;
import android.os.Environment;

import com.yhj.app.bike.system.Application;

public class ImageCacheNameUtil {
	private static final String CHARACTER_DIVIDER = File.separator;
	private static final String SMALL_IMAGE_SUFFIX = ".sic";
	private static final String LARGE_IMAGE_SUFFIX = ".lic";
	private static final String PNG_IMAGE_SUFFIX = ".pic";
	private static final String SAVE_IMAGE_SUFFIX = ".jpg";

	private static final String CACHE_IMAGE_DIRECTION = "image" + CHARACTER_DIVIDER;
	/**
	 * Tencent/TencentNews
	 */
	private static final String SAVE_IMAGE_DIR = "Tencent" + CHARACTER_DIVIDER + "TencentNews";
	private static final String CACHE_DIR = "Tencent" + CHARACTER_DIVIDER + "TencentNews" + CHARACTER_DIVIDER + "data";
	private static final String SMALL_IMAGE_DIRECTION = "small-image-cache" + CHARACTER_DIVIDER;
	private static final String LARGE_IMAGE_DIRECTION = "large-image-cache" + CHARACTER_DIVIDER;
	private static final String PNG_IMAGE_DIRECTION = "png_image_cache" + CHARACTER_DIVIDER;
	private static final String IP_IMAGE_DIRECTION = "ip-image-cache" + CHARACTER_DIVIDER;
	private static final String SAVE_IMAGE_DIRECTION = "download" + CHARACTER_DIVIDER;
	private static final String SAVE_SPLASH_DIRECTION = "splash" + CHARACTER_DIVIDER;
	private static final String SAVE_NEW_SPLASH_DIRECTION = "new_splash" + CHARACTER_DIVIDER;
	private static final String SAVE_EXTENDED_DIRECTION = "extended" + CHARACTER_DIVIDER;
	private static final String SAVE_NEW_EXTENDED_DIRECTION = "new_extended" + CHARACTER_DIVIDER;

	public static String getSmallImageFileName(String url) {
		String path = MobileUtil.getSdCardPath() + CACHE_DIR + CHARACTER_DIVIDER + CACHE_IMAGE_DIRECTION + SMALL_IMAGE_DIRECTION + StringUtil.toMd5(url) + SMALL_IMAGE_SUFFIX;

		return path;
	}

	public static File getSmallImageFileDir() {
		String path = MobileUtil.getSdCardPath() + CACHE_DIR + CHARACTER_DIVIDER + CACHE_IMAGE_DIRECTION + SMALL_IMAGE_DIRECTION;

		return new File(path);
	}

	public static String getLargeImageFileName(String url) {
		String path = MobileUtil.getSdCardPath() + CACHE_DIR + CHARACTER_DIVIDER + CACHE_IMAGE_DIRECTION + LARGE_IMAGE_DIRECTION + StringUtil.toMd5(url) + LARGE_IMAGE_SUFFIX;

		return path;
	}

	public static File getLargeImageFileDir() {
		String path = MobileUtil.getSdCardPath() + CACHE_DIR + CHARACTER_DIVIDER + CACHE_IMAGE_DIRECTION + LARGE_IMAGE_DIRECTION;

		return new File(path);
	}

	public static String getPngImageFileName(String url) {
		String path = MobileUtil.getSdCardPath() + CACHE_DIR + CHARACTER_DIVIDER + CACHE_IMAGE_DIRECTION + PNG_IMAGE_DIRECTION + StringUtil.toMd5(url) + PNG_IMAGE_SUFFIX;

		return path;
	}

	public static File getPngImageFileDir() {
		String path = MobileUtil.getSdCardPath() + CACHE_DIR + CHARACTER_DIVIDER + CACHE_IMAGE_DIRECTION + PNG_IMAGE_DIRECTION;

		return new File(path);
	}

	public static String getSaveImageFileName(String url) {
		String path = MobileUtil.getSdCardPath() + CACHE_DIR + CHARACTER_DIVIDER + SAVE_IMAGE_DIRECTION + StringUtil.toMd5(url) + SAVE_IMAGE_SUFFIX;

		return path;
	}

	public static String getSplashImageFileName(String url) {
		String path = MobileUtil.getSdCardPath() + SAVE_IMAGE_DIR + CHARACTER_DIVIDER + SAVE_SPLASH_DIRECTION + StringUtil.toMd5(url) + PNG_IMAGE_SUFFIX;

		return path;
	}
	
	public static File getSplashImageFileDir() {
		String path = MobileUtil.getSdCardPath() + SAVE_IMAGE_DIR + CHARACTER_DIVIDER + SAVE_SPLASH_DIRECTION;

		return new File(path);
	}
	
	//splash新下载路径
	public static String getNewSplashImageFileName(String url){
		String path = MobileUtil.getSdCardPath() + SAVE_IMAGE_DIR + CHARACTER_DIVIDER + SAVE_NEW_SPLASH_DIRECTION + StringUtil.toMd5(url) + PNG_IMAGE_SUFFIX;
		return path;
	}
	
	//splash新下载目录
	public static File getNewSplashImageFileDir() {
		String path = MobileUtil.getSdCardPath() + SAVE_IMAGE_DIR + CHARACTER_DIVIDER + SAVE_NEW_SPLASH_DIRECTION;

		return new File(path);
	}
	
	public static String getExtendedIconFileName(String url) {
		String path = MobileUtil.getSdCardPath() + SAVE_IMAGE_DIR + CHARACTER_DIVIDER + SAVE_EXTENDED_DIRECTION + StringUtil.toMd5(url) + PNG_IMAGE_SUFFIX;

		return path;
	}
	
	public static File getExtendedIconFileDir() {
		String path = MobileUtil.getSdCardPath() + SAVE_IMAGE_DIR + CHARACTER_DIVIDER + SAVE_EXTENDED_DIRECTION;

		return new File(path);
	}
	
	public static String getNewExtendedIconFileName(String url) {
		String path = MobileUtil.getSdCardPath() + SAVE_IMAGE_DIR + CHARACTER_DIVIDER + SAVE_NEW_EXTENDED_DIRECTION + StringUtil.toMd5(url) + PNG_IMAGE_SUFFIX;

		return path;
	}
	
	public static File getNewExtendedIconFileDir() {
		String path = MobileUtil.getSdCardPath() + SAVE_IMAGE_DIR + CHARACTER_DIVIDER + SAVE_NEW_EXTENDED_DIRECTION;

		return new File(path);
	}
	
	/**
	 * 
	 * @return SD卡根路径-->/Tencent/TencentNews/image/
	 */
	public static File getImageDeleteCacheDir() {
		String path = MobileUtil.getSdCardPath() + CACHE_DIR + CHARACTER_DIVIDER + CACHE_IMAGE_DIRECTION;
		return new File(path);
	}

	public static String getImageProcessDir() {
		String path = MobileUtil.getSdCardPath() + MobileUtil.getPackageName() + CHARACTER_DIVIDER + CACHE_IMAGE_DIRECTION + IP_IMAGE_DIRECTION;
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return path;
	}

	public static File getCachedCameraImageFile(Context context) {
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			File cachedCameraIamgeFile = new File(getExternalFilesDir(), "camera.jpg");
			return cachedCameraIamgeFile;
		} else {
			File cachedCameraIamgeFile = new File(getInternalFilesDir(context), "camera.jpg");
			return cachedCameraIamgeFile;
		}
	}

	private static File getExternalFilesDir() {
		File externalFilesDir = new File(Environment.getExternalStorageDirectory() + CHARACTER_DIVIDER + "Android" + CHARACTER_DIVIDER + "data" + CHARACTER_DIVIDER
				+ Application.getInstance().getPackageName() + CHARACTER_DIVIDER + "files");
		if (!externalFilesDir.exists() || !externalFilesDir.isDirectory())
			externalFilesDir.mkdirs();
		return externalFilesDir;
	}

	private static File getInternalFilesDir(Context context) {
		File internalFilesDir = new File(context.getFilesDir() + CHARACTER_DIVIDER + "Android" + CHARACTER_DIVIDER + "data" + CHARACTER_DIVIDER + context.getPackageName() + CHARACTER_DIVIDER
				+ "files");
		if (!internalFilesDir.exists() || !internalFilesDir.isDirectory())
			internalFilesDir.mkdirs();
		return internalFilesDir;
	}

	public static String getImageProcessResultPath() {
		String pathDir = getImageProcessDir();

		String filePath = pathDir + "result.ipc";

		return filePath;
	}
}

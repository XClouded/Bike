/**
 * @author junzhangcheng
 * @2011-11-24
 * SOHU.com © All Rights Received.
 */
package com.yhj.app.bike.system;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;

import com.yhj.app.bike.cache.UserDBHelper;
import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.model.pojo.UserInfo;
import com.yhj.app.bike.utils.FileUtil;
import com.yhj.app.bike.utils.MobileUtil;
import com.yhj.app.bike.utils.SLog;
import com.yhj.app.bike.utils.StringUtil;

/**
 * @author junzhangcheng
 * @since 2011-11-24
 */
public class CrashHandler implements UncaughtExceptionHandler {
	// 需求是 整个应用程序 只有一个 MyCrash-Handler
	private static CrashHandler myCrashHandler = new CrashHandler();
//	private Context context;
	private SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat dataFormatFileName = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

	// 1.私有化构造方法
	private CrashHandler() {

	}

	public static CrashHandler getInstance() {
		return myCrashHandler;
	}

	public void init(Context context) {
//		this.context = context;
	}

	public void uncaughtException(Thread arg0, Throwable arg1) {

		SLog.e(arg1.toString(), arg1);

		String mobileInfo = getMobileInfo();

		String errorinfo = getErrorInfo(arg1);

		final StringBuffer s = new StringBuffer();
		s.append("------------------------------------------------------------------------\n");
		s.append(mobileInfo + "\n\n" + errorinfo + "\n");
		s.append("------------------------------------------------------------------------\n");
		SLog.e(s.toString());

		s.append("signature:" + StringUtil.toMd5("Welcome to Tencent News" + s.toString()));

		// [MOD]-->向/data/data/com.cola.twisohu/log/下的 ERRORLOG文件写入数据start
		// FileUtil.writeString(Constants.LOG_FILE, s);
		// FileUtil.save2FileCache(context, "ERRORLOG", s.getBytes());
		// [MOD]<--向/data/data/com.cola.twisohu/log/下的 ERRORLOG文件写入数据end

		if (MobileUtil.isSDCardExists()) {
			File file = new File(Constants.LOG_PATH);
			if (!file.exists()) {
				file.mkdirs();
			}
			String fileName = "log" + dataFormatFileName.format(new Date()) + ".txt";
			FileUtil.writeString(Constants.LOG_PATH + fileName, s.toString(), false);
		}

		// 系统奔溃，修复：清除缓存
		clearOldData();

		// 干掉当前的程序
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	/**
	 * 获取错误的信息
	 * 
	 * @param arg1
	 * @return
	 */
	private String getErrorInfo(Throwable arg1) {
		Writer writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		arg1.printStackTrace(pw);
		pw.close();
		String error = writer.toString();
		return error;
	}

	/**
	 * 获取出错时的环境
	 * 
	 * @return
	 */
	private String getMobileInfo() {
		StringBuffer sb = new StringBuffer();

		sb.append("MANUFACTURER:" + MobileUtil.getManufacturer());
		sb.append("\n");
		sb.append("MODEL:" + MobileUtil.getProductType());
		sb.append("\n");
		sb.append("IMEI:" + MobileUtil.getImei());
		sb.append("\n");
		//sb.append("channel:" + MobileUtil.getFrom());
		sb.append("\n");
		sb.append("AndroidSDK:" + MobileUtil.getSystemSdk());
		sb.append("\n");
		sb.append("ClientVer:" + MobileUtil.getStringVersionCode());
		sb.append("\n");
		UserInfo mUserInfo = UserDBHelper.getInstance().getUserInfo();
		sb.append("User:" + (mUserInfo != null ? mUserInfo.getAccount() : "not login >_<.."));
		sb.append("\n");
		sb.append("Time:" + dataFormat.format(new Date()));
		sb.append("\n");
		// 通过反射获取系统的硬件信息
		// try {
		//
		// Field[] fields = Build.class.getDeclaredFields();
		// for (Field field : fields) {
		// // 暴力反射 ,获取私有的信息
		// field.setAccessible(true);
		// String name = field.getName();
		// String value = field.get(null).toString();
		// sb.append(name + "=" + value);
		// }
		// } catch (Exception e) {
		// SLog.e(e.toString(),e);
		// }
		return sb.toString();
	}

	// /**
	// * 获取手机的版本信息
	// *
	// * @return
	// */
	// private String getVersionInfo() {
	// try {
	// PackageManager pm = context.getPackageManager();
	// PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
	// return info.versionName;
	// } catch (Exception e) {
	// SLog.e(e.toString(),e);
	// return "版本号未知";
	// }
	// }

	/**
	 * 清理数据
	 */
	private void clearOldData() {
		// String srcFile = "/data/data/com.cola.twisohu/databases";
		// String srcFile1 = "/data/data/com.cola.twisohu/emotions";
		// String srcFile2 = "/data/data/com.cola.twisohu/files";
		// String srcFile3 = "/data/data/com.cola.twisohu/shared_prefs";

		// 清除Sp缓存
		// SharePrefrenceUtil.clearSettingAndConfig();

		// FileUtil.delete(new File(srcFile), true);
		// FileUtil.delete(new File(srcFile1), true);
		// FileUtil.delete(new File(srcFile2), true);
		// FileUtil.delete(new File(srcFile3), true);
	}
}

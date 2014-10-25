package com.yhj.app.bike.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.http.util.EncodingUtils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.IBinder;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.yhj.app.bike.model.pojo.NewsVersion;
import com.yhj.app.bike.shareprefrence.SpConfig;
import com.yhj.app.bike.system.Application;

public class MobileUtil {

	private static final String TAG = "MobileUtil";

	private static String from = null;

	private static String imei = null;
	private static String mac = null;

	private static String systemVersion = null;

	private static String packageName = null;

	private static int versionCode = 0;

	private static String versionName = null;

	public static final int DATASIZE = 20 * 1024 * 1024;

	private static String sdCardPath = null;

	private static int screenWidth = 0;
	private static int screenHeight = 0;
	private static String sceneid = "00000";

	/**
	 * 移动步径大小
	 */
	private static int scaledTouchSlop = -1;

	public static String getFrom() {
		if (from != null && from.length() > 0) {
			return from;
		} else {
			from = getFromAssets("channel").trim();// 默认14为开发测试专用!
		}
		return from;
	}

	public static String getFromAssets(String fileName) {
		String result = "";
		try {
			InputStream in = Application.getInstance().getResources().getAssets().open(fileName);
			// 获取文件的字节数
			int lenght = in.available();
			// 创建byte数组
			byte[] buffer = new byte[lenght];
			// 将文件中的数据读到byte数组中
			in.read(buffer);
			result = EncodingUtils.getString(buffer, "utf8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getImei() {
		if (imei == null) {
			/**
			 * 获取系统保存设置
			 */
			String tmpImei = SpConfig.getIMEI();
			if (tmpImei == null || tmpImei.equals("")) {
				TelephonyManager tm = (TelephonyManager) Application.getInstance().getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
				String deviceId = tm.getDeviceId();
				if (deviceId != null && !deviceId.equals("")) {
					// imei = CodeUtil.toMD5(deviceId);
					imei = deviceId;
					SpConfig.setIMEI(imei);
					SLog.i(SLog.BIKE, "[System]本机IMEI(1)号为:" + imei);
				} else {
					/**
					 * 系统生成一个Imei号，为当前时间+6为随机码
					 */
					Random a = new Random();
					tmpImei = "" + System.currentTimeMillis() + a.nextInt(1000000);
					imei = tmpImei;
					// imei = CodeUtil.toMD5(tmpImei);
					SpConfig.setIMEI(imei);
					SLog.i(SLog.BIKE, "[System]本机IMEI(2)号为:" + imei);
				}
			} else {
				imei = tmpImei;
				SLog.i(SLog.BIKE, "[System]本机IMEI(3)号为:" + imei);
			}
		}

		if (imei == null || imei.equals("")) {
			Random a = new Random();
			String tmpImei = "" + System.currentTimeMillis() + a.nextInt(1000000);
			imei = tmpImei;
			SpConfig.setIMEI(imei);
			// imei = CodeUtil.toMD5(tmpImei);
		}
		return imei;
	}

	public static String getLocalMacAddress() {

		if (mac == null) {
			String tmpMac = SpConfig.getMAC();
			if (tmpMac == null || tmpMac.equals("")) {
				WifiManager wifi = (WifiManager) Application.getInstance().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
				WifiInfo info = wifi.getConnectionInfo();
				tmpMac = info.getMacAddress();
				if (tmpMac != null && !tmpMac.equals("")) {
					SLog.i(SLog.BIKE, "[System]本机MAC为:" + mac);
				} else {
					tmpMac = "mac unknown";
				}
				mac = tmpMac;
				SpConfig.setMAC(mac);
			} else {
				mac = tmpMac;
			}
		}
		return mac;
	}

	public static String getBossId() {
		try {
			if (Long.parseLong(getImei()) == 0L) {
				return getLocalMacAddress();
			}
		} catch (Exception e) {
		}
		return getImei();
	}

	/**
	 * 用android版本号代替
	 * 
	 * @see getSystemSdk()
	 * @return
	 */
	@Deprecated
	public static String getSystemVersion() {
		if (systemVersion == null) {
			systemVersion = android.os.Build.VERSION.RELEASE;
		}

		return systemVersion;
	}

	public static int getVersionCode() {
		if (versionCode == 0) {
			setVersionInfo();
		}
		return versionCode;
	}

	public static String getVersionName() {
		if (versionName == null) {
			setVersionInfo();
		}
		return versionName;
	}

	public static String getPackageName() {
		if (packageName == null) {
			setVersionInfo();
		}
		return packageName;
	}

	public static void setVersionInfo() {
		packageName = Application.getInstance().getPackageName();
		try {
			PackageInfo pm = Application.getInstance().getPackageManager().getPackageInfo(packageName, PackageManager.GET_CONFIGURATIONS);
			versionCode = pm.versionCode;
			versionName = pm.versionName;
		} catch (NameNotFoundException e) {
			SLog.e(TAG, e.toString(), e);
		}
	}

	public static String getStringVersionCode() {
		int code = getVersionCode();
		StringBuffer versionCode = new StringBuffer(String.valueOf(code));
		versionCode.insert(versionCode.length() - 1, ".");
		versionCode.insert(versionCode.length() - 3, ".");
		return versionCode.toString();
	}

	public static int getScreenWidthIntPx() {
		setScreenSize();
		return screenWidth;
	}

	public static int getScreenHeightIntPx() {
		setScreenSize();
		return screenHeight;
	}

	public static void setScreenSize() {
		android.view.WindowManager windowsManager = (android.view.WindowManager) Application.getInstance().getSystemService(Context.WINDOW_SERVICE);
		android.view.Display display = windowsManager.getDefaultDisplay();
		int nWidth = display.getWidth();
		int nHeight = display.getHeight();
		if (display.getOrientation() == Configuration.ORIENTATION_LANDSCAPE && nWidth >= nHeight) {
			screenWidth = Math.max(nWidth, nHeight);
			screenHeight = Math.min(nWidth, nHeight);
		} else {
			screenWidth = Math.min(nWidth, nHeight);
			screenHeight = Math.max(nWidth, nHeight);
		}
	}

	public static DisplayMetrics getDeviceDisplayMetrics(Context context) {
		android.view.WindowManager windowsManager = (android.view.WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		android.view.Display display = windowsManager.getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);

		return outMetrics;
	}

	public static int dpToPx(int dp) {
		return (int) (dp * MobileUtil.getDeviceDisplayMetrics(Application.getInstance()).density + 0.5f);
	}

	public static int pxToDp(int px) {
		return (int) (px / MobileUtil.getDeviceDisplayMetrics(Application.getInstance()).density + 0.5f);
	}

	/**
	 * 获取SD卡路径，以'/'结束
	 * 
	 * @return
	 * @author boyang
	 * @since 2011-10-26
	 */
	public static String getSdCardPath() {
		if ((sdCardPath == null) || (sdCardPath.equals(""))) {
			sdCardPath = Environment.getExternalStorageDirectory().getPath();
			if (sdCardPath.substring(sdCardPath.length() - 1).equals(File.separator) == false) {
				sdCardPath += File.separator;
			}
		}
		return sdCardPath;
	}

	public static boolean isSDCardExists() {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	public static boolean isSDCardFull() {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			String sdcard = Environment.getExternalStorageDirectory().getPath();
			StatFs statFs = new StatFs(sdcard);
			long blockSize = statFs.getBlockSize();
			long blocks = statFs.getAvailableBlocks();
			long availableSpare = blocks * blockSize;
			// int availableSpare = (int)
			// (statFs.getBlockSize()*((long)statFs.getAvailableBlocks()-4))/(1024*1024);//以比特计算
			// 换算成MB
			// MLog.d("availableSpare = " + availableSpare);
			if (DATASIZE > availableSpare) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	/**
	 * 获得生产厂商
	 * 
	 * @return
	 * @author junzhangcheng
	 * @since 2011-12-7
	 */
	public static String getManufacturer() {
		return android.os.Build.MANUFACTURER;
	}

	/**
	 * 获取手机android操作系统版本
	 * 
	 * @return
	 * @author junzhangcheng
	 * @since 2011-12-7
	 */
	public static int getSystemSdk() {
		return android.os.Build.VERSION.SDK_INT;
	}

	/**
	 * 获取手机型号
	 * 
	 * @return
	 * @author junzhangcheng
	 * @since 2011-12-7
	 */
	public static String getProductType() {
		String str = SpConfig.getProductType();
		if (str != null && str.length() > 0) {
			// str = str.replaceAll("[:{} \\[\\]\"']*", "");
		} else {
			str = android.os.Build.MODEL;
			str = str.replaceAll("[:{} \\[\\]\"']*", "");
			SpConfig.setProductType(str);
		}
		return str;
	}

	/**
	 * 隐藏软键盘
	 * 
	 * @param activity
	 */
	public static void hideKeyboard(Activity activity) {
		InputMethodManager localInputMethodManager = (InputMethodManager) activity.getSystemService("input_method");

		if (activity.getCurrentFocus() != null) {
			IBinder localIBinder = activity.getCurrentFocus().getWindowToken();
			localInputMethodManager.hideSoftInputFromWindow(localIBinder, 0);
		}
	}

	public static void measureView(View child) {
		ViewGroup.LayoutParams p = child.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		}

		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}

	// public static void clearNotification() {
	// Context context = Application.getInstance().getApplicationContext();
	// NotificationManager nm = (NotificationManager)
	// context.getSystemService(Context.NOTIFICATION_SERVICE);
	// nm.cancelAll();
	// }

	/**
	 * 显示隐藏软件键盘
	 * 
	 * @param show
	 * @param edittext
	 * @author guoqiangwang
	 * @since 2012-3-13
	 */
	public static void showInputKeyboard(boolean show, View edittext) {
		InputMethodManager imm = (InputMethodManager) Application.getInstance().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		if (show) {
			edittext.setFocusable(true);
			edittext.setFocusableInTouchMode(true);
			edittext.requestFocus();
			edittext.requestFocusFromTouch();
			imm.showSoftInput(edittext, 0);
		} else {
			// if (imm.isActive()) {
			imm.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
			// }
		}
	}

	/**
	 * 获取移动步径
	 * 
	 * @return
	 */
	public static int getScaledTouchSlop() {
		if (scaledTouchSlop == -1) {
			scaledTouchSlop = ViewConfiguration.get(Application.getInstance().getApplicationContext()).getScaledTouchSlop();
		}
		return scaledTouchSlop;
	}

	/**
	 * 版本号比较
	 * 
	 * @return
	 */
	public static boolean versionUpgrade(NewsVersion version) {
		if (version != null) {
			try {
				int newCode = Integer.parseInt(version.getVersion());
				int versionCode = getVersionCode();
				if (newCode > versionCode) {
					return true;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return false;
	}

	public static String getImsi(Context paramContext) {
		String s = ((TelephonyManager) paramContext.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
		if (s == null) {
			s = "";
		}
		return s;
	}

	public static boolean isForgroundRunning() {
		ActivityManager activityManager = (ActivityManager) Application.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
		if (tasksInfo.size() > 0) {
			if (Application.getInstance().getPackageName().equals(tasksInfo.get(0).topActivity.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	public static boolean getDebugMode() {
		boolean ISDEBUG = false;
		PackageManager pm = Application.getInstance().getApplicationContext().getPackageManager();
		try {
			ApplicationInfo info = pm.getApplicationInfo(Application.getInstance().getApplicationContext().getPackageName(), 0);
			ISDEBUG = (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
			return ISDEBUG;
		} catch (NameNotFoundException e) {
			ISDEBUG = false;
			return ISDEBUG;
		}
	}

	public static String getSceneId() {
		String data = null;
		data = TempManager.getManager().getSceneIdInfo(Application.getInstance().getApplicationContext());
		if (data != null && data.length() > 0) {
			String subDatas[] = data.split("=");
			if (subDatas.length >= 2) {
				sceneid = subDatas[1];
			}
		}
		return sceneid;
	}

	public static int getNumCores() {
		class CpuFilter implements FileFilter {
			@Override
			public boolean accept(File pathname) {
				if (Pattern.matches("cpu[0-9]", pathname.getName())) {
					return true;
				}
				return false;
			}
		}

		try {
			File dir = new File("/sys/devices/system/cpu/");
			// Filter to only list the devices we care about
			File[] files = dir.listFiles(new CpuFilter());
			// Return the number of cores (virtual CPU devices)
			return files.length;
		} catch (Exception e) {
			// Default to return 1 core
			return 1;
		}
	}
}

package com.yhj.app.bike.download;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;


public class DownloadNetworkState extends BroadcastReceiver {

	public static final int NETWORK_TYPE_UNKNOWN = 0;
	public static final int NETWORK_TYPE_WIFI = 1;
	public static final int NETWORK_TYPE_3G = 2;
	public static final int NETWORK_TYPE_2G = 3;

	private static DownloadNetworkState instance = null;

	public static DownloadNetworkState g() {
		if (null == instance) {
			instance = new DownloadNetworkState();
		}
		return instance;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e("TAG", "NetworkStateReceiver ====== " + intent.getAction());
        if (intent.getAction().compareTo(ConnectivityManager.CONNECTIVITY_ACTION) == 0) {
            notifyObservers(isNetworkConnected(context));
        }
	}

	// //////////////////////////////////////////////////////////
	private Context context = null;

	// 运营商信息
	private String providerName = null;

	// 网络类型 2G 3G WIFI
	private int networkType = 0;

	public void setContext(Context context) {
		this.context = context;

		// IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
		TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String IMSI = telephony.getSubscriberId();
		if (null == IMSI || "".equals(IMSI)) {
			providerName = "未知";
		}
		else if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
			providerName = "中国移动";
		}
		else if (IMSI.startsWith("46001")) {
			providerName = "中国联通";
		}
		else if (IMSI.startsWith("46003")) {
			providerName = "中国电信";
		}
		else {
			providerName = "未知";
		}

		// 监听网络变更状态
		IntentFilter filter = new IntentFilter(new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
		context.registerReceiver(this, filter);
		// 初始化先检测网络
		isNetworkConnected(context);
	}

	public void unregisterReceiver() {
		context.unregisterReceiver(this);
	}

	// ////////////////////////////////////////////////////////////////////

	private List<NetworkStateListener> observers = new ArrayList<NetworkStateListener>();

	public boolean isNetworkConnected() {
		if (null == context) { // 系统启动时需要初始化context
			return true;
		}
		return isNetworkConnected(context);
	}

	public String getProviderName() {
		return providerName;
	}

	public int getNetworkType() {
		return networkType;
	}

	public void addListener(NetworkStateListener listener) {
		if (null == listener) {
			return;
		}
		synchronized (observers) {
			if (!observers.contains(listener)) {
				observers.add(listener);
			}
		}
	}

	public void removeListener(NetworkStateListener listener) {
		synchronized (observers) {
			observers.remove(listener);
		}
	}

	private void notifyObservers(boolean connected) {
		int size = 0;
		NetworkStateListener[] arrays = null;
		synchronized (observers) {
			size = observers.size();
			arrays = new NetworkStateListener[size];
			observers.toArray(arrays);
		}
		if (null != arrays) {
			for (NetworkStateListener listener : arrays) {
				listener.onNetworkConnect(connected);
			}
		}
	}

	private boolean isNetworkConnected(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return true;
		}
		else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].isConnectedOrConnecting()) {
						getNetworkType(info[i]);
						return true;
					}
				}
			}
		}
		return false;
	}

	private int getNetworkType(NetworkInfo networkInfo) {
		switch (networkInfo.getType()) {
		case ConnectivityManager.TYPE_WIFI: {
			networkType = NETWORK_TYPE_WIFI;
			return networkType;
		}

		case ConnectivityManager.TYPE_MOBILE:
			switch (networkInfo.getSubtype()) {
			// EDGE (2.75G)
			case TelephonyManager.NETWORK_TYPE_EDGE: // ~ 50-100 kbps
				// GPRS (2.5G)
			case TelephonyManager.NETWORK_TYPE_GPRS: // ~ 100 kbps
				// CDMA: Either IS95A or IS95B (2G)
			case TelephonyManager.NETWORK_TYPE_CDMA: // ~ 14-64 kbps
				// 1xRTT (2G - Transitional)
			case TelephonyManager.NETWORK_TYPE_1xRTT: // ~ 50-100 kbps
				// iDen (2G)
			case TelephonyManager.NETWORK_TYPE_IDEN: // ~25 kbps
			{
				networkType = NETWORK_TYPE_2G;
				return networkType;
			}

			// UMTS (3G)
			case TelephonyManager.NETWORK_TYPE_UMTS: // ~ 400-7000 kbps
				// EVDO revision 0 (3G)
			case TelephonyManager.NETWORK_TYPE_EVDO_0: // ~ 400-1000 kbps
				// EVDO revision A (3G - Transitional)
			case TelephonyManager.NETWORK_TYPE_EVDO_A: // ~ 600-1400 kbps
				// HSDPA (3G - Transitional)
			case TelephonyManager.NETWORK_TYPE_HSDPA: // ~ 2-14 Mbps
				// HSPA (3G - Transitional)
			case TelephonyManager.NETWORK_TYPE_HSPA: // ~ 700-1700 kbps
				// HSUPA (3G - Transitional)
			case TelephonyManager.NETWORK_TYPE_HSUPA: // ~ 1-23 Mbps
			{
				networkType = NETWORK_TYPE_3G;
				return networkType;
			}

			// Unknown
			case TelephonyManager.NETWORK_TYPE_UNKNOWN:
			}
		default: // Unknown
			networkType = NETWORK_TYPE_UNKNOWN;
		}
		return networkType;
	}

	public interface NetworkStateListener {
		public void onNetworkConnect(boolean connected);
	}
}

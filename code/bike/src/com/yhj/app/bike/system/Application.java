
package com.yhj.app.bike.system;

import java.util.List;
import java.util.Locale;

import oicq.wlogin_sdk.request.WtloginHelper;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.tencent.omg.webdev.WebDevConfig;
import com.yhj.app.bike.download.DownloadConstants;
import com.yhj.app.bike.model.pojo.ChannelList;
import com.yhj.app.bike.model.pojo.ListMapData;
import com.yhj.app.bike.model.pojo.NewsVersion;
import com.yhj.app.bike.model.pojo.UserInfo;
import com.yhj.app.bike.utils.MobileUtil;
import com.yhj.app.bike.utils.SLog;

public class Application extends android.app.Application {
    private static Application instance = null;
    private InputMethodManager inputMethodManager = null;
    private float scaleDensity = 0.0f;
    private LocationManager locationManager = null;
    private Handler observableHandler = new Handler();
    private ListMapData mChannel = null;
    private ListMapData mNewsChannelSelected = null;
    private ChannelList mPhotoChannel = null;
    private ChannelList mVideoChannel = null;
    private NewsVersion mVersion = null;
    public WtloginHelper helper = null;
    public boolean isServiceRunningInSeparateProcess = false;

    public static UserInfo userInfo = null;
    public int downloadState = DownloadConstants.T_DOWNLOAD;

    private void initBossSDK() {
        try {
            ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
            List<RunningAppProcessInfo> infos = am.getRunningAppProcesses();

            for (RunningAppProcessInfo info : infos) {
                if (Process.myPid() == info.pid) {
                    if (info.processName.toUpperCase(Locale.US).contains("PUSHSERVICE")) {

                    	SLog.i("=======service=====" + info.pid + ", className=" + info.processName);
                        isServiceRunningInSeparateProcess = true;

                        WebDevConfig.setAppKey("ATXRSY245Y54");
                        WebDevConfig.setEnableConcurrentProcess(true);
                        
                    } else {
                    	
                        SLog.i("============" + info.pid + ", className=" + info.processName);

                        WebDevConfig.setAppKey("A3LFCJD983W9");
                    }
                }
            }

            WebDevConfig.setInsallChannel(MobileUtil.getFrom());
            WebDevConfig.setStatSendStrategy(WebDevConfig.BATCH);
            WebDevConfig.setMaxBatchReportCount(5);
            WebDevConfig.setMaxStoreEventCount(10000);
            WebDevConfig.setMaxParallelTimmingEvents(4096);// 不能超过4096，否则crash
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Application() {
        super();
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initBossSDK();

        CrashHandler handler = CrashHandler.getInstance();
        handler.init(getApplicationContext());
        Thread.setDefaultUncaughtExceptionHandler(handler);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        scaleDensity = MobileUtil.getDeviceDisplayMetrics(this).density;
        setNetAndSDCard();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static synchronized Application getInstance() {
        return instance;
    }

    public void runOnUIThread(Runnable r) {
        observableHandler.post(r);
    }

    public void setNetAndSDCard() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo allNetInfo = cm.getActiveNetworkInfo();

        if (allNetInfo == null) {
            if (mobileNetInfo != null && (mobileNetInfo.isConnected() || mobileNetInfo.isConnectedOrConnecting())) {
                NetStatusReceiver.netStatus = NetStatusReceiver.NETSTATUS_MOBILE;
            } else if (wifiNetInfo != null && (wifiNetInfo.isConnected() || wifiNetInfo.isConnectedOrConnecting())) {
                NetStatusReceiver.netStatus = NetStatusReceiver.NETSTATUS_WIFI;
            } else {
                NetStatusReceiver.netStatus = NetStatusReceiver.NETSTATUS_INAVAILABLE;
            }
        } else {
            if (allNetInfo.isConnected() || allNetInfo.isConnectedOrConnecting()) {
                if (mobileNetInfo != null && (mobileNetInfo.isConnected() || mobileNetInfo.isConnectedOrConnecting())) {
                    NetStatusReceiver.netStatus = NetStatusReceiver.NETSTATUS_MOBILE;
                } else {
                    NetStatusReceiver.netStatus = NetStatusReceiver.NETSTATUS_WIFI;
                }
            } else {
                NetStatusReceiver.netStatus = NetStatusReceiver.NETSTATUS_INAVAILABLE;
            }
        }

        ExternalStorageReceiver.isSDCardMounted = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());

        IntentFilter filterNet = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        NetStatusReceiver netReceiver = new NetStatusReceiver();
        this.registerReceiver(netReceiver, filterNet);

        IntentFilter filterStorage = new IntentFilter(Intent.ACTION_MEDIA_MOUNTED);
        filterStorage.addDataScheme("file");
        filterStorage.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        filterStorage.addAction(Intent.ACTION_MEDIA_REMOVED);
        filterStorage.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);
        ExternalStorageReceiver storageReceiver = new ExternalStorageReceiver();
        this.registerReceiver(storageReceiver, filterStorage);
    }

    public void hideSoftInputFromWindow(IBinder token) {// 隐藏虚拟键盘
        if (inputMethodManager == null)
            inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(token, 0);
        }
    }

    public void showSoftInput(View view) {// 显示虚拟键盘
        if (inputMethodManager == null)
            inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
    }

    public float getScaleDensity() {
        return scaleDensity;
    }

    public LocationManager getLocationManager() {
        if (locationManager == null)
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager;
    }

    public void setChannelList(ListMapData channel) {
        this.mChannel = channel;
    }

    public ListMapData getChannelList() {
        return this.mChannel;
    }

    public void setNewsChannelSelected(ListMapData channel) {
        this.mNewsChannelSelected = channel;
    }

    public ListMapData getNewsChannelSelected() {
        return this.mNewsChannelSelected;
    }

    public void setPhotoChannelList(ChannelList mPhotoChannel) {
        this.mPhotoChannel = mPhotoChannel;
    }

    public ChannelList getPhotoChannelList() {
        return this.mPhotoChannel;
    }

    public void setVideoChannelList(ChannelList mVideoChannel) {
        this.mVideoChannel = mVideoChannel;
    }

    public ChannelList getVideoChannelList() {
        return this.mVideoChannel;
    }

    public void setVersion(NewsVersion mVersion) {
        this.mVersion = mVersion;
    }

    public NewsVersion getVersion() {
        return this.mVersion;
    }

    public int getDownloadState() {
        return downloadState;
    }

    public void setDownloadState(int downloadState) {
        this.downloadState = downloadState;
    }

    @Override
    public void onLowMemory() {
        SLog.v("ApplicationActivity", "onLowMemory");
        super.onLowMemory();
    }
}

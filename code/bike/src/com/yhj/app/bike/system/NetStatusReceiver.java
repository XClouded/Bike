
package com.yhj.app.bike.system;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.yhj.app.bike.utils.SLog;

public class NetStatusReceiver extends BroadcastReceiver {
    public static final int NETSTATUS_INAVAILABLE = 0;
    public static final int NETSTATUS_WIFI = 1;
    public static final int NETSTATUS_MOBILE = 2;
    public static int netStatus = 0;

    public static boolean updateSuccess = false;

    Dialog dlg;
    Context mContext;

    public NetStatusReceiver() {

    }

    public NetStatusReceiver(Context context, Dialog d) {
        this.dlg = d;
        mContext = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Auto-generated method stub
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo allNetInfo = cm.getActiveNetworkInfo();

        if (allNetInfo == null) {
            if (mobileNetInfo != null && (mobileNetInfo.isConnected() || mobileNetInfo.isConnectedOrConnecting())) {
                netStatus = NETSTATUS_MOBILE;
            } else if (wifiNetInfo != null && wifiNetInfo.isConnected() || wifiNetInfo.isConnectedOrConnecting()) {
                netStatus = NETSTATUS_WIFI;
            } else {
                netStatus = NETSTATUS_INAVAILABLE;
            }
        } else {
            if (allNetInfo.isConnected() || allNetInfo.isConnectedOrConnecting()) {
                if (mobileNetInfo != null && (mobileNetInfo.isConnected() || mobileNetInfo.isConnectedOrConnecting())) {
                    netStatus = NETSTATUS_MOBILE;
                } else {
                    netStatus = NETSTATUS_WIFI;
                }
            } else {
                netStatus = NETSTATUS_INAVAILABLE;
            }
        }

        if (netStatus == NETSTATUS_INAVAILABLE) {
            SLog.w(SLog.BIKE, "[System]:网络未连接");
        } else if (netStatus == NETSTATUS_MOBILE) {
            SLog.d(SLog.BIKE, "[System]:网络处于移动网络");
        } else {
            SLog.d(SLog.BIKE, "[System]:网络处于Wifi网络");
        }

        if (dlg != null && context != null) {

            //ImageView network_status = (ImageView) dlg.findViewById(R.id.network_status);

        /*    if (NetStatusReceiver.netStatus == NetStatusReceiver.NETSTATUS_WIFI) {
                network_status.setVisibility(View.VISIBLE);
                network_status.setImageDrawable(context.getResources().getDrawable(R.drawable.offline_wifi_icon));
            } else if (NetStatusReceiver.netStatus == NetStatusReceiver.NETSTATUS_MOBILE) {
                network_status.setVisibility(View.VISIBLE);
                network_status.setImageDrawable(context.getResources().getDrawable(R.drawable.offline_notwifi_icon));
            } else {
                network_status.setVisibility(View.GONE);
            }*/

        }

    }
}

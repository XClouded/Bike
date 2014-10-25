
package com.yhj.app.bike.utils;

import android.content.Context;
import android.os.PowerManager;

public class WakeLockUtil {
    private static final long WakelockHoldMaxTime = 10 * 1000;
    private static PowerManager.WakeLock mServiceLock;
    private static PowerManager.WakeLock mNetworkLock;
    private static long serviceStart;
    private static long netWorkStart;

    public synchronized static void acquireServiceWakeLock(Context context) {
        try {
            if (mServiceLock == null) {
                PowerManager powerManager = (PowerManager) (context.getSystemService(Context.POWER_SERVICE));
                mServiceLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                        "com.tencent.pushServiceWakelock");
                mServiceLock.setReferenceCounted(false);
            }

            if (mServiceLock.isHeld()) {
                return;
            }

            SLog.push("acquireServiceWakeLock", "acquireServiceWakeLock");
            serviceStart = System.currentTimeMillis();
            mServiceLock.acquire(WakelockHoldMaxTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static void releaseServiceWakeLock() {
        try {
            if (mServiceLock != null && mServiceLock.isHeld()) {
                long now = System.currentTimeMillis();
                SLog.push("releaseServiceWakeLock finished: " + (now - serviceStart),
                        "releaseServiceWakeLock");
                mServiceLock.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static void acquireNetWakeLock(Context context) {
        try {
            if (mNetworkLock == null) {
                PowerManager powerManager = (PowerManager) (context.getSystemService(Context.POWER_SERVICE));
                mNetworkLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "com.tencent.networkWakelock");
                mNetworkLock.setReferenceCounted(false);
            }

            if (mNetworkLock.isHeld()) {
                return;
            }

            SLog.push("acquireNetWakeLock", "acquireNetWakeLock");
            netWorkStart = System.currentTimeMillis();
            mNetworkLock.acquire(WakelockHoldMaxTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static void releaseNetWakeLock() {
        try {
            if (mNetworkLock != null && mNetworkLock.isHeld()) {
                long now = System.currentTimeMillis();
                SLog.push("releaseNetWakeLock finished: " + (now - netWorkStart), "releaseNetWakeLock");
                mNetworkLock.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.yhj.app.bike.utils;

import android.content.Intent;

import com.yhj.app.bike.config.Constants;

public class IntentUtil {
    public static String getReadBroadcastAction(Intent intent) {
        String action = null;
        if (intent != null) {
            action = intent.getStringExtra(Constants.NEWS_ITEM_READ_BROADCAST);
            if (action != null && action.length() == 0) {
                action = null;
            }
        }
        return action;
    }
    
}


package com.yhj.app.bike.shareprefrence;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.system.Application;

public class SpNewsHadRead {

    /**
     * 保存id新闻已读
     * 
     * @param id
     */
    public static void saveNewsHadRead(String id) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_NEWS_HAD_READ,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(id, true);
        editor.commit();
    }

    /**
     * @param id
     * @return false该id的新闻未读,true表示该id的新闻已读
     */
    public static boolean isNewsHadRead(String id) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_NEWS_HAD_READ,
                Context.MODE_PRIVATE);
        return sp.getBoolean(id, false);
    }

    public static void delNewsHadRead(String id) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_NEWS_HAD_READ,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.remove(id);
        editor.commit();
    }

    public static void delNewsHadRead(List<String> delIds) {
        if (delIds != null && delIds.size() > 0) {
            SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_NEWS_HAD_READ,
                    Context.MODE_PRIVATE);
            Editor editor = sp.edit();
            for (String id : delIds) {
                editor.remove(id);
            }
            editor.commit();
        }
    }

    public static void delNewsHadRead(String[] delIds) {
        if (delIds != null && delIds.length > 0) {
            SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_NEWS_HAD_READ,
                    Context.MODE_PRIVATE);
            Editor editor = sp.edit();
            for (String id : delIds) {
                editor.remove(id);
            }
            editor.commit();
        }
    }

    public static void delAll() {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_NEWS_HAD_READ,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

}

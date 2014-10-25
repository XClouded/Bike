package com.yhj.app.bike.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

final public class JSON {
	
    private JSON() {
        // should never be instantiated
        throw new AssertionError();
    }

    private static ThreadLocal<Map<String, SimpleDateFormat>> formatMap = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        @Override
        protected Map<String, SimpleDateFormat> initialValue() {
            return new HashMap<String, SimpleDateFormat>();
        }
    };

    public static String getRawString(String name, JSONObject json){
        try {
            if (!json.has(name) || json.isNull(name)) {
                return null;
            } else {
                return json.getString(name);
            }
        } catch (JSONException jsone) {
			jsone.printStackTrace();
            return null;
        }
    }

    public static Date getDate(String name, String format)
            throws Exception {
        SimpleDateFormat sdf = formatMap.get().get(format);
        if (null == sdf) {
            sdf = new SimpleDateFormat(format, Locale.ENGLISH);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            formatMap.get().put(format, sdf);
        }
        try {
            return sdf.parse(name);
        } catch (ParseException pe) {
            throw new Exception("Unexpected date format(" + name
                    + ") returned from twitter.com", pe);
        }
    }

    public static int getInt(String name, JSONObject elem) {
        String str2 = getRawString(name, elem);
        if (null == str2 || "".equals(str2) || "null".equals(str2)) {
            return -1;
        } else {
            return Integer.valueOf(str2);
        }
    }

    public static long getLong(String name, JSONObject json) {
        String str2 = getRawString(name, json);
        if (null == str2 || "".equals(str2) || "null".equals(str2)) {
            return -1;
        } else {
            return Long.valueOf(str2);
        }
    }

    public static double getDouble(String name, JSONObject json) {
        String str2 = getRawString(name, json);
        if (null == str2 || "".equals(str2) || "null".equals(str2)) {
            return -1;
        } else {
            return Double.valueOf(str2);
        }
    }

    public static boolean getBoolean(String name, JSONObject json) {
        String str = getRawString(name, json);
        if (null == str || "null".equals(str)) {
            return false;
        }
        return Boolean.valueOf(str);
    }

    public static JSONObject asJSONObject(String res) throws Exception {
        JSONObject ret = null;
        try {
            ret = new JSONObject(new JSONTokener(res));
        } catch (JSONException jsone) {
            throw new Exception(jsone.getMessage(), jsone);
        }
        return ret;
    }

    public static JSONArray asJSONArray(String res) throws Exception {
        JSONArray ret = null;
        try {
            ret = new JSONArray(new JSONTokener(res));
        } catch (JSONException jsone) {
            throw new Exception(jsone.getMessage(), jsone);
        }
        return ret;
    }
}

package com.yhj.app.bike.cache;

import android.content.ContentValues;
import android.database.Cursor;

public class FavorDbItem {
    public static final int OP_SYNCED = 0;
    public static final int OP_ADD = 1;
    public static final int OP_DEL = 2;
    //public static final int OP_OLD = 3;//过期
    
    public static final String CLUM_ID = "_id";
    public static final String CLUM_NEWS_ID = "news_id";
    public static final String CLUM_USER_ID = "user_id";
    public static final String CLUM_SOURCE = "source";
    public static final String CLUM_TIMESTAMP = "timestamp";
    public static final String CLUM_LIST_ITEM = "list_item";
    public static final String CLUM_OPERATION = "operation";
    public static final String CLUM_EXTEND = "extend";
    
    public int _id;
    public String news_id;
    public String user_id;
    public int source;
    public long timestamp;
//    public int list_item;
    public int operation;
//    public int extend;
    
    public FavorDbItem() {
    }
    
    public FavorDbItem(Cursor cursor) {
        //TODO sync
        this._id = cursor.getInt(0);
        this.news_id = cursor.getString(1);
        this.user_id = cursor.getString(2);
        this.source = cursor.getInt(3);
        this.timestamp = cursor.getLong(4);
        // this.list_item = cursor.getInt(5);
        this.operation = cursor.getInt(6);
//        this.extend = cursor.getInt(7);
    }

    public ContentValues getContentValues() {
        //TODO sync
        ContentValues cv = new ContentValues();
        cv.put(CLUM_NEWS_ID, this.news_id);
        cv.put(CLUM_USER_ID, this.user_id);
        cv.put(CLUM_SOURCE, this.source);
        cv.put(CLUM_TIMESTAMP, this.timestamp);
//        cv.put(CLUM_LIST_ITEM, this.list_item);
        cv.put(CLUM_OPERATION, this.operation);
//        cv.put(CLUM_EXTEND, this.extend);
        return cv;
    }
}

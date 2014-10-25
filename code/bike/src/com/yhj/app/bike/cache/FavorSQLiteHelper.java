
package com.yhj.app.bike.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yhj.app.bike.model.pojo.Item;
import com.yhj.app.bike.system.Application;
import com.yhj.app.bike.utils.SLog;

public class FavorSQLiteHelper extends SQLiteOpenHelper {

    private SQLiteDatabase datebase = null;

    private final static String DATABASE_NAME = "favor_sync.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "favor_log";
    private static final String[] ALL_COLLUMNS = new String[] {
            FavorDbItem.CLUM_ID, FavorDbItem.CLUM_NEWS_ID, FavorDbItem.CLUM_USER_ID,
            FavorDbItem.CLUM_SOURCE, FavorDbItem.CLUM_TIMESTAMP, FavorDbItem.CLUM_LIST_ITEM,
            FavorDbItem.CLUM_OPERATION, FavorDbItem.CLUM_EXTEND
    };

    public FavorSQLiteHelper() {
        super(Application.getInstance().getApplicationContext(), DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public boolean isOpen() {
        return datebase != null && datebase.isOpen();
    }

    public void open() {
        datebase = getWritableDatabase();
    }
    
    public void beginTransaction(){
        datebase.beginTransaction();
    }
    
    public void setTransactionSuccessful(){
        datebase.setTransactionSuccessful();
    }
    
    public void endTransaction(){
        datebase.endTransaction();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS '" + TABLE_NAME + "' ('" + FavorDbItem.CLUM_ID
                + "' INTEGER PRIMARY KEY AUTOINCREMENT," + "'" + FavorDbItem.CLUM_NEWS_ID
                + "' TEXT DEFAULT ''," + "'" + FavorDbItem.CLUM_USER_ID + "' TEXT DEFAULT '0',"
                + "'" + FavorDbItem.CLUM_SOURCE + "' INTEGER NOT NULL," + "'"
                + FavorDbItem.CLUM_TIMESTAMP + "' INTEGER," + "'" + FavorDbItem.CLUM_LIST_ITEM
                + "' BLOB," + "'" + FavorDbItem.CLUM_OPERATION + "' INTEGER NOT NULL," + "'"
                + FavorDbItem.CLUM_EXTEND + "' BLOB);");
        
        db.execSQL("CREATE TABLE IF NOT EXISTS '" + ITEM_CACHE_TABLE_NAME + "' ('" + FavorCacheItem.CLUM_ID
                + "' INTEGER PRIMARY KEY AUTOINCREMENT," + "'" + FavorCacheItem.CLUM_NEWS_ID
                + "' TEXT DEFAULT ''," + "'" + FavorCacheItem.CLUM_ITEM + "' BLOB);"
                + "CREATE INDEX Idx_"+ FavorCacheItem.CLUM_NEWS_ID+" on '" + ITEM_CACHE_TABLE_NAME + "' ('"+ FavorCacheItem.CLUM_NEWS_ID+"');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_CACHE_TABLE_NAME + ";");
        db.execSQL("DROP INDEX IF EXISTS Idx_"+ FavorCacheItem.CLUM_NEWS_ID+";");
        onCreate(db);
    }

    public boolean insert(FavorDbItem item) {
        boolean success = false;
        datebase.beginTransaction();
        try {
            datebase.insert(TABLE_NAME, null, item.getContentValues());
            datebase.setTransactionSuccessful();
            success = true;
        } finally {
            datebase.endTransaction();
        }
        return success;
    }
    
    public long insert2(FavorDbItem item){
        long success = -1;
        success = datebase.insert(TABLE_NAME, null, item.getContentValues());
        return success;
    }

    public boolean update(FavorDbItem item) {
        boolean success = false;
        datebase.beginTransaction();
        try {
            datebase.update(TABLE_NAME, item.getContentValues(), FavorDbItem.CLUM_ID + "=?",
                    new String[] {
                        String.valueOf(item._id)
                    });
            datebase.setTransactionSuccessful();
            success = true;
        } finally {
            datebase.endTransaction();
        }
        return success;
    }
    
    /**
     * 一次更新多条记录
     * @param upList
     * @return
     */
    public boolean updates(List<FavorDbItem> upList){

        boolean success = false;
        if(upList==null || upList.size()==0){
        	return success;
        }
        datebase.beginTransaction();
        try {
        	for (FavorDbItem item : upList) {
        		datebase.update(TABLE_NAME, item.getContentValues(), FavorDbItem.CLUM_ID + "=?",
                    new String[] {
                        String.valueOf(item._id)
                    });
        	}
            datebase.setTransactionSuccessful();
            success = true;
        } catch (Exception e) {
        	SLog.e("FavorSQLiteHelper", e.getMessage());
        } finally {
            datebase.endTransaction();
        }
        return success;
    }
    
    /**
     * 更新指定帐号下多条新闻的操作状态
     * @param newsIdList 新闻id列表
     * @param op_type 更改后的操作状态
     * @param account 帐号
     * @return
     */
    public boolean updates(List<String> newsIdList, int op_type, String account){
    	boolean success = false;
        if(newsIdList==null || newsIdList.size()==0){
        	return success;
        }
        if(op_type!=FavorDbItem.OP_DEL && op_type!=FavorDbItem.OP_ADD && op_type!=FavorDbItem.OP_SYNCED){
        	return success;
        }
        datebase.beginTransaction();
        try {
        	ContentValues cv = new ContentValues();
            cv.put(FavorDbItem.CLUM_OPERATION, op_type);
            
        	for (String news_id : newsIdList) {
        		datebase.update(TABLE_NAME, cv, FavorDbItem.CLUM_USER_ID + "=? AND " + 
        	            FavorDbItem.CLUM_NEWS_ID + "=?",
                    new String[] {
        				account,
                        String.valueOf(news_id)
                    });
        	}
            datebase.setTransactionSuccessful();
            success = true;
        } catch (Exception e) {
        	SLog.e("FavorSQLiteHelper", e.getMessage());
        } finally {
            datebase.endTransaction();
        }
        return success;
    }
    
    public int delete2(int OpType, String account) {
        int success = 0;
        datebase.beginTransaction();
        try {
            success = datebase.delete(TABLE_NAME, 
                    FavorDbItem.CLUM_USER_ID + "=? AND " + 
            FavorDbItem.CLUM_OPERATION + " = " + OpType, new String[]{account});
            datebase.setTransactionSuccessful();
        } finally {
            datebase.endTransaction();
        }
        return success;
    }
    
    public int delete(String newsId, String account){
        int success = 0;
        datebase.beginTransaction();
        try {
            success = datebase.delete(TABLE_NAME, 
                    FavorDbItem.CLUM_USER_ID + "=? AND " + 
            FavorDbItem.CLUM_NEWS_ID + "=?", new String[]{account,newsId});
            datebase.setTransactionSuccessful();
        } finally {
            datebase.endTransaction();
        }
        return success;
    }
    
    /**
     * 一次删除多条记录
     * @param delList 记录id列表
     * @param account 用户帐号
     * @return
     */
    public int deletes(List<String> delList, String account){
        int success = 0;
        if(delList==null || delList.size()==0){
        	return success;
        }
        datebase.beginTransaction();
        try {
        	for (String newsId : delList) {        		
        		int ret_int = datebase.delete(TABLE_NAME, 
                    	FavorDbItem.CLUM_USER_ID + "=? AND " + 
                    		FavorDbItem.CLUM_NEWS_ID + "=?", new String[]{account,newsId});
        		success =success +ret_int;
        	}
            datebase.setTransactionSuccessful();
        } catch (Exception e) {
        	SLog.e("FavorSQLiteHelper", e.getMessage());
        } finally {
            datebase.endTransaction();
        }
        return success;
    }

    public int updateDefaultUserAccount(String from, String to) {
        int count = 0;
        datebase.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put("user_id", to);
            count = datebase.update(TABLE_NAME, cv, FavorDbItem.CLUM_USER_ID + "=?", new String[] {
                from
            });
            datebase.setTransactionSuccessful();
        } finally {
            datebase.endTransaction();
        }
        return count;
    }

    public FavorDbItem findFavorItem(String newsId, String account) {
        FavorDbItem item = null;
        Cursor cursor = null;
        try {
            cursor = datebase.query(TABLE_NAME, ALL_COLLUMNS, FavorDbItem.CLUM_NEWS_ID
                    + "=? AND " + FavorDbItem.CLUM_USER_ID + "=?", new String[] {
                    newsId, account
            }, null, null, null);
            if (cursor.moveToFirst()) {
                item = new FavorDbItem(cursor);
            }
        } catch (Exception e) {
            String selectString = "SELECT FavorDbItem FROM " + TABLE_NAME + " WHERE "
                    + FavorDbItem.CLUM_NEWS_ID + "='" + newsId + "' AND "
                    + FavorDbItem.CLUM_USER_ID + "='" + account + "'";
            Log.e("sync", "query " + selectString + " failed because: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        return item;
    }
    
    /**
     * 获取需要同步的items. 每次增删最多各20个
     * @param account
     * @return
     */
    public ArrayList<FavorDbItem> getFavorItemsForSync(String account) {
        ArrayList<FavorDbItem> list = new ArrayList<FavorDbItem>();
        Cursor cursor = null;
        try {
            cursor = datebase.query(TABLE_NAME, ALL_COLLUMNS, FavorDbItem.CLUM_USER_ID
                    + "=? AND " + FavorDbItem.CLUM_OPERATION + "=?", new String[] {
                    account, String.valueOf(FavorDbItem.OP_ADD)
            }, null, null, null, "20");
            while (cursor.moveToNext()) {
                FavorDbItem item = new FavorDbItem(cursor);
                list.add(item);
            }
        } catch (Exception e) {
            Log.e("sync", "getFavorItemsForSync failed because: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }

        try {
            cursor = datebase.query(TABLE_NAME, ALL_COLLUMNS, FavorDbItem.CLUM_USER_ID
                    + "=? AND " + FavorDbItem.CLUM_OPERATION + "=?", new String[] {
                    account, String.valueOf(FavorDbItem.OP_DEL)
            }, null, null, null, "20");
            while (cursor.moveToNext()) {
                FavorDbItem item = new FavorDbItem(cursor);
                list.add(item);
            }
        } catch (Exception e) {
            Log.e("sync", "getFavorItemsForSync failed because: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        
        return list;
    }

    public ArrayList<FavorDbItem> getNotSyncFavorItems(String account) {
        ArrayList<FavorDbItem> list = new ArrayList<FavorDbItem>();
        Cursor cursor = null;
        try {
            cursor = datebase.query(TABLE_NAME, ALL_COLLUMNS, FavorDbItem.CLUM_USER_ID
                    + "=? AND " + FavorDbItem.CLUM_OPERATION + "!=?", new String[] {
                    account, String.valueOf(FavorDbItem.OP_SYNCED)
            }, null, null, null, null);
            while (cursor.moveToNext()) {
                FavorDbItem item = new FavorDbItem(cursor);
                list.add(item);
            }
        } catch (Exception e) {
            Log.e("sync", "getNotSyncFavorItems failed because: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        
        return list;
    }
    
    /**
     * 获取本地收藏的FavorDbItem
     * @param account
     * @return
     */
    public ArrayList<FavorDbItem> getFavorItemsCache(String account) {
        ArrayList<FavorDbItem> list = new ArrayList<FavorDbItem>();
        HashMap<String,FavorDbItem> idHash = new  HashMap<String,FavorDbItem>();
        List<String> delList= new ArrayList<String>();
        Cursor cursor = null;
        try {
            cursor = datebase.query(TABLE_NAME, ALL_COLLUMNS, FavorDbItem.CLUM_USER_ID
                    + "=? AND " + FavorDbItem.CLUM_OPERATION + "!=?", new String[] {
                    account, String.valueOf(FavorDbItem.OP_DEL)
            }, null, null, FavorDbItem.CLUM_TIMESTAMP+" desc");
            while (cursor.moveToNext()) {
                FavorDbItem item = new FavorDbItem(cursor);
                if(idHash.containsKey(item.news_id)){
                	if(item.operation!=FavorDbItem.OP_SYNCED){
                		list.remove(idHash.get(item.news_id));
                		delList.add(String.valueOf(idHash.get(item.news_id)._id));
                		list.add(item);
                		idHash.put(item.news_id, item);
                	}else{
                		delList.add(String.valueOf(item._id));
                	}
                }else{
                	idHash.put(item.news_id, item);
                	list.add(item);
                }
            }
        } catch (Exception e) {
            Log.e("sync", "getFavorItemsForSync failed because: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        deletes(delList, account);
		delList.clear();
		idHash.clear();
        return list;
    }

    /**
     * 获取本地收藏的所有状态的FavorDbItem
     * @param account
     * @return
     */
    public ArrayList<FavorDbItem> getFavorItemsCacheAllStatus(String account) {
        ArrayList<FavorDbItem> list = new ArrayList<FavorDbItem>();
        Cursor cursor = null;
        try {
            cursor = datebase.query(TABLE_NAME, ALL_COLLUMNS, FavorDbItem.CLUM_USER_ID
                    + "=?", new String[] {
                    account
            }, null, null, FavorDbItem.CLUM_TIMESTAMP+" desc");
            while (cursor.moveToNext()) {
                FavorDbItem item = new FavorDbItem(cursor);
                list.add(item);
            }
        } catch (Exception e) {
            Log.e("sync", "getFavorItemsForSync failed because: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        return list;
    }
    
    //------------把item内容存入数据库中
    private static final String ITEM_CACHE_TABLE_NAME = "favor_item";
    private static final String[] ITEM_CACHE_TABLE_ALL_COLLUMNS = new String[] {
    	FavorCacheItem.CLUM_ID, FavorCacheItem.CLUM_NEWS_ID, FavorCacheItem.CLUM_ITEM
    };
    
    /**
     * 增加item信息
     * @param item
     * @return
     */
    public boolean insertItem(String newsId, Item cacheItem) {
        boolean success = false;
        FavorCacheItem item = new FavorCacheItem();
        Cursor cursor = null;
        item.news_id=newsId;
        item.news_item=cacheItem;
        datebase.beginTransaction();
        try {
        	cursor = datebase.query(ITEM_CACHE_TABLE_NAME, new String[] {FavorCacheItem.CLUM_ID}, FavorCacheItem.CLUM_NEWS_ID
                    + "=?", new String[] {
                    newsId
            }, null, null, null);
        	
            if (cursor.moveToFirst()) {//如果已有就更新
            	datebase.update(ITEM_CACHE_TABLE_NAME, item.getContentValues(), FavorCacheItem.CLUM_NEWS_ID + "=?",
                        new String[] {
            		newsId
                        });
            }else{//否则添加
            	datebase.insert(ITEM_CACHE_TABLE_NAME, null, item.getContentValues());
            }
            datebase.setTransactionSuccessful();
            success = true;
        } finally {
            datebase.endTransaction();
        }
        return success;
    }
    
    /**
     * 更新item信息
     * @param item
     * @return
     */
    public boolean updateItem(FavorCacheItem item) {
        boolean success = false;
        datebase.beginTransaction();
        try {
            datebase.update(ITEM_CACHE_TABLE_NAME, item.getContentValues(), FavorCacheItem.CLUM_ID + "=?",
                    new String[] {
                        String.valueOf(item._id)
                    });
            datebase.setTransactionSuccessful();
            success = true;
        } finally {
            datebase.endTransaction();
        }
        return success;
    }
    
    public int deleteItem(String newsId){
    	int success = 0;
        datebase.beginTransaction();
        try {
            success = datebase.delete(ITEM_CACHE_TABLE_NAME, 
            		FavorCacheItem.CLUM_NEWS_ID
                    + "=?", new String[] {
                    newsId
            });
            datebase.setTransactionSuccessful();

        } catch (Exception e) {
            Log.e("sync", "deleteItem failed because: " + e.getMessage());
        } finally {
            datebase.endTransaction();
        }
        return success;
    }
    
    /**
     * 删除所有item信息
     * @return
     */
    public int deleteItemAll(){
        int success = 0;
        datebase.beginTransaction();
        try {
            success = datebase.delete(ITEM_CACHE_TABLE_NAME, 
                    null, null);
            datebase.setTransactionSuccessful();
        } finally {
            datebase.endTransaction();
        }
        return success;
    }
    
    public boolean hasFavorCacheItem(String newsId) {
    	//FavorCacheItem item = null;
        Cursor cursor = null;
        boolean success=false;
        try {
        	cursor = datebase.query(ITEM_CACHE_TABLE_NAME, new String[] {FavorCacheItem.CLUM_ID}, FavorCacheItem.CLUM_NEWS_ID
                    + "=?", new String[] {
                    newsId
            }, null, null, null);
        	
            if (cursor.moveToFirst()) {//如果已有就更新
            	success=true;
            }
        } catch (Exception e) {
            String selectString = "SELECT FavorCacheItem FROM " + ITEM_CACHE_TABLE_NAME + " WHERE "
                    + FavorDbItem.CLUM_NEWS_ID + "='" + newsId + "'";
            Log.e("sync", "query " + selectString + " failed because: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        return success;
    }
    
    /**
     * 根据新闻id查询item信息
     * @param newsId
     * @return
     */
    public Item findFavorCacheItem(String newsId) {
    	FavorCacheItem item = null;
        Cursor cursor = null;
        try {
            cursor = datebase.query(ITEM_CACHE_TABLE_NAME, ITEM_CACHE_TABLE_ALL_COLLUMNS, FavorCacheItem.CLUM_NEWS_ID
                    + "=?", new String[] {
                    newsId
            }, null, null, null);
            if (cursor.moveToFirst()) {
                item = new FavorCacheItem(cursor);
            }
        } catch (Exception e) {
            String selectString = "SELECT FavorCacheItem FROM " + ITEM_CACHE_TABLE_NAME + " WHERE "
                    + FavorDbItem.CLUM_NEWS_ID + "='" + newsId + "'";
            Log.e("sync", "query " + selectString + " failed because: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        if(item!=null){
        	return item.news_item;
        }else{
        	return null;
        }
    }

    /**
     * 获取本地收藏的所有FavorCacheItem
     * @return
     */
    public ArrayList<FavorCacheItem> findAllFavorCacheItem() {
        ArrayList<FavorCacheItem> list = new ArrayList<FavorCacheItem>();
        Cursor cursor = null;
        try {
            cursor = datebase.query(ITEM_CACHE_TABLE_NAME, ITEM_CACHE_TABLE_ALL_COLLUMNS, null, null, null, null, null);
            while (cursor.moveToNext()) {
                FavorCacheItem item = new FavorCacheItem(cursor);
                list.add(item);
            }
        } catch (Exception e) {
            Log.e("sync", "findAllFavorCacheItem failed because: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        return list;
    }

    /**
     * 获取本地收藏的所有FavorCacheItem
     * @return
     */
//    public ArrayList<FavorCacheItem> findFavorCacheItem(List<String> newIdList) {
//        ArrayList<FavorCacheItem> list = new ArrayList<FavorCacheItem>();
//        if(newIdList==null || newIdList.size()==0){
//        	return list;
//        }
//        String idString="";
//        for (String newsId : newIdList) {
//        	if(!(newsId.indexOf("'")!=-1 || newsId.indexOf("\"")!=-1)){
//        		idString+="'"+newsId+"'";
//        	}
//        }
//        if(idString==null || idString.length()==0){
//        	return list;
//        }
//        Cursor cursor = null;
//        try {
//            cursor = datebase.query(ITEM_CACHE_TABLE_NAME, ITEM_CACHE_TABLE_ALL_COLLUMNS, FavorDbItem.CLUM_USER_ID
//                    + "in ("+idString+")", null, null, null, null);
//            while (cursor.moveToNext()) {
//                FavorCacheItem item = new FavorCacheItem(cursor);
//                list.add(item);
//            }
//        } catch (Exception e) {
//            Log.e("sync", "findAllFavorCacheItem failed because: " + e.getMessage());
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//                cursor = null;
//            }
//        }
//        return list;
//    }
    
    /**
     * 收藏列表页用到的item缓存结构
     * @author brookeli
     *
     */
    public class FavorCacheItem {
        public static final String CLUM_ID = "_id";
        public static final String CLUM_NEWS_ID = "news_id";
        public static final String CLUM_ITEM = "news_item";

        public int _id;
        public String news_id;
        public Item news_item;
        
        public FavorCacheItem() {
        }
        
        public FavorCacheItem(Cursor cursor) {
            //TODO sync
            this._id = cursor.getInt(0);
            this.news_id = cursor.getString(1);
            //this.news_item = cursor.getString(2);
            
            ObjectInputStream objectIn = null;
            try {
            	objectIn = new ObjectInputStream(  new ByteArrayInputStream(  cursor.getBlob(2)));
            	this.news_item = (Item) objectIn.readObject(); 
            }catch (Exception ex) {
            	ex.printStackTrace();
            } finally {
            	if (null != objectIn) {
            		try {
            			objectIn.close();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                }
            }
        }

        public ContentValues getContentValues() {
            //TODO sync
            ContentValues cv = new ContentValues();
            cv.put(CLUM_NEWS_ID, this.news_id);
            //cv.put(CLUM_ITEM, this.news_item);
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream out = null;
            try {
            	out = new ObjectOutputStream(baos);
            	out.writeObject(this.news_item);
            	byte[] bytes = baos.toByteArray();
            	cv.put(CLUM_ITEM, bytes);
    		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
    			if (out != null) {
    				try {
    					 out.close();
    			         baos.close();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    		}
            
            return cv;
        }
    }
    //----上面功能:把item内容存入数据库中
}

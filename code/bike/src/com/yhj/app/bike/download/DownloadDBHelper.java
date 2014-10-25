package com.yhj.app.bike.download;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yhj.app.bike.system.Application;

/**
 * 断点续传所需数据库表
 * @author vincesun
 */
public class DownloadDBHelper extends SQLiteOpenHelper {
	private static final String TABLE_DOWNLOAD_INFO = "app_download_info";
	public static final String DOWNLOAD_DB_BASE_NAME = "app_download"; // db名字
	public static final int DOWNLOAD_DB_VERSION = 12; // db版本
	public static final String ID_COL = "_id";
	public static final String THREAD_ID_COL = "thread_id";// 线程id
	public static final String START_POS_COL = "start_pos";// 线程开始位置
	public static final String END_POS_COL = "end_pos";// 线程结束位置
	public static final String COMPELETE_SIZE_COL = "compelete_size";// 该线程块下载完成大小
	public static final String DOWNLOAD_URL_COL = "download_url";// 下载url
	public static final String APPID_COL = "appid";// 应用的appid
	public static final String PACKAGE_COL = "package";// 应用的包名
	public static final String PHONY_PERCENT_COL = "phony_percent";// 应用的包名
	public static final String NETWORK_TYPE_COL = "network_type";
	public static final String TITLE_COL = "push_title";
	public static final String CONTENT_COL = "push_content";
	public static final String TYPE_COL = "push_type";
	public static final String ICON_COL = "push_icon";
	public static final String SENDTIME_COL = "sendtime_col";
	public static final String UIN_COL = "uin_col";

	public static final String[] DOWNLOAD_COLUMS = { ID_COL, THREAD_ID_COL,
			START_POS_COL, END_POS_COL, COMPELETE_SIZE_COL, DOWNLOAD_URL_COL,
			APPID_COL, PACKAGE_COL, PHONY_PERCENT_COL, NETWORK_TYPE_COL,
			TITLE_COL, CONTENT_COL, TYPE_COL, ICON_COL, SENDTIME_COL, UIN_COL };
	public static final int _ID_INDEX = 0;
	public static final int THREAD_ID_INDEX = 1;
	public static final int START_POS_INDEX = 2;
	public static final int END_POS_LEVEL_INDEX = 3;
	public static final int COMPELETE_SIZE_INDEX = 4;
	public static final int DOWNLOAD_URL_INDEX = 5;
	public static final int APPID_INDEX = 6;
	public static final int PACKAGE_INDEX = 7;
	public static final int PHONY_PERCENT_INDEX = 8;
	public static final int NETWORK_TYPE_INDEX = 9;
	public static final int TITLE_INDEX = 10;
	public static final int CONTENT_INDEX = 11;
	public static final int TYPE_INDEX = 12;
	public static final int ICON_INDEX = 13;
	public static final int SENDTIME_INDEX = 14;
	public static final int UIN_INDEX = 15;

	private final static byte synTemp[] = new byte[1];
	private String uin = "";

	private static DownloadDBHelper instance;

	public static DownloadDBHelper getInstance() {
		if (instance == null) {
			instance = new DownloadDBHelper(Application.getInstance().getApplicationContext());
		}
		return instance;
	}

	private DownloadDBHelper(Context context) {
		super(context, DOWNLOAD_DB_BASE_NAME, null, DOWNLOAD_DB_VERSION);
	}

	/**
	 * 在download.db数据库下创建一个download_info表存储下载信息
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_DOWNLOAD_INFO + " (" + ID_COL
				+ " INTEGER PRIMARY KEY," + THREAD_ID_COL + " INTEGER,"
				+ START_POS_COL + " INTEGER," + END_POS_COL + " INTEGER,"
				+ COMPELETE_SIZE_COL + " INTEGER," + DOWNLOAD_URL_COL
				+ " TEXT," + APPID_COL + " TEXT," + PACKAGE_COL + " TEXT" + ","
				+ PHONY_PERCENT_COL + " TEXT," + NETWORK_TYPE_COL + " INTEGER,"
				+ TITLE_COL + " TEXT," + CONTENT_COL + " TEXT," + TYPE_COL
				+ " INTEGER," + ICON_COL + " TEXT," + SENDTIME_COL + " TEXT,"
				+ UIN_COL + " TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion != newVersion) { // 直接放弃旧表..
			String clearrTableSQL1 = "DROP TABLE IF EXISTS "
					+ TABLE_DOWNLOAD_INFO + ";";
			db.beginTransaction();
			db.execSQL(clearrTableSQL1);
			db.setTransactionSuccessful();
			db.endTransaction();
			onCreate(db);
		}
	}

	/**
	 * 删除数据库中关于appid的数据
	 */
	public void deleteInfoByAppId(String appid) {
		synchronized (synTemp) {
			SQLiteDatabase db = getWritableDatabase();
			try {
				db.delete(TABLE_DOWNLOAD_INFO, APPID_COL + " = ? and "
						+ UIN_COL + " = ?", new String[] { appid, uin });

			} catch (Exception e) {

			} finally {
			}
			db.close();
		}
	}

	/**
	 * 更新数据库中线程块下载信息
	 */
	public void updataPieceInfoByAppId(int threadId, long compeleteSize,
			String appId, String sendTimes) {
		synchronized (synTemp) {
			SQLiteDatabase db = getWritableDatabase();
			try {

				ContentValues cv = new ContentValues();
				cv.put(COMPELETE_SIZE_COL, compeleteSize);
				cv.put(SENDTIME_COL, sendTimes);
				db.update(TABLE_DOWNLOAD_INFO, cv, APPID_COL + " = ? and "
						+ THREAD_ID_COL + " = ? and " + UIN_COL + " = ?",
						new String[] { appId, "" + threadId, uin });
			} catch (Exception e) {
				
			} finally {
			}
			db.close();
		}
	}

	public void updataPieceInfoByAppId(DownloadDataInfo info) {
		synchronized (synTemp) {
			SQLiteDatabase db = null;
			try {
				db = getWritableDatabase();
				ContentValues cv = new ContentValues();
				cv.put(START_POS_COL, info.getStartPos());
				cv.put(END_POS_COL, info.getEndPos());
				cv.put(COMPELETE_SIZE_COL, info.getCompeleteSize());

				db.update(TABLE_DOWNLOAD_INFO, cv, APPID_COL + " = ? and "
						+ THREAD_ID_COL + " = ? and " + UIN_COL + " = ?",
						new String[] { info.getAppId(),
								"" + info.getThreadId(), uin });
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (db != null)
					db.close();
			}

		}
	}

	/**
	 * 更新假进度
	 */
	public void updatePhonyPercent(String appid, String percent) {
		synchronized (synTemp) {
			SQLiteDatabase db = null;
			try {

				db = getWritableDatabase();
				// db.beginTransaction();
				ContentValues cv = new ContentValues();
				cv.put(PHONY_PERCENT_COL, percent);
				db.update(TABLE_DOWNLOAD_INFO, cv, APPID_COL + " = ? and "
						+ UIN_COL + " = ?", new String[] { appid, uin });
				// db.setTransactionSuccessful();
			} catch (Exception e) {

			} finally {
				// db.endTransaction();
				if (db != null)
					db.close();
			}

		}
	}

	/**
	 * 得到同个应用appid的所有线程块信息
	 */
	public List<DownloadDataInfo> getInfosByAppId(String appId) {
		List<DownloadDataInfo> list = new ArrayList<DownloadDataInfo>();
		synchronized (synTemp) {
			SQLiteDatabase db = getReadableDatabase();
			Cursor cursor = db.query(TABLE_DOWNLOAD_INFO, DOWNLOAD_COLUMS,
					APPID_COL + " = ? and " + UIN_COL + " = ? ", new String[] {
							appId, uin }, null, null, null);
			try {
				if (cursor.getCount() > 0) {
					cursor.moveToFirst();
					do {
						DownloadDataInfo info = new DownloadDataInfo(
								cursor.getInt(THREAD_ID_INDEX),
								cursor.getLong(START_POS_INDEX),
								cursor.getLong(END_POS_LEVEL_INDEX),
								cursor.getLong(COMPELETE_SIZE_INDEX),
								cursor.getString(DOWNLOAD_URL_INDEX),
								cursor.getString(APPID_INDEX),
								cursor.getString(PACKAGE_INDEX),
								cursor.getString(PHONY_PERCENT_INDEX),
								cursor.getInt(NETWORK_TYPE_INDEX),
								cursor.getString(TITLE_INDEX),
								cursor.getString(CONTENT_INDEX),
								cursor.getInt(TYPE_INDEX),
								cursor.getString(ICON_INDEX),
								cursor.getString(SENDTIME_INDEX));

						list.add(info);
					} while (cursor.moveToNext());
				}
			} catch (Exception e) {

			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
			db.close();
		}
		return list;
	}

	/**
	 * 得到同个应用包名的所有线程块信息
	 */
	public List<DownloadDataInfo> getInfosByAppPackageName(String packageName) {
		List<DownloadDataInfo> list = new ArrayList<DownloadDataInfo>();
		synchronized (synTemp) {
			SQLiteDatabase db = getReadableDatabase();
			Cursor cursor = db.query(TABLE_DOWNLOAD_INFO, DOWNLOAD_COLUMS,
					PACKAGE_COL + " = ? and " + UIN_COL + " = ? ",
					new String[] { packageName, uin }, null, null, null);
			try {
				if (cursor.getCount() > 0) {
					cursor.moveToFirst();
					do {
						DownloadDataInfo info = new DownloadDataInfo(
								cursor.getInt(THREAD_ID_INDEX),
								cursor.getLong(START_POS_INDEX),
								cursor.getLong(END_POS_LEVEL_INDEX),
								cursor.getLong(COMPELETE_SIZE_INDEX),
								cursor.getString(DOWNLOAD_URL_INDEX),
								cursor.getString(APPID_INDEX),
								cursor.getString(PACKAGE_INDEX),
								cursor.getString(PHONY_PERCENT_INDEX),
								cursor.getInt(NETWORK_TYPE_INDEX),
								cursor.getString(TITLE_INDEX),
								cursor.getString(CONTENT_INDEX),
								cursor.getInt(TYPE_INDEX),
								cursor.getString(ICON_INDEX),
								cursor.getString(SENDTIME_INDEX));
						list.add(info);
					} while (cursor.moveToNext());
				}
			} catch (Exception e) {

			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
			db.close();
		}
		return list;
	}

	/**
	 * 批量获取下载信息
	 */
	public Map<String, List<DownloadDataInfo>> getInfosByAppIdBatch(
			List<String> appIds) {
		Map<String, List<DownloadDataInfo>> retData = new HashMap<String, List<DownloadDataInfo>>();
		// Log.v(TAG, "=======批量查询:" + appIds);
		synchronized (synTemp) {
			SQLiteDatabase db = getReadableDatabase();

			// 下载表数据相对比较少,用in去批量查询，还不如直接查询所有再过滤appid
			Cursor cursor = db.query(TABLE_DOWNLOAD_INFO, DOWNLOAD_COLUMS,
					null, null, null, null, null);
			try {
				if (cursor.getCount() > 0) {
					cursor.moveToFirst();
					do {
						String appId = cursor.getString(APPID_INDEX);
						String _uin = cursor.getString(UIN_INDEX);
						if (!appIds.contains(appId) || !_uin.equals(uin))
							continue;

						DownloadDataInfo info = new DownloadDataInfo(
								cursor.getInt(THREAD_ID_INDEX),
								cursor.getLong(START_POS_INDEX),
								cursor.getLong(END_POS_LEVEL_INDEX),
								cursor.getLong(COMPELETE_SIZE_INDEX),
								cursor.getString(DOWNLOAD_URL_INDEX),
								cursor.getString(APPID_INDEX),
								cursor.getString(PACKAGE_INDEX),
								cursor.getString(PHONY_PERCENT_INDEX),
								cursor.getInt(NETWORK_TYPE_INDEX),
								cursor.getString(TITLE_INDEX),
								cursor.getString(CONTENT_INDEX),
								cursor.getInt(TYPE_INDEX),
								cursor.getString(ICON_INDEX),
								cursor.getString(SENDTIME_INDEX));

						List<DownloadDataInfo> infos = retData.get(info
								.getAppId());
						if (infos == null) {
							infos = new ArrayList<DownloadDataInfo>();
							retData.put(info.getAppId(), infos);
						}

						infos.add(info);

					} while (cursor.moveToNext());
				}
			} catch (Exception e) {

			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
			db.close();
		}
		return retData;
	}

	/**
	 * 添加 下载的具体线程块信息
	 */
	public void addInfos(List<DownloadDataInfo> infos) {
		// Log.w(TAG, "@@@@@添加数据:" + infos);
		synchronized (synTemp) {
			SQLiteDatabase db = getWritableDatabase();
			try {
				db.beginTransaction();
				for (DownloadDataInfo info : infos) {
					ContentValues cv = new ContentValues();
					cv.put(THREAD_ID_COL, info.getThreadId());
					cv.put(START_POS_COL, info.getStartPos());
					cv.put(END_POS_COL, info.getEndPos());
					cv.put(COMPELETE_SIZE_COL, info.getCompeleteSize());
					cv.put(DOWNLOAD_URL_COL, info.getUrlStr());
					cv.put(APPID_COL, info.getAppId());
					cv.put(PACKAGE_COL, info.getPackageName());
					cv.put(PHONY_PERCENT_COL, info.getPhonyPercent());
					cv.put(NETWORK_TYPE_COL, info.getNetworkType());
					cv.put(TITLE_COL, info.getPushTitle());
					cv.put(CONTENT_COL, info.getPushContent());
					cv.put(TYPE_COL, info.getPushType());
					cv.put(ICON_COL, info.getPushIcon());
					cv.put(SENDTIME_COL, info.getSendTime());
					cv.put(UIN_COL, uin);
					db.insert(TABLE_DOWNLOAD_INFO, null, cv);
				}
				db.setTransactionSuccessful();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.endTransaction();
			}
			db.close();
		}
	}

	public void resetUin(String uin) {
		this.uin = uin;
	}
}

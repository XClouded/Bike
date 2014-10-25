package com.yhj.app.bike.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.yhj.app.bike.model.pojo.UserInfo;
import com.yhj.app.bike.shareprefrence.SpUserUin;
import com.yhj.app.bike.system.Application;

public class UserDBHelper {

	private static final String DATABASE_NAME = "TencentCookie.db";
	private static final String TABLE_NAME = "USERINFO";
	private static final int DATABASE_VERSION = 1;

	private static final String CREAT_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( 'ACCOUNT' TEXT, 'JSON' TEXT );";
	private static final String UPDATE_TABLE_SQL = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

	private static UserDBHelper mDBInstance;

	Context mContext;
	SQLiteDatabase userDB;

	/**
	 * 获取用户登录信息
	 * 
	 * @return 为null则需要登录
	 */
	public UserInfo getUserInfo() {

		if (Application.userInfo == null) {
			Cursor cur = userDB.query(TABLE_NAME, new String[] { "ACCOUNT", "JSON" }, null, null, null, null, null);

			for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
				String json = cur.getString(1);
				Application.userInfo = new Gson().fromJson(json, UserInfo.class);
			}
			cur.close();
		}
		return Application.userInfo;
	}

	/**
	 * 用户登录信息
	 * 
	 * @param userInfo
	 */
	public void saveUserInfo(UserInfo userInfo) {
		userDB.beginTransaction();
		try {
			ContentValues cv = new ContentValues();
			cv.put("ACCOUNT", userInfo.getAccount());
			cv.put("JSON", userInfo.toString());

			if (checkUserInfoExist(userInfo)) {
				userDB.update(TABLE_NAME, cv, "account=?", new String[] { userInfo.getAccount() });
			} else {
				userDB.insert(TABLE_NAME, null, cv);
			}

			userDB.setTransactionSuccessful();
		} finally {
			userDB.endTransaction();
			SpUserUin.saveUserUin(userInfo.getUin());
		}

	}

	public void logoutUserInfo() {
		userDB.beginTransaction();
		try {
			// userDB.delete(TABLE_NAME, "account=?", new String[] {
			// userInfo.getAccount() });
			userDB.delete(TABLE_NAME, null, null);
			Application.userInfo = null;
			userDB.setTransactionSuccessful();
		} finally {
			userDB.endTransaction();
		}
	}

	/**
	 * 检查用户信息是否存在
	 * 
	 * @param userInfo
	 * @return
	 */
	private boolean checkUserInfoExist(UserInfo userInfo) {
		int ret = userDB.query(TABLE_NAME, null, "account=?", new String[] { userInfo.getAccount() }, null, null, null).getCount();
		return ret > 0 ? true : false;
	}

	private UserDBHelper(Context applicationContext) {
		mContext = applicationContext;
	}

	public synchronized static UserDBHelper getInstance() {
		if (mDBInstance == null) {
			mDBInstance = new UserDBHelper(Application.getInstance().getApplicationContext());
			mDBInstance.open();
		}
		return mDBInstance;
	}

	private void open() {
		if (userDB == null || !userDB.isOpen()) {
			UserSQLiteOpenHelper uso = new UserSQLiteOpenHelper();
			userDB = uso.getWritableDatabase();
		}
	}

	// private void end() {
	// // if (userDB != null && userDB.isOpen()) {
	// // userDB.close();
	// // }
	// }

	class UserSQLiteOpenHelper extends SQLiteOpenHelper {

		public UserSQLiteOpenHelper() {
			super(mContext, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREAT_TABLE_SQL);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(UPDATE_TABLE_SQL);
			onCreate(db);
		}

	}
}

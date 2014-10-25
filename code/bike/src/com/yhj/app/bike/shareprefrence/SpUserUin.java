package com.yhj.app.bike.shareprefrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.system.Application;

/**
 * 用户登录一次之后,保存QQ号,注销之后仍然保存QQ号,直到下一次登录,该sp中的数据才会更新,getRemoteConfig接口里传uin用
 * @author jackiecheng
 *
 */
public class SpUserUin {

	public static void saveUserUin(String uin) {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_USER_UIN, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(Constants.SP_USER_UIN, uin);
		editor.commit();
	}

	public static String getUserUin() {
		SharedPreferences sp = Application.getInstance().getSharedPreferences(Constants.SP_USER_UIN, Context.MODE_PRIVATE);
		return sp.getString(Constants.SP_USER_UIN, "");
	}

}

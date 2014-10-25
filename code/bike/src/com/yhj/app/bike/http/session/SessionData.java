package com.yhj.app.bike.http.session;

public abstract class SessionData {
	
	public static final String KEY_USER_ID = "user_id";
	public static final String KEY_TOKEN = "token";
	
	public static SessionData inst = null;
	
	public static synchronized SessionData get() {
		if (inst == null) {
			inst = new FileSessionData();
		}
		
		return inst;
	}
	
	public abstract void setVal(String key,Object value);
	
	public abstract Object getVal(String key);
	
	public abstract void clear();
}

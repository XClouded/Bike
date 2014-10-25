package com.yhj.app.bike.http.session;

import java.util.HashMap;
import java.util.Map;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.utils.FileUtil;

public class FileSessionData extends SessionData {
	
	public static final String SESSION_FILE = Constants.CACHE_PATH + "session.data";
	
	private Map<String,Object> data = new HashMap<String,Object>();
	
	@Override
	public synchronized void setVal(String key, Object value) {
		data.put(key, value);
		
		FileUtil.saveSerObjectToFile(data, SESSION_FILE);
	}

	@Override
	public synchronized Object getVal(String key) {
		data = (Map<String, Object>) FileUtil.readSerObjectFromFile(SESSION_FILE);
		if (null == data) {
			data = new HashMap<String, Object>();
		}
		return data.get(key);
	}

	@Override
	public void clear() {
		data.clear();
		FileUtil.saveSerObjectToFile(data, SESSION_FILE);
	}

}

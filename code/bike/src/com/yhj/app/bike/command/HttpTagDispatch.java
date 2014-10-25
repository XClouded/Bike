package com.yhj.app.bike.command;

import com.google.gson.Gson;
import com.yhj.app.bike.model.LoginRes;
import com.yhj.app.bike.model.OrgData;

public class HttpTagDispatch {
	public enum HttpTag { //

		NONE("0"), 
		SMSCODE("101"), 
		REGIST("102"), 
		LOGIN("103");
		
		HttpTag(String s) {
			this.ns = s;
		}
		
		@Override
		public String toString() {
			return this.ns;
		}
		
		public static HttpTag fromString(String text) {
		    if (text != null) {
		      for (HttpTag b : HttpTag.values()) {
		        if (text.equalsIgnoreCase(b.ns)) {
		          return b;
		        }
		      }
		    }
		    return null;
		}
		
		final String ns;
	}

	public static OrgData dispatch(HttpDataRequest request, OrgData data)
			throws Exception {
		OrgData result = data;
		
		switch (request.getTag()) {
			case LOGIN: {
				String dataStr = (String) result.getData();
				
				Gson gson = new Gson();
				LoginRes res = gson.fromJson(dataStr, LoginRes.class);
				
				result.setData(res);
				
				break;
			}
	
			default:
				break;
			}
		
		return result;
	}
}

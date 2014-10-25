package com.yhj.app.bike.command;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.util.ByteArrayBuffer;

import com.yhj.app.bike.utils.SLog;
import com.yhj.app.bike.utils.StringUtil;

/**
 * 用的时候把post参数用setBodyParams方法设置到bodyParams里，把读取的图片set到image里，没有图片时不用管image
 * picKey是服务器要求的传图片时的参数key，请根据实际情况改动
 * 
 * @author hanyandu
 * @since 2012-4-18
 */
public class HttpPostRequest extends HttpDataRequest {
	private static final String TAG = "HttpPostRequest";
	private static String BR = "\r\n";
	public static String BOUNDARY = "----37531613912423";
	private static String DEVIDER = "--";
	private static String CONTENT_DISPOSITION_PRE = BR + "Content-Disposition: form-data; name=\"";
	private static String CONTENT_DISPOSITION_SUF = "\"" + BR + BR;
	private String picKey = "pic";
	String pic = DEVIDER + BOUNDARY + CONTENT_DISPOSITION_PRE + picKey + "\"; filename=\"postpic.jpg\"\r\nContent-Type: image/jpeg\r\n\r\n";
	String endData = (BR + DEVIDER + BOUNDARY + DEVIDER + BR);

	private Map<String, String> bodyParams;
	private byte[] image;
	
	private String body;
	
	public void setImage(byte[] image) {
		this.image = image;
	}

	public void setBodyParams(Map<String, String> bodyParams) {
		this.bodyParams = bodyParams;
	}

	public Map<String, String> getBodyParams() {
		return bodyParams;
	}

	public byte[] getBytes() {
		byte[] picBytes = pic.getBytes();
		byte[] endBytes = endData.getBytes();
		ByteArrayBuffer bytes = new ByteArrayBuffer(0);
		byte[] keyValueBytes = map2KeyValueBytes(bodyParams);
		bytes.append(keyValueBytes, 0, keyValueBytes.length);
		bytes.append(picBytes, 0, picBytes.length);
		bytes.append(image, 0, image.length);
		bytes.append(endBytes, 0, endBytes.length);
		return bytes.toByteArray();
	}

	private byte[] map2KeyValueBytes(Map<String, String> map) {
		if (map == null) {
			return "".getBytes();
		}
		StringBuffer str = new StringBuffer("");
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			str.append(CONTENT_DISPOSITION_PRE + key + CONTENT_DISPOSITION_SUF);
			try {
				str.append(StringUtil.urlEncode(value) + BR);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				SLog.e(TAG, e.toString(), e);
			}
		}
		return str.toString().getBytes();
	}

	public String getString() {
		return map2KeyValueString(bodyParams);
	}

	private String map2KeyValueString(Map<String, String> map) {
		if (map == null) {
			return "";
		}
		StringBuffer str = new StringBuffer("");
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			try {
				value = StringUtil.urlEncode(value==null?"":value);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				SLog.e(TAG, e.toString(), e);
			}
			str.append(key + "=" + value + "&");
		}
		if (str.length() > 0) {
			str = str.deleteCharAt(str.length() - 1);
		}
		return str.toString();
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}

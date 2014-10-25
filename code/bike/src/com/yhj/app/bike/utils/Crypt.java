package com.yhj.app.bike.utils;

/*
 * 基于base64的加密解密算法
 * 
 * */

public class Crypt { 
	

	public static String key = "FAXFAAAFSSAAEAAFA";

	
	public static boolean isDebug = false;

	

	/**
	 * 
	 * 
	 *             1.分别对post_string和密钥appkey进行base64encode得到bstr和bkey
	 *             2.对bstr的第i个字符进行步骤a-c处理得到新字符串nbstr a)取bstr的第i个字符的ascii码a
	 *             b)取bkey的第i%len(bkey) 个字符的ascii码b c)取a异或b的�?为ascii码得到新的字�?
	 *             3.�?得到的字符串做base64encode
	 */
	
	public static String encode(String postStr) {
		String bstr = android.util.Base64.encodeToString(postStr.getBytes(),android.util.Base64.DEFAULT);
		bstr = bstr.replace("\n", "");
		String bkey = android.util.Base64.encodeToString(key.getBytes(),android.util.Base64.DEFAULT);
		bkey = bkey.replace("\n", "");
		
		StringBuilder builder = new StringBuilder("");
		
		int l = bstr.length();
		for (int i=0;i<bstr.length();i++) {
			char bstr_asc = bstr.charAt(i);
			char bkey_asc = bkey.charAt(i % bkey.length());			
			char n = (char)(bstr_asc ^ bkey_asc);			
			char nCh = (char)n;
			
			
			builder.append(nCh);
		}
		
		String res =  android.util.Base64.encodeToString(builder.toString().getBytes(),android.util.Base64.DEFAULT);
		res = res.replace("\n", "");
		return res;
	}
	
	/**
	 *             1.对post_string进行base64decode得到dstr
	 *             2.对appkey进行base64encode得到bkey
	 *             3.对dstr的第i个字符进行步骤a-c处理得到新字符串ndstr a)取dstr的第i个字符的ascii码a
	 *             b)取bkey的第i%len(bkey) 个字符的ascii码b c)取a异或b的值为ascii码得到新的字符
	 *             4.对2得到的字符串做base64encode
	 */
	public static String decode(String postStr) {
		String dStr = new String(android.util.Base64.decode(postStr.getBytes(),android.util.Base64.DEFAULT));
		String bKey = android.util.Base64.encodeToString(key.getBytes(),android.util.Base64.DEFAULT);
		
		bKey = bKey.replaceAll("\n", "");
		
		StringBuilder builder = new StringBuilder("");		
		for (int i=0;i<dStr.length();i++) {
			int bstr_asc = dStr.charAt(i);
			int bkey_asc = bKey.charAt(i % bKey.length());			
			int n = bstr_asc ^ bkey_asc;			
			char nCh = (char)n;			
			
			builder.append(nCh + "");
		}
		
		return new String(android.util.Base64.decode(builder.toString().getBytes(),android.util.Base64.DEFAULT));
	}
	
	
	public static String getSign(String strJson)
	{
		try
		{
		   return MD5.getMD5(strJson+key);
		}
		catch(Exception e)
		{
			return "";
		}
		
	}
	
	
}


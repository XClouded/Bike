package com.yhj.app.bike.utils;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESUtil
{
	public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

	 /**
	  * DES算法，加密
	  * @param data  待加密字符串
	  * @param key  加密私钥，长度不能够小于8位
	  * @return 加密后的字节数组
	  * @throws CryptException
	  *             异常
	  */
	public static byte[] encode(String key, String iV, byte[] data)
	{
		if (key == null || iV == null || data == null) return null;
		try
		{
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// key的长度不能够小于8位字节
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
			IvParameterSpec iv = new IvParameterSpec(iV.getBytes());
			AlgorithmParameterSpec paramSpec = iv;
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
			return cipher.doFinal(data);
		}
		catch (Exception e)
		{
			SLog.e(e.getMessage());
			return null;
		}
	}

	 /**
	  * DES算法，解密
	  * @param data  待解密字符串
	  * @param key 解密私钥，长度不能够小于8位
	  * @return 解密后的字节数组
	  * @throws Exception  异常
	  */
	public static byte[] decode(String key, String iV, byte[] data)
	{
		if (key == null || iV == null || data == null) return null;
		try
		{
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// key的长度不能够小于8位字节
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
			IvParameterSpec iv = new IvParameterSpec(iV.getBytes());
			AlgorithmParameterSpec paramSpec = iv;
			cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
			return cipher.doFinal(data);
		}
		catch (Exception e)
		{
			SLog.e(e.getMessage());
			return null;
		}
	}
}

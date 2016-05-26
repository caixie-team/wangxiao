package com.atdld.open.common.util;
/**  
 * 概要:DES加密算法，兼容PHP的解密  
 */


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/**
 * 微信加密
 * @author Administrator
 *
 */

public class DESCoder    
{  
	private final static String KEY = "sedu2010"; // 字节数必须是8的倍数  
	public  static String SECONDKEY = "sedu2010";//自定义加密第二变量
	public static byte[] desEncrypt(byte[] plainText) throws Exception  
	{  
		SecureRandom sr = new SecureRandom();  
		DESKeySpec dks = new DESKeySpec(KEY.getBytes());  
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
		SecretKey key = keyFactory.generateSecret(dks);  
		Cipher cipher = Cipher.getInstance("DES");  
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);  
		byte data[] = plainText;  
		byte encryptedData[] = cipher.doFinal(data);  
		return encryptedData;  
	}  


    
    /**
	 * MessageDigest类为应用程序提供信息摘要算法的功能，如MD5 或SHA算法。
信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
MessageDigest对象开始被初始化。该对象通过使用 update()方法处理数据。
任何时候都可以调用 reset（）方法重置摘要。
一旦所有需要更新的数据都已经被更新了，应该调用digest() 方法之一完成哈希计算。

	 * @param strSrc
	 * @return
	 */
	public static String sha1(String strSrc) {
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(bt);//获得哈希值
			//对于给定数量的更新数据，digest方法只能被调用一次。
			//在调用digest之后，MessageDigest 对象被重新设置成其初始状态。
			strDes = bytes2Hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Invalid algorithm.");
			return null;
		}
		return strDes;
	}
	public static String bytes2Hex(byte[] bts) {//二行制转字符串
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
	/**
	 * 计算采用utf-8编码方式时字符串所占字节数
	 * 
	 * @param content
	 * @return
	 */
	public static int getByteSize(String content) {
		int size = 0;
		if (null != content) {
			try {
				// 汉字采用utf-8编码时占3个字节
				size = content.getBytes("utf-8").length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return size;
	}
	
}  
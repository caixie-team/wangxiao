package io.wangxiao.core.util;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.Security;

public class EncryptionDecryption {

	private Cipher encryptCipher = null;
	private Cipher decryptCipher = null;

	public static String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		StringBuilder sb = new StringBuilder(iLen * 2);
        for (byte anArrB : arrB) {
            int intTmp = anArrB;
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
		return sb.toString();
	}

	public static byte[] hexStr2ByteArr(String strIn) {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}


	public EncryptionDecryption(String strKey){
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey.getBytes());
		try{
			encryptCipher = Cipher.getInstance("DES");
			encryptCipher.init(Cipher.ENCRYPT_MODE, key);
	
			decryptCipher = Cipher.getInstance("DES");
			decryptCipher.init(Cipher.DECRYPT_MODE, key);
		}catch(Exception ignored){}
	}

	public byte[] encrypt(byte[] arrB) {
		try{
			return encryptCipher.doFinal(arrB);
		}catch(Exception e){
			return null;
		}
	}

	public String encrypt(String strIn) {
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	private Key getKey(byte[] arrBTmp) {
		byte[] arrB = new byte[8];
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
        return new javax.crypto.spec.SecretKeySpec(arrB, "DES");
	}

}
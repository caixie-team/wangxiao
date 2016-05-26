package co.bluepx.edu.core.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 
 * @description DESCoder加密
 * @author :
 */

public class DESCoder {
    private final static String KEY = "qinggang"; // 字节数必须是8的倍数
    public static String SECONDKEY = "qinggang";// 自定义加密第二变量

    public static byte[] desEncrypt(byte[] plainText) throws Exception {
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

    public static byte[] desDecrypt(byte[] encryptText) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(KEY.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
        byte encryptedData[] = encryptText;
        byte decryptedData[] = cipher.doFinal(encryptedData);
        return decryptedData;
    }

    public static String encrypt(String input) throws Exception {
        return base64Encode(desEncrypt(input.getBytes()));
        // return base64Encode(input.getBytes());
    }

    public static String decrypt(String input) throws Exception {
        byte[] result = base64Decode(input);
        return new String(desDecrypt(result));
        // return new String(base64Decode(input));
    }

    public static String base64Encode(byte[] s) {
        if (s == null)
            return null;
        BASE64Encoder b = new BASE64Encoder();
        return b.encode(s);
    }

    public static byte[] base64Decode(String s) throws IOException {
        if (s == null) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(s);
        return b;
    }

    public static String md5(String s) throws Exception {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(s.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

    public static void main(String args[]) {
        try {
            System.out.println(DESCoder.encrypt("1"));// 加密
            System.out.println(DESCoder.decrypt("XHDcnN3xxow="));// 解密
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
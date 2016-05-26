package com.atdld.os.core.util.security;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.NoSuchPaddingException;

/**
 * ANSI X9.9 MAC校验算法
 * 
 * DES加密结果： des key = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08}; des data
 * = {0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37}; des result = 1b 18 b9 7a
 * 85 f9 67 e9
 * 
 * MAC加密结果： mac key = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08}; mac data
 * = {0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x30, 0x31,
 * 0x32, 0x33, 0x34, 0x35, 0x36, 0x37}; mac result = a1 e8 02 aa 02 74 74 bb
 * 
 */
public class MessageAuthenticationCode {

    /**
     * 
     * @param key
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IllegalStateException
     */
    public static byte[] mac(byte[] key, byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, IllegalStateException {

        return mac(key, data, 0, data.length);
    }

    /**
     * 
     * @param key
     * @param data
     * @param offset
     * @param len
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IllegalStateException
     */

    public static byte[] mac(byte[] key, byte[] data, int offset, int len) throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, IllegalStateException {
        final String Algorithm = "DES"; // DES,DESede,Blowfish

        //
        SecretKey deskey = new SecretKeySpec(key, Algorithm);

        //
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.ENCRYPT_MODE, deskey);

        byte buf[] = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
        for (int i = 0; i < len;) {
            for (int j = 0; j < 8 && i < len; i++, j++) {
                buf[j] ^= data[offset + i];
            }
            buf = c1.update(buf);
        }
        c1.doFinal();
        return buf;
    }

    /**
     * des
     * 
     * @param key
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws Exception
     */
    public static byte[] desEncryption(byte[] key, byte[] data) throws NoSuchAlgorithmException, Exception {
        final String Algorithm = "DES/ECB/NoPadding"; // ���� �����㷨,����
                                                      // DES,DESede,Blowfish

        if (key.length != DESKeySpec.DES_KEY_LEN || data.length != 8)
            throw new IllegalArgumentException("key or data's length != 8");

        //
        DESKeySpec desKS = new DESKeySpec(key);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey deskey = skf.generateSecret(desKS);

        //
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.ENCRYPT_MODE, deskey);

        byte buf[];
        //
        buf = c1.doFinal(data);

        //
        byte[] enc_data = new byte[8];
        System.arraycopy(buf, 0, enc_data, 0, 8);
        return enc_data;
    }

    /**
     * DES
     * 
     * @param key
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws Exception
     */
    public static byte[] desDecryption(byte[] key, byte[] data) throws NoSuchAlgorithmException, Exception {
        final String Algorithm = "DES/ECB/NoPadding"; // ����
                                                      // DES,DESede,Blowfish

        if (key.length != DESKeySpec.DES_KEY_LEN || data.length != 8)
            throw new IllegalArgumentException("key's len != 8 or data's length != 8");

        //
        SecretKey deskey = new SecretKeySpec(key, "DES");

        //
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.DECRYPT_MODE, deskey);

        byte decrypted[];
        decrypted = c1.doFinal(data);
        return decrypted;
    }

    /**
     * DES
     * 
     * @param bytP
     * @param bytKey
     * @return
     * @throws Exception
     */
    protected byte[] encryptByDES(byte[] bytP, byte[] bytKey) throws Exception {
        DESKeySpec desKS = new DESKeySpec(bytKey);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey sk = skf.generateSecret(desKS);
        Cipher cip = Cipher.getInstance("DES");
        cip.init(Cipher.ENCRYPT_MODE, sk);
        return cip.doFinal(bytP);
    }

    /**
     * 
     * @param bytE
     * @param bytKey
     * @return
     * @throws Exception
     */
    protected byte[] decryptByDES(byte[] bytE, byte[] bytKey) throws Exception {
        DESKeySpec desKS = new DESKeySpec(bytKey);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey sk = skf.generateSecret(desKS);
        Cipher cip = Cipher.getInstance("DES");
        cip.init(Cipher.DECRYPT_MODE, sk);
        return cip.doFinal(bytE);
    }

    /**
     * 
     * @param key
     * @param data
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IllegalStateException
     */
    public static byte[] des3Encryption(byte[] key, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException,
            IllegalStateException {
        final String Algorithm = "DESede"; // DES,DESede,Blowfish
        SecretKey deskey = new SecretKeySpec(key, Algorithm);
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.ENCRYPT_MODE, deskey);
        return c1.doFinal(data);
    }

    /**
     * 
     * @param key
     * @param data
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IllegalStateException
     */
    public static byte[] des3Decryption(byte[] key, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException,
            IllegalStateException {
        final String Algorithm = "DESede"; 
        SecretKey deskey = new SecretKeySpec(key, Algorithm);
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.DECRYPT_MODE, deskey);
        return c1.doFinal(data);
    }

    /**
     * 
     * @param key
     * @param iv
     * @param data
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IllegalStateException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeySpecException
     */
    public static byte[] des3Encryption(byte[] key, byte[] iv, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, IllegalStateException, InvalidAlgorithmParameterException, InvalidKeySpecException {
        final String Algorithm = "DESede/CBC/PKCS5Padding";
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKey deskey = keyFactory.generateSecret(spec);
        IvParameterSpec tempIv = new IvParameterSpec(iv);
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.ENCRYPT_MODE, deskey, tempIv);
        return c1.doFinal(data);
    }

    /**
     * 
     * @param key
     * @param iv
     * @param data
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IllegalStateException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeySpecException
     */
    public static byte[] des3Decryption(byte[] key, byte[] iv, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, IllegalStateException, InvalidAlgorithmParameterException, InvalidKeySpecException {
        final String Algorithm = "DESede/CBC/PKCS5Padding"; //
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKey deskey = keyFactory.generateSecret(spec);
        // SecretKey deskey = new SecretKeySpec(key, Algorithm);
        // iv
        IvParameterSpec tempIv = new IvParameterSpec(iv);
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.DECRYPT_MODE, deskey, tempIv);
        return c1.doFinal(data);

    }

    public static void main(String[] args) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, IllegalStateException,
            InvalidAlgorithmParameterException, InvalidKeySpecException, UnsupportedEncodingException {
        String key = "6647b807e97889fca8b60047e85d9186e380d3c234f71566";
        String iv = "9df131b13df6bdfe";

        byte[] keyb = StringArrayUtil.hex2byte(key, key.length());
        byte[] ivb = StringArrayUtil.hex2byte(iv, iv.length());
        String value = "2032309250345045,500";

        // byte[]temp_value =StringArrayUtil.hex2byte(value, value.length());
        byte[] temp_bytes = des3Encryption(keyb, ivb, value.getBytes("UTF-8"));
        String en = StringArrayUtil.byte2hex(temp_bytes);
        System.out.println("en=" + en);
        byte[] decrptBytes = des3Decryption(keyb, ivb, temp_bytes);
        System.out.println("de=" + new String(decrptBytes, "UTF-8"));

        String md5key = "66ea3f65-382a-44f6-97e4-15b0a873332f";

        String tempMD5 = "PICODE=PI00001CARDDATA=" + StringArrayUtil.byte2hex(temp_bytes) + md5key;
        System.out.println(tempMD5);

        String md5 = Digest.hmacSign(tempMD5);
        System.out.println(md5);

    }
}
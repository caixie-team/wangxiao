package io.wangxiao.commons.util.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;

/**
 * @ClassName Des3Encryption
 */
public class Des3Encryption {
    public static final String CHAR_ENCODING = "UTF-8";

    public static byte[] encode(byte[] key, byte[] data) throws Exception {
        return MessageAuthenticationCode.des3Encryption(key, data);
    }

    public static byte[] decode(byte[] key, byte[] value) throws Exception {
        return MessageAuthenticationCode.des3Decryption(key, value);
    }

    public static String encode(String key, String data) {
        try {
            byte[] keyByte = key.getBytes(CHAR_ENCODING);
            byte[] dataByte = data.getBytes(CHAR_ENCODING);
            byte[] valueByte = MessageAuthenticationCode
                    .des3Encryption(keyByte, dataByte);
            String value = new String(Base64.encode(valueByte), CHAR_ENCODING);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decode(String key, String value) {
        try {
            byte[] keyByte = key.getBytes(CHAR_ENCODING);
            byte[] valueByte = Base64.decode(value.getBytes(CHAR_ENCODING));
            byte[] dataByte = MessageAuthenticationCode
                    .des3Decryption(keyByte, valueByte);
            String data = new String(dataByte, CHAR_ENCODING);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptToHex(String key, String data) {
        try {
            byte[] keyByte = key.getBytes(CHAR_ENCODING);
            byte[] dataByte = data.getBytes(CHAR_ENCODING);
            byte[] valueByte = MessageAuthenticationCode
                    .des3Encryption(keyByte, dataByte);
            String value = ConvertUtils.toHex(valueByte);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptFromHex(String key, String value) {
        try {
            byte[] keyByte = key.getBytes(CHAR_ENCODING);
            byte[] valueByte = ConvertUtils.fromHex(value);
            byte[] dataByte = MessageAuthenticationCode
                    .des3Decryption(keyByte, valueByte);
            String data = new String(dataByte, CHAR_ENCODING);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String udpEncrypt(String key, String data) {
        try {
            Key k = updGenerateKey(key);
            IvParameterSpec IVSpec = new IvParameterSpec(new byte[8]);
            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(1, k, ((IVSpec)));
            byte output[] = c.doFinal(data.getBytes("UTF-8"));
            return new String(Base64.encode(output), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Key updGenerateKey(String key) {
        try {
            DESedeKeySpec KeySpec = new DESedeKeySpec(UdpHexDecode(key));
            SecretKeyFactory KeyFactory = SecretKeyFactory.getInstance("DESede");
            Key k = ((KeyFactory.generateSecret(((KeySpec)))));
            return k;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String udpDecrypt(String key, String data) {
        try {
            byte[] input = Base64.decode(data.getBytes("UTF-8"));
            Key k = updGenerateKey(key);
            IvParameterSpec IVSpec = new IvParameterSpec(new byte[8]);
            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(2, k, ((IVSpec)));
            byte output[] = c.doFinal(input);
            return new String(output, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] UdpHexDecode(String s) {
        byte abyte0[] = new byte[s.length() / 2];
        String s1 = s.toLowerCase();
        for (int i = 0; i < s1.length(); i += 2) {
            char c = s1.charAt(i);
            char c1 = s1.charAt(i + 1);
            int j = i / 2;
            if (c < 'a')
                abyte0[j] = (byte) (c - 48 << 4);
            else
                abyte0[j] = (byte) ((c - 97) + 10 << 4);
            if (c1 < 'a')
                abyte0[j] += (byte) (c1 - 48);
            else
                abyte0[j] += (byte) ((c1 - 97) + 10);
        }
        return abyte0;
    }

    public static String encode(String value) {
        return encode("z9aa179L5c2g0253375qx67G", value);
    }

    public static String decode(String value) {
        return decode("z9aa179L5c2g0253375qx67G", value);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        /*String content = "Cz%2F0YwKtY44gLnMbKB6V20cv9fXtxCITe8P2TdLgZCDmeP8wyihbt%2FerJD6D8dA3gtxunh8%2BbA41rBVeA8%2FUZRgwtZxNGHZQDAJ9v%2BtLIljfJoVGj0mZ92Ph6B%2B%2FOwE3lIEhj%2F1LJMbnib%2BX5KaHnJ%2Bx0K1firqFTqk6wUD7CLYxZ5FFyYy1qmoDNh3LKjILe3ZiGNJGJDGQoN6e6cfJAkhL4EiBxXQO%2BLvoyWdBsFiTlMISreYWR%2FBteu8wbLguSjnAIwccBDEwwhh1xKHWOl8QPdJYToFNNQiDrHFgWpN7M9FtkDlqjrd5rpDNDWVIFAj%2F1ltVm6sd9pkstUwyo4kUmtCWU11c6NlP2PA2KZWn221kLsGoG%2B2r6DH8nP1Qyt6nFtGin7YgMVNix51LGA%3D%3D";
        content = URLDecoder.decode(content,CHAR_ENCODING);
        String key = "Rk6SokaffBChWCw2ZBiDrUZDkMJGpHwQ8i8ujKIvlD3UbQ7y";
        String crypt = udpDecrypt(key, content);
        System.out.println(crypt);*/
        // byte[] keys =
        // Hex.decode("Rk6SokaffBChWCw2ZBiDrUZDkMJGpHwQ8i8ujKIvlD3UbQ7y");
        // for(byte b : keys){
        // System.out.print(b);
        // }
        // System.out.println(ConvertUtils.fromHex("Rk6SokaffBChWCw2ZBiDrUZDkMJGpHwQ8i8ujKIvlD3UbQ7y"));

        System.out.println(encryptToHex("l3Z5q138122111tyl3Z53812", "1,2,3"));
        System.out.println(decryptFromHex("l3Z5q138122111tyl3Z53812", "10cfebdc47b27286"));
    }
}
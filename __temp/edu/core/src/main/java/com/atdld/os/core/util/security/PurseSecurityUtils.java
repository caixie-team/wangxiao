package com.atdld.os.core.util.security;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 
 * 安全工具类
 * 
 * @ClassName com.atdld.os.core.util.security.PurseSecurityUtils
 * @description
 * @author :
 * @Create Date : 2014-1-11 上午9:29:45
 */
public class PurseSecurityUtils {

    private static CryptUtil cryptUtil = new CryptUtilImpl();

    private static final String publickey = "GTUqysMnTkUcKP3ouLgyrahQ";

    public static CryptUtil getCrypt() {
        return cryptUtil;
    }

    // 24位加密
    private final static int count = 24;

    public static String getKey(String key) {
        return key.substring(0, count);
    }

    /**
     * 数据签名
     * 
     * @param key
     *            加密key
     * @param value
     *            数据
     * @return
     */
    public static String hmacSign(String key, String value) {

        return Digest.hmacSign(value, key);
    }

    /**
     * 将报文数据加密 des3 后base64
     * 
     * @param value
     *            报文加密后的字符串
     * @return
     */
    public static String secrect(String value, String key) {
        return cryptUtil.cryptDes(value, getKey(key));
    }

    /**
     * 将报文数据解密 des3 后base64
     * 
     * @param value
     *            解密后原文
     * @return
     */
    public static String decryption(String value, String key) throws Exception {
        return cryptUtil.decryptDes(value, getKey(key));
    }

    /**
     * 验签是否通过
     * 
     * @param key
     *            加密key
     * @param value
     *            报文数据
     * @param secretValue
     *            加密后的报文数据
     * @return false 未通过 true 通过
     */
    public static boolean isPassHmac(String secretValue, String key, String value) {
        String svalue = Digest.hmacSign(value, key);
        boolean flag = false;
        if (secretValue.equals(svalue)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 根据Map里面设定的值 转化成签名
     * 
     * @param map
     *            相应返回的数据报文数据
     * @param customerKey
     *            customer自己的 hamcKey
     */
    public static void generateHmac(Map<String, String> map, String customerKey) {
        StringBuilder sb = new StringBuilder();
        Set<String> set = map.keySet();
        for (String string : set) {
            String value = map.get(string);
            sb.append(value);
        }
        String hmacSign = hmacSign(customerKey, sb.toString());
        map.put("hmac", hmacSign);
    }

    /**
     * 产生不重复号(如：订单号、批次号)
     * 
     * @param prefix
     *            前缀
     * @return 生成号
     */
    public static synchronized String generateOrderNumber(String prefix) {
        StringBuffer sb = new StringBuffer();
        long time = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        String uu = uuid.toString().split("-")[0];
        sb.append(prefix);
        sb.append(time);
        sb = new StringBuffer(sb.substring(0, sb.toString().length() - 2));
        sb.append(uu);
        return sb.toString();
    }

    /**
     * 加密 des加密
     * 
     * @param key
     * @param data
     * @return
     */
    public static String encryptToHex(String data) {
        // des加密
        return Des3Encryption.encryptToHex(getKey(publickey), data);
    }

    /**
     * 解密 des加密的
     * 
     * @param key
     * @param value
     * @return
     */
    public static String decryptFromHex(String value) throws Exception {
        return Des3Encryption.decryptFromHex(getKey(publickey), value);
    }
    
    public static void main(String[] args) {
        try {
            String pas = "uid:10987";
            String jiamihou =encryptToHex(pas); //Des3Encryption.encryptToHex(publickey, pas);
            System.out.println(jiamihou);
            System.out.println(decryptFromHex(jiamihou));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

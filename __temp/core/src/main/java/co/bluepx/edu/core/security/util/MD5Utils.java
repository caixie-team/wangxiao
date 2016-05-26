package co.bluepx.edu.core.security.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5帮助类
 */
public class MD5Utils {
//    private static final Log log = LogFactory.getLog(MD5Utils.class);
    /**
     * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,apache校验下载的文件的正确性用的就是默认的这个组合
     */
    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
//            log.error("MD5Utils初始化失败！", e);
        }
    }

    /**
     * 生成字符串的md5校验值
     *
     * @param s
     *
     * @return
     */
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    /**
     * 判断字符串的md5校验码是否与一个已知的md5码相匹配
     *
     * @param str    要校验的字符串
     * @param md5Str 已知的md5校验码
     *
     * @return
     */
    public static boolean comparisonWithKnownMD5String(String str, String md5Str) {
        String s = getMD5String(str);
        return s.equals(md5Str);
    }

    /**
     * @param bytes
     *
     * @return
     */
    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer sb = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], sb);
        }
        return sb.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer sb) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换, >>>
        // 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
        char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
        sb.append(c0);
        sb.append(c1);
    }

}

package com.atdld.os.core.util.security;

import java.text.DecimalFormat;

/**
 * StringArrayUtil
 * 
 * @ClassName com.atdld.os.core.util.security.StringArrayUtil
 * @description
 * @author :
 * @Create Date : 2014-1-11 上午9:30:15
 */
public class StringArrayUtil {
    private StringArrayUtil() {
    }

    /**
     * 
     * @param s
     * @param ss
     * @return
     */
    public static int contain(String s, String[] ss) {
        for (int i = 0; i < ss.length; i++) {
            if (s.equals(ss[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 
     * @param ss
     * @param delimeter
     * @return
     */
    public static String arrayToString(String[] ss, String delimeter) {
        StringBuffer sb = new StringBuffer();

        if (ss != null && ss.length > 0) {
            for (int i = 0; i < ss.length; i++) {
                sb.append(ss[i]);
                if ((i + 1) < ss.length)
                    sb.append(delimeter);
            }
        }

        return sb.toString();
    }

    /**
     * 
     * @param ss
     * @param delimeter
     * @return
     */
    public static String arrayToString(String[] ss, char delimeter) {
        return arrayToString(ss, Character.toString(delimeter));
    }

    /**
     * 
     * @param str
     * @return
     */
    public static boolean isValidDecString(String str) {
        char ch;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (ch < '0' || ch > '9')
                return false;
        }
        return true;
    }

    public static String int2char2(int val) {
        DecimalFormat fmt = new DecimalFormat("00");

        if ((val < 0) || (val > 99))
            return null;
        return fmt.format(val);
    }

    public static String int2char3(int val) {
        DecimalFormat fmt = new DecimalFormat("000");

        if ((val < 0) || (val > 999))
            return null;
        return fmt.format(val);
    }

    /**
     * �ַ���'0'
     * 
     * @param str
     * @param len
     * @return
     */
    public static String StringFillLeftZero(String str, int len) {
        if (str.length() < len) {
            StringBuffer sb = new StringBuffer(len);
            for (int i = 0; i < len - str.length(); i++)
                sb.append('0');
            sb.append(str);
            return new String(sb);
        } else
            return str;
    }

    /**
     * �ַ��Ҳ��ո�
     * 
     * @param str
     * @param len
     * @return
     */
    public static String StringFillRightBlank(String str, int len) {
        if (str.length() < len) {
            StringBuffer sb = new StringBuffer(len);
            sb.append(str);
            for (int i = 0; i < len - str.length(); i++)
                sb.append(' ');
            return new String(sb);
        } else
            return str;
    }

    public static String long2StringLeftZero(long val, int len) {
        String pattern = "";
        for (int i = 0; i < len; i++)
            pattern += '0';

        DecimalFormat fmt = new DecimalFormat(pattern);
        return fmt.format(val);
    }

    public static String FillString(char val, int len) {
        String str = "";
        for (int i = 0; i < len; i++)
            str = str + val;
        return str;
    }

    public static String long2StringRightBlank(long val, int len) {
        String pattern = "#";
        DecimalFormat fmt = new DecimalFormat(pattern);
        String str = fmt.format(val);
        if (str.length() < len) {
            return str + FillString(' ', len - str.length());
        } else
            return str.substring(str.length() - len);
    }

    /**
     * 
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        return byte2hex(b, (char) 0);
    }

    /*
     * Converts a byte to hex digit and writes to the supplied buffer
     */
    final private static char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    private static void byte2hex(byte b, StringBuffer buf) {
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }

    public static String byte2hex(byte b) {
        int high = b;
        high = (high & 0xf0) >> 4; // System.out.print(Integer.toHexString(high));
        int low = (b & 0x0f); // System.out.print(Integer.toHexString(low));
        return "" + hexChars[high] + hexChars[low];
    }

    /**
     * 
     * @param b
     * @param delimeter
     * @return
     */
    public static String byte2hex(byte[] b, char delimeter) {
        StringBuffer sb = new StringBuffer();
        for (int n = 0; n < b.length; n++) {
            byte2hex(b[n], sb);
            if (n < (b.length - 1) && delimeter != 0)
                sb.append(delimeter);
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 
     * @param hexStr
     * @param len
     * @return
     * @throws AssertionError
     */
    public static byte[] hex2byte(String hexStr, int len) throws AssertionError {
        if ((len % 2) != 0)
            throw new AssertionError("Hex2Byte() fail: len should be divided by 2");

        byte[] buf = new byte[len / 2];
        for (int i = 0, j = 0; i < len; i += 2, j++) {
            byte hi = (byte) Character.toUpperCase(hexStr.charAt(i));
            byte lo = (byte) Character.toUpperCase(hexStr.charAt(i + 1));
            if (!Character.isDigit((char) hi) && !(hi >= 'A' && hi <= 'F'))
                throw new AssertionError("Hex2Byte() fail: hex string is invalid in " + i + " with char '" + hexStr.charAt(i) + "'");
            if (!Character.isDigit((char) lo) && !(lo >= 'A' && lo <= 'F'))
                throw new AssertionError("Hex2Byte() fail: hex string is invalid in " + (i + 1) + " with char '" + hexStr.charAt(i + 1) + "'");

            int ch = 0;
            ch |= (Character.isDigit((char) hi) ? (hi - '0') : (0x0a + hi - 'A')) << 4;
            ch |= (Character.isDigit((char) lo) ? (lo - '0') : (0x0a + lo - 'A'));
            buf[j] = (byte) ch;
        }

        return buf;
    }

    /**
     * 
     * @param val
     * @param c
     * @param maxlen
     * @return
     */
    public static String leftFillChar(String val, char c, int maxlen) {
        StringBuffer sb = new StringBuffer();
        if (val.length() < maxlen) {
            for (int i = 0; i < maxlen - val.length(); i++) {
                sb.append('0');
            }
        }
        sb.append(val);
        return sb.toString();
    }

}

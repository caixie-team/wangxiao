package com.atdld.os.core.util;

import java.text.SimpleDateFormat;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author lvjx
 * @version 1.0
 */

public class IdGen {
    private static long suff = 100;

    private static long cring = 0;

    private static String getyyyymmddhhmmss() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(new java.util.Date());
    }

    private static synchronized String getInc() {
        cring += 1;
        if (cring > 99999999)
            cring = 0;
        String tem = String.valueOf(cring);
        while (tem.length() < 8)
            tem = "0" + tem;
        return tem;
    }

    public static String getSeq(String seq) {
        return (seq + getyyyymmddhhmmss() + getInc());
    }

    public static String MD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static synchronized String genId() {
        suff += 1;
        if (suff > 999)
            suff = 100;
        String tem = "" + suff;
        return tem;
    }
}

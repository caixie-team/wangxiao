// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Md5Encrypt.java

package io.wangxiao.edu.common.util.alipay;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Encrypt {

    private static final char DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public Md5Encrypt() {
    }

    public static String md5(String text) {
        MessageDigest msgDigest = null;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }
        try {
            msgDigest.update(text.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("System doesn't support your  EncodingException.");
        }
        byte bytes[] = msgDigest.digest();
        String md5Str = new String(encodeHex(bytes));
        return md5Str;
    }

    public static char[] encodeHex(byte data[]) {
        int l = data.length;
        char out[] = new char[l << 1];
        int i = 0;
        int j = 0;
        for (; i < l; i++) {
            out[j++] = DIGITS[(0xf0 & data[i]) >>> 4];
            out[j++] = DIGITS[0xf & data[i]];
        }

        return out;
    }

}

// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UtilDate.java

package io.wangxiao.edu.common.util.alipay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UtilDate {

    public UtilDate() {
    }

    public static String getOrderNum() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(date);
    }

    public static String getDateFormatter() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    public static String getDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }

    public static String getThree() {
        Random rad = new Random();
        return (new StringBuilder(String.valueOf(rad.nextInt(1000)))).toString();
    }

}

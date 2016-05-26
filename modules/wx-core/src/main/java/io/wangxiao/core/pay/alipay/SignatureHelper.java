// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SignatureHelper.java

package io.wangxiao.core.pay.alipay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

// Referenced classes of package com.alipay.util:
//			Md5Encrypt

public class SignatureHelper {

    public SignatureHelper() {
    }

    public static String sign(Map params, String privateKey) {
        Properties properties = new Properties();
        for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            Object value = params.get(name);
            if (name != null && !name.equalsIgnoreCase("sign") && !name.equalsIgnoreCase("sign_type"))
                properties.setProperty(name, value.toString());
        }

        String content = getSignatureContent(properties);
        return sign(content, privateKey);
    }

    public static String getSignatureContent(Properties properties) {
        StringBuffer content = new StringBuffer();
        List keys = new ArrayList((Collection) properties.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = properties.getProperty(key);
            content.append((new StringBuilder(String.valueOf(i != 0 ? "&" : ""))).append(key).append("=").append(value).toString());
        }

        return content.toString();
    }

    public static String sign(String content, String privateKey) {
        if (privateKey == null)
            return null;
        String signBefore = (new StringBuilder(String.valueOf(content))).append(privateKey).toString();
        /*
         * try { FileWriter writer = new FileWriter((new
         * StringBuilder("/highsolog/alipay_notify_log"
         * )).append(System.currentTimeMillis()).append(".txt").toString());
         * writer.write(signBefore); writer.close(); } catch (Exception e) {
         * e.printStackTrace(); }
         */
        return Md5Encrypt.md5(signBefore);
    }
}

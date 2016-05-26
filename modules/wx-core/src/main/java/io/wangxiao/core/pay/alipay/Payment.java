// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Payment.java

package io.wangxiao.core.pay.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

// Referenced classes of package com.alipay.util:
//			Md5Encrypt

public class Payment {

    public Payment() {
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String CreateUrl(String paygateway, String service, String sign_type, String out_trade_no, String input_charset, String partner, String key, String show_url, String body,
            String total_fee, String payment_type, String seller_email, String subject, String notify_url, String return_url, String paymethod, String defaultbank,String extra_common_param) {
        Map params = new HashMap();
        params.put("service", service);
        params.put("partner", partner);
        params.put("subject", subject);
        params.put("body", body);
        params.put("out_trade_no", out_trade_no);
        params.put("total_fee", total_fee);
        params.put("show_url", show_url);
        params.put("payment_type", payment_type);
        params.put("seller_email", seller_email);
        params.put("return_url", return_url);
        params.put("notify_url", notify_url);
        params.put("paymethod", paymethod);
        params.put("extra_common_param", extra_common_param);
        if (defaultbank != null && !"".equals(defaultbank)) {
            params.put("defaultbank", defaultbank);
        }
        params.put("_input_charset", input_charset);
        String prestr = "";
        prestr = (new StringBuilder(String.valueOf(prestr))).append(key).toString();
        String sign = Md5Encrypt.md5(getContent(params, key));
        String parameter = "";
        parameter = (new StringBuilder(String.valueOf(parameter))).append(paygateway).toString();
        List keys = new ArrayList((Collection) params.keySet());
        for (int i = 0; i < keys.size(); i++)
            try {
                parameter = (new StringBuilder(String.valueOf(parameter))).append(keys.get(i)).append("=").append(URLEncoder.encode((String) params.get(keys.get(i)), input_charset)).append("&")
                        .toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        parameter = (new StringBuilder(String.valueOf(parameter))).append("sign=").append(sign).append("&sign_type=").append(sign_type).toString();
        return parameter;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static String getContent(Map params, String privateKey) {
        List keys = new ArrayList((Collection) params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = (String) params.get(key);
            if (i == keys.size() - 1)
                prestr = (new StringBuilder(String.valueOf(prestr))).append(key).append("=").append(value).toString();
            else
                prestr = (new StringBuilder(String.valueOf(prestr))).append(key).append("=").append(value).append("&").toString();
        }

        return (new StringBuilder(String.valueOf(prestr))).append(privateKey).toString();
    }
}

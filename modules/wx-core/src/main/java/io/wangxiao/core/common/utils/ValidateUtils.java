package io.wangxiao.core.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 格式校验帮助类
 */
public class ValidateUtils {

    static Pattern emailRegex = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    static Pattern mobileRegex = Pattern
            .compile("^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email String
     */
    public static boolean isEmail(String email) {
        if (StringUtils.isBlank(email))
            return false;
        email = email.toLowerCase();
        return emailRegex.matcher(email).matches();
    }

    /**
     * 判断是不是一个合法的手机号
     *
     * @param mobile
     *
     * @return
     */
    public static boolean isMobile(String mobile) {
        return !StringUtils.isBlank(mobile) && mobileRegex.matcher(mobile).matches();
    }

}

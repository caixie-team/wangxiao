package com.atdld.os.core.util;

/**
 * 
 * @ClassName com.supergenius.sns.util.EnumUtil
 * @description EnumUtil工具类转换字符串
 * @author : Administrator
 * @Create Date : 2013-12-13 下午2:17:54
 */
public class EnumUtil {

    public static <T extends Enum<T>> T transStringToEnum(Class<T> c, String enumString) {
        if (c != null && !"".equals(enumString)) {
            try {
                return Enum.valueOf(c, enumString.trim());
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static String transEnumToString(Enum<?> enumValue) {
        if ("".equals(enumValue) || enumValue == null) {
            return "";
        }
        return enumValue.toString();
    }

}
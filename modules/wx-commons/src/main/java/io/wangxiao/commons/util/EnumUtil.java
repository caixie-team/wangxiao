package io.wangxiao.commons.util;

/**
 * 
 * @ClassName EnumUtil
 * @description EnumUtil工具类转换字符串
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
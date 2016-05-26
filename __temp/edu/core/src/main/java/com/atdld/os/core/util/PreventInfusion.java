package com.atdld.os.core.util;

public class PreventInfusion {

    private static final String inj_str = "'|insert|select|delete|update|count|*|%|truncate|declare|;|-|+|,";

    private static String strReplace(String str, String restr) {
        return str.replace(restr, "");
    }

    private static String dealNull(String str) {
        String returnstr = null;
        if (str == null)
            returnstr = "";
        else
            returnstr = str;
        return returnstr;
    }

    /**
     * 防注入，去除sql命令和空格
     */
    public static String sqlInfusion(String str) {
        String inj_stra[] = inj_str.split("\\|");
        str = dealNull(str);
        str = str.toLowerCase();
        for (int i = 0; i < inj_stra.length; i++) {
            if (str.indexOf(inj_stra[i]) != -1) {
                str = strReplace(str, inj_stra[i]);
            }
        }
        return str;
    }

    /**
     * 去除非法关键字和替换xss关键字符
     * 
     * @param str
     * @return
     */
    public static String safeRep(String str) {
        str = sqlInfusion(str);
        return xssEncode(str);
    }

    /**
     * 防注入，判断是否含有sql命令
     */
    public static boolean sql_Injection(String str) {
        String arr[] = inj_str.split("\\|");
        for (int i = 0; i < arr.length; i++) {
            if (str.indexOf(arr[i]) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     * 
     * @param s
     * @return
     */
    public static String xssEncode(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
            case '>':
                sb.append('＞');// 全角大于号
                break;
            case '<':
                sb.append('＜');// 全角小于号
                break;
            case '\'':
                sb.append('‘');// 全角单引号
                break;
            case '\"':
                sb.append('“');// 全角双引号
                break;
            case '&':
                sb.append('＆');// 全角
                break;
            case '\\':
                sb.append('＼');// 全角斜线
                break;
            case '#':
                sb.append('＃');// 全角井号
                break;
            default:
                sb.append(c);
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 检查字符串是否有安全
     * 
     * @param str
     * @return
     */
    public static boolean checkSafety(String str) {
        String arr[] = inj_str.split("\\|");
        for (int i = 0; i < arr.length; i++) {
            if (str.indexOf(arr[i]) != -1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(sqlInfusion(""));
        System.out.println(sqlInfusion("null"));
        System.out.println(sqlInfusion(null));
        System.out.println(xssEncode("&"));
    }

    /***
     * 防止sql注入
     * 
     * @param str
     * @return
     */
    public static boolean sql_inj(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        String[] inj_stra = inj_str.split("\\|");
        for (int i = 0; i < inj_stra.length; i++) {
            if (str.indexOf(inj_stra[i]) != -1) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean issysok(String s){
        return "1".equalsIgnoreCase(s);
    }
    
}

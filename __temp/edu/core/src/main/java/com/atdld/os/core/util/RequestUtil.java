package com.atdld.os.core.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    public static int getInt(HttpServletRequest request, String str) {
        String tempStr = request.getParameter(str);
        int tempInt = 0;
        if (tempStr == null || "".equals(tempStr)) {
            tempStr = "0";
        }
        try {
            tempInt = Integer.parseInt(tempStr);
        } catch (Exception e) {
            e.printStackTrace();
            tempInt = 0;
            e.printStackTrace();
        }
        return tempInt;
    }

    public static int getInt(HttpServletRequest request, String str, int defaultInt) {
        String tempStr = request.getParameter(str);
        int tempInt = 0;
        if (tempStr == null || "".equals(tempStr)) {
            tempInt = defaultInt;
            return tempInt;
        }
        try {
            tempInt = Integer.parseInt(tempStr);
        } catch (Exception e) {
            e.printStackTrace();
            tempInt = 0;
            e.printStackTrace();
        }
        return tempInt;
    }

    public static Long getLong(HttpServletRequest request, String str) {
        String tempStr = request.getParameter(str);
        Long tempInt = 0L;
        if (tempStr == null || "".equals(tempStr)) {
            tempStr = "0";
        }
        try {
            tempInt = Long.valueOf(tempStr);
        } catch (Exception e) {
            e.printStackTrace();
            tempInt = 0L;
            e.printStackTrace();
        }
        return tempInt;
    }

    public static Long getLong(HttpServletRequest request, String str, Long defaultInt) {
        String tempStr = request.getParameter(str);
        Long tempInt = 0L;
        if (tempStr == null || "".equals(tempStr)) {
            tempInt = defaultInt;
            return tempInt;
        }
        try {
            tempInt = Long.valueOf(tempStr);
        } catch (Exception e) {
            e.printStackTrace();
            tempInt = 0L;
            e.printStackTrace();
        }
        return tempInt;
    }

}

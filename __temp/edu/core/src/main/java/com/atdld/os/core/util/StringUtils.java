package com.atdld.os.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName com.supergenius.sns.util.StringUtils
 * @description 字符串工具类
 * @author :
 * @Create Date : 2013-12-13 下午2:23:56
 */
public class StringUtils {

    /**
     * 手机号中奖4位加星号
     * 
     * @author
     * @param mobile
     * @return String
     * 
     */
    public static String starMobile(String mobile) {
        if (mobile.length() == 11) {
            String starmobile = String.valueOf(mobile.charAt(0)) + String.valueOf(mobile.charAt(1)) + String.valueOf(mobile.charAt(2)) + "****" + String.valueOf(mobile.charAt(7))
                    + String.valueOf(mobile.charAt(8)) + String.valueOf(mobile.charAt(9)) + String.valueOf(mobile.charAt(10));
            return starmobile;
        }
        return mobile;
    }

    /**
     * 生成指定长度的随机字符串
     * 
     * @author
     * @param strLength
     * @return
     */
    public static String getRandomString(int strLength) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < strLength; i++) {
            int charInt;
            char c;
            if (random.nextBoolean()) {
                charInt = 48 + random.nextInt(10);
                c = (char) charInt;
                buffer.append(c);
                continue;
            }
            charInt = 65;
            if (random.nextBoolean())
                charInt = 65 + random.nextInt(26);
            else
                charInt = 97 + random.nextInt(26);
            if (charInt == 79)
                charInt = 111;
            c = (char) charInt;
            buffer.append(c);
        }

        return buffer.toString();
    }

    /**
     * MD5加密方法
     * 
     * @author
     * @param str
     *            String
     * @return String
     */
    public static String md5(String str) {
        if (str == null) {
            return null;
        }
        byte newByte1[] = str.getBytes();
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            byte newByte2[] = messagedigest.digest(newByte1);
            String cryptograph = "";
            for (int i = 0; i < newByte2.length; i++) {
                String temp = Integer.toHexString(newByte2[i] & 0x000000ff);
                if (temp.length() < 2)
                    temp = "0" + temp;
                cryptograph += temp;
            }
            return cryptograph;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证Email地址是否有效
     * 
     * @author
     * @param sEmail
     * @return boolean
     */
    public static boolean validEmail(String sEmail) {
        String pattern = "^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return sEmail.matches(pattern);
    }

    /**
     * 验证字符是否大长
     * 
     * @author
     * @param str
     * @return
     */
    public static boolean validMaxLen(String str, int length) {
        if (str == null || str.equals("")) {
            return false;
        }
        if (str.length() > length) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证字符是否长度太小
     * 
     * @author
     * @param str
     * @return boolean
     */
    public static boolean validMinLen(String str, int length) {
        if (str == null || str.equals("")) {
            return false;
        }
        if (str.length() < length) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证两个字符串是否相等且不能为空
     * 
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null || str1.equals("") || str2 == null || str2.equals("")) {
            return false;
        }
        return str1.equals(str2);
    }

    /**
     * 将字符型转为Int
     * 
     * @param str
     * @return
     */
    public static int toInt(String str) {
        int value = 0;
        if (str == null || str.equals("")) {
            return 0;
        }
        try {
            value = Integer.parseInt(str);
        } catch (Exception ex) {
            ex.printStackTrace();
            value = 0;
        }
        return value;
    }

    /**
     * 把数组转换成String
     * 
     * @param array
     * @return
     */
    public static String arrayToString(Object[] array, String split) {
        if (array == null) {
            return "";
        }
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                str.append(array[i].toString()).append(split);
            } else {
                str.append(array[i].toString());
            }
        }
        return str.toString();
    }

    /**
     * 得到WEB-INF的绝对路�?
     * 
     * @return
     */
    public static String getWebInfPath() {
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString();
        if (filePath.toLowerCase().indexOf("file:") > -1) {
            filePath = filePath.substring(6, filePath.length());
        }
        if (filePath.toLowerCase().indexOf("classes") > -1) {
            filePath = filePath.replaceAll("/classes", "");
        }
        if (System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
            filePath = "/" + filePath;
        }
        if (!filePath.endsWith("/"))
            filePath += "/";
        return filePath;
    }

    /**
     * 得到根目录绝对路�?(不包含WEB-INF)
     * 
     * @return
     */
    public static String getRootPath() {
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString();
        if (filePath.toLowerCase().indexOf("file:") > -1) {
            filePath = filePath.substring(6, filePath.length());
        }
        if (filePath.toLowerCase().indexOf("classes") > -1) {
            filePath = filePath.replaceAll("/classes", "");
        }
        if (filePath.toLowerCase().indexOf("web-inf") > -1) {
            filePath = filePath.substring(0, filePath.length() - 9);
        }
        if (System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
            filePath = "/" + filePath;
        }

        if (filePath.endsWith("/"))
            filePath = filePath.substring(0, filePath.length() - 1);

        return filePath;
    }

    /**
     * 格式化页�?
     * 
     * @param page
     * @return
     */
    public static int formatPage(String page) {
        int iPage = 1;
        if (page == null || page.equals("")) {
            return iPage;
        }
        try {
            iPage = Integer.parseInt(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            iPage = 1;
        }
        return iPage;
    }

    /**
     * 将计量单位字节转换为相应单位
     * 
     * @param size
     * @return
     */
    public static String getFileSize(String fileSize) {
        String temp = "";
        DecimalFormat df = new DecimalFormat("0.00");
        double dbFileSize = Double.parseDouble(fileSize);
        if (dbFileSize >= 1024) {
            if (dbFileSize >= 1048576) {
                if (dbFileSize >= 1073741824) {
                    temp = df.format(dbFileSize / 1024 / 1024 / 1024) + " GB";
                } else {
                    temp = df.format(dbFileSize / 1024 / 1024) + " MB";
                }
            } else {
                temp = df.format(dbFileSize / 1024) + " KB";
            }
        } else {
            temp = df.format(dbFileSize / 1024) + " KB";
        }
        return temp;
    }

    /**
     * 得到32位随机字
     * 
     * @return
     */
    public static String getEntry() {
        Random random = new Random(100);
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(new String("yyyyMMddHHmmssS"));
        return md5(formatter.format(now) + random.nextDouble());
    }

    /**
     * 将中文汉字转成UTF8编码
     * 
     * @param str
     * @return
     */
    public static String toUTF8(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        try {
            return new String(str.getBytes("ISO8859-1"), "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static String to(String str, String charset) {
        if (str == null || str.equals("")) {
            return "";
        }
        try {
            return new String(str.getBytes("ISO8859-1"), charset);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 得到10个数字的大写，0-9
     * 
     * @param num
     * @return
     */
    public static String getChineseNum(int num) {
        String[] chineseNum = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        return chineseNum[num];
    }

    public static String replaceEnter(String str) {
        if (str == null)
            return null;
        return str.replaceAll("\r", "").replaceAll("\n", "");
    }

    /**
     * 去除HTML 元素
     * 
     * @param element
     * @return
     */
    public static String getTxtWithoutHTMLElement(String element) {
        if (null == element) {
            return element;
        }
        Pattern pattern = Pattern.compile("<[^<|^>]*>");
        Matcher matcher = pattern.matcher(element);
        StringBuffer txt = new StringBuffer();
        while (matcher.find()) {
            String group = matcher.group();
            if (group.matches("<[\\s]*>")) {
                matcher.appendReplacement(txt, group);
            } else {
                matcher.appendReplacement(txt, "");
            }
        }
        matcher.appendTail(txt);
        String temp = txt.toString().replaceAll("[\r|\n]", "");
        // 多个连续空格替换为一个空格
        temp = temp.replaceAll("\\s+", " ");
        return temp;
    }

    /**
     * clear trim to String
     * 
     * @return
     */
    public static String toTrim(String strtrim) {
        if (null != strtrim && !strtrim.equals("")) {
            return strtrim.trim();
        }
        return "";
    }

    /**
     * UUID
     */
    public static String createUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 按传入字数截断并加结尾符号
     * 
     * @param sourceStr
     * @param length
     * @param charactor
     * @return
     */
    public static String cutffStr(String sourceStr, int length, String charactor) {
        String resultStr = sourceStr;
        if (sourceStr == null || "".equals(sourceStr)) {

            return "";
        }
        if (sourceStr.length() > length) {
            resultStr = sourceStr.substring(0, length);
            resultStr += charactor;

        }

        return resultStr;

    }

    public static boolean isNumber(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        boolean flag = false;
        try {
            Long.parseLong(str);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static String getLength(Object goodsName, int length) {
        if (goodsName == null) {
            return null;
        } else {
            String temp = String.valueOf(goodsName);
            if (temp.length() <= length) {
                return temp;
            } else {
                temp = temp.substring(0, length) + "...";
                return temp;
            }
        }
    }

    /**
     * 替换email中@符号前的一半字符为*号
     * 
     * @param email
     * @return
     */
    public static String handleEmail(String email) {
        if (email == null) {
            return "";
        } else {
            String[] aryEmail = email.split("@");
            if (aryEmail != null && aryEmail.length == 2) {
                if (aryEmail[0] != null) {
                    String firstPart = aryEmail[0].substring(aryEmail[0].length() / 2, aryEmail[0].length());
                    if (firstPart != null && !"".equals(firstPart)) {
                        char repeatChar[] = new char[firstPart.length()];
                        for (int i = 0; i < firstPart.length(); i++) {
                            repeatChar[i] = '*';
                        }
                        email = email.replaceFirst(firstPart + "@", new String(repeatChar) + "@");
                    }
                }
            }
        }
        return email;
    }

    private static final String regex_mobile = "^1\\d{10}$";

    /**
     * 
     * @author
     * @param tocheckNo
     * @return 手机号码校验
     * 
     */
    public static boolean isMobileNo(String tocheckNo) {
        return Pattern.matches(regex_mobile, tocheckNo);
    }

    private static final String regex_digital = "^[1-9]\\d{0,}";

    /**
     * 
     * @author
     * @param source
     * @param ingoreDigital
     *            忽略数字校验
     * @return
     * 
     */
    public static boolean neNullAndDigital(String source, boolean ingoreDigital, Integer length) {
        boolean isvalid = false;
        if (source != null && !"".equals(source.trim())) {
            isvalid = true;
        }
        if (!ingoreDigital && isvalid) {
            isvalid = Pattern.matches(regex_digital, source);
        }
        if (isvalid && length != null) {
            isvalid = source.trim().length() <= length;
        }
        return isvalid;
    }

    /**
     * 验证字符串是否为空
     * 
     * @author
     * @param str
     * @return true:不为空
     */
    public static boolean validNull(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @author
     * @param str
     * @return
     * 
     */
    public static boolean validNull(String... str) {
        for (int i = 0; i < str.length; i++) {
            if (str[i] == null || str[i].trim().equals("")) {
                return false;
            }
        }
        return true;
    }

    public static String getRootPath(String resource) {
        String filePath = Thread.currentThread().getContextClassLoader().getResource(resource).toString();
        if (filePath.toLowerCase().indexOf("file:") > -1) {
            filePath = filePath.substring(6, filePath.length());
        }
        if (filePath.toLowerCase().indexOf("classes") > -1) {
            filePath = filePath.replaceAll("/classes", "");
        }
        if (filePath.toLowerCase().indexOf("web-inf") > -1) {
            filePath = filePath.substring(0, filePath.length() - 9);
        }
        if (System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
            filePath = "/" + filePath;
        }

        if (!filePath.endsWith("/"))
            filePath += "/";

        return filePath;
    }

    public static String getRandStr(int n) {
        Random random = new Random();
        String sRand = "";
        for (int i = 0; i < n; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }
        return sRand;
    }

    /**
     * 序列号备用随机数
     * 
     * @return
     */
    public static String getSysTimeRandom() {
        return System.currentTimeMillis() + "" + new Random().nextInt(100);

    }

    /**
     * 商品订单序列号备用随机数--指定位数
     * 
     * @return
     */
    public static String getSysTimeRandom(int count) {

        String resultRandom = System.currentTimeMillis() + "" + new Random().nextInt(100);

        String resultRandomPro = "";
        int resultCount = resultRandom.length();
        if (count >= resultCount) {
            for (int i = 0; i < count - resultCount; i++) {

                resultRandomPro += "0";

            }
            return resultRandomPro + resultRandom;
        } else {

            return resultRandom.substring(resultCount - 1 - count, resultCount - 1);
        }

    }

    /**
     * 参数转换
     * 
     * @param source
     * @return
     */
    public static String[] parseParam(String source) {

        if (source == null || "".equals(source)) {
            throw new IllegalArgumentException("source is null");
        }
        String[] resultAry = source.split("&");
        return resultAry;
    }

    /**
     * 参数转换 renli.yu
     * 
     * @param source
     * @return
     */
    public static String[] parseParamArray(String source) {

        if (source == null || "".equals(source)) {
            throw new IllegalArgumentException("source is null");
        }
        String[] resultAry = source.split("\\|");
        return resultAry;
    }

    public static String convStrToHessian(String item, int count) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(URLEncoder.encode(item, "utf-8")).append("|");
        }
        if (sb != null && sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static String convToHessian(String item, int count) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(URLEncoder.encode(item, "utf-8")).append("|");
        }

        return sb.toString();
    }

    public static String convAryToStr(String sourceStr, String sourceChar, String resultChar, boolean isTrans) {
        if (isTrans) {
            sourceChar = "\\" + sourceChar;
        }
        String[] sourceStrAry = sourceStr.split(sourceChar);

        int count = sourceStrAry.length;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            try {
                Long.parseLong(sourceStrAry[i]); // 如果不为数字，则抛异常

            } catch (Exception e) {
                e.printStackTrace();
            }

            sb.append(sourceStrAry[i]).append(resultChar);
        }
        if (sb != null && sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static String convListToString(List<Map<String, Object>> list, String flag) {
        StringBuilder sb = new StringBuilder();
        int count = list.size();
        for (int i = 0; i < count; i++) {
            sb.append(list.get(i).get(flag));
            sb.append(",");
        }
        if (sb != null && sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static String queryParam(String param, String queryParam) {
        if (validNull(param)) {
            return queryParam + "=" + param + "&";
        } else {
            return "";
        }
    }

    /**
     * Description : 讲字符串类型转换为java.sql.Timestamp
     * 
     * @param time
     * @return
     */
    public static Timestamp convertToTimestamp(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date myDate = null;
        Timestamp myTimestamp = null;
        try {
            myDate = sdf.parse(time);
            myTimestamp = new Timestamp(myDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myTimestamp;
    }

    /**
     * 随机取模
     * 
     * @return
     */
    public static String randomBase() {

        String result = String.valueOf(System.currentTimeMillis() % 10);

        return result;

    }

    /**
     * 根据用户账户ID取模
     * 
     * @param id
     * @return
     */

    public static Long getDeliveryIdBase(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }

        return id % 10;

    }

    /**
     * @param strIp1
     *            获取的分销商IP
     * @param StrIp2数据库白名单IP
     * @return
     */
    public static boolean checkIp(String strIp1, String StrIp2) {
        boolean boo = false;
        if ("".equals(StrIp2)) {
            return true;
        }
        boolean isOrderIpRule = strIp1
                .matches("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
        if (!isOrderIpRule) {
            return boo;
        }
        String ipArray[] = StrIp2.split(",");
        for (int i = 0; i < ipArray.length; i++) {
            String ipArr = ipArray[i];
            String ipay = "";
            if (ipArr.contains("*")) { // 如格式为192.168.1.*判断
                ipay = ipArr.substring(0, ipArr.lastIndexOf("."));
                boo = strIp1.substring(0, strIp1.lastIndexOf(".")).equals(ipay);
                if (boo) {
                    return boo;
                }
            } else if (ipArr.contains("-")) { // 如格式为192.168.1.1-155判断
                ipay = ipArr.substring(ipArr.lastIndexOf(".") + 1);
                String ipayArray[] = ipay.split("-");
                String ips = strIp1.substring(strIp1.lastIndexOf(".") + 1);
                if (Integer.parseInt(ipayArray[0]) <= Integer.parseInt(ips) && Integer.parseInt(ips) <= Integer.parseInt(ipayArray[1])) {
                    boo = true;
                    return boo;
                }
            } else { // 如格式为192.168.1.1判断
                boo = strIp1.equals(ipArr);
                if (boo) {
                    return boo;
                }
            }
        }
        return boo;
    }

    public static boolean isEmpty(String str) {
        if (null == str || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 组装字符串 为字符串添加前缀后缀 add by wangweijie
     * 
     * @param str
     * @param prefix
     *            前缀
     * @param suffix
     *            后缀
     * @return
     */
    public static String packagingString(String str, String prefix, String suffix) {
        if (StringUtils.isEmpty(str))
            str = "";
        if (StringUtils.isEmpty(prefix))
            prefix = "";
        if (StringUtils.isEmpty(suffix))
            suffix = "";
        return prefix + str + suffix;
    }

    /**
     * 字符串对以分号分隔的字符串转化为数组，并对数组按有小到大的排序 add by wangweijie 2012-11-16
     * 
     * @return
     */
    public static String[] sortArray(String[] array) {
        // 冒泡排序--有小到大顺序
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i].compareTo(array[j]) < 0) {
                    String temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 字符串折半查找(数组必须是由小到大排列的有序数组 -1代表未查到，否则返回查找的下标 add by wangweijie 2012-11-16
     */
    public static int bisearch(final String[] sourceArray, final String seek) {
        if (null == sourceArray || sourceArray.length == 0 || null == seek) {
            return -1;
        }

        int bottom = 0;
        int top = sourceArray.length - 1;
        int mid;

        while (bottom <= top) {
            mid = (bottom + top) / 2;
            int result = sourceArray[mid].compareTo(seek);
            if (0 == result) {
                return mid;
            } else if (result > 0) {
                top = mid - 1;
            } else {
                bottom = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 判断字符串是否为空
     * 
     * @param str
     * @return true(空); false(非空)
     */
    public static boolean isBlank(String str) {
        return null == str || str.trim().length() == 0;
    }

    public static boolean isBlank(Object obj) {
        return ObjectUtils.isNull(obj) ? true : isBlank(obj.toString());
    }

    /**
     * 格式化日期
     * 
     * @param oldDate
     * @return
     */
    public static String getModelDate(Date oldDate) {
        // 判断为空
        if (ObjectUtils.isNotNull(oldDate)) {
            Date newDate = new Date();
            long second = (newDate.getTime() - oldDate.getTime()) / 1000;
            if (second <= 60) {// 0-60秒
                return second + "秒前";
            } else if (60 < second && second <= (60 * 60)) {// 1-60分钟
                second = second / 60;// 分钟数
                return second + "分钟前";
            } else if (60 * 60 < second && second <= (60 * 60 * 24)) {// 1-24小时
                second = second / 60 / 60;// 小时数
                return second + "小时前";
            } else if (60 * 60 * 24 < second && second <= (60 * 60 * 24 * 10)) {// 2-10天
                String formatDate = DateUtils.formatDate(oldDate, "HH:mm:ss");
                second = second / 60 / 60 / 24;// 天数
                return second + "天前 " + formatDate;
            } else {
                // 大于10天不符合条件按原格式返回
                return DateUtils.formatDate(oldDate, "yyyy-MM-dd HH:mm:ss");
            }
        } else {
            return "";
        }
    }

}
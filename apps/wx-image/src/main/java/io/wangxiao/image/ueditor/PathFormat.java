package io.wangxiao.image.ueditor;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class PathFormat {

    private static final String TIME = "time";
    private static final String FULL_YEAR = "yyyy";
    private static final String YEAR = "yy";
    private static final String MONTH = "mm";
    private static final String DAY = "dd";
    private static final String HOUR = "hh";
    private static final String MINUTE = "ii";
    private static final String SECOND = "ss";
    private static final String RAND = "rand";

    private static Date currentDate = null;

    public static String parse(String input) {

        Pattern pattern = compile("\\{([^\\}]+)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        currentDate = new Date();

        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {

            matcher.appendReplacement(sb, getString(matcher.group(1)));

        }

        matcher.appendTail(sb);

        return sb.toString();
    }

    /**
     * 格式化路径, 把windows路径替换成标准路径
     *
     * @param input 待格式化的路径
     * @return 格式化后的路径
     */
    public static String format(String input) {

        return input.replace("\\", "/");

    }

    public static String parse(String input, String filename) {

        Pattern pattern = compile("\\{([^\\}]+)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        String matchStr;

        currentDate = new Date();

        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {

            matchStr = matcher.group(1);
            if (matchStr.contains("filename")) {
                filename = filename.replace("$", "\\$").replaceAll("[\\/:*?\"<>|]", "");
                matcher.appendReplacement(sb, filename);
            } else {
                matcher.appendReplacement(sb, getString(matchStr));
            }

        }

        matcher.appendTail(sb);

        return sb.toString();
    }

    private static String getString(String pattern) {

        pattern = pattern.toLowerCase();

        // time 处理
        if (pattern.contains(TIME)) {
            return getTimestamp();
        } else if (pattern.contains(FULL_YEAR)) {
            return getFullYear();
        } else if (pattern.contains(YEAR)) {
            return getYear();
        } else if (pattern.contains(MONTH)) {
            return getMonth();
        } else if (pattern.contains(DAY)) {
            return getDay();
        } else if (pattern.contains(HOUR)) {
            return getHour();
        } else if (pattern.contains(MINUTE)) {
            return getMinute();
        } else if (pattern.contains(SECOND)) {
            return getSecond();
        } else if (pattern.contains(RAND)) {
            return getRandom(pattern);
        }

        return pattern;

    }

    private static String getTimestamp() {
        return System.currentTimeMillis() + "";
    }

    private static String getFullYear() {
        return new SimpleDateFormat("yyyy").format(currentDate);
    }

    private static String getYear() {
        return new SimpleDateFormat("yy").format(currentDate);
    }

    private static String getMonth() {
        return new SimpleDateFormat("MM").format(currentDate);
    }

    private static String getDay() {
        return new SimpleDateFormat("dd").format(currentDate);
    }

    private static String getHour() {
        return new SimpleDateFormat("HH").format(currentDate);
    }

    private static String getMinute() {
        return new SimpleDateFormat("mm").format(currentDate);
    }

    private static String getSecond() {
        return new SimpleDateFormat("ss").format(currentDate);
    }

    private static String getRandom(String pattern) {

        int length;
        pattern = ":".split(pattern)[1].trim();

        length = Integer.parseInt(pattern);

        return (Math.random() + "").replace(".", "").substring(0, length);

    }

    public static void main(String[] args) {

    }

}

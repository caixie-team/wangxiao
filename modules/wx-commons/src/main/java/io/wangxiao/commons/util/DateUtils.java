package io.wangxiao.commons.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 
 * @ClassName DateUtils
 * @description 日期格式化工具类
 */

public class DateUtils {

    /**
     * 
     *
     * @return 获取当前时间
     */
    public static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * 
     *
     * @param days
     *            间隔天数
     * @return 前后几天
     */
    public static String getTimeBeforeORAfter(int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, days);
        return sdf.format(c.getTime());
    }

    /**
     * 将一个字符串转换成日期格式
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static Date toDate(String date, String pattern) {
        if (("" + date).equals("")) {
            return null;
        }
        if (pattern == null) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date newDate = new Date();
        try {
            newDate = sdf.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return newDate;
    }

    /**
     * 把日期转换成字符串型
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String toString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (pattern == null) {
            pattern = "yyyy-MM-dd";
            ;
        }
        String dateString = "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            dateString = sdf.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dateString;
    }

    /**
     * 获取现在时间
     * 
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     * 
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     * 
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     * 
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     * 
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     * 
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     * 
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     * 
     * @param dateDate
     * @param k
     * @return
     */
    public static String dateToStr(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    public static String dateToStr(Date dateDate, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(dateDate);
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     * 
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     * 
     * @param strDate
     * @return
     */
    @SuppressWarnings("null")
    public static Date strToDate(String strDate, String patten) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (patten != null || patten.length() > 0) {
            formatter = new SimpleDateFormat(patten);
        }

        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 得到现在时间
     * 
     * @return
     */
    public static Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }

    /**
     * 得到现在时间(Str)
     * 
     * @return
     */
    public static String getCurrentDateStr() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 提取一个月中的最后一天
     * 
     * @param day
     * @return
     */
    public static Date getLastDate(long day) {
        Date date = new Date();
        long date_3_hm = date.getTime() - 3600000 * 34 * day;
        Date date_3_hm_date = new Date(date_3_hm);
        return date_3_hm_date;
    }

    /**
     * 得到现在时间
     * 
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 得到现在时间
     * 
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringTodayto() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 得到现在小时
     */
    public static String getHour() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }

    /**
     * 得到现在分钟
     * 
     * @return
     */
    public static String getTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(14, 16);
        return min;
    }

    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     * 
     * @param sformat
     *            yyyyMMddhhmmss
     * @return
     */
    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
     */
    public static String getTwoHour(String st1, String st2) {
        String[] kk = null;
        String[] jj = null;
        kk = st1.split(":");
        jj = st2.split(":");
        if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
            return "0";
        else {
            double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
            double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
            if ((y - u) > 0)
                return y - u + "";
            else
                return "0";
        }
    }

    /**
     * 得到二个日期间的间隔天数
     */
    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            java.util.Date date = myFormatter.parse(sj1);
            java.util.Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return day + "";
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    public static String getPreTime(String sj1, String jj) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            Date date1 = format.parse(sj1);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mydate1;
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的时间(精确到天)
     */
    public static String getNextDay(String nowdate, String delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = (d.getTime() / 1000) + Long.parseLong(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 得到一个时间延后或前移几天的时间,statrdate为时间,delay为前移或后延的时间(精确到秒)
     */
    public static String getNextDaytoSen(String statrdate, String delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String mdate = "";
            Date d = strToDate(statrdate, "yyyy-MM-dd HH:mm:ss");
            long myTime = (d.getTime() / 1000) + Long.parseLong(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 判断是否润年
     * 
     * @param ddate
     * @return
     */
    public static boolean isLeapYear(String ddate) {

        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
            return true;
        else if ((year % 4) == 0) {
            if ((year % 100) == 0)
                return false;
            else
                return true;
        } else
            return false;
    }

    /**
     * 返回美国时间格式 26 Apr 2006
     * 
     * @param str
     * @return
     */
    public static String getEDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
    }

    /**
     * 获取一个月的最后一天
     * 
     * @param dat
     * @return
     */
    public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
        String str = dat.substring(0, 8);
        String month = dat.substring(5, 7);
        int mon = Integer.parseInt(month);
        if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10
                || mon == 12) {
            str += "31";
        } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
            str += "30";
        } else {
            if (isLeapYear(dat)) {
                str += "29";
            } else {
                str += "28";
            }
        }
        return str;
    }

    /**
     * 判断二个时间是否在同一个周
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    /**
     * 产生周序列,即得到当前时间所在的年度是第几周
     * 
     * @return
     */
    public static String getSeqWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1)
            week = "0" + week;
        String year = Integer.toString(c.get(Calendar.YEAR));
        return year + week;
    }

    /**
     * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
     * 
     * @param sdate
     * @param num
     * @return
     */
    public static String getWeek(String sdate, String num) {
        // 再转换为时间
        Date dd = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(dd);
        if (num.equals("1")) // 返回星期一所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        else if (num.equals("2")) // 返回星期二所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        else if (num.equals("3")) // 返回星期三所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        else if (num.equals("4")) // 返回星期四所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        else if (num.equals("5")) // 返回星期五所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        else if (num.equals("6")) // 返回星期六所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        else if (num.equals("0")) // 返回星期日所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     * 
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate) {
        // 再转换为时间
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    /**
     * 返回星期几
     * 
     * @param sdate
     * @return
     */
    public static String getWeekStr(String sdate) {
        String str = "";
        str = getWeek(sdate);
        if ("1".equals(str)) {
            str = "星期日";
        } else if ("2".equals(str)) {
            str = "星期一";
        } else if ("3".equals(str)) {
            str = "星期二";
        } else if ("4".equals(str)) {
            str = "星期三";
        } else if ("5".equals(str)) {
            str = "星期四";
        } else if ("6".equals(str)) {
            str = "星期五";
        } else if ("7".equals(str)) {
            str = "星期六";
        }
        return str;
    }

    /**
     * 两个时间之间的天数
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        java.util.Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
     * 此函数返回该日历第一行星期日所在的日期
     * 
     * @param sdate
     * @return
     */
    public static String getNowMonth(String sdate) {
        // 取该时间所在月的一号
        sdate = sdate.substring(0, 8) + "01";

        // 得到这个月的1号是星期几
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int u = c.get(Calendar.DAY_OF_WEEK);
        String newday = getNextDay(sdate, (1 - u) + "");
        return newday;
    }

    // 获取相隔月数
    static public long getDistinceMonth(String beforedate, String afterdate)
            throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        long monthCount = 0;
        try {
            java.util.Date d1 = d.parse(beforedate);
            java.util.Date d2 = d.parse(afterdate);

            monthCount = (getYear(d2) - getYear(d1)) * 12 + getMonth(d2) - getMonth(d1);
            // dayCount = (d2.getTime()-d1.getTime())/(30*24*60*60*1000);

        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Date parse error!");
            // throw e;
        }
        return monthCount;
    }

    // 获取相隔天数
    public static long getDistinceDay(Date beforedate, Date afterdate) {
        long dayCount = 0;
        dayCount = (beforedate.getTime() - afterdate.getTime()) / (24 * 60 * 60 * 1000);
        return dayCount;
    }

    // 获取相隔天数
    static public long getDistinceDay(String beforedate, String afterdate)
            throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        long dayCount = 0;
        try {
            java.util.Date d1 = d.parse(beforedate);
            java.util.Date d2 = d.parse(afterdate);

            dayCount = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);

        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Date parse error!");
            // throw e;
        }
        return dayCount;
    }

    /**
     * 计算返回时间数 eg：几小时 几分 几秒
     * 
     * @param date1
     *            当前时间
     * @param date2
     *            指定时间
     * @return
     */
    static public String disTime(Date date1, Date date2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return disTime(df.format(date1), df.format(date2));
    }

    static public int disDay(String date1, String date2) {
        int day = 0;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date now = df.parse(date1);
            java.util.Date date = df.parse(date2);
            long l = now.getTime() - date.getTime();
            day = (int) (l / (24 * 60 * 60 * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    public static double disDateTime(String date1, String date2) {
        double la = 0;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date now = df.parse(date1);
            java.util.Date date = df.parse(date2);
            long l = now.getTime() - date.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            double min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            double s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            la = day * 24 + hour + min / 60 + s / 3600;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return la;
    }

    static public String disTime(String date1, String date2) {
        StringBuffer sb = new StringBuffer();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date now = df.parse(date1);
            java.util.Date date = df.parse(date2);
            long l = now.getTime() - date.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            // long s=(l/1000-day*24*60*60-hour*60*60-min*60);

            if (day <= 0 && hour <= 0 && min <= 0) {
                return "1";
            }
            if (day > 0) {
                sb.append(day + "天");
            }
            if (hour > 0) {
                sb.append(hour + "小时");
            }
            if (min > 0) {
                sb.append(min + "");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static Date getFirstDateOfMonth(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date date = c.getTime();
        return date;
    }

    /**
     * add by wh.cheng at 2011-5-6
     */
    /**
     * 字符串日期格式按照日期模式转换成日期
     * 
     * @param sDate
     *            -- 字符串的日期
     * @param pattern
     *            -- 日期格式 （比如：yyyy-MM-dd）
     * @return
     * @throws ParseException
     */

    /**
     * 格式化日期，返回字符串内容 例如："yyyy-MM-dd HH:mm:ss"
     * 
     * @param d
     * @param pattern
     * @return
     */
    public static String formatDate(Date d, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(d);
    }

    public static Date parseToDate(String sDate, String pattern) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.parse(sDate);
    }

    public static Date getMinTime(Date dt) {
        Date dt1 = null;
        try {
            dt1 = DateUtils.parseToDate(DateUtils.formatDate(dt, "yyyyMMdd"), "yyyyMMdd");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("date formate error ：" + dt + ".   " + e.getMessage());
        }
        return dt1;
    }

    public static Date getMaxTime(Date dt) {
        Date dt1 = null;
        Calendar ca = Calendar.getInstance();
        ca.setTime(dt);
        ca.add(Calendar.DAY_OF_MONTH, 1);
        dt1 = ca.getTime();
        dt1 = DateUtils.getMinTime(dt1);
        ca.setTime(dt1);
        ca.add(Calendar.SECOND, -1);
        dt1 = ca.getTime();
        return dt1;
    }

    /**
     * java date to sql timestamp
     * 
     * @param date
     * @return
     */
    public static Timestamp parseToTimestamp(Date date) {

        return new Timestamp(date.getTime());
    }

    /**
     * 格式化日期，返回字符串内容 例如："yyyy-MM-dd HH:mm:ss"
     * 
     * @param d
     * @param pattern
     * @return
     */
    public static String formatDate(Timestamp d, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(d);
    }

    public static boolean isValidKey(String createKeyDate, long expire_time) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 当前时间
        Date now = new Date();
        try {
            Date createTime = dateformat.parse(createKeyDate);
            Date currentTime = dateformat.parse(dateformat.format(now));
            long time = (currentTime.getTime() - createTime.getTime()) / 1000;
            return time > expire_time ? true : false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 计算过期时间.购买日期+过期绝对日与过期相对日比较，小者生效
     */
    public static Date compareDateInNull(Date createDate, String absDays,
            String sourceDate2) {

        String loseReadyDateStr = DateUtils.getNextDay(
                formatDate(createDate, "yyyy-MM-dd HH:mm:ss"), absDays.toString());
        Date loseReadyDate = toDate(loseReadyDateStr, "yyyy-MM-dd HH:mm:ss");
        loseReadyDate = getMaxTime(loseReadyDate);

        if ("null".equals(sourceDate2) && "0".equals(absDays)) {

            return getMaxTime(new Date());

        } else if ("null".equals(sourceDate2)) {

            return loseReadyDate;
        } else if ("0".equals(absDays)) {
            Date sourceDate = getMaxTime(toDate(sourceDate2, "yyyy-MM-dd HH:mm:ss"));
            return sourceDate;
        } else {
            Date sourceDate = getMaxTime(toDate(sourceDate2, "yyyy-MM-dd HH:mm:ss"));
            if (loseReadyDate.after(sourceDate)) {// loseReadyDate

                return sourceDate;
            } else {

                return loseReadyDate;
            }
        }

    }

    /**
     * 当前时间与传入时间比较前后
     * 
     * @param loseDate
     * @return
     */
    public static boolean loseDate(Date loseDate) {
        Date nows = new Date();
        long hous = (nows.getTime() - loseDate.getTime()) / (1000);
        System.out.println(nows.getTime() - loseDate.getTime());
        System.out.println(hous);
        if (hous > 0) {
            return true;
        }
        return false;
    }

    public static boolean betweenBeginAndEnd(Date paramDate, Date beginDate, Date endDate) {
        long bHous = (paramDate.getTime() - beginDate.getTime()) / (1000);
        long eHous = (endDate.getTime() - paramDate.getTime()) / (1000);
        if (bHous > 0 && eHous > 0) {
            return true;
        }
        return false;
    }

    public static Date compareDate(Date createDate, String absDays, Date sourceDate2) {
        sourceDate2 = getMaxTime(sourceDate2);
        String loseReadyDateStr = DateUtils.getNextDay(
                formatDate(createDate, "yyyy-MM-dd HH:mm:ss"), absDays.toString());

        Date loseReadyDate = toDate(loseReadyDateStr, "yyyy-MM-dd HH:mm:ss");
        loseReadyDate = getMaxTime(loseReadyDate);
        if (loseReadyDate.after(sourceDate2)) {// loseReadyDate
            // 在sourceDate2之后，即为前比后晚
            return sourceDate2;
        }

        return loseReadyDate;
    }

    /**
     * 按照standFormat格式格式化时间,day为当前时间向前活向后推几天的数据
     * 
     * @param day
     * @param standFormat
     * @return
     */
    public static String formatTime(int day, String standFormat) {
        String date = null;
        Calendar c = Calendar.getInstance();// 获得系统当前日期
        c.add(Calendar.DATE, day);
        DateFormat format = new SimpleDateFormat(standFormat);
        date = format.format(c.getTime());
        return date;
    }

    /**
     * 
     * @param days
     * @param pattern
     * @return
     */
    public static String getTimeBeforeORAfter(int days, String pattern) {
        if (pattern == null) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, days);
        return sdf.format(c.getTime());
    }

    /**
     * 
     * liuyidao
     * 
     * @param tocompary
     * @return 今天之前
     * @throws ParseException
     * 
     */
    public static boolean isAfterToday(String tocompareStr) throws ParseException {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date tocompareDate = sdf.parse(tocompareStr);
        return tocompareDate.after(Calendar.getInstance().getTime());
    }

    /**
     * 计算两个时间戳相差秒数，大的时间戳-小的时间戳
     * 
     * @param t1
     * @param t2
     * @return
     * @author qiaowb
     */
    public static Long countDifSeconds(Timestamp t1, Timestamp t2) {
        if (t1 == null || t2 == null) {
            return 0l;
        } else {
            if (t1.compareTo(t2) >= 0) {
                return (t1.getTime() - t2.getTime()) / 1000;
            } else {
                return (t2.getTime() - t1.getTime()) / 1000;
            }
        }
    }

    /**
     * 返回当前日期
     * 
     * @param d
     * @return
     */
    public static int getDateOfMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取时间戳前/后N秒的时间，返回时间格式yyyy-MM-dd HH:mm:ss字符串
     * 
     * @param sourceTime
     * @param seconds
     * @return yyyy-MM-dd HH:mm:ss
     * @author qiaowb
     */
    public static String getTimeBeforeOrAfterSenconds(Timestamp sourceTime, long seconds) {
        if (sourceTime != null) {
            long time = sourceTime.getTime() + seconds * 1000;
            sourceTime.setTime(time);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(sourceTime);
        } else {
            return "";
        }
    }

    /**
     * 
     * @param days
     * @param pattern
     * @return
     */
    public static String getTimeBeforeOrAfterMonth(int months, String pattern,
            Date sourceTime) {
        if (pattern == null) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        c.setTime(sourceTime);

        c.add(Calendar.MONTH, months);
        return sdf.format(c.getTime());
    }

    public static void main(String[] args) {
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.MONTH, -1);// 减一个月
        lastDate.add(Calendar.DAY_OF_MONTH, 5);
        Date date = lastDate.getTime();
        long s = DateUtils.getDistinceDay(new Date(), date);
        System.out.println(s);
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

}

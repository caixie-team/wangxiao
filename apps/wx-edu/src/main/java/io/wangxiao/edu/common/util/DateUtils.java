package io.wangxiao.edu.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Description:日期工具类
 *
 */
public class DateUtils {

    /**
     * 格式化日期
     */
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 获取日期
     */
    private static final Calendar calendar = Calendar.getInstance();

    /**
     * 根据天数获取日期
     * @param day 天数
     * @return 日期字符串数组
     * @throws ParseException
     */
    public static String[] getLastDays(int day) throws ParseException{
        if(day<=0){
            return null;
        }
        Date now = new Date();
        calendar.setTime(now);
        calendar.add(Calendar.DATE,-(day-1));
        return getDaysBetween(calendar.getTime(),now);
    }

    /**
     * 获取日期
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 日期字符串数组
     */
    public static String[] getDaysBetween(Date startDate,Date endDate) throws ParseException{
        // 日期转换
        startDate = simpleDateFormat.parse(simpleDateFormat.format(startDate));
        endDate = simpleDateFormat.parse(simpleDateFormat.format(endDate));
        // 开始时间不能等于结束时间
        if(startDate.getTime()>=endDate.getTime()){
            return null;
        }
        List<String> list = new ArrayList<>();
        // 设置开始时间
        calendar.setTime(startDate);
        while(true){
            list.add(simpleDateFormat.format(calendar.getTime()));
            if(calendar.getTime().getTime()>=endDate.getTime()){
                break;
            }
            calendar.add(Calendar.DATE,1);
        }
        return list.toArray(new String[list.size()]);
    }
}

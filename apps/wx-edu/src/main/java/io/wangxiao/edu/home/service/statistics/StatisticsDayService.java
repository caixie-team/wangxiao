package io.wangxiao.edu.home.service.statistics;


import java.util.Date;
import java.util.List;
import java.util.Map;

import io.wangxiao.edu.home.entity.statistics.StatisticsDay;


/**
 * StatisticsDay管理接口
 */
public interface StatisticsDayService {

    /**
     * 定时添加StatisticsDay
     *
     * @param date
     * @return
     */
    void addStatisticsDayAuto();


    /**
     * 网站统计 （按年、月）
     *
     * @return
     * @throws Exception
     */
    Map<String, Object> getStatisticsMsg(String month, String year);

    /**
     * 网站统计 （总记录）
     *
     * @return
     * @throws Exception
     */
    StatisticsDay getStatisticsSumMsg();

    /**
     * 查询最近30条的统计数据
     *
     * @param date
     */
    List<StatisticsDay> getStatisticThirty(int days);

    /**
     * 查询指定时间段的统计数据
     *
     * @param date
     */
    List<StatisticsDay> getStatisticsByDate(String startTime, String endTime);

    /**
     * 删除指定时间段的统计数据
     *
     * @param date
     */
    void delStatisticsByDate(String startTime, String endTime);

    /**
     * 生成指定时间段的统计数据
     *
     * @param date
     */
    void createStatisticsByDate(Date startTime, Date endTime);

    /**
     * 获取日期的登录人数
     *
     * @param date
     * @return
     */
    int getTodayLoginNum(Date date);

    /**
     * 根据类型获取日期的统计数
     *
     * @param date
     * @param type
     * @return
     */
    int getStatisticalNumByType(Date date, String type);

    /**
     * 获取总课时
     *
     * @return
     */
    int getStatisticsKpoint();

    String getVerifySnsKeyword();
}
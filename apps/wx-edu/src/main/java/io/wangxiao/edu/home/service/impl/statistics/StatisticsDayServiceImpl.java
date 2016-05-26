package io.wangxiao.edu.home.service.impl.statistics;


import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.common.service.SnsHessianService;
import io.wangxiao.edu.common.util.StatisticsUtil;
import io.wangxiao.edu.home.constants.enums.StatisticsQueryType;
import io.wangxiao.edu.home.constants.enums.UserExpandFrom;
import io.wangxiao.edu.home.constants.enums.WebSiteProfileType;
import io.wangxiao.edu.home.dao.statistics.StatisticsDayDao;
import io.wangxiao.edu.home.dao.user.UserDao;
import io.wangxiao.edu.home.entity.statistics.StatisticsDay;
import io.wangxiao.edu.home.entity.user.UserFromStatistics;
import io.wangxiao.edu.home.service.statistics.StatisticsDayService;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * StatisticsDayDay管理接口
 */
@Service("statisticsDayService")
public class StatisticsDayServiceImpl implements StatisticsDayService {
    CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private StatisticsDayDao statisticsDayDao;
    @Autowired
    private SnsHessianService snsHessianService;
    // 注入 websiteProfileService 接口
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private UserDao userDao;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    @Getter
    @Setter
    private Map<String, Object> userMsg = new HashMap<String, Object>();

    /**
     * 定时添加StatisticsDayDay
     *
     * @return
     */
    public void addStatisticsDayAuto() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        //每天定时统计前一天的数据，天数减1
        c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
        Date date = c.getTime();
        //先删除再生成，防止数据重复
        statisticsDayDao.delStatisticsDay(date);
        //先添加网校统计
        statisticsDayDao.addStatisticsDay(date);
        // 更新社区统计(如果需要包含社区统计)
        if (getVerifySnsKeyword().equalsIgnoreCase("on")) {
            //更新社区统计
            List<Date> dates = new ArrayList<Date>();
            dates.add(date);
            List<StatisticsDay> statisticsDays = snsHessianService.getSnsStatistics(dates);
            statisticsDayDao.updateStatisticsDay(statisticsDays.get(0));
        }

        cacheKit.remove(MemConstans.WEB_STATISTICS);
        cacheKit.remove(MemConstans.WEB_STATISTICS_THIRTY + 7);
        cacheKit.remove(MemConstans.WEB_STATISTICS_THIRTY + 15);
        cacheKit.remove(MemConstans.WEB_STATISTICS_THIRTY + 30);
    }

    /**
     * 查询网站统计
     */
    public Map<String, Object> getStatisticsMsg(String month, String year) {

        if (month != null && month != "") {
            getStatisticsDayByMonth(month, year);// 按月查
        } else if (year != null) {
            getStatisticsDayByYear(year);//按年查
        }
        return userMsg;
    }

    /**
     * 按月查询网站统计
     *
     * @param month
     * @return
     */
    public void getStatisticsDayByMonth(String month, String year) {
        String showDate = "";
        List<StatisticsDay> statisticsDayList = statisticsDayDao.getStatisticsByMonth(month, year);
        for (int i = 0; i < statisticsDayList.size(); i++) {
            showDate += (i + 1) + ",";
        }
        showDate = showDate.substring(0, showDate.length() - 1);
        userMsg.put("showDate", showDate);
        userMsg.put("statisticsDayList", statisticsDayList);
    }

    /**
     * 按年查询网站统计
     *
     * @param year
     * @return
     */
    public void getStatisticsDayByYear(String year) {
        List<StatisticsDay> statisticsList = statisticsDayDao.getStatisticsByYear(year);
        String showDate = "01,02,03,04,05,06,07,08,09,10,11,12";
        // 数据拼凑
        userMsg.put("showDate", showDate);
        userMsg.put("statisticsDayList", statisticsList);
    }

    /**
     * 网站统计 （总记录）
     *
     * @return
     * @throws Exception
     */
    public StatisticsDay getStatisticsSumMsg() {
        StatisticsDay statisticsDay = (StatisticsDay) cacheKit.get(MemConstans.WEB_STATISTICS);
        if (statisticsDay != null) {
            return statisticsDay;
        }
        statisticsDay = statisticsDayDao.getStatisticsSumMsg();
        if (statisticsDay != null) {
            cacheKit.set(MemConstans.WEB_STATISTICS, statisticsDay);
        }
        return statisticsDay;
    }

    /**
     * 查询指定时间段的统计数据
     *
     * @param startTime
     * @param endTime
     */
    public List<StatisticsDay> getStatisticsByDate(String startTime, String endTime) {

        UserFromStatistics userFromStatistics = userDao.getUserFromStatistics();
        // 注册用户
        StatisticsUtil.setActionOneDayOrActionCount(MemConstans.REGISTER_NUM, UserExpandFrom.registerFrom.toString(), userFromStatistics.getRegisterNum());
        // 微站注册用户
        StatisticsUtil.setActionOneDayOrActionCount(MemConstans.REGISTER_NUM, UserExpandFrom.mobileFrom.toString(), userFromStatistics.getMobileNum());
        // 后台批量开通用户
        StatisticsUtil.setActionOneDayOrActionCount(MemConstans.REGISTER_NUM, UserExpandFrom.adminFrom.toString(), userFromStatistics.getAdminNum());
        // 课程卡用户
        StatisticsUtil.setActionOneDayOrActionCount(MemConstans.REGISTER_NUM, UserExpandFrom.userCardFrom.toString(), userFromStatistics.getUserCardNum());
        // app注册用户
        StatisticsUtil.setActionOneDayOrActionCount(MemConstans.REGISTER_NUM, UserExpandFrom.appFrom.toString(), userFromStatistics.getAppNum());
        return statisticsDayDao.getStatisticsByDate(startTime, endTime);
    }

    /**
     * 动态查询活跃度
     *
     * @param days
     */
    @SuppressWarnings("unchecked")
    public List<StatisticsDay> getStatisticThirty(int days) {

        List<StatisticsDay> statistics = (List<StatisticsDay>) cacheKit.get(MemConstans.WEB_STATISTICS_THIRTY + days);
        if (statistics != null && statistics.size() > 0) {
            return statistics;
        }
        statistics = statisticsDayDao.getStatisticThirty(days);
        if (statistics != null) {
            cacheKit.set(MemConstans.WEB_STATISTICS_THIRTY + days, statistics, MemConstans.WEB_STATISTICS_TIME);
        }
        return statistics;
    }

    /**
     * 删除指定时间段的统计数据
     *
     * @param startTime
     * @param endTime
     */
    public void delStatisticsByDate(String startTime, String endTime) {
        statisticsDayDao.delStatisticsByDate(startTime, endTime);
        cacheKit.remove(MemConstans.WEB_STATISTICS);
        cacheKit.remove(MemConstans.WEB_STATISTICS_THIRTY + 7);
        cacheKit.remove(MemConstans.WEB_STATISTICS_THIRTY + 15);
        cacheKit.remove(MemConstans.WEB_STATISTICS_THIRTY + 30);
    }

    /**
     * 生成指定时间段的统计数据
     *
     * @param startDate
     * @param endDate
     */
    public void createStatisticsByDate(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        List<Date> dates = new ArrayList<Date>();
        dates.add(startDate);
        // 使用给定的 Date 设置此 Calendar 的时间   
        cal.setTime(startDate);
        while (true) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量   
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后   
            if (endDate.after(cal.getTime())) {
                dates.add(cal.getTime());
            } else {
                break;
            }
        }
        if (dates.get(0).getTime() != endDate.getTime()) {//首尾日起相同，只添加一条
            dates.add(endDate);
        }
        //批量添加网校统计
        statisticsDayDao.addStatisticsDayBatch(dates);
        
/*        if (getVerifySnsKeyword().equalsIgnoreCase("on")) {
            //批量更新社区统计
        	List<StatisticsDay> statisticsDays = snsHessianService.getSnsStatistics(dates);        	
			statisticsDayDao.updateStatisticsDay(statisticsDays.get(0));
		}*/

        cacheKit.remove(MemConstans.WEB_STATISTICS);
        cacheKit.remove(MemConstans.WEB_STATISTICS_THIRTY + 7);
        cacheKit.remove(MemConstans.WEB_STATISTICS_THIRTY + 15);
        cacheKit.remove(MemConstans.WEB_STATISTICS_THIRTY + 30);
    }

    /**
     * 获取日期的登录人数
     *
     * @param date
     * @return
     */
    public int getTodayLoginNum(Date date) {
        //先从redis中获取
        int count = StatisticsUtil.uniqueCount(StatisticsQueryType.LOGIN_NUM.toString(), sdf.format(date));
        if (count > 0) {
            return count;
        }
        return statisticsDayDao.getTodayLoginNum(date);
    }

    /**
     * 根据类型获取日期的统计数
     */
    public int getStatisticalNumByType(Date date, String type) {
        //先从redis中获取
        Long count = StatisticsUtil.getActionOneDayOrActionCount(type, sdf.format(date));
        if (count != null && count > 0) {
            return count.intValue();
        }
        return statisticsDayDao.getStatisticalNumByType(date, type);
    }

    /**
     * 获取总课时
     */
    public int getStatisticsKpoint() {
        Long count = StatisticsUtil.getActionAllCount(StatisticsQueryType.KPOINT_NUM.toString());
        if (count != null && count > 0) {
            return count.intValue();
        }
        return statisticsDayDao.getStatisticsKpoint();
    }

    /**
     * 获得社区开关是否开启
     *
     * @return
     */
    public String getVerifySnsKeyword() {
        Map<String, Object> keywordmap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) keywordmap.get(WebSiteProfileType.keyword.toString());
        return map.get("verifySns").toString();
    }

}
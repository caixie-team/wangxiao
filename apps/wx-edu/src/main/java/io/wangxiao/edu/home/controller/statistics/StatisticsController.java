package io.wangxiao.edu.home.controller.statistics;

import io.wangxiao.commons.controller.BaseController;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.common.util.DateUtils;
import io.wangxiao.edu.home.constants.enums.StatisticsQueryType;
import io.wangxiao.edu.home.entity.statistic.UserCourseKpointStatistic;
import io.wangxiao.edu.home.entity.statistics.StatisticsDay;
import io.wangxiao.edu.home.service.statistic.UserCourseKpointStatisticService;
import io.wangxiao.edu.home.service.statistics.StatisticsDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description:前台数据统计查询
 */
@Controller
public class StatisticsController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    private StatisticsDayService statisticsDayService;
    @Autowired
    private UserCourseKpointStatisticService userCourseKpointStatisticService;

    /**
     * 最近统计查询
     *
     * @param request
     * @param days
     * @param type
     * @return
     */
    @RequestMapping("/statistics/web/detailajax")
    @ResponseBody
    public Map<String, Object> getWebStatisticsAjax(HttpServletRequest request, @RequestParam int days, @RequestParam String type) {
        Map<String, Object> json = null;
        try {
            if (days > 0) {// 历史的
                List<StatisticsDay> loginStatistics = statisticsDayService.getStatisticThirty(days);
                String statisticalNum = "";// 登录数--统计数（包括三中）
                for (int i = loginStatistics.size() - 1; i >= 0; i--) {
                    if (type.equals(StatisticsQueryType.LOGIN_NUM.toString())) {
                        statisticalNum += loginStatistics.get(i).getLoginNum() + ",";
                    } else if (type.equals(StatisticsQueryType.REGISTER_NUM.toString())) {
                        statisticalNum += loginStatistics.get(i).getRegisterNum() + ",";
                    } else if (type.equals(StatisticsQueryType.COURSE_NUM.toString())) {
                        statisticalNum += loginStatistics.get(i).getCourseNum() + ",";
                    }
                }
                if (statisticalNum.length() > 0) {
                    statisticalNum = statisticalNum.substring(0, statisticalNum.length() - 1);
                }
                String[] lastDays = DateUtils.getLastDays(days);
                String[] _lastDays = new String[lastDays.length];
                for (int i = 0; i < lastDays.length; i++) {
                    /*String dayAndMonth = lastDays[i].substring(5);
                    String day = lastDays[i].substring(8);
					if("01-01".equals(dayAndMonth)){
						_lastDays[i] = lastDays[i];
					} else if("01".equals(day)){
						_lastDays[i] = dayAndMonth;
					}else{
						_lastDays[i] = day;
					}*/
                    String day = lastDays[i].substring(8);
                    _lastDays[i] = day;
                }
                json = this.getJsonMap(true, statisticalNum, _lastDays);
            } else {// 今天的

            }

        } catch (Exception e) {
            logger.error("StatisticsDayController.getWebStatisticsAjax" + e);
            json = this.getJsonMap(false, "", null);
        }
        return json;
    }

    /**
     * 获取课程播放记录
     *
     * @param days
     * @return
     */
    @RequestMapping("/statistics/web/getStatisticListByDays")
    @ResponseBody
    public Map<String, Object> getStatisticListByDays(@RequestParam("days") Integer days) {
        Map<String, Object> json = null;
        try {
            List<UserCourseKpointStatistic> statisticList = userCourseKpointStatisticService.getStatisticListByDays(days);
            String dataTime = "";
            String playCount = "";
            for (int i = statisticList.size() - 1; i >= 0; i--) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd");
                Date date = statisticList.get(i).getStatisticDate();
                dataTime += sdf.format(date) + ",";
                playCount += statisticList.get(i).getPlayCount() + ",";
            }
            if (StringUtils.isNotEmpty(dataTime) && StringUtils.isNotEmpty(playCount)) {
                dataTime = dataTime.substring(0, dataTime.length() - 1);
                playCount = playCount.substring(0, playCount.length() - 1);
            }
            json = getJsonMap(true, playCount, dataTime);
        } catch (Exception e) {
            logger.error("getStatisticListByCount", e);
            json = getJsonMap(false, "error", false);
        }
        return json;
    }
}

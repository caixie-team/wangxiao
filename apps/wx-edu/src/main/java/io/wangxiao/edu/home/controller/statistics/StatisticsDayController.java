package io.wangxiao.edu.home.controller.statistics;

import com.google.gson.JsonObject;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.common.util.DateUtils;
import io.wangxiao.edu.common.util.StatisticsUtil;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.StatisticsQueryType;
import io.wangxiao.edu.home.constants.enums.UserExpandFrom;
import io.wangxiao.edu.home.constants.enums.WebSiteProfileType;
import io.wangxiao.edu.home.entity.course.*;
import io.wangxiao.edu.home.entity.statistic.UserCourseKpointStatistic;
import io.wangxiao.edu.home.entity.statistics.StatisticsDay;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.home.entity.user.UserFromStatistics;
import io.wangxiao.edu.home.entity.user.UserGroup;
import io.wangxiao.edu.home.service.course.CourseKpointService;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.course.CourseStudyhistoryService;
import io.wangxiao.edu.home.service.statistic.UserCourseKpointStatisticService;
import io.wangxiao.edu.home.service.statistics.StatisticsDayService;
import io.wangxiao.edu.home.service.user.UserGroupService;
import io.wangxiao.edu.home.service.user.UserService;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import io.wangxiao.edu.sysuser.entity.QuerySubject;
import io.wangxiao.edu.sysuser.entity.Subject;
import io.wangxiao.edu.sysuser.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class StatisticsDayController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(StatisticsDayController.class);
    @Autowired
    private StatisticsDayService statisticsDayService;
    // 注入 WebsiteProfileService 接口
    @Autowired
    private WebsiteProfileService websiteProfileService;

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseKpointService courseKpointService;
    @Autowired
    private CourseStudyhistoryService courseStudyhistoryService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private UserCourseKpointStatisticService userCourseKpointStatisticService;

    CacheKit cacheKit = CacheKit.getInstance();

    private static final String registerChart = getViewPath("/admin/statistics/statistics_register");// 用户注册统计图
    private static final String registerFromChart = getViewPath("/admin/statistics/statistics_register_from");// 用户注册来源统计图
    private static final String ajaxRegisterFrom = getViewPath("/admin/statistics/ajax_register_from");


    private static final String loginChart = getViewPath("/admin/statistics/statistics_login");// 用户登录统计图
    private static final String snsChart = getViewPath("/admin/statistics/statistics_sns");// 社区活跃统计图
    private static final String courseOrderChart = getViewPath("/admin/statistics/statistics_trxorder");// 课程订单统计图
    private static final String webChart = getViewPath("/admin/statistics/statistics_web_detail");// 网站概况饼状图

    private static final String toUserList = getViewPath("/admin/statistics/statistics_user_list");//学员列表
    private static final String toUserCourseList = getViewPath("/admin/statistics/statistics_user_course_list");// 学员学习课程列表
    private static final String studyHistory = getViewPath("/admin/statistics/statistics_study_history");//课程学习详情

    private static final String toCourseList = getViewPath("/admin/statistics/statistics_course_list");// 课程记录列表
    private static final String toCourseKpointList = getViewPath("/admin/statistics/statistics_course_kpoint_list");//课程小节

    private static final String toGroupList = getViewPath("/admin/statistics/statistics_group_list");// 部门学习记录
    private static final String toGroupUserList = getViewPath("/admin/statistics/statistics_group_user_list");// 部门学员列表

    @InitBinder({"user"})
    public void initBinderUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    @InitBinder({"queryCourse"})
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }

    @InitBinder({"courseKpoint"})
    public void initBinderCourseKpoint(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("courseKpoint.");
    }

    @InitBinder({"courseStudyhistory"})
    public void initBinderStudyhistory(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("courseStudyhistory.");
    }

    @InitBinder({"userGroup"})
    public void initBinderUserGroup(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userGroup.");
    }

    /**
     * 会员记录列表
     *
     * @return
     */
    @RequestMapping("/StatisticsDay/StatisticsDays")
    public String getStatisticsDays(HttpServletRequest request) {

        try {
            statisticsDayService.addStatisticsDayAuto();
        } catch (Exception e) {
            logger.error("AdminMemberController.getStatisticsDays--会员记录列表出错", e);
            return setExceptionRequest(request, e);
        }
        return "";
    }

    /**
     * 注册统计信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/statistics/register")
    public ModelAndView getRegisterStatistics(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(registerChart);
        try {
            SimpleDateFormat s = new SimpleDateFormat("yyyy");
            String month = request.getParameter("month");// 月份
            String year = request.getParameter("year");// 年
            if (year == null) {
                year = s.format(new Date());
            }
            Map<String, Object> sourceMap = statisticsDayService.getStatisticsMsg(month, year);
            @SuppressWarnings("unchecked")
            List<StatisticsDay> statistics = (List<StatisticsDay>) sourceMap.get("statisticsDayList");
            String showDate = (String) sourceMap.get("showDate");// 统计js的X轴的日期显示
            String registerNum = "";// 注册数
            for (int i = statistics.size() - 1; i >= 0; i--) {
                registerNum += statistics.get(i).getRegisterNum() + ",";
            }
            if (registerNum.length() > 0) {
                registerNum = registerNum.substring(0, registerNum.length() - 1);
            }

            modelAndView.addObject("chart", showDate + "|" + registerNum);// 绘图数据
            modelAndView.addObject("statistics", statistics);// 表数据
            modelAndView.addObject("month", month);
            modelAndView.addObject("year", year);
        } catch (Exception e) {
            logger.error("StatisticsDayController.getRegisterStatistics" + e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 注册来源统计信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/statistics/registerFrom")
    public ModelAndView getRegisterFromStatistics(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(registerFromChart);
        try {
            UserFromStatistics userFromStatistics = null;
            // 后台开通用户数量
            Long adminNum = StatisticsUtil.getActionOneDayOrActionCount(MemConstans.MEMFIX, UserExpandFrom.adminFrom.toString());
            // app注册用户数量
            Long appNum = StatisticsUtil.getActionOneDayOrActionCount(MemConstans.MEMFIX, UserExpandFrom.appFrom.toString());
            // 注册用户数量
            Long registerNum = StatisticsUtil.getActionOneDayOrActionCount(MemConstans.MEMFIX, UserExpandFrom.registerFrom.toString());
            // 课程卡用户数量
            Long userCardNum = StatisticsUtil.getActionOneDayOrActionCount(MemConstans.MEMFIX, UserExpandFrom.userCardFrom.toString());
            // 微站注册用户数量
            Long mobileNum = StatisticsUtil.getActionOneDayOrActionCount(MemConstans.MEMFIX, UserExpandFrom.mobileFrom.toString());
            // 如果注册数量都为空，则查询一次
            if (ObjectUtils.isNull(adminNum) && ObjectUtils.isNull(appNum) && ObjectUtils.isNull(registerNum) &&
                    ObjectUtils.isNull(userCardNum) && ObjectUtils.isNull(mobileNum)) {
                userFromStatistics = userService.getUserFromStatistics();
                // 后台开通用户数量
                adminNum = userFromStatistics.getAdminNum();
                StatisticsUtil.setActionOneDayOrActionCount(MemConstans.MEMFIX, UserExpandFrom.adminFrom.toString(), adminNum);
                // app注册用户数量
                appNum = userFromStatistics.getAppNum();
                StatisticsUtil.setActionOneDayOrActionCount(MemConstans.MEMFIX, UserExpandFrom.appFrom.toString(), appNum);
                // 注册用户数量
                registerNum = userFromStatistics.getRegisterNum();
                StatisticsUtil.setActionOneDayOrActionCount(MemConstans.MEMFIX, UserExpandFrom.registerFrom.toString(), registerNum);
                // 课程卡用户数量
                userCardNum = userFromStatistics.getUserCardNum();
                StatisticsUtil.setActionOneDayOrActionCount(MemConstans.MEMFIX, UserExpandFrom.userCardFrom.toString(), userCardNum);
                // 微站注册用户数量
                mobileNum = userFromStatistics.getMobileNum();
                StatisticsUtil.setActionOneDayOrActionCount(MemConstans.MEMFIX, UserExpandFrom.mobileFrom.toString(), mobileNum);
            } else {
                userFromStatistics = new UserFromStatistics();
                userFromStatistics.setAdminNum(adminNum);
                userFromStatistics.setAppNum(appNum);
                userFromStatistics.setMobileNum(mobileNum);
                userFromStatistics.setRegisterNum(registerNum);
                userFromStatistics.setUserCardNum(userCardNum);
            }
            // 获取注册来源饼状图
            modelAndView.addObject("userFromStatistics", userFromStatistics);
        } catch (Exception e) {
            logger.error("StatisticsDayController.getRegisterFromStatistics" + e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * ajax获取注册来源统计表
     *
     * @param request
     * @return
     */
    @RequestMapping("/ajax/ajaxRegisterFromTable")
    public String ajaxRegisterFromTable(HttpServletRequest request) {
        try {
            SimpleDateFormat s = new SimpleDateFormat("yyyy");
            String month = request.getParameter("month");// 月份
            String year = request.getParameter("year");// 年
            if (year == null) {
                year = s.format(new Date());
            }
            Map<String, Object> sourceMap = userService.getRegisterFromMsg(month, year);
            List<UserFromStatistics> statistics = (List<UserFromStatistics>) sourceMap.get("registerFromList");
            request.setAttribute("statistics", statistics);
            request.setAttribute("month", month);
            request.setAttribute("year", year);
        } catch (Exception e) {
            logger.error("StatisticsDayController.ajaxRegisterFromTable" + e);
        }
        return ajaxRegisterFrom;
    }

    /**
     * 登录统计（活跃人数）信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/statistics/login")
    public ModelAndView getLoginStatistics(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(loginChart);
        try {
            SimpleDateFormat s = new SimpleDateFormat("yyyy");
            String month = request.getParameter("month");// 月份
            String year = request.getParameter("year");// 年
            if (year == null) {
                year = s.format(new Date());
            }
            Map<String, Object> sourceMap = statisticsDayService.getStatisticsMsg(month, year);
            @SuppressWarnings("unchecked")
            List<StatisticsDay> statistics = (List<StatisticsDay>) sourceMap.get("statisticsDayList");
            String showDate = (String) sourceMap.get("showDate");// 统计js的X轴的日期显示
            String loginNum = "";// 登录数
            for (int i = statistics.size() - 1; i >= 0; i--) {
                loginNum += statistics.get(i).getLoginNum() + ",";
            }
            if (loginNum.length() > 0) {
                loginNum = loginNum.substring(0, loginNum.length() - 1);
            }

            modelAndView.addObject("chart", showDate + "|" + loginNum);// 绘图数据
            modelAndView.addObject("statistics", statistics);// 表数据
            modelAndView.addObject("month", month);
            modelAndView.addObject("year", year);
        } catch (Exception e) {
            logger.error("StatisticsDayController.getLoginStatistics" + e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 社区统计（活跃人数）信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/statistics/sns")
    public ModelAndView getSnsStatistics(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(snsChart);
        try {
            SimpleDateFormat s = new SimpleDateFormat("yyyy");

            String year = request.getParameter("year");// 年
            if (year == null) {
                year = s.format(new Date());
            }
            Map<String, Object> sourceMap = statisticsDayService.getStatisticsMsg(null, year);
            @SuppressWarnings("unchecked")
            List<StatisticsDay> statistics = (List<StatisticsDay>) sourceMap.get("statisticsDayList");
            Object[] weiboNum = new Object[12];// 发表观点数
            Object[] blogNum = new Object[12];// 博文数
            Object[] assesNum = new Object[12];// 课程评论数
            Object[] quesNum = new Object[12];// 问题数
            for (int i = statistics.size() - 1; i >= 0; i--) {
                weiboNum[statistics.size() - 1 - i] = statistics.get(i).getWeiboNum();
                blogNum[statistics.size() - 1 - i] = statistics.get(i).getBlogNum();
                assesNum[statistics.size() - 1 - i] = statistics.get(i).getAssesNum();
                quesNum[statistics.size() - 1 - i] = statistics.get(i).getQuesNum();

            }
            modelAndView.addObject("chart", gson.toJson(weiboNum) + "|" + gson.toJson(blogNum) + "|" + gson.toJson(assesNum) + "|" + gson.toJson(quesNum));// 绘图数据
            modelAndView.addObject("statistics", statistics);// 表数据
            modelAndView.addObject("year", year);
        } catch (Exception e) {
            logger.error("StatisticsDayController.getSnsStatistics" + e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 课程订单统计信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/statistics/order/course")
    public ModelAndView getCourseOrderStatistics(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(courseOrderChart);
        try {
            SimpleDateFormat s = new SimpleDateFormat("yyyy");
            String month = request.getParameter("month");// 月份
            String year = request.getParameter("year");// 年
            if (year == null) {
                year = s.format(new Date());
            }
            Map<String, Object> sourceMap = statisticsDayService.getStatisticsMsg(month, year);
            @SuppressWarnings("unchecked")
            List<StatisticsDay> statistics = (List<StatisticsDay>) sourceMap.get("statisticsDayList");
            String showDate = (String) sourceMap.get("showDate");// 统计js的X轴的日期显示
            // 数据整理
            String allOrderNum = "";// 订单数
            String payOrderNum = "";
            String amountNum = "";
            for (int i = statistics.size() - 1; i >= 0; i--) {
                allOrderNum += statistics.get(i).getCourseNum() + ",";
                payOrderNum += statistics.get(i).getCoursePayNum() + ",";
                amountNum += statistics.get(i).getCoursePayAmount() + ",";
            }
            if (allOrderNum.length() > 0) {
                allOrderNum = allOrderNum.substring(0, allOrderNum.length() - 1);
            }
            if (payOrderNum.length() > 0) {
                payOrderNum = payOrderNum.substring(0, payOrderNum.length() - 1);
            }
            if (amountNum.length() > 0) {
                amountNum = amountNum.substring(0, amountNum.length() - 1);
            }
            modelAndView.addObject("chart", showDate + "|" + allOrderNum + "|" + payOrderNum + "|" + amountNum);// 绘图数据
            modelAndView.addObject("statistics", statistics);// 表数据
            modelAndView.addObject("month", month);
            modelAndView.addObject("year", year);
        } catch (Exception e) {
            logger.error("StatisticsDayController.getCourseOrderStatistics" + e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 网站概况
     *
     * @param request
     * @return
     */
    @RequestMapping("/statistics/web/detail")
    public ModelAndView getWebStatistics(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(webChart);
        try {
            modelAndView.addObject("userName", SingletonLoginUtils.getLoginUserName(request));
            // 今天数据获得。总数据获得
            int todayloginnum = statisticsDayService.getTodayLoginNum(new Date());
            int todayOrderNum = statisticsDayService.getStatisticalNumByType(new Date(), StatisticsQueryType.ORDER_NUM.toString());
            int todayKpointNum = statisticsDayService.getStatisticalNumByType(new Date(), StatisticsQueryType.KPOINT_NUM.toString());
            int todayRegisterNum = statisticsDayService.getStatisticalNumByType(new Date(), StatisticsQueryType.REGISTER_NUM.toString());
            int todayAssesNum = statisticsDayService.getStatisticalNumByType(new Date(), StatisticsQueryType.ASSES_NUM.toString());
            int todayQuesNum = statisticsDayService.getStatisticalNumByType(new Date(), StatisticsQueryType.QUES_NUM.toString());
            modelAndView.addObject("todayloginnum", todayloginnum);// 今天登录人数
            modelAndView.addObject("todayOrderNum", todayOrderNum);// 今日订单数
            modelAndView.addObject("todayKpointNum", todayKpointNum);// 今日新增课时数
            modelAndView.addObject("todayRegisterNum", todayRegisterNum);// 今日新增会员数
            modelAndView.addObject("todayAssesNum", todayAssesNum);// 今日新增评论数
            modelAndView.addObject("todayQuesNum", todayQuesNum);// 今日新增答疑数
            // 今天数据获得。总数据获得
            // 获取总课时
            int kpointNum = statisticsDayService.getStatisticsKpoint();
            modelAndView.addObject("kpointNum", kpointNum);// 总课时
            DecimalFormat df = new java.text.DecimalFormat("#.0");// 保留百分比一位小数
            DecimalFormat df1 = new java.text.DecimalFormat("#");

            StatisticsDay statistics = statisticsDayService.getStatisticsSumMsg();
            modelAndView.addObject("statistics", statistics);

            // 历史数据
            modelAndView.addObject("historyRegisterNum", statistics.getRegisterNum());// 历史注册会员数
            modelAndView.addObject("historyAssesNum", statistics.getAssesNum());// 历史课程评论数
            modelAndView.addObject("historyQuesNum", statistics.getQuesNum());// 历史互动答疑数

        } catch (Exception e) {
            logger.error("StatisticsDayController.getWebStatistics" + e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }

//		// 获得社区开关是否开启
//		Map<String, Object> map = getVerifySnsKeyword();
//		if (ObjectUtils.isNotNull(map) && map.get("verifySns").toString().equalsIgnoreCase("ON")) {
//			// SNS数据
//			try {
//				SimpleDateFormat s = new SimpleDateFormat("yyyy");
//				String year = request.getParameter("year");// 年
//				if (year == null) {
//					year = s.format(new Date());
//				}
//				Map<String, Object> sourceMap = statisticsDayService.getStatisticsMsg(null, year);
//				@SuppressWarnings("unchecked")
//				List<StatisticsDay> statistics = (List<StatisticsDay>) sourceMap.get("statisticsDayList");
//				Object[] weiboNum = new Object[12];// 发表观点数
//				Object[] blogNum = new Object[12];// 博文数
//				Object[] assesNum = new Object[12];// 课程评论数
//				Object[] quesNum = new Object[12];// 问题数
//				for (int i = statistics.size() - 1; i >= 0; i--) {
//					weiboNum[statistics.size() - 1 - i] = statistics.get(i).getWeiboNum();
//					blogNum[statistics.size() - 1 - i] = statistics.get(i).getBlogNum();
//					assesNum[statistics.size() - 1 - i] = statistics.get(i).getAssesNum();
//					quesNum[statistics.size() - 1 - i] = statistics.get(i).getQuesNum();
//
//				}
//				modelAndView.addObject("chartsns", gson.toJson(weiboNum) + "|" + gson.toJson(blogNum) + "|" + gson.toJson(assesNum) + "|" + gson.toJson(quesNum));// 绘图数据
//				modelAndView.addObject("statisticssns", statistics);// 表数据
//				modelAndView.addObject("year", year);
//				modelAndView.addObject("defaultSnsKey", map.get("verifySns").toString().toLowerCase());
//			} catch (Exception e) {
//				logger.error("StatisticsDayController.getSnsStatistics" + e);
//				return new ModelAndView(this.setExceptionRequest(request, e));
//			}
//		} else {
//			modelAndView.addObject("defaultSnsKey", "off");
//		}

        return modelAndView;
    }

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
     * 生成指定时间段的统计
     *
     * @param request
     * @return
     */
    @RequestMapping("/statistics/create/batch")
    @ResponseBody
    public Map<String, Object> createStatisticsByDate(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            List<StatisticsDay> statistics = statisticsDayService.getStatisticsByDate(startTime, endTime);
            if (statistics.size() > 0) {// 时间段内已有数据
                json = this.getJsonMap(true, "exists", null);
                return json;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dBegin = sdf.parse(startTime);
            Date dEnd = sdf.parse(endTime);
            statisticsDayService.createStatisticsByDate(dBegin, dEnd);
            json = this.getJsonMap(true, "true", null);
        } catch (Exception e) {
            logger.error("StatisticsDayController.createStatisticsByDate" + e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 删除指定时间段的统计
     *
     * @param request
     * @return
     */
    @RequestMapping("/statistics/del/batch")
    @ResponseBody
    public Map<String, Object> delStatisticsByDate(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            statisticsDayService.delStatisticsByDate(startTime, endTime);
            json = this.getJsonMap(true, "true", null);
        } catch (Exception e) {
            logger.error("StatisticsDayController.createStatisticsByDate" + e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 获得社区开关是否开启
     *
     * @return
     */
    public Map<String, Object> getVerifySnsKeyword() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Map<String, Object> keywordmap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());
            if (ObjectUtils.isNotNull(keywordmap)) {
                JsonObject jsonObject = jsonParser.parse(gson.toJson(keywordmap.get(WebSiteProfileType.keyword.toString()))).getAsJsonObject();
                if (ObjectUtils.isNotNull(jsonObject) && StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
                    map.put("verifySns", jsonObject.get("verifySns").getAsString());
                }
            }
        } catch (Exception e) {
            logger.error("getVerifySnsKeyword", e);
        }
        return map;
    }

    /**
     * 获取用户列表
     *
     * @param page
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/statistics/user/list")
    public String getUserList(@ModelAttribute("page") PageEntity page, Model model, @ModelAttribute("user") User user) {
        try {
            // 查询学员列表
            List<User> list = userService.getUserListByCondition(user, page);
            model.addAttribute("list", list);
            model.addAttribute("page", page);
            model.addAttribute("user", user);
        } catch (Exception e) {
            logger.error("StatisticsDayController.getUserList", e);
        }
        return toUserList;
    }

    /**
     * 根据学员id获取学员课程列表
     *
     * @param page  分页
     * @param model model
     * @return
     */
    @RequestMapping("/statistics/user/course/list")
    public String getUserCourse(@ModelAttribute("page") PageEntity page, Model model, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        try {
            // 页面传来的数据放到page中
            page.setPageSize(12);
            List<CourseDto> courseList = courseService.getUserCourseList(queryCourse, page);
            model.addAttribute("courseList", courseList);
            model.addAttribute("page", page);
            model.addAttribute("queryCourse", queryCourse);
        } catch (Exception e) {
            logger.error("StatisticsDayController.getUserCourse", e);
        }
        return toUserCourseList;
    }

    /**
     * 获取课时学习详情
     *
     * @param page               分页
     * @param model              model
     * @param courseStudyhistory 课程记录
     * @return
     */
    @RequestMapping("/statistics/course/history")
    public String getCourseHistoryInfo(@ModelAttribute("page") PageEntity page, Model model, @ModelAttribute("courseStudyhistory") CourseStudyhistory courseStudyhistory) {
        try {
            page.setPageSize(12);
            courseStudyhistory.setStatistics(1l);
            List<CourseStudyhistory> studyHistoryList = courseStudyhistoryService.getCourseStudyhistoryListGroupByCourseId(courseStudyhistory, page);
            model.addAttribute("page", page);
            model.addAttribute("list", studyHistoryList);
        } catch (Exception e) {
            logger.error("StatisticsDayController.getUserCourse", e);
        }
        return studyHistory;
    }

    /**
     * 获取课程列表
     *
     * @param page        分页
     * @param model       MODEL
     * @param queryCourse 课程
     * @return
     */
    @RequestMapping("/statistics/course/list")
    public String getCourseList(@ModelAttribute("page") PageEntity page, Model model, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        try {
            // 查询课程列表
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            model.addAttribute("courseList", courseList);
            // 查询专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            model.addAttribute("subjectList", gson.toJson(subjectList));
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("StatisticsDayController.getCourseList", e);
        }
        return toCourseList;
    }

    /**
     * 获取课程小节列表
     *
     * @param page         分页
     * @param model        MODEL
     * @param courseKpoint 课程小节
     * @return
     */
    @RequestMapping("/statistics/courseKpoint/list")
    public String getCourseKpointList(@ModelAttribute("page") PageEntity page, Model model, @ModelAttribute("courseKpoint") CourseKpoint courseKpoint) {
        try {
            // 获取课程数据
            Course course = courseService.getCourseById(courseKpoint.getCourseId());
            model.addAttribute("course", course);
            // 获取课程小节列表
            List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointListPage(courseKpoint, page);
            model.addAttribute("courseKpointList", courseKpointList);
        } catch (Exception e) {
            logger.error("StatisticsDayController.getCourseKpointList", e);
        }
        return toCourseKpointList;
    }

    /**
     * 获取部门列表
     *
     * @param page      分页
     * @param model     MODEL
     * @param userGroup 部门
     * @return
     */
    @RequestMapping("/statistics/group/list")
    public String getGroupList(@ModelAttribute("page") PageEntity page, Model model, @ModelAttribute("userGroup") UserGroup userGroup) {
        try {
            // 查询部门列表
            List<UserGroup> userGroupList = userGroupService.getUserGroupListStatistics(userGroup, page);
            model.addAttribute("userGroupList", userGroupList);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("StatisticsDayController.getGroupList", e);
        }
        return toGroupList;
    }

    /**
     * 获取部门学员列表
     *
     * @param page  分页
     * @param model MODEL
     * @param user  学员
     * @return
     */
    @RequestMapping("/statistics/group/user/list")
    public String getGroupUserList(@ModelAttribute("page") PageEntity page, Model model, @ModelAttribute("user") User user) {
        try {
            // 查询学员列表
            List<User> list = userService.getUserListByCondition(user, page);
            model.addAttribute("list", list);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("StatisticsDayController.getGroupUserList", e);
        }
        return toGroupUserList;
    }


    /**
     * 获取注册来源饼状图数据
     *
     * @return
     */
    public String getRegisterFrom() {
        DecimalFormat df = new java.text.DecimalFormat("#.0");// 保留百分比一位小数
        //用户注册来源饼状图
        String registerFromChar = "";
        Object[][] userFromArray = new Object[9][2];
        // 用户数量
        //Double totalUserNum = cacheKit.get(MemConstans.MEMFIX+"totalUserNum")!=null?new Double(cacheKit.get(MemConstans.MEMFIX+"totalUserNum").toString()):new Double(0);
        Long totalNum = StatisticsUtil.getActionAllCount(MemConstans.MEMFIX);
        Double totalUserNum = totalNum.doubleValue();
        // 后台开通用户数量
        Long adminNum = StatisticsUtil.getActionOneDayOrActionCount(MemConstans.MEMFIX, UserExpandFrom.adminFrom.toString());
        // app注册用户数量
        Long appNum = StatisticsUtil.getActionOneDayOrActionCount(MemConstans.MEMFIX, UserExpandFrom.appFrom.toString());
        // 注册用户数量
        Long registerNum = StatisticsUtil.getActionOneDayOrActionCount(MemConstans.MEMFIX, UserExpandFrom.registerFrom.toString());
        // 课程卡用户数量
        Long userCardNum = StatisticsUtil.getActionOneDayOrActionCount(MemConstans.MEMFIX, UserExpandFrom.userCardFrom.toString());
        // 微站注册用户数量
        Long mobileNum = StatisticsUtil.getActionOneDayOrActionCount(MemConstans.MEMFIX, UserExpandFrom.mobileFrom.toString());

        // 如果注册数量都为空，则查询一次
        if (ObjectUtils.isNull(adminNum) && ObjectUtils.isNull(appNum) && ObjectUtils.isNull(registerNum) &&
                ObjectUtils.isNull(userCardNum) && ObjectUtils.isNull(mobileNum)) {
            UserFromStatistics userFromStatistics = userService.getUserFromStatistics();
            // 用户数量
            totalUserNum = Double.parseDouble(Long.toString(userFromStatistics.getUserNum()));
            cacheKit.set(MemConstans.MEMFIX + "totalUserNum", totalUserNum);
            // 后台开通用户数量
            adminNum = userFromStatistics.getAdminNum();
            cacheKit.set(MemConstans.MEMFIX + UserExpandFrom.adminFrom.toString(), adminNum);
            // app注册用户数量
            appNum = userFromStatistics.getAppNum();
            cacheKit.set(MemConstans.MEMFIX + UserExpandFrom.appFrom.toString(), appNum);
            // 注册用户数量
            registerNum = userFromStatistics.getRegisterNum();
            cacheKit.set(MemConstans.MEMFIX + UserExpandFrom.registerFrom.toString(), registerNum);
            // 课程卡用户数量
            userCardNum = userFromStatistics.getUserCardNum();
            cacheKit.set(MemConstans.MEMFIX + UserExpandFrom.userCardFrom.toString(), userCardNum);
            // 微站注册用户数量
            mobileNum = userFromStatistics.getMobileNum();
            cacheKit.set(MemConstans.MEMFIX + UserExpandFrom.mobileFrom.toString(), mobileNum);
        }

        if (totalUserNum > 0) {
            float adminNumPersent = Float.parseFloat(df.format((adminNum / totalUserNum) * 100));
            float appNumPersent = Float.parseFloat(df.format((appNum / totalUserNum) * 100));
            float registerNumPersent = Float.parseFloat(df.format((registerNum / totalUserNum) * 100));
            float userCardNumPersent = Float.parseFloat(df.format((userCardNum / totalUserNum) * 100));
            float mobileNumPersent = Float.parseFloat(df.format((mobileNum / totalUserNum) * 100));
            userFromArray[0][0] = "后台开通用户:" + adminNum + "人";
            userFromArray[0][1] = adminNumPersent;
            userFromArray[1][0] = "app注册用户:" + appNum + "人";
            userFromArray[1][1] = appNumPersent;
            userFromArray[2][0] = "注册用户:" + registerNum + "人";
            userFromArray[2][1] = registerNumPersent;
            userFromArray[3][0] = "课程卡用户:" + userCardNum + "人";
            userFromArray[3][1] = userCardNumPersent;
            userFromArray[4][0] = "微站注册用户:" + mobileNum + "人";
            userFromArray[4][1] = mobileNumPersent;
            registerFromChar = gson.toJson(userFromArray).toString();
        }

        return registerFromChar;
    }


    /**
     * 课程播放统计列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/statistics/userCourseKpoint")
    public String getUserCourseKpointStatistics(HttpServletRequest request) {
        try {
            SimpleDateFormat s = new SimpleDateFormat("yyyy");
            String month = request.getParameter("month");// 月份
            String year = request.getParameter("year");// 年
            if (year == null) {
                year = s.format(new Date());
            }
            Map<String, Object> sourceMap = userCourseKpointStatisticService.getStatisticsMsg(month, year);
            @SuppressWarnings("unchecked")
            List<UserCourseKpointStatistic> statistics = (List<UserCourseKpointStatistic>) sourceMap.get("statisticsDayList");
            if (ObjectUtils.isNotNull(statistics)) {
                // 数据整理
                String date = "";// 时间
                String playCount = ""; //播放次数
                String playerNum = ""; //观看人数
                for (int i = statistics.size() - 1; i >= 0; i--) {
                    if (i == 0) {
                        date += statistics.get(i).getDate();
                        playCount += statistics.get(i).getPlayCount();
                        playerNum += statistics.get(i).getPlayerNum();
                    } else {
                        date += statistics.get(i).getDate() + ",";
                        playCount += statistics.get(i).getPlayCount() + ",";
                        playerNum += statistics.get(i).getPlayerNum() + ",";
                    }
                }
                request.setAttribute("data", date + "|" + playCount + "|" + playerNum);
            }
            request.setAttribute("statistics", statistics);
            request.setAttribute("year", year);
            request.setAttribute("month", month);
        } catch (Exception e) {

        }
        return getViewPath("/admin/statistics/statistics_user_course_kpoinst");
    }
}
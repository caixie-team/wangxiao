package io.wangxiao.edu.home.service.impl.statistic;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.home.dao.statistic.UserCourseKpointStatisticDao;
import io.wangxiao.edu.home.entity.course.CourseStudyhistory;
import io.wangxiao.edu.home.entity.statistic.UserCourseKpointStatistic;
import io.wangxiao.edu.home.service.course.CourseStudyhistoryService;
import io.wangxiao.edu.home.service.statistic.UserCourseKpointStatisticService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * UserCourseKpointStatistic管理接口
 */
@Service("userCourseKpointStatisticService")
public class UserCourseKpointStatisticServiceImpl implements UserCourseKpointStatisticService {
    CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private UserCourseKpointStatisticDao userCourseKpointStatisticDao;
    @Autowired
    private CourseStudyhistoryService historyService;

    @Getter
    @Setter
    private Map<String, Object> userMsg = new HashMap<String, Object>();

    /**
     * 添加UserCourseKpointStatistic
     *
     * @param userCourseKpointStatistic 要添加的UserCourseKpointStatistic
     * @return id
     */
    public java.lang.Long addUserCourseKpointStatistic(UserCourseKpointStatistic userCourseKpointStatistic) {
        return userCourseKpointStatisticDao.addUserCourseKpointStatistic(userCourseKpointStatistic);
    }

    /**
     * 根据id删除一个UserCourseKpointStatistic
     *
     * @param id 要删除的id
     */
    public void deleteUserCourseKpointStatisticById(Long id) {
        userCourseKpointStatisticDao.deleteUserCourseKpointStatisticById(id);
    }

    /**
     * 修改UserCourseKpointStatistic
     *
     * @param userCourseKpointStatistic 要修改的UserCourseKpointStatistic
     */
    public void updateUserCourseKpointStatistic(UserCourseKpointStatistic userCourseKpointStatistic) {
        UserCourseKpointStatistic _userCourseKpointStatistic = userCourseKpointStatisticDao.getUserCourseKpointStatistic(userCourseKpointStatistic);
        // 判断记录是否存在
        if (ObjectUtils.isNotNull(_userCourseKpointStatistic)) {
            _userCourseKpointStatistic.setPlayCount(_userCourseKpointStatistic.getPlayCount() + userCourseKpointStatistic.getPlayCount());
            _userCourseKpointStatistic.setPlayerNum(_userCourseKpointStatistic.getPlayerNum() + userCourseKpointStatistic.getPlayerNum());
            userCourseKpointStatisticDao.updateUserCourseKpointStatistic(_userCourseKpointStatistic);
        } else {
            _userCourseKpointStatistic = new UserCourseKpointStatistic();
            _userCourseKpointStatistic.setStatisticDate(new Date());
            _userCourseKpointStatistic.setPlayCount(1L);
            _userCourseKpointStatistic.setPlayerNum(1L);
            this.addUserCourseKpointStatistic(_userCourseKpointStatistic);
        }
    }

    /**
     * 根据id获取单个UserCourseKpointStatistic对象
     *
     * @param id 要查询的id
     * @return UserCourseKpointStatistic
     */
    public UserCourseKpointStatistic getUserCourseKpointStatisticById(Long id) {
        return userCourseKpointStatisticDao.getUserCourseKpointStatisticById(id);
    }

    /**
     * 根据条件获取UserCourseKpointStatistic列表
     *
     * @param userCourseKpointStatistic 查询条件
     * @return List<UserCourseKpointStatistic>
     */
    public List<UserCourseKpointStatistic> getUserCourseKpointStatisticList(UserCourseKpointStatistic userCourseKpointStatistic) {
        return userCourseKpointStatisticDao.getUserCourseKpointStatisticList(userCourseKpointStatistic);
    }

    @Override
    public void updatePlayCount(CourseStudyhistory history) {
        // 时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startTime = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endTime = calendar.getTime();


        UserCourseKpointStatistic userCourseKpointStatistic = new UserCourseKpointStatistic();
        userCourseKpointStatistic.setStatisticDate(startTime);
        // 播放记录+1
        userCourseKpointStatistic.setPlayCount(1L);
        // 是否有学习记录
        List<CourseStudyhistory> historyList = historyService.getCourseStudyhistoryListByCondition(history, new PageEntity());
        if (ObjectUtils.isNotNull(historyList)) {

            history.setStartTime(startTime);
            history.setEndTime(endTime);
            // 今天是否有学习记录
            List<CourseStudyhistory> _historyList = historyService.getCourseStudyhistoryListByCondition(history, new PageEntity());
            // 今天没有学习记录 人数+1
            if (ObjectUtils.isNull(_historyList)) {
                userCourseKpointStatistic.setPlayerNum(1L);
            }
        } else {
            userCourseKpointStatistic.setPlayerNum(1L);
        }
        updateUserCourseKpointStatistic(userCourseKpointStatistic);

    }

    @Override
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
        List<UserCourseKpointStatistic> statisticsDayList = userCourseKpointStatisticDao.getStatisticsByMonth(month, year);
        if (ObjectUtils.isNotNull(statisticsDayList)) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.valueOf(year), Integer.valueOf(month), 0);
            int dateNum = statisticsDayList.size() - calendar.get(Calendar.DATE);
            for (int i = 0; i < dateNum; i++) {
                statisticsDayList.remove(i);
            }
        }
        userMsg.put("statisticsDayList", statisticsDayList);
    }

    /**
     * 按年查询网站统计
     *
     * @param year
     * @return
     */
    public void getStatisticsDayByYear(String year) {
        List<UserCourseKpointStatistic> statisticsList = userCourseKpointStatisticDao.getStatisticsByYear(year);
        userMsg.put("statisticsDayList", statisticsList);
    }

    /**
     * 网站统计 （总记录）
     *
     * @return
     * @throws Exception
     */
    public UserCourseKpointStatistic getStatisticsSumMsg() {

        UserCourseKpointStatistic userCourseKpointStatistic = userCourseKpointStatisticDao.getStatisticsSumMsg();

        return userCourseKpointStatistic;
    }

    @Override
    public void addCourseKpointStatisticAuto() {
        UserCourseKpointStatistic statistic = new UserCourseKpointStatistic();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -1);
        statistic.setStatisticDate(calendar.getTime());
        statistic = userCourseKpointStatisticDao.getUserCourseKpointStatistic(statistic);
        if (ObjectUtils.isNull(statistic)) {
            statistic = new UserCourseKpointStatistic();
            statistic.setStatisticDate(calendar.getTime());
            userCourseKpointStatisticDao.addUserCourseKpointStatistic(statistic);
        }
    }

    @Override
    public List<UserCourseKpointStatistic> getStatisticListByDays(int days) {
        List<UserCourseKpointStatistic> statisticList = (List<UserCourseKpointStatistic>) cacheKit.get(MemConstans.COURSE_PLAY_COUNT_THIRTY);
        if (ObjectUtils.isNull(statisticList)) {
            statisticList = this.userCourseKpointStatisticDao.getStatisticListByDays(days);
            cacheKit.set(MemConstans.COURSE_PLAY_COUNT_THIRTY, statisticList, MemConstans.COURSE_PLAY_COUNT_TIME);
        }
        return statisticList;
    }
}
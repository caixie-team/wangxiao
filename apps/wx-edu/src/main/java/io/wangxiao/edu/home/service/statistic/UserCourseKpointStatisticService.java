package io.wangxiao.edu.home.service.statistic;

import io.wangxiao.edu.home.entity.course.CourseStudyhistory;
import io.wangxiao.edu.home.entity.statistic.UserCourseKpointStatistic;

import java.util.List;
import java.util.Map;

/**
 * UserCourseKpointStatistic管理接口
 */
public interface UserCourseKpointStatisticService {

    /**
     * 添加UserCourseKpointStatistic
     * @param userCourseKpointStatistic 要添加的UserCourseKpointStatistic
     * @return id
     */
    java.lang.Long addUserCourseKpointStatistic(UserCourseKpointStatistic userCourseKpointStatistic);

    /**
     * 根据id删除一个UserCourseKpointStatistic
     * @param id 要删除的id
     */
    void deleteUserCourseKpointStatisticById(Long id);

    /**
     * 修改UserCourseKpointStatistic
     * @param userCourseKpointStatistic 要修改的UserCourseKpointStatistic
     */
    void updateUserCourseKpointStatistic(UserCourseKpointStatistic userCourseKpointStatistic);

    /**
     * 根据id获取单个UserCourseKpointStatistic对象
     * @param id 要查询的id
     * @return UserCourseKpointStatistic
     */
    UserCourseKpointStatistic getUserCourseKpointStatisticById(Long id);

    /**
     * 根据条件获取UserCourseKpointStatistic列表
     * @param userCourseKpointStatistic 查询条件
     * @return List<UserCourseKpointStatistic>
     */
    List<UserCourseKpointStatistic> getUserCourseKpointStatisticList(UserCourseKpointStatistic userCourseKpointStatistic);


    /**
     * 修改节点播放次数
     * @param history
     */
    void updatePlayCount(CourseStudyhistory history);

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
    UserCourseKpointStatistic getStatisticsSumMsg();

    /**
     * 自动生成课程播放记录
     */
    void addCourseKpointStatisticAuto();

    /**
     * 获取记录条数
     * @param days
     * @return
     */
    List<UserCourseKpointStatistic> getStatisticListByDays(int days);
}
package io.wangxiao.edu.home.dao.statistic;
import java.util.List;
import io.wangxiao.edu.home.entity.statistic.UserCourseKpointStatistic;

/**
 * UserCourseKpointStatistic管理接口
 * User: qinggang.liu
 * Date: 2016-01-30
 */
public interface UserCourseKpointStatisticDao {

    /**
     * 添加UserCourseKpointStatistic
     * @param userCourseKpointStatistic 要添加的UserCourseKpointStatistic
     * @return id
     */
    public java.lang.Long addUserCourseKpointStatistic(UserCourseKpointStatistic userCourseKpointStatistic);

    /**
     * 根据id删除一个UserCourseKpointStatistic
     * @param id 要删除的id
     */
    public void deleteUserCourseKpointStatisticById(Long id);

    /**
     * 修改UserCourseKpointStatistic
     * @param userCourseKpointStatistic 要修改的UserCourseKpointStatistic
     */
    public void updateUserCourseKpointStatistic(UserCourseKpointStatistic userCourseKpointStatistic);

    /**
     * 根据id获取单个UserCourseKpointStatistic对象
     * @param id 要查询的id
     * @return UserCourseKpointStatistic
     */
    public UserCourseKpointStatistic getUserCourseKpointStatisticById(Long id);

    /**
     * 根据条件获取单个UserCourseKpointStatistic对象
     * @param userCourseKpointStatistic 查询条件
     * @return UserCourseKpointStatistic
     */
    public UserCourseKpointStatistic getUserCourseKpointStatistic(UserCourseKpointStatistic userCourseKpointStatistic);

    /**
     * 根据条件获取UserCourseKpointStatistic列表
     * @param userCourseKpointStatistic 查询条件
     * @return List<UserCourseKpointStatistic>
     */
    public List<UserCourseKpointStatistic> getUserCourseKpointStatisticList(UserCourseKpointStatistic userCourseKpointStatistic);

    /**
     * 按年查询网站统计
     *
     * @param year
     * @return
     */
    public List<UserCourseKpointStatistic> getStatisticsByYear(String year);
    /**
     * 按月查询网站统计
     *
     * @param month
     * @param year
     * @return
     */
    public List<UserCourseKpointStatistic> getStatisticsByMonth(String month,String year);

    /**
     * 网站统计 （总记录）
     *
     * @return
     * @throws Exception
     */
    public UserCourseKpointStatistic getStatisticsSumMsg();

    /**
     * 根据数量获取记录
     * @param days
     * @return
     */
    List<UserCourseKpointStatistic> getStatisticListByDays(int days);
}
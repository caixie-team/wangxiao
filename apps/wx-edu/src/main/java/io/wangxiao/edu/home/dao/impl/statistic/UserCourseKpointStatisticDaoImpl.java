package io.wangxiao.edu.home.dao.impl.statistic;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.statistic.UserCourseKpointStatisticDao;
import io.wangxiao.edu.home.entity.statistic.UserCourseKpointStatistic;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userCourseKpointStatisticDao")
public class UserCourseKpointStatisticDaoImpl extends GenericDaoImpl implements UserCourseKpointStatisticDao {

    public java.lang.Long addUserCourseKpointStatistic(UserCourseKpointStatistic userCourseKpointStatistic) {
        return this.insert("UserCourseKpointStatisticMapper.createUserCourseKpointStatistic", userCourseKpointStatistic);
    }

    public void deleteUserCourseKpointStatisticById(Long id) {
        this.delete("UserCourseKpointStatisticMapper.deleteUserCourseKpointStatisticById", id);
    }

    public void updateUserCourseKpointStatistic(UserCourseKpointStatistic userCourseKpointStatistic) {
        this.update("UserCourseKpointStatisticMapper.updateUserCourseKpointStatistic", userCourseKpointStatistic);
    }

    public UserCourseKpointStatistic getUserCourseKpointStatisticById(Long id) {
        return this.selectOne("UserCourseKpointStatisticMapper.getUserCourseKpointStatisticById", id);
    }

    public UserCourseKpointStatistic getUserCourseKpointStatistic(UserCourseKpointStatistic userCourseKpointStatistic) {
        return this.selectOne("UserCourseKpointStatisticMapper.getUserCourseKpointStatistic", userCourseKpointStatistic);
    }

    public List<UserCourseKpointStatistic> getUserCourseKpointStatisticList(UserCourseKpointStatistic userCourseKpointStatistic) {
        return this.selectList("UserCourseKpointStatisticMapper.getUserCourseKpointStatisticList", userCourseKpointStatistic);
    }

    @Override
    public List<UserCourseKpointStatistic> getStatisticsByYear(String year) {
        return this.selectList("UserCourseKpointStatisticMapper.getKpointStatisticsByYear", year);
    }

    @Override
    public List<UserCourseKpointStatistic> getStatisticsByMonth(String month, String year) {
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("yearAndMonth", year + "-" + month);//年月
        return this.selectList("UserCourseKpointStatisticMapper.getKpointStatisticsByMonth", map);
    }

    @Override
    public UserCourseKpointStatistic getStatisticsSumMsg() {
        return this.selectOne("UserCourseKpointStatisticMapper.getKpointStatisticsSumMsg", 0);
    }

    @Override
    public List<UserCourseKpointStatistic> getStatisticListByDays(int days) {
        return this.selectList("UserCourseKpointStatisticMapper.getStatisticListByDays", days);
    }
}

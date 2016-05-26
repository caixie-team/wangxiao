package io.wangxiao.edu.home.dao.impl.course;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.course.CourseStudyhistoryDao;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.CourseStudyhistory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository("courseStudyhistoryDao")
public class CourseStudyhistoryDaoImpl extends GenericDaoImpl implements CourseStudyhistoryDao {

    public java.lang.Long addCourseStudyhistory(CourseStudyhistory courseStudyhistory) {
        return this.insert("CourseStudyhistoryMapper.createCourseStudyhistory", courseStudyhistory);
    }

    public void deleteCourseStudyhistoryById(Long id) {
        this.delete("CourseStudyhistoryMapper.deleteCourseStudyhistoryById", id);
    }

    public void updateCourseStudyhistory(CourseStudyhistory courseStudyhistory) {
        this.update("CourseStudyhistoryMapper.updateCourseStudyhistory", courseStudyhistory);
    }

    public CourseStudyhistory getCourseStudyhistoryById(Long id) {
        return this.selectOne("CourseStudyhistoryMapper.getCourseStudyhistoryById", id);
    }

    public CourseStudyhistory getCourseStudyhistory(CourseStudyhistory courseStudyhistory) {
        return this.selectOne("CourseStudyhistoryMapper.getCourseStudyhistory", courseStudyhistory);
    }

    public List<CourseStudyhistory> getCourseStudyhistoryList(CourseStudyhistory courseStudyhistory) {
        return this.selectList("CourseStudyhistoryMapper.getCourseStudyhistoryList", courseStudyhistory);
    }

    @Override
    public List<CourseStudyhistory> getCourseStudyhistoryListByCondition(
            CourseStudyhistory courseStudyhistory, PageEntity page) {
        // TODO Auto-generated method stub
        return this.queryForListPage("CourseStudyhistoryMapper.getCourseStudyhistoryListByCondition", courseStudyhistory, page);
    }


    public List<CourseStudyhistory> getCourseStudyhistoryListGroupByCourseId(CourseStudyhistory courseStudyhistory, PageEntity page) {
        return this.queryForListPage("CourseStudyhistoryMapper.getCourseStudyhistoryListGroupByCourseId", courseStudyhistory, page);
    }


    /**
     * 根据ids删除CourseStudyhistory
     *
     * @param ids 要删除的id
     */
    public void delCourseStudyhistory(String ids) {
        this.delete("CourseStudyhistoryMapper.delCourseStudyhistory", ids);
    }

    /**
     * 根据课程ids和用户查询
     *
     * @param list
     * @param userId
     * @return
     */
    public Long getCourseStudyhistoryCount(List<CourseDto> list, Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("userId", userId);
        return this.selectOne("CourseStudyhistoryMapper.getCourseStudyhistoryCount", map);
    }


    /**
     * 清空播放记录
     *
     * @param userId
     */
    public void cleanCourseStudyhistory(Long userId) {
        this.delete("CourseStudyhistoryMapper.cleanCourseStudyhistory", userId);
    }

    @Override
    public void updateCourseStudyhistoryPlayTime(CourseStudyhistory courseStudyhistory) {
        this.update("CourseStudyhistoryMapper.updateCourseStudyhistoryPlayTime", courseStudyhistory);
    }

    @Override
    public int getLearnCourseNum(Map<String, Object> map) {
        return this.selectOne("CourseStudyhistoryMapper.getLearnCourseNum", map);
    }
}

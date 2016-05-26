package io.wangxiao.edu.home.dao.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.CourseStudyhistory;

import java.util.List;
import java.util.Map;

/**
 * CourseStudyhistory管理接口
 */
public interface CourseStudyhistoryDao {

    /**
     * 添加CourseStudyhistory
     *
     * @param courseStudyhistory 要添加的CourseStudyhistory
     * @return id
     */
    java.lang.Long addCourseStudyhistory(CourseStudyhistory courseStudyhistory);


    /**
     * 根据id删除一个CourseStudyhistory
     *
     * @param id 要删除的id
     */
    void deleteCourseStudyhistoryById(Long id);

    /**
     * 修改CourseStudyhistory
     *
     * @param courseStudyhistory 要修改的CourseStudyhistory
     */
    void updateCourseStudyhistory(CourseStudyhistory courseStudyhistory);

    /**
     * 根据id获取单个CourseStudyhistory对象
     *
     * @param id 要查询的id
     * @return CourseStudyhistory
     */
    CourseStudyhistory getCourseStudyhistoryById(Long id);

    /**
     * 获取单个CourseStudyhistory
     *
     * @param courseStudyhistory
     * @return
     */
    CourseStudyhistory getCourseStudyhistory(CourseStudyhistory courseStudyhistory);

    /**
     * 根据条件获取CourseStudyhistory列表
     *
     * @param courseStudyhistory 查询条件
     * @return List<CourseStudyhistory>
     */
    List<CourseStudyhistory> getCourseStudyhistoryList(CourseStudyhistory courseStudyhistory);


    List<CourseStudyhistory> getCourseStudyhistoryListByCondition(CourseStudyhistory courseStudyhistory, PageEntity page);


    List<CourseStudyhistory> getCourseStudyhistoryListGroupByCourseId(CourseStudyhistory courseStudyhistory, PageEntity page);


    /**
     * 根据ids删除CourseStudyhistory
     *
     * @param id 要删除的id
     */
    void delCourseStudyhistory(String ids);

    /**
     * 根据课程ids和用户查询
     *
     * @param list
     * @param userId
     * @return
     */
    Long getCourseStudyhistoryCount(List<CourseDto> list, Long userId);

    /**
     * 清空播放记录
     *
     * @param userId
     */
    void cleanCourseStudyhistory(Long userId);

    /**
     * 更新节点播放时长
     *
     * @param courseStudyhistory
     */
    void updateCourseStudyhistoryPlayTime(CourseStudyhistory courseStudyhistory);

    /**
     * 获取小组学习课程数
     *
     * @param map
     * @return
     */
    int getLearnCourseNum(Map<String, Object> map);
}
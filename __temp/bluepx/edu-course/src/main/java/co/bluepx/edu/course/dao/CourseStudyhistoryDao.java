package co.bluepx.edu.course.dao;

import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.core.entity.PageEntity;
import co.bluepx.edu.course.entity.CourseDto;
import co.bluepx.edu.course.entity.CourseStudyhistory;

import java.util.List;

/**
 * CourseStudyhistory管理接口
 */
public interface CourseStudyhistoryDao extends BaseDao<CourseStudyhistory> {

    /**
     * 添加CourseStudyhistory
     *
     * @param courseStudyhistory 要添加的CourseStudyhistory
     * @return id
     */
    Long addCourseStudyhistory(CourseStudyhistory courseStudyhistory);


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
     * 根据条件获取CourseStudyhistory列表
     *
     * @param courseStudyhistory 查询条件
     * @return List<CourseStudyhistory>
     */
    List<CourseStudyhistory> getCourseStudyhistoryList(CourseStudyhistory courseStudyhistory);


    List<CourseStudyhistory> getCourseStudyhistoryListByCondition(CourseStudyhistory courseStudyhistory, PageEntity page);


    /**
     * 根据ids删除CourseStudyhistory
     *
     * @param ids 要删除的id
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


}
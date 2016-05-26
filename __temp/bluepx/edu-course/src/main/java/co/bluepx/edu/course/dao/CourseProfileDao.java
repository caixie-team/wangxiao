package co.bluepx.edu.course.dao;

import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.course.entity.CourseProfile;

import java.util.List;

/**
 * CourseProfile管理接口
 */
public interface CourseProfileDao extends BaseDao<CourseProfile> {

    /**
     * 添加CourseProfile
     *
     * @param courseProfile 要添加的CourseProfile
     * @return id
     */
    Long addCourseProfile(CourseProfile courseProfile);

    /**
     * 根据id删除一个CourseProfile
     *
     * @param id 要删除的id
     */
    void deleteCourseProfileById(Long id);

    /**
     * 修改CourseProfile
     *
     * @param courseProfile 要修改的CourseProfile
     */
    void updateCourseProfile(CourseProfile courseProfile);


    /**
     * 根据条件获取CourseProfile列表
     *
     * @param courseProfile 查询条件
     * @return List<CourseProfile>
     */
    List<CourseProfile> getCourseProfileList(CourseProfile courseProfile);


    /**
     * 要更新的数量类型
     * 修改CourseProfile浏览次数
     *
     */
    void updateCourseProfile(String type, Long courseId, Long count, String operation);

    /**
     * 根据课程编号更新购买该课程的数量
     *
     * @param ids
     */
    void updateCourseBuyCount(String ids);
}
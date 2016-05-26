package co.bluepx.edu.course.dao;

import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.course.entity.CourseTag;

import java.util.List;

/**
 * CourseTag管理接口
 */
public interface CourseTagDao extends BaseDao<CourseTag> {

    /**
     * 添加CourseTag
     *
     * @param courseTag 要添加的CourseTag
     * @return id
     */
    Long addCourseTag(CourseTag courseTag);

    /**
     * 根据id删除一个CourseTag
     *
     * @param id 要删除的id
     */
    void deleteCourseTagById(Long id);

    /**
     * 修改CourseTag
     *
     * @param courseTag 要修改的CourseTag
     */
    void updateCourseTag(CourseTag courseTag);

    /**
     * 根据id获取单个CourseTag对象
     *
     * @param id 要查询的id
     * @return CourseTag
     */
    CourseTag getCourseTagById(Long id);

    /**
     * 根据条件获取CourseTag列表
     *
     * @param courseTag 查询条件
     * @return List<CourseTag>
     */
    List<CourseTag> getCourseTagList(CourseTag courseTag);
}
package com.atdld.os.edu.dao.course;
import java.util.List;
import com.atdld.os.edu.entity.course.CourseTag;

/**
 * CourseTag管理接口
 * User:
 * Date: 2014-05-27
 */
public interface CourseTagDao {

    /**
     * 添加CourseTag
     * @param courseTag 要添加的CourseTag
     * @return id
     */
    public java.lang.Long addCourseTag(CourseTag courseTag);

    /**
     * 根据id删除一个CourseTag
     * @param id 要删除的id
     */
    public void deleteCourseTagById(Long id);

    /**
     * 修改CourseTag
     * @param courseTag 要修改的CourseTag
     */
    public void updateCourseTag(CourseTag courseTag);

    /**
     * 根据id获取单个CourseTag对象
     * @param id 要查询的id
     * @return CourseTag
     */
    public CourseTag getCourseTagById(Long id);

    /**
     * 根据条件获取CourseTag列表
     * @param courseTag 查询条件
     * @return List<CourseTag>
     */
    public List<CourseTag> getCourseTagList(CourseTag courseTag);
}
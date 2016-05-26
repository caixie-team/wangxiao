package co.bluepx.edu.course.dao;

import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.core.entity.PageEntity;
import co.bluepx.edu.course.entity.CourseDto;
import co.bluepx.edu.course.entity.CourseKpoint;

import java.util.List;

/**
 * CourseKpoint管理接口
 */
public interface CourseKpointDao extends BaseDao<CourseKpoint> {

    /**
     * 添加CourseKpoint
     *
     * @param courseKpoint 要添加的CourseKpoint
     * @return id
     */
    Long addCourseKpoint(CourseKpoint courseKpoint);

    /**
     * 根据id删除一个CourseKpoint
     *
     * @param id 要删除的id
     */
    void deleteCourseKpointById(Long id);

    /**
     * 根据id集合删除CourseKpoint
     */
    void deleteCourseKpointByIdBatch(String[] courseKpointIds);

    /**
     * 修改CourseKpoint
     *
     * @param courseKpoint 要修改的CourseKpoint
     */
    void updateCourseKpoint(CourseKpoint courseKpoint);

    /**
     * 修改CourseKpoint 播放数加一
     */
    void updateCourseKpointPlaycountAdd(Long kpointId);

    /**
     * 根据id获取单个CourseKpoint对象
     *
     * @param id 要查询的id
     * @return CourseKpoint
     */
    CourseKpoint getCourseKpointById(Long id);

    /**
     * 根据条件获取CourseKpoint列表
     *
     * @param courseKpoint 查询条件
     * @return List<CourseKpoint>
     */
    List<CourseKpoint> getCourseKpointList(CourseKpoint courseKpoint);

    /**
     * 根据条件获取CourseKpoint列表
     *
     * @param courseKpoint 查询条件
     * @return List<CourseKpoint>
     */
    List<CourseKpoint> getCourseKpointNewList(CourseKpoint courseKpoint, PageEntity page);

    /**
     * 根据课程集合获取节点集合
     *
     * @param list
     * @return
     */
    Long getCourseKpointNumByCourseList(List<CourseDto> list);

    /**
     * 根据几点ID集合字符串查询节点集合
     *
     * @param ids
     * @return
     */
    List<CourseKpoint> getCourseKpointListByIds(String ids);

    /**
     * 批量添加子节点
     *
     * @param childCourseKpointList
     */
    void createChildCourseKpointList(List<CourseKpoint> childCourseKpointList);
}
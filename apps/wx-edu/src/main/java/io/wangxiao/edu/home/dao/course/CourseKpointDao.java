package io.wangxiao.edu.home.dao.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.CourseKpoint;

import java.util.List;

public interface CourseKpointDao {

    /**
     * 添加CourseKpoint
     *
     * @param courseKpoint 要添加的CourseKpoint
     * @return id
     */
    java.lang.Long addCourseKpoint(CourseKpoint courseKpoint);

    /**
     * 根据id删除一个CourseKpoint
     *
     * @param id 要删除的id
     */
    void deleteCourseKpointById(Long id);

    /**
     * 根据courseId删除一个CourseKpoint
     *
     * @param courseId 要删除的courseId
     */
    void deleteCourseKpointByCourseId(Long courseId);

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
     * /**
     * 根据条件获取CourseKpoint列表
     *
     * @param courseKpoint 查询条件
     * @return List<CourseKpoint>
     */
    List<CourseKpoint> getCourseKpointListPage(CourseKpoint courseKpoint, PageEntity page);

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

    /**
     * 添加课时（部分字节）
     */
    java.lang.Long addKpoint(CourseKpoint courseKpoint);

    /**
     * 章节父节点变更
     *
     * @param courseKpoint
     */
    void updateCourseKpointParentId(CourseKpoint courseKpoint);


    /**
     * 查询课程课时数量
     *
     * @param courseId 课程id
     * @return
     */
    Long getCourseKpointCount(Long courseId);

    /**
     * 修改拖拽后的节点排序
     *
     * @return
     */
    void updateSortable(CourseKpoint courseKpointlist);

    /**
     * 修改章节的Name
     *
     * @return
     */
    void updateKpointName(CourseKpoint courseKpointlist);

    /**
     * 查询出最大的排序
     *
     * @return
     */
    Long getCourseKpointSort(Long courseid);

    /**
     * 查询出当前couserid下的记录总数
     *
     * @return
     */
    Long getCourseKpointListPageCount(CourseKpoint CourseKpoint);

    /**
     * 根据排序和课程ID查询出当前主键ID
     *
     * @return
     */
    Long getCourseKpointId(CourseKpoint CourseKpoint);

    /**
     * 批量添加CourseKpoint
     *
     * @param courseKpointList
     */
    void addCourseKpointBatch(List<CourseKpoint> courseKpointList);
}
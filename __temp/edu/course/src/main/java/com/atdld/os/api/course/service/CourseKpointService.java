package com.atdld.os.api.course.service;

import com.atdld.os.api.course.entity.CourseKpoint;
import com.atdld.os.entity.PageEntity;

import java.util.List;


/**
 * CourseKpoint管理接口
 * User:
 * Date: 2014-05-27
 */
public interface CourseKpointService {

    /**
     * 添加CourseKpoint
     * @param courseKpoint 要添加的CourseKpoint
     * @return id
     */
    public Long addCourseKpoint(CourseKpoint courseKpoint);

    /**
     * 根据id删除一个CourseKpoint
     * @param id 要删除的id
     */
    public void deleteCourseKpointById(Long id);
    /**
     * 根据id集合删除CourseKpoint
     */
    public void deleteCourseKpointByIdBatch(String[] courseKpointIds);
    /**
     * 修改CourseKpoint
     * @param courseKpoint 要修改的CourseKpoint
     */
    public void updateCourseKpoint(CourseKpoint courseKpoint);
    /**
     * 修改CourseKpoint 播放数加一
     */
    public void updateCourseKpointPlaycountAdd(Long kpointId);

    /**
     * 根据id获取单个CourseKpoint对象
     * @param id 要查询的id
     * @return CourseKpoint
     */
    public CourseKpoint getCourseKpointById(Long id);

    /**
     * 根据条件获取CourseKpoint列表
     * @param courseKpoint 查询条件
     * @return List<CourseKpoint>
     */
    public List<CourseKpoint> getCourseKpointList(CourseKpoint courseKpoint);

	 /**
     * 根据条件获取CourseKpoint列表
     * @param courseKpoint 查询条件
     * @return List<CourseKpoint>
     */
    public List<CourseKpoint> getCourseKpointNewList(CourseKpoint courseKpoint, PageEntity page);
    
    /**
	 * 根据几点ID集合字符串查询节点集合
	 * @param ids
	 * @return
	 */
	public List<CourseKpoint> getCourseKpointListByIds(String ids);
	
	/**
	 * 批量添加子节点
	 * @param childCourseKpointList
	 */
	public void createChildCourseKpointList(List<CourseKpoint> childCourseKpointList);
}
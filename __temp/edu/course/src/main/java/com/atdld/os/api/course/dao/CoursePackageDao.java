package com.atdld.os.api.course.dao;

import com.atdld.os.edu.entity.course.CoursePackage;

import java.util.List;

/**
 * Course管理接口 User:  Date: 2014-05-27
 */
public interface CoursePackageDao {
	  /**
     * 添加CoursePackage
     */
    public void addCoursePackageBatch(List<CoursePackage> coursePackageList);
    /**
     * 通过id查询CoursePackage
     */
    public CoursePackage queryCoursePackageById(CoursePackage coursePackage);
    /**
	 * 删除CoursePackage
	 */
	public void delCoursePackage(CoursePackage coursePackage);
	
	/**
     * 修改套餐课程下的课程排序
     * @param course
     */
    public void updateCoursePackageOrderNum(CoursePackage coursePackage);
}
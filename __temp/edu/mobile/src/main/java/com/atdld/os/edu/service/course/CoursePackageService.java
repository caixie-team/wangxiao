package com.atdld.os.edu.service.course;

import com.atdld.os.edu.entity.course.CoursePackage;

/**
 * Course管理接口 User:  Date: 2014-05-27
 */
public interface CoursePackageService {

	/**
	 * 添加CoursePackage
	 */
	public void addCoursePackageBatch(String ids, Long courseId);

	/**
	 * 删除CoursePackage
	 */
	public void delCoursePackage(CoursePackage coursePackage);
}
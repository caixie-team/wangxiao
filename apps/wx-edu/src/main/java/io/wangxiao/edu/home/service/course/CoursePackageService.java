package io.wangxiao.edu.home.service.course;

import java.util.List;

import io.wangxiao.edu.home.entity.course.CoursePackage;

/**
 * Course管理接口 User: qinggang.liu Date: 2014-05-27
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
	
	/**
    * 修改套餐课程下的课程排序
    * @param course
    */
    public void updateCoursePackageOrderNum(CoursePackage coursePackage);
    /**
     * 获取CoursePackage list
     * @param coursePackage
     * @return
     */
    public List<CoursePackage> getCoursePackageList(CoursePackage coursePackage);
    /**
     * 批量添加CoursePackage
     * @param coursePackageList
     */
    public void createCoursePackageBatch(List<CoursePackage> coursePackageList);
}
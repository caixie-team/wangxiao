package io.wangxiao.edu.home.dao.course;

import io.wangxiao.edu.home.entity.course.CoursePackage;

import java.util.List;

public interface CoursePackageDao {
	  /**
     * 添加CoursePackage
     */
      void addCoursePackageBatch(List<CoursePackage> coursePackageList);
    /**
     * 通过id查询CoursePackage
     */
    CoursePackage queryCoursePackageById(CoursePackage coursePackage);
    /**
	 * 删除CoursePackage
	 */
    void delCoursePackage(CoursePackage coursePackage);
	
	/**
     * 修改套餐课程下的课程排序
     * @param course
     */
    void updateCoursePackageOrderNum(CoursePackage coursePackage);

    /**
     * 根据课程id删除套餐
     * @param courseId
     */
    void deleteCoursePackageByCourseId(Long courseId);
    /**
     * 获取CoursePackage list
     * @param coursePackage
     * @return
     */
    List<CoursePackage> getCoursePackageList(CoursePackage coursePackage);

    /**
     * 获取套餐下课程数量
     * @param id
     * @return
     */
    int getCoursePackageCount(Long id);
}
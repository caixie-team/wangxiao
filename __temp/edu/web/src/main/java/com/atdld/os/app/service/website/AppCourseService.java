package com.atdld.os.app.service.website;

import java.util.List;

import com.atdld.os.app.entity.website.AppCourse;
import com.atdld.os.app.entity.website.AppCourseDto;
import com.atdld.os.app.entity.website.QueryAppCourseCondition;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.course.CourseDto;
import com.atdld.os.edu.entity.course.QueryCourse;


/**
 * app课程Service
 */
public interface AppCourseService {
	/**
	 * 查询app课程
	 * @param query
	 * @param page
	 * @return
	 */
	public List<AppCourseDto> queryAppMainCourse(QueryAppCourseCondition query,PageEntity page);
	/**
	 * 删除app课程
	 * @param id
	 */
	public void delAppCourseById(long id);
	/**
	 * 查询课程
	 * @param course
	 * @param page
	 * @return
	 */
	public List<CourseDto> getCourseListPage(QueryCourse course, PageEntity page);
	/**
	 * 查询app课程
	 * @param course
	 * @param page
	 * @return
	 */
	public List<CourseDto> getAppCourseListPage(QueryCourse course, PageEntity page);
	/**
	 * 选取app课程
	 * @param appCourse
	 */
	public void createAppMainCourse(AppCourse appCourse);
	/**
	 * 查询课程是否已选取
	 * @param id
	 * @return
	 */
	public int getAppCourseById(Long id);
	
	/**
	 * 批量删除app课程
	 * @param ids id字符串集合
	 */
	public void delAppCourseBatch(String ids);
}

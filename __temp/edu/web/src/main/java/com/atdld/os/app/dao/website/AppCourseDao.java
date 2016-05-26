package com.atdld.os.app.dao.website;

import java.util.List;

import com.atdld.os.app.entity.website.AppCourse;
import com.atdld.os.app.entity.website.AppCourseDto;
import com.atdld.os.app.entity.website.QueryAppCourseCondition;
import com.atdld.os.core.entity.PageEntity;


/**
 * app课程Dao
 */
public interface AppCourseDao {
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
	 * 创建app课程
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

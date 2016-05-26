package com.atdld.os.app.service.impl.website;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.app.dao.website.AppCourseDao;
import com.atdld.os.app.entity.website.AppCourse;
import com.atdld.os.app.entity.website.AppCourseDto;
import com.atdld.os.app.entity.website.QueryAppCourseCondition;
import com.atdld.os.app.service.website.AppCourseService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.dao.course.CourseDao;
import com.atdld.os.edu.entity.course.CourseDto;
import com.atdld.os.edu.entity.course.QueryCourse;


@Service("appCourseService")
public class AppCourseServiceImpl implements AppCourseService{
	@Autowired
	private AppCourseDao appCourseDao;
	@Autowired
	private CourseDao courseDao;

	@Override
	public List<AppCourseDto> queryAppMainCourse(QueryAppCourseCondition query,
			PageEntity page) {
		// TODO Auto-generated method stub
		return appCourseDao.queryAppMainCourse(query, page);
	}

	@Override
	public void delAppCourseById(long id) {
		// TODO Auto-generated method stub
		appCourseDao.delAppCourseById(id);
	}

	@Override
	public List<CourseDto> getCourseListPage(QueryCourse course, PageEntity page) {
		// TODO Auto-generated method stub
		return courseDao.getCourseListPage(course, page);
	}

	@Override
	public void createAppMainCourse(AppCourse appCourse) {
		// TODO Auto-generated method stub
		appCourseDao.createAppMainCourse(appCourse);
	}

	@Override
	public int getAppCourseById(Long id) {
		// TODO Auto-generated method stub
		return appCourseDao.getAppCourseById(id);
	}

	@Override
	public List<CourseDto> getAppCourseListPage(QueryCourse course,
			PageEntity page) {
		// TODO Auto-generated method stub
		return courseDao.getAppCourseListPage(course, page);
	}
	
	/**
	 * 批量删除app课程
	 * @param ids id字符串集合
	 */
	public void delAppCourseBatch(String ids){
		appCourseDao.delAppCourseBatch(ids.substring(0, (ids.length()-1)));
	}
	
}

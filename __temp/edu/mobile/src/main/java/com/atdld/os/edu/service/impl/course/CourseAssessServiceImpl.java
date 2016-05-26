package com.atdld.os.edu.service.impl.course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.constants.enums.CourseProfileType;
import com.atdld.os.edu.dao.course.CourseAssessDao;
import com.atdld.os.edu.entity.course.CourseAssess;
import com.atdld.os.edu.entity.course.CourseProfile;
import com.atdld.os.edu.entity.course.QueryCourseAssess;
import com.atdld.os.edu.service.course.CourseAssessService;
import com.atdld.os.edu.service.course.CourseProfileService;
import com.atdld.os.edu.service.user.UserExpandService;

/**
 * CourseAssess管理接口 User:  Date: 2014-05-27
 */
@Service("courseAssessService")
public class CourseAssessServiceImpl implements CourseAssessService {

	@Autowired
	private CourseAssessDao courseAssessDao;
	@Autowired
	private UserExpandService userExpandService;
	@Autowired
	private CourseProfileService courseProfileService;
	@Autowired
	private WebHessianService webHessianService;
	/**
	 * 添加CourseAssess
	 * 
	 * @param courseAssess
	 *            要添加的CourseAssess
	 * @return id
	 */
	/*public java.lang.Long addCourseAssess(CourseAssess courseAssess) {
		//更新课程的笔记数量
        Map<String,String> map=new HashMap<String, String>();
        map.put("type", CourseProfileType.commentcount.toString());//类型
        map.put("courseId", courseAssess.getCourseId()+"");//课程id
        map.put("count", 1L+"");//1
        map.put("operation",CourseProfile.ADD);//操作
        webHessianService.updateCourseProfile(map);
		return courseAssessDao.addCourseAssess(courseAssess);
	}*/

	/**
	 * 根据id删除一个CourseAssess
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteCourseAssessById(Long id) {
		courseAssessDao.deleteCourseAssessById(id);
	}

	/**
	 * 修改CourseAssess
	 * 
	 * @param courseAssess
	 *            要修改的CourseAssess
	 */
	public void updateCourseAssess(CourseAssess courseAssess) {
		courseAssessDao.updateCourseAssess(courseAssess);
	}

	/**
	 * 根据id获取单个CourseAssess对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return CourseAssess
	 */
	public QueryCourseAssess getCourseAssessById(Long id) {
		return courseAssessDao.getCourseAssessById(id);
	}

	/**
	 * 根据条件获取CourseAssess列表
	 * 
	 * @param courseAssess
	 *            查询条件
	 * @return List<CourseAssess>
	 */
	public List<CourseAssess> getCourseAssessList(CourseAssess courseAssess) {
		return courseAssessDao.getCourseAssessList(courseAssess);
	}

	/**
	 * 根据条件获取CourseAssess列表
	 * 
	 * @param courseAssess
	 *            查询条件
	 * @return List<CourseAssess>
	 */
	public List<QueryCourseAssess> getCourseAssessListPage(CourseAssess courseAssess, PageEntity page) {
		return courseAssessDao.getCourseAssessListPage(courseAssess, page);
	}
	/**
	 * 后台查询课程评论列表
	 * 
	 * @param queryCourseAssess
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	public List<QueryCourseAssess> getAdminCourseAssessList(QueryCourseAssess queryCourseAssess, PageEntity page) throws Exception {
		List<QueryCourseAssess> queryCourseAssessList=courseAssessDao.getAdminCourseAssessList(queryCourseAssess, page);
		return queryCourseAssessList;
	}
	/**
	 * 删除课程评论
	 * 
	 * @param courseAssessIds
	 */
	public void delCourseAssessBatch(String courseAssessIds) {
		courseAssessDao.delCourseAssessBatch(courseAssessIds);
	}
}
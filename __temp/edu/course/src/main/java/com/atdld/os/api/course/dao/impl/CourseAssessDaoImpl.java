package com.atdld.os.api.course.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.dao.course.CourseAssessDao;
import com.atdld.os.edu.entity.course.CourseAssess;
import com.atdld.os.edu.entity.course.QueryCourseAssess;

/**
 * 
 * CourseAssess User:  Date: 2014-05-27
 */
@Repository("courseAssessDao")
public class CourseAssessDaoImpl extends GenericDaoImpl implements CourseAssessDao {
	/**
	 * 添加CourseAssess
	 * 
	 * @param courseAssess
	 *            要添加的CourseAssess
	 * @return id
	 */
	public Long addCourseAssess(CourseAssess courseAssess) {
		return this.insert("CourseAssessMapper.createCourseAssess", courseAssess);
	}
	/**
	 * 根据id删除一个CourseAssess
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteCourseAssessById(Long id) {
		this.delete("CourseAssessMapper.deleteCourseAssessById", id);
	}
	/**
	 * 修改CourseAssess
	 * 
	 * @param courseAssess
	 *            要修改的CourseAssess
	 */
	public void updateCourseAssess(CourseAssess courseAssess) {
		this.update("CourseAssessMapper.updateCourseAssess", courseAssess);
	}
	/**
	 * 根据id获取单个QueryCourseAssess对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return QueryCourseAssess
	 */
	public QueryCourseAssess getCourseAssessById(Long id) {
		return this.selectOne("CourseAssessMapper.getCourseAssessById", id);
	}
	/**
	 * 根据条件获取CourseAssess列表
	 * 
	 * @param courseAssess
	 *            查询条件
	 * @return List<CourseAssess>
	 */
	public List<CourseAssess> getCourseAssessList(CourseAssess courseAssess) {
		return this.selectList("CourseAssessMapper.getCourseAssessList", courseAssess);
	}

	/**
	 * 根据条件获取CourseAssess列表
	 * 
	 * @param courseAssess
	 *            查询条件
	 * @return List<CourseAssess>
	 */
	public List<QueryCourseAssess> getCourseAssessListPage(CourseAssess courseAssess, PageEntity page) {
		return this.queryForListPage("CourseAssessMapper.getCourseAssessListPage", courseAssess, page);
	}
	
	/**
	 * 如果课程为套餐课程，查询该套餐课程下所有课程的评论
	 * @param courseAssess
	 * @param page
	 * @return
	 */
	public List<QueryCourseAssess> getAllCourseAssessListPage(CourseAssess courseAssess, PageEntity page){
		return this.queryForListPage("CourseAssessMapper.getAllCourseAssessListPage", courseAssess, page);
	}

	/**
	 * 后台查询课程评论列表
	 * 
	 * @param queryCourseAssess
	 * @param page
	 * @return
	 */
	public List<QueryCourseAssess> getAdminCourseAssessList(QueryCourseAssess queryCourseAssess, PageEntity page) {
		return this.queryForListPage("CourseAssessMapper.getAdminCourseAssessList", queryCourseAssess, page);
	}
	/**
	 * 删除课程评论
	 * 
	 * @param courseAssessIds
	 */
	public void delCourseAssessBatch(String courseAssessIds) {
		this.delete("CourseAssessMapper.delCourseAssessBatch", courseAssessIds.replaceAll(" ", "").split(","));
	}
}

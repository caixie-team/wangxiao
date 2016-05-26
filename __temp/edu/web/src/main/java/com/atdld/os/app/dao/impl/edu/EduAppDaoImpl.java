package com.atdld.os.app.dao.impl.edu;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.app.dao.edu.EduAppDao;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;

@Repository("eduAppDao")
public class EduAppDaoImpl extends GenericDaoImpl implements EduAppDao{

	@Override
	public List<Map<String, Object>> queryAppAllCourse(Map<String, Object> map, PageEntity page) {
		return queryForListPage("EduAppMapper.queryAppAllCourse", map, page);
	}

	@Override
	public List<Map<String,Object>> getUserListForLogin(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("EduAppMapper.getUserListForLogin", map);
	}

	@Override
	public List<Map<String, Object>> getUserListForTelLogin(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("EduAppMapper.getUserListForTelLogin", map);
	}

	@Override
	public List<Map<String, Object>> getUserList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("EduAppMapper.getUserList", map);
	}

	@Override
	public int getUserByMobile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectOne("EduAppMapper.getUserByMobile", map);
	}

	@Override
	public List<Map<String, Object>> queryArticleListPage(
			Map<String, Object> map, PageEntity page) {
		// TODO Auto-generated method stub
		return queryForListPage("EduAppMapper.queryArticleListPage", map, page);
	}

	@Override
	public Map<String, Object> getArticleById(Long id) {
		// TODO Auto-generated method stub
		return selectOne("EduAppMapper.getArticleById", id);
	}

	@Override
	public Map<String, Object> queryArticleUpOrDown(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectOne("EduAppMapper.queryArticleUpOrDown", map);
	}

	@Override
	public List<Map<String,Object>> getCourseFavoritesByUserId(Map<String ,Object> map,
			PageEntity page) {
		// TODO Auto-generated method stub
		return queryForListPage("EduAppMapper.getCourseFavoritesByUserId",map,page);
	}

	@Override
	public List<Map<String, Object>> getCourseStudyhistoryListByCondition(
			Map<String, Object> map, PageEntity page) {
		// TODO Auto-generated method stub
		return queryForListPage("EduAppMapper.getCourseStudyhistoryListByCondition", map, page);
	}

	@Override
	public Map<String, Object> getCourseById(Long id) {
		// TODO Auto-generated method stub
		return selectOne("EduAppMapper.getCourseById", id);
	}

	@Override
	public Map<String, Object> getCourseKpointById(Long id) {
		// TODO Auto-generated method stub
		return selectOne("EduAppMapper.getCourseKpointById", id);
	}

	@Override
	public Map<String, Object> getUserById(Long id) {
		// TODO Auto-generated method stub
		return selectOne("EduAppMapper.getUserById", id);
	}

	@Override
	public void addUserFeedback(Map<String, Object> map) {
		// TODO Auto-generated method stub
		insert("EduAppMapper.createUserFeedback", map);
	}

	@Override
	public List<Map<String, Object>> getWebWebsiteCourseDetails(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("EduAppMapper.getWebWebsiteCourseDetails", map);
	}

	@Override
	public void insertWebsiteUse(Map<String, Object> map) {
		// TODO Auto-generated method stub
		insert("EduAppMapper.insertWebsiteUse", map);
	}

	@Override
	public void updateWebsiteUseForEndtime(Map<String, Object> map) {
		// TODO Auto-generated method stub
		update("EduAppMapper.updateWebsiteUseForEndtime", map);
	}

	@Override
	public List<Map<String, Object>> queryAppSubjectCourse(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return selectList("EduAppMapper.queryAppSubjectCourse", map);
	}

	@Override
	public void deletePlayRecord(String ids) {
		delete("EduAppMapper.deletePlayRecord", ids);
	}

}

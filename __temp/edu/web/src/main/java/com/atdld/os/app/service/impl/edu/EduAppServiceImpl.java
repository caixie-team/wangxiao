package com.atdld.os.app.service.impl.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.app.dao.edu.EduAppDao;
import com.atdld.os.app.service.edu.EduAppService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.dao.course.CourseTeacherDao;
import com.atdld.os.edu.entity.course.Teacher;

@Service("eduAppService")
public class EduAppServiceImpl implements EduAppService{
	@Autowired
	private EduAppDao eduAppDao;
	
	@Autowired
	private CourseTeacherDao courseTeacherDao;
	
	@Override
	public List<Map<String, Object>> queryAppAllCourse(Map<String, Object> map,PageEntity page) {
		return eduAppDao.queryAppAllCourse(map, page);
	}

	@Override
	public List<Map<String,Object>> getUserListForLogin(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return eduAppDao.getUserListForLogin(map);
	}

	@Override
	public List<Map<String, Object>> getUserListForTelLogin(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return eduAppDao.getUserListForTelLogin(map);
	}

	@Override
	public List<Map<String, Object>> getUserList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return eduAppDao.getUserList(map);
	}

	@Override
	public int getUserByMobile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return eduAppDao.getUserByMobile(map);
	}

	@Override
	public List<Map<String, Object>> queryArticleListPage(
			Map<String, Object> map, PageEntity page) {
		// TODO Auto-generated method stub
		return eduAppDao.queryArticleListPage(map, page);
	}

	@Override
	public Map<String, Object> getArticleById(Long id) {
		// TODO Auto-generated method stub
		return eduAppDao.getArticleById(id);
	}

	@Override
	public Map<String, Object> queryArticleUpOrDown(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return eduAppDao.queryArticleUpOrDown(map);
	}

	@Override
	public List<Map<String, Object>> getCourseFavoritesByUserId(
			Map<String, Object> map, PageEntity page) {
		// TODO Auto-generated method stub
		return eduAppDao.getCourseFavoritesByUserId(map, page);
	}

	@Override
	public List<Map<String, Object>> getCourseStudyhistoryListByCondition(
			Map<String, Object> map, PageEntity page) {
		// TODO Auto-generated method stub
		return eduAppDao.getCourseStudyhistoryListByCondition(map, page);
	}

	@Override
	public Map<String, Object> getCourseById(Long id) {
		// TODO Auto-generated method stub
		return eduAppDao.getCourseById(id);
	}

	@Override
	public Map<String, Object> getCourseKpointById(Long id) {
		// TODO Auto-generated method stub
		return eduAppDao.getCourseKpointById(id);
	}

	@Override
	public Map<String, Object> getUserById(Long id) {
		// TODO Auto-generated method stub
		return eduAppDao.getUserById(id);
	}

	@Override
	public void addUserFeedback(Map<String, Object> map) {
		// TODO Auto-generated method stub
		eduAppDao.addUserFeedback(map);
	}

	@Override
	public List<Map<String, Object>> getWebWebsiteCourseDetails(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return eduAppDao.getWebWebsiteCourseDetails(map);
	}

	@Override
	public void insertWebsiteUse(Map<String, Object> map) {
		// TODO Auto-generated method stub
		eduAppDao.insertWebsiteUse(map);
	}

	@Override
	public void updateWebsiteUseForEndtime(Map<String, Object> map) {
		// TODO Auto-generated method stub
		eduAppDao.updateWebsiteUseForEndtime(map);
	}

	@Override
	public List<Map<String, Object>> queryAppSubjectCourse(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> courseDtos=eduAppDao.queryAppSubjectCourse(map);
		 if (ObjectUtils.isNotNull(courseDtos)) {
	            List<Long> list = new ArrayList<Long>();
	            for (Map<String,Object> courseDto : courseDtos) {
	                list.add(Long.parseLong(courseDto.get("courseId")+""));
	            }
	            // 获取讲师的list
	            Map<Long, List<Teacher>> teacherMap = courseTeacherDao.getCourseTeacherListByCourse(list);
	            // 将讲师的list放到旧的list中
	            for (Map<String,Object> courseDto : courseDtos) {
	            	Long id;
	            	if((courseDto.get("id")+"").equals("null")){
	            		id=0l;
	            	}
	            	else{
		            	id=Long.parseLong(courseDto.get("id")+"");
	            	}
 	                courseDto.put("teacherList",teacherMap.get(id));
	            }
	        }
	        return courseDtos;
	}

	@Override
	public void deletePlayRecord(String ids) {
		eduAppDao.deletePlayRecord(ids);
	}


}

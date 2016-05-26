package io.wangxiao.edu.app.service.impl.edu;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.app.dao.edu.EduAppDao;
import io.wangxiao.edu.app.service.edu.EduAppService;
import io.wangxiao.edu.home.dao.course.CourseTeacherDao;
import io.wangxiao.edu.home.entity.course.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("eduAppService")
public class EduAppServiceImpl implements EduAppService {
    @Autowired
    private EduAppDao eduAppDao;

    @Autowired
    private CourseTeacherDao courseTeacherDao;

    @Override
    public List<Map<String, Object>> queryAppAllCourse(Map<String, Object> map, PageEntity page) {
        return eduAppDao.queryAppAllCourse(map, page);
    }

    @Override
    public List<Map<String, Object>> getUserListForLogin(Map<String, Object> map) {
        return eduAppDao.getUserListForLogin(map);
    }

    @Override
    public List<Map<String, Object>> getUserListForTelLogin(
            Map<String, Object> map) {
        return eduAppDao.getUserListForTelLogin(map);
    }

    @Override
    public List<Map<String, Object>> getUserList(Map<String, Object> map) {
        return eduAppDao.getUserList(map);
    }

    @Override
    public int getUserByMobile(Map<String, Object> map) {
        return eduAppDao.getUserByMobile(map);
    }

    @Override
    public List<Map<String, Object>> queryArticleListPage(
            Map<String, Object> map, PageEntity page) {
        return eduAppDao.queryArticleListPage(map, page);
    }

    @Override
    public Map<String, Object> getArticleById(Long id) {
        return eduAppDao.getArticleById(id);
    }

    @Override
    public Map<String, Object> queryArticleUpOrDown(Map<String, Object> map) {
        return eduAppDao.queryArticleUpOrDown(map);
    }

    @Override
    public List<Map<String, Object>> getCourseFavoritesByUserId(
            Map<String, Object> map, PageEntity page) {
        return eduAppDao.getCourseFavoritesByUserId(map, page);
    }

    @Override
    public List<Map<String, Object>> getCourseStudyhistoryListByCondition(
            Map<String, Object> map, PageEntity page) {
        return eduAppDao.getCourseStudyhistoryListByCondition(map, page);
    }


    public Map<String, Object> getCourseById(Long id) {

        return eduAppDao.getCourseById(id);
    }

    @Override
    public Map<String, Object> getCourseKpointById(Long id) {
        return eduAppDao.getCourseKpointById(id);
    }

    @Override
    public Map<String, Object> getUserById(Long id) {
        return eduAppDao.getUserById(id);
    }

    @Override
    public void addUserFeedback(Map<String, Object> map) {
        eduAppDao.addUserFeedback(map);
    }


    public Map<String, List<Map<String, Object>>> getWebWebsiteCourseDetails() {
        Map<String, List<Map<String, Object>>> recommendMap = new HashMap();
        List<Map<String, Object>> recomendList = eduAppDao.getWebWebsiteCourseDetails();
        if (recomendList != null && recomendList.size() > 0) {
            List<Map<String, Object>> tempList = new ArrayList<>();
            String recommendId = recomendList.get(0).get("recommendId").toString();
            for (Map<String, Object> course : recomendList) {
                if (!course.get("recommendId").toString().equals(recommendId)) {
                    recommendId = course.get("recommendId").toString();
                    recommendMap.put(recommendId, tempList);
                    tempList = new ArrayList<>();
                }
                tempList.add(course);
            }
            recommendMap.put(recommendId, tempList);
        }
        return recommendMap;
    }

    @Override
    public void insertWebsiteUse(Map<String, Object> map) {

        eduAppDao.insertWebsiteUse(map);
    }

    @Override
    public void updateWebsiteUseForEndtime(Map<String, Object> map) {
        eduAppDao.updateWebsiteUseForEndtime(map);
    }

    @Override
    public List<Map<String, Object>> queryAppSubjectCourse(
            Map<String, Object> map) {

        List<Map<String, Object>> courseDtos = eduAppDao.queryAppSubjectCourse(map);
        if (ObjectUtils.isNotNull(courseDtos)) {
            List<Long> list = new ArrayList<Long>();
            for (Map<String, Object> courseDto : courseDtos) {
                list.add(Long.parseLong(courseDto.get("courseId") + ""));
            }
            // 获取讲师的list
            Map<Long, List<Teacher>> teacherMap = courseTeacherDao.getCourseTeacherListByCourse(list);
            // 将讲师的list放到旧的list中
            for (Map<String, Object> courseDto : courseDtos) {
                Long id;
                if ((courseDto.get("id") + "").equals("null")) {
                    id = 0l;
                } else {
                    id = Long.parseLong(courseDto.get("id") + "");
                }
                courseDto.put("teacherList", teacherMap.get(id));
            }
        }
        return courseDtos;
    }

    @Override
    public void deletePlayRecord(String ids) {
        eduAppDao.deletePlayRecord(ids);
    }


}

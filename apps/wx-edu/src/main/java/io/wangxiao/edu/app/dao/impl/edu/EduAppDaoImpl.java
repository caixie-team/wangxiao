package io.wangxiao.edu.app.dao.impl.edu;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.dao.edu.EduAppDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("eduAppDao")
public class EduAppDaoImpl extends GenericDaoImpl implements EduAppDao {

    @Override
    public List<Map<String, Object>> queryAppAllCourse(Map<String, Object> map, PageEntity page) {
        return queryForListPage("EduAppMapper.queryAppAllCourse", map, page);
    }

    @Override
    public List<Map<String, Object>> getUserListForLogin(Map<String, Object> map) {
        return selectList("EduAppMapper.getUserListForLogin", map);
    }

    @Override
    public List<Map<String, Object>> getUserListForTelLogin(
            Map<String, Object> map) {
        return selectList("EduAppMapper.getUserListForTelLogin", map);
    }

    @Override
    public List<Map<String, Object>> getUserList(Map<String, Object> map) {
        return selectList("EduAppMapper.getUserList", map);
    }

    @Override
    public int getUserByMobile(Map<String, Object> map) {
        return selectOne("EduAppMapper.getUserByMobile", map);
    }

    @Override
    public List<Map<String, Object>> queryArticleListPage(Map<String, Object> map, PageEntity page) {
        return queryForListPage("EduAppMapper.queryArticleListPage", map, page);
    }

    @Override
    public Map<String, Object> getArticleById(Long id) {
        return selectOne("EduAppMapper.getArticleById", id);
    }

    @Override
    public Map<String, Object> queryArticleUpOrDown(Map<String, Object> map) {
        return selectOne("EduAppMapper.queryArticleUpOrDown", map);
    }

    @Override
    public List<Map<String, Object>> getCourseFavoritesByUserId(Map<String, Object> map,
                                                                PageEntity page) {
        return queryForListPage("EduAppMapper.getCourseFavoritesByUserId", map, page);
    }

    @Override
    public List<Map<String, Object>> getCourseStudyhistoryListByCondition(
            Map<String, Object> map, PageEntity page) {
        return queryForListPage("EduAppMapper.getCourseStudyhistoryListByCondition", map, page);
    }

    @Override
    public Map<String, Object> getCourseById(Long id) {
        return selectOne("EduAppMapper.getCourseById", id);
    }

    @Override
    public Map<String, Object> getCourseKpointById(Long id) {
        return selectOne("EduAppMapper.getCourseKpointById", id);
    }

    @Override
    public Map<String, Object> getUserById(Long id) {
        return selectOne("EduAppMapper.getUserById", id);
    }

    @Override
    public void addUserFeedback(Map<String, Object> map) {
        insert("EduAppMapper.createUserFeedback", map);
    }

    @Override
    public List<Map<String, Object>> getWebWebsiteCourseDetails() {
        return selectList("EduAppMapper.getWebWebsiteCourseDetails", null);
    }

    @Override
    public void insertWebsiteUse(Map<String, Object> map) {
        insert("EduAppMapper.insertWebsiteUse", map);
    }

    @Override
    public void updateWebsiteUseForEndtime(Map<String, Object> map) {
        update("EduAppMapper.updateWebsiteUseForEndtime", map);
    }

    @Override
    public List<Map<String, Object>> queryAppSubjectCourse(Map<String, Object> map) {
        return selectList("EduAppMapper.queryAppSubjectCourse", map);
    }

    @Override
    public void deletePlayRecord(String ids) {
        delete("EduAppMapper.deletePlayRecord", ids);
    }

}

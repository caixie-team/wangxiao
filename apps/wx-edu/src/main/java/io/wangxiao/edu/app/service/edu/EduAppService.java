package io.wangxiao.edu.app.service.edu;

import io.wangxiao.commons.entity.PageEntity;

import java.util.List;
import java.util.Map;


public interface EduAppService {

    /**
     * app查询课程列表专用
     *
     * @param map  查询条件
     * @param page 分页条件
     * @return
     */
    List<Map<String, Object>> queryAppAllCourse(Map<String, Object> map, PageEntity page);

    /**
     * app根据邮箱查询用户
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> getUserListForLogin(Map<String, Object> map);

    /**
     * app根据手机号码查询用户
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> getUserListForTelLogin(Map<String, Object> map);

    /**
     * app查询邮箱是否被注册
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> getUserList(Map<String, Object> map);

    /**
     * app查询手机号是否被注册
     *
     * @param map
     * @return
     */
    int getUserByMobile(Map<String, Object> map);

    /**
     * app查询资讯列表
     *
     * @param map
     * @param page
     * @return
     */
    List<Map<String, Object>> queryArticleListPage(Map<String, Object> map, PageEntity page);

    /**
     * app通过id获取资讯
     *
     * @param id
     * @return
     */
    Map<String, Object> getArticleById(Long id);

    /**
     * app查询上下篇
     *
     * @param map
     * @return
     */
    Map<String, Object> queryArticleUpOrDown(Map<String, Object> map);

    /**
     * app收藏课程列表
     *
     * @param userId
     * @param page
     * @return
     */
    List<Map<String, Object>> getCourseFavoritesByUserId(Map<String, Object> map, PageEntity page);

    /**
     * app我的学习记录
     *
     * @param map
     * @param page
     * @return
     */
    List<Map<String, Object>> getCourseStudyhistoryListByCondition(Map<String, Object> map, PageEntity page);

    /**
     * app根据课程id查询课程信息
     *
     * @param id
     * @return
     */
    Map<String, Object> getCourseById(Long id);

    /**
     * app视频信息
     *
     * @param id
     * @return
     */
    Map<String, Object> getCourseKpointById(Long id);

    /**
     * app获取用户信息
     *
     * @param id
     * @return
     */
    Map<String, Object> getUserById(Long id);

    /**
     * app反馈信息
     *
     * @param map
     */
    void addUserFeedback(Map<String, Object> map);

    /**
     * app推荐课程
     *
     * @return
     */
    Map<String, List<Map<String, Object>>> getWebWebsiteCourseDetails();

    /**
     * app统计用户使用记录
     *
     * @param map
     */
    void insertWebsiteUse(Map<String, Object> map);

    /**
     * app修改统计用户使用记录
     *
     * @param map
     */
    void updateWebsiteUseForEndtime(Map<String, Object> map);

    /**
     * 查询同类课程
     *
     * @param subjectId
     * @param courseId
     * @param num
     * @return
     */
    List<Map<String, Object>> queryAppSubjectCourse(Map<String, Object> map);

    /***
     * 删除播放记录
     *
     * @param ids
     */
    void deletePlayRecord(String ids);
}

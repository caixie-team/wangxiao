package io.wangxiao.edu.home.service.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.user.GroupMiddleCourse;
import io.wangxiao.edu.home.entity.website.WebsiteCourseDetail;
import io.wangxiao.edu.home.entity.website.WebsiteCourseDetailDTO;

import java.util.List;
import java.util.Map;

public interface WebsiteCourseDetailService {

    /**
     * 添加推荐课程
     *
     * @param websiteCourseDetail
     */
    void addWebsiteCourseDetail(List<WebsiteCourseDetail> websiteCourseDetails);

    /**
     * 查询推荐课程列表
     *
     * @param websiteCourseDetail
     * @param page
     * @return
     */
    List<WebsiteCourseDetailDTO> queryWebsiteCourseDetailList(WebsiteCourseDetailDTO websiteCourseDetailDTO, PageEntity page);

    /**
     * 根据id删除推荐课程
     *
     * @param id
     * @return Long
     * @throws Exception
     */
    void deleteWebsiteCourseDetail(Long id);

    /**
     * 查询单个推荐课程分类
     *
     * @param id
     * @return
     */
    WebsiteCourseDetailDTO queryWebsiteCourseDetailById(Long id);

    /**
     * 更新推荐课程
     *
     * @param reSortId
     * @return Long
     */
    void updateWebsiteCourseDetail(WebsiteCourseDetail websiteCourseDetail);

    /**
     * 分类查找推荐课程集合
     *
     * @param id
     */
    List<WebsiteCourseDetail> getWebsiteCourseDetails(Long id);

    /**
     * web推荐课程集合
     *
     * @param id
     */
    Map<String, Object> showWebsiteCourseDetails();

    /**
     * 根据条件删除推荐课程
     */
    void deleteWebsiteCourseDetail(WebsiteCourseDetailDTO websiteCourseDetailDTO);

    /**
     * 添加学员组课程
     *
     * @param websiteCourseDetail
     */
    void addGroupCourseList(List<GroupMiddleCourse> groupMiddleCourseList);

    /**
     * 删除学员组课程
     *
     * @param websiteCourseDetail
     */
    void deleteGroupCourseList(Long courseId);

    /**
     * 获取课程下的学员组
     *
     * @param addGroupMiddleCourseList
     */
    List<GroupMiddleCourse> getGroupMiddleCourse(GroupMiddleCourse groupMiddleCourse);

    void deleteGroupCourse(GroupMiddleCourse GroupMiddleCourse);
}
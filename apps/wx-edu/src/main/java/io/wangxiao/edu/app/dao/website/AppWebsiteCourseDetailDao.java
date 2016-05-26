package io.wangxiao.edu.app.dao.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.entity.website.AppWebsiteCourseDetail;
import io.wangxiao.edu.app.entity.website.AppWebsiteCourseDetailDTO;

import java.util.List;
import java.util.Map;

/**
 * WebsiteCourseDetail管理接口
 */
public interface AppWebsiteCourseDetailDao {

    /**
     * 添加推荐课程
     */
    void addWebsiteCourseDetail(List<AppWebsiteCourseDetail> websiteCourseDetails);

    /**
     * 查询推荐课程列表
     *
     * @param page
     * @return
     */
    List<AppWebsiteCourseDetailDTO> queryWebsiteCourseDetailList(AppWebsiteCourseDetailDTO appWebsiteCourseDetailDTO, PageEntity page);

    /**
     * 查询推荐课程列表
     *
     * @return
     */
    List<AppWebsiteCourseDetailDTO> queryWebsiteCourseDetailList(AppWebsiteCourseDetail appWebsiteCourseDetail);

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
    AppWebsiteCourseDetailDTO queryWebsiteCourseDetailById(Long id);

    /**
     * 更新推荐课程
     *
     * @return Long
     */
    void updateWebsiteCourseDetail(AppWebsiteCourseDetail websiteCourseDetail);

    /**
     * 分类查找推荐课程集合
     *
     * @param id
     */
    List<AppWebsiteCourseDetail> getWebsiteCourseDetails(Long id);

    /**
     * web推荐课程集合
     */
    List<AppWebsiteCourseDetailDTO> getWebWebsiteCourseDetails(Map<String, Object> map);

    /**
     * 根据条件删除推荐课程
     */
    void deleteWebsiteCourseDetail(AppWebsiteCourseDetailDTO appWebsiteCourseDetailDTO);

}
package io.wangxiao.edu.app.service.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.entity.website.AppWebsiteCourseDetail;
import io.wangxiao.edu.app.entity.website.AppWebsiteCourseDetailDTO;

import java.util.List;

/**
 * WebsiteCourseDetail管理接口
 */
public interface AppWebsiteCourseDetailService {

    /**
     * 添加推荐课程
     */
    void addWebsiteCourseDetail(List<AppWebsiteCourseDetail> websiteCourseDetails);

    /**
     * 查询推荐课程列表
     *
     * @return
     */
    List<AppWebsiteCourseDetailDTO> queryWebsiteCourseDetailList(AppWebsiteCourseDetailDTO websiteCourseDetailDTO, PageEntity page);

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
     */
    void updateWebsiteCourseDetail(AppWebsiteCourseDetail appWebsiteCourseDetail);

    /**
     * 分类查找推荐课程集合
     *
     * @param id
     */
    List<AppWebsiteCourseDetail> getWebsiteCourseDetails(Long id);

    /**
     * web推荐课程集合
     */
    List<AppWebsiteCourseDetailDTO> showWebsiteCourseDetails(int recommendId, int count);
}
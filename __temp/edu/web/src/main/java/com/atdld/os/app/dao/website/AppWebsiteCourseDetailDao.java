package com.atdld.os.app.dao.website;

import java.util.List;
import java.util.Map;

import com.atdld.os.app.entity.website.AppWebsiteCourseDetail;
import com.atdld.os.app.entity.website.AppWebsiteCourseDetailDTO;
import com.atdld.os.core.entity.PageEntity;

/**
 * WebsiteCourseDetail管理接口
 * User:
 * Date: 2014-05-27
 */
public interface AppWebsiteCourseDetailDao {

	/**
	 * 添加推荐课程
	 */
    public void addWebsiteCourseDetail(List<AppWebsiteCourseDetail> websiteCourseDetails);

    /**
     * 查询推荐课程列表
     * @param page
     * @return
     */
    public List<AppWebsiteCourseDetailDTO> queryWebsiteCourseDetailList(AppWebsiteCourseDetailDTO appWebsiteCourseDetailDTO, PageEntity page);

    /**
     * 查询推荐课程列表
     * @return
     */
    public List<AppWebsiteCourseDetailDTO> queryWebsiteCourseDetailList(AppWebsiteCourseDetail appWebsiteCourseDetail);

    /**
     * 根据id删除推荐课程
     * 
     * @param id
     * @return Long
     * @throws Exception
     */
    public void deleteWebsiteCourseDetail(Long id);

    /**
     * 查询单个推荐课程分类
     * @param id
     * @return
     */
    public AppWebsiteCourseDetailDTO queryWebsiteCourseDetailById(Long id);

    /**
     * 更新推荐课程
     * @return Long
     */
    public void updateWebsiteCourseDetail(AppWebsiteCourseDetail websiteCourseDetail);
    
    /**
     * 分类查找推荐课程集合
     * @param id
     */
    public List<AppWebsiteCourseDetail> getWebsiteCourseDetails(Long id);
    /**
     * web推荐课程集合
     */
    public List<AppWebsiteCourseDetailDTO> getWebWebsiteCourseDetails(Map<String,Object> map);
    /**
     * 根据条件删除推荐课程
     */
    public void deleteWebsiteCourseDetail(AppWebsiteCourseDetailDTO appWebsiteCourseDetailDTO);

}
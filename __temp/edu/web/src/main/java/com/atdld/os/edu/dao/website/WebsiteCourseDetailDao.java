package com.atdld.os.edu.dao.website;
import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.website.WebsiteCourseDetail;
import com.atdld.os.edu.entity.website.WebsiteCourseDetailDTO;

/**
 * WebsiteCourseDetail管理接口
 * User:
 * Date: 2014-05-27
 */
public interface WebsiteCourseDetailDao {

	/**
	 * 添加推荐课程
	 * @param websiteCourseDetail
	 */
    public void addWebsiteCourseDetail(List<WebsiteCourseDetail> websiteCourseDetails);

    /**
     * 查询推荐课程列表
     * @param websiteCourseDetail
     * @param page
     * @return
     */
    public List<WebsiteCourseDetailDTO> queryWebsiteCourseDetailList(WebsiteCourseDetailDTO websiteCourseDetailDTO,PageEntity page);

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
    public WebsiteCourseDetailDTO queryWebsiteCourseDetailById(Long id);

    /**
     * 更新推荐课程
     * 
     * @param reSortId
     * @return Long
     */
    public void updateWebsiteCourseDetail(WebsiteCourseDetail websiteCourseDetail);
    
    /**
     * 分类查找推荐课程集合
     * @param id
     */
    public List<WebsiteCourseDetail> getWebsiteCourseDetails(Long id);
    /**
     * web推荐课程集合
     * @param id
     */
    public List<WebsiteCourseDetailDTO> getWebWebsiteCourseDetails();
    /**
     * 根据条件删除推荐课程
     */
    public void deleteWebsiteCourseDetail(WebsiteCourseDetailDTO websiteCourseDetailDTO);

}
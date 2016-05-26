package co.bluepx.edu.website.dao;

import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.core.entity.PageEntity;
import co.bluepx.edu.website.entity.WebsiteCourseDetail;
import co.bluepx.edu.website.entity.WebsiteCourseDetailDTO;

import java.util.List;

/**
 * WebsiteCourseDetail管理接口
 */
public interface WebsiteCourseDetailDao extends BaseDao<WebsiteCourseDetail> {

	/**
	 * 添加推荐课程
	 * @param websiteCourseDetail
	 */
    void addWebsiteCourseDetail(List<WebsiteCourseDetail> websiteCourseDetails);

    /**
     * 查询推荐课程列表
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
     * @param id
     */
    List<WebsiteCourseDetail> getWebsiteCourseDetails(Long id);
    /**
     * web推荐课程集合
     * @param id
     */
    List<WebsiteCourseDetailDTO> getWebWebsiteCourseDetails();
    /**
     * 根据条件删除推荐课程
     */
    void deleteWebsiteCourseDetail(WebsiteCourseDetailDTO websiteCourseDetailDTO);

}
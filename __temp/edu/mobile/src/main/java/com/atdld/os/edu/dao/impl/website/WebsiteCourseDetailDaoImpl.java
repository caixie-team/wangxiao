package com.atdld.os.edu.dao.impl.website;

import java.util.List;

import com.atdld.os.edu.entity.website.WebsiteCourseDetail;
import com.atdld.os.edu.entity.website.WebsiteCourseDetailDTO;
import com.atdld.os.edu.dao.website.WebsiteCourseDetailDao;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;

/**
 *
 * WebsiteCourseDetail
 * User:
 * Date: 2014-05-27
 */
 @Repository("websiteCourseDetailDao")
public class WebsiteCourseDetailDaoImpl extends GenericDaoImpl implements WebsiteCourseDetailDao{

	/**
	 * 添加推荐课程
	 * @param websiteCourseDetail
	 */
    public void addWebsiteCourseDetail(List<WebsiteCourseDetail> websiteCourseDetails){
    	this.insert("WebsiteCourseDetailMapper.createWebsiteCourseDetail", websiteCourseDetails);
    }

    /**
     * 查询推荐课程列表
     * @param websiteCourseDetail
     * @param page
     * @return
     */
    public List<WebsiteCourseDetailDTO> queryWebsiteCourseDetailList(WebsiteCourseDetailDTO websiteCourseDetailDTO,PageEntity page){
    	return this.queryForListPage("WebsiteCourseDetailMapper.queryWebsiteCourseDetailList",websiteCourseDetailDTO ,page );
    }

    /**
     * 根据id删除推荐课程
     * 
     * @param id
     * @return Long
     * @throws Exception
     */
    public void deleteWebsiteCourseDetail(Long id){
    	this.delete("WebsiteCourseDetailMapper.delWebsiteCourseDetailById", id);
    }

    /**
     * 查询单个推荐课程分类
     * @param id
     * @return
     */
    public WebsiteCourseDetailDTO queryWebsiteCourseDetailById(Long id){
    	return this.selectOne("WebsiteCourseDetailMapper.getWebsiteCourseDetailDTOById", id);
    }

    /**
     * 更新推荐课程
     * 
     * @param reSortId
     * @return Long
     */
    public void updateWebsiteCourseDetail(WebsiteCourseDetail websiteCourseDetail){
    	this.update("WebsiteCourseDetailMapper.updateWebsiteCourseDetail", websiteCourseDetail);
    }
    
    /**
     * @param id
     */
    public List<WebsiteCourseDetail> getWebsiteCourseDetails(Long id){
    	return this.selectList("WebsiteCourseDetailMapper.getWebsiteCourseDetails", id);
    }
    /**
     * web推荐课程集合
     * @param id
     */
    public List<WebsiteCourseDetailDTO> getWebWebsiteCourseDetails(){
    	return this.selectList("WebsiteCourseDetailMapper.getWebWebsiteCourseDetails",0);
    }
    /**
     * 根据条件删除推荐课程
     */
    public void deleteWebsiteCourseDetail(WebsiteCourseDetailDTO websiteCourseDetailDTO){
    	this.delete("WebsiteCourseDetailMapper.deleteWebsiteCourseDetail", websiteCourseDetailDTO);
    }
}

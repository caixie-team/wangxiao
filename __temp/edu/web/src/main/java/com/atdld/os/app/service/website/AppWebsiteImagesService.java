package com.atdld.os.app.service.website;

import java.util.List;
import java.util.Map;

import com.atdld.os.app.entity.website.AppWebsiteImages;
import com.atdld.os.core.entity.PageEntity;

/**
 * 
 * @ClassName com.atdld.os.edu.service.website.WebsiteImagesService
 * @description
 * @author :
 * @Create Date : 2014年6月9日 下午2:15:10
 */
public interface AppWebsiteImagesService {
	/**
	 * 查询首页banner图
	 *
	 * @return List<AppWebsiteImages>
	 * @throws Exception
	 */
	public Map<String, List<AppWebsiteImages>> getIndexPageBanner(Map<String,Object> paramMap) throws Exception;

	/**
	 * 添加广告图
	 *
	 */
	public void insertWebsiteImages(AppWebsiteImages appWebsiteImages);

	/**
	 * 广告图分页列表
	 * 
	 * @param appWebsiteImages
	 * @param page
	 * @return
	 */
	public List<AppWebsiteImages> getImgPageList(AppWebsiteImages appWebsiteImages, PageEntity page);

	/**
	 * 删除广告图
	 * 
	 * @param ids
	 */
	public void deleteImgByIds(String ids);

	/**
	 * 查询广告图
	 * 
	 * @param id
	 * @return
	 */
	public AppWebsiteImages getWebsiteImagesById(Long id);

	/**
	 * 更新广告图
	 *
	 */
	public void updateWebsiteImages(AppWebsiteImages appWebsiteImages);
}

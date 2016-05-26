package com.atdld.os.app.dao.website;

import java.util.List;
import java.util.Map;

import com.atdld.os.app.entity.website.AppWebsiteImages;
import com.atdld.os.core.entity.PageEntity;


/**
 * 
 * @author jjl
 *
 */
public interface AppWebsiteImagesDao {
	/**
	 * 查询首页banner图
	 * 
	 * @param appWebsiteImages
	 * @return List<WebsiteWebsiteImages>
	 */
	public List<AppWebsiteImages> getIndexPageBanner(Map<String,Object> map);
	/**
	 * 添加广告图
	 */
	public void insertWebsiteImages(AppWebsiteImages appWebsiteImages);

	/**
	 * 广告图分页列表
	 * @param appWebsiteImages
	 * @param page
	 * @return
	 */
	public List<AppWebsiteImages> getImgPageList(AppWebsiteImages appWebsiteImages, PageEntity page);

	/**
	 * 删除广告图
	 * @param ids
	 */
	public void deleteImgByIds(String ids);

	/**
	 * 查询广告图
	 * @param id
	 * @return
	 */
	public AppWebsiteImages getWebsiteImagesById(Long id) ;

	/**
	 * 更新广告图
	 */
	public void updateWebsiteImages(AppWebsiteImages appWebsiteImages);
}

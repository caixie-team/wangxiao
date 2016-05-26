package io.wangxiao.edu.home.dao.website;

import java.util.List;

import io.wangxiao.edu.home.entity.website.WebsiteImageMange;

public interface WebsiteImageMangeDao {
	/**
	 * 得到全部管理图片的列表
	 * @return
	 */
		public List<WebsiteImageMange>  queryWebsiteImageMangeList();
		/**
		 * 添加图片管理
		 * @return
		 */
		public void addimagesMange(WebsiteImageMange websiteImageMange);
		
		/**
		 * 删除图片
		 * @return
		 */
		public void deleteImagesMangeById(long id );
		/**
		 * 得到单个管理图片的信息
		 * @return
		 */
		public WebsiteImageMange getImagesMangeByKey(String key);
			
		/**
		 * 修改图片管理
		 * @param websiteImageMange
		 */
		public void updateImagesMange(WebsiteImageMange websiteImageMange);
		
		/** 
		 * 验证是否有相同的key
		 * @param key
		 */
		public int checkImagesMange(String key);
		/**
		 * 批量删除图片类型
		 * @param ids
		 */
		public void delImageMangebatch(String ids);
		
}

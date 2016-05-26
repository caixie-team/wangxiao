package com.atdld.os.edu.dao.impl.website;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.edu.dao.website.WebsiteImageMangeDao;
import com.atdld.os.edu.entity.website.WebsiteImageMange;
@Repository("websiteImageMangeDao")
public class WebsiteImageMangeDaoImpl extends GenericDaoImpl implements WebsiteImageMangeDao {
	/**
	 * 得到全部管理图片的列表
	 * @return
	 */
		public List<WebsiteImageMange>  queryWebsiteImageMangeList(){
			return this.selectList("WebsiteImageMangeMapper.queryWebsiteImageMangeList", null);
		}
		
		/**
		 * 添加图片管理
		 * @return
		 */
		public void addimagesMange(WebsiteImageMange websiteImageMange){
			 this.insert("WebsiteImageMangeMapper.createWebsiteImageMange", websiteImageMange);
		}
		
		/**
		 * 删除图片
		 * @return
		 */
		public void deleteImagesMangeById(long id ){
			 this.delete("WebsiteImageMangeMapper.deleteImagesMangeById", id);
		}
		/**
		 * 得到单个管理图片的信息
		 * @return
		 */
		public WebsiteImageMange getImagesMangeByKey(String key ){
		  return 	 this.selectOne("WebsiteImageMangeMapper.getImagesMangeByKey", key);
		}
		
		/**
		 * 修改图片管理
		 * @param websiteImageMange
		 */
		public void updateImagesMange(WebsiteImageMange websiteImageMange){
			this.update("WebsiteImageMangeMapper.updateImagesMange", websiteImageMange);
		}
		/** 
		 * 验证是否有相同的key
		 * @param key
		 */
		public int checkImagesMange(String key){
		return this.selectOne("WebsiteImageMangeMapper.checkImagesMange", key);
		}
}

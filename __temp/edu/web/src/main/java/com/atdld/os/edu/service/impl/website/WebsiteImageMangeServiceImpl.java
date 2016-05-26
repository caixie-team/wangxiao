package com.atdld.os.edu.service.impl.website;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.edu.dao.answer.AnswerQuestionDao;
import com.atdld.os.edu.dao.website.WebsiteImageMangeDao;
import com.atdld.os.edu.entity.website.WebsiteImageMange;
import com.atdld.os.edu.service.website.WebsiteImageMangeService;
@Service("websiteImageMangeService")
public class WebsiteImageMangeServiceImpl  implements WebsiteImageMangeService{
	@Autowired
	private WebsiteImageMangeDao websiteImageMangeDao;
	/**
	 * 得到全部管理图片的列表
	 * @author root
	 *
	 */
		public List<WebsiteImageMange>  queryWebsiteImageMangeList(){
			return websiteImageMangeDao.queryWebsiteImageMangeList();
		}
		
		/**
		 * 添加图片管理
		 * @return
		 */
		public  void addimagesMange(WebsiteImageMange websiteImageMange){
			 websiteImageMangeDao.addimagesMange(websiteImageMange);
		}
		
		/**
		 * 删除图片
		 * @return
		 */
		public void deleteImagesMangeById(long id ){
			 websiteImageMangeDao.deleteImagesMangeById(id);
		}
		
		/**
		 * 得到单个管理图片的信息
		 * @return
		 */
		public WebsiteImageMange getImagesMangeByKey(String key ){
			return websiteImageMangeDao.getImagesMangeByKey(key);
		}
		/**
		 * 修改图片管理
		 * @param websiteImageMange
		 */
		public void updateImagesMange(WebsiteImageMange websiteImageMange){
			websiteImageMangeDao.updateImagesMange(websiteImageMange);
		}
		/** 
		 * 验证是否有相同的key
		 * @param key
		 */
		public int checkImagesMange(String key){
		return 	websiteImageMangeDao.checkImagesMange(key);
		}

		
		
}

package com.atdld.os.edu.service.impl.website;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.atdld.os.common.service.ExamHessianService;
import com.atdld.os.common.service.SnsHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.constants.enums.WebSiteProfileType;
import com.atdld.os.edu.dao.website.WebsiteTruncateDao;
import com.atdld.os.edu.entity.website.WebsiteProfile;
import com.atdld.os.edu.entity.website.WebsiteTruncate;
import com.atdld.os.edu.service.website.WebsiteProfileService;
import com.atdld.os.edu.service.website.WebsiteTruncateService;

/**
 * 
 * @author jjl
 * 
 */
@Service("websiteTruncateService")
public class WebsiteTruncateServiceImpl implements WebsiteTruncateService {
	@Autowired
	private WebsiteTruncateDao websiteTruncateDao;

	@Autowired
	private SnsHessianService snsHessianService;
	@Autowired
	private ExamHessianService examHessianService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	/**
	 * 添加清空表
	 *
	 * @param WebsiteTruncate
	 */
	public void insertWebsiteTruncate(WebsiteTruncate websiteTruncate) {
		websiteTruncateDao.insertWebsiteTruncate(websiteTruncate);
	}

	/**
	 * 清空表分页列表
	 * 
	 * @param websiteTruncate
	 * @param page
	 * @return
	 */
	public List<WebsiteTruncate> getTruncatePageList(WebsiteTruncate websiteTruncate, PageEntity page) {
		return websiteTruncateDao.getTruncatePageList(websiteTruncate, page);
	}

	/**
	 * 删除清空表
	 * 
	 * @param ids
	 */
	public void delTruncateByIds(String ids) {
		websiteTruncateDao.delTruncateByIds(ids);
	}

	/**
	 * 查询清空表
	 * 
	 * @param id
	 * @return
	 */
	public WebsiteTruncate getWebsiteTruncateById(Long id) {
		return websiteTruncateDao.getWebsiteTruncateById(id);
	}

	/**
	 * 更新清空表
	 * 
	 * @param WebsiteTruncate
	 */
	public void updateWebsiteTruncate(WebsiteTruncate websiteTruncate) {
		websiteTruncateDao.updateWebsiteTruncate(websiteTruncate);
	}

	/**
	 * 清空表
	 * 
	 * @param ids
	 */
	public void truncateTableByIds(String ids,String type) {
		//清空表集合
		List<WebsiteTruncate> websiteTruncates =websiteTruncateDao.getTruncateList(ids,type);
		for(WebsiteTruncate websiteTruncate: websiteTruncates){
			if(websiteTruncate.getType().equals("web")){
				websiteTruncateDao.truncateTable(websiteTruncate.getTableName());
			}else if(websiteTruncate.getType().equals("exam")){
				// 获得考试的开关是否打开
				Map<String, Object> websiteProfile = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());
				if (ObjectUtils.isNotNull(websiteProfile)) {
					@SuppressWarnings("unchecked")
					Map<String, Object> map1 = (Map<String, Object>)websiteProfile.get(WebSiteProfileType.keyword.toString());
					if (map1.get("verifyExam").toString().equalsIgnoreCase("no")) { // 如果开关打开
						examHessianService.truncateTable(websiteTruncate.getTableName());
					}	
				}	
			}else if(websiteTruncate.getType().equals("sns")){
				// 获得社区的开关是否打开
				Map<String, Object> websiteProfile = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());
				if (ObjectUtils.isNotNull(websiteProfile)) {
					@SuppressWarnings("unchecked")
					Map<String, Object> map1 = (Map<String, Object>)websiteProfile.get(WebSiteProfileType.keyword.toString());
					if (map1.get("verifySns").toString().equalsIgnoreCase("no")) { // 如果开关打开
						snsHessianService.truncateTable(websiteTruncate.getTableName());
					}	
				}	
			}
			
		}
	}
}

package com.atdld.os.common.service;


import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atdld.os.sns.entity.dynamic.DynamicWeb;
import com.atdld.os.sns.service.dynamic.DynamicWebService;

/**
 * Created by Administrator on 2014/12/25.
 */
@Service("snsHessianService")
public class SnsHessianServiceImpl implements SnsHessianService {
	private static final Logger logger = LoggerFactory.getLogger(SnsHessianServiceImpl.class);
	@Autowired
    private DynamicWebService dynamicWebService;
	
	
	/**
	 * 社区添加动态
	 * @param map
	 */
	public void addDynamic(Map<String,String> map){
		try {
			DynamicWeb dynamicWeb= new DynamicWeb();
			dynamicWeb.setCusId(Long.parseLong(map.get("userId")));//用户id
			dynamicWeb.setBizId(Long.parseLong(map.get("bizId")));//试卷id
			dynamicWeb.setType(Integer.parseInt(map.get("type")));//11观看课程 12购买了商品 13 考试
			dynamicWeb.setDescription(map.get("desc"));
			dynamicWeb.setTitle(map.get("title"));//试卷名
			dynamicWeb.setAddTime(new Date());//添加时间
			dynamicWeb.setContent(map.get("content"));
			dynamicWeb.setUrl(map.get("url"));
			dynamicWeb.setAssistId(Long.parseLong(map.get("assistId")));// 辅助id
			dynamicWebService.addDynamicWebForLearning(dynamicWeb);
		} catch (Exception e) {
			logger.error("SnsHessianServiceImpl.addDynamic------error",e);	
		}
	}
	
	/**
	 * 社区删除动态
	 * @param map
	 */
	public void delDynamic(Long id,int type){
		try {
			DynamicWeb dynamicWeb = new DynamicWeb();// 删除建议动态
            dynamicWeb.setBizId(id);
            dynamicWeb.setType(type);
			dynamicWebService.deleteDynamicWebByCondition(dynamicWeb);
		} catch (Exception e) {
			logger.error("SnsHessianServiceImpl.delDynamic------error",e);	
		}
	}
	 
}

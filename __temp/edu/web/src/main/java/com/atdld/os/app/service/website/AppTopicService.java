package com.atdld.os.app.service.website;

import java.util.List;
import java.util.Map;

import com.atdld.os.app.entity.website.AppTopic;
import com.atdld.os.app.entity.website.AppTopicDto;
import com.atdld.os.app.entity.website.QueryAppTopicCondition;
import com.atdld.os.core.entity.PageEntity;

public interface AppTopicService {
	/**
	 * 创建话题
	 * @param entity
	 */
	public void createAppTopic(AppTopic entity);
	/**
	 * 获取话题
	 * @param id
	 * @return
	 */
	public AppTopicDto getAppTopicById(Long id);
	public AppTopic getAppTopic(Long id);
	/**
	 * 修改话题
	 * @param entity
	 */
	public void updateAppTopic(AppTopic entity);
	   
	/**
	 * 查询话题
	 * @param condition
	 * @param page
	 * @return
	 */
	public List<AppTopicDto> queryAppTopic(QueryAppTopicCondition condition,PageEntity page);
	/**
	 * 删除话题
	 * @param ids
	 */
	public void delAppTopicById(String ids);
	
	/**
	 * app分页查询话题
	 * @param map
	 * @param page
	 * @return
	 */
	public List<Map<String,Object>> queryAppTopicPage(Map<String,Object> map,PageEntity page);
}

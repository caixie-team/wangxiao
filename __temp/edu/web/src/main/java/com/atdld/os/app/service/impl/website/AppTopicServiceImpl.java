package com.atdld.os.app.service.impl.website;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.app.dao.website.AppTopicDao;
import com.atdld.os.app.entity.website.AppTopic;
import com.atdld.os.app.entity.website.AppTopicDto;
import com.atdld.os.app.entity.website.QueryAppTopicCondition;
import com.atdld.os.app.service.website.AppTopicService;
import com.atdld.os.core.entity.PageEntity;

@Service("appTopicService")
public class AppTopicServiceImpl implements AppTopicService{
	@Autowired
	private AppTopicDao appTopicDao;

	@Override
	public void createAppTopic(AppTopic entity) {
		// TODO Auto-generated method stub
		appTopicDao.createAppTopic(entity);
	}

	@Override
	public AppTopicDto getAppTopicById(Long id) {
		// TODO Auto-generated method stub
		return appTopicDao.getAppTopicById(id);
	}

	@Override
	public void updateAppTopic(AppTopic entity) {
		// TODO Auto-generated method stub
		appTopicDao.updateAppTopic(entity);
	}

	@Override
	public List<AppTopicDto> queryAppTopic(
			QueryAppTopicCondition condition, PageEntity page) {
		// TODO Auto-generated method stub
		return appTopicDao.queryAppTopic(condition, page);
	}

	@Override
	public void delAppTopicById(String ids) {
		// TODO Auto-generated method stub
		appTopicDao.delAppTopicById(ids);
	}

	@Override
	public AppTopic getAppTopic(Long id) {
		// TODO Auto-generated method stub
		return appTopicDao.getAppTopic(id);
	}

	@Override
	public List<Map<String, Object>> queryAppTopicPage(Map<String, Object> map,
			PageEntity page) {
		return appTopicDao.queryAppTopicPage(map, page);
	}

}

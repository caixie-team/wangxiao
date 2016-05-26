package com.atdld.os.app.dao.impl.website;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.app.dao.website.AppTopicDao;
import com.atdld.os.app.entity.website.AppTopic;
import com.atdld.os.app.entity.website.AppTopicDto;
import com.atdld.os.app.entity.website.QueryAppTopicCondition;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;

@Repository("appTopicDao")
public class AppTopicDaoImpl extends GenericDaoImpl implements AppTopicDao{

	@Override
	public void createAppTopic(AppTopic entity) {
		// TODO Auto-generated method stub
		insert("AppTopicMapper.createAppTopic", entity);
	}

	@Override
	public AppTopicDto getAppTopicById(Long id) {
		// TODO Auto-generated method stub
		return selectOne("AppTopicMapper.getAppTopicById", id);
	}

	@Override
	public void updateAppTopic(AppTopic entity) {
		// TODO Auto-generated method stub
		update("AppTopicMapper.updateAppTopic", entity);
	}

	@Override
	public List<AppTopicDto> queryAppTopic(
			QueryAppTopicCondition condition, PageEntity page) {
		// TODO Auto-generated method stub
		return queryForListPage("AppTopicMapper.queryAppTopic", condition, page);
	}

	@Override
	public void delAppTopicById(String ids) {
		// TODO Auto-generated method stub
		delete("AppTopicMapper.delAppTopicById", ids);
	}

	@Override
	public AppTopic getAppTopic(Long id) {
		// TODO Auto-generated method stub
		return selectOne("AppTopicMapper.getAppTopic", id);
	}

	@Override
	public List<Map<String, Object>> queryAppTopicPage(Map<String, Object> map, PageEntity page) {
		return queryForListPage("AppTopicMapper.queryAppTopicPage", map, page);
	}

}

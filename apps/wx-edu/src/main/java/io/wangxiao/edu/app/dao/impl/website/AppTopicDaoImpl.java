package io.wangxiao.edu.app.dao.impl.website;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.dao.website.AppTopicDao;
import io.wangxiao.edu.app.entity.website.AppTopic;
import io.wangxiao.edu.app.entity.website.AppTopicDto;
import io.wangxiao.edu.app.entity.website.QueryAppTopicCondition;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("appTopicDao")
public class AppTopicDaoImpl extends GenericDaoImpl implements AppTopicDao {

    @Override
    public void createAppTopic(AppTopic entity) {
        insert("AppTopicMapper.createAppTopic", entity);
    }

    @Override
    public AppTopicDto getAppTopicById(Long id) {
        return selectOne("AppTopicMapper.getAppTopicById", id);
    }

    @Override
    public void updateAppTopic(AppTopic entity) {
        update("AppTopicMapper.updateAppTopic", entity);
    }

    @Override
    public List<AppTopicDto> queryAppTopic(
            QueryAppTopicCondition condition, PageEntity page) {
        return queryForListPage("AppTopicMapper.queryAppTopic", condition, page);
    }

    @Override
    public void delAppTopicById(String ids) {
        delete("AppTopicMapper.delAppTopicById", ids);
    }

    @Override
    public AppTopic getAppTopic(Long id) {
        return selectOne("AppTopicMapper.getAppTopic", id);
    }

    @Override
    public List<Map<String, Object>> queryAppTopicPage(Map<String, Object> map, PageEntity page) {
        return queryForListPage("AppTopicMapper.queryAppTopicPage", map, page);
    }

}

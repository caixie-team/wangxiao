package io.wangxiao.edu.app.service.impl.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.dao.website.AppTopicDao;
import io.wangxiao.edu.app.entity.website.AppTopic;
import io.wangxiao.edu.app.entity.website.AppTopicDto;
import io.wangxiao.edu.app.entity.website.QueryAppTopicCondition;
import io.wangxiao.edu.app.service.website.AppTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("appTopicService")
public class AppTopicServiceImpl implements AppTopicService {
    @Autowired
    private AppTopicDao appTopicDao;

    @Override
    public void createAppTopic(AppTopic entity) {
        appTopicDao.createAppTopic(entity);
    }

    @Override
    public AppTopicDto getAppTopicById(Long id) {
        return appTopicDao.getAppTopicById(id);
    }

    @Override
    public void updateAppTopic(AppTopic entity) {
        appTopicDao.updateAppTopic(entity);
    }

    @Override
    public List<AppTopicDto> queryAppTopic(
            QueryAppTopicCondition condition, PageEntity page) {
        return appTopicDao.queryAppTopic(condition, page);
    }

    @Override
    public void delAppTopicById(String ids) {
        appTopicDao.delAppTopicById(ids);
    }

    @Override
    public AppTopic getAppTopic(Long id) {
        return appTopicDao.getAppTopic(id);
    }

    @Override
    public List<Map<String, Object>> queryAppTopicPage(Map<String, Object> map,
                                                       PageEntity page) {
        return appTopicDao.queryAppTopicPage(map, page);
    }

}

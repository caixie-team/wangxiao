package io.wangxiao.edu.app.service.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.entity.website.AppTopic;
import io.wangxiao.edu.app.entity.website.AppTopicDto;
import io.wangxiao.edu.app.entity.website.QueryAppTopicCondition;

import java.util.List;
import java.util.Map;

public interface AppTopicService {
    /**
     * 创建话题
     *
     * @param entity
     */
    void createAppTopic(AppTopic entity);

    /**
     * 获取话题
     *
     * @param id
     * @return
     */
    AppTopicDto getAppTopicById(Long id);

    AppTopic getAppTopic(Long id);

    /**
     * 修改话题
     *
     * @param entity
     */
    void updateAppTopic(AppTopic entity);

    /**
     * 查询话题
     *
     * @param condition
     * @param page
     * @return
     */
    List<AppTopicDto> queryAppTopic(QueryAppTopicCondition condition, PageEntity page);

    /**
     * 删除话题
     *
     * @param ids
     */
    void delAppTopicById(String ids);

    /**
     * app分页查询话题
     *
     * @param map
     * @param page
     * @return
     */
    List<Map<String, Object>> queryAppTopicPage(Map<String, Object> map, PageEntity page);
}

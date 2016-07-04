package io.wangxiao.site.service;


import io.wangxiao.site.model.WebsiteNavigate;

import java.util.List;

public interface WebsiteNavigateService {

    /**
     * 按导航类型查询
     * @param type
     * @return
     */
    List<WebsiteNavigate> findByType(String type);

}
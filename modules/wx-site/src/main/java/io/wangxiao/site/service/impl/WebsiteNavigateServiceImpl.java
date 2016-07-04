package io.wangxiao.site.service.impl;

import io.wangxiao.site.dao.WebsiteNavigateDao;
import io.wangxiao.site.model.WebsiteNavigate;
import io.wangxiao.site.service.WebsiteNavigateService;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bison on 5/28/16.
 *
 * 网站导航管理
 */
@Service
public class WebsiteNavigateServiceImpl implements WebsiteNavigateService {

    @Autowired
    SQLManager sqlManager;

    @Autowired
    WebsiteNavigateDao websiteNavigateDao;

    @Override
    public List<WebsiteNavigate> findByType(String type) {

        WebsiteNavigate _nav = new WebsiteNavigate();
        _nav.setType(type);
        // 仅查询未禁用数据
        _nav.setStatus(0);

        return websiteNavigateDao.findByCondition(_nav);
    }

}

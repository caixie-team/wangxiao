package io.wangxiao.site.service.impl;

import io.wangxiao.site.model.WebsiteProfile;
import io.wangxiao.site.service.WebsiteProfileService;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bison on 5/29/16.
 *
 */
@Service
public class WebsiteProfileServiceImpl implements WebsiteProfileService{

    @Autowired
    SQLManager sql;

    public WebsiteProfile findByType(String type) {

        Map<String, Long> types = new HashMap<>();
        types.put("web", 1L);
        types.put("alpay", 2L);
        types.put("email", 3L);
        types.put("keyword", 4L);
        types.put("logo", 5L);
        types.put("qq", 6L);
        types.put("sina", 7L);
        types.put("cc", 8L);
        types.put("p56", 9L);
        types.put("weixin", 10L);
        types.put("censusCode", 11L);
        types.put("online", 12L);
        types.put("sale", 13L);
        types.put("letv", 14L);
        types.put("yee", 15L);

        return sql.unique(WebsiteProfile.class, types.get(type));
    }

    public List<WebsiteProfile> getAllWebsiteProfile(){
        return sql.all(WebsiteProfile.class);
    }
    @Override
    public void updateWebsiteProfile(WebsiteProfile websiteProfile) throws Exception {

    }

    @Override
    public Map<String, Object> getWebsiteProfileList() throws Exception {
        return null;
    }

    @Override
    public Map<String, Object> findWebsiteProfileByType(String type) {
        return null;
    }
}

package io.wangxiao.edu.home.service.impl.website;

import com.google.gson.Gson;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.home.dao.website.WebsiteNavigateDao;
import io.wangxiao.edu.home.entity.website.WebsiteNavigate;
import io.wangxiao.edu.home.service.website.WebsiteNavigateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service("websiteNavigateService")
public class WebsiteNavigateServiceImpl implements WebsiteNavigateService {
    CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private WebsiteNavigateDao websiteNavigateDao;
    private Gson gson = new Gson();

    /**
     * 导航列表
     *
     * @return
     */
    public List<WebsiteNavigate> getWebsiteNavigate(WebsiteNavigate websiteNavigate) {
        return websiteNavigateDao.getWebsiteNavigate(websiteNavigate);
    }

    /**
     * 添加导航
     *
     * @param WebsiteNavigate
     */
    public void addWebsiteNavigate(WebsiteNavigate websiteNavigate) {
        websiteNavigateDao.addWebsiteNavigate(websiteNavigate);
        cacheKit.remove(MemConstans.WEBSITE_NAVIGATE);
    }

    /**
     * 冻结、解冻导航
     *
     * @param WebsiteNavigate
     */
    public void freezeWebsiteNavigate(WebsiteNavigate websiteNavigate) {
        websiteNavigateDao.freezeWebsiteNavigate(websiteNavigate);
        cacheKit.remove(MemConstans.WEBSITE_NAVIGATE);
    }

    /**
     * 删除导航
     *
     * @param id
     */
    public void delWebsiteNavigate(Long id) {
        websiteNavigateDao.delWebsiteNavigate(id);
        cacheKit.remove(MemConstans.WEBSITE_NAVIGATE);
    }

    /**
     * 更新导航
     *
     * @param WebsiteNavigate
     */
    public void updateWebsiteNavigate(WebsiteNavigate websiteNavigate) {
        websiteNavigateDao.updateWebsiteNavigate(websiteNavigate);
        cacheKit.remove(MemConstans.WEBSITE_NAVIGATE);
    }

    /**
     * id查询导航
     *
     * @param id
     * @return
     */
    public WebsiteNavigate getWebsiteNavigateById(Long id) {
        return websiteNavigateDao.getWebsiteNavigateById(id);
    }

    /**
     * 前台导航列表
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getWebNavigate() {
        Map<String, Object> navigateMap = new HashMap<String, Object>();
        Map<String, List<String>> navigatesMapJson = (Map<String, List<String>>) cacheKit.get(MemConstans.WEBSITE_NAVIGATE);
        if (navigatesMapJson != null && navigatesMapJson.size() > 0) {//多类型导航json存在
            for (Entry<String, List<String>> entry : navigatesMapJson.entrySet()) {
                List<WebsiteNavigate> navigates = new ArrayList<WebsiteNavigate>();//单类型导航json
                List<String> navigatesJson = entry.getValue();
                for (String navigateJson : navigatesJson) {
                    navigates.add(gson.fromJson(navigateJson, WebsiteNavigate.class));//单个导航
                }
                navigateMap.put(entry.getKey(), navigates);
            }
            return navigateMap;
        }
        navigatesMapJson = new HashMap<String, List<String>>();
        List<WebsiteNavigate> navigates = websiteNavigateDao.getWebNavigate();
        List<String> navigatesJson = new ArrayList<String>();//json
        List<WebsiteNavigate> navigateList = new ArrayList<WebsiteNavigate>();//对象
        String type = navigates.get(0).getType();
        for (WebsiteNavigate navigate : navigates) {
            if (!type.equalsIgnoreCase(navigate.getType())) {
                navigateMap.put(type, navigateList);//导航集合
                navigateList = new ArrayList<WebsiteNavigate>();

                navigatesMapJson.put(type, navigatesJson);//导航json集合
                navigatesJson = new ArrayList<String>();

                navigateList.add(navigate);//导航对象
                navigatesJson.add(gson.toJson(navigate));//导航json
                type = navigate.getType();
            } else {
                navigateList.add(navigate);//导航对象
                navigatesJson.add(gson.toJson(navigate));//导航json
            }
        }
        if (ObjectUtils.isNotNull(navigatesJson)) {
            navigatesMapJson.put(type, navigatesJson);//导航json集合
        }
        cacheKit.set(MemConstans.WEBSITE_NAVIGATE, navigatesMapJson, MemConstans.WEBSITE_NAVIGATE_TIME);
        return navigateMap;
    }

    /**
     * 批量删除导航
     *
     * @param ids
     */
    public void delNavigatebatch(String ids) {
        websiteNavigateDao.delNavigatebatch(ids);
    }

}
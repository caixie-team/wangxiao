package co.bluepx.edu.website.service;

import co.bluepx.edu.core.BaseService;
import co.bluepx.edu.website.dao.WebsiteProfileDao;
import co.bluepx.edu.website.entity.WebsiteProfile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * 网站管理配置service
 */
@Service
public class WebsiteProfileService extends BaseService<WebsiteProfile, WebsiteProfileDao> {


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

        return this.findById(types.get(type));
    }


//    * 根据type查询网站配置
//    *
//            * @param type
//    * @return
//            */
//    public Map<String, Object> getWebsiteProfileByType(String type) {
//        //根据类型获得数据 从memcache获取
//        String websiteProfileStr = (String) memcache.get(MemConstans.WEBSITE_PROFILE + type);
//        if (ObjectUtils.isNull(websiteProfileStr)) {//memcache为空查询数据库
//            WebsiteProfile websiteProfile = websiteProfileDao.getWebsiteProfileByType(type);
//            websiteProfileStr = gson.toJson(websiteProfile);//websiteProfileStr json串
//            memcache.set(MemConstans.WEBSITE_PROFILE + type, websiteProfileStr, MemConstans.WEBSITE_PROFILE_TIME);//设置key 时间一天
//        }
//        WebsiteProfile websiteProfile = gson.fromJson(websiteProfileStr, WebsiteProfile.class);//转回对象
//        String desciption = websiteProfile.getDesciption();
//        //把json数据转化为Map
//        Map<String, Object> map1 = gson.fromJson(checkString(desciption), new TypeToken<Map<String, Object>>() {
//        }.getType());
//        Map<String, Object> webSiteMap = new HashMap<String, Object>();
//        webSiteMap.put(websiteProfile.getType(), map1);
//        return webSiteMap;
//    }

//    @Autowired
//    private WebsiteProfileDao websiteProfileDao;


//    private MemCache memcache = MemCache.getCourseAssessByIdnstance();
//    private Gson gson = new Gson();

    /**
     * 修改WebsiteProfile
     *
     * @param websiteProfile
     * @throws Exception
     */
//    public void updateWebsiteProfile(WebsiteProfile websiteProfile) throws Exception {
//        websiteProfileDao.updateWebsiteProfile(websiteProfile);
//        memcache.remove(MemConstans.WEBSITE_PROFILE + websiteProfile.getType());
//    }

    /**
     * 查询所有网站配置
     *
     * @return 无参
     * @throws Exception
     */
//    public Map<String, Object> getWebsiteProfileList() throws Exception {
//        //获得所有配置
//        @SuppressWarnings("unchecked")
//        List<String> websitesStr = (List<String>) memcache.get(MemConstans.WEBSITE_PROFILE);
//        if (ObjectUtils.isNull(websitesStr) || websitesStr.size() == 0) {
//            List<WebsiteProfile> websiteProfiles = websiteProfileDao.getWebsiteProfileList();
//            for (WebsiteProfile websiteProfile : websiteProfiles) {
//                //转json字符串
//                websitesStr.add(gson.toJson(websiteProfile));
//            }
//            memcache.set(MemConstans.WEBSITE_PROFILE, websitesStr, MemConstans.WEBSITE_PROFILE_TIME);
//        }
//        Map<String, Object> webSiteMap = new HashMap<String, Object>();
//        if (ObjectUtils.isNotNull(websitesStr) && websitesStr.size() > 0) {
//            for (String websiteStr : websitesStr) {
//                //转回对象
//                WebsiteProfile websiteProfile = gson.fromJson(websiteStr, WebsiteProfile.class);
//                String desciption = websiteProfile.getDesciption();
//                Map<String, Object> map1 = gson.fromJson(checkString(desciption), new TypeToken<Map<String, Object>>() {
//                }.getType());
//                map1.put("explain", websiteProfile.getExplain());//描述
//                webSiteMap.put(websiteProfile.getType(), map1);
//            }
//        }
//        return webSiteMap;
//    }

    /**
     * 检查字符串空的方法
     *
     * @param str
     * @return
     */
//    public String checkString(Object str) {
//        if (ObjectUtils.isNotNull(str)
//                && !"null".equals(str.toString())) {
//            return str.toString();
//        } else {
//            return "";
//        }
//    }

//    /**


}
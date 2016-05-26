package io.wangxiao.website.service;

import io.wangxiao.core.BaseService;
import io.wangxiao.core.Condition;
import io.wangxiao.website.dao.WebsiteNavigateDao;
import io.wangxiao.website.entity.WebsiteNavigate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * WebsiteNavigateTbl管理接口
 */
@Service("websiteNavigateService")
public class WebsiteNavigateService extends BaseService<WebsiteNavigate, WebsiteNavigateDao> {

    /**
     * 按导航类型检索
     *
     * @param type
     * @return
     */
    public List<WebsiteNavigate> findByType(String type) {

        return baseDao.find(
                null,
                Arrays.asList(
                        Condition.parseCondition("type.string.eq").setValue(type),
                        Condition.parseCondition("status.int.eq").setValue(0)
                )
        );
    }
// 	@Autowired
//    private WebsiteNavigateDao websiteNavigateDao;
//    private Gson gson=new Gson();
    /**
     * 导航列表
     * @return
     */
//	public List<WebsiteNavigate> getWebsiteNavigate(WebsiteNavigate websiteNavigate){
//		return websiteNavigateDao.getWebsiteNavigate(websiteNavigate);
//	}
    /**
     * 添加导航
     * @param WebsiteNavigate
     */
//	public void addWebsiteNavigate(WebsiteNavigate websiteNavigate){
//		websiteNavigateDao.addWebsiteNavigate(websiteNavigate);
//		memCache.remove(MemConstans.WEBSITE_NAVIGATE);
//	}
    /**
     * 冻结、解冻导航
     * @param WebsiteNavigate
     */
//	public void freezeWebsiteNavigate(WebsiteNavigate websiteNavigate){
//		websiteNavigateDao.freezeWebsiteNavigate(websiteNavigate);
//		memCache.remove(MemConstans.WEBSITE_NAVIGATE);
//	}
    /**
     * 删除导航
     * @param id
     */
//	public void delWebsiteNavigate(Long id){
//		websiteNavigateDao.delWebsiteNavigate(id);
//		memCache.remove(MemConstans.WEBSITE_NAVIGATE);
//	}
    /**
     * 更新导航
     * @param WebsiteNavigate
     */
//	public void updateWebsiteNavigate(WebsiteNavigate websiteNavigate){
//		websiteNavigateDao.updateWebsiteNavigate(websiteNavigate);
//		memCache.remove(MemConstans.WEBSITE_NAVIGATE);
//	}
    /**
     * id查询导航
     * @param id
     * @return
     */
//	public WebsiteNavigate getWebsiteNavigateById(Long id){
//		return websiteNavigateDao.getWebsiteNavigateById(id);
//	}
    /**
     * 前台导航列表
     * @return
     */
/*	@SuppressWarnings("unchecked")
    public Map<String,Object> getWebNavigate(){
		Map<String,Object> navigateMap=new HashMap<String, Object>();
		Map<String,List<String>> navigatesMapJson= (Map<String, List<String>>) memCache.get(MemConstans.WEBSITE_NAVIGATE);
        if (navigatesMapJson!=null&&navigatesMapJson.size()>0) {//多类型导航json存在
        	for(Entry<String,List<String>> entry:navigatesMapJson.entrySet()){
        		List<WebsiteNavigate> navigates=new ArrayList<WebsiteNavigate>();//单类型导航json
        		List<String> navigatesJson = entry.getValue();
				for(String navigateJson:navigatesJson){
					navigates.add(gson.fromJson(navigateJson,WebsiteNavigate.class));//单个导航
        		}
				navigateMap.put(entry.getKey(), navigates);
        	}
            return navigateMap;
        } 
        navigatesMapJson=new HashMap<String, List<String>>();
        List<WebsiteNavigate> navigates=websiteNavigateDao.getWebNavigate();
        List<String> navigatesJson=new ArrayList<String>();//json
        List<WebsiteNavigate> navigateList=new ArrayList<WebsiteNavigate>();//对象
        String type = navigates.get(0).getType();
        for(WebsiteNavigate navigate : navigates){
            if (!type.equalsIgnoreCase(navigate.getType())) {
            	navigateMap.put(type, navigateList);//导航集合
            	navigateList=new ArrayList<WebsiteNavigate>();
            	
            	navigatesMapJson.put(type,navigatesJson);//导航json集合
            	navigatesJson=new ArrayList<String>();
                
                navigateList.add(navigate);//导航对象
                navigatesJson.add(gson.toJson(navigate));//导航json
                type = navigate.getType();
            } else {
            	navigateList.add(navigate);//导航对象
            	navigatesJson.add(gson.toJson(navigate));//导航json
            }
        }
        if (ObjectUtils.isNotNull(navigatesJson)) {
        	navigatesMapJson.put(type,navigatesJson);//导航json集合
        } 
        memCache.set(MemConstans.WEBSITE_NAVIGATE,navigatesMapJson, MemConstans.WEBSITE_NAVIGATE_TIME);
		return navigateMap;
	}*/

}
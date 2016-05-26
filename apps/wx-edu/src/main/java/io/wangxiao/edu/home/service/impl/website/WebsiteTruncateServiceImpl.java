package io.wangxiao.edu.home.service.impl.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.service.ExamHessianService;
import io.wangxiao.edu.common.service.SnsHessianService;
import io.wangxiao.edu.home.constants.enums.WebSiteProfileType;
import io.wangxiao.edu.home.dao.website.WebsiteTruncateDao;
import io.wangxiao.edu.home.entity.website.WebsiteTruncate;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import io.wangxiao.edu.home.service.website.WebsiteTruncateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("websiteTruncateService")
public class WebsiteTruncateServiceImpl implements WebsiteTruncateService {
    @Autowired
    private WebsiteTruncateDao websiteTruncateDao;

    @Autowired
    private SnsHessianService snsHessianService;
    @Autowired
    private ExamHessianService examHessianService;
    @Autowired
    private WebsiteProfileService websiteProfileService;

    /**
     * 添加清空表
     *
     * @param WebsiteTruncate
     */
    public void insertWebsiteTruncate(WebsiteTruncate websiteTruncate) {
        websiteTruncateDao.insertWebsiteTruncate(websiteTruncate);
    }

    /**
     * 清空表分页列表
     *
     * @param websiteTruncate
     * @param page
     * @return
     */
    public List<WebsiteTruncate> getTruncatePageList(WebsiteTruncate websiteTruncate, PageEntity page) {
        return websiteTruncateDao.getTruncatePageList(websiteTruncate, page);
    }

    /**
     * 删除清空表
     *
     * @param ids
     */
    public void delTruncateByIds(String ids) {
        websiteTruncateDao.delTruncateByIds(ids);
    }

    /**
     * 查询清空表
     *
     * @param id
     * @return
     */
    public WebsiteTruncate getWebsiteTruncateById(Long id) {
        return websiteTruncateDao.getWebsiteTruncateById(id);
    }

    /**
     * 更新清空表
     *
     * @param WebsiteTruncate
     */
    public void updateWebsiteTruncate(WebsiteTruncate websiteTruncate) {
        websiteTruncateDao.updateWebsiteTruncate(websiteTruncate);
    }

    /**
     * 清空表
     *
     * @param ids
     */
    public void truncateTableByIds(String ids, String type) {
        //清空表集合
        List<WebsiteTruncate> websiteTruncates = websiteTruncateDao.getTruncateList(ids, type);
        for (WebsiteTruncate websiteTruncate : websiteTruncates) {
            if (websiteTruncate.getType().equals("web")) {
                websiteTruncateDao.truncateTable(websiteTruncate.getTableName());
            } else if (websiteTruncate.getType().equals("exam")) {
                // 获得考试的开关是否打开
                Map<String, Object> websiteProfile = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());
                if (ObjectUtils.isNotNull(websiteProfile)) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> map1 = (Map<String, Object>) websiteProfile.get(WebSiteProfileType.keyword.toString());
                    if (map1.get("verifyExam").toString().equalsIgnoreCase("no")) { // 如果开关打开
                        examHessianService.truncateTable(websiteTruncate.getTableName());
                    }
                }
            } else if (websiteTruncate.getType().equals("sns")) {
                // 获得社区的开关是否打开
                Map<String, Object> websiteProfile = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());
                if (ObjectUtils.isNotNull(websiteProfile)) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> map1 = (Map<String, Object>) websiteProfile.get(WebSiteProfileType.keyword.toString());
                    if (map1.get("verifySns").toString().equalsIgnoreCase("no")) { // 如果开关打开
                        snsHessianService.truncateTable(websiteTruncate.getTableName());
                    }
                }
            }

        }
    }
}

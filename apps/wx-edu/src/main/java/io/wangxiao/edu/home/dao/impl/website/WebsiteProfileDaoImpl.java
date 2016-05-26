package io.wangxiao.edu.home.dao.impl.website;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.website.WebsiteProfileDao;
import io.wangxiao.edu.home.entity.website.WebsiteProfile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("websiteProfileDao")
public class WebsiteProfileDaoImpl extends GenericDaoImpl implements WebsiteProfileDao {

    /**
     * 根据type查询网站配置
     *
     * @param type
     * @return
     */
    public WebsiteProfile getWebsiteProfileByType(String type) {
        return this.selectOne("WebsiteProfileMapper.getWebsiteProfileByType", type);
    }

    /**
     * 更新网站配置管理
     *
     * @param websiteProfile
     */
    public void updateWebsiteProfile(WebsiteProfile websiteProfile) {
        this.update("WebsiteProfileMapper.updateWebsiteProfile", websiteProfile);
    }

    /**
     * 查询网站配置
     *
     * @return List<WebsiteProfile>
     */
    public List<WebsiteProfile> getWebsiteProfileList() {
        return this.selectList("WebsiteProfileMapper.getWebsiteProfileList", null);
    }

}

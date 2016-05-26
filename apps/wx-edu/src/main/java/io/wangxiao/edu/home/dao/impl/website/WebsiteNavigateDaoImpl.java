package io.wangxiao.edu.home.dao.impl.website;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.website.WebsiteNavigateDao;
import io.wangxiao.edu.home.entity.website.WebsiteNavigate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("websiteNavigateDao")
public class WebsiteNavigateDaoImpl extends GenericDaoImpl implements WebsiteNavigateDao {

    /**
     * 导航列表
     *
     * @return
     */
    public List<WebsiteNavigate> getWebsiteNavigate(WebsiteNavigate websiteNavigate) {
        return this.selectList("WebsiteNavigateMapper.getWebsiteNavigate", websiteNavigate);
    }

    /**
     * 添加导航
     *
     * @param WebsiteNavigate
     */
    public void addWebsiteNavigate(WebsiteNavigate websiteNavigate) {
        this.insert("WebsiteNavigateMapper.createWebsiteNavigate", websiteNavigate);
    }

    /**
     * 冻结、解冻导航
     *
     * @param WebsiteNavigate
     */
    public void freezeWebsiteNavigate(WebsiteNavigate websiteNavigate) {
        this.update("WebsiteNavigateMapper.freezeWebsiteNavigate", websiteNavigate);
    }

    /**
     * 删除导航
     *
     * @param id
     */
    public void delWebsiteNavigate(Long id) {
        this.delete("WebsiteNavigateMapper.delWebsiteNavigate", id);
    }

    /**
     * 更新导航
     *
     * @param WebsiteNavigate
     */
    public void updateWebsiteNavigate(WebsiteNavigate websiteNavigate) {
        this.update("WebsiteNavigateMapper.updateWebsiteNavigate", websiteNavigate);
    }

    /**
     * id查询导航
     *
     * @param id
     * @return
     */
    public WebsiteNavigate getWebsiteNavigateById(Long id) {
        return this.selectOne("WebsiteNavigateMapper.getWebsiteNavigateById", id);
    }

    /**
     * 查询未冻结的导航列表
     *
     * @return
     */
    public List<WebsiteNavigate> getWebNavigate() {
        return this.selectList("WebsiteNavigateMapper.getWebNavigate", 0);
    }

    /**
     * 批量删除导航
     *
     * @param ids
     */
    public void delNavigatebatch(String ids) {
        this.delete("WebsiteNavigateMapper.delNavigatebatch", ids.replaceAll(" ", "").split(","));
    }

}

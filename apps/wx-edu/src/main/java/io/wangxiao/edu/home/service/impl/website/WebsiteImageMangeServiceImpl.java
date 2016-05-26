package io.wangxiao.edu.home.service.impl.website;

import io.wangxiao.edu.home.dao.website.WebsiteImageMangeDao;
import io.wangxiao.edu.home.entity.website.WebsiteImageMange;
import io.wangxiao.edu.home.service.website.WebsiteImageMangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("websiteImageMangeService")
public class WebsiteImageMangeServiceImpl implements WebsiteImageMangeService {
    @Autowired
    private WebsiteImageMangeDao websiteImageMangeDao;

    /**
     * 得到全部管理图片的列表
     *
     * @author root
     */
    public List<WebsiteImageMange> queryWebsiteImageMangeList() {
        return websiteImageMangeDao.queryWebsiteImageMangeList();
    }

    /**
     * 添加图片管理
     *
     * @return
     */
    public void addimagesMange(WebsiteImageMange websiteImageMange) {
        websiteImageMangeDao.addimagesMange(websiteImageMange);
    }

    /**
     * 删除图片
     *
     * @return
     */
    public void deleteImagesMangeById(long id) {
        websiteImageMangeDao.deleteImagesMangeById(id);
    }

    /**
     * 得到单个管理图片的信息
     *
     * @return
     */
    public WebsiteImageMange getImagesMangeByKey(String key) {
        return websiteImageMangeDao.getImagesMangeByKey(key);
    }

    /**
     * 修改图片管理
     *
     * @param websiteImageMange
     */
    public void updateImagesMange(WebsiteImageMange websiteImageMange) {
        websiteImageMangeDao.updateImagesMange(websiteImageMange);
    }

    /**
     * 验证是否有相同的key
     *
     * @param key
     */
    public int checkImagesMange(String key) {
        return websiteImageMangeDao.checkImagesMange(key);
    }

    /**
     * 批量删除图片类型
     *
     * @param ids
     */
    public void delImageMangebatch(String ids) {
        websiteImageMangeDao.delImageMangebatch(ids);
    }


}

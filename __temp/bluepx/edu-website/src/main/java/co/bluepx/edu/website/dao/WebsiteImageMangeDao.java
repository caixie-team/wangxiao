package co.bluepx.edu.website.dao;

import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.website.entity.WebsiteImageMange;

import java.util.List;


public interface WebsiteImageMangeDao extends BaseDao<WebsiteImageMange> {
    /**
     * 得到全部管理图片的列表
     *
     * @return
     */
    List<WebsiteImageMange> queryWebsiteImageMangeList();

    /**
     * 添加图片管理
     *
     * @return
     */
    void addimagesMange(WebsiteImageMange websiteImageMange);

    /**
     * 删除图片
     *
     * @return
     */
    void deleteImagesMangeById(long id);

    /**
     * 得到单个管理图片的信息
     *
     * @return
     */
    WebsiteImageMange getImagesMangeByKey(String key);

    /**
     * 修改图片管理
     *
     * @param websiteImageMange
     */
    void updateImagesMange(WebsiteImageMange websiteImageMange);

    /**
     * 验证是否有相同的key
     *
     * @param key
     */
    int checkImagesMange(String key);

}

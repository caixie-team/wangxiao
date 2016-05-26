package io.wangxiao.edu.home.service.impl.website;

import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.home.dao.website.WebsiteCourseDao;
import io.wangxiao.edu.home.entity.website.WebsiteCourse;
import io.wangxiao.edu.home.service.website.WebsiteCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("websiteCourseService")
public class WebsiteCourseServiceImpl implements WebsiteCourseService {
    CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private WebsiteCourseDao websiteCourseDao;

    /**
     * 推荐课程分类列表
     *
     * @param websiteCourse
     * @param page
     * @return
     */
    public List<WebsiteCourse> queryWebsiteCourseList() {
        return websiteCourseDao.queryWebsiteCourseList();
    }

    /**
     * 查询推荐课程分类
     *
     * @param id
     * @return
     */
    public WebsiteCourse queryWebsiteCourseById(Long id) {
        return websiteCourseDao.queryWebsiteCourseById(id);
    }

    /**
     * 修改推荐课程分类
     *
     * @param id
     * @return
     */
    public void updateWebsiteCourseById(WebsiteCourse websiteCourse) {
        websiteCourseDao.updateWebsiteCourseById(websiteCourse);
        cacheKit.remove(MemConstans.RECOMMEND_COURSE);
    }

    /**
     * 添加推荐课程分类
     *
     * @param id
     * @return
     */
    public void addWebsiteCourse(WebsiteCourse websiteCourse) {
        websiteCourseDao.addWebsiteCourse(websiteCourse);
        cacheKit.remove(MemConstans.RECOMMEND_COURSE);
    }

    /**
     * 删除推荐课程分类及分类下推荐课程
     *
     * @param id
     */
    public void deleteWebsiteCourseDetailById(Long id) {
        websiteCourseDao.deleteWebsiteCourseDetailById(id);
        cacheKit.remove(MemConstans.RECOMMEND_COURSE);
    }

}
package io.wangxiao.edu.home.dao.impl.website;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.website.WebsiteCourseDao;
import io.wangxiao.edu.home.entity.website.WebsiteCourse;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("websiteCourseDao")
public class WebsiteCourseDaoImpl extends GenericDaoImpl implements WebsiteCourseDao {


    /**
     * 推荐课程分类列表
     *
     * @param websiteCourse
     * @param page
     * @return
     */
    public List<WebsiteCourse> queryWebsiteCourseList() {
        return this.selectList("WebsiteCourseMapper.queryWebsiteCourseList", 0);
    }

    /**
     * 查询推荐课程分类
     *
     * @param id
     * @return
     */
    public WebsiteCourse queryWebsiteCourseById(Long id) {
        return this.selectOne("WebsiteCourseMapper.getWebsiteCourseById", id);
    }

    /**
     * 修改推荐课程分类
     *
     * @param id
     * @return
     */
    public void updateWebsiteCourseById(WebsiteCourse websiteCourse) {
        this.update("WebsiteCourseMapper.updateWebsiteCourse", websiteCourse);
    }

    /**
     * 添加推荐课程分类
     *
     * @param id
     * @return
     */
    public void addWebsiteCourse(WebsiteCourse websiteCourse) {
        this.insert("WebsiteCourseMapper.createWebsiteCourse", websiteCourse);

    }

    /**
     * 删除推荐课程分类及分类下推荐课程
     *
     * @param id
     */
    public void deleteWebsiteCourseDetailById(Long id) {
        this.delete("WebsiteCourseMapper.deleteWebsiteCourseById", id);
        this.delete("WebsiteCourseDetailMapper.delWebsiteCourseDetails", id);
    }
}

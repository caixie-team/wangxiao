package co.bluepx.edu.website.dao;

import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.website.entity.WebsiteCourse;

import java.util.List;

/**
 * WebsiteCourse
 */
public interface WebsiteCourseDao  extends BaseDao<WebsiteCourse> {

	
    /**
     * 推荐课程分类列表
     * @param websiteCourse
     * @param page
     * @return
     */
    public List<WebsiteCourse> queryWebsiteCourseList();

    /**
     * 查询推荐课程分类
     * @param id
     * @return
     */
    public WebsiteCourse queryWebsiteCourseById(Long id);
    /**
     * 修改推荐课程分类
     * @param id
     * @return
     */
    public void updateWebsiteCourseById(WebsiteCourse websiteCourse);
    /**
     * 添加推荐课程分类
     * @param id
     * @return
     */
    public void addWebsiteCourse(WebsiteCourse websiteCourse);

    /**
     * 删除推荐课程分类及分类下推荐课程
     * 
     * @param id
     */
    public void deleteWebsiteCourseDetailById(Long id);
}
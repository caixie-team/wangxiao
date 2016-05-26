package io.wangxiao.edu.home.dao.website;

import java.util.List;
import io.wangxiao.edu.home.entity.website.WebsiteCourse;

/**
 * WebsiteCourse管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface WebsiteCourseDao {

	
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
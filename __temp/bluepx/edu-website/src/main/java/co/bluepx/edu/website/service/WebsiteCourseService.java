package co.bluepx.edu.website.service;

import co.bluepx.edu.core.BaseService;
import co.bluepx.edu.website.dao.WebsiteCourseDao;
import co.bluepx.edu.website.entity.WebsiteCourse;
import org.springframework.stereotype.Service;


/**
 * WebsiteCourse管理接口
 */
@Service("websiteCourseService")
public class WebsiteCourseService extends BaseService<WebsiteCourse, WebsiteCourseDao> {

    /**
     * 推荐课程分类列表
     * @param websiteCourse
     * @param page
     * @return
     */
/*    public List<WebsiteCourse> queryWebsiteCourseList(){
        return websiteCourseDao.queryWebsiteCourseList();
    }*/

    /**
     * 查询推荐课程分类
     * @param id
     * @return
     */
/*    public WebsiteCourse queryWebsiteCourseById(Long id){
    	return websiteCourseDao.queryWebsiteCourseById(id);
    }*/
    /**
     * 修改推荐课程分类
     * @param id
     * @return
     */
/*    public void updateWebsiteCourseById(WebsiteCourse websiteCourse){
    	websiteCourseDao.updateWebsiteCourseById(websiteCourse);
    	memCache.remove(MemConstans.RECOMMEND_COURSE);
    }*/
    /**
     * 添加推荐课程分类
     * @param id
     * @return
     */
/*    public void addWebsiteCourse(WebsiteCourse websiteCourse){
    	websiteCourseDao.addWebsiteCourse(websiteCourse);
    	memCache.remove(MemConstans.RECOMMEND_COURSE);
    }*/
    /**
     * 删除推荐课程分类及分类下推荐课程
     *
     * @param id
     */
/*    public void deleteWebsiteCourseDetailById(Long id){
    	websiteCourseDao.deleteWebsiteCourseDetailById(id);
    	memCache.remove(MemConstans.RECOMMEND_COURSE);
    }*/

}
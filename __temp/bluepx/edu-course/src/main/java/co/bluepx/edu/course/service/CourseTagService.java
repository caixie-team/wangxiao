package co.bluepx.edu.course.service;

import co.bluepx.edu.core.BaseService;
import co.bluepx.edu.course.dao.CourseTagDao;
import co.bluepx.edu.course.entity.CourseTag;

/**
 * CourseTag管理接口
 */
//@Service("courseTagService")
public class CourseTagService extends BaseService<CourseTag, CourseTagDao> {
/*
 	@Autowired
    private CourseTagDao courseTagDao;
    
    *//**
     * 添加CourseTag
     * @param courseTag 要添加的CourseTag
     * @return id
     *//*
    public Long addCourseTag(CourseTag courseTag){
    	return courseTagDao.addCourseTag(courseTag);
    }

    *//**
     * 根据id删除一个CourseTag
     * @param id 要删除的id
     *//*
    public void deleteCourseTagById(Long id){
    	 courseTagDao.deleteCourseTagById(id);
    }

    *//**
     * 修改CourseTag
     * @param courseTag 要修改的CourseTag
     *//*
    public void updateCourseTag(CourseTag courseTag){
     	courseTagDao.updateCourseTag(courseTag);
    }

    *//**
     * 根据id获取单个CourseTag对象
     * @param id 要查询的id
     * @return CourseTag
     *//*
    public CourseTag getCourseTagById(Long id){
    	return courseTagDao.getCourseTagById( id);
    }

    *//**
     * 根据条件获取CourseTag列表
     * @param courseTag 查询条件
     * @return List<CourseTag>
     *//*
    public List<CourseTag> getCourseTagList(CourseTag courseTag){
    	return courseTagDao.getCourseTagList(courseTag);
    }*/
}
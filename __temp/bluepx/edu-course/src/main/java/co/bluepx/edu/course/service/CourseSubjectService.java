package co.bluepx.edu.course.service;

import co.bluepx.edu.core.BaseService;
import co.bluepx.edu.course.dao.CourseSubjectDao;
import co.bluepx.edu.course.entity.CourseSubject;

/**
 * CourseSubject管理接口
 */
//@Service("CourseSubjectService")
public class CourseSubjectService extends BaseService<CourseSubject, CourseSubjectDao> {

/*
    *//**
     * 添加CourseSubject
     * @param CourseSubject 要添加的CourseSubject
     * @return id
     *//*
    public Long addCourseSubject(CourseSubject CourseSubject){
    	return CourseSubjectDao.addCourseSubject(CourseSubject);
    }

    *//**
     * 根据id删除一个CourseSubject
     * @param id 要删除的id
     *//*
    public void deleteCourseSubjectById(Long id){
    	 CourseSubjectDao.deleteCourseSubjectById(id);
    }
    *//**
     * 根据条件删除一个CourseSubject
     *//*
    public void deleteCourseSubject(CourseSubject CourseSubject){
    	CourseSubjectDao.deleteCourseSubject(CourseSubject);
    }
    *//**
     * 修改CourseSubject
     * @param CourseSubject 要修改的CourseSubject
     *//*
    public void updateCourseSubject(CourseSubject CourseSubject){
     	CourseSubjectDao.updateCourseSubject(CourseSubject);
    }

    *//**
     * 根据id获取单个CourseSubject对象
     * @param id 要查询的id
     * @return CourseSubject
     *//*
    public CourseSubject getCourseSubjectById(Long id){
    	return CourseSubjectDao.getCourseSubjectById( id);
    }

    *//**
     * 根据条件获取CourseSubject列表
     * @param CourseSubject 查询条件
     * @return List<CourseSubject>
     *//*
    public List<CourseSubject> getCourseSubjectList(CourseSubject CourseSubject){
    	return CourseSubjectDao.getCourseSubjectList(CourseSubject);
    }*/
}
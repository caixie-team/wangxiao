package com.atdld.os.api.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.dao.course.TeacherDao;
import com.atdld.os.edu.entity.course.QueryTeacher;
import com.atdld.os.edu.entity.course.Teacher;
import com.atdld.os.edu.service.course.TeacherService;

/**
 * Teacher管理接口 User:  Date: 2014-05-27
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherDao teacherDao;

	/**
	 * 添加Teacher
	 * 
	 * @param teacher
	 *            要添加的Teacher
	 * @return id
	 */
	public Long addTeacher(Teacher teacher) {
		return teacherDao.addTeacher(teacher);
	}

	/**
	 * 根据id删除一个Teacher
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteTeacherById(Long id) {
		teacherDao.deleteTeacherById(id);
	}

	/**
	 * 修改Teacher
	 * 
	 * @param teacher
	 *            要修改的Teacher
	 */
	public void updateTeacher(Teacher teacher) {
		teacherDao.updateTeacher(teacher);
	}

	/**
	 * 根据id获取单个Teacher对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return Teacher
	 */
	public Teacher getTeacherById(Long id) {
		return teacherDao.getTeacherById(id);
	}

	/**
	 * 根据条件获取Teacher列表
	 * 
	 * @param teacher
	 *            查询条件
	 * @return List<Teacher>
	 */
	public List<Teacher> getTeacherList(Teacher teacher) {
		return teacherDao.getTeacherList(teacher);
	}

	/**
	 * 根据条件获取Teacher列表
	 * 
	 * @param teacher
	 *            查询条件
	 * @return List<Teacher>
	 */
	public List<QueryTeacher> queryTeacherAndCourseListPage(Teacher teacher,PageEntity page) {
		return teacherDao.queryTeacherAndCourseListPage(teacher,page);
	}
	/**
	 * 首页Teacher列表
	 * 
	 * @param teacher
	 *            查询条件
	 * @return List<Teacher>
	 */
	public List<QueryTeacher> queryTeacherListPage(Teacher teacher,PageEntity page){
		return teacherDao.queryTeacherListPage(teacher, page);
	}
	
	/**
	 * 根据id获取Teacher列表
	 */
	public List<QueryTeacher> queryTeacherInIds(String ids){
		return teacherDao.queryTeacherInIds(ids);
	}
	/**
     * 查询讲相同课程的讲师，把当前讲师排除出去
     * 
     * @param courseIds
     *            课程集合
     * @param teacher
     *            排除的讲师id
     * @return
     */
    public List<Teacher> getTeachersByCourse(List<Long> courseIds, Long teacherId){
        return teacherDao.getTeachersByCourse(courseIds, teacherId);
    }
    /**
	 * 根据老师id删除（软删除） 做更新操作
	 * 
	 * @param teacherId
	 */
    public void delTeacherByStatus(Long teacherId) {
    	teacherDao.delTeacherByStatus(teacherId);
    }
}
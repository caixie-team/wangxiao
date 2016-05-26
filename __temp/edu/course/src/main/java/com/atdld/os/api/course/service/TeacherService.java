package com.atdld.os.api.course.service;

import com.atdld.os.api.course.entity.QueryTeacher;
import com.atdld.os.api.course.entity.Teacher;
import com.atdld.os.entity.PageEntity;

import java.util.List;


/**
 * Teacher管理接口 User:  Date: 2014-05-27
 */
public interface TeacherService {

	/**
	 * 添加Teacher
	 * 
	 * @param teacher
	 *            要添加的Teacher
	 * @return id
	 */
	public Long addTeacher(Teacher teacher);

	/**
	 * 根据id删除一个Teacher
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteTeacherById(Long id);

	/**
	 * 修改Teacher
	 * 
	 * @param teacher
	 *            要修改的Teacher
	 */
	public void updateTeacher(Teacher teacher);

	/**
	 * 根据id获取单个Teacher对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return Teacher
	 */
	public Teacher getTeacherById(Long id);

	/**
	 * 根据条件获取Teacher列表
	 * 
	 * @param teacher
	 *            查询条件
	 * @return List<Teacher>
	 */
	public List<Teacher> getTeacherList(Teacher teacher);

	/**
	 * 根据条件获取Teacher列表（包含课程）
	 * 
	 * @param teacher
	 *            查询条件
	 * @return List<Teacher>
	 */
	public List<QueryTeacher> queryTeacherAndCourseListPage(Teacher teacher, PageEntity page);
	/**
	 * Teacher列表
	 * 
	 * @param teacher
	 *            查询条件
	 * @return List<Teacher>
	 */
	public List<QueryTeacher> queryTeacherListPage(Teacher teacher, PageEntity page);
	/**
	 * 根据id获取Teacher列表
	 * 
	 * @param ids
	 * @return
	 */
	public List<QueryTeacher> queryTeacherInIds(String ids);

	/**
	 * 查询讲相同课程的讲师，把当前讲师排除出去
	 * 
	 * @param courseIds
	 *            课程集合
	 * @param teacher
	 *            排除的讲师id
	 * @return
	 */
	public List<Teacher> getTeachersByCourse(List<Long> courseIds, Long teacherId);

	/**
	 * 根据老师id删除（软删除） 做更新操作
	 * 
	 * @param teacherId
	 */
	public void delTeacherByStatus(Long teacherId);

}
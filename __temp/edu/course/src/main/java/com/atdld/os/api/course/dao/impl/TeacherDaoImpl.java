package com.atdld.os.api.course.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.dao.course.TeacherDao;
import com.atdld.os.edu.entity.course.QueryTeacher;
import com.atdld.os.edu.entity.course.Teacher;

/**
 * 
 * Teacher User:  Date: 2014-05-27
 */
@Repository("teacherDao")
public class TeacherDaoImpl extends GenericDaoImpl implements TeacherDao {

    /**
     * 添加Teacher
     * 
     * @param teacher
     *            要添加的Teacher
     * @return id
     */
    public Long addTeacher(Teacher teacher) {
        return this.insert("TeacherMapper.createTeacher", teacher);
    }

    /**
     * 根据id删除一个Teacher
     * 
     * @param id
     *            要删除的id
     */
    public void deleteTeacherById(Long id) {
        this.delete("TeacherMapper.deleteTeacherById", id);
    }

    /**
     * 修改Teacher
     * 
     * @param teacher
     *            要修改的Teacher
     */
    public void updateTeacher(Teacher teacher) {
        this.update("TeacherMapper.updateTeacher", teacher);
    }

    /**
     * 根据id获取单个Teacher对象
     * 
     * @param id
     *            要查询的id
     * @return Teacher
     */
    public Teacher getTeacherById(Long id) {
        return this.selectOne("TeacherMapper.getTeacherById", id);
    }

    /**
     * 根据条件获取Teacher列表
     * 
     * @param teacher
     *            查询条件
     * @return List<Teacher>
     */
    public List<Teacher> getTeacherList(Teacher teacher) {
        return this.selectList("TeacherMapper.getTeacherList", teacher);
    }
    
    /**
     * 根据条件获取Teacher列表(包含课程)
     * 
     * @param teacher
     *            查询条件
     * @return List<Teacher>
     */
   	public List<QueryTeacher> queryTeacherAndCourseListPage(Teacher teacher,PageEntity page){
        return this.queryForListPage("TeacherMapper.queryTeacherAndCourseListPage", teacher, page);
    }
    /**
	 * Teacher列表
	 * 
	 * @param teacher
	 *            查询条件
	 * @return List<Teacher>
	 */
   	public List<QueryTeacher> queryTeacherListPage(Teacher teacher,PageEntity page){
   		return this.queryForListPage("TeacherMapper.queryTeacherListPage", teacher, page);
	}
    /**
     * 根据id获取Teacher列表
     */
    public List<QueryTeacher> queryTeacherInIds(String ids) {
        return selectList("TeacherMapper.queryTeacherInIds", ids);
    }

    /**
     * 查询讲相同课程的讲师，把当前讲师排除出去
     * 
     * @param courseIds
     *            课程集合
     * @param teacherId
     *            排除的讲师id
     * @return
     */
    public List<Teacher> getTeachersByCourse(List<Long> courseIds, Long teacherId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("courseIds", courseIds);
        params.put("teacherId", teacherId);
        return this.selectList("TeacherMapper.getTeachersByCourse", params);
    }
    /**
	 * 根据老师id删除（软删除） 做更新操作
	 * 
	 * @param teacherId
	 */
    public void delTeacherByStatus(Long teacherId) {
    	this.update("TeacherMapper.delTeacherByStatus", teacherId);
    }
}

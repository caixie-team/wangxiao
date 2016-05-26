package io.wangxiao.edu.home.service.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.course.QueryTeacher;
import io.wangxiao.edu.home.entity.course.Teacher;

import java.util.List;

public interface TeacherService {

    /**
     * 添加Teacher
     *
     * @param teacher 要添加的Teacher
     * @return id
     */
    Long addTeacher(Teacher teacher);

    /**
     * 根据id删除一个Teacher
     *
     * @param id 要删除的id
     */
    void deleteTeacherById(Long id);

    /**
     * 修改Teacher
     *
     * @param teacher 要修改的Teacher
     */
    void updateTeacher(Teacher teacher);

    /**
     * 根据id获取单个Teacher对象
     *
     * @param id 要查询的id
     * @return Teacher
     */
    Teacher getTeacherById(Long id);

    /**
     * 根据条件获取Teacher列表
     *
     * @param teacher 查询条件
     * @return List<Teacher>
     */
    List<Teacher> getTeacherList(Teacher teacher);

    /**
     * 根据条件获取Teacher列表
     * 查询条件
     *
     * @return List<Teacher>
     */
    List<Teacher> getQueryTeachers();

    /**
     * 根据条件获取Teacher列表（包含课程）
     *
     * @param teacher 查询条件
     * @return List<Teacher>
     */
    List<QueryTeacher> queryTeacherAndCourseListPage(Teacher teacher, PageEntity page);

    /**
     * Teacher列表
     *
     * @param teacher 查询条件
     * @return List<Teacher>
     */
    List<QueryTeacher> queryTeacherListPage(Teacher teacher, PageEntity page);

    /**
     * 根据id获取Teacher列表
     *
     * @param ids
     * @return
     */
    List<QueryTeacher> queryTeacherInIds(String ids);

    /**
     * 查询讲相同课程的讲师，把当前讲师排除出去
     *
     * @param courseIds 课程集合
     * @param teacher   排除的讲师id
     * @return
     */
    List<Teacher> getTeachersByCourse(List<Long> courseIds, Long teacherId);

    /**
     * 根据老师id删除（软删除） 做更新操作
     *
     * @param teacherId
     */
    void delTeacherByStatus(Long teacherId);

    /**
     * 讲师下的课程数量
     *
     * @param teacherId
     * @return
     */
    Long queryTeacherCourseNum(Long teacherId);

}
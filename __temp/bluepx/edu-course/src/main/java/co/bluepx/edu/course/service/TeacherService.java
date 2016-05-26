package co.bluepx.edu.course.service;

import co.bluepx.edu.core.BaseService;
import co.bluepx.edu.course.dao.TeacherDao;
import co.bluepx.edu.course.entity.CourseDto;
import co.bluepx.edu.course.entity.QueryCourse;
import co.bluepx.edu.course.entity.Teacher;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * Teacher管理接口
 */
@Service
public class TeacherService extends BaseService<Teacher, TeacherDao> {

/*	@Autowired
    private TeacherDao teacherDao;

	*//**
     * 添加Teacher
     *
     * @param teacher
     *            要添加的Teacher
     * @return id
     *//*
	public Long addTeacher(Teacher teacher) {
		return teacherDao.addTeacher(teacher);
	}

	*//**
     * 根据id删除一个Teacher
     *
     * @param id
     *            要删除的id
     *//*
	public void deleteTeacherById(Long id) {
		teacherDao.deleteTeacherById(id);
	}

	*//**
     * 修改Teacher
     *
     * @param teacher
     *            要修改的Teacher
     *//*
	public void updateTeacher(Teacher teacher) {
		teacherDao.updateTeacher(teacher);
	}

	*//**
     * 根据id获取单个Teacher对象
     *
     * @param id
     *            要查询的id
     * @return Teacher
     *//*
	public Teacher getTeacherById(Long id) {
		return teacherDao.getTeacherById(id);
	}

	*//**
     * 根据条件获取Teacher列表
     *
     * @param teacher
     *            查询条件
     * @return List<Teacher>
     *//*
	public List<Teacher> getTeacherList(Teacher teacher) {
		return teacherDao.getTeacherList(teacher);
	}

	*//**
     * 根据条件获取Teacher列表
     *
     * @param teacher
     *            查询条件
     * @return List<Teacher>
     *//*
	public List<QueryTeacher> queryTeacherAndCourseListPage(Teacher teacher,PageEntity page) {
		return teacherDao.queryTeacherAndCourseListPage(teacher,page);
	}
	*//**
     * 首页Teacher列表
     *
     * @param teacher
     *            查询条件
     * @return List<Teacher>
     *//*
	public List<QueryTeacher> queryTeacherListPage(Teacher teacher,PageEntity page){
		return teacherDao.queryTeacherListPage(teacher, page);
	}
	
	*//**
     * 根据id获取Teacher列表
     *//*
	public List<QueryTeacher> queryTeacherInIds(String ids){
		return teacherDao.queryTeacherInIds(ids);
	}
	*//**
     * 查询讲相同课程的讲师，把当前讲师排除出去
     *
     * @param courseIds
     *            课程集合
     * @param teacher
     *            排除的讲师id
     * @return
     *//*
    public List<Teacher> getTeachersByCourse(List<Long> courseIds, Long teacherId){
        return teacherDao.getTeachersByCourse(courseIds, teacherId);
    }
    *//**
     * 根据老师id删除（软删除） 做更新操作
     *
     * @param teacherId
     *//*
    public void delTeacherByStatus(Long teacherId) {
    	teacherDao.delTeacherByStatus(teacherId);
    }*/

    /**
     * 获取教师分页数据
     */
    public PageInfo<TeacherDao> findTeacherListByPage(int pageNum, int pageSize) {

        return PageHelper
                .startPage(pageNum, pageSize)
//                .setOrderBy("id desc")
                .doSelectPageInfo(() -> baseDao.find(null));
    }
}
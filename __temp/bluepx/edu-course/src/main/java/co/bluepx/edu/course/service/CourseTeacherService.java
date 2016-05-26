package co.bluepx.edu.course.service;

import co.bluepx.edu.core.BaseService;
import co.bluepx.edu.course.dao.CourseTeacherDao;
import co.bluepx.edu.course.entity.CourseTeacher;
import org.springframework.stereotype.Service;

/**
 * CourseTeacher管理接口
 */
@Service("courseTeacherService")
public class CourseTeacherService extends BaseService<CourseTeacher, CourseTeacherDao> {


/*    public List<CourseTeacher> getCourseTeachers(List<Long> ids) {

        List<Map<String, Object>> teacherList = baseDao.findTeacherNameByCourseIds(ids);

        for (Map<String, Object> m : teacherList) {
            for (String)
        }
        return null;
    }*/

/*
    public List<Teacher> findTeacherNameByCourseIds(List<Long> courseIds) {

        List<Map<String, Object>> result = baseDao.findTeacherNameByCourseIds(courseIds);

        for (Map<String, Object> m : result) {
            for (String k : m.keySet()) {

            }
            if (m.get("goodsId").toString().equals(IGNROE_TEACHER_NAME_GOODS_ID)) {
                m.put("teachersName", new ArrayList<String>());
                break;
            }
        }
        return result;
    }*/
/*
     @Autowired
    private CourseTeacherDao courseTeacherDao;
    
    *//**
     * 添加CourseTeacher
     * @param courseTeacher 要添加的CourseTeacher
     * @return id
     *//*
    public Long addCourseTeacher(CourseTeacher courseTeacher){
    	return courseTeacherDao.addCourseTeacher(courseTeacher);
    }

    *//**
     * 根据id删除一个CourseTeacher
     * @param id 要删除的id
     *//*
    public void deleteCourseTeacherById(Long id){
    	 courseTeacherDao.deleteCourseTeacherById(id);
    }

    *//**
     * 修改CourseTeacher
     * @param courseTeacher 要修改的CourseTeacher
     *//*
    public void updateCourseTeacher(CourseTeacher courseTeacher){
     	courseTeacherDao.updateCourseTeacher(courseTeacher);
    }

    *//**
     * 根据id获取单个CourseTeacher对象
     * @param id 要查询的id
     * @return CourseTeacher
     *//*
    public CourseTeacher getCourseTeacherById(Long id){
    	return courseTeacherDao.getCourseTeacherById( id);
    }

    *//**
     * 根据条件获取CourseTeacher列表
     * @param courseTeacher 查询条件
     * @return List<CourseTeacher>
     *//*
    public List<CourseTeacher> getCourseTeacherList(CourseTeacher courseTeacher){
    	return courseTeacherDao.getCourseTeacherList(courseTeacher);
    }
    *//**
     * 根据条件查询课程统计
     * @param queryCourseProfile
     * @return
     *//*
    public List<CourseProfile> getCourseByteacher(QueryCourseProfile queryCourseProfile, PageEntity entity){
    	return courseTeacherDao.getCourseByteacher(queryCourseProfile,entity);
    }*/

}
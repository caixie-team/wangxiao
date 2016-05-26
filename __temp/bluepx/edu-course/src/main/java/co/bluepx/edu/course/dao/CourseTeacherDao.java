package co.bluepx.edu.course.dao;

import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.course.entity.CourseTeacher;

/**
 * CourseTeacher 数据接口
 */
public interface CourseTeacherDao extends BaseDao<CourseTeacher> {

    /**
     * @param courseIds 课程编号集合
     * @return List<Map<String,Object>>
     * @Title findTeacherNameByCourseIds
     * @Description 根据课程编号集合查询主讲老师名称
     */
//    List<Map<String, Object>> findTeacherNameByCourseIds(@Param("courseIds") List<Long> courseIds);


/*     List<CourseDto> getCourseTeacherListByCourse(List<Long> courses) {


//        if(ObjectUtils.isNull(courses)){
//            return null;
//        }
        try {
            //此service加异常防止获取时出异常，用到的没有事务处理
            Map<Long, List<Teacher>> result = new HashMap<Long, List<Teacher>>();
            List<CourseDto> list = this.selectList("CourseTeacherMapper.getCourseTeacherListByCourse", courses);
            for (CourseDto courseDto  : list) {
                List<Teacher> teacherList = new ArrayList<Teacher>();
                String courseId = courseDto.getId().toString();//map.get("courseId").toString();
                // byte[] teacherIds = (byte[]) map.get("teacherIds");
                String teacherIds=courseDto.getCoursetag();//map.get("teacherIds").toString();
                String teacherName =courseDto.getName();// map.get("teacherNames").toString();
                String[] teaId = new String(teacherIds).split(",");
                String[] teaName = new String(teacherName).split(",");
                if (teaId.length == teaName.length) {
                    for (int i = 0; i < teaId.length; i++) {
                        Teacher teacher = new Teacher();
                        teacher.setId(Long.valueOf(teaId[i]));
                        teacher.setName(teaName[i]);
                        teacherList.add(teacher);
                    }
                    result.put(Long.valueOf(courseId), teacherList);
                }

            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }*/

}
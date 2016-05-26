package io.wangxiao.edu.home.dao.impl.course;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.dao.course.CourseTeacherDao;
import io.wangxiao.edu.home.entity.course.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("courseTeacherDao")
public class CourseTeacherDaoImpl extends GenericDaoImpl implements CourseTeacherDao {

    public java.lang.Long addCourseTeacher(CourseTeacher courseTeacher) {
        return this.insert("CourseTeacherMapper.createCourseTeacher", courseTeacher);
    }

    /**
     * 批量添加CourseTeacher
     */
    public void addCourseTeacherBatch(List<CourseTeacher> courseTeacherList) {
        this.insert("CourseTeacherMapper.addCourseTeacherBatch", courseTeacherList);
    }

    public void deleteCourseTeacherById(Long id) {
        this.delete("CourseTeacherMapper.deleteCourseTeacherById", id);
    }

    /**
     * 根据courseId删除一个CourseTeacher
     *
     * @param courseId
     */
    public void deleteCourseTeacherByCourseId(Long courseId) {
        this.delete("CourseTeacherMapper.deleteCourseTeacherByCourseId", courseId);
    }

    public void updateCourseTeacher(CourseTeacher courseTeacher) {
        this.update("CourseTeacherMapper.updateCourseTeacher", courseTeacher);
    }

    public CourseTeacher getCourseTeacherById(Long id) {
        return this.selectOne("CourseTeacherMapper.getCourseTeacherById", id);
    }

    public List<CourseTeacher> getCourseTeacherList(CourseTeacher courseTeacher) {
        return this.selectList("CourseTeacherMapper.getCourseTeacherList", courseTeacher);
    }

    /**
     * 根据课程获得课程的讲师list
     *
     * @param courses
     * @return
     */
    public Map<Long, List<Teacher>> getCourseTeacherListByCourse(List<Long> courses) {
        if (ObjectUtils.isNull(courses)) {
            return null;
        }
        try {
            //此service加异常防止获取时出异常，用到的没有事务处理
            Map<Long, List<Teacher>> result = new HashMap<Long, List<Teacher>>();
            List<CourseDto> list = this.selectList("CourseTeacherMapper.getCourseTeacherListByCourse", courses);
            for (CourseDto courseDto : list) {
                List<Teacher> teacherList = new ArrayList<Teacher>();
                String courseId = courseDto.getId().toString();//map.get("courseId").toString();
                // byte[] teacherIds = (byte[]) map.get("teacherIds");
                String teacherIds = courseDto.getCoursetag();//map.get("teacherIds").toString();
                String teacherName = courseDto.getName();// map.get("teacherNames").toString();
                String teacherPicPath = courseDto.getLogo();
                String teacherIsStar = courseDto.getSellType();
                String[] teaId = teacherIds.split(",");
                String[] teaName = teacherName.split(",");
                String[] teaPicPath = teacherPicPath.split(",");
                String[] teaIsStar = teacherIsStar.split(",");
                if (teaId.length == teaName.length) {
                    for (int i = 0; i < teaId.length; i++) {
                        Teacher teacher = new Teacher();
                        teacher.setId(Long.valueOf(teaId[i]));
                        teacher.setName(teaName[i]);
                        teacher.setPicPath(teaPicPath[i]);
                        teacher.setIsStar(Long.parseLong(teaIsStar[i]));
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

    }

    /**
     * 根据条件查询课程统计
     *
     * @param queryCourseProfile
     * @return
     */
    public List<CourseProfile> getCourseByteacher(QueryCourseProfile queryCourseProfile, PageEntity entity) {
        return this.queryForListPage("CourseTeacherMapper.getCourseByteacher", queryCourseProfile, entity);
    }

}

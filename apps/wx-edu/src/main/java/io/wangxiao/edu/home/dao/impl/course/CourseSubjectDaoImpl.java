package io.wangxiao.edu.home.dao.impl.course;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.course.CourseSubjectDao;
import io.wangxiao.edu.home.entity.course.CourseSubject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CourseSubjectDao")
public class CourseSubjectDaoImpl extends GenericDaoImpl implements CourseSubjectDao {

    public java.lang.Long addCourseSubject(CourseSubject CourseSubject) {
        return this.insert("CourseSubjectMapper.createCourseSubject", CourseSubject);
    }

    public void deleteCourseSubjectById(Long id) {
        this.delete("CourseSubjectMapper.deleteCourseSubjectById", id);
    }

    /**
     * 根据条件删除一个CourseSubject
     */
    public void deleteCourseSubject(CourseSubject courseSubject) {
        this.delete("CourseSubjectMapper.deleteCourseSubject", courseSubject);
    }

    public void updateCourseSubject(CourseSubject CourseSubject) {
        this.update("CourseSubjectMapper.updateCourseSubject", CourseSubject);
    }

    public CourseSubject getCourseSubjectById(Long id) {
        return this.selectOne("CourseSubjectMapper.getCourseSubjectById", id);
    }

    public List<CourseSubject> getCourseSubjectList(CourseSubject CourseSubject) {
        return this.selectList("CourseSubjectMapper.getCourseSubjectList", CourseSubject);
    }
}

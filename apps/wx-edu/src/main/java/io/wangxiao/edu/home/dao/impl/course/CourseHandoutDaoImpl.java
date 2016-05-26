package io.wangxiao.edu.home.dao.impl.course;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.course.CourseHandoutDao;
import io.wangxiao.edu.home.entity.course.CourseHandout;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("courseHandoutDao")
public class CourseHandoutDaoImpl extends GenericDaoImpl implements CourseHandoutDao {

    @Override
    public Long addCourseHandout(CourseHandout courseHandout) {
        return this.insert("CourseHandoutMapper.addCourseHandout", courseHandout);
    }

    @Override
    public Long deleteCourseHandout(String ids) {
        return this.delete("CourseHandoutMapper.deleteCourseHandout", ids.replace(" ", "").split(","));
    }

    @Override
    public Long updateCourseHandout(CourseHandout courseHandout) {
        return this.update("CourseHandoutMapper.updateCourseHandout", courseHandout);
    }

    @Override
    public CourseHandout getCourseHandoutById(Long id) {
        return this.selectOne("CourseHandoutMapper.getCourseHandoutById", id);
    }

    @Override
    public List<CourseHandout> getCourseHandoutList(CourseHandout courseHandout) {
        return this.selectList("CourseHandoutMapper.getCourseHandoutList", courseHandout);
    }

    @Override
    public List<CourseHandout> getCourseHandoutListPage(CourseHandout courseHandout, PageEntity page) {
        return this.queryForListPage("CourseHandoutMapper.getCourseHandoutListPage", courseHandout, page);
    }
}

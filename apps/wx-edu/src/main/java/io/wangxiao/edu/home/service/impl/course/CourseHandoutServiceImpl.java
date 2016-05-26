package io.wangxiao.edu.home.service.impl.course;


import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.course.CourseHandoutDao;
import io.wangxiao.edu.home.entity.course.CourseHandout;
import io.wangxiao.edu.home.service.course.CourseHandoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("courseHandoutService")
public class CourseHandoutServiceImpl implements CourseHandoutService {

    @Autowired
    private CourseHandoutDao courseHandoutDao;

    @Override
    public Long addCourseHandout(CourseHandout courseHandout) {
        courseHandout.setCreateTime(new Date());
        return this.courseHandoutDao.addCourseHandout(courseHandout);
    }

    @Override
    public Long deleteCourseHandout(String ids) {
        return this.courseHandoutDao.deleteCourseHandout(ids);
    }

    @Override
    public Long updateCourseHandout(CourseHandout courseHandout) {
        return this.courseHandoutDao.updateCourseHandout(courseHandout);
    }

    @Override
    public CourseHandout getCourseHandoutById(Long id) {
        return this.courseHandoutDao.getCourseHandoutById(id);
    }

    @Override
    public List<CourseHandout> getCourseHandoutList(CourseHandout courseHandout) {
        return this.courseHandoutDao.getCourseHandoutList(courseHandout);
    }

    @Override
    public List<CourseHandout> getCourseHandoutListPage(CourseHandout courseHandout, PageEntity page) {
        return this.courseHandoutDao.getCourseHandoutListPage(courseHandout, page);
    }
}
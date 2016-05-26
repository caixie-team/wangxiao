package io.wangxiao.edu.app.service.impl.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.dao.website.AppCourseDao;
import io.wangxiao.edu.app.entity.website.AppCourse;
import io.wangxiao.edu.app.entity.website.AppCourseDto;
import io.wangxiao.edu.app.entity.website.QueryAppCourseCondition;
import io.wangxiao.edu.app.service.website.AppCourseService;
import io.wangxiao.edu.home.dao.course.CourseDao;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.QueryCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("appCourseService")
public class AppCourseServiceImpl implements AppCourseService {
    @Autowired
    private AppCourseDao appCourseDao;
    @Autowired
    private CourseDao courseDao;

    @Override
    public List<AppCourseDto> queryAppMainCourse(QueryAppCourseCondition query,
                                                 PageEntity page) {
        return appCourseDao.queryAppMainCourse(query, page);
    }

    @Override
    public void delAppCourseById(long id) {
        appCourseDao.delAppCourseById(id);
    }

    @Override
    public List<CourseDto> getCourseListPage(QueryCourse course, PageEntity page) {
        return courseDao.getCourseListPage(course, page);
    }

    @Override
    public void createAppMainCourse(AppCourse appCourse) {
        appCourseDao.createAppMainCourse(appCourse);
    }

    @Override
    public int getAppCourseById(Long id) {
        return appCourseDao.getAppCourseById(id);
    }

    @Override
    public List<CourseDto> getAppCourseListPage(QueryCourse course,
                                                PageEntity page) {
        return courseDao.getAppCourseListPage(course, page);
    }

    /**
     * 批量删除app课程
     *
     * @param ids id字符串集合
     */
    public void delAppCourseBatch(String ids) {
        appCourseDao.delAppCourseBatch(ids.substring(0, (ids.length() - 1)));
    }

}

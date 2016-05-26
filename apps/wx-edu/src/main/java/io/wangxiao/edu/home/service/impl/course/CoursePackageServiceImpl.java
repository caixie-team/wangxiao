package io.wangxiao.edu.home.service.impl.course;

import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.home.dao.course.CoursePackageDao;
import io.wangxiao.edu.home.entity.course.CoursePackage;
import io.wangxiao.edu.home.service.course.CoursePackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("coursePackageService")
public class CoursePackageServiceImpl implements CoursePackageService {
    @Autowired
    private CoursePackageDao coursePackageDao;

    /**
     * 添加CoursePackage
     */
    public void addCoursePackageBatch(String ids, Long courseId) {
        if (ObjectUtils.isNotNull(courseId) && StringUtils.isNotEmpty(ids)) {
            String[] courseList = ids.split(",");
            List<CoursePackage> coursePackageList = new ArrayList<CoursePackage>();
            //筛选已经添加过的课程
            for (String str : courseList) {
                if (StringUtils.isNotEmpty(str)) {
                    CoursePackage coursePackage = new CoursePackage();
                    coursePackage.setCourseId(Long.valueOf(str));
                    coursePackage.setMainCourseId(courseId);
                    coursePackage.setOrderNum(0L);
                    if (ObjectUtils.isNull(coursePackageDao.queryCoursePackageById(coursePackage))) {
                        coursePackageList.add(coursePackage);
                    }
                }
            }
            //批量添加课程包关联的课程
            if (ObjectUtils.isNotNull(coursePackageList)) {
                coursePackageDao.addCoursePackageBatch(coursePackageList);
            }
        }
    }

    public void createCoursePackageBatch(List<CoursePackage> coursePackageList) {
        coursePackageDao.addCoursePackageBatch(coursePackageList);
    }

    /**
     * 删除CoursePackage
     */
    public void delCoursePackage(CoursePackage coursePackage) {
        coursePackageDao.delCoursePackage(coursePackage);
    }

    /**
     * 修改套餐课程下的课程排序
     *
     * @param coursePackage
     */
    public void updateCoursePackageOrderNum(CoursePackage coursePackage) {
        coursePackageDao.updateCoursePackageOrderNum(coursePackage);
    }

    /**
     * 获取CoursePackage list
     *
     * @param coursePackage
     * @return
     */
    public List<CoursePackage> getCoursePackageList(CoursePackage coursePackage) {
        return coursePackageDao.getCoursePackageList(coursePackage);
    }

}
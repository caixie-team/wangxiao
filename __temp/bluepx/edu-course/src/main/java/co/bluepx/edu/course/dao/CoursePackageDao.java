package co.bluepx.edu.course.dao;

import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.course.entity.CoursePackage;

import java.util.List;

/**
 * Course管理接口
 */
public interface CoursePackageDao extends BaseDao<CoursePackage> {
    /**
     * 添加CoursePackage
     */
    void addCoursePackageBatch(List<CoursePackage> coursePackageList);

    /**
     * 通过id查询CoursePackage
     */
    CoursePackage queryCoursePackageById(CoursePackage coursePackage);

    /**
     * 删除CoursePackage
     */
    void delCoursePackage(CoursePackage coursePackage);

    /**
     * 修改套餐课程下的课程排序
     *
     */
    void updateCoursePackageOrderNum(CoursePackage coursePackage);
}
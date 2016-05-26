package io.wangxiao.edu.home.dao.impl.course;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.course.CoursePackageDao;
import io.wangxiao.edu.home.entity.course.CoursePackage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("coursePackageDao")
public class CoursePackageDaoImpl extends GenericDaoImpl implements CoursePackageDao {
    /**
     * 添加CoursePackage
     */
    public void addCoursePackageBatch(List<CoursePackage> coursePackageList) {
        insert("CoursepackageMapper.addCoursePackageBatch", coursePackageList);
    }

    /**
     * 通过id查询CoursePackage
     */
    public CoursePackage queryCoursePackageById(CoursePackage coursePackage) {
        return selectOne("CoursepackageMapper.queryCoursePackageById", coursePackage);
    }

    /**
     * 删除CoursePackage
     */
    public void delCoursePackage(CoursePackage coursePackage) {
        delete("CoursepackageMapper.delCoursePackage", coursePackage);
    }

    /**
     * 修改套餐课程下的课程排序
     *
     * @param course
     */
    public void updateCoursePackageOrderNum(CoursePackage coursePackage) {
        this.update("CoursepackageMapper.updateCoursePackageOrderNum", coursePackage);
    }

    @Override
    public void deleteCoursePackageByCourseId(Long courseId) {
        this.delete("CoursepackageMapper.deleteCoursePackageByCourseId", courseId);
    }

    @Override
    public List<CoursePackage> getCoursePackageList(CoursePackage coursePackage) {
        return this.selectList("CoursepackageMapper.getCoursePackageList", coursePackage);
    }

    @Override
    public int getCoursePackageCount(Long id) {
        return this.selectOne("CoursepackageMapper.getCoursePackageCount", id);
    }
}

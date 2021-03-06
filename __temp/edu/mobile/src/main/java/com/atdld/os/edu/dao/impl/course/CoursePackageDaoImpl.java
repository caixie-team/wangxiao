package com.atdld.os.edu.dao.impl.course;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.edu.dao.course.CoursePackageDao;
import com.atdld.os.edu.entity.course.CoursePackage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * Course User:  Date: 2014-05-27
 */
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
	public void delCoursePackage(CoursePackage coursePackage){
		delete("CoursepackageMapper.delCoursePackage", coursePackage);
	}
}

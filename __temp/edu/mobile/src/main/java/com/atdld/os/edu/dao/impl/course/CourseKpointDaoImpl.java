package com.atdld.os.edu.dao.impl.course;

import java.util.List;

import com.atdld.os.edu.entity.course.CourseKpoint;
import com.atdld.os.edu.entity.course.FavouriteCourseDTO;
import com.atdld.os.edu.dao.course.CourseKpointDao;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;

/**
 *
 * CourseKpoint
 * User:
 * Date: 2014-05-27
 */
 @Repository("courseKpointDao")
public class CourseKpointDaoImpl extends GenericDaoImpl implements CourseKpointDao{

    public java.lang.Long addCourseKpoint(CourseKpoint courseKpoint) {
        return this.insert("CourseKpointMapper.createCourseKpoint",courseKpoint);
    }

    public void deleteCourseKpointById(Long id){
        this.delete("CourseKpointMapper.deleteCourseKpointById",id);
    }
    /**
     * 根据id集合删除CourseKpoint
     */
    public void deleteCourseKpointByIdBatch(String[] courseKpointIds){
    	this.update("CourseKpointMapper.deleteCourseKpointByIdBatch",courseKpointIds);
    }

    public void updateCourseKpoint(CourseKpoint courseKpoint) {
        this.update("CourseKpointMapper.updateCourseKpoint",courseKpoint);
    }
    /**
     * 修改CourseKpoint 播放数加一
     */
    public void updateCourseKpointPlaycountAdd(Long kpointId){
    	 this.update("CourseKpointMapper.updateCourseKpointPlaycountAdd",kpointId);
    }
    public CourseKpoint getCourseKpointById(Long id) {
        return this.selectOne("CourseKpointMapper.getCourseKpointById",id);
    }

    public List<CourseKpoint> getCourseKpointList(CourseKpoint courseKpoint) {
        return this.selectList("CourseKpointMapper.getCourseKpointList",courseKpoint);
    }
}

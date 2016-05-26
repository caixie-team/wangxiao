package com.atdld.os.edu.dao.impl.course;

import java.util.List;

import com.atdld.os.edu.entity.course.CourseStudyhistory;
import com.atdld.os.edu.dao.course.CourseStudyhistoryDao;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;

/**
 *
 * CourseStudyhistory
 * User:
 * Date: 2014-05-27
 */
 @Repository("courseStudyhistoryDao")
public class CourseStudyhistoryDaoImpl extends GenericDaoImpl implements CourseStudyhistoryDao{

    public java.lang.Long addCourseStudyhistory(CourseStudyhistory courseStudyhistory) {
        return this.insert("CourseStudyhistoryMapper.createCourseStudyhistory",courseStudyhistory);
    }

    public void deleteCourseStudyhistoryById(Long id){
        this.delete("CourseStudyhistoryMapper.deleteCourseStudyhistoryById",id);
    }

    public void updateCourseStudyhistory(CourseStudyhistory courseStudyhistory) {
        this.update("CourseStudyhistoryMapper.updateCourseStudyhistory",courseStudyhistory);
    }

    public CourseStudyhistory getCourseStudyhistoryById(Long id) {
        return this.selectOne("CourseStudyhistoryMapper.getCourseStudyhistoryById",id);
    }

    public List<CourseStudyhistory> getCourseStudyhistoryList(CourseStudyhistory courseStudyhistory) {
        return this.selectList("CourseStudyhistoryMapper.getCourseStudyhistoryList",courseStudyhistory);
    }
    @Override
    public List<CourseStudyhistory> getCourseStudyhistoryListByCondition(
    		CourseStudyhistory courseStudyhistory, PageEntity page) {
    	// TODO Auto-generated method stub
    	 return this.queryForListPage("CourseStudyhistoryMapper.getCourseStudyhistoryListByCondition",courseStudyhistory,page);
    }
    /**
     * 根据ids删除CourseStudyhistory
     * @param id 要删除的id
     */
    public void delCourseStudyhistory(String ids){
    	this.delete("CourseStudyhistoryMapper.delCourseStudyhistory", ids);
    }
}

package com.atdld.os.edu.dao.impl.course;

import java.util.List;
import com.atdld.os.edu.entity.course.CourseTag;
import com.atdld.os.edu.dao.course.CourseTagDao;
import org.springframework.stereotype.Repository;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * CourseTag
 * User:
 * Date: 2014-05-27
 */
 @Repository("courseTagDao")
public class CourseTagDaoImpl extends GenericDaoImpl implements CourseTagDao{

    public java.lang.Long addCourseTag(CourseTag courseTag) {
        return this.insert("CourseTagMapper.createCourseTag",courseTag);
    }

    public void deleteCourseTagById(Long id){
        this.delete("CourseTagMapper.deleteCourseTagById",id);
    }

    public void updateCourseTag(CourseTag courseTag) {
        this.update("CourseTagMapper.updateCourseTag",courseTag);
    }

    public CourseTag getCourseTagById(Long id) {
        return this.selectOne("CourseTagMapper.getCourseTagById",id);
    }

    public List<CourseTag> getCourseTagList(CourseTag courseTag) {
        return this.selectList("CourseTagMapper.getCourseTagList",courseTag);
    }
}

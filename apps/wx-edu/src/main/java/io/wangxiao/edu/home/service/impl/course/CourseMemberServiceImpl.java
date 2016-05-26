package io.wangxiao.edu.home.service.impl.course;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.wangxiao.edu.home.dao.course.CourseMemberDao;
import io.wangxiao.edu.home.entity.course.CourseMember;
import io.wangxiao.edu.home.service.course.CourseMemberService;


/**
 * CourseMember管理接口
 */
@Service("courseMemberService")
public class CourseMemberServiceImpl implements CourseMemberService {

    @Autowired
    private CourseMemberDao courseMemberDao;


    /**
     * 添加课程所属会员
     *
     * @param CourseMember
     */
    public void addCourseMember(List<CourseMember> courseMembers) {
        courseMemberDao.addCourseMember(courseMembers);
    }

    /**
     * 删除课程所属会员
     *
     * @param id
     */
    public void delCourseMember(Long courseId) {
        courseMemberDao.delCourseMember(courseId);
    }

    /**
     * 根据课程id courseMember list
     *
     * @param courseId
     * @return
     */
    public List<CourseMember> getCourseMemberListByCourseId(Long courseId) {
        return courseMemberDao.getCourseMemberListByCourseId(courseId);
    }

}
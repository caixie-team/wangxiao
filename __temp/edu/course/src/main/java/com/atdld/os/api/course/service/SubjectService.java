package com.atdld.os.api.course.service;

import com.atdld.os.api.course.entity.QuerySubject;
import com.atdld.os.api.course.entity.Subject;

import java.util.List;


/**
 * @author :
 * @ClassName com.atdld.os.sysuser.service.SubjectService
 * @description
 * @Create Date : 2014年6月9日 上午10:32:11
 */
public interface SubjectService {
    /**
     * ①单条增加 返回主键(可以不返回)
     *
     * @param subject
     * @return
     */
    Long addOneSubject(Subject subject);

    /**
     * 根据Pid返回SubjectList
     *
     * @param querySubject
     * @return
     */
    List<Subject> getSubjectList(QuerySubject querySubject);

    /**
     * 批量删除除subject
     *
     * @param ids
     */
    void delSubjects(List<Long> ids);

    /**
     * 通过subjectId获得subject
     *
     * @param subject
     * @return
     */
    Subject getSubjectBySubjectId(Subject subject);

    /**
     * 通过subjectId获得subject
     *
     * @param subject
     */
    void updateSubjectBySubjectId(Subject subject);

    /**
     * @param querySubject
     * @return List<Subject>
     */
    List<Subject> getSubjectListByLevel(QuerySubject querySubject);

    /**
     * 获得所有的专业
     * @return List<Subject>
     */
    List<Subject> getAllSubjectList();


    /**
     * 查询所有一级项目
     *
     * @return
     */
    List<Subject> getSubjectOneList();

    /**
     * 根据父级ID查找子项目集合
     */
    List<Subject> getSubjectListByOne(Long subjectId);
}

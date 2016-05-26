package com.atdld.os.api.course.dao;

import com.atdld.os.api.course.entity.QuerySubject;
import com.atdld.os.api.course.entity.Subject;

import java.util.List;


/**
 * @description
 * @Create Date : 2014年6月9日 上午10:09:28
 */
public interface SubjectDao {
    /**
     * 根据父id查询专业
     *
     * @param parentId
     * @return
     */
    List<Subject> getSubjectList(int parentId);

    /**
     * ①单条增加 返回主键(可以不返回)
     */
    Long addOneSubject(Subject subject);

    /**
     * 根据Pid返回SubjectList
     */
    List<Subject> getSubjectList(QuerySubject querySubject);

    /**
     * 批量删除除subject
     */
    void delSubjects(List<Subject> subjectList);

    /**
     * 通过subjectId获得subject
     */
    Subject getSubjectBySubjectId(Subject subject);

    /**
     * 通过subjectId更新subject
     */
    void updateSubjectBySubjectId(Subject subject);

    /**
     * 查询所有一级项目
     *
     * @return
     */
    List<Subject> getSubjectOneList();

    /**
     * 父级ID查询子项目
     *
     * @return
     */
    List<Subject> getSubjectListByOne(Long subjectId);

}
package io.wangxiao.edu.sysuser.service;

import io.wangxiao.edu.sysuser.entity.QuerySubject;
import io.wangxiao.edu.sysuser.entity.Subject;

import java.util.List;


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
     *
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

    /**
     * 获取首页显示的项目
     *
     * @return
     */
    List<Subject> getSubjectByShowIndex();

    /**
     * 批量显示或隐藏首页显示
     *
     * @param ids       项目id
     * @param showIndex 0隐藏1显示
     */
    Long updateSubjectShowIndexBatch(String ids, Long showIndex);
}

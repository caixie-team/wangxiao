package io.wangxiao.edu.sysuser.dao;

import io.wangxiao.edu.sysuser.entity.QuerySubject;
import io.wangxiao.edu.sysuser.entity.Subject;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询父级首页显示专业
     *
     * @return
     */
    List<Subject> getParentSubjectShowIndex();

    /**
     * 查询子级首页显示专业
     *
     * @param id 父id
     * @return
     */
    List<Subject> getChildrenSubjectShowIndex(Long id);

    /**
     * 批量显示或隐藏首页显示
     *
     * @param map 项目id
     */
    Long updateSubjectShowIndexBatch(Map<String, Object> map);
}
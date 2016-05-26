package io.wangxiao.edu.sysuser.dao.impl;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.sysuser.dao.SubjectDao;
import io.wangxiao.edu.sysuser.entity.QuerySubject;
import io.wangxiao.edu.sysuser.entity.Subject;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Subject对象操作实现类
 */
@Repository("subjectDao")
public class SubjectDaoImpl extends GenericDaoImpl implements SubjectDao {
    /**
     * 根据父id查询专业
     *
     * @param parentId
     * @return
     */
    public List<Subject> getSubjectList(int parentId) {
        return this.selectList("SubjectMapper.getSubjectList", parentId);
    }

    /**
     * ①单条增加 返回主键(可以不返回)
     */
    public Long addOneSubject(Subject subject) {
        return new Long(this.insert("SubjectMapper.createSubject", subject));
    }


    public List<Subject> getSubjectList(QuerySubject querySubject) {
        return this.selectList("SubjectMapper.getSubjectList", querySubject);
    }

    public void delSubjects(List<Subject> subjectList) {
        this.delete("SubjectMapper.deleteSubjectByIdBatch", subjectList);
    }


    public Subject getSubjectBySubjectId(Subject subject) {
        return this.selectOne("SubjectMapper.getSubjectBySubjectId", subject);
    }


    public void updateSubjectBySubjectId(Subject subject) {
        this.update("SubjectMapper.updateSubjectBySubjectId", subject);
    }

    public List<Subject> getSubjectOneList() {

        return this.selectList("SubjectMapper.getSubjectOneList", 0);
    }

    /**
     * 根据父级ID查找子项目集合
     */
    public List<Subject> getSubjectListByOne(Long subjectId) {

        return this.selectList("SubjectMapper.getSubjectListByOne", subjectId);
    }

    @Override
    public List<Subject> getParentSubjectShowIndex() {
        return this.selectList("SubjectMapper.getParentSubjectShowIndex", 0);
    }

    @Override
    public List<Subject> getChildrenSubjectShowIndex(Long id) {
        return this.selectList("SubjectMapper.getChildrenSubjectShowIndex", id);
    }

    @Override
    public Long updateSubjectShowIndexBatch(Map<String, Object> map) {
        return this.update("SubjectMapper.updateSubjectShowIndexBatch", map);
    }
}

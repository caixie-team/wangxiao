package io.wangxiao.edu.home.dao.impl.knowledge;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.knowledge.KnowledgeDao;
import io.wangxiao.edu.home.entity.knowledge.Knowledge;
import io.wangxiao.edu.home.entity.knowledge.KnowledgeCatalog;
import io.wangxiao.edu.home.entity.knowledge.KnowledgeCatalogDTO;
import io.wangxiao.edu.home.entity.knowledge.KnowledgeCatalogMiddleCourse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("knowledgeDao")
public class KnowledgeDaoImpl extends GenericDaoImpl implements KnowledgeDao {

    @Override
    public Long addKnowledge(Knowledge knowledge) {
        return this.insert("KnowledgeMapper.addKnowledge", knowledge);
    }

    @Override
    public Long deleteKnowledge(Long id) {
        return this.delete("KnowledgeMapper.deleteKnowledge", id);
    }

    @Override
    public Long updateKnowledge(Knowledge knowledge) {
        return this.update("KnowledgeMapper.updateKnowledge", knowledge);
    }

    @Override
    public Knowledge getKnowledgeById(Long id) {
        return this.selectOne("KnowledgeMapper.getKnowledgeById", id);
    }

    @Override
    public List<Knowledge> getKnowledgeList(Knowledge knowledge, PageEntity page) {
        return this.queryForListPage("KnowledgeMapper.getKnowledgeList", knowledge, page);
    }

    @Override
    public Long addKnowledgeCatalog(KnowledgeCatalog knowledgeCatalog) {
        return this.insert("KnowledgeMapper.addKnowledgeCatalog", knowledgeCatalog);
    }

    @Override
    public Long deleteKnowledgeCatalog(Long id) {
        return this.delete("KnowledgeMapper.deleteKnowledgeCatalog", id);
    }

    @Override
    public Long updateKnowledgeCatalog(KnowledgeCatalog knowledgeCatalog) {
        return this.update("KnowledgeMapper.updateKnowledgeCatalog", knowledgeCatalog);
    }

    @Override
    public List<KnowledgeCatalogDTO> getKnowledgeCatalog(Long id) {
        return this.selectList("KnowledgeMapper.getKnowledgeCatalog", id);
    }

    @Override
    public KnowledgeCatalog getKnowledgeCatalogById(Long id) {
        return this.selectOne("KnowledgeMapper.getKnowledgeCatalogById", id);
    }

    @Override
    public List<KnowledgeCatalog> getKnowledgeCatalogList(KnowledgeCatalog knowledgeCatalog) {
        return this.selectList("KnowledgeMapper.getKnowledgeCatalogList", knowledgeCatalog);
    }

    @Override
    public Long addKnowledgeCatalogMiddleCourse(KnowledgeCatalogMiddleCourse knowledgeCatalogMiddleCourse) {
        return this.insert("KnowledgeMapper.addKnowledgeCatalogMiddleCourse", knowledgeCatalogMiddleCourse);
    }

    @Override
    public Long deleteKnowledgeCatalogMiddleCourse(String ids) {
        return this.delete("KnowledgeMapper.deleteKnowledgeCatalogMiddleCourse", ids.replace(" ", "").split(","));
    }

    @Override
    public Long deleteKnowledgeCatalogMiddleCourseByCatalogId(Long id) {
        return this.delete("KnowledgeMapper.deleteKnowledgeCatalogMiddleCourseByCatalogId", id);
    }

    @Override
    public Long updateKnowledgeCatalogMiddleCourseSort(KnowledgeCatalogMiddleCourse knowledgeCatalogMiddleCourse) {
        return this.update("KnowledgeMapper.updateKnowledgeCatalogMiddleCourseSort", knowledgeCatalogMiddleCourse);
    }

    @Override
    public KnowledgeCatalogMiddleCourse getKnowledgeCatalogMiddleCourseById(Long id) {
        return this.selectOne("KnowledgeMapper.getKnowledgeCatalogMiddleCourseById", id);
    }

    @Override
    public List<KnowledgeCatalogMiddleCourse> getKnowledgeCatalogMiddleCourseList(KnowledgeCatalogMiddleCourse knowledgeCatalogMiddleCourse) {
        return this.selectList("KnowledgeMapper.getKnowledgeCatalogMiddleCourseList", knowledgeCatalogMiddleCourse);
    }

    @Override
    public int getCourseNumByKnowledgeId(Long id) {
        return this.selectOne("KnowledgeMapper.getCourseNumByKnowledgeId", id);
    }
}


package io.wangxiao.edu.home.service.knowledge;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.knowledge.Knowledge;
import io.wangxiao.edu.home.entity.knowledge.KnowledgeCatalog;
import io.wangxiao.edu.home.entity.knowledge.KnowledgeCatalogDTO;
import io.wangxiao.edu.home.entity.knowledge.KnowledgeCatalogMiddleCourse;

import java.util.List;

/**
 *
 */
public interface KnowledgeService {

    /**
     * 添加 Knowledge
     *
     * @param knowledge Knowledge
     * @return Long
     */
    Long addKnowledge(Knowledge knowledge);

    /**
     * 删除 Knowledge
     *
     * @param id id
     * @return
     */
    Long deleteKnowledge(Long id);

    /**
     * 更新 Knowledge
     *
     * @param knowledge Knowledge
     * @return Long
     */
    Long updateKnowledge(Knowledge knowledge);

    /**
     * 根据id查询 Knowledge
     *
     * @param id id
     * @return Knowledge
     */
    Knowledge getKnowledgeById(Long id);

    /**
     * 查询 Knowledge
     *
     * @param knowledge Knowledge
     * @param page      PageEntity
     * @return List<Knowledge>
     */
    List<Knowledge> getKnowledgeList(Knowledge knowledge, PageEntity page);

    /**
     * 添加 KnowledgeCatalog
     *
     * @param knowledgeCatalog KnowledgeCatalog
     * @return Long
     */
    Long addKnowledgeCatalog(KnowledgeCatalog knowledgeCatalog);

    /**
     * 删除目录
     *
     * @param id String
     * @return Long
     */
    Long deleteKnowledgeCatalog(Long id);

    /**
     * 更新知识体系目录
     *
     * @param knowledgeCatalog 知识体系目录
     * @return 影响行数
     */
    Long updateKnowledgeCatalog(KnowledgeCatalog knowledgeCatalog);

    /**
     * 获取知识体系目录
     *
     * @param id id
     * @return
     */
    List<KnowledgeCatalogDTO> getKnowledgeCatalog(Long id);

    /**
     * 根据id获取知识体系目录
     *
     * @param id id
     * @return 目录
     */
    KnowledgeCatalog getKnowledgeCatalogById(Long id);

    /**
     * 查询知识体系目录
     *
     * @param knowledgeCatalog 知识体系目录
     * @return 知识体系目录列表
     */
    List<KnowledgeCatalog> getKnowledgeCatalogList(KnowledgeCatalog knowledgeCatalog);

    /**
     * 添加目录关联课程
     *
     * @param knowledgeCatalogMiddleCourse 目录关联课程
     * @return 影响行数
     */
    Long addKnowledgeCatalogMiddleCourse(KnowledgeCatalogMiddleCourse knowledgeCatalogMiddleCourse);

    /**
     * 批量删除目录关联课程
     *
     * @param ids 目录关联课程id集合
     * @return 影响行数
     */
    Long deleteKnowledgeCatalogMiddleCourse(String ids);

    /**
     * 修改课程排序
     *
     * @param knowledgeCatalogMiddleCourse 目录关联课程
     * @return 影响行数
     */
    Long updateKnowledgeCatalogMiddleCourseSort(KnowledgeCatalogMiddleCourse knowledgeCatalogMiddleCourse);

    /**
     * 根据id获取目录关联课程id
     *
     * @param id id
     * @return KnowledgeCatalogMiddleCourse
     */
    KnowledgeCatalogMiddleCourse getKnowledgeCatalogMiddleCourseById(Long id);

    /**
     * 课程目录查询
     *
     * @param knowledgeCatalogMiddleCourse 目录关联课程
     * @return 目录关联课程列表
     */
    List<KnowledgeCatalogMiddleCourse> getKnowledgeCatalogMiddleCourseList(KnowledgeCatalogMiddleCourse knowledgeCatalogMiddleCourse);

}


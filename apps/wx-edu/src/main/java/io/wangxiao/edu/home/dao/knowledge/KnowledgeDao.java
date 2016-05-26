package io.wangxiao.edu.home.dao.knowledge;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.knowledge.Knowledge;
import io.wangxiao.edu.home.entity.knowledge.KnowledgeCatalog;
import io.wangxiao.edu.home.entity.knowledge.KnowledgeCatalogDTO;
import io.wangxiao.edu.home.entity.knowledge.KnowledgeCatalogMiddleCourse;

import java.util.List;

/**
 *
 */
public interface KnowledgeDao {

    /**
     * 添加知识体系
     *
     * @param knowledge 知识体系
     * @return 影响行数
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
     * 更新知识体系
     *
     * @param knowledge 知识体系
     * @return 影响行数
     */
    Long updateKnowledge(Knowledge knowledge);

    /**
     * 根据id查询知识体系
     *
     * @param id id
     * @return 知识体系
     */
    Knowledge getKnowledgeById(Long id);

    /**
     * 查询知识体系
     *
     * @param knowledge 知识体系
     * @param page      分页
     * @return 知识体系列表
     */
    List<Knowledge> getKnowledgeList(Knowledge knowledge, PageEntity page);

    /**
     * 添加知识体系目录
     *
     * @param knowledgeCatalog 知识体系目录
     * @return 影响行数
     */
    Long addKnowledgeCatalog(KnowledgeCatalog knowledgeCatalog);

    /**
     * 删除目录
     *
     * @param id id
     * @return 影响行数
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
     * 删除目录关联课程
     *
     * @param id catalogId
     * @return Long
     */
    Long deleteKnowledgeCatalogMiddleCourseByCatalogId(Long id);

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
     * @return
     */
    KnowledgeCatalogMiddleCourse getKnowledgeCatalogMiddleCourseById(Long id);

    /**
     * 课程目录查询
     *
     * @param knowledgeCatalogMiddleCourse 目录关联课程
     * @return 目录关联课程列表
     */
    List<KnowledgeCatalogMiddleCourse> getKnowledgeCatalogMiddleCourseList(KnowledgeCatalogMiddleCourse knowledgeCatalogMiddleCourse);

    /**
     * 根据知识体系id获取课程数量
     *
     * @param id
     * @return
     */
    int getCourseNumByKnowledgeId(Long id);
}


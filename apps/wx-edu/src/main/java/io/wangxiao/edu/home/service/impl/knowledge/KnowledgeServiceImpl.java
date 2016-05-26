package io.wangxiao.edu.home.service.impl.knowledge;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.dao.knowledge.KnowledgeDao;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.knowledge.Knowledge;
import io.wangxiao.edu.home.entity.knowledge.KnowledgeCatalog;
import io.wangxiao.edu.home.entity.knowledge.KnowledgeCatalogDTO;
import io.wangxiao.edu.home.entity.knowledge.KnowledgeCatalogMiddleCourse;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.knowledge.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("knowledgeService")
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    KnowledgeDao knowledgeDao;
    @Autowired
    CourseService courseService;

    @Override
    public Long addKnowledge(Knowledge knowledge) {
        knowledge.setCreateTime(new Date());
        return this.knowledgeDao.addKnowledge(knowledge);
    }

    @Override
    public Long deleteKnowledge(Long id) {
        KnowledgeCatalog knowledgeCatalog = new KnowledgeCatalog();
        knowledgeCatalog.setKnowledgeId(id);
        List<KnowledgeCatalog> knowledgeCatalogList = this.getKnowledgeCatalogList(knowledgeCatalog);
        if (ObjectUtils.isNotNull(knowledgeCatalogList)) {
            for (KnowledgeCatalog catalog : knowledgeCatalogList) {
                this.deleteKnowledgeCatalog(catalog.getId());
            }
        }
        return this.knowledgeDao.deleteKnowledge(id);
    }

    @Override
    public Long updateKnowledge(Knowledge knowledge) {
        return this.knowledgeDao.updateKnowledge(knowledge);
    }

    @Override
    public Knowledge getKnowledgeById(Long id) {
        Knowledge knowledge = this.knowledgeDao.getKnowledgeById(id);
        if (ObjectUtils.isNotNull(knowledge)) {
            int courseNum = knowledgeDao.getCourseNumByKnowledgeId(knowledge.getId());
            knowledge.setCourseNum(courseNum);
        }
        return knowledge;
    }

    @Override
    public List<Knowledge> getKnowledgeList(Knowledge knowledge, PageEntity page) {
        List<Knowledge> knowledgeList = this.knowledgeDao.getKnowledgeList(knowledge, page);
        if (ObjectUtils.isNotNull(knowledgeList)) {
            for (Knowledge _knowledge : knowledgeList) {
                int courseNum = knowledgeDao.getCourseNumByKnowledgeId(_knowledge.getId());
                _knowledge.setCourseNum(courseNum);
            }
        }
        return knowledgeList;
    }

    @Override
    public Long addKnowledgeCatalog(KnowledgeCatalog knowledgeCatalog) {
        knowledgeCatalog.setCreateTime(new Date());
        return this.knowledgeDao.addKnowledgeCatalog(knowledgeCatalog);
    }

    @Override
    public Long deleteKnowledgeCatalog(Long id) {
        this.knowledgeDao.deleteKnowledgeCatalogMiddleCourseByCatalogId(id);
        return this.knowledgeDao.deleteKnowledgeCatalog(id);
    }

    @Override
    public Long updateKnowledgeCatalog(KnowledgeCatalog knowledgeCatalog) {
        return this.knowledgeDao.updateKnowledgeCatalog(knowledgeCatalog);
    }

    @Override
    public List<KnowledgeCatalogDTO> getKnowledgeCatalog(Long id) {
        List<KnowledgeCatalogDTO> knowledgeCatalog = this.knowledgeDao.getKnowledgeCatalog(id);
        if (ObjectUtils.isNotNull(knowledgeCatalog)) {
            for (KnowledgeCatalogDTO catalog : knowledgeCatalog) {
                if (ObjectUtils.isNotNull(catalog.getMiddleList())) {
                    List<CourseDto> courseList = new ArrayList<>();
                    for (KnowledgeCatalogMiddleCourse course : catalog.getMiddleList()) {
                        CourseDto _course = courseService.getCourseInfoByCourseId(course.getCourseId());
                        courseList.add(_course);
                    }
                    catalog.setCourseList(courseList);
                }
            }
        }
        return knowledgeCatalog;
    }

    @Override
    public KnowledgeCatalog getKnowledgeCatalogById(Long id) {
        return this.knowledgeDao.getKnowledgeCatalogById(id);
    }

    @Override
    public List<KnowledgeCatalog> getKnowledgeCatalogList(KnowledgeCatalog knowledgeCatalog) {
        return this.knowledgeDao.getKnowledgeCatalogList(knowledgeCatalog);
    }

    @Override
    public Long addKnowledgeCatalogMiddleCourse(KnowledgeCatalogMiddleCourse knowledgeCatalogMiddleCourse) {
        knowledgeCatalogMiddleCourse.setCreateTime(new Date());
        Course course = courseService.getCourseById(knowledgeCatalogMiddleCourse.getCourseId());
        knowledgeCatalogMiddleCourse.setCourseName(course.getName());
        return this.knowledgeDao.addKnowledgeCatalogMiddleCourse(knowledgeCatalogMiddleCourse);
    }

    @Override
    public Long deleteKnowledgeCatalogMiddleCourse(String ids) {
        return this.knowledgeDao.deleteKnowledgeCatalogMiddleCourse(ids);
    }

    @Override
    public Long updateKnowledgeCatalogMiddleCourseSort(KnowledgeCatalogMiddleCourse knowledgeCatalogMiddleCourse) {
        return this.knowledgeDao.updateKnowledgeCatalogMiddleCourseSort(knowledgeCatalogMiddleCourse);
    }

    @Override
    public KnowledgeCatalogMiddleCourse getKnowledgeCatalogMiddleCourseById(Long id) {
        return this.knowledgeDao.getKnowledgeCatalogMiddleCourseById(id);
    }

    @Override
    public List<KnowledgeCatalogMiddleCourse> getKnowledgeCatalogMiddleCourseList(KnowledgeCatalogMiddleCourse knowledgeCatalogMiddleCourse) {
        return this.knowledgeDao.getKnowledgeCatalogMiddleCourseList(knowledgeCatalogMiddleCourse);
    }
}


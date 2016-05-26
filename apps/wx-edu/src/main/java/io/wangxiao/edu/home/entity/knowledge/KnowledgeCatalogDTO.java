package io.wangxiao.edu.home.entity.knowledge;

import io.wangxiao.edu.home.entity.course.CourseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * Description:知识体系目录
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KnowledgeCatalogDTO extends KnowledgeCatalog implements Serializable {
    /**
     * 中间表列表
     */
    private List<KnowledgeCatalogMiddleCourse> middleList;

    /**
     * 课程列表
     */
    private List<CourseDto> courseList;
}

package io.wangxiao.edu.home.entity.knowledge;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:目录课程中间表
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KnowledgeCatalogMiddleCourse implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 知识体系目录ID
     */
    private Long catalogId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 排序
     */
    private int sort;
}

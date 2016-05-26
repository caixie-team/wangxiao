package io.wangxiao.edu.home.entity.knowledge;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:知识体系目录
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KnowledgeCatalog implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 图片路径
     */
    private String picture;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 知识体系ID
     */
    private Long knowledgeId;

    /**
     * 排序
     */
    private int sort;
}

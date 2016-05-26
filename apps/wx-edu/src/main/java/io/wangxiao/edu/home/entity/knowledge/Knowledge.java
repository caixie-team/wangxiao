package io.wangxiao.edu.home.entity.knowledge;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:知识体系
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Knowledge implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片路径
     */
    private String picture;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 排序
     */
    private int sort;

    // 课程数量
    private int courseNum;

    private String startTime;

    private String endTime;
}

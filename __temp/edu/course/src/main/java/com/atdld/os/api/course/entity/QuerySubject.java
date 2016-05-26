package com.atdld.os.api.course.entity;

import com.atdld.os.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
public class QuerySubject extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -1912600357482790771L;
    /**
     *
     */
    /**
     * 专业id
     */
    private Long subjectId = -1L;
    /**
     * 专业名称
     */
    private String subjectName;
    /**
     * 状态
     */
    private int status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 父节点
     */
    private Long parentId = -1L;
    /**
     * 等级 1，2，3
     */
    // private int level;
    //图片
    private String image;
}

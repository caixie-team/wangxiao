package io.wangxiao.edu.sysuser.entity;

import io.wangxiao.commons.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
public class QuerySubject extends BaseEntity {

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
    private int type;//1关键词分类 2专业分类
    private String promote;//推广
}

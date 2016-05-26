package com.atdld.os.sns.entity.lucene;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :
 * @ClassName com.atdld.os.sns.entity.lucene.LuceneLast
 * @description
 * @Create Date : 2013-12-25 下午4:14:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LuceneLast implements Serializable {

    /**
     *
     *
     */
    private static final long serialVersionUID = -1584237669995965898L;

    private Long id;// 主键

    private String project;// 项目，微博、文章、建议

    private Long maxid;// 最后一次处理的的id

    private Date updatetime;// 最后更新时间

}
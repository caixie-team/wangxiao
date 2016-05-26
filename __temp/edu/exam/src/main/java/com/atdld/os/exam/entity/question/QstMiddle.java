package com.atdld.os.exam.entity.question;


import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class QstMiddle extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -5794185938561357214L;
    /**
     * Id
     */
    private Long id;
    /**
     * 试卷Id
     */
    private Long paperId;
    /**
     * 试题Id
     */
    private Long qstId;
    /**
     * 试题类型
     */
    private int qstType;
    /**
     * 复合题类型
     */
    private Long complexId;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 组卷试题排序
     */
    private int sort;
    /**
     * paperMiddle的Id
     */
    private Long paperMiddleId;
    /**
     * paperRecord的Id
     */
    private Long paperRecordId;
    /**
     * 用户Id
     */
    private Long cusId;
    /**
     * 试题内容
     */
    private String qstContent;
}

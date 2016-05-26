package com.atdld.os.exam.entity.note;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryNoteQuestion extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 5267681294890693049L;
    /**
     * Id
     */
    private Long id;
    /**
     * 用户Id
     */
    private Long cusId;
    /**
     * 试题Id
     */
    private Long qstId;
    /**
     * 专业Id
     */
    private Long subjectId;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 笔记内容
     */
    private String noteContent;

}

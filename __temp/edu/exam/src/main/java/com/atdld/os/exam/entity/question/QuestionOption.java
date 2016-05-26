package com.atdld.os.exam.entity.question;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionOption extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -8709294324437895837L;
    /**
     * 选项Id
     */
    private Long id;
    /**
     * 试题id
     */
    private Long qstId;
    /**
     * 选项内容
     */
    private String optContent;
    /**
     * 选项
     */
    private String optOrder;
    /**
     * 选择的答案
     */
    private String optAnswer;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 试题类型
     */
    private int qstType;
}

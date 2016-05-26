package com.atdld.os.exam.entity.question;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class Question extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -589283590702563827L;
    /**
     * 试题Id
     */
    private Long id;
    /**
     * 试题内容
     */
    private String qstContent;
    /**
     * 正确选项
     */
    private String isAsr;
    /**
     * 试题类型
     */
    private int qstType;
    /**
     * 试题难度
     */
    private int level;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 试题分析
     */
    private String qstAnalyze;
    /**
     * 知识点考点Id
     */
    private Long pointId;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 项目id
     */
    private Long subjectId;
    /**
     * 状态
     */
    private int status;
    /**
     * 出题人
     */
    private String author;
    /**
     * 标志
     */
    private String flag;
    /**
     * 判断材料题还是非材料题
     */
    private Long complexFalg = 0L;
    /**
     * 被考过多少次
     */
    private int time;
    /**
     * 正确次数
     */
    private int rightTime;
    /**
     * 错误次数
     */
    private int errorTime;
    /**
     * 正确率
     */
    private float accuracy;
}

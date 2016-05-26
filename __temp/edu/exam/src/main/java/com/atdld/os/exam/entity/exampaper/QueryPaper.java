package com.atdld.os.exam.entity.exampaper;

import java.util.Date;


import lombok.Data;
import lombok.EqualsAndHashCode;
import com.atdld.os.core.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryPaper extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 2013190216862802717L;
    /**
     * 试卷Id
     */
    private Long id;
    /**
     * 所属专业ID
     */
    private Long subjectId;
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 考试描述
     */
    private String info;
    /**
     * 答题考试时间
     */
    private int replyTime;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 考试状态
     */
    private int status;
    /**
     * 试卷难易程度
     */
    private int level;
    /**
     * 参加考试人数
     */
    private int joinNum;
    /**
     * 平均分
     */
    private float avgScore;
    /**
     * 关节点类型
     */
    private int type;
    //试题总数
    private int qstCount;
    //试卷作者
    private String author;
    //更新时间
    private Date updateTime;
    //专业名称
    private String subjectName;
    /**
     * 试卷总分
     */
    private int score;
    //用户Id
    private Long cusId;
    /**
     * 每个试卷考过次数
     */
    private int passNum;
    //专业id
    private int professionalId;
}

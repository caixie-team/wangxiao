package com.atdld.os.exam.entity.question;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryQstMiddle extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 491534082699009356L;
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
     * exam_qstmiddle_tbl 试题类型
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
     * 试题内容
     */
    private String qstContent;
    /**
     * 材料题内容
     */
    private String complexContent;
    /**
     * 选项内容
     */
    private String optContent;
    /**
     * 选项
     */
    private String optOrder;
    /**
     * 选项答案
     */
    private String optAnswer;
    /**
     * 选项答案
     */
    private List<QuestionOption> optionList = new ArrayList<QuestionOption>();
    /**
     * 试题答案
     */
    private String isAsr;
    /**
     * 试题分析
     */
    private String qstAnalyze;
    /**
     * 考点
     */
    private Long pointId;
    /**
     * 用户答案
     */
    private String userAnswer;
    /**
     * 试题收藏表id
     */
    private Long favoritesId;
    /**
     * 是否答对该题0为正确1为错误
     */
    private int status;
    /**
     * 笔记内容
     */
    private String noteContent;
    /**
     * 笔记Id
     */
    private Long noteId;
    /**
     * 试题表的试题类型
     */
    private int questionType;
    /**
     * 考点名
     */
    private String pointName;
    /**
     * 考点父id
     */
    private Long parentId;
    /**
     * 试题得分
     */
    private int score;
    /**
     * 0未审阅1已审阅
     */
    private int state;
    /**
     * 试题记录id
     */
    private Long qstrdId;
}

package com.atdld.os.exam.entity.question;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionRecord extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 257359238086209932L;
    /**
     * 选项Id
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
     * 用户答案
     */
    private String userAnswer;
    /**
     * 状态0为正确1为错误
     */
    private int status;
    /**
     * 用户Id
     */
    private Long cusId;
    /**
     * 试卷记录表Id
     */
    private Long paperRecordId;
    /**
     * 添加试卷
     */
    private Date addTime;
    /**
     * paperMiddleId
     */
    private Long paperMiddleId;
    /**
     * 上次记得的questionRecordId
     */
    private Long lastQuestionRecordId;
    /**
     * 试题数量
     */
    private int qstNum;
    /**
     * 正确题数
     */
    private int rightQstNum;
    private int qstType;// 试题类型冗余
    private Long pointId;// 试题考点冗余
    private int score;//试题得分
}

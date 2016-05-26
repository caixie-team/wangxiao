package com.atdld.os.exam.entity.exampaper;

import java.util.Date;

import com.atdld.os.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author
 * @ClassName Paper
 * @package com.atdld.os.exam.entity.exampaper
 * @description
 * @Create Date: 2013-9-27 下午7:17:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ExamPaper extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -2389521716166433210L;
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
    private String info;
    private int replyTime;
    private Date addTime;
    private int status;
    private int level;
    private int joinNum;
    private int avgScore;
    private int type;
    private int qstCount;
    private String author;
    private Date updateTime;
    private int score;
}

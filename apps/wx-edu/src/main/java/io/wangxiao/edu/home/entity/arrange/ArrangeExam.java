package io.wangxiao.edu.home.entity.arrange;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArrangeExam implements Serializable {
    /**
     * 试卷任务主键
     */
    private Long id;
    /**
     * 安排考试Id
     */
    private Long arrangeId;
    /**
     * 试卷id
     */
    private Long exampaperId;
    /**
     * 试卷名称
     */
    private String exampaperName;
    /**
     * 限制答题的时间 单位分钟 (备用)
     */
    private Long replyTime;
    /**
     * 试卷难度级别(备用)
     * 1:简单
     * 2:中等
     * 3:困难
     */
    private Long exampaperLevel;
    /**
     * 参加考试人数 (备用)
     */
    private Long joinNum;
    /**
     * 参加考试的平均分数 (备用)
     */
    private java.math.BigDecimal avgscore;
}

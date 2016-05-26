package io.wangxiao.edu.home.entity.task;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class TaskExam implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 试卷任务主键
     */
    private Long id;
    /**
     * 任务id
     */
    private Long taskId;
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

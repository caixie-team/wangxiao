package io.wangxiao.edu.home.entity.task;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class TaskRecord implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 任务记录主键
     */
    private Long id;
    /**
     * 员工Id
     */
    private Long userId;
    /**
     * 任务Id
     */
    private Long taskId;
    /**
     * 部门Id
     */
    private Long userGroupId;
    /**
     * 试卷Id
     */
    private Long exampaperId;
    /**
     * 试卷名称
     */
    private String exampaperName;
    /**
     * 分数
     */
    private java.math.BigDecimal score;
    /**
     * 是否完成
     * 0:未完成
     * 1:完成
     */
    private Long isComplete;
    /**
     * 提交时间
     */
    private java.util.Date submitTime;
    private String name;
    /**
     * 任务名
     */
    private String taskname;
    /**
     * 用户名
     */
    private String nickname;
    /**
     * 部门名
     */
    private String groupname;

}





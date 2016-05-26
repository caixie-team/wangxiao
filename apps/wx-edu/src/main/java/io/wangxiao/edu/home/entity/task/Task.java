package io.wangxiao.edu.home.entity.task;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class Task implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 任务主键
     */
    private Long id;
    /**
     * 任务名称
     */
    private String name;
    /**
     * 任务开始时间
     */
    private java.util.Date beginTime;
    /**
     * 任务结束时间
     */
    private java.util.Date endTime;
    /**
     * 发布人
     */
    private String releasePeople;
    /**
     * 任务发布时间
     */
    private java.util.Date releaseTime;
    /**
     * 任务状态
     * 0:未启动
     * 1:启动
     * 2"作废
     */
    private Long status;
    /**
     * 是否可重复
     * 0:不可重复
     * 1:可重复
     */
    private Long isRepeat;
    /**
     * 任务类型
     */
    private int type;
    /**
     * 员工Ids
     */
    private String userIds;
    private String userNames;
    private Long userId;
    /**
     * 试卷Ids
     */
    private String examIds;
    private String examNames;
    /**
     * 部门Id
     */
    private String groupIds;
    private Long userGroupId;
    private String exampaperName;
    private String groupName;
    private String nickname;
    private String taskname;
    private Long taskstatus;
    private String description;
    private int sort;
    private Long taskid;

    private int submit;//1未提交2已提交
}

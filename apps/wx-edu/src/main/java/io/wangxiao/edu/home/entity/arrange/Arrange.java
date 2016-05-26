package io.wangxiao.edu.home.entity.arrange;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class Arrange implements Serializable {
    /**
     * 安排考试主键
     */
    private Long id;
    /**
     * 安排考试名称
     */
    private String name;
    /**
     * 安排考试开始时间
     */
    private java.util.Date beginTime;
    /**
     * 安排考试结束时间
     */
    private java.util.Date endTime;
    /**
     * 发布人
     */
    private String releasePeople;
    /**
     * 安排考试发布时间
     */
    private java.util.Date releaseTime;
    /**
     * notrelease,// 未发布
     * release,// 已发布
     * END,//已结束
     * DISUSE,//废用
     */
    private String status;
    /**
     * 是否可重复
     * 0:不可重复
     * 1:可重复
     */
    private Long isRepeat;
    /**
     * 安排考试类型
     */
    private Long type;
    private Long createrId;//创建人id
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
    private Long iscomplete;


}

package io.wangxiao.edu.home.entity.arrange;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArrangeRecord implements Serializable {
    /**
     * 任务记录主键
     */
    private Long id;
    /**
     * 员工Id
     */
    private Long userId;
    /**
     * 安排考试Id
     */
    private Long arrangeId;
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
     * 考试记录id
     */
    private Long examRecordId;
    /**
     * 提交时间
     */
    private java.util.Date submitTime;
    /**
     * 用户名
     */
    private String nickname;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 部门名
     */
    private String groupname;
    /**
     * 安排考试任务名称
     */
    private String arrangeName;
    private Date beginTime;
    private Date endTime;
    //是否可重复 0不可重复，1可重复
    private Long isRepeat;

    public Long getBeginTimeNum() {
        if (beginTime != null) {
            Long num = (beginTime.getTime() / 1000) - (new Date().getTime() / 1000);
            if (num < 0) {
                return 0l;
            } else {
                return num;
            }
        }
        return 0l;

    }

    public Long getEndTimeNum() {
        if (endTime != null) {
            Long num = (endTime.getTime() / 1000) - (new Date().getTime() / 1000);
            if (num < 0) {
                return 0l;
            } else {
                return num;
            }
        }
        return 0l;
    }

}





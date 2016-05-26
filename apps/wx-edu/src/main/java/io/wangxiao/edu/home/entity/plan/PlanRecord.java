package io.wangxiao.edu.home.entity.plan;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class PlanRecord implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long planId;//计划id
    private Long userId;//用户id
    private int totalTime;//总学时
    private int completeTime;//完成学时
    private Date updateTime;//最近学习时间
    private Date finishTime;//完成时间
    private Long courseId;//当前学习课程id

    private String userName;//用户名
    private String avatar;//用户头像

    private String planName;//计划名称
    private Date releaseTime;//发布时间
    private Date endTime;//结束时间
    private int peopleNum;// 参加计划人数
    private int completeNum;// 完成计划人数

    public double getProgressPercentage() {
        if (this.totalTime > 0) {
            return completeTime * 1.0 / totalTime;
        }
        return 0;
    }

    // 剩余天数
    public int getOverDays() {
        return getDays(new Date(), this.getEndTime());
    }

    // 计算两个时间相差几天
    public int getDays(Date beginTime, Date endTime) {
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        long l = end.getTimeInMillis() - begin.getTimeInMillis();
        int days = new Long(l / (1000 * 60 * 60 * 24)).intValue();
        return days;
    }
}

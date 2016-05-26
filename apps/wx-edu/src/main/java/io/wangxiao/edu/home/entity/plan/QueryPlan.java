package io.wangxiao.edu.home.entity.plan;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryPlan extends Plan implements Serializable {

    private String userIds;//员工Ids
    private String groupIds;//部门Ids
    private String examIds;//试卷Ids

    private int orderNum;//排序分类1按人数倒序

    // 计划天数
    public int getTotalDate() {
        return getDays(this.getBeginTime(), this.getEndTime());
    }

    // 剩余天数
    public int getOverDate() {
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

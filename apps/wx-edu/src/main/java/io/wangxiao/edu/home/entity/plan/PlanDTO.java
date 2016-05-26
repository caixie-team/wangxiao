package io.wangxiao.edu.home.entity.plan;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Calendar;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class PlanDTO extends Plan{

    private int totalTime;// 计划总时长

    // 计划天数
    public int getTotalDate(){
        return getDays(this.getBeginTime(),this.getEndTime());
    }

    // 剩余天数
    public int getOverDate(){
        return getDays(new Date(),this.getEndTime());
    }

    // 计算两个时间相差几天
    public int getDays(Date beginTime,Date endTime){
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        long l=end.getTimeInMillis()-begin.getTimeInMillis();
        int days=new Long(l/(1000*60*60*24)).intValue();
        return days;
    }
}

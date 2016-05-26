package io.wangxiao.edu.home.entity.plan;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PlanMiddleUserDTO extends PlanMiddleUser{

    private String userName;// 员工姓名

    /**
     * 总时长
     */
    private int totalTime;

    /**
     * 总完成时长
     */
    private int complete;

    /**
     * 完成百分比
     */
    public double getProgressPercentage(){
        if(totalTime>0){
            return complete*1.0 / totalTime;
        }
        return 0;
    }
}

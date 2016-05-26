package io.wangxiao.edu.home.entity.plan;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PlanMiddleGroupDTO extends PlanMiddleGroup {

    /**
     * 部门名称
     */
    private String groupName;

    /**
     * 部门计划总时长
     */
    private int totalTime;

    /**
     * 部门计划总完成时长
     */
    private int complete;

    /**
     * 部门人数
     */
    private int peopleNum;

    /**
     * 平均完成时长
     */
    public int getAverageComplete() {
        if (peopleNum > 0 && complete > 0) {
            return complete / peopleNum;
        }
        return 0;
    }

    /**
     * 完成百分比
     */
    public double getProgressPercentage() {
        if (totalTime > 0) {
            return getAverageComplete() * 1.0 / totalTime;
        }
        return 0;
    }
}

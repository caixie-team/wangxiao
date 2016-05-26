package io.wangxiao.edu.home.entity.plan;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class PlanMiddleGroup implements Serializable {
    /**
     * 部门任务主键
     */
    private Long id;
    /**
     * 部门id
     */
    private Long userGroupId;
    /**
     * 任务id
     */
    private Long planId;
}

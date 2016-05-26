package io.wangxiao.edu.home.entity.task;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class TaskMiddleGroup implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
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
    private Long taskId;
}

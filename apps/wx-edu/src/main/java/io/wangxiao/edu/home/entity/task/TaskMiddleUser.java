package io.wangxiao.edu.home.entity.task;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class TaskMiddleUser implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 员工任务主键
     */
    private Long id;
    /**
     * 员工id
     */
    private Long userId;
    /**
     * 任务主键_id
     */
    private Long taskId;
    /**
     * 部门Id
     */
    private Long groupId;
}

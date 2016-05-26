package io.wangxiao.edu.home.entity.arrange;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArrangeMiddleGroup implements Serializable {
    /**
     * 部门任务主键
     */
    private Long id;
    /**
     * 部门id
     */
    private Long userGroupId;
    /**
     * 安排考试Id
     */
    private Long arrangeId;
}

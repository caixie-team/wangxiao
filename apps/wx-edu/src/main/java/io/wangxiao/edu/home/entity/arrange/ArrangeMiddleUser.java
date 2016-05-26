package io.wangxiao.edu.home.entity.arrange;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArrangeMiddleUser implements Serializable {
    /**
     * 员工任务主键
     */
    private Long id;
    /**
     * 员工id
     */
    private Long userId;
    /**
     * 安排考试Id
     */
    private Long arrangeId;
}

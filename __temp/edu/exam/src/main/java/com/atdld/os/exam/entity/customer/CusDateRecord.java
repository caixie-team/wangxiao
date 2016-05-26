package com.atdld.os.exam.entity.customer;


import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class CusDateRecord extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 主键Id
     */
    private Long id;
    /**
     * 错题数量
     */
    private int errorQstNum;
    /**
     * 用户id
     */
    private Long cusId;
    /**
     * 正确题数
     */
    private int rightQstNum;
    /**
     * 做过的总题数
     */
    private int qstNum;
    /**
     * 平均分
     */
    private int averageScore;
    /**
     * 专业id
     */
    private Long subjectId;

}

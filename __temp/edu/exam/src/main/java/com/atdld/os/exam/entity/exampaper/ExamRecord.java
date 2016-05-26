package com.atdld.os.exam.entity.exampaper;

import java.util.Date;

import com.atdld.os.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author
 * @ClassName Paper
 * @package com.atdld.os.exam.entity.exampaper
 * @description
 * @Create Date: 2013-9-27 下午7:17:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ExamRecord extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -4802679776491972905L;
    /**
     * 试卷Id
     */
    private Long id;
    /**
     * 上次记录的Id
     */
    private Long lastRecordId;
    /**
     * 上次记录更新时间
     */
    private Date lastUpdateRecord;
    /**
     * 区别类型
     */
    private String Type;
}

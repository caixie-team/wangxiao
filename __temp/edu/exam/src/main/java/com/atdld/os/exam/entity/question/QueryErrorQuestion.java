package com.atdld.os.exam.entity.question;

import java.util.Date;

import com.atdld.os.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryErrorQuestion extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 4358469301226451536L;
    /**
     * Id
     */
    private Long id;
    /**
     * 用户Id
     */
    private Long cusId;
    /**
     * 试卷Id
     */
    private Long paperId;
    /**
     * 试题Id
     */
    private Long qstId;
    /**
     * 专业Id
     */
    private Long subjectId;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * paperRecord的id
     */
    private Long paperRecordId;
    /**
     * 试题位置
     */
    private int placeNumber;


}

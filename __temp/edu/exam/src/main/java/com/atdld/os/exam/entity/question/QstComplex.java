package com.atdld.os.exam.entity.question;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class QstComplex extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -7357965645187966005L;
    /**
     * 选项Id
     */
    private Long id;
    /**
     * 材料题内容
     */
    private String complexContent;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 试题中间表list
     */
    private List<QueryQstMiddle> queryQstMiddleList = new ArrayList<QueryQstMiddle>();
    private Long paperId;// 试卷id
    private Long paperMiddleId;// 试卷中间表id
}

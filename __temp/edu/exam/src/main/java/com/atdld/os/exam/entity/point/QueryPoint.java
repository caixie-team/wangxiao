package com.atdld.os.exam.entity.point;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryPoint extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 3739894965121564833L;
    /**
     * 考点Id
     */
    private Long id;
    /**
     * 父id
     */
    private Long pId;
    /**
     * 考点名字
     */
    private String name;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 专业id
     */
    private Long subjectId;
    /**
     * 考频
     */
    private int examFrequency;
    /**
     * 考点信息
     */
    private String info;
    /**
     * 树的等级
     */
    private int level;
    /**
     * 状态1显示2删除
     */
    private int state = 1;
    /**
     * 该考点的的试题数量
     */
    private int qstCount;
    /**
     * 该用户在该考点下作对过的试题数量
     */
    private int cusRightQstNum;
    /**
     * 用户Id
     */
    private Long cusId;
    private Long paperId;//查询试卷报告时用
    private Long paperRecordId; //查询试卷报告时用
}

package com.atdld.os.exam.entity.point;

import com.atdld.os.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExamPoint extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -2972088766561758830L;
    /**
     * 考点Id
     */
    private Long id;
    /**
     * 父id
     */
    private Long parentId;
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

    private String pointNameAndId;
    private List<ExamPoint> examPointList = new ArrayList<ExamPoint>();
    public String getPointNameAndId() {
        return this.getName() + "(id:" + this.getId() + ")";
    }
}

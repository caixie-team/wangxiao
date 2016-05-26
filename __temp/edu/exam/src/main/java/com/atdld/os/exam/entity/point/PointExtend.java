package com.atdld.os.exam.entity.point;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.entity.BaseEntity;

/**
 * @author
 * @ClassName PointExtend
 * @package com.atdld.os.exam.entity.point
 * @description 考点扩展数据
 * @Create Date: 2013-10-12 下午5:09:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PointExtend extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -2241785576574999737L;
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
    private String level;
    /**
     * 状态1显示2删除
     */
    private int state = 1;
    //考点下的试题数量
    private Long qstCount;
    //不包含的试题类型（随机出题，查数量时要把材料分析的选项的剔除掉）
    private int excludeType;
    /**
     * 该用户在该考点下作对过的试题数量
     */
    private int cusRightQstNum;

    private Long paperId;//试卷id查询报告用
}

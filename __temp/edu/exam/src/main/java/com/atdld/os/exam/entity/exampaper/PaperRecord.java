package com.atdld.os.exam.entity.exampaper;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.entity.BaseEntity;


/**
 * @author
 * @ClassName Paper
 * @package com.atdld.os.exam.entity.exampaper
 * @description
 * @Create Date: 2013-9-27 下午7:17:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PaperRecord extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 5155840960297449726L;
    /**
     * 试卷Id
     */
    private Long id;
    /**
     * 所属专业ID
     */
    private Long subjectId;
    /**
     * 用户分数
     */
    private float userScore;
    /**
     * 正确率
     */
    private float accuracy;
    /**
     * 用户Id
     */
    private Long cusId;
    /**
     * 试卷id
     */
    private Long epId;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 测试用时
     */
    private int testTime;
    /**
     * 考试题数
     */
    private int qstCount;
    /**
     * 正确题数
     */
    private int correctNum;
    /**
     * 1为随机2为试题组卷
     */
    private int type;
    /**
     * 0为默认1为未考完
     */
    private int status;
    /**
     * 限制答题时间
     */
    private int replyTime;
    /**
     * 试卷名字
     */
    private String name;
    /**
     * 试卷名字
     */
    private String paperName;
    /**
     * 主观题得分（手动）
     */
    private BigDecimal subjectiveScore;
    /**
     * 客观题得分
     */
    private BigDecimal objectiveScore;
}

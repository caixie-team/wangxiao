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
public class QueryPaperRecord extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -1546098842448254079L;
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
     * 试题记录表状态表示该题是否正确
     */
    private int questionStatus;
    /**
     * 1为随机2为试题组卷
     */
    private int type;
    /**
     * 试题名称
     */
    private String name;
    /**
     * 0为默认1为未考完
     */
    private int status;
    /**
     * 试题名称
     */
    private String paperName;
    /**
     * 上次试卷记录Id
     */
    private Date lastUpdateRecord;
    /**
     * 每个试卷的分数和
     */
    private int sumUserScore;
    /**
     * 每个试卷的分数和
     */
    private int num;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 格式化正确率
     */
    private String fmtAccuracy;
    /**
     * 格式化测试用时
     */
    private String fmtTestTime;
    /**
     * 交卷时间开始时间 （用于查询）
     */
    private String startTime;
    /**
     * 交卷时间结束时间（用于查询）
     */
    private String endTime;
    /**
     * 主观题得分（手动）
     */
    private BigDecimal subjectiveScore;
    /**
     * 客观题得分
     */
    private BigDecimal objectiveScore;
    /**
     * 试题id
     */
    private long qstId;
    /**
     * 用户答案
     */
    private String userAnswer;
    /**
     * 试题记录id
     */
    private Long qstrcdId;
    /**
     * 试题内容
     */
    private String qstContent;
    /**
     * 试题分数
     */
    private int score;
    /**
     * 阅卷分数
     */
    private int qstrdScore;
    /**
     * 是否审阅 0未审阅1已审阅
     */
    private int state;

    public void setTestTime(int testTime) {
        this.testTime = testTime;
        if (testTime < 60) {
            fmtTestTime = "1";
        } else {
            fmtTestTime = testTime / 60 + "";
        }
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
        java.text.NumberFormat percentFormat = java.text.NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(2); //最大小数位数
        percentFormat.setMaximumIntegerDigits(3);//最大整数位数
        percentFormat.setMinimumFractionDigits(0); //最小小数位数
        percentFormat.setMinimumIntegerDigits(0);//最小整数位数
        fmtAccuracy = percentFormat.format(accuracy);//自动转换成百分比显示..
    }

    public Date getAddTime() {
        return addTime;
    }
}

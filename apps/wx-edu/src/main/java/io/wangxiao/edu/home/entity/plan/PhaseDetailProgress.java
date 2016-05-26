package io.wangxiao.edu.home.entity.plan;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 阶段详细进度
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PhaseDetailProgress implements Serializable {

    private Long id;//主键
    private Long phaseDetailId;//阶段详细id
    private Long phaseId;//阶段id
    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private int type;//类型：1课程2考试3直播
    private int status = 0;//状态：0未完成1已完成
    private String name;//名称
    private int total = 0;//总时长
    private int complete = 0;//完成时长
    private Long userId;//用户id

    private String userName;// 用户名
    private Long courseId;// 课程id
}

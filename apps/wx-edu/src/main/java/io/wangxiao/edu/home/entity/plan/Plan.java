package io.wangxiao.edu.home.entity.plan;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class Plan implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long id;//任务主键
    private String name;//任务名称
    private java.util.Date beginTime;//任务开始时间
    private java.util.Date endTime;//任务结束时间
    private java.util.Date releaseTime;//任务发布时间
    private Long status;//0:未启动,1:启动,2:作废
    private Long isRepeat;//是否可重复,0:不可重复,1:可重复
    private Long type;//计划类型0个人1部门
    private Long submit;//1未提交2已提交
    private int peopleNum;// 参加计划人数
    private int completeNum;// 完成计划人数
}

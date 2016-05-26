package io.wangxiao.edu.home.entity.card;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 课程卡查询条件
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryMainCard implements Serializable {

    private java.util.Date beginTime;//开始时间
    private java.util.Date endTime;//结束时间
    private String cardName;    //课程卡名称
    private int type;    //卡类型(1课程卡2充值卡)
    private String createUser;    //创建人名称
    private String courseName;    //课程名称
}

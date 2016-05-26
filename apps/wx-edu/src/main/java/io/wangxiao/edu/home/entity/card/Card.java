package io.wangxiao.edu.home.entity.card;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 卡主表
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Card implements Serializable {
    public static int COURSE_CARD = 1;    //课程卡
    public static int RECHARGE_CARD = 2;    //充值卡
    public static int STUDENT_CARD = 3; //学员卡
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private java.math.BigDecimal money;
    private int type;    //卡类型(1课程卡2充值卡3学员卡)
    private Long num;
    private java.util.Date beginTime;
    private java.util.Date endTime;
    private String remark;
    private String createUser;
    private java.util.Date createTime;
    private String courseName;

    private String emailPrefix;//添加学员卡时 卡号 前缀   
    private String emailSuffix;//添加学员卡时  卡号 后缀
}

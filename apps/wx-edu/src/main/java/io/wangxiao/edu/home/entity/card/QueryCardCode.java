package io.wangxiao.edu.home.entity.card;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 获取线下卡信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryCardCode implements Serializable {


    private Long id;
    private String name;
    private java.math.BigDecimal money;
    private int type;    //卡类型(1课程卡2充值卡)
    private Long num;                    //申城个数
    private String status;                //状态
    private Long cardNode;                //课程编码
    private java.util.Date beginTime;    //开始时间
    private java.util.Date endTime;        //结束时间
    private String createUser;            //创建人
    private java.util.Date createTime;    //创建时间
    private String cardCodePassword;    //充值卡密码
    private Long userId;    //学员id
    private String email;    //邮件
    private String remark;    //备注信息
    private Long cardId;    //课程主卡id


}

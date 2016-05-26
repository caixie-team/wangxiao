package io.wangxiao.edu.home.entity.card;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 卡附表
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CardCode implements Serializable {
    private Long id;
    private Long cardId;
    private String cardCode;
    private String cardCodePassword;
    private String status;
    private String userEmail;
    private Long userId;
    private Long trxorderId;
    private String requestId;
    private java.util.Date createTime;
    private java.util.Date useTime;
    private int type; //课程卡类型  1课程卡  2充值卡
    private String name;
}

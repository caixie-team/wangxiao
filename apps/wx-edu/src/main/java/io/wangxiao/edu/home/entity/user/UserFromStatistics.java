package io.wangxiao.edu.home.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserFromStatistics implements Serializable {

    private Long userNum = 0L;//用户数量
    private Long registerNum = 0L;//注册用户数量
    private Long adminNum = 0L;//后台开通用户数量
    private Long userCardNum = 0L;//课程卡用户数量
    private Long appNum = 0L;//app注册用户数量
    private Long qqNum = 0L;//qq用户数量
    private Long weixinNum = 0L;//微信用户数量
    private Long weiboNum = 0L;//微博用户数量
    private Long mobileNum = 0L;//微站注册用户数量

    private String date;
}

package io.wangxiao.edu.home.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserAccountOptRecord implements Serializable {
    private Long id;//id自增
    private Long userId;//用户id
    private Long optuser;//操作者id
    private String optusername;//操作人名字
    private Long accountId;//账户id
    private java.math.BigDecimal amount;//操作金额
    private String type;//操作类型
    private String outNo;//操作标识
    private String description;//备注
    private java.util.Date createTime;//操作时间
}

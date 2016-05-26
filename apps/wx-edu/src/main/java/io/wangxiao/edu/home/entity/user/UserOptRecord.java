package io.wangxiao.edu.home.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户操作记录
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserOptRecord implements Serializable {

    private Long id;//主键
    private Long userId;//用户id
    private String type;//操作类型
    private Long optuser;//操作者id
    private String optusername;//操作者名字
    private Long bizId;//业务id
    private String description;//描述 Json 格式
    private Date createTime;//创建时间
    private String startDate;//开始时间
    private String endDate;//结束时间
}

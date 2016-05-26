package io.wangxiao.edu.sysuser.entity;

import io.wangxiao.commons.entity.PageEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @description 查询登录辅助类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryLoginLogCondition extends PageEntity implements Serializable {
    private Long userId;            //外键_登录人ID
    private String userName;    //登录人
    private String startDate;//开始时间
    private String endDate;//结束时间

}

package io.wangxiao.edu.sysuser.entity;

import io.wangxiao.commons.entity.PageEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @description 查询系统用户条件类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryUserCondition extends PageEntity implements Serializable {
    private Long userId;//用户id
    private Long groupId;//组id
    private int userType = -1;//用户类型
    private String searchKey;//页面关键字
    private String searchType;//页面类型

    List<SysGroup> groupList = new ArrayList<SysGroup>();
}
package io.wangxiao.edu.sysuser.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description 系统用户所属组
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class SysGroup implements Serializable {
    public static int GROUP_DEFAULT_STATUS = 0;//群组默认状态 0正常 2删除  1网站根不显示
    public static int GROUP_DEFAULT_PARENT_ID = -1;
    public static int GROUP_DELETE_STATUS = 2;//群组删除状态
    private Long groupId;// 组id
    private Long parentGroupId;// 父id
    private String groupName;// 组名称
    private int status;// 状态
    private java.util.Date createTime;// 创建时间
    private java.util.Date updateTime;// 修改时间
    private int level;// 组级别 1.一级组 2.二级组 3.三级组
}

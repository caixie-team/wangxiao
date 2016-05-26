package io.wangxiao.edu.sysuser.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description 功能对象
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysFunction implements Serializable {

    public static int FUNCTION_TYPE_CATALOG = 1;// 功能类型：针对页面内的按钮
    public static int FUNCTION_TYPE_ITEM = 2;// 页面实际显示的菜单
    private Long functionId;// 功能Id
    private Long parentFunctionId;// 父Id
    private String functionName;// 功能名称
    private int functionTypeId;// 功能类型  1.功能类型拦截的url，针对页面内的按钮 2.菜单功能(页面内显示的菜单) 
    private String functionUrl;// 功能对应Url
    private java.util.Date createTime;// 创建时间
    private java.util.Date updateTime;// 最后修改时间
    private int sort;// 显示排序
    private int functionUrlType;//链接地址类型：1，网校 2，社区 3，考试 4 视频
    private String icon;
}

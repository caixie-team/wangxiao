package io.wangxiao.edu.home.entity.letter;

import java.io.Serializable;
import java.util.Date;

import io.wangxiao.edu.home.entity.user.UserExpandDto;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @description 站内信查询类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryMsgReceive implements Serializable {
    private Long id;// 主键Id
    private Long cusId;// 发信人用户id
    private String content;// 信内容
    private Long receivingCusId;// 收信人id
    private Date addTime;// 添加时间
    private Date updateTime;// 更新时间
    private int type;// 类型1系统通知2站内信3评论回复4问答回复
    private int status;// 0未读1已读
    private String cusName;// 用户名
    private Long groupId;// 小组组id申请加入小组组时用到
    private String remarks;// 好友备注
    private String groupName;// 小组名称
    private int systemNum;// 系统消息未读数量
    private int letterNum;// 站内信未读数量
    private int friNum;// 好友请求未读数量
    private int groupNum;// 小组组消息未读数量
    private int unReadNum;// 用户全部未读数量
    private String showname;// 会员名
    private UserExpandDto userExpandDto;// 用户信息
}

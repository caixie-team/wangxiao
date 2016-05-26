package com.atdld.os.sns.entity.friend;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.friend.Friend
 * @description 好友
 * @Create Date : 2013-12-13 下午12:40:59
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Friend implements Serializable {

    private static final long serialVersionUID = -2972088766561758830L;
    private Long id;// 主键Id
    private Long cusId;// 用户id
    private Long cusFriendId;// 用户好友id
    private Date addTime;// 添加时间
    private String remarks;// 备注
    private Long mutual;//0:单向 1:已互相关注

}

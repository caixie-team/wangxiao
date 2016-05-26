package com.atdld.os.sns.entity.friend;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.friend.BlackList
 * @description 黑名单
 * @Create Date : 2013-12-13 下午12:39:50
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BlackList implements Serializable {

    private static final long serialVersionUID = -2972088766561758830L;
    private Long id;// 主键Id
    private Long cusId;// 用户id
    private Long cusBlackListId; // 黑名单id
    private Date addTime;// 添加时间

}

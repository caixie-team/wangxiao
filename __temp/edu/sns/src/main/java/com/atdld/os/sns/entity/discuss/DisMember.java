package com.atdld.os.sns.entity.discuss;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.sns.entity.customer.SnsUserExpandDto;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.discuss.DisMember
 * @description 小组成员
 * @Create Date : 2013-12-11 下午2:05:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DisMember implements Serializable {

    private static final long serialVersionUID = -6914182777012565276L;

    private Long id;// 成员id
    private Long cusId;// 用户ID
    private String showName;// 会员名
    private Long groupId;// 所属小组ID
    private Date addTime;// 加入时间
    private int type;// 申请加入状态1加入小组2拒绝加入
    private int transferId;// 成员身份标示1成员0管理员
    private String disName;// 小组的名称
    private String disImageUrl;// 小组头像
    private SnsUserExpandDto userExpandDto;// 获得用户信息
    private Long cusAttentionId;//用户关注表id （判断该用户是否关注过）

}

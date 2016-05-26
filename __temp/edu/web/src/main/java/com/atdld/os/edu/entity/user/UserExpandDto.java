package com.atdld.os.edu.entity.user;

import java.io.Serializable;
import java.util.Date;

import com.atdld.os.core.util.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.customer.QueryCustomer
 * @description 用户查询
 * @Create Date : 2013-12-13 下午12:35:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserExpandDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -4181611215034299276L;

    private Long id;// 主键 id
    private String nickname = "";// 用户名
    private String email = "";// 邮件
    private int emailIsavalible = 0;// 邮件是否验证
    private String mobile = "";// 手机号
    private int mobileIsavalible = 0;// 手机号是否验证
    private String password;
    private int isavalible = 0;// 是否激活(0正常 1冻结)
    private String customerkey;
    private Date createdate;
    private String userip = "";
    private String realname;// 真实姓名
    private int gender;// 性别：0男 1女
    private String avatar;// 头像地址
    private String bannerUrl;//自定义背景的地址
    private Long studysubject;// 最后一次选择的专业

    private String nowYear;
    private String nowMonth;
    private String nowTime;

    private int weiBoNum;// 微博数
    private int fansNum;// 粉丝数
    private int attentionNum;// 关注数
    private Long cusAttentionId;// 是否关注过这个用户的 关注表id
    private int msgNum;// 站内信未读消息数
    private int sysMsgNum;// 系统自动消息未读消息数
    private Date lastSystemTime;// 上传统计系统消息时间
    private int unreadFansNum;// 未读粉丝数

    private String showname="";// 前端显示名称
    private String userInfo="";// 用户简介
    private Long cusId;// 用户id
    private int commonFriendNum;// 共同好友数

    private int friendId;// 好友id
    private Long cusFriendId;// 好友的用户id
    private String remarks;// 备注
    private int mutual;// 是否互相关注了
    private Long maxId;// 最大id 后台批量添加系统消息用到
    private int cusNum;// 返回的行数
    
    private long current;//登录时的当前时间戳
    
    private String registerFrom;//注册来源 studentCard（学员卡）
    private String updateEmail;//是否可以修改邮箱号 YES可以
    /**
     * 页面显示用户姓名
     * 
     * @return
     */
    public String getShowname() {
        if (StringUtils.isNotEmpty(getNickname())) {
            return getNickname();
        }
        return getEmail();
    }

    public void setExtendInfo(UserExpandDto userExpandDto) {
        this.id = userExpandDto.getId();
        this.nickname = userExpandDto.getNickname();
        this.mobile = userExpandDto.getMobile();
        this.email = userExpandDto.getEmail();
        this.cusId = userExpandDto.getCusId();
        this.realname = userExpandDto.getRealname();
        this.gender = userExpandDto.getGender();
        this.avatar = userExpandDto.getAvatar();
        this.studysubject = userExpandDto.getStudysubject();
        this.weiBoNum = userExpandDto.getWeiBoNum();
        this.fansNum = userExpandDto.getFansNum();
        this.attentionNum = userExpandDto.getAttentionNum();
        this.commonFriendNum = userExpandDto.getCommonFriendNum();
        this.msgNum = userExpandDto.getMsgNum();
        this.sysMsgNum = userExpandDto.getSysMsgNum();
        this.lastSystemTime = userExpandDto.getLastSystemTime();
        this.unreadFansNum = userExpandDto.getUnreadFansNum();
    }

}

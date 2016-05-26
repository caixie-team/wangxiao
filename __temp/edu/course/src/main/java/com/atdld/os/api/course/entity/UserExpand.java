package com.atdld.os.api.course.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserExpand implements Serializable {
	
	public static String ADD = "+";
    public static String SUBTRACTION = "-";	
	    
	/**
	 * 
	 */
	private static final long serialVersionUID = 6702212021177973240L;
	private Long id;
	private String realname;// 真实姓名
	private int gender;// 性别：0男 1女
	private String avatar;// 头像地址
	private String bannerUrl;//自定图片
	private Long studysubject;// 最后一次选择的专业
	private String email;// 邮箱
    private Long cusId;// 用户id
    private String mobile;// 手机号
    private int weiBoNum;// 微博数
    private int fansNum;// 粉丝数
    private int attentionNum;// 关注数
    private Long cusAttentionId;// 是否关注过这个用户的 关注表id
    private String showname;//网页显示,优先显示nickname,然后显示邮箱
    private int msgNum;//站内信未读消息数
    private int sysMsgNum;//系统自动消息未读消息数
    private Date lastSystemTime;//上传统计系统消息时间
    private int unreadFansNum;//未读粉丝数
    private String nickname;//用户名
    private String userInfo="";// 用户简介
    private String registerFrom;//注册来源
    private Long courseid;//课程ID

    public String getShowname() {
//        if (StringUtils.isNotEmpty(nickname)) {
//            return getNickname();
//        }
        return getEmail();
    }
    
    
}

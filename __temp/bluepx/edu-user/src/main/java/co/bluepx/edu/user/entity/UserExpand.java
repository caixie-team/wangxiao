package co.bluepx.edu.user.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

import java.util.Date;

public class UserExpand extends BaseIncrementIdModel {

    public static String ADD = "+";
    public static String SUBTRACTION = "-";

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Long getStudysubject() {
        return studysubject;
    }

    public void setStudysubject(Long studysubject) {
        this.studysubject = studysubject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCusId() {
        return cusId;
    }

    public void setCusId(Long cusId) {
        this.cusId = cusId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getWeiBoNum() {
        return weiBoNum;
    }

    public void setWeiBoNum(int weiBoNum) {
        this.weiBoNum = weiBoNum;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getAttentionNum() {
        return attentionNum;
    }

    public void setAttentionNum(int attentionNum) {
        this.attentionNum = attentionNum;
    }

    public Long getCusAttentionId() {
        return cusAttentionId;
    }

    public void setCusAttentionId(Long cusAttentionId) {
        this.cusAttentionId = cusAttentionId;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public int getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(int msgNum) {
        this.msgNum = msgNum;
    }

    public int getSysMsgNum() {
        return sysMsgNum;
    }

    public void setSysMsgNum(int sysMsgNum) {
        this.sysMsgNum = sysMsgNum;
    }

    public Date getLastSystemTime() {
        return lastSystemTime;
    }

    public void setLastSystemTime(Date lastSystemTime) {
        this.lastSystemTime = lastSystemTime;
    }

    public int getUnreadFansNum() {
        return unreadFansNum;
    }

    public void setUnreadFansNum(int unreadFansNum) {
        this.unreadFansNum = unreadFansNum;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getRegisterFrom() {
        return registerFrom;
    }

    public void setRegisterFrom(String registerFrom) {
        this.registerFrom = registerFrom;
    }

    public Long getCourseid() {
        return courseid;
    }

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }

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
    private String userInfo = "";// 用户简介
    private String registerFrom;//注册来源
    private Long courseid;//课程ID

    public String getShowname() {
//        if (StringUtils.isNotEmpty(nickname)) {
//            return getNickname();
//        }
        return getEmail();
    }


}

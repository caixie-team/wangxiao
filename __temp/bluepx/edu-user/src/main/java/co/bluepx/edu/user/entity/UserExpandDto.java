package co.bluepx.edu.user.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

import java.util.Date;

/**
 * @description 用户查询
 */
public class UserExpandDto extends BaseIncrementIdModel {
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmailIsavalible() {
        return emailIsavalible;
    }

    public void setEmailIsavalible(int emailIsavalible) {
        this.emailIsavalible = emailIsavalible;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getMobileIsavalible() {
        return mobileIsavalible;
    }

    public void setMobileIsavalible(int mobileIsavalible) {
        this.mobileIsavalible = mobileIsavalible;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsavalible() {
        return isavalible;
    }

    public void setIsavalible(int isavalible) {
        this.isavalible = isavalible;
    }

    public String getCustomerkey() {
        return customerkey;
    }

    public void setCustomerkey(String customerkey) {
        this.customerkey = customerkey;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

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

    public String getNowYear() {
        return nowYear;
    }

    public void setNowYear(String nowYear) {
        this.nowYear = nowYear;
    }

    public String getNowMonth() {
        return nowMonth;
    }

    public void setNowMonth(String nowMonth) {
        this.nowMonth = nowMonth;
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
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

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public Long getCusId() {
        return cusId;
    }

    public void setCusId(Long cusId) {
        this.cusId = cusId;
    }

    public int getCommonFriendNum() {
        return commonFriendNum;
    }

    public void setCommonFriendNum(int commonFriendNum) {
        this.commonFriendNum = commonFriendNum;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public Long getCusFriendId() {
        return cusFriendId;
    }

    public void setCusFriendId(Long cusFriendId) {
        this.cusFriendId = cusFriendId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getMutual() {
        return mutual;
    }

    public void setMutual(int mutual) {
        this.mutual = mutual;
    }

    public Long getMaxId() {
        return maxId;
    }

    public void setMaxId(Long maxId) {
        this.maxId = maxId;
    }

    public int getCusNum() {
        return cusNum;
    }

    public void setCusNum(int cusNum) {
        this.cusNum = cusNum;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public String getRegisterFrom() {
        return registerFrom;
    }

    public void setRegisterFrom(String registerFrom) {
        this.registerFrom = registerFrom;
    }

    public String getUpdateEmail() {
        return updateEmail;
    }

    public void setUpdateEmail(String updateEmail) {
        this.updateEmail = updateEmail;
    }

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

    private String showname = "";// 前端显示名称
    private String userInfo = "";// 用户简介
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
//        if (StringUtils.isNotEmpty(getNickname())) {
//            return getNickname();
//        }
        return getEmail();
    }

    public void setExtendInfo(UserExpandDto userExpandDto) {
        this.setId(userExpandDto.getId());
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

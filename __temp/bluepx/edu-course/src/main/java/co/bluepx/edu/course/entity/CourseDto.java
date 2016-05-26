package co.bluepx.edu.course.entity;

import co.bluepx.edu.user.entity.UserExpand;

import java.util.Date;
import java.util.List;


/**
 * @description 课程综合信息
 */
public class CourseDto extends Course {

    public void setBuycount(Long buycount) {
        this.buycount = buycount;
    }

    public void setViewcount(Long viewcount) {
        this.viewcount = viewcount;
    }

    public Long getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(Long commentcount) {
        this.commentcount = commentcount;
    }

    public Long getQuestiongcount() {
        return questiongcount;
    }

    public void setQuestiongcount(Long questiongcount) {
        this.questiongcount = questiongcount;
    }

    public Long getNotecount() {
        return notecount;
    }

    public void setNotecount(Long notecount) {
        this.notecount = notecount;
    }

    public void setPlaycount(Long playcount) {
        this.playcount = playcount;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public int getRemainDays() {
        return remainDays;
    }

    public void setRemainDays(int remainDays) {
        this.remainDays = remainDays;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setBeginTimeNum(Long beginTimeNum) {
        this.beginTimeNum = beginTimeNum;
    }

    public void setEndTimeNum(Long endTimeNum) {
        this.endTimeNum = endTimeNum;
    }

    public Long getKpointNum() {
        return kpointNum;
    }

    public void setKpointNum(Long kpointNum) {
        this.kpointNum = kpointNum;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getStudyhistoryNum() {
        return studyhistoryNum;
    }

    public void setStudyhistoryNum(Long studyhistoryNum) {
        this.studyhistoryNum = studyhistoryNum;
    }


    public Long getBeginTimeNum() {
        if (getLiveBeginTime() != null) {
            Long num = (getLiveBeginTime().getTime() / 1000) - (new Date().getTime() / 1000);
            if (num < 0) {
                return 0l;
            } else {
                return num;
            }
        }
        return 0l;

    }

    public Long getEndTimeNum() {
        if (getLiveEndTime() != null) {
            Long num = (getLiveEndTime().getTime() / 1000) - (new Date().getTime() / 1000);
            if (num < 0) {
                return 0l;
            } else {
                return num;
            }
        }
        return 0l;
    }


    /**
     * @return the buycount
     */
    public Long getBuycount() {
        return getPageBuycount() + buycount;
    }

    /**
     * @return the viewcount
     */
    public Long getViewcount() {
        return getPageViewcount() + viewcount;
    }

    public Long getPlaycount() {
        return getPageViewcount() + playcount;
    }

    public List<UserExpand> getUserExpandList() {
        return userExpandList;
    }

    public void setUserExpandList(List<UserExpand> userExpandList) {
        this.userExpandList = userExpandList;
    }

    private Long buycount=0L;// 购买数量
    private Long viewcount=0L;// 浏览数量
    private Long commentcount=0L;//评论次数
    private Long questiongcount=0L;//问题数
    private Long notecount=0L;//笔记数量
    private Long playcount;//播放次数
//    private List<Teacher> teacherList;//该课程 下的老师list
    //private  Course course;
    private Date authTime;//课程过期时间
    private int remainDays;//课程有效期的剩余天数

    private String subjectName;
    private Long beginTimeNum;
    private Long endTimeNum;
    private Long kpointNum;//课程节点
    private String avatar;//头像
    private String nickname;// 用户名


    private List<UserExpand> userExpandList;//用户列表

    private Long studyhistoryNum;//学习过的数量
}

package io.wangxiao.edu.home.entity.course;

import io.wangxiao.edu.home.entity.user.UserExpand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description 课程综合信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseDto extends Course implements Serializable {
    private Long buycount = 0L;// 购买数量
    private Long viewcount = 0L;// 浏览数量
    private Long commentcount = 0L;//评论次数
    private Long questiongcount;//问题数
    private Long notecount;//笔记数量
    private Long playcount = 0L;//播放次数
    private Long watchpersoncount = 0L;//观看人数
    private List<Teacher> teacherList;//该课程 下的老师list
    //private  Course course;
    private java.util.Date authTime;//课程过期时间
    private int remainDays;//课程有效期的剩余天数

    private String subjectName;
    private Long beginTimeNum;
    private Long endTimeNum;
    private Long kpointNum;//课程节点
    private String avatar;//头像
    private String nickname;// 用户名
    private Map<String, String> begin;//距离开始天数时分
    private int endMin;//距离结束分钟

    private List<UserExpand> userExpandList;//用户列表

    private Long studyhistoryNum = 0L;//学习过的数量

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

    public boolean getIsFree() {
        if (getIsPay() == 0 || getCurrentprice().compareTo(new BigDecimal(0)) <= 0) {
            return true;
        }
        return false;
    }
}

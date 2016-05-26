package com.atdld.os.api.course.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Date;
import java.util.List;

import com.atdld.os.edu.entity.user.UserExpand;

/**
 * @ClassName com.atdld.os.edu.entity.course.CourseDto
 * @description 课程综合信息
 * @author :
 * @Create Date : 2014-9-12 下午4:49:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseDto extends Course implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 9148002883869292944L;
    private Long buycount;// 购买数量
    private Long viewcount;// 浏览数量
    private Long commentcount;//评论次数
    private Long questiongcount;//问题数
    private Long notecount;//笔记数量
    private Long  playcount;//播放次数
    private List<Teacher> teacherList;//该课程 下的老师list
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
    public Long getBeginTimeNum() {
    	if(getLiveBeginTime()!=null){
    		Long num = (getLiveBeginTime().getTime()/1000)-(new Date().getTime()/1000);
            if(num<0){
                return 0l;
            }else{
                return num;
            }
    	}
    	return 0l;

    }
    public Long getEndTimeNum() {
    	if(getLiveEndTime()!=null){
	        Long num = (getLiveEndTime().getTime()/1000)-(new Date().getTime()/1000);
	        if(num<0){
	            return 0l;
	        }else{
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

    public Long getPlaycount(){return getPageViewcount() + playcount;};
}

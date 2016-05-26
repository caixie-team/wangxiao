package io.wangxiao.edu.home.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 记录播放记录
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseStudyhistory implements Serializable {
    private Long id;
    private Long userId;//播放次数
    private Long courseId;//播放次数
    private Long kpointId;//播放次数
    private Long playercount;//播放次数
    private String courseName;//课程名称
    private String kpointName;//节点名称
    private String parentKpointName;// 父节点名称
    private String databak;//playercount小于20时记录,备注观看的时间，叠加
    private java.util.Date updateTime;//更新时间
    private String logo;    //图片
    private String teacherName;    //教师名称

    private Long playTime = 0L;// 时长

    private Long statistics = 0L;//是否是统计0非1是


    private Date startTime;
    private Date endTime;
}

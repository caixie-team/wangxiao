package io.wangxiao.course.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.Table;

/*
* 
*/
@Data
@Table(name = "edu_course_profile")
@EqualsAndHashCode(callSuper = false)
public class CourseProfile {
    private Long id;
    //购买数量
    private Long buycount = 0L;
    //评论数量
    private Long commentcount = 0L;
    //课程id
    private Long courseId = 0L;
    //笔记数量
    private Long notecount = 0L;
    //播放次数
    private Long playcount = 0L;
    //问题数量
    private Long questiongcount = 0L;
    //查看数量
    private Long viewcount = 0L;
    //观看人数
    private Long watchpersoncount = 0L;

    public static String ADD = "+";
    public static String SUBTRACTION = "-";
    private String name;//课程名称

}

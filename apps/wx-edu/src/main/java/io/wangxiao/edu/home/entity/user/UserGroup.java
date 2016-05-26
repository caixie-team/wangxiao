package io.wangxiao.edu.home.entity.user;

import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.task.Task;
import io.wangxiao.edu.home.entity.task.TaskMiddleGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserGroup implements Serializable {
    private Long id;    //主键
    private String name;    //小组名称
    private String description;    //描述
    private int sort;    //排序
    private int status;    //状态
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private java.util.Date createDate;
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private java.util.Date updateDate;

    private Long userId;//学员id 

    private int check;//选中

    private List<Task> task; //task实体
    private List<TaskMiddleGroup> taskmiddlegroup;
    private List<UserGroupMiddle> usergroupmiddle;

    private List<CourseDto> courseDtoArrayList;
    private String ids;//小组id集合

    private int courseNum;// 部门学习课程数
    private int studyTime;// 部门学习时长(分钟)
}

package io.wangxiao.edu.home.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description 课程评论
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseAssess implements Serializable {
    private Long id;// 主键自增
    private Long userId;// 用户id
    private Long courseId;// 课程id
    private Long kpointId;// 节点id
    private String content;// 内容
    private int status;//0显示 1隐藏
    private java.util.Date createTime;// 创建时间

    private String courseIds; // 套餐课程下所有课程ID集合字符串
}

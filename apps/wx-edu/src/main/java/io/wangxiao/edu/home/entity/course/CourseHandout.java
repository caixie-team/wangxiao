package io.wangxiao.edu.home.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseHandout implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 课程id
     */
    private Long courseId;
    /**
     * 名称
     */
    private String name;
    /**
     * 路径
     */
    private String path;

    /**
     * 创建时间
     */
    private Date createTime;

    private String startTime;
    private String endTime;
}

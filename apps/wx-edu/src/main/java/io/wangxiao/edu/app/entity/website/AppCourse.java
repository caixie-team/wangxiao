package io.wangxiao.edu.app.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * app课程管理
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppCourse implements Serializable {
    private long mainId;//app课程id
    private long courseId;// 课程id
}

package io.wangxiao.edu.home.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseFavorites implements Serializable {
    private Long id;
    private Long courseId;
    private Long userId;
    private java.util.Date addTime;
}

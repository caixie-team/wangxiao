package com.atdld.os.api.course.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseTag implements Serializable{
    private Long id;
    private Long couartId;
    private int type;
    private String name;
}

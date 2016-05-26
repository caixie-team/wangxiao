package io.wangxiao.edu.home.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseMember implements Serializable {

    private Long id;
    private Long courseId;//课程Id
    private Long memberTypeId;//会员类型Id

}

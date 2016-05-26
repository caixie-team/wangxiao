package io.wangxiao.edu.home.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class GroupMiddleCourse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long id;    //主键id
    private Long groupId;    //学员组id
    private Long courseId;    //课程id
    private int check = 0;
}

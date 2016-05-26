package io.wangxiao.edu.home.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserGroupMiddle implements Serializable {

    private Long id;    //主键
    private Long userId;    //学员id
    private Long groupId;    //组id
}

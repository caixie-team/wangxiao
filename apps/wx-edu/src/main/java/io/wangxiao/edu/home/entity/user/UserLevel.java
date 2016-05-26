package io.wangxiao.edu.home.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserLevel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;//主键自增
    private Long level;//等级
    private String title;//头衔
    private Long exp;//经验值

    List<UserLevel> userLevel = new ArrayList<UserLevel>();
}

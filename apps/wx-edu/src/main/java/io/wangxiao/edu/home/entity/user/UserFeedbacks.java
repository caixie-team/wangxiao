package io.wangxiao.edu.home.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserFeedbacks {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String content;
    private String qq;
    private String mobile;
    private String email;
    private String name;
    private String startDate;
    private String endDate;
}

package io.wangxiao.edu.home.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginOnline implements Serializable {
    /**
     *
     */
    private Long id;
    private Long userId;
    private String loginsid;
    private String type;
    private java.util.Date createTime;
}

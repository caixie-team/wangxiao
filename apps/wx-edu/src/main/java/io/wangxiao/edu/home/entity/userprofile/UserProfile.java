package io.wangxiao.edu.home.entity.userprofile;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserProfile implements Serializable {
    private Long id;
    private String name;
    private String value;
    private String profiletype;
    private Long userid;
    private java.util.Date profiledate;
}

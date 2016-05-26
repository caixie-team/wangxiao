package io.wangxiao.edu.home.entity.customer;

import io.wangxiao.edu.home.entity.user.UserExpandDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description 查询自定义课程条件
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryCustomerCourse implements Serializable {


    private Long id;
    private String title;
    private String content;
    private String teacherName;
    private String mobile;
    private String email;
    private Long status;
    private java.util.Date createTime;
    private Long createuserId;
    private int joinNum;
    private String remark;
    private UserExpandDto userExpandDto;

}

package io.wangxiao.edu.home.entity.course;

import io.wangxiao.commons.util.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseReserveRecord implements Serializable {

    private Long id;
    private Long userId;//用户id
    private Long courseId;//课程id
    private String courseName;//课程名
    private java.math.BigDecimal coursePrice;//课程价格
    private Date createTime;//创建时间
    private Integer check;//审查是否通过0未通过1通过

    private String nickname;//昵称
    private String mobile;// 手机号
    private String email;//邮箱

    public String getShowName() {
        return StringUtils.isNotEmpty(nickname) ? nickname : StringUtils.isNotEmpty(mobile) ? mobile : email;
    }

}

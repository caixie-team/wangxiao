package io.wangxiao.edu.home.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserIntegralTemplate implements Serializable {

    private Long id;// 主键自增
    private String name;// 模板名称
    private Long type;// 0学员
    private String keyword;// 使用场景
    private java.math.BigDecimal score;// 功能分数
    private Long status;// 0正常1停用
    private java.util.Date createTime;// 创建时间
    private java.util.Date updateTime;// 更新时间
    private String createUser;// 创建人
    private BigDecimal showScore;

    public BigDecimal getShowScore() {// 显示整数
        if (score != null) {
            showScore = score;//转化为int
        } else {
            showScore = new BigDecimal(0);
        }
        return showScore;
    }
}


package io.wangxiao.edu.home.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryUserAccounthistory implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long userId;// 用户id
    private String actHistoryType;//交易类型
    private String bizType;//业务类型
    private java.util.Date startTime;    //开始时间
    private java.util.Date endTime;        //结束时间
}

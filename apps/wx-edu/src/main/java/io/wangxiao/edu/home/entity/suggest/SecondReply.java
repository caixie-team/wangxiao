package io.wangxiao.edu.home.entity.suggest;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class SecondReply implements Serializable {

    private Long id;//主键
    private Long replyId;//回复id
    private String content;// 内容
    private Date createTime;// 创建时间
    private Long userId;// 回复者id

    private String nickname;//用户名
    private String email;//邮箱
    private String mobile;//密码

    private String avatar;//头像

}

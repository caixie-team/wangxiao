package io.wangxiao.edu.home.entity.answer;

import io.wangxiao.edu.home.entity.user.UserExpandDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生答疑回复表
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnswerReply implements Serializable {

    private Long id;
    private String content;//回复内容
    private Long answerId;//问题Id
    private Long userId;//回复者id
    private String showName;//回复者显示姓名
    private Date addTime;//回复时间
    private int status;//状态 0正常1隐藏
    private UserExpandDto userExpandDto;
}

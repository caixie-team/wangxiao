package io.wangxiao.edu.home.entity.letter;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description 站内信
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MsgSystem implements Serializable {
    private Long id;// 主键Id
    private String content;// 信内容
    private Date addTime;// 添加时间
    private Date updateTime;// 更新时间
    private int status;//
    private String addTimeStr;

}

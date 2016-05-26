package com.atdld.os.edu.entity.letter;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.weibo.Letter
 * @description 站内信
 * @Create Date : 2013-12-13 下午12:43:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MsgSystem implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2972088766561758830L;
    private Long id;// 主键Id
    private String content;// 信内容
    private Date addTime;// 添加时间
    private Date updateTime;// 更新时间
    private int status;//
    private String addTimeStr;

}

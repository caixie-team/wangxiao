package com.atdld.os.sns.entity.customer;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.util.StringUtils;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.customer.Customer
 * @description 用户实体
 * @Create Date : 2013-12-13 上午10:53:49
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class Visitor implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -4181611215034299276L;

    private Long id;// 主键
    private Long cusId;// 被访问者用户id
    private Date addTime;// 添加时间
    private String showname;// 会员名
    private Long visitorCusId;// 访问者用户id
    private String viewDay;// 每天记一次存年月日
    private String avatar;// 头像
    private SnsUserExpandDto userExpandDto;
    private Long friendId;
    private String modelStr;//字符串时间

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
        //整理后的时间
        modelStr = StringUtils.getModelDate(addTime);
    }
}

package com.atdld.os.sns.entity.dynamic;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.core.util.StringUtils;

/**
 * @author :
 * @ClassName com.atdld.os.sns.entity.dynamic.DynamicWeb
 * @description 动态
 * @Create Date : 2014-1-11 下午2:19:42
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DynamicWeb implements Serializable {

    private static final long serialVersionUID = -6056892018843994772L;

    private Long id;// 动态id
    private Long cusId;// 用户id
    private int type;
    private Long bizId;// 动态信息的id
    private Date addTime;// 动态时间
    private String cusName;// 用户名称
    private String description;// 动态描述
    private String content;// 发表回复内容
    private String title;// 辅助标题
    private Long assistId;// 辅助业务（事件）ID
    private String avatar;// 头像
    private String account;// 账户信息
    private Long cusAttentionId;//用户关注表id （判断该用户是否关注过）
    private String url;//学习动态url
    private String modelStr;//字符串时间
    @Setter
    private String modelUrl;

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
        //整理后的时间
        modelStr = StringUtils.getModelDate(addTime);
    }

    public String getModelUrl() {

        if (type == 3 || type == 6) {
            url = CommonConstants.contextPath+"/dis/artinfor/" + bizId + "/" + assistId;
        } else if (type == 8) {
            url = CommonConstants.contextPath+"/sug/info/" + bizId;
        } else if (type == 0 || type == 4) {
            url = CommonConstants.contextPath+"/dis/info/" + assistId;
        } else if (type == 9 || type == 10) {
            url = CommonConstants.contextPath+"/blog/info/" + bizId;
        } else if (type == 11 || type == 12) {
        } else if (type == 13) {
            // url=url;
        }

        return url;
    }


}

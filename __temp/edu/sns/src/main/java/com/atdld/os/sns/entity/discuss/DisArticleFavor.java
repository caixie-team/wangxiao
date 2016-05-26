package com.atdld.os.sns.entity.discuss;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :
 * @ClassName com.atdld.os.sns.entity.discuss.DisArticleFavor
 * @description 推荐 喜欢话题
 * @Create Date : 2014年5月14日 下午3:38:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DisArticleFavor {
    private int id;
    private Long cusId;//用户id
    private Long articleId;//话题id
    private int type;//类型 0喜欢 1 推荐
    private String content;//分享内容
    private Date addTime;//添加时间
}

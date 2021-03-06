package io.wangxiao.edu.app.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * app课程管理
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppTopic implements Serializable {
    private long topicId;//app话题id
    private String topicTitle;//话题标题
    private long userId;//话题发布人id
    private String topicContent;//话题内容
    private Date createTime;// 发布时间
    private String states;//状态 DEFAULT默认 HIDDEN隐藏
    private String groupId;//群组id
    private String imageUrl;//图片链接
}

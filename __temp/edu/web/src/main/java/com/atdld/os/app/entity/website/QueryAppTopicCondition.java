package com.atdld.os.app.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * app课程管理
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryAppTopicCondition implements Serializable {
	private static final long serialVersionUID = 1383373953853661904L;
	private long topicId;//app话题id
	private String topicTitle;//话题标题
	private long userId;//话题发布人id
	private String userName;//发布人名称
	private String topicContent;//话题内容
	private Date createTime;// 发布时间
	private String states;//状态 DEFAULT默认 HIDDEN隐藏
	private String groupId;//群组id
}

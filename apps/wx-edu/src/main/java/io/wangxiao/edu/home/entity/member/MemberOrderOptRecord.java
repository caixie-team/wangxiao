package io.wangxiao.edu.home.entity.member;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 操作记录
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberOrderOptRecord implements Serializable {

    private Long id;//会员操作
    private Long userId;//用户id
    private Long optuser;//操作者id
    private String optusername;//操作者名称
    private Long memberRecordId;//开通记录id
    private String type;//操作类型
    private String description;//描述
    private Date createTime;//创建时间
}

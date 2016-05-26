package com.atdld.os.edu.entity.member;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberRecordDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	/**
	 * 用户id
	 */
    private Long userId;
    /**
	 * 用户邮箱
	 */
    private String email;
    /**
     * 会员类型
     */
    private Long memberType;
    /**
     * 会员类型名称
     */
    private String memberTitle;
    /**
     * 首次开通时间
     */
    private java.util.Date beginDate;
    /**
     * 会员到期时间
     */
    private java.util.Date endDate;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态 0正常 1关闭
     */
    private Long status;
}
package com.atdld.os.member.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberRecord implements Serializable{
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
     * 会员类型
     */
    private Long memberType;
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
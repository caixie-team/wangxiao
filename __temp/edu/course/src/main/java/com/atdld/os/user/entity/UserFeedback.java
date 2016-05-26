package com.atdld.os.user.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserFeedback implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long userId;
    private String content;
    private String qq;
    private String mobile;
    private String email;
    private String name;
    private java.util.Date createTime;
}

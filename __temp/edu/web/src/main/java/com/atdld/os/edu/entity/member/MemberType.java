package com.atdld.os.edu.entity.member;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 会员服务
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 服务名称
     */
    private String title;
    /**
     * 状态0正常1删除
     */
    private int status;
    
}


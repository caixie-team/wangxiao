package io.wangxiao.edu.home.entity.member;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 会员服务
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberType implements Serializable {
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


package io.wangxiao.edu.sysuser.entity;

import io.wangxiao.commons.entity.PageEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @description 查询Group条件辅助类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryGroupCondition extends PageEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long groupId;//组id
    private Long thirdGroupId;//组子id
}
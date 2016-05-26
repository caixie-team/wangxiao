package io.wangxiao.edu.sysuser.entity;

import io.wangxiao.commons.entity.PageEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @description 查询功能对象所使用的条件类
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryFunctionCondition extends PageEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -7055484731686817657L;
    private Long parentFunctionId;// 父功能id
    private int functionTypeId;// 功能类型id
    private int functionApplyScopeId;// 功能适用范围id
    private Integer status;// 功能状态id

}
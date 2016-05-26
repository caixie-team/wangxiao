package com.atdld.os.exam.entity.customer;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerQueryCondition extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -4181611215034299276L;
    private String email;
    private Long cusId;
    private Date regTime;

}

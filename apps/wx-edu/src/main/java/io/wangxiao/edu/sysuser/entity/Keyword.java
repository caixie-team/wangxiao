package io.wangxiao.edu.sysuser.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Keyword implements Serializable {
    private Long id;
    private String keyword;
}

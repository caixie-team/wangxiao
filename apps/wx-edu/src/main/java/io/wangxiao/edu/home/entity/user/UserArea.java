package io.wangxiao.edu.home.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserArea implements Serializable {
    private Long id;
    private Long parentId = -1l;
    private String areaName;
    private int areaType;
}

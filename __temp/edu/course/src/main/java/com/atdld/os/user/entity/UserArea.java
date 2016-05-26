package com.atdld.os.user.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserArea implements Serializable{
    private Long id;
    private Long parentId=-1l;
    private String areaName;
    private int areaType;
}

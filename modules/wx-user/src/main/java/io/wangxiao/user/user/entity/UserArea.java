package io.wangxiao.user.user.entity;

import io.wangxiao.core.model.BaseIncrementIdModel;

public class UserArea extends BaseIncrementIdModel {

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getAreaType() {
        return areaType;
    }

    public void setAreaType(int areaType) {
        this.areaType = areaType;
    }

    private Long parentId=-1l;
    private String areaName;
    private int areaType;
}

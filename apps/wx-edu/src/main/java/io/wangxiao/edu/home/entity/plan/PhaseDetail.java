package io.wangxiao.edu.home.entity.plan;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class PhaseDetail implements Serializable {

    private Long id;//主键
    private Long phaseId;//阶段id
    private Long otherId;//试卷或者知识id
    private Long type;//1试卷，2知识
    private String detailName;//名称
    private Long hours;//学时
    private Long detailRank;//排序
}

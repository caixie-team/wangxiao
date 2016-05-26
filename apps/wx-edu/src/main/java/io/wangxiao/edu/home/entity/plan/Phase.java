package io.wangxiao.edu.home.entity.plan;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class Phase implements Serializable {

    private Long id;//主键
    private Long planId;//任务id
    private String phaseName;//阶段名称
    private String phaseDescribe;//阶段描述
    private Long phaseRank;//阶段排序
    private Long status;//0正常1移除

    private Long knowledgeNo;//知识数
    private Long studyTimeNo;//学时数
    private Long examNo;//试卷数
}

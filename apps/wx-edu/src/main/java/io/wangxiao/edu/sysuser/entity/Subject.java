package io.wangxiao.edu.sysuser.entity;

import io.wangxiao.commons.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class Subject extends BaseEntity {
    private Long subjectId; // 专业id
    private String subjectName;// 专业名称
    private int status;// 状态
    private Date createTime;// 创建时间
    private Date updateTime;// 修改时间
    private Long parentId;// 父节点
    private int level;// 等级 1，2，3
    private int sort;

    // 二级level的List
    private List<Subject> childSubjectList = new ArrayList<Subject>();

    @SuppressWarnings("unused")
    private String subjectNameAndId;// 树中显示用

    private Long showIndex = 0L;// 首页显示 0不显示1显示

}

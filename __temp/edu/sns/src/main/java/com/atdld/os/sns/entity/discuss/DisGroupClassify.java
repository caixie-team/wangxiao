package com.atdld.os.sns.entity.discuss;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :
 * @ClassName com.atdld.os.core.entity.discuss.DisGroupClassify
 * @description 小组分类
 * @Create Date : 2013-12-11 下午2:05:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DisGroupClassify implements Serializable {

    private static final long serialVersionUID = -8567196654739792298L;

    private Long id;// 小组分类id
    private String name;// 小组分类
    private int status;// 0没有删除1删除
    private int sort;// 设置排序
    private int groupNum;// 统计该分类下的小组
    private String code;// 分类代码
    private List<DisGroup> disGroupList;// 首页小组
}

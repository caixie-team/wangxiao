package com.atdld.os.sns.entity.lucene;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :
 * @ClassName com.atdld.os.sns.entity.lucene.SearchWord
 * @description 搜索词
 * @Create Date : 2014-2-20 上午10:43:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchWord implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7073055911892578594L;
    private Long id;
    private String type;// 搜索的类型
    private String word;// 搜索词
    private Long count;// 搜索次数
    private java.util.Date updatetime;
    private int top;
}

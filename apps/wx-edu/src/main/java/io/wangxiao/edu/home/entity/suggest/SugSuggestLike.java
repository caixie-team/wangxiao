package io.wangxiao.edu.home.entity.suggest;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SugSuggestLike implements Serializable {

    private Long id;// 主键
    private Long suggestId;//问答id
    private Long userId;//用户id
}

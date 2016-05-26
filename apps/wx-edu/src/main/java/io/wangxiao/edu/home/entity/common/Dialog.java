package io.wangxiao.edu.home.entity.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description 弹出窗类
 */
@Data
@EqualsAndHashCode
public class Dialog implements Serializable {


    private String title;//窗口标题
    private String conent;//窗口内容
    private Long index;//索引
    private String url;//确认窗口的确定按钮路径
}

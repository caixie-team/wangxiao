package io.wangxiao.edu.home.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 网站配置实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebsiteProfile implements Serializable {
    private Long id;
    private String type;//类型
    private String desciption;//描述内容JSON格式
    private String explain;//说明
}

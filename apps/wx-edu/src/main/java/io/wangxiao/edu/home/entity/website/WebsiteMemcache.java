package io.wangxiao.edu.home.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description memcache管理
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebsiteMemcache implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4611745081384979974L;

    private Long id;//主键自增
    private String memKey;//memcache key
    private String memDesc;//memcache 描述
}

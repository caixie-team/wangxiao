package io.wangxiao.edu.home.entity.weixin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 多图文素材类
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinManyImageDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * ID
     **/
    private Long id;
    /**
     * 多图文ID
     **/
    private Long manyImageId;
    /**
     * 单图文ID
     **/
    private Long imageId;
    /**
     * 单图文标题
     **/
    private String title;

}

package io.wangxiao.edu.app.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * APP 验证管理
 *
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class AppIosCheck implements Serializable {

    private static final long serialVersionUID = 1565236565623652L;
    private Long id; // 主键
    private String productId; // 产品Id
    private String purchaseDate; // 交易日期
    private String rReceipt; // 明文密码
    private String md5Receipt; // 密文

}

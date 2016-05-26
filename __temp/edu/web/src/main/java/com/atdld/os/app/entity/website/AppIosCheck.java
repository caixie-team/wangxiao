package com.atdld.os.app.entity.website;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * APP 验证管理
 * 
 * @author WangKaiping
 * 
 * @date 2015-06-15 15:06:15
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

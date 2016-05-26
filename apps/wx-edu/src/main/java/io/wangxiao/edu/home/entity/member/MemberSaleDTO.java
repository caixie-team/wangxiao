package io.wangxiao.edu.home.entity.member;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 会员商品
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberSaleDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id;
    
    /**
     * 商品名称
     */
    private String name;
    /**
     * 价格
     */
    private java.math.BigDecimal price;
    /**
     * 描述
     */
    private String description;
    /**
     * 权重排序
     */
    private int sort;
    /**
     * 会员类型
     */
    private Long type;
    /**
     * 会员类型名称
     */
    private String title;
    /**
     * 按年时成功支付后开通的天数
     */
    private int days;
}


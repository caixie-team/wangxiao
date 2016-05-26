package io.wangxiao.core.model;



/**
 * 作何类型的用户都可以创建本实体（比如订单 Order,  前台用户和后台用户都可以创建本实体）
 * 实现本接口在创建后不用设置本属性，会由 BaseModelBeforeSaveAdvice 自动填写。
 * 目前由学员创建的，值为0，由系统管理用户创建的，值为1. 值参见  com.edu.dictionary.constant.UserDetailType的定义;
 *
 */
public interface AnyUserTypeCreationEnable {
	/**
	 * 得到创建的用户类型
	 * @return
	 */
	Integer getCreateType();
	/**
	 * 设置创建的用户类型
	 * @param createType
	 */
	void setCreateType(Integer createType);
}

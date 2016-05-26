package io.wangxiao.core;
/**
 * 字典枚举类型基类
 * <p>
 * 	定义了枚举类型基本接口
 * </p>
 * 
 *
 */
public interface BaseEnum {
	/**
	 * 值，一般对应字典英文名称
	 * @return 枚举值
	 */
	String name();
	/**
	 * 名称，字典中文名称
	 * @return 枚举值中文名称
	 */
	String getChineseName();
	/**
	 * 序号
	 * @return 序号
	 */
	int ordinal();
}

package io.wangxiao.core;

import java.io.Serializable;

/**
 * 通用查询条件的封装。可以调用parseCondition()方法生成一个条件，目前支持单个条件和多个条件两种格式
 * 单个条件传 属性名_类型_比较符 , 多个条件传 属性1-属性2-属性*_类型_比较符。多个条件解析后各条件之间是 or 的关系 [如同时按邮箱和姓名查询]
 * @see SingleFieldCondition
 * @see MultiFieldCondition
 *
 */
public abstract class Condition implements Serializable{
	
	public static final String FIELDS_DELIMETER ="-"; //多个字段时，字段之间的分隔符
	public static final String FIELD_INTERNAL_DELIMETER ="\\." ; //字段和操作符等中间的连接符。
	protected abstract void parse(String parameter);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6323255822270908073L;
	/**
	 * 
	 * 设置查询的字段名及查询类型
	 */
	public static Condition parseCondition(String parameter){
		Condition condition ;
		int fieldIndex = parameter.indexOf(FIELDS_DELIMETER);  //是否为多个字段
		if(fieldIndex == -1){
			condition = new SingleFieldCondition();
		}else{
			condition = new MultiFieldCondition();
		}
		condition.parse(parameter);
		return condition;
		
	}
	

	
	public abstract String getOperator() ;
	public abstract void setOperator(String operator) ;
	public abstract String getField() ;
	public abstract void setField(String field) ;
	public abstract Object getValue() ;
	public abstract String getType() ;
	public abstract void setType(String type) ;
	public abstract Object getNewValue() ;
//	public abstract void setValue(Object value) ;
	public abstract Condition setValue(Object value) ;

	
	
}

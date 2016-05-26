package io.wangxiao.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @see Condition#parseCondition(String)
 *
 */
public class MultiFieldCondition extends Condition implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 3576987175244428406L;

	private List<SingleFieldCondition> singleConditions = null;

	public List<SingleFieldCondition> getSingleConditions() {
		return singleConditions;
	}

	@Override
	public String getOperator() {
		return singleConditions.get(0).getOperator();
	}
	@Override
	public void setOperator(String operator) {
		for (SingleFieldCondition condition : singleConditions){
			condition.setOperator(operator);
		}

	}
	@Override
	public String getField() {
		return singleConditions.get(0).getField();
	}
	@Override
	public void setField(String field) {
		for (SingleFieldCondition condition : singleConditions){
			condition.setField(field);
		}

	}
	@Override
	public Object getValue() {
		return singleConditions.get(0).getValue();
	}
	@Override
	public String getType() {
		return singleConditions.get(0).getType();
	}
	@Override
	public void setType(String type) {
		for (SingleFieldCondition condition : singleConditions){
			condition.setType(type);
		}

	}
	@Override
	public Object getNewValue() {
		return singleConditions.get(0).getNewValue();
	}
	@Override
	public Condition setValue(Object value) {
		for (SingleFieldCondition condition : singleConditions){
			condition.setValue(value);
		}

		return this;
	}
//	public Condition setValue(Object value) {
//		for (SingleFieldCondition condition : singleConditions){
//			condition.setValue(value);
//		}
//
//		return this;
//	}

	/**
	 * 如果输入一个值，查询两个或者多个字段，用这个方法来分解。字段之间用-分隔，其它规则和Condition一样。
	 * @see Condition
	 * @param parameter 如 "name-title_String_eq"
	 * @return
	 */
	@Override
	protected void parse(String parameter) {
		singleConditions = new ArrayList<SingleFieldCondition>();
		int fieldIndex = parameter.indexOf(FIELDS_DELIMETER);  //是否为多个字段
		if(fieldIndex == -1) {
			throw new RuntimeException("parameter 如 'name-title_String_eq'");
		}
		String[] fields ;
		String suffix ="";
		int splitIndex = parameter.indexOf(FIELD_INTERNAL_DELIMETER); //是否有类型或者比较符
		if(splitIndex == -1){
			fields = parameter.split(FIELDS_DELIMETER);
		}else{
			fields = parameter.substring(0, splitIndex).split(FIELDS_DELIMETER);
			suffix = parameter.substring(splitIndex);
		}

		for(String field : fields){
			SingleFieldCondition singleCondition = new SingleFieldCondition();
			singleCondition.parse(field+suffix);
			singleConditions.add(singleCondition);
		}

	}

	public static void main(String[] args) {
		Condition c = parseCondition("sex_name");
		c.setValue("1111");

		System.out.println(c.toString());
		
	}
}

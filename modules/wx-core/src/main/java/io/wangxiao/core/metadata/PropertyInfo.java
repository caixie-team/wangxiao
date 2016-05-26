package io.wangxiao.core.metadata;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 实体的一个属性的元信息
 *
 */
public class PropertyInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3858591765739593679L;
	
	private Method readMethod;
	private String chineseName;
	private Class<?> parentModel;
	private String propertyName;
	private String dictionaryType;
	private String pattern ;
	private boolean required = false;
	private int maxLength =0;
	private Class<?> returnType;
	private boolean logged = true;

//	public boolean isColumn() {
//		return isColumn;
//	}
//
//	public void setColumn(boolean column) {
//		isColumn = column;
//	}

//	private boolean isColumn = true;
	/**
	 * 当前属性和数据字典中哪个类别对应
	 * @return
	 */
	public String getDictionaryType() {
		return dictionaryType;
	}
	public void setDictionaryType(String dictionaryType) {
		this.dictionaryType = dictionaryType;
	}
	/**
	 * 当前属性的属性名
	 * @return
	 */
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	/**
	 * 当前属性的返回值类型
	 * @return
	 */
	public Class<?> getReturnType() {
		return returnType;
	}
	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}
	
	/**
	 * 当前属性对应的get方法
	 * @return
	 */
	public Method getReadMethod() {
		return readMethod;
	}
	public void setReadMethod(Method readMethod) {
		this.readMethod = readMethod;
	}
	
	/**
	 * 当前属性的中文名
	 * @return
	 */
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	/**
	 * 属性如果保存的是主实体的主键，则返回对应的主实体的实体类型，否则为null
	 * 如果本属性不为null ,则所属的 {@link co.bluepx.core.modules.core.metadata.BeanInfo#getParentModelProperty()}会返回本对象
	 * @return
	 */
	public Class<?> getParentModel() {
		return parentModel;
	}
	public void setParentModel(Class<?> parentModel) {
		//if BaseIncrementIdModel
		this.parentModel = parentModel;
	}
	/**
	 * 检验的正则表达式，暂没用到
	 * @return
	 */
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	/**
	 * 是否为必填，暂没用到
	 * @return
	 */
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	/**
	 * 最大长度，暂没用到。
	 * @return
	 */
	public int getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	/**
	 * 该字段是否记录日志
	 * @return
	 */
	public boolean isLogged() {
		return logged;
	}
	public void setLogged(boolean logged) {
		this.logged = logged;
	}	
	
	
}

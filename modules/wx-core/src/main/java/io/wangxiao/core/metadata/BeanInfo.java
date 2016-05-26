package io.wangxiao.core.metadata;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 一个实体的元数据信息
 *
 */
public class BeanInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6665037435903059802L;
	
	private boolean logged = true;
	private String chineseName;
	private boolean cached = false;
	private String orderBy ;
	//不被记录log的字段
	private String[] notLoggedFields;
	//目前只支持一个类只有一个父类的操作。
	private PropertyInfo parentModelProperty;

	private List<PropertyInfo> properties;
	protected Map<String,PropertyInfo> propertiesMap;

	/**
	 * 得到当前实体的和数据库中对应的所有属性的Map集合
	 * @return
	 */
	protected Map<String, PropertyInfo> getPropertiesMap() {
		return propertiesMap;
	}
	/**
	 * 得到当前实体属于哪个主实体。 如果当前实体就是独立实体，则此值为null
	 * @return
	 */
	public PropertyInfo getParentModelProperty() {
		return parentModelProperty;
	}
	public void setParentModelProperty(PropertyInfo parentModelProperty) {
		this.parentModelProperty = parentModelProperty;
	}
	/**
	 * 实体默认排序字符
	 * @return
	 */
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 是否记日志
	 * @return
	 */
	public boolean isLogged() {
		return logged;
	}
	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	/**
	 * 是否使用缓存
	 * @return
	 */
	public boolean isCached() {
		return cached;
	}
	public void setCached(boolean cached) {
		this.cached = cached;
	}

	/**
	 * 得到当前实体的和数据库中对应的所有属性的集合
	 * @return
	 */
	public List<PropertyInfo> getProperties() {
		return properties;
	}
	public void setProperties(List<PropertyInfo> properties) {
		this.properties = properties;
	}
	/**
	 * 当前实体的中文名
	 * @return
	 */
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	
	/**
	 * 不被记录log的字段
	 * @return
	 */
	public String[] getNotLoggedFields() {
		return notLoggedFields;
	}
	public void setNotLoggedFields(String[] notLoggedFields) {
		this.notLoggedFields = notLoggedFields;
	}


	
}

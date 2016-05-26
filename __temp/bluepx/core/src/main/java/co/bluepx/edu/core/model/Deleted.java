package co.bluepx.edu.core.model;

/**
 * 逻辑删除的实体继承此接口
 * 
 *
 */

public interface Deleted {
	/**
	 * 删除状态：已删除
	 */
	Integer DELETE_TRUE = 1;
	/**
	 * 删除状态，正常
	 */
	Integer DELETE_FALSE = 0;

	Integer getDeleted();

	void setDeleted(Integer deleted);
}

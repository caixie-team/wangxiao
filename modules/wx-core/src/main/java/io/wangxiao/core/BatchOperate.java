package io.wangxiao.core;


import io.wangxiao.core.model.BaseIncrementIdModel;

import java.util.ArrayList;
import java.util.List;


/**
 * 批量保存实体操作类
 * <p>
 * 	该类用于处理一个动作可能包含多个操作（删除、修改和新增）的业务操作，调用{@link #process(BaseBiz)}方法处理，可以通过相应属性的get方法，获取处理结果。
 * </p>
 *
 */
public class BatchOperate<T extends BaseIncrementIdModel> {
	/**
	 * 需要保存的实体，该列表包含新增和修改的实体
	 */
	private List<T> newList;
	/**
	 * 要更新的实体，该列表仅包含更新的实体
	 */
	private List<T> updateList;
	/**
	 * 新增的实体，该列表仅包含新增的实体
	 */
	private List<T> addList;
	/**
	 * 删除的实体，需要删除的实体ID
	 */
	private List<T> deleteList;
	/**
	 * 构造一个空的实体
	 */
	public BatchOperate(){
	}
	/**
	 * 类构造一个实体
	 * <p>
	 * 	使用此构造方法构造时，请务必使用{@link #setOldList(List)}或 {@link #setDeleteList(List)}来设置需要删除的值列表，否则默认为没有需要删除的数据
	 * </p>
	 * @param newList 需要批量操作的实体
	 */
	public BatchOperate(List<T> newList){
		this.newList = newList;
	}
	/**
	 * 构造一个操作实体
	 * @param newList 新实体列表
	 * @param oldList 原始实体列表
	 */
	public BatchOperate(List<T> newList,List<T> oldList){
		this.newList = newList;
		setOldList(oldList);
	}
	/**
	 * 获取要保存的实体列表，该列表应包含新增和修改的实体
	 * @return 保存的实体列表
	 */
	public List<T> getNewList() {
		return newList;
	}
	/**
	 * 设置要保存的实体列表，该列表应包含新增和修改的实体
	 * @param newList 要保存的实体列表
	 */
	public void setNewList(List<T> newList) {
		this.newList = newList;
	}
	/**
	 * 获取需要更新的实体
	 * @return 实体列表
	 */
	public List<T> getUpdateList() {
		return updateList;
	}
	/**
	 * 设置需要更新的实体列表
	 * @param updateList 实体列表
	 */
	public void setUpdateList(List<T> updateList) {
		this.updateList = updateList;
	}
	/**
	 * 设置需要新增的实体列表
	 * @return 新增的实体列表
	 */
	public List<T> getAddList() {
		return addList;
	}
	/**
	 * 设置需要新增的实体列表
	 * @param addList 实体列表
	 */
	public void setAddEntities(List<T> addList) {
		this.addList = addList;
	}
	/**
	 * 获取需要删除的实体列表
	 * @return 列表
	 */
	public List<T> getDeleteList() {
		return deleteList;
	}
	/**
	 * 设置需要删除的实体列表
	 * @param deleteList 列表值
	 */
	public void setDeleteList(List<T> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * 根据原始数据计算需要删除的数据列表，该参数为实体列表
	 * <p>
	 * 	调用该方法进行重写设置要移除的列表，该方法会用{@link #getNewList()}中查找不存在的列表，并设置进入需要删除的列表中
	 * </p>
	 * @param oldList 原始数据
	 */
	public void setOldList(List<T> oldList){
		deleteList = new ArrayList<T>();
		if(oldList != null){
			//检查需要保存的列表不存在，即全部删除
			if(newList == null){
				T entity = null;
                for (T anOldList : oldList) {
                    entity = anOldList;
                    if (entity.getId() != null) {
                        deleteList.add(entity);
                    }
                }
			}else{
				T entity = null;
				T t = null;
				//开始逐个检查是否存在
                for (T anOldList : oldList) {
                    entity = anOldList;
                    for (T aNewList : newList) {
                        t = aNewList;
                        //如果存在
                        if (t.getId() != null && entity.getId().longValue() == t.getId().longValue()) {
                            break;
                        }
                        t = null;
                    }
                    //如果不存在
                    if (t == null) {
                        deleteList.add(entity);
                    }
                }
			}
		}
	}

	/**
	 * 从oldList列表中计算新增和更新的实体列表
	 */
	public void countUpdateOrAdd(){
		addList = new ArrayList<T>();
		updateList = new ArrayList<T>();
		if(newList != null){
			T entity = null;
            for (T aNewList : newList) {
                entity = aNewList;
                //如果是新增
                if (entity.getId() == null) {
                    addList.add(entity);
                }
                //修改
                else {
                    updateList.add(entity);
                }
            }
		}
	}
	/**
	 * 处理，即增删该操作处理
	 * @param baseBiz 实体的biz对象，用于处理持久化
	 */
	public <K extends BaseDao<T>> void process(BaseService<T,K> baseBiz){
		if(baseBiz == null){
			throw new IllegalArgumentException("baseBiz 不能为空");
		}
		//删除实体列表
		if(deleteList != null){
            for (T aDeleteList : deleteList) {
                baseBiz.delete(aDeleteList);
            }
		}
		//如果未计算
		if(addList == null){
			countUpdateOrAdd();
		}
		//保存
		if(newList != null){
			T entity = null;
            for (T aNewList : newList) {
                entity = aNewList;
                baseBiz.save(entity);
            }
		}
	}
}

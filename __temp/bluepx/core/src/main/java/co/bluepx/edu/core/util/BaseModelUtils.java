package co.bluepx.edu.core.util;


import co.bluepx.edu.core.model.BaseIncrementIdModel;
import co.bluepx.edu.core.model.CategoryEnable;

import java.util.ArrayList;
import java.util.List;

/**
 * entity 工具类
 */
public class BaseModelUtils {
	
	/**
	 * 获取ID列表
	 * @param models
	 * @return
	 *
	 */
	public static List<Long> getIds(List<? extends BaseIncrementIdModel> models){
		if(CollectionUtil.isEmpty(models)){
			return new ArrayList<Long>();
		}
		List<Long> ids = new ArrayList<Long>(models.size());
		for(BaseIncrementIdModel m : models){
			ids.add(m.getId());
		}
		return ids;
	}

	/**
	 * 获取category id列表
	 * @param models
	 * @return
	 *
	 */
	public static List<Long> getCategoryIds(List<? extends CategoryEnable> models){
		if(CollectionUtil.isEmpty(models)){
			return new ArrayList<Long>();
		}
		List<Long> ids = new ArrayList<Long>(models.size());
		for(CategoryEnable m : models){
			ids.add(m.getCategoryId());
		}
		return ids;
	}
}

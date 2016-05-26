package com.atdld.os.common.service;

import java.util.Map;

/**
 * Created by Administrator on 2014/12/25.
 */
public interface SnsHessianService {
	/**
	 * 社区添加动态
	 * @param map
	 */
	public void addDynamic(Map<String,String> map);
	/**
	 * 社区删除动态
	 * @param map
	 */
	public void delDynamic(Long id,int type);
}

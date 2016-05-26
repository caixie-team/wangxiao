package io.wangxiao.core.util;

import io.wangxiao.core.model.BaseIncrementIdModel;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 集合工具类
 *
 */
public class CollectionUtil {
	
	/**
	 * 判断集合是否为空
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Collection<?> obj) {
		return (obj == null || obj.size() == 0);
	}
	
	/**
	 * 将字符串集合值转换成长整型类型集合，将其转换成ArrayList类型结果集
	 * @param collection 要转换的集合
	 * 这个已经限定了返回的类型，所以直接用List by adou
	 * @return 结果
	 */
	public static List<Long> toLongCollection(Collection<String> collection){
		return toLongCollection(collection,ArrayList.class,false);
	}
	
	/**
	 * 将字符串集合值转换成长整型类型集合，将其转换成ArrayList类型结果集,忽略重复数据
	 * @param collection 要转换的集合
	 * 这个已经限定了返回的类型，所以直接用List by adou
	 * @return 结果
	 */
	public static List<Long> toLongCollectionByIgnoreRepeat(Collection<String> collection){
		return toLongCollection(collection,ArrayList.class,true);
	}
	/**
	 * 将字符串数组值转换成长整型类型集合，将其转换成ArrayList类型结果集
	 * @param collection 要转换的集合
	 * 这个已经限定了返回的类型，所以直接用List by adou
	 * @return 结果
	 */
	public static List<Long> toLongCollection(String[] collection){
		return toLongCollection(collection,ArrayList.class,false);
	}
	
	/**
	 * 将字符串数组值转换成长整型类型集合，将其转换成ArrayList类型结果集，忽略重复数据
	 * @param collection 要转换的集合
	 * 这个已经限定了返回的类型，所以直接用List by adou
	 * @return 结果
	 */
	public static List<Long> toLongCollectionByIgnoreRepeat(String[] collection){
		return toLongCollection(collection,ArrayList.class,true);
	}
	/**
	 * 将字符串数组转换成Long集合
	 * @param collection 字符串数组
	 * @param arrayType 要转换的结果集合类型
	 * @param ignoreRepeat 忽略重复数据
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Collection<Long>> T toLongCollection(String[] collection,Class<?> arrayType,boolean ignoreRepeat){
		//检查参数
		if(collection == null){
			return null;
		}
		//如果不存在
		if(arrayType == null){
			arrayType = ArrayList.class;
		}
		//检查类型是否是collection
		if(!Collection.class.isAssignableFrom(arrayType)){
			throw new IllegalArgumentException("arrayType 必须是Collection子类");
		}
		//检查是否是接口
		if(arrayType.isInterface()){
			throw new IllegalArgumentException("arrayType 必须是可以实例化的子类");
		}
		try {
			//实例化结果类
			Collection<Long> rs = (Collection<Long>)arrayType.newInstance();
			if(ignoreRepeat){
				//使用set去重
				Set<Long> sets = new HashSet<Long>();
				for(int i = 0;i < collection.length;i ++){
					sets.add(Long.parseLong(collection[i]));
				}
				rs.addAll(sets);
			}else{
				for(int i = 0;i < collection.length;i ++){
					rs.add(Long.parseLong(collection[i]));
				}
			}
			return (T) rs;
		}catch (Exception e) {
			throw new IllegalArgumentException("arrayType 必须是Collection子类",e);
		}
	}
	
	/**
	 * 将字符串集合值转换成长整型类型集合
	 * @param collection 要转换的集合
	 * @param arrayType 返回的集合类型，如果为空则默认为{@link ArrayList}
	 * @param ignoreRepeat 忽略重复数据
	 * @return 结果
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Collection<Long>> T toLongCollection(Collection<String> collection,Class<?> arrayType,boolean ignoreRepeat){
		//检查参数
		if(collection == null){
			return null;
		}
		//如果不存在
		if(arrayType == null){
			arrayType = ArrayList.class;
		}
		//检查类型是否是collection
		if(!Collection.class.isAssignableFrom(arrayType)){
			throw new IllegalArgumentException("arrayType 必须是Collection子类");
		}
		//检查是否是接口
		if(arrayType.isInterface()){
			throw new IllegalArgumentException("arrayType 必须是可以实例化的子类");
		}
		try {
			//实例化结果类
			Collection<Long> rs = (Collection<Long>)arrayType.newInstance();
			if(ignoreRepeat){
				//使用set去重
				Set<Long> sets = new HashSet<Long>();
				Iterator<String> it = collection.iterator();
				while(it.hasNext()){
					//转换并插入到结果集中
					sets.add(Long.parseLong(it.next()));
				}
				rs.addAll(sets);
			}else{
				Iterator<String> it = collection.iterator();
				while(it.hasNext()){
					//转换并插入到结果集中
					rs.add(Long.parseLong(it.next()));
				}
			}
			return (T) rs;
		}catch (Exception e) {
			throw new IllegalArgumentException("arrayType 必须是Collection子类",e);
		}
	}
	
	/**
	 * 将集合转换成特定相应类型
	 * @param list
	 * @param type 类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T>  T[] toArray(List<T> list,Class<T> type){
		if(isEmpty(list)){
			return null;
		}
		T[] result = null;
		//根据类型进行转换
		//字符串
		if(type == String.class){
			result = (T[])new String[list.size()];
		}
		//INTEGER
		else if(type == Integer.class){
			result = (T[])new Integer[list.size()];
		}
		//Long
		else if(type == Long.class){
			result = (T[])new Long[list.size()];
		}
		for(int i = 0;i < list.size();i ++){
			result[i] = list.get(i);
		}
		return result;
	}
	
	/**
	 * 功能：以#号分割String
	 * @param arr 
	 * @return 
	 * @author xianhai
	 * @date 2013-1-25 下午6:54:28
	 */
	public static Map<Long,Integer> convertToMap(List<String> arr) {
		//Map<goodsId,duration>
		Map<Long,Integer> tempMap = new HashMap<Long,Integer>();
		if(arr==null){
			return tempMap;
		}
		for (String _obj : arr) {
			_obj = _obj==null?"":_obj;
			String[] _arr = _obj.split("#");
			if(_arr.length!=2){continue;}
			//商品视频
			Long id = Long.parseLong(_arr[0]);
			tempMap.put(id, Integer.parseInt(_arr[1]));
		}

		return tempMap;
	}
	
	/**
	 * 
	 * <p>
	 * 转换字符串为Long数组并去除重复数据
	 * </p>
	 * @param
	 * @return List<Long>
	 * @throws
	 */
	public static List<Long> toLongCollection(String str, String separator) {
		if(StringUtils.isNotBlank(str)) {
			if(str.endsWith(separator)) {
				str = str.substring(0, str.length() - 1);
			}
			return toLongCollectionByIgnoreRepeat(str.split(separator));
		}
		return new ArrayList<Long>();
	}
	
	/**
	 * 功能:把list<entity>对象转换成List<long> idList
	 * @param list
	 * @return
	 */
	public static List<Long> toLongIdList(List<? extends BaseIncrementIdModel> list){
		List<Long> idList = new ArrayList<Long>();
		for(BaseIncrementIdModel baseModel:list){
			idList.add(baseModel.getId());
		}
		return idList;
	}
	
	/**
	 * 将列表以特定字符连接
	 * @param list
	 * @return
	 */
	public static String toString(Collection<?> list,String split){
		if(list == null){
			return null;
		}
		StringBuilder rs = new StringBuilder();
		Iterator<?> it = list.iterator();
		while(it.hasNext()){
			rs.append(it.next());
			if(it.hasNext()){
				rs.append(split);
			}
		}
		return rs.toString();
	}

}

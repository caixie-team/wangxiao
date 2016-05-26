package io.wangxiao.core.web;


import io.wangxiao.core.util.DateUtils;
import io.wangxiao.core.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

/**
 * httpServletRequest 工具类
 * <p>
 * 用于从HttpServletRequest中获取特定类型数据
 * </p>
 * 
 *
 */
public class RequestUtil {
	/**
	 * HttpServletRequest 对象
	 */
	private HttpServletRequest request;

	/**
	 * 传入一个HttpServletRequest 源
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 */
	public RequestUtil(HttpServletRequest request) {
		//检查request是否为空
		if(request == null){
			throw new IllegalArgumentException("request is null");
		}
		this.request = request;
	}

	/**
	 * 获取整型数据
	 * 
	 * @param request
	 *            数据源
	 * @param name
	 *            数据 Param key
	 * @return 值，如果不存在则返回null
	 */
	public static Integer getInteger(HttpServletRequest request, String name) {
		// 检查参数是否为空
		if (request == null || StringUtils.isTrimEmpty(name)) {
			return null;
		}
		Integer i = null;
		try {
			i = Integer.parseInt(request.getParameter(name));
		} catch (Exception ex) {
		}
		return i;
	}
	/**
	 * 获取整型数据
	 * @param request 数据源
	 * @param name 数据 Param key
	 * @param defaultValue 默认值
	 * @return 获取的值，如果不存在则返回defaultValue
	 */
	public static Integer getInteger(HttpServletRequest request, String name,
			Integer defaultValue) {
		// 检查参数是否为空
		if (request == null || StringUtils.isTrimEmpty(name)) {
			return defaultValue;
		}
		Integer i = defaultValue;
		try {
			i = Integer.parseInt(request.getParameter(name));
		} catch (Exception ex) {
		}
		return i;
	}

	/**
	 * 获取整型数据
	 * 
	 * @param name
	 *            数据 Param key
	 * @return 值，如果不存在则返回null
	 */
	public Integer getInteger(String name) {
		// 检查参数是否存在
		if (StringUtils.isTrimEmpty(name)) {
			return null;
		}
		Integer i = null;
		try {
			i = Integer.parseInt(request.getParameter(name));
		} catch (Exception ex) {
		}
		return i;
	}
	/**
	 * 获取整型数据
	 * @param name 数据 Param key
	 * @param defaultValue 默认值
	 * @return 获取的值，如果不存在则返回defaultValue
	 */
	public Integer getInteger(String name,Integer defaultValue) {
		// 检查参数是否为空
		if (request == null || StringUtils.isTrimEmpty(name)) {
			return defaultValue;
		}
		Integer i = defaultValue;
		try {
			i = Integer.parseInt(request.getParameter(name));
		} catch (Exception ex) {
		}
		return i;
	}
	/**
	 * 获取长整型值
	 * @param request
	 * @param name 参数名称
	 * @return 值，如果不存在则返回null
	 */
	public static Long getLong(HttpServletRequest request, String name){
		//检查request和name 是否为空
		if(request == null || StringUtils.isTrimEmpty(name)){
			return null;
		}
		Long value  = null;
		try{
			value = Long.parseLong(request.getParameter(name));
		}catch(Exception e){}
		return value;
	}
	/**
	 * 获取长整型值
	 * @param name 参数名称
	 * @return 值，如果不存在则返回null
	 */
	public Long getLong(String name){
		//检查request和name 是否为空
		if(StringUtils.isTrimEmpty(name)){
			return null;
		}
		Long value  = null;
		try{
			value = Long.parseLong(request.getParameter(name));
		}catch(Exception e){}
		return value;
	}
	
	/**
	 * 获取长整型值
	 * @param request
	 * @param name 参数名称
	 * @param defaultValue 默认值
	 * @return 值，如果不存在则返回defaultValue
	 */
	public static Long getLong(HttpServletRequest request, String name,Long defaultValue){
		//检查request和name 是否为空
		if(request == null || StringUtils.isTrimEmpty(name)){
			return defaultValue;
		}
		Long value  = defaultValue;
		try{
			value = Long.parseLong(request.getParameter(name));
		}catch(Exception e){}
		return value;
	}
	/**
	 * 获取长整型值
	 * @param name 参数名称
	 * @param defaultValue 默认值
	 * @return 值，如果不存在则返回defaultValue
	 */
	public Long getLong(String name,Long defaultValue){
		//检查request和name 是否为空
		if(StringUtils.isTrimEmpty(name)){
			return defaultValue;
		}
		Long value  = defaultValue;
		try{
			value = Long.parseLong(request.getParameter(name));
		}catch(Exception e){}
		return value;
	}
	/**
	 * 获取日期值，该值的类型为{@value  #DateUtils.PATTERN_YYYY_MM_DD}
	 * @param request
	 * @param name 名称
	 * @return 值，默认返回null
	 */
	public static Date getDate(HttpServletRequest request, String name){
		//检查request和name 是否为空
		if(request == null || StringUtils.isTrimEmpty(name)){
			return null;
		}
		//开始解析
		try {
			return DateUtils.parse(request.getParameter(name), DateUtils.PATTERN_YYYY_MM_DD);
		} catch (ParseException e) {
		}
		return null;
	}
	/**
	 * 获取日期值，该值的类型为{@value #DateUtils.PATTERN_YYYY_MM_DD}
	 * @param request
	 * @param name 名称
	 * @param defaultValue 默认值
	 * @return 值，默认返回defaultValue
	 */
	public static Date getDate(HttpServletRequest request, String name,Date defaultValue){
		//检查request和name 是否为空
		if(request == null || StringUtils.isTrimEmpty(name)){
			return defaultValue;
		}
		//开始解析
		try {
			return DateUtils.parse(request.getParameter(name), DateUtils.PATTERN_YYYY_MM_DD);
		} catch (ParseException e) {
		}
		return defaultValue;
	}
	/**
	 * 获取日期值，该值的类型为{@value #DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS}
	 * @param request
	 * @param name 名称
	 * @return 值，默认返回null
	 */
	public static Date getDateTime(HttpServletRequest request, String name){
		//检查request和name 是否为空
		if(request == null || StringUtils.isTrimEmpty(name)){
			return null;
		}
		//开始解析
		try {
			return DateUtils.parse(request.getParameter(name), DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS);
		} catch (ParseException e) {
		}
		return null;
	}
	/**
	 * 获取日期值，该值的类型为{@value #DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS}
	 * @param request
	 * @param name 名称
	 * @return 值，默认返回null
	 */
	public static Date getDateTimeSecond(HttpServletRequest request, String name){
		//检查request和name 是否为空
		if(request == null || StringUtils.isTrimEmpty(name)){
			return null;
		}
		//开始解析
		try {
			return DateUtils.parse(request.getParameter(name), DateUtils.PATTERN_YYYY_MM_DD_HH_MM);
		} catch (ParseException e) {
		}
		return null;
	}
	
	/**
	 * 获取日期值，该值的类型为{@value #DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS}
	 * @param request
	 * @param name 名称
	 * @param defaultValue 默认值
	 * @return 值，默认返回defaultValue
	 */
	public static Date getDateTime(HttpServletRequest request, String name,Date defaultValue){
		//检查request和name 是否为空
		if(request == null || StringUtils.isTrimEmpty(name)){
			return defaultValue;
		}
		//开始解析
		try {
			return DateUtils.parse(request.getParameter(name), DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS);
		} catch (ParseException e) {
		}
		return defaultValue;
	}
	
	/**
	 * 获取日期值，该值的类型为{@value #DateUtils.PATTERN_YYYY_MM_DD}
	 * @param name 名称
	 * @return 值，默认返回null
	 */
	public Date getDate(String name){
		//检查request和name 是否为空
		if(StringUtils.isTrimEmpty(name)){
			return null;
		}
		//开始解析
		try {
			return DateUtils.parse(request.getParameter(name), DateUtils.PATTERN_YYYY_MM_DD);
		} catch (ParseException e) {
		}
		return null;
	}
	
	/**
	 * 获取日期值，该值的类型为{@value #DateUtils.PATTERN_YYYY_MM_DD_HH}
	 * @param request
	 * @param name
	 * @return
	 */
	public static Date getHourDate(HttpServletRequest request,String name){
		//检查request和name 是否为空
		if(StringUtils.isTrimEmpty(name)){
			return null;
		}
		//开始解析
		try {
			return DateUtils.parse(request.getParameter(name), DateUtils.PATTERN_YYYY_MM_DD_HH);
		} catch (ParseException e) {
		}
		return null;
	}
	/**
	 * 获取日期值，该值的类型为{@value #DateUtils.PATTERN_YYYY_MM_DD_HH}
	 * @param name
	 * @return
	 */
	public Date getHourDate(String name){
		//检查request和name 是否为空
		if(StringUtils.isTrimEmpty(name)){
			return null;
		}
		//开始解析
		try {
			return DateUtils.parse(request.getParameter(name), DateUtils.PATTERN_YYYY_MM_DD_HH);
		} catch (ParseException e) {
		}
		return null;
	}
	
	/**
	 * 获取日期值，该值的类型为{@value #DateUtils.PATTERN_YYYY_MM_DD}
	 * @param name 名称
	 * @param defaultValue 默认值
	 * @return 值，默认返回defaultValue
	 */
	public Date getDate(String name,Date defaultValue){
		//检查request和name 是否为空
		if(StringUtils.isTrimEmpty(name)){
			return defaultValue;
		}
		//开始解析
		try {
			return DateUtils.parse(request.getParameter(name), DateUtils.PATTERN_YYYY_MM_DD);
		} catch (ParseException e) {
		}
		return defaultValue;
	}
	/**
	 * 获取日期值，该值的类型为{@value #DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS}
	 * @param name 名称
	 * @return 值，默认返回null
	 */
	public Date getDateTime(String name){
		//检查request和name 是否为空
		if(StringUtils.isTrimEmpty(name)){
			return null;
		}
		//开始解析
		try {
			return DateUtils.parse(request.getParameter(name), DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS);
		} catch (ParseException e) {
		}
		return null;
	}
	/**
	 * 获取日期值，该值的类型为{@value #DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS}
	 * @param name 名称
	 * @param defaultValue 默认值
	 * @return 值，默认返回defaultValue
	 */
	public Date getDateTime(String name,Date defaultValue){
		//检查request和name 是否为空
		if(request == null || StringUtils.isTrimEmpty(name)){
			return defaultValue;
		}
		//开始解析
		try {
			return DateUtils.parse(request.getParameter(name), DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS);
		} catch (ParseException e) {
		}
		return defaultValue;
	}
	
}

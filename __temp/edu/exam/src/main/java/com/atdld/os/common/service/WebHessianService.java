package com.atdld.os.common.service;


import java.util.Map;






/**
 * Created by Administrator on 2014/12/25.
 */
public interface WebHessianService {

    /**
     * 修改用户最后考试的专业
     */
	public void updateUserExpandForSubject(Map<String,String> map);
	
    //调用项目专业接口-web
	
    /**
     * 查询用户信息
     * @param map
     * @return
     */
	public Map<String,String> getUserInfo(Long cusId);
	
	/**
     * 批量查询用户信息
     * @param map
     * @return
     */
	public Map<String,String> getUserInfoByUids(String ids);
	
	/**
	 * 添加积分
	 * @param map
	 */
	public void addUserIntegral(Map<String,String> map);
	
	/**
     * 修改CourseProfile浏览次数
     * @param map
     */
    public void updateCourseProfile(Map<String,String> map);

    /**
	 * 通过标识更新未读数加一或清零
	 */
	public void readMsgNumAddOrReset(String falg, Long cusId,String iType);
	
	/**
	 * 更新用户的上传统计系统消息时间
	 */
	public void updateCusForLST(Long cusId,Long date);
	
	/**
     * 修改UserExpand浏览次数
     * @param map
     */
    public void updateUserExpandCount(Map<String,String> map);
    
    /**
     * 查询当前用户是否跟传来的userIds是好友关系，返回是好友的列表
     * @param userIds
     * @param cusId
     * @return
     */
    public Map<String,String> queryIsFrirndByIds(String userIdsJson,Long cusId);
    
    /**
     * 查询全部好友
     * 
     * @param customer
     *            好友实体
     * @param page
     *            分页参数
     * @return List<QueryCustomer>
     * @throws Exception
     */
    public Map<String,String> queryAllCustomer(Map<String,String> map);

    /**
     * 通过用户id 查询该用户的关注的列表
     * 
     * @param friend
     *            传入cusId 用户id
     * @param page
     *            分页参数
     * @return List<QueryCustomer>
     * @throws Exception
     */
    public Map<String,String> queryMyAttentionCustomer(Map<String,String> map);
    
    /**
     * 通过showname 查询customer(精确搜索)
     */
    public Map<String,String> queryCustomerByShowNameEquals(String showName);
    /**
	 * 查询我的粉丝用户的列表
	 * 
	 * @param cusAttention
	 *            cusAttentionId传入
	 * @param page
	 *            分页参数
	 * @return List<QueryCustomer> 查询我的粉丝用户列表
	 */
	public Map<String,String> queryMyFans(Map<String,String> map);
	/**
	 * 通过showname 查询customer
	 * 
	 * @param showName
	 */
	public Map<String,String> queryCustomerByShowName(String showName, int size);
	/**
	 * 首页获取推荐的课程 封装为map
	 * @param recommendId
	 * @return
	 * @throws Exception
	 */
    public Map<String,String> getCourseListByHomePage(Long recommendId);
    
    /**
     * 关键字过滤
     * @param str
     * @return
     */
    public String doFilter(String content);
}

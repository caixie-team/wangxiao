package com.atdld.os.app.common;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.atdld.os.app.entity.website.AppGroup;
import com.atdld.os.common.constants.CommonConstants;

public class IMUtil {
	private static Logger logger = Logger.getLogger(IMUtil.class);
	private static String email=CommonConstants.imEmail;
	private static String devpwd=CommonConstants.imDevpwd;
	private static String appkey=CommonConstants.imAppKey;
	private static Gson gson = new Gson();
	/**
	 * 创建群组
	 * @param group_name 群名称，必选项
	 * @param group_head 群头像，可选项
	 * @param owner_type 所有类型，必选项。0为公开群，1为私有群
	 * @param owner_account 所有者账号，必选项
	 * @param approval 加入类型，必选项。0为自由加入，1为需要群主验证
	 * @param group_info 群扩展信息，可选项。用于保存一些额外的信息，服务器不会对此信息做解析，在获取详情的时候可以拉取到
	 * @throws Exception 
	 * @throws HttpException 
	 * @return boolean true创建成功 false创建失败
	 */
	public static Map<String,String> createGroup(AppGroup group) throws HttpException, Exception{
		String url ="https://qplusapi.gotye.com.cn:8443/api/CreateGroup";
		Map<String,Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("email", email);
		dataMap.put("devpwd", devpwd);
		dataMap.put("appkey", appkey);
		dataMap.put("group_name", group.getGroupName());
		dataMap.put("group_head", group.getGroupHead());
		dataMap.put("owner_type", group.getOwnerType());
		dataMap.put("owner_account", group.getOwnerAccount());
		dataMap.put("approval", group.getAppRoval());
		dataMap.put("group_info", group.getGroupInfo());
		//发送请求
		String result = requestHttp(dataMap,url);
		//得到请求返回结果
		logger.info("----result:"+result);
		Map<String,String> resultMap = gson.fromJson(result, new TypeToken<Map<String,String>>(){}.getType());
		
		return resultMap;
	}
	
	/***
	 * 获取群详情
	 * @param groupId 群组ID
	 * @return AppGroup
	 */
	public static AppGroup getGroupDetail(String[] groupIdArr) throws Exception{
		AppGroup appGroup = new AppGroup();
		String url ="https://qplusapi.gotye.com.cn:8443/api/GetGroupDetail";
		Map<String,Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("email", email);
		dataMap.put("devpwd", devpwd);
		dataMap.put("appkey", appkey);
		dataMap.put("group_id_list", groupIdArr);
		String result = requestHttp(dataMap,url);
		System.out.println(result);
		JsonParser jsonParser=new JsonParser();
		JsonObject  json = jsonParser.parse(result).getAsJsonObject();
		JsonArray list = json.getAsJsonArray("group_list");
		List<Map<String,String>> resultMap = gson.fromJson(list, new TypeToken<List<Map<String,String>>>(){}.getType());
		
		Map<String,String> group = resultMap.get(0);
		appGroup.setGroupId(group.get("group_id"));//群ID
		appGroup.setGroupName(group.get("group_name"));//群名
		appGroup.setGroupHead(group.get("group_head"));//群图片
		appGroup.setOwnerType(group.get("owner_type"));//群类型
		appGroup.setOwnerAccount(group.get("owner_account"));//创建用户
		appGroup.setAppRoval(group.get("approval"));//验证级别
		appGroup.setGroupInfo(group.get("group_info"));//扩展数据
		appGroup.setMaxNumber(group.get("max_number"));
		return appGroup;
	}
	
	
	/**
	 * 解散（删除）群组
	 * @param groupId 群组ID
	 * @return true操作成功 false操作失败
	 * @throws Exception
	 */
	public static boolean dismissGroup(String groupId) throws Exception{
		String url ="https://qplusapi.gotye.com.cn:8443/api/DismissGroup";
		Map<String,Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("email",email);
		dataMap.put("devpwd",devpwd);
		dataMap.put("appkey",appkey);
		dataMap.put("group_id",groupId);
		String reslut = requestHttp(dataMap,url);
		Map<String,String> resultMap = gson.fromJson(reslut, new TypeToken<Map<String,String>>(){}.getType());
		if(resultMap.get("errcode").equals("200")){
			return true;
		}
		return false;
	}
	
	/**
	 * 修改群组
	 * @param group 群组对象
	 * @return true修改成功 false修改失败
	 * @throws Exception 
	 */
	public static boolean modifyGroup(AppGroup group) throws Exception{
		String url="https://qplusapi.gotye.com.cn:8443/api/ModifyGroup";
		Map<String,Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("email",email);
		dataMap.put("devpwd",devpwd);
		dataMap.put("appkey",appkey);
		dataMap.put("group_id",group.getGroupId());
		dataMap.put("group_name", group.getGroupName());
		dataMap.put("group_info", group.getGroupInfo());
		
		String result = requestHttp(dataMap,url);
		Map<String,String> resultMap = gson.fromJson(result, new TypeToken<Map<String,String>>(){}.getType());
		if(resultMap.get("errcode").equals("200")){
			return true;
		}
		return false;
	}
	
	/**
	 * 发送请求
	 * @param dataMap 传入参数
	 * @param url 请求路径
	 * @return String 请求结果
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	private static String requestHttp(Map<String,Object> dataMap,String url) throws Exception{
		String params = gson.toJson(dataMap);
		
		HttpClient httpClient = new HttpClient();
		
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("ContentType","text/xml;charset=UTF-8");
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		postMethod.setRequestBody(params);
		logger.info("----requestUrl:"+url);
		httpClient.executeMethod(postMethod);
		return postMethod.getResponseBodyAsString();
	}
	
	public static void main(String[] args) {
		try{
			
			dismissGroup("16425");
		}catch (Exception e) {
			logger.error("-----------",e);
		}
	}
}

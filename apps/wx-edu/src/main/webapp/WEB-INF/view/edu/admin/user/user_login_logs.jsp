<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>系统用户登陆列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}" charset="UTF-8"></script>
<script type="text/javascript">
function clean(){
	//清空时间
	$("input[name='startDate']:first").attr("value", "");
	$("input[name='endDate']:first").attr("value", "");
}
function submitSearch(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
function goBack(){
	window.location.href="${ctx}/admin/user/listAllUser";
}




</script>
</head>

<body  >


<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">系统用户登陆日志</strong> / <small>登陆日志列表</small></div>
</div>
			<!-- /tab1 begin-->
<div class="mt20">

	<div class="commonWrap">
	
		<form action="${ctx}/admin/sys/loginlog/${userId}" class="am-form" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
			<div class="mt20 am-padding admin-content-list">
				 	<div class="am-tab-panel am-fade am-active am-in">
						<div class="am-g am-margin-top am-u-sm-6 am-text-left">
							<div class="am-u-sm-4 am-text-right">
								登陆开始日期：
							</div>
						<div class="am-u-sm-8 am-u-end">
							<input class="" readonly="readonly" data-am-datepicker="{format: 'yyyy-mm-dd'}" type="text" name="startDate" value="${userLoginLog.startDate}"  id="startDate" />
						</div>
						</div>
						<div class="am-g am-margin-top am-u-sm-6 am-text-left">
							<div class="am-u-sm-4 am-text-right">
								登陆结束日期：
							</div>
						<div class="am-u-sm-8 am-u-end">
						  <input readonly="readonly" data-am-datepicker="{format: 'yyyy-mm-dd'}" type="text" name="endDate" value="${userLoginLog.endDate}"  id="endDate" />
						</div>
						</div> 
						
						
                               
                                <div class="am-g am-margin-top am-u-sm-12 am-text-left">
									<div class="am-u-sm-12 am-u-end am-text-center">
										<input type="button"  class="am-btn am-btn-danger" value="查询" name="" onclick="submitSearch()"/>
										<input type="button"  class="am-btn am-btn-danger" value="清空" name="" onclick="clean()"/>
                    				  <input class="am-btn am-btn-danger" type="button" onclick="goBack()" value="返回">
								</div>
								<div class="clearfix"></div>
							</div>
							 </div>
							 <div class="clearfix"></div>
						</div>
						<div class="mt20"></div>
					<div class="am-scrollable-horizontal am-scrollable-vertical">
					<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
						<thead>
							<tr>
								<th><span>&nbsp;序号</span></th>
								<th><span>用户名</span></th>
								<th><span>登陆Ip</span></th>
								<th><span>登陆地址</span></th>
								<th><span>浏览器</span></th>
								<th><span>操作系统</span></th>
								<th><span>登陆时间</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${loginLogList.size()>0}">
						<c:forEach  items="${loginLogList}" var="user" >
							<tr>
								<%-- <td><input type="checkbox" class="questionIds" id="${list.id }"/>&nbsp;${list.id }</td> --%>
								<td>${user.id }</td>
								<td>${user.nickname }</td>
								<td>${user.loginIp}</td>
								<td>${user.address}</td>
								<td>${user.userAgent}</td>
								<td>${user.osname}</td>
								<td><fmt:formatDate value="${user.loginTime }" type="both"/></td>
							<%-- 	<td  class="c_666 czBtn" align="center">
								<c:if test="${user.isavalible==0}">
							        <a class="ml10 btn smallbtn btn-y attr${user.id}" title="禁用" href="javascript:updateIsavalible(${user.id},1)">禁用</a>
							     </c:if>
							      <c:if test="${user.isavalible==1}">
							        <a class="ml10 btn smallbtn btn-y attr${user.id}" title="恢复" href="javascript:updateIsavalible(${user.id},0)">恢复</a>
							     </c:if>
								<a class="ml10 btn smallbtn btn-y" title="修改密码" href="${ctx}/admin/user/toupdatepwd/${user.id}">修改密码</a>
							     <a class="ml10 btn smallbtn btn-y" title="赠送课程" href="${ctx}/admin/user/courseList?queryCourse.userId=${user.id}">赠送课程</a>
							     <a class="ml10 btn smallbtn btn-y" target="_blank" title="学习记录" href="${ctx}/admin/user/studyhistory/${user.id}">学习记录</a>
                                <a class="ml10 btn smallbtn btn-y" target="_blank" title="考试记录" href="${ctx}/admin/paper/toPaperRecord?queryPaperRecord.email=${user.email}">考试记录</a>
								</td> --%>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${loginLogList.size()==0||loginLogList==null}">
							<tr>
						<td colspan="12">
							<div data-am-alert=""
								 class="am-alert am-alert-secondary mt20 mb50">
								<center>
									<big> <i class="am-icon-frown-o vam"
											 style="font-size: 34px;"></i> <span class="vam ml10">还没有登陆信息！</span></big>
								</center>
							</div>
						</td>
					</tr>
							</c:if>
						</tbody>
					</table>
						</div>
					</table>
					</form>
					<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					<!-- /pageBar end -->
				<!-- /commonWrap -->
			</div>
		
</body>
</html>

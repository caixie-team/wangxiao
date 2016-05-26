<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>学员登陆日志</title>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}"></script>
	<script type="text/javascript">
		function submitSearch(){
			$("#pageCurrentPage").val(1);
			$("#searchForm").submit();
		}
		function clean(){
			$("#email,#userId,#nickname,#mobile,#startDate,#endDate").val("");
		}
	</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">学员管理</strong> / <small>学员登陆日志</small></div>
</div>
<hr>
<form action="${ctx}/admin/user/loginlog" class="am-form" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					学员ID
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="number"class="am-input-sm" name="userLoginLog.userId" value="${userLoginLog.userId}" id="userId" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					登陆开始日期
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="form-datetime-lang am-form-field" readonly name="userLoginLog.startDate" value="${userLoginLog.startDate}"  id="startDate" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-u-end am-text-left">
				<div class="am-u-sm-4 am-text-right">
					登陆结束日期
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="form-datetime-lang am-form-field" readonly name="userLoginLog.endDate" value="${userLoginLog.endDate}"  id="endDate" />
				</div>
			</div>
			<div class="mt20 clear"></div>

			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					用户名
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm"  name="userLoginLog.nickname" value="${userLoginLog.nickname}"  id="nickname" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					邮箱
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="email" class=" am-input-sm" name="userLoginLog.email" value="${userLoginLog.email}" id="email" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-u-end am-text-right">
					手机号
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class=" am-input-sm" pattern="^1[3-9]\d{9}$" maxlength="11"  name="userLoginLog.mobile" value="${userLoginLog.mobile}" id="mobile" />
				</div>
			</div>
			<div class="mt20 clear"></div>

			<div class="am-g am-margin-top am-u-sm-12 am-text-left">
				<div class="am-u-sm-12 am-u-end am-text-center">
					<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)()">
						<span class="am-icon-search"></span> 搜索
					</button>
					<button type="button" class="am-btn am-btn-danger" onclick="clean()">
						清空
					</button>
				</div>
			</div>
			<div class="mt20 clear"></div>
		</div>
	</div>
</form>
</div>

<div class="mt20">
	<div class="doc-example">
		<div class="am-scrollable-horizontal">
			<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
				<thead>
				<tr>

					<th class="table-title" width="11%">学员ID</th>
					<th class="table-title" width="11%">用户名</th>
					<th class="table-title" width="11%">手机</th>

					<th class="table-title" width="11%">邮箱</th>
					<th class="table-title" width="11%">登陆时间</th>
					<th class="table-title" width="11%">登陆Ip</th>

					<th class="table-title" width="11%">登陆地址</th>
					<th class="table-title" width="11%">浏览器</th>
					<th class="table-title" width="12%">操作系统</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${userLoginLogList.size()>0}">
					<c:forEach  items="${userLoginLogList}" var="user" >
						<tr>
							<td>${user.userId }</td>
							<td>${user.nickname }</td>
							<td>${user.mobile }</td>
							<td>${user.email }</td>
							<td><fmt:formatDate value="${user.loginTime }" type="both"/></td>
							<td>${user.loginIp}</td>
							<td>${user.address}</td>
							<td>${user.userAgent}</td>
							<td>${user.osname}</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${userLoginLogList.size()==0||userLoginLogList==null}">
					<tr>
						<td colspan="10">
							<div data-am-alert=""
								 class="am-alert am-alert-secondary mt20 mb50">
								<center>
									<big> <i class="am-icon-frown-o vam"
											 style="font-size: 34px;"></i> <span class="vam ml10">还没有学员登陆信息！</span></big>
								</center>
							</div>
						</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
		<!-- /pageBar begin -->
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
		<!-- /pageBar end -->
	</div>
</div>
</body>
</html>

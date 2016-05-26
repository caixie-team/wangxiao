<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>学员列表</title>
<script type="text/javascript">
function clean(){
	$("#userId,#nickname,#email,#mobile").val("");
}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">部门管理</strong> / <small>学员列表</small></div>
</div>
<hr>
<form class="am-form" action="${ctx}/admin/statistics/group/user/list" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<input type="hidden" name="groupId" value="${user.groupId}"/>
	<div class="mt20">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					学员ID
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" id="userId" value="${user.id}" name="user.id" onkeyup="this.value=this.value.replace(/\D/g,'')">
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					用户名
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" id="nickname" value="${user.nickname}" name="user.nickname">
				</div>
			</div>
			<div class="mt20 clear"></div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					邮箱
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" id="email" value="${user.email}" name="user.email">
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					手机
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" id="mobile" value="${user.mobile}" name="user.mobile" onkeyup="this.value=this.value.replace(/\D/g,'')">
				</div>
			</div>
			<div class="mt20 clear"></div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					&nbsp;
				</div>
				<div class="am-u-sm-8">
					<button type="submit" class="am-btn am-btn-warning">
						<span class="am-icon-search"></span> 查询
					</button>
					<button type="button" onclick="clean()" class="am-btn am-btn-danger">清空</button>
					<button type="button" onclick="window.location.href='${ctx}/admin/statistics/group/list'" class="am-btn am-btn-success">返回</button>
				</div>
			</div>
			<div class="mt20 clear"></div>
		</div>
	</div>
	<div class="am-g">
		<div class="mt20">
			<table class="am-table am-table-striped am-table-hover table-main">
				<thead>
				<tr>
					<th class="table-title" width="10%">ID</th>
					<th class="table-title" width="18%">用户名</th>
					<th class="table-title" width="20%">邮箱</th>
					<th class="table-title" width="20%">手机号</th>
					<th class="table-title" width="15%">学习课程数</th>
					<th class="table-title" width="17%">学习时间(时长:分钟)</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${not empty list}">
					<c:forEach items="${list}" var="user" >
						<tr>
							<td>${user.id }</td>
							<td>${user.nickname }</td>
							<td>${user.email }</td>
							<td>${user.mobile }</td>
							<td>${user.courseNum}</td>
							<td>${user.studyTime }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty list}">
					<tr>
						<td colspan="6">
							<div data-am-alert=""
								 class="am-alert am-alert-secondary mt20 mb50">
								<center>
									<big> <i class="am-icon-frown-o vam"
											 style="font-size: 34px;"></i> <span class="vam ml10">还没有学员信息！</span></big>
								</center>
							</div>
						</td>
					</tr>
				</c:if>
				</tbody>
			</table>
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		</div>
	</div>
</form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>学员列表</title>
<script type="text/javascript">
function studyInfo(id){
	var i = id;
	window.location.href="${ctx}/admin/statistics/user/course/list?userId="+i;
}
function clean(){
	$("#userId,#nickname,#email,#mobile").val("");
}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">统计管理</strong> / <small>学员列表</small></div>
</div>
<hr>
<form class="am-form" action="${ctx}/admin/statistics/user/list" method="post" id="searchForm" name="searchForm">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<div class="mt20">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					ID
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" onkeyup="this.value=this.value.replace(/\D/g,'')" id="userId" value="${user.id}" name="user.id">
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
					<input type="text" class="am-input-sm" onkeyup="this.value=this.value.replace(/\D/g,'')" id="mobile" value="${user.mobile}" name="user.mobile">
				</div>
			</div>
			<div class="mt20 clear"></div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					&nbsp;
				</div>
				<div class="am-u-sm-8">
					<button type="submit" class="am-btn am-btn-warning" onclick="goPage(1)">
						<span class="am-icon-search"></span> 查询
					</button>
					<button type="button" onclick="clean()" class="am-btn am-btn-danger">清空</button>
				</div>
			</div>
			<div class="mt20 clear"></div>
		</div>
	</div>
	<div class="mt20">
		<div class="doc-example">
			<div class="am-scrollable-horizontal">
				<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
					<tr>
						<th class="table-title" width="10%">ID</th>
						<th class="table-title" width="15%">用户名</th>
						<th class="table-title" width="15%">邮箱</th>
						<th class="table-title" width="15%">手机号</th>
						<th class="table-title" width="15%">学习课程数</th>
						<th class="table-title" width="15%">学习时间(时长:分钟)</th>
						<th class="table-set" width="15%">操作</th>
					</tr>
					</thead>
					<tbody>
					<c:if test="${list.size()>0}">
						<c:forEach  items="${list}" var="user" >
							<tr>
								<td>${user.id }</td>
								<td>${user.nickname }</td>
								<td>${user.email }</td>
								<td>${user.mobile }</td>
								<td>${user.courseNum}</td>
								<td>${user.studyTime }</td>
								<td>
									<div class="am-btn-toolbar">
										<div class="am-btn-group am-btn-group-xs">
											<a class="am-btn am-btn-default am-btn-xs am-text-secondary" href="${ctx}/admin/statistics/user/course/list?userId=${user.id }"><span class="am-icon-info-circle"></span> 学习记录</a>
										</div>
									</div>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${list.size()==0||list==null}">
						<tr>
							<td colspan="16">
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
			</div>
		</div>
	</div>
</form>
<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</body>
</html>

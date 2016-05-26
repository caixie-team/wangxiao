<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>部门列表</title>
<script type="text/javascript">
	//清空
	function clean(){
		$("#id,#name").val("");
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">统计管理</strong> / <small>部门列表</small></div>
</div>
<hr>
<form class="am-form" action="${ctx}/admin/statistics/group/list" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<div class="am-tab-panel am-fade am-active am-in">
		<div class="am-g am-margin-top am-u-sm-5">
			<div class="am-u-sm-4 am-text-right">
				部门ID
			</div>
			<div class="am-u-sm-8 am-u-end">
				<input type="text" class="am-input-sm" onkeyup="value=value.replace(/[^\d]/g,'')" name="userGroup.id" value="${userGroup.id}" id="id">
			</div>
		</div>
		<div class="am-g am-margin-top am-u-sm-5">
			<div class="am-u-sm-4 am-text-right">
				部门名称
			</div>
			<div class="am-u-sm-8 am-u-end">
				<input type="text" class="am-input-sm" name="userGroup.name" value="${userGroup.name}" id="name"/>
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
			</div>
		</div>
		<div class="mt20 clear"></div>
	</div>
	<div class="am-g">
		<div class="mt20">
			<table class="am-table am-table-striped am-table-hover table-main">
				<thead>
				<tr>
					<th class="table-title" width="8%">ID</th>
					<th class="table-title" width="20%">部门名称</th>
					<th class="table-title" width="25%">部门简介</th>
					<th class="table-title" width="15%">部门学习课程数</th>
					<th class="table-title" width="17%">部门学习时长(分钟)</th>
					<th class="table-set" width="15%">操作</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${not empty userGroupList}">
					<c:forEach items="${userGroupList}" var="group">
						<tr>
							<td>${group.id}</td>
							<td>${group.name}</td>
							<td>${group.description}</td>
							<td>${group.courseNum}</td>
							<td>${group.studyTime}</td>
							<td class="c_666 czBtn" align="center">
								<div class="am-btn-toolbar">
									<div class="am-btn-group am-btn-group-xs">
										<a class="am-btn am-btn-default am-btn-xs am-text-secondary" href="${ctx}/admin/statistics/group/user/list?groupId=${group.id}"><span class="am-icon-info-circle"></span> 部门学员</a>
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty userGroupList}">
					<tr>
						<td colspan="6">
							<div data-am-alert=""
								 class="am-alert am-alert-secondary mt20 mb50">
								<center>
									<big> <i class="am-icon-frown-o vam"
											 style="font-size: 34px;"></i> <span class="vam ml10">还没有相关数据！</span></big>
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

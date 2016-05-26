<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>课程小节</title>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">课程列表</strong> / <small>课程小节</small></div>
</div>
<hr>
<div class="mt20 am-padding admin-content-list">
	<div class="am-tab-panel am-fade am-active am-in">
		<form class="am-form">
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					课程名称
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" value="${course.name}" disabled>
				</div>
			</div>
			<div class="mt20 clear"></div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
				</div>
				<div class="am-u-sm-8">
					<button type="button" class="am-btn am-btn-success" onclick="window.location.href='${ctx}/admin/statistics/course/list'">返回</button>
				</div>
			</div>
			<div class="mt20 clear"></div>
		</form>
	</div>
</div>
<div class="am-g">
	<div class="mt20">
		<form class="am-form" action="${ctx}/admin/statistics/courseKpoint/list" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<input type="hidden" name="courseKpoint.courseId" value="${course.id}"/>
		</form>
		<table class="am-table am-table-striped am-table-hover table-main">
			<thead>
			<tr>
				<th class="table-title" width="20%">ID</th>
				<th class="table-title" width="20%">节点名称</th>
				<th class="table-title" width="20%">观看次数</th>
				<th class="table-title" width="20%">观看人数</th>
				<th class="table-title" width="20%">观看时长(单位：分)</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${not empty courseKpointList}">
				<c:forEach items="${courseKpointList}" var="kpoint">
					<c:if test="${kpoint.parentId>0}">
						<tr>
							<td>${kpoint.id}</td>
							<td>${kpoint.parentName}-${kpoint.name }</td>
							<td>${kpoint.playcount}</td>
							<td>${kpoint.lookNumber}</td>
							<td>${kpoint.playTime}</td>
						</tr>
					</c:if>
					<c:if test="${kpoint.parentId<=0}">
						<tr>
							<td>${kpoint.id}</td>
							<td>${kpoint.name }</td>
							<td>${kpoint.playcount}</td>
							<td>${kpoint.lookNumber}</td>
							<td>${kpoint.playTime}</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${empty courseKpointList}">
				<tr>
					<td colspan="5">
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
</body>
</html>

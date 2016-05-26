<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>课程统计列表</title>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">讲师课程管理</strong> / <small>讲师课程统计列表</small></div>
</div>
<hr>
<form action="${ctx}/admin/teacher/selectCourseList?teacherId=${queryCourseProfile.teacherId}" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
</form>
<div class="mt20">
	<div class="doc-example">
		<div class="am-scrollable-horizontal">
			<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
				<thead>
					<th width="10%"><span>课程ID</span></th>
					<th width="10%"><span>课程名称</span></th>
					<th width="10%"><span>购买数量</span></th>
					<th width="10%"><span>查看次数</span></th>
					<th width="10%"><span>观看次数</span></th>
					<th width="10%"><span>观看人数</span></th>
					<th width="10%"><span>评论数量</span></th>
					<th width="10%"><span>问题数量</span></th>
					<th width="10%"><span>笔记数量</span></th>
				</thead>
				<tbody>
				<c:if test="${not empty list}">
					<c:forEach items="${list}" var="cf">
						<tr >
							<td>${cf.courseId }</td>
							<td>${cf.name }</td>
							<td>${cf.buycount }</td>
							<td>${cf.viewcount }</td>
							<td>${cf.playcount }</td>
							<td>${cf.wacthPersonCount }</td>
							<td>${cf.commentcount }</td>
							<td>${cf.questiongcount }</td>
							<td>${cf.notecount }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty list}">
					<tr>
						<td colspan="8">
							<div data-am-alert=""
								 class="am-alert am-alert-secondary mt20 mb50">
								<center>
									<big> <i class="am-icon-frown-o vam"
											 style="font-size: 34px;"></i> <span class="vam ml10">还没有讲师课程信息！</span></big>
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
<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>计划详情</title>
<script type="text/javascript">
	// 个人计划详情(阶段信息)
	function userPlanInfo(userId){
		window.location.href="${ctx}/admin/plan/userPlanInfo/${plan.id}/"+userId;
	}

	// 部门计划详情(阶段信息)
	function groupPlanInfo(groupId){
		window.location.href="${ctx}/admin/plan/groupPlanInfo/${plan.id}/"+groupId;
	}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">计划管理</strong> / <small>详情列表</small></div>
	</div>
	<hr>
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<form class="am-form" action="${ctx}/admin/plan/planInfo/${plan.id}" method="post" name="searchForm" id="searchForm">
				<input type="hidden" id="pageCurrentPage"  name="page.currentPage" value="${page.currentPage}"/>
			</form>
			<div class="am-g am-margin-top am-u-sm-4">
				<div class="am-u-sm-4 am-text-right">
					计划名称
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" disabled value="${plan.name}"/>
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4">
				<div class="am-u-sm-4 am-text-right">
					计划类型
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" disabled value="<c:if test="${plan.type==0}">个人计划</c:if><c:if test="${plan.type==1}">岗位计划</c:if>"/>
				</div>
			</div>
			<div class="am-g am-margin-top  am-u-sm-4">
				<div class="am-u-sm-4 am-text-right">
					计划时长
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" disabled value="${plan.totalTime}"/>
				</div>
			</div>
			<div class="mt20 clear"></div>
			<div class="am-g am-margin-top  am-u-sm-4">
				<div class="am-u-sm-4 am-text-right">
					&nbsp;
				</div>
				<div class="am-u-sm-8 am-u-end">
					<a class="am-btn am-btn-warning" href="${ctx}/admin/plan/planList">返回</a>
				</div>
			</div>
		</div>

		<div class="am-g">
			<div class="mt20">
				<%-- 个人计划 --%>
				<c:if test="${plan.type==0}">
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
						<tr>
							<th class="table-title" width="14%">计划ID</th>
							<th class="table-title" width="14%">用户ID</th>
							<th class="table-title" width="14%">用户名</th>
							<th class="table-title" width="14%">计划时长(分钟)</th>
							<th class="table-title" width="14%">完成时长(分钟)</th>
							<th class="table-title" width="15%">完成进度</th>
							<th class="table-title" width="15%">操作</th>
						</tr>
						</thead>
						<tbody>
						<c:if test="${not empty recordList }">
							<c:forEach items="${recordList }" var="record">
								<tr>
									<td>${record.id }</td>
									<td>${record.userId }</td>
									<td>${record.userName }</td>
									<td>${record.totalTime}</td>
									<td>${record.completeTime }</td>
									<td><fmt:formatNumber value="${record.progressPercentage }" type="percent"/></td>
									<td>
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="userPlanInfo(${record.userId})">
													<span class="am-icon-list"></span> 详细
												</button>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty recordList}">
							<tr>
								<td colspan="7">
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
				</c:if>
				<%-- 岗位计划 --%>
				<c:if test="${plan.type==1}">
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
						<tr>
							<th class="table-title" width="8%">计划ID</th>
							<th class="table-title" width="8%">部门ID</th>
							<th class="table-title" width="18%">部门名称</th>
							<th class="table-title" width="14%">计划时长(分钟)</th>
							<th class="table-title" width="16%">平均完成时长(分钟)</th>
							<th class="table-title" width="12%">人数</th>
							<th class="table-title" width="10%">完成进度</th>
							<th class="table-title" width="14%">操作</th>
						</tr>
						</thead>
						<tbody>
						<c:if test="${not empty planGroupList }">
							<c:forEach items="${planGroupList }" var="planGroup">
								<tr>
									<td>${planGroup.id }</td>
									<td>${planGroup.userGroupId }</td>
									<td>${planGroup.groupName }</td>
									<td>${planGroup.totalTime}</td>
									<td>${planGroup.averageComplete }</td>
									<td>${planGroup.peopleNum }</td>
									<td><fmt:formatNumber value="${planGroup.progressPercentage }" type="percent"/></td>
									<td>
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="groupPlanInfo(${planGroup.userGroupId})">
													<span class="am-icon-list"></span> 详细
												</button>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty planGroupList}">
							<tr>
								<td colspan="7">
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
				</c:if>
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			</div>
		</div>
	</div>
</body>
</html>

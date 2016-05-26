<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>计划详情</title>
<script type="text/javascript">
	function goBack(){
		window.history.go(-1);
	}

	// 阶段信息
	function userPlanInfo(id){
		window.location.href="${ctx}/admin/plan/userPlanInfo/${plan.id}/"+id;
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
			<form class="am-form" action="${ctx}/admin/plan/groupPlanInfo/${plan.id}/${groupId}" method="post" name="searchForm" id="searchForm">
				<input type="hidden" id="pageCurrentPage"  name="page.currentPage" value="${page.currentPage}"/>
			</form>
		</div>
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
				<input type="text" class="am-input-sm" disabled value="部门计划"/>
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
				<a class="am-btn am-btn-warning" onclick="goBack()">返回</a>
			</div>
		</div>
	</div>
	<div class="am-g">
		<div class="mt20">
			<table class="am-table am-table-striped am-table-hover table-main">
				<thead>
				<tr>
					<th class="table-title" width="10%">用户ID</th>
					<th class="table-title" width="18%">用户名</th>
					<th class="table-title" width="18%">计划时长(分钟)</th>
					<th class="table-title" width="18%">完成时长(分钟)</th>
					<th class="table-title" width="18%">完成进度</th>
					<th class="table-title" width="18%">操作</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${not empty planUserList }">
					<c:forEach items="${planUserList }" var="planUser">
						<tr>
							<td>${planUser.userId }</td>
							<td>${planUser.userName }</td>
							<td>${planUser.totalTime}</td>
							<td>${planUser.complete }</td>
							<td><fmt:formatNumber value="${planUser.progressPercentage }" type="percent"/></td>
							<td>
								<div class="am-btn-toolbar">
									<div class="am-btn-group am-btn-group-xs">
										<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="userPlanInfo(${planUser.userId})">
											<span class="am-icon-list"></span> 详细
										</button>
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				</tbody>
			</table>
		</div>
	</div>
	<div>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
</body>
</html>

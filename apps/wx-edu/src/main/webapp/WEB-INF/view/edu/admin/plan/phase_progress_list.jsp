<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>阶段列表</title>
<script type="text/javascript">
	$(function(){
		$(".am-tabs-nav").find("a").eq(0).click();
	});
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">计划管理</strong> / <small>阶段进度详细</small></div>
	</div>
	<hr>
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<form class="am-form">
				<div class="am-g am-margin-top am-u-sm-4">
					<div class="am-u-sm-4 am-text-right">
						计划名称
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="plan.name" value="${plan.name}" disabled>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4">
					<div class="am-u-sm-4 am-text-right">
						用户名
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="plan.name" value="${nickname}" disabled>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4">
					<div class="am-u-sm-4 am-text-right">
						&nbsp;
					</div>
					<div class="am-u-sm-8 am-u-end">
						<a class="am-btn am-btn-warning" href="${ctx}/admin/plan/planInfo/${plan.id}">返回</a>
					</div>
				</div>
				<div class="mt20 clear"></div>
			</form>
		</div>
	</div>
	<div class="mt20">
		<c:if test="${not empty phaseList}">
		<div data-am-tabs="" class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<c:forEach items="${phaseList}" var="phase" varStatus="index">
					<li class="<c:if test="${index.index==0}">am-active</c:if>"><a href="#tab${index.index}">${index.index+1}.${phase.phaseName}</a></li>
				</c:forEach>
			</ul>
			<div class="am-tabs-bd">
				<c:forEach items="${phaseList}" var="phase" varStatus="index">
					<div id="tab${index.index}" class="am-tab-panel am-fade <c:if test="${index.index==0}">am-active</c:if> am-in">
						<span>
							总学时(分钟):&nbsp;&nbsp;<font color="red">${phase.studyTimeNo}</font>&nbsp;&nbsp;&nbsp;&nbsp;
							完成学时(分钟):&nbsp;&nbsp;<font color="red">${phase.complete}</font>&nbsp;&nbsp;&nbsp;&nbsp;
							完成进度:&nbsp;&nbsp;<font color="red"><fmt:formatNumber value="${phase.progressPercentage}" type="percent"/></font>
						</span>
						<div class="am-g">
							<div class="mt20">
								<table class="am-table am-table-striped am-table-hover table-main">
									<thead>
									<tr>
										<th class="table-title" width="8%">ID</th>
										<th class="table-title" width="8%">阶段ID</th>
										<th class="table-title" width="8%">类型</th>
										<th class="table-title" width="34%">名称</th>
										<th class="table-title" width="15%">总学时(分钟)</th>
										<th class="table-title" width="15%">完成学时(分钟)</th>
										<th class="table-title" width="12%">完成进度</th>
									</tr>
									</thead>
									<tbody>
									<c:if test="${not empty phase.phaseDetailList}">
										<c:forEach items="${phase.phaseDetailList}" var="phaseDetail" varStatus="index">
											<tr>
												<td>
														${phaseDetail.id }
												</td>
												<td>
														${phaseDetail.phaseId }
												</td>
												<td>
														${phaseDetail.otherId }
												</td>
												<td>
														${phaseDetail.detailName }
												</td>
												<td>
														${phaseDetail.hours }
												</td>
												<td>
														${phaseDetail.averageComplete }
												</td>
												<td>
													<fmt:formatNumber value="${phaseDetail.progressPercentage }" type="percent"/>
												</td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${empty phase.phaseDetailList}">
										<tr>
											<td colspan="9">
												<div data-am-alert=""
													 class="am-alert am-alert-secondary mt20 mb50">
													<center>
														<big> <i class="am-icon-frown-o vam"
																 style="font-size: 34px;"></i> <span class="vam ml10">还没有阶段详细进度！</span></big>
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
				</c:forEach>
			</div>
		</div>
		</c:if>
		<c:if test="${empty phaseList}">

		</c:if>
	</div>
</body>
</html>

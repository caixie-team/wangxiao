<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>阶段列表</title>
<script type="text/javascript">
	function goBack(){
		window.history.go(-1);
	}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">计划管理</strong> / <small>阶段进度详细</small></div>
	</div>
	<hr>
	<div class="am-g">
		<button class="am-btn am-btn-warning" onclick="goBack()">返回</button>
		<div class="mt20">
			<table class="am-table am-table-striped am-table-hover table-main">
				<thead>
				<tr>
					<th class="table-title" width="6%">ID</th>
					<th class="table-title" width="8%">阶段ID</th>
					<th class="table-title" width="8%">类型</th>
					<th class="table-title" width="34%">名称</th>
					<th class="table-title" width="8%">学时</th>
					<th class="table-title" width="14%">平均完成学时</th>
					<th class="table-title" width="10%">人数</th>
					<th class="table-title" width="12%">完成进度</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${not empty phaseDetailList }">
					<c:forEach items="${phaseDetailList }" var="phaseDetail">
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
									${phaseDetail.peopleNum }
							</td>
							<td>
								<fmt:formatNumber value="${phaseDetail.progressPercentage }" type="percent"/>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty phaseDetailList}">
					<tr>
						<td colspan="7">
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
</body>
</html>

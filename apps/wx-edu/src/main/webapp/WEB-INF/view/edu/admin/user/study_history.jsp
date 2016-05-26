<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>学员学习情况</title>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">学员管理</strong> / <small>学员学习情况</small></div>
</div>
<hr>
<div class="am-g">
	<div class="mt20">
		<button class="am-btn am-btn-success" onclick="window.history.go(-1)">返回</button>
		<form class="am-form">
			<table class="am-table am-table-striped am-table-hover table-main">
				<thead>
				<tr>
					<th class="table-title" width="25%">课程名称</th>
					<th class="table-title" width="25%">节点名称</th>
					<th class="table-title" width="25%">观看次数</th>
					<th class="table-title" width="25%">最后观看时间</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${list.size()>0}">
					<c:forEach items="${list}" var="studyHistory">
						<tr>
							<td>${studyHistory.courseName}</td>
							<td>${studyHistory.kpointName}</td>
							<td>${studyHistory.playercount}</td>
							<td><fmt:formatDate
									value="${studyHistory.updateTime}"
									pattern="yyyy/MM/dd  HH:mm:ss" /></td>
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
											 style="font-size: 34px;"></i> <span class="vam ml10">还没有学习信息！</span></big>
								</center>
							</div>
						</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</form>
	</div>
</div>
</body>
</html>

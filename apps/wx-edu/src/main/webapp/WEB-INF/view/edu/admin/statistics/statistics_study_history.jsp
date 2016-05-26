<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>学员学习情况</title>
<script type="text/javascript">
	function goback(){
		window.location.href='http://127.0.0.1/admin/statistics/course/history?userId='+$("#userId").val()+'&courseId='+$("#courseId").val();
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">课程详情</strong> / <small>学习记录</small></div>
</div>
<hr>
<div class="mt20">
	<div class="am-tab-panel am-fade am-active am-in">
		<form class="am-form">
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					&nbsp;
				</div>
				<div class="am-u-sm-8">
					<button type="button" onclick="window.location.href='${ctx}/admin/statistics/user/course/list?userId=${courseStudyhistory.userId}'" class="am-btn am-btn-success">返回</button>
				</div>
			</div>
			<div class="mt20 clear"></div>
		</form>
	</div>
</div>
<div class="am-g">
	<div class="mt20">
		<form class="am-form" action="${ctx}/admin/statistics/course/history" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<input type="hidden" name="courseStudyhistory.userId" value="${courseStudyhistory.userId}" id="userId"/>
			<input type="hidden" name="courseStudyhistory.courseId" value="${courseStudyhistory.courseId}" id="courseId"/>
		</form>
		<table class="am-table am-table-striped am-table-hover table-main">
			<thead>
			<tr>
				<th class="table-title" width="20%">课程名称</th>
				<th class="table-title" width="20%">节点名称</th>
				<th class="table-title" width="20%">观看次数</th>
				<th class="table-title" width="20%">观看时长(单位:分)</th>
				<th class="table-title" width="20%">最后观看时间</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${list.size()>0}">
				<c:forEach items="${list}" var="studyHistory">
					<tr>
						<td>${studyHistory.courseName}</td>
						<td>
							<c:if test="${not empty studyHistory.parentKpointName}">
								${studyHistory.parentKpointName} - ${studyHistory.kpointName}
							</c:if>
							<c:if test="${empty studyHistory.parentKpointName}">
								${studyHistory.kpointName}
							</c:if>
						</td>
						<td>${studyHistory.playercount}</td>
						<td>${studyHistory.playTime}</td>
						<td><fmt:formatDate value="${studyHistory.updateTime}" pattern="yyyy/MM/dd  HH:mm:ss" /></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${list.size()==0||list==null}">
				<tr>
					<td colspan="5">
						<div data-am-alert="" class="am-alert am-alert-secondary mt20 mb50">
							<center>
								<big>
									<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
									<span class="vam ml10">还没有相关数据！</span>
								</big>
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

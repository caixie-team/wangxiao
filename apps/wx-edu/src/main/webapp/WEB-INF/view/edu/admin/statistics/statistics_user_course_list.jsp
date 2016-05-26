<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程列表</title>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">学习记录</strong> / <small>课程列表</small></div>
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
					<button type="button" onclick="window.location.href='${ctx}/admin/statistics/user/list'" class="am-btn am-btn-success">返回</button>
				</div>
			</div>
			<div class="mt20 clear"></div>
		</form>
	</div>
</div>
<div class="am-g">
	<div class="mt20">
		<form class="am-form" action="${ctx}/admin/statistics/user/course/list" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<input type="hidden" id="userId" name="queryCourse.userId" value="${queryCourse.userId }"/>
		</form>
		<table class="am-table am-table-striped am-table-hover table-main">
			<thead>
			<tr>
				<th class="table-title" width="8%">ID</th>
				<th class="table-title" width="20%">课程名称</th>
				<th class="table-title" width="11%">售卖形式</th>
				<th class="table-title" width="10%">上下架</th>
				<th class="table-title" width="10%">价格</th>
				<th class="table-title" width="10%">课程节数</th>
				<th class="table-title" width="10%">学习节数</th>
				<th class="table-title" width="10%">学习进度</th>
				<th class="table-set" width="11%">操作</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${courseList.size()>0}">
				<c:forEach items="${courseList}" var="cou">
					<tr>
						<td>${cou.id}</td>
						<td><a target="_blank" href="${ctx}/front/couinfo/${cou.id }">${cou.name }</a></td>
						<td>
							<c:if test="${cou.sellType=='COURSE'}">课程</c:if>
							<c:if test="${cou.sellType=='PACKAGE'}">套餐</c:if>
							<c:if test="${cou.sellType=='LIVE'}">直播</c:if>
						</td>
						<td>
							<c:if test="${cou.isavaliable==0}">上架</c:if>
							<c:if test="${cou.isavaliable==1}">下架</c:if>
						</td>
						<td>${cou.currentprice}</td>
						<td>${cou.lessionnum}</td>
						<td>${cou.studyhistoryNum}</td>
						<td>
							<c:if test="${cou.lessionnum<=0}">
								0%
							</c:if>
							<c:if test="${cou.lessionnum>0}">
								<fmt:formatNumber type="percent" value="${cou.studyhistoryNum/cou.lessionnum}"/>
							</c:if>
						</td>
						<td>
							<div class="am-btn-toolbar">
								<div class="am-btn-group am-btn-group-xs">
									<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="window.location.href='${ctx}/admin/statistics/course/history?userId=${queryCourse.userId}&courseId=${cou.id}'"><span class="am-icon-info-circle"></span> 课程详情</button>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${courseList.size()==0||courseList==null}">
				<tr>
					<td colspan="9">
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

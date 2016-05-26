<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>学员学习情况</title>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">学员管理</strong> / <small>学员信息</small></div>
</div>
<hr>
<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="#tab1">学员信息</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<a class="am-btn am-btn-warning" href="${ctx}/admin/user/courseList?queryCourse.userId=${user.id}">赠送课程</a>
				<a class="am-btn am-btn-warning" href="${ctx}/admin/user/toCouponCodes/${user.id}">赠送优惠券</a>
				<a class="am-btn am-btn-success" href="javascript:history.go(-1)">返回</a>
				<form class="am-form">
					<fieldset disabled>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								学员ID
							</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="${user.id }" class="am-input-sm">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								学员名称
							</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="${user.nickname }" class="am-input-sm">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								学员邮箱
							</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="${user.email }" class="am-input-sm">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								手机号
							</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="${user.mobile }" class="am-input-sm">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
					</fieldset>
				</form>

				<a class="am-btn am-btn-warning" href="${ctx}/admin/user/studyhistory/${user.id}">查看更多</a>

				<div class="am-g">
					<div class="mt20 am-scrollable-horizontal">
						<div class="mt10">
							<table class="am-table am-table-bd am-table-striped admin-content-table">
								<thead>
								<tr>
									<th class="table-title" width="25%">课程名称</th>
									<th class="table-title" width="25%">观看次数</th>
									<th class="table-title" width="25%">售卖形式</th>
									<th class="table-title" width="25%">最后观看时间</th>
								</tr>
								</thead>
								<tbody>
								<c:if test="${not empty studyHistoryList }">
									<c:forEach items="${studyHistoryList}" var="studyHistory">
										<tr>
											<td>${studyHistory.courseName}</td>
											<td>${studyHistory.kpointName}</td>
											<td>${studyHistory.playercount}</td>
											<td><fmt:formatDate value="${studyHistory.updateTime}"
																pattern="yyyy/MM/dd  HH:mm:ss" /></td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty studyHistoryList}">
									<tr>
										<td colspan="4">
											<div data-am-alert=""
												 class="am-alert am-alert-secondary mt20 mb50">
												<center>
													<big> <i class="am-icon-frown-o vam"
															 style="font-size: 34px;"></i> <span class="vam ml10">还没相关数据！</span></big>
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

				<a class="am-btn am-btn-warning" href="${ctxexam}/admin/paper/toPaperRecord?queryPaperRecord.cusId=${user.id}">查看更多</a>
				<div class="am-g">
					<div class="mt20 am-scrollable-horizontal">
						<div class="mt10">
							<table class="am-table am-table-bd am-table-striped admin-content-table">
								<thead>
								<tr>
									<th class="table-title" width="9%">ID</th>
									<th class="table-title" width="9%">试卷名</th>
									<th class="table-title" width="9%">用户邮箱</th>
									<th class="table-title" width="9%">分数</th>

									<th class="table-title" width="9%">正确题数</th>
									<th class="table-title" width="9%">总题数</th>
									<th class="table-title" width="9%">答题时间</th>

									<th class="table-title" width="9%">交卷时间</th>
									<th class="table-title" width="9%">正确率</th>
									<th class="table-title" width="9%">试卷类型</th>
									<th class="table-title" width="10%">操作</th>
								</tr>
								</thead>
								<tbody>
								<c:if test="${not empty result.entity.paperRecordList}">
									<c:forEach items="${result.entity.paperRecordList}" var="paperRecord">
										<tr>
											<td>${fn:substringBefore(paperRecord.id,'.')}</td>
											<td>${paperRecord.paperName}</td>
											<td>${paperRecord.email}</td>
											<td>${paperRecord.userScore}</td>
											<td>${fn:substringBefore(paperRecord.correctNum,'.')}</td>
											<td>${fn:substringBefore(paperRecord.qstCount,'.')}</td>
											<td>${fn:substringBefore(paperRecord.testTime,'.')}</td>
											<td>${paperRecord.addTime}</td>
											<td>${paperRecord.fmtAccuracy}</td>
											<td>
												<c:if test="${paperRecord.type==1 }">
													随机
												</c:if>
												<c:if test="${paperRecord.type==2 }">
													试题组卷
												</c:if>
											</td>
											<td>
												<a class="am-btn am-btn-link" href="${ctxexam }/admin/paper/toPaperRecordInfo?id=${fn:substringBefore(paperRecord.id,'.')}&cusId=${paperRecord.cusId }">查看</a>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty result.entity.paperRecordList}}">
									<tr>
										<td colspan="11">
											<div data-am-alert=""
												 class="am-alert am-alert-secondary mt20 mb50">
												<center>
													<big> <i class="am-icon-frown-o vam"
															 style="font-size: 34px;"></i> <span class="vam ml10">还没相关数据！</span></big>
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
			</div>
		</div>
	</div>
</div>
</body>
</html>

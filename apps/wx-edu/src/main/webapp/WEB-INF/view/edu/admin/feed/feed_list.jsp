<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>反馈列表</title>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}" charset="UTF-8"></script>
	<script type="text/javascript">
		function cancel(){
			$("input[name='userFeedbacks.startDate']:first").attr("value", "");
			$("input[name='userFeedbacks.endDate']:first").attr("value", "");
			$( "#name" ).val('');
			$( "#email" ).val('');
			$( "#mobile" ).val('');
			$( "#qq" ).val('');
		}
	</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">系统</strong> / <small>反馈列表</small>
		</div>
	</div>
	<hr/>
	<form  action="${ctx}/admin/feed/feedList" name="searchForm" id="searchForm" method="post"  class="am-form">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
		<div class="mt20 am-padding admin-content-list">
			<div class="am-tab-panel am-fade am-active am-in">
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						开始时间
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" name="userFeedbacks.startDate" readonly="readonly" data-am-datepicker="{format: 'yyyy-mm-dd'}" value="${userFeedbacks.startDate}" id="startDate" class="am-input-sm" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						结束时间
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" name="userFeedbacks.endDate" readonly="readonly" data-am-datepicker="{format: 'yyyy-mm-dd'}" value="${userFeedbacks.endDate }" id="endDate" class="am-input-sm" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						姓名查询
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" id="name" name="userFeedbacks.name" value="${userFeedbacks.name}" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						邮箱
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="userFeedbacks.email" value="${userFeedbacks.email }" id="email" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						手机号
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" onkeyup="this.value=this.value.replace(/\D/g,'')" id="mobile" name="userFeedbacks.mobile" value="${userFeedbacks.mobile }" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						QQ
					</div>
					<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" id="qq" onkeyup="this.value=this.value.replace(/\D/g,'')" name="userFeedbacks.qq" value="${userFeedbacks.qq }" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						&nbsp;
					</div>
						&nbsp;
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						&nbsp;
					</div>
						&nbsp;
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						&nbsp;
					</div>
					<div class="am-u-sm-8 am-u-end">
					<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">
						<span class="am-icon-search"></span> 搜索
					</button>
					<input class="am-btn am-btn-danger" type="button" onclick="cancel()" value="清空"/>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="mt20"></div>
		<div class="mt20">
			<div class="doc-example">
				<div class="am-scrollable-horizontal">
					<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
						<thead>
							<tr>
								<th><span>ID</span></th>
								<th><span>反馈信息</span></th>
								<th><span>反馈时间</span></th>
								<th><span>电子邮件</span></th>
								<th><span>手机号码</span></th>
								<th><span>QQ</span></th>
								<th><span>姓名</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="left">
							<c:if test="${not empty userFeedbackList }">
								<c:forEach items="${userFeedbackList}" var="feed">
									<tr>
										<td>${feed.id}</td>
										<td>${feed.content}</td>
										<td><fmt:formatDate value="${feed.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${feed.email}</td>
										<td>${feed.mobile}</td>
										<td>${feed.qq}</td>
										<td>${feed.name}</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty userFeedbackList }">
								<tr>
									<td align="center" colspan="16">
										<div class="am-alert am-alert-secondary mt20 mb50" data-am-alert="">
											<center>
											<big>
												<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
													<span class="vam ml10">还没有反馈信息！</span>
											</big>
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
	</form>
	<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</body>
</html>

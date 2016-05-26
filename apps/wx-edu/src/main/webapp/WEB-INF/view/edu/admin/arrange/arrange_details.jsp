<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>考试情况</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}" charset="UTF-8"></script>
<script type="text/javascript">
	function showExam(examRecordId){
		window.open('${ctxexam}/paper/getExamPaperReport/'+examRecordId);
	}
	function clearSearch(){
		$("#nickname").val("");
		$("#email").val("");
		//select清空
		$("#isCompleteStatus").val("");
		$("#isCompleteStatus").find('option').eq(0).attr('selected', true);
	}
	$(function(){
		$("#isCompleteStatus").val("${arrangeRecord.isComplete}");
	})
	function goBack(){
		window.location.href="${ctx}/admin/arrange/arrangeList";
	}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">安排考试管理</strong> / <small>${arrange.name}-详情</small></div>
	</div>
	<hr/>
	<div class="mt20 am-padding admin-content-list">
		<form action="${ctx}/admin/arrange/arrangeDetails?arrangeId=${arrange.id}" class="am-form" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					 用户名
				</div>
				<div class="am-u-sm-8 am-u-end">
				  <input type="text"class="am-input-sm"  name="arrangeRecord.nickname" value="${arrangeRecord.nickname }" id="nickname" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					 状态
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select name="arrangeRecord.isComplete" id="isCompleteStatus" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="">请选择</option>
						<option value="0">未开始</option>
						<option value="1">已完成</option>
					</select>
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					 邮箱
				</div>
				<div class="am-u-sm-8 am-u-end">
				  <input type="text"class="am-input-sm"  name="arrangeRecord.email" value="${arrangeRecord.email }" id="email" />
				</div>
			</div>
			<div class="mt20 clear"></div>

			<div class="am-g am-margin-top am-u-sm-8 am-text-left">
				&nbsp;
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">
					<span class="am-icon-search"></span> 搜索
				</button>
				<button type="button" class="am-btn am-btn-danger" onclick="clearSearch()">
					清空
				</button>
				<button type="button" class="am-btn am-btn-success" onclick="goBack()">
					返回
				</button>
			</div>
			<div class="mt20 clear"></div>
		 </div>
		 </form>
	</div>
	<div class="am-g">
		<div class="mt20">
			<table class="am-table am-table-striped am-table-hover table-main">
				<thead>
					<tr>
						<th>用户名</th>
						<th>邮箱</th>
						<th>试卷名称</th>
						<th>交卷时间</th>
						<th>得分</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${not empty arrangeRecordList}">
					<c:forEach items="${arrangeRecordList}" var="arrangeRecord">
						<tr>
							<td>${arrangeRecord.nickname }</td>
							<td>${arrangeRecord.email }</td>
							<td>${arrangeRecord.exampaperName }</td>
							<td><fmt:formatDate value="${arrangeRecord.submitTime }" pattern="yyy-MM-dd HH:mm" /></td>
							<td>${arrangeRecord.score }</td>
							<td>
								<c:if test="${arrangeRecord.isComplete==0}">未开始</c:if>
								<c:if test="${arrangeRecord.isComplete==1}">已完成</c:if>
							</td>
							<td>
								<c:if test="${arrangeRecord.isComplete==1}">
									<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="showExam(${arrangeRecord.examRecordId})"><span class="am-icon-pencil-square-o"></span> 查看试卷</button>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty arrangeRecordList }">
					<tr>
						<td colspan="16">
							<div data-am-alert="" class="am-alert am-alert-secondary mt20 mb50">
								<center><big>
									<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
									<span class="vam ml10">还没有考试信息！</span>
								</big></center>
							</div>
						</td>
					</tr>
				</c:if>
				</tbody>
			</table>
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
		</div>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>积分记录管理</title>

<script type="text/javascript">
function selectIntegral(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">积分管理</strong> / <small>积分记录管理</small>
		</div>
	</div>
	<hr/>
	<form action="${ctx}/admin/user/integralrecordlist/${userId}" method="post" id="searchForm">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
</form>

<div class="mt20">
<div class="mt10 clearfix">
<button class="am-btn am-btn-primary am-dropdown-toggle" onclick="window.location.href='${ctx}/admin/user/integralist'">
<span class="am-icon-mail-reply"></span>
返回
</button>
</div>
<table class="am-table am-table-striped am-table-hover table-main">
					<thead>
						<tr>
							<th style="text-align:center;"><span>ID</span></th>
							<th style="text-align:center;"><span>用户名</span></th>
							<th style="text-align:center;"><span>用户邮箱</span></th>
							<th style="text-align:center;"><span>积分类型</span></th>
							<th style="text-align:center;"><span>分数</span></th>
							<th style="text-align:center;"><span>时间</span></th>
							<th style="text-align:center;"><span>当前总分数</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="left">
						<c:forEach items="${userIntegralRecordList}" var="igRecord">
							<tr style="text-align:center;">
								<td>${igRecord.id}</td>
								<td>${igRecord.nickname}</td>
								<td>${igRecord.email}</td>
								<td>${igRecord.templateName}</td>
								<td>${igRecord.score}</td>
								<td><fmt:formatDate value="${igRecord.createTime }"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${igRecord.currentScore }</td>
							</tr>
						</c:forEach>
						<c:if test="${userIntegralRecordList.size()==0||userIntegralRecordList==null}">
							<tr>
								<td align="center" colspan="16">
								<div class="am-alert am-alert-secondary mt20 mb50" data-am-alert="">
								<center>
								<big>
									<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
										<span class="vam ml10">还没有用户积分信息！</span>
								</big>
								</center>
								</div>
							    </td>
							</tr>
							</c:if>
					</tbody>
</table>
	<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
<!-- /pageBar end -->
</div>
</body>
</html>
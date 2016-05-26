<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的积分</title>
</head>
<h5 class="c-333 fsize14 f-bold">获取积分记录列表</h5>
<c:if test="${empty userIntegralRecordList }">
	<div class="mt40">
		<!-- /无数据提示 开始-->
		<section class="no-data-wrap">
			<em class="no-data-ico">&nbsp;</em> <span
				class="c-666 fsize14 ml10 vam">您还没有兑换记录</span>
		</section>
		<!-- /无数据提示 结束-->
	</div>
</c:if>

<c:if test="${not empty userIntegralRecordList }">
<table class="tab-integral mt10" width="100%" cellspacing="0" cellpadding="0" border="0">
<thead>
	<tr>
		<th width="30%">操作得分</th>
		<th width="30%">兑换礼品</th>
		<th width="20%">时间</th>
		<th width="20%">得分</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${userIntegralRecordList}" var="itegral">
	<tr>
		<td align="center">${itegral.templateName}</td>
		<td align="center">${itegral.giftName}</td>
		<td align="center"><fmt:formatDate
						value="${itegral.createTime}" type="both" /></td>
		<td align="center">${itegral.score}</td>
	</tr>
</c:forEach>
</tbody>
</table>
<section class="mt5 mb5 tac">
	<jsp:include page="/WEB-INF/view/common/u_ajaxpage.jsp"></jsp:include>
</section>
</c:if>
</body>
</html>
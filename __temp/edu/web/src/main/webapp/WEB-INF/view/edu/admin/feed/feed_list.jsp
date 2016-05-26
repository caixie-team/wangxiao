<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>反馈列表</title>
</head>
<body>
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em>&nbsp;<span>系统</span> &gt; <span>反馈列表</span>
		</h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/feed/feedList" name="searchForm" id="searchForm" method="post">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
				<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
					<caption>
					</caption>
					<thead>
						<tr>
							<th width="5%"><span>ID</span></th>
							<th><span>反馈信息</span></th>
							<th><span>电子邮件</span></th>
							<th><span>手机号码</span></th>
							<th><span>QQ</span></th>
							<th><span>姓名</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:if test="${userFeedbackList.size()>0}">
							<c:forEach items="${userFeedbackList}" var="feed">
								<tr>
								<td>${feed.id}</td>
								<td>${feed.content}</td>
								<td>${feed.email}</td>
								<td>${feed.mobile}</td>
								<td>${feed.qq}</td>
								<td>${feed.name}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${userFeedbackList.size()==0||userFeedbackList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
										<span>还没有反馈信息！</span>
									</div>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</form>
			<!-- /pageBar begin -->
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			<!-- /pageBar end -->
		</div>
		<!-- /commonWrap -->
	</div>

</body>
</html>

<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>我的会员</title>
</head>
<body>
	<div class="">
		<article>
			<header class="uc-com-title">
				<span>我的会员</span>
			</header>
			<div class="i-box">
				<section>
					<div class="">
					<c:if test="${empty memberRecords}">
					<section class="mt10">
						<h5 class="c-red fsize14 f-bold">您还未开通会员</h5>
						</section>
					</c:if>
					<c:if test="${not empty memberRecords}">
						<section class="mt10">
							<h5 class="c-red fsize14 f-bold">您好，您已经开通<c:forEach var="memberRecord" items="${memberRecords}">${memberRecord.memberTitle}。</c:forEach></h5>
						</section>
					</c:if>
					
						<section class="mt30 pb20 line2">
							<h4 class="hLh30">
								<a class="ml20 jihu-btn" title="开通会员" href="${ctx}/member/alltype/1">开通会员</a>
							</h4>
						</section>
						<section class="mt15">
							<h5 class="c-333 fsize14 f-bold">开通会员有什么用途？</h5>
							<p class="c-666 mt5">开通会员后可以免费观看会员权限的课程</p>
						</section>
						<section class="mt30">
							<h5 class="c-333 fsize14 line2 pb10 f-bold">会员开通记录</h5>
						
				<%-- 		<c:if test="${empty memberRecords}">
							<section class="mt10">
							<h5 class="c-red fsize14 f-bold">您还未开通会员</h5>
							</section>
						</c:if> --%>
						<c:if test="${!empty memberRecords}">
							<table width="100%" cellspacing="0" cellpadding="0" border="0"
								class=" tab-integral mt10">
								<thead>
									<tr>
										<th width="">会员类型</th>
										<th width="">首次开通时间</th>
										<th width="">会员到期时间</th>
									</tr>
								</thead>
								<tbody class="tac">
								<c:forEach var="memberRecord" items="${memberRecords}">
									<tr>
										<td>${memberRecord.memberTitle}</td>
										<td><fmt:formatDate value="${memberRecord.beginDate}" type="both" pattern="yyyy/MM/dd  HH:mm:ss" /></td>
										<td><fmt:formatDate value="${memberRecord.endDate}" type="both" pattern="yyyy/MM/dd  HH:mm:ss" /></td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							</c:if>
							<c:if test="${empty memberRecords}">
							<div class="mt40">
								<!-- /无数据提示 开始-->
								<section class="no-data-wrap">
									<em class="no-data-ico cTipsIco">&nbsp;</em> <span
										class="c-666 fsize14 ml10 vam">您还未开通会员</span>
								</section>
								<!-- /无数据提示 结束-->
							</div>
							</c:if>
						</section>
					</div>
				</section>
			</div>
		</article>
	</div>
</body>
</html>
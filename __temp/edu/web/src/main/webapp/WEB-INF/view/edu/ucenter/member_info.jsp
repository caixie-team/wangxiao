<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的会员信息</title>
</head>
<body>
	<article class="u-m-c-w837 u-m-center">
		<section class="u-m-c-wrap">
			<!-- /u-m-c-head -->
			<section class="u-m-c-head">
				<ul class="fl u-m-c-h-txt">
					<li class="current"><a href="#" title="我的会员管理">我的会员管理</a></li>
				</ul>
				<div class="clear"></div>
			</section>
			<!-- /u-m-c-head -->
			<section class="line1">
				<div class="pl20 pr15">
					<section class="mt15">
					<%-- <c:if test="${empty memberRecords}">
						<h5 class="c-red fsize14">您还未开通会员</h5>
					</c:if> --%>
					<c:if test="${!empty memberRecords}">
					<h5 class="c-red fsize14">您好，您已经开通<c:forEach var="memberRecord" items="${memberRecords}">${memberRecord.memberTitle}。</c:forEach></h5>
					</c:if>
						<!-- <p class="c-666 mt5">开通会员后可以免费观看会员权限的课程</p> -->
					</section>
					<section class="mt40 pb20 line2">
					<!-- <h4 class="hLh30"></h4> -->
						<h4 class="hLh30">
							<a href="${ctx}/member/alltype/1" title="开通会员" class="ml20 jihu-btn">开通会员</a>
						</h4>
					</section>
					<section class="mt15">
						<h5 class="c-333 fsize14">开通会员有什么用途？</h5>
						<p class="c-666 mt5">开通会员后可以免费观看会员权限的课程</p>
					</section>
					<section class="mt30">
						<h5 class="c-333 fsize14 line2 pb10">会员开通记录</h5>
						<c:if test="${empty memberRecords}">
							<section class="comm-tips-1">
								<p>
									<em class="vam c-tips-1">&nbsp;</em> <font class="c-999 fsize12 vam">您还未开通会员</font>
								</p>
							</section>
						</c:if>
						<c:if test="${!empty memberRecords}">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" class="u-c-card tab-integral mt10">
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
					</section>
				</div>
			</section>
		</section>
	</article>
</body>
</html>

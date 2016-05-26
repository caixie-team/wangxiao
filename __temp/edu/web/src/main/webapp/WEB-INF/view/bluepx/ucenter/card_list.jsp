<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>课程卡激活</title>
 <script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/u_card.js"></script>
</head>
<body>
	<article class="u-m-c-w837 u-m-center">
		<section class="u-m-c-wrap">
			<!-- /u-m-c-head -->
			<section class="u-m-c-head">
				<ul class="fl u-m-c-h-txt">
					<li><strong><a
							href="${ctx}/uc/order"
							class="whiteCol">订单管理</a></strong></li>
					<%-- <li><strong><a href="${ctx}/bookOrder/myBookOrderList" class="whiteCol">图书订单</a></strong></li> --%>
					<li><strong><a
							href="${ctx}/uc/address"
							class="grayCol">收货地址</a></strong></li>
					<li class="current"><strong><a
							href="${ctx}/uc/card"
							class="grayCol">课程卡管理</a></strong></li>
				</ul>
				<div class="clear"></div>
			</section>
			<!-- /u-m-c-head -->
			<section class="line1">
				<div class="pl20 pr15">
					<section class="mt40 pb20 line2">
						<h4 class="hLh30">
							<span class="c-333 fsize14">有新课程卡？</span> <a
								href="javascript:void(0)" id="activateCardCourse" title="马上激活"
								class="ml20 jihu-btn">马上激活</a>
						</h4>
					</section>
					<section class="mt15">
						<h5 class="c-333 fsize14">课程卡有什么用途？</h5>
						<p class="c-666 mt5">使用课程卡激活后可以去学习与课程卡金额相应的课程。</p>
					</section>
					<section class="mt30">
						<h5 class="c-333 fsize14 line2 pb10">我的课程卡列表</h5>
						
						<c:if test="${userCardCodeList==null}">
						
							<section class="comm-tips-1">
								<p>
									<em class="vam c-tips-1">&nbsp;</em> <font
										class="c-999 fsize12 vam">你还没有课程卡记录</font>
								</p>
							</section>
						</c:if>
						<c:if test="${userCardCodeList!=null}">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="u-c-card tab-integral mt10">
								<thead>
									<tr>
										<th>编号</th>
										<th>卡类型</th>
										<th>卡号</th>
										<th>卡密码</th>
										<th width="28%">关联课程</th>
										<th>使用时间</th>
										<th>卡状态</th>
									</tr>
								</thead>
								<tbody class="tac">

									<c:forEach var="userCard" items="${userCardCodeList}">
										<tr>
											<td>${userCard.id}</td>
											<td>课程卡</td>
											<td>${userCard.cardCode}</td>
											<td>${userCard.cardCodePassword}</td>
											<td>${userCard.courseName}</td>
											<td>
											  <fmt:formatDate value="${userCard.useTime}" pattern="yyyy/MM/dd  HH:mm:ss" />
										  </td>
											<td>已使用</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<section class="mt5 mb5">
						<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp"/>
							</section>
					</c:if>
					</section>
				</div>
			</section>
		</section>
	</article>
</body>
</html>

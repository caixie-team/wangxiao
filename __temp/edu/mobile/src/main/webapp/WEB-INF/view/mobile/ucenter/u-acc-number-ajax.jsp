<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	
								<c:forEach var="acc" items="${accList}">
								<li>
									<span class="list-pointer">&nbsp;</span>
									<section class="v-c-kcb-lis">
										<p>
											<c:if test="${acc.actHistoryType=='SALES'}">
												消费
											</c:if> <c:if test="${acc.actHistoryType=='REFUND'}">
												退款
											</c:if> <c:if test="${acc.actHistoryType=='CASHLOAD'}">
												现金充值
											</c:if> <c:if test="${acc.actHistoryType=='VMLOAD'}">
												充值卡充值
											</c:if>
										</p>
									</section>
									<section class="v-c-kcb-time">
										<span><fmt:formatDate value="${acc.createTime}" pattern="yyyy-MM-dd  HH:mm" /></span>
									</section>
									<aside class="u-a-n-num">
										<c:if test="${acc.actHistoryType=='SALES'}">
											<span class="zc-num">-${acc.balance}</span>
										</c:if>
										<c:if test="${acc.actHistoryType!='SALES'}">
											<span class="sr-num">+${acc.balance}</span>
										</c:if>
									</aside>
								</li>
								</c:forEach>
							
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	
								<c:forEach items="${trxorderList }" var="trxorder">
								<table cellspacing="0" cellpadding="0" border="0" width="100%" style="margin-bottom: 10px;">
									<thead>
										<tr>
											<th style="text-align: left" width="70%">订单号：${trxorder.requestId }</th>
											<th width="20%" align="center">
												<c:if test="${trxorder.trxStatus=='INIT' }">未完成</c:if>
												<c:if test="${trxorder.trxStatus=='SUCCESS' }">已支付</c:if>
												<c:if test="${trxorder.trxStatus=='CANCEL' }">已取消</c:if>
												<c:if test="${trxorder.trxStatus=='REFUND' }">已退款</c:if>
											</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${trxorder.trxorderDetailList}" var="detail" >
										<tr>
											<td align="left">
												<div>
													<span class="u-o-c-p">
														<img  src="<%=staticImageServer%>${detail.courseImgUrl}" width="80" alt="">
													</span>
													<h5 class="u-o-c-n">${detail.courseName}</h5>
													<section class="c-price">
														<span>￥${detail.currentPirce}</span>
													</section>
													<div class="clear"></div>
												</div>
											</td>
											<td style="text-align: center">
												<fmt:formatDate value="${trxorder.createTime }" pattern="yyyy-MM-dd HH:mm"/>
											</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
								</c:forEach>
</body>
</html>
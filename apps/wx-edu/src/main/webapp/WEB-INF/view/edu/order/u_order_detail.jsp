<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>订单详情</title>
</head>
<body>
<article>
	<header class="uc-com-title">
		<span>订单详情</span>
	</header>
	<div class="i-box">
		<section>
			<div class="mt20">
				<h6 class="hLh30">
					<span class="c-4e fsize14">订单信息：</span>
				</h6>
				<section class="u-add-list u-order-infor-wrap-1">
					<ol>
						<li>
							<span class="w50per disIb">订单号：<b>${trxorder.requestId}</b></span>
							<span class="w50per disIb"> 订单状态：<span class="c-orange">
								<c:if test="${trxorder.trxStatus=='INIT'}"> 未支付 </c:if>
								<c:if test="${trxorder.trxStatus=='SUCCESS'}"> 已支付 </c:if>
								<c:if test="${trxorder.trxStatus=='REFUND'}"> 已退款 </c:if>
								<c:if test="${trxorder.trxStatus=='CANCEL'}"> 已取消 </c:if>
							</span></span>
						</li>
						<li>
							<span class="w50per disIb">下单时间： <fmt:formatDate value="${trxorder.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss " /> </span><span class="w50per disIb">应付订单金额：￥ ${trxorder.orderAmount}</span>
						</li>
						<li>
							<span class="w50per disIb">付款方式：
								<span>
									<c:if test="${trxorder.payType=='KUAIQIAN'}"> 快钱 </c:if>
									<c:if test="${trxorder.payType=='ALIPAY'}"> 支付宝 </c:if>
									<c:if test="${trxorder.payType=='CARD'}"> 课程卡 </c:if>
									<c:if test="${trxorder.payType=='FREE'}"> 赠送 </c:if>
									<c:if test="${trxorder.payType=='INTEGRAL'}"> 未支付 </c:if>
								</span>
							</span>
						</li>
					</ol>
				</section>
				<div class="mt20">
					<h6 class="hLh30">
						<span class="c-4e fsize14">购买课程详情：</span>
					</h6>
					<c:forEach var="detail" items="${trxorder.trxorderDetailList}">
						<section class="mt10">
							<h4 class="u-pageBar unFw of">
								<font class="fsize14 f-fM c-orange">${detail.courseName}</font>
								<span class="fr"><font class="fsize14 f-fM c-orange">￥${detail.currentPirce}</font></span>
							</h4>
							<table width="100%" cellspacing="0" cellpadding="0" border="0" class="tab-integral mt10">
								<thead>
								<tr>
									<th width="60%" class="t-th-fir">包含课程</th>
									<th width="20%" class="t-th-sec">描述</th>
									<th width="20%" class="t-th-thi">课时</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<td>${detail.courseName}</td>
									<td align="center">${detail.courseTitle}</td>
									<td align="center">${detail.totalLessionNum}</td>
								</tr>
								</tbody>
							</table>
						</section>
					</c:forEach>
				</div>
				<div class="mt20 order-top-pagebar">
					<section class="mt5 mb5 of tar c-666 u-o-detail">
						<p class=" fsize14 mt20">
							<span class="ml50">课程数量：<span class="f-fM">￥ ${fn:length(trxorder.trxorderDetailList)}</span>件 </span>
							<span class="ml50">课程金额：<span class="f-fM">￥${trxorder.orderAmount}</span></span>
							<span class="ml50">优惠金额：<span class="f-fM">￥${trxorder.couponAmount}</span></span>
						</p>
						<p class="fsize18 mt10 mb20">
							<tt>应付订单总金额：</tt>
							<tt id="zongjiaId" class="c-orange m10 fsize20 f-fM">￥${trxorder.orderAmount}</tt>
						</p>
					</section>
				</div>
			</div>
		</section>
	</div>
</article>
</body>
</html>
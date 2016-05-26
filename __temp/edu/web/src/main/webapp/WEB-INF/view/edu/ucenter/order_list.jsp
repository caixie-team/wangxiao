<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>订单管理</title>
<script type="text/javascript">
	$(function() {
		var payStatus = '${payStatusRe}';
		if (payStatus != null && payStatus != '') {
			$("#querySelect").val(payStatus);
		}
	});
	function gotoPay(id){
		$.ajax({
			url:"${ctx}/front/repaycheck/"+id,
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.message=='true'){
					window.location.href='${ctx}/front/repay/'+id;
				}else{
					dialog('错误提示',result.message,17,'javascript:window.location.reload()');
				}
			}
		})
	}
</script>
<script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/u_order.js"></script>
</head>
<body>
	<article class="u-m-c-w837 u-m-center">
		<section class="u-m-c-wrap">
			<!-- /u-m-c-head -->
			<section class="u-m-c-head">
				<ul class="fl u-m-c-h-txt">
					<li class="current"><strong><a href="${ctx}/uc/order" class="whiteCol">订单管理</a></strong></li>
					<%-- <li><strong><a href="${ctx}/bookOrder/myBookOrderList" class="whiteCol">图书订单</a></strong></li> --%>
					<li><strong><a href="${ctx}/uc/address" class="grayCol">收货地址</a></strong></li>
					<li><strong><a href="${ctx}/uc/card" class="grayCol">课程卡管理</a></strong></li>
				</ul>
				<div class="clear"></div>
			</section>
			<form action="${ctx}/uc/order" id="searchForm" method="post">
			<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
			</form>
			<!-- /u-m-c-head -->
			<section class="line1">
				<!-- /u-m-sub-head -->
				<div class="u-m-sub-head" style="padding-left: 0px;">
					<ol class="clearfix tac c-333 fsize14"  >
						<li style="width: 253px;">课程</li>
						<li style="width: 109px;">价格</li>
						<li style="width: 104px;">课时</li>
						<li style="width: 110px;">实付款</li>
						<li style="width: 116px;"><select onchange="queryByState(this)" id='querySelect' class="pl10 c-333" style="padding: 2px 10px;">
								<option value="" >订单状态</option>
								<option value="SUCCESS">已支付</option>
								<option value="INIT">未支付</option>
								<option value="CANCEL">已取消</option>
								<option value="REFUND">已退款</option>
						</select></li>
						<li style="width: 135px;">交易操作</li>
					</ol>
				</div>
				<!-- /u-m-sub-head -->
				<div>
					<div class="mt10">
						<section>
							<input id="orderId" type="hidden" value=""><input id="orderno" type="hidden" value="">
							<c:if test="${empty  trxorderList}">
								<section class="comm-tips-1">
									<p>
										<em class="vam c-tips-1">&nbsp;</em> <font class="c-999 fsize12 vam">对不起，你还没购买课程！建议你<a href="${ctx}/front/showcoulist" title="" class="c-orange">去选课</a></font>
									</p>
								</section>
							</c:if>
							<c:forEach var="trxorder"  items="${trxorderList}" varStatus="index">
								<table style="width: 836px;"  border="0" cellspacing="0" cellpadding="0" class="u-order-table">
									<thead>
										<tr>
											<th colspan="6"><span> <span class="vam fsize12">订单号： ${trxorder.requestId}</span>
											</span> <span class="dislb mr20">下单时间： <fmt:formatDate value="${trxorder.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss " /></span> <span class="dislb mr20">状态:
										<c:if test="${trxorder.trxStatus=='INIT'}">
										未支付
										</c:if>
											<c:if test="${trxorder.trxStatus=='SUCCESS'}">
										已支付
										</c:if>	<c:if test="${trxorder.trxStatus=='REFUND'}">
										已退款
										</c:if>
										<c:if test="${trxorder.trxStatus=='CANCEL'}">
										已取消
										</c:if>
												</span>
										优惠
										<span class="c-green">
										${trxorder.couponAmount}</span>
											</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${trxorder.trxorderDetailList}" var="detail" varStatus="index">
											<tr>
												<td style="border-right: none;width:257px" valign="top">
													<section class="fl u-order-img pl10">
														<c:if test="${not empty detail.courseImgUrl}">
															<img src="<%=staticImageServer%>${detail.courseImgUrl}" height="57" width="75" alt="">
														</c:if>
														<c:if test="${empty detail.courseImgUrl}">
															<img src="<%=staticImageServer%>/images/pics/7.jpg" height="57" width="75" alt="">
														</c:if>
													</section>
													<h6 class="unFw c-blue">
														${detail.courseName}
													</h6>
													<div class="mt10 u-order-desc">
														<p class="c-999">
															${detail.courseTitle}
														</p>
													</div>
												</td>
												<td  style=" width:104px;/* border-right: none; border-left: none; */" align="center" valign="middle"><strong class="c-666">￥${detail.currentPirce}</strong></td>
												<td  style="width:101px;/* border-left: none; */" align="center" valign="middle">${detail.lessionNum}课时</td>
												<c:if test="${index.count == 1 }">
												<td  style="width:105px;" align="center" valign="middle" rowspan="${trxorder.trxorderDetailList.size()}">
													<p>
														<tt class="c-orange f-fM fsize16">
															￥ ${trxorder.orderAmount}
														</tt>
													</p>
												</td>
												</c:if>
												<td style="width:115px;/* border-left: none; */" align="center" valign="middle">
													<c:if test="${trxorder.trxStatus=='INIT'}">
														未支付
													</c:if>
													<c:if test="${trxorder.trxStatus=='SUCCESS'}">
														已支付
													</c:if>
													<c:if test="${trxorder.trxStatus=='REFUND'}">
														已退款
													</c:if>
													<c:if test="${trxorder.trxStatus=='CANCEL'}">
														已取消
													</c:if>
												</td>
												<c:if test="${index.count == 1 }">
												<td align="center" style="width:144px;" rowspan="${trxorder.trxorderDetailList.size()}">
													<div>
														<span>
															<c:if test="${trxorder.trxStatus=='INIT'}">
																<a class="c-red mr5" href="javascript:gotoPay(${trxorder.id})">付款</a>
																<a class="" href="javascript:cancleCourse(${trxorder.id})">取消</a>
															</c:if>
															<a href="javascript:toDetails(${trxorder.id})" class="c-blue ml5">详情</a>
														</span>
													</div>
												</td>
												</c:if>
											</tr>
										</c:forEach>

									</tbody>
								</table>
								
						</c:forEach>

						</section>
					</div>
					<section class="mt50 mb50">
						<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
					</section>
				</div>
			</section>
		</section>
	</article>
	<div id="actdivWinId" style="display: none; background: none repeat scroll 0 0 #000000; height: 100%; left: 0; opacity: 0.2; position: fixed; top: 0; width: 100%; z-index: 9999999;"></div>
</body>
</html>

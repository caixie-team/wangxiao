<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>充值订单管理</title>
<script type="text/javascript">
	function orderStatus(){
		var status=$("#queryStatus").val();	
		$("#trxStatus").val(status)
		$("#pageCurrentPage").val(1);
		$("#searchForm").submit();
		
	}
	function orderCancel(id){
		$.ajax({
			url:'/cash/cancel/oder/'+id,
			type:'post',
			dataTypa:'json',
			success:function(result){
				if(result.success){
					window.location.reload();
				}
			}
		});
		
	}
	
</script>
</head>
<body>
	<article class="u-m-c-w837 u-m-center">
		<section class="u-m-c-wrap">
			<!-- /u-m-c-head -->
			<section class="u-m-c-head">
				<ul class="fl u-m-c-h-txt">
					<%--<li><strong><a href="${ctx}/uc/order" class="whiteCol">课程订单</a></strong></li>--%>
					<li class="current"><strong><a href="${ctx}/uc/cash/order" class="cashCol">充值订单</a></strong></li>
					<%-- <li><strong><a href="${ctx}/bookOrder/myBookOrderList" class="whiteCol">图书订单</a></strong></li> 
					<li><strong><a href="${ctx}/uc/address" class="grayCol">收货地址</a></strong></li>
					 <li><strong><a href="${ctx}/uc/card" class="grayCol">课程卡管理</a></strong></li> --%>
				</ul>
				<div class="clear"></div>
			</section>
			<form action="${ctx}/uc/cash/order" id="searchForm" method="post">
				<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
				<input type="hidden" name="cashOrderDTO.trxStatus" value="${cashOrderDTO.trxStatus}" id="trxStatus"/>
			</form>
			<!-- /u-m-c-head -->
			<section class="line1">
				<!-- /u-m-sub-head -->
				<div class="u-m-sub-head" style="padding-left: 0px;">
					<ol class="clearfix tac c-333 fsize14"  >
						<li style="width:449px;">充值金额</li>
						<li style="width: 112px;">实付款</li>
						<li style="width: 123px;"><select onchange="orderStatus()" id='queryStatus' class="pl10 c-333" style="padding: 2px 10px;">
								<option value="" >订单状态</option>
								<option value="SUCCESS" <c:if test="${cashOrderDTO.trxStatus=='SUCCESS' }">selected="selected"</c:if>>已支付</option>
								<option value="INIT" <c:if test="${cashOrderDTO.trxStatus=='INIT' }">selected="selected"</c:if>>未支付</option>
								<option value="CANCEL" <c:if test="${cashOrderDTO.trxStatus=='CANCEL' }">selected="selected"</c:if>>已取消</option>
						</select></li>
						<li style="width: 120px;">交易操作</li>
					</ol>
				</div>
				<!-- /u-m-sub-head -->
				<div>
					<div class="mt10">
						<section>
							<input id="orderId" type="hidden" value=""><input id="orderno" type="hidden" value="">
							<c:if test="${empty  cashOrderDTOs}">
								<section class="comm-tips-1">
									<p>
										<em class="vam c-tips-1">&nbsp;</em> <font class="c-999 fsize12 vam">对不起，你还没充值学币！建议你<a href="${ctx}/uc/acc" title="" class="c-orange">去充值</a></font>
									</p>
								</section>
							</c:if>
							<c:if test="${cashOrderDTOs!=null&&cashOrderDTOs.size()>0}">
								<c:forEach items="${cashOrderDTOs}" var="cashOrderDTO" >
								<table style="width: 836px;"  border="0" cellspacing="0" cellpadding="0" class="u-order-table">
									<thead>
										<tr>
											<th colspan="4"><span> <span class="vam fsize12 mr40">订单号： ${cashOrderDTO.requestId}</span>
											</span> <span class="dislb mr40">下单时间： <fmt:formatDate value="${cashOrderDTO.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss " /></span> 
											<span class="dislb mr20">状态:
												<c:if test="${cashOrderDTO.trxStatus=='INIT'}">
												未支付
												</c:if>
													<c:if test="${cashOrderDTO.trxStatus=='SUCCESS'}">
												已支付
												</c:if>	
												<c:if test="${cashOrderDTO.trxStatus=='CANCEL'}">
												已取消
												</c:if>
												</span>
										
											</th>
										</tr>
									</thead>
									<tbody>
										
											<tr>
												<td style="border-right: none;width:462px" align="center" valign="middle">
													￥${cashOrderDTO.orderAmount}
												</td>
												<td  style="width:105px;" align="center" valign="middle" class='md${cashOrderDTO.id}td${index.count} md${cashOrderDTO.id}'>
													<p>
														<tt class="c-orange f-fM fsize16">
															￥ ${cashOrderDTO.amount}
														</tt>
													</p>
												</td>
												<td style="width:115px;/* border-left: none; */" align="center" valign="middle">
													<c:if test="${cashOrderDTO.trxStatus=='INIT'}">
														未支付
													</c:if>
													<c:if test="${cashOrderDTO.trxStatus=='SUCCESS'}">
														已支付
													</c:if>
													<c:if test="${cashOrderDTO.trxStatus=='CANCEL'}">
														已取消
													</c:if>
												</td>
												<td align="center" style="width:144px;" class='td${cashOrderDTO.id}td${index.count} td${cashOrderDTO.id}'>
													<div>
														<span>
															<c:if test="${cashOrderDTO.trxStatus=='INIT'}">
																<a class="c-red mr5" href="/cash/order/repay/${cashOrderDTO.id}">付款</a>
																<a class="" href="javascript:orderCancel(${cashOrderDTO.id})">取消</a>
															</c:if>
														</span>
													</div>
												</td>
											</tr>
										

									</tbody>
								</table>
								</c:forEach>
						</c:if>

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

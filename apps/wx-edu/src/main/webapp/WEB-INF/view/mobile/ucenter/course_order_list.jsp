<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>我的订单</title>
</head>
<body>
	<div class="m-ptb54">
		<header id="header">
			<section class="h-wrap">
				<div class="menu-btn">
					<a href="/mobile/uc/home" title="" class="go-history">&nbsp;</a>
				</div>
				<h2 class="commHeadTitle"><span>我的订单</span></h2>
			</section>
		</header>
		<!-- /header -->
		<div>
			<!-- body main -->
			<section class="animated fadeIn">
				<div>
					<div class="v-card-title-box">
						<div class="v-card-title" style="position: absolute;">
							<ul class="col-2" id="item-l-ul">
								<li class="current"><a href="javascript: void(0)" title="">录播课程订单</a></li>
								<li><a href="/mobile/uc/order/live" title="">直播课程订单</a></li>
							</ul>
						</div>
					</div>
				</div>
				<c:if test="${empty  trxorderList}">
				<!-- 无数据时提示 开始 -->
				<div class="undataBox">
					<span class="undata-icon">&nbsp;</span>
					<span>你还没有课程订单记录</span>
				</div>
				</c:if>
				<!-- 无数据时提示 结束 -->
				<div class="order-tab" style="padding-bottom: 0;">
					<table cellspacing="0" cellpadding="0" border="0" width="100%">
						<caption style="padding-bottom: 0;">
							<span>录播订单状态</span>
							<select id="queryPayType" onchange="changePayType()">
								<option value="">订单状态</option>
								<option value="INIT" <c:if test="${queryTrxorder.payType=='INIT' }">selected="selected"</c:if>>未支付订单</option>
								<option value="SUCCESS" <c:if test="${queryTrxorder.payType=='SUCCESS' }">selected="selected"</c:if>>已支付订单</option>
								<option value="CANCEL" <c:if test="${queryTrxorder.payType=='CANCEL' }">selected="selected"</c:if>>已取消订单</option>
								<option value="REFUND" <c:if test="${queryTrxorder.payType=='REFUND' }">selected="selected"</c:if>>已退款订单</option>
							</select>
						</caption>
					</table>
				</div>
				<c:if test="${trxorderList!=null &&trxorderList.size()>0}">
				<div class="i-box" style="margin-bottom: -50px;">
					<div>
						
						<section>
							<div class="order-tab" id="courseOrderContent">
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
							</div>
							<!-- /order table -->
						</section>
						<!-- /录播课程 -->

					</div>
					<section>
						<section class="onload-more" style="display: none">
							<a title="加载更多..." href="javascript: void(0)">
								<img src="${ctximg }/static/mobile/img/loading.gif">
								<span>正在努力加载中...</span>
							</a>
						</section>
					</section>
				</div>
				</c:if>
				<form action="/mobile/uc/order/course" id="courseForm" method="post">
						<input type="hidden" value="${page.totalPageSize}" id="totalPageSize"/>
						<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
						<input type="hidden" name="queryTrxorder.payType"  value="${queryTrxorder.payType }" id="payType"/>
					</form>
			</section>
			<!-- body main -->
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			posFun(); //定位上TopBar与下menuBar的位置
			$("#pageCurrentPage").val(1);
			//加载分页
			$(window).bind('scroll',function(){
				var sTop = document.documentElement.scrollTop + document.body.scrollTop;
				var sHeight = document.documentElement.scrollHeight;
				var windowHeight = $(this).height();
				//当滚动到最底下时加载数据
				if(sHeight==(sTop+windowHeight)){
					var totalPageSize=parseInt($("#totalPageSize").val());//总页数
					var nextPage=parseInt($("#pageCurrentPage").val())+1;//下一页
					if(nextPage<=totalPageSize){
						$("#pageCurrentPage").val(nextPage);
						$(".onload-more").show();
						$.ajax({
							url:'/mobile/uc/order/ajax/course',
							type:"post",
							data:{"page.currentPage":nextPage,"queryTrxorder.payType":$("#payType").val()},
							dataType:"text",
							success:function(result){
								$("#courseOrderContent").append(result);
							}
						});
						$(".onload-more").hide();
					}
					
				}
			});
		})
		function changePayType(){
			$("#payType").val($("#queryPayType").val());
			$("#pageCurrentPage").val(1);
			$("#courseForm").submit();
		}
		
	</script>
</body>
</html>
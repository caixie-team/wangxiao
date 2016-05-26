<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>充值订单列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-timepicker-addon.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
$(function(){
	$( "#startTime,#endTime,#startPayTime,#endPayTime" ).datetimepicker({
		regional:"zh-CN",
		changeYear: true,
		changeMonth: true,
		dateFormat:"yy-mm-dd",
		timeFormat : 'HH:mm:ss',
		timeFormat : 'HH:mm:ss'
	});
});
	function cancel(){
		$("#email").val("");
		$("#startTime").val("");
		$("#endTime").val("");
		$("#startPayTime").val("");
		$("#endPayTime").val("");
		$("#trxStatus").val("");
		$("#userId").val("");
		$("#requestId").val("");
	}
	function cashOrderExcel(){
		$("#searchForm").prop("action","${ctx}/admin/cashOrder/export");
		$("#searchForm").submit();
		$("#searchForm").prop("action","${ctx}/admin/cashOrder/page");
	}
</script>
</head>
<body>
<!-- 内容 开始  -->
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>充值订单管理</span> &gt; <span>充值订单列表</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/cashOrder/page" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
				<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
					<caption>
						<div class="capHead">
							<div class="w50pre fl">
								<ul class="ddBar">
									<li>
										<span class="ddTitle"><font>用户ID：</font></span>
										<input type="text" name="cashOrderDTO.userId" value="${cashOrderDTO.userId}" id="userId"/>
									</li>
									<li>
										<span class="ddTitle"><font>订单编号：</font></span>
										<input type="text" name="cashOrderDTO.requestId" value="${cashOrderDTO.requestId}" id="requestId"/>
									</li>
									<li>
										<span class="ddTitle"><font>下单开始时间：</font></span>
										<input type="text" name="cashOrderDTO.startTime" readonly id="startTime" class="AS-inp"
										value="${cashOrderDTO.startTime}"/>
									</li>
									<li>
										<span class="ddTitle"><font>支付开始时间：</font></span>
										<input type="text" name="cashOrderDTO.startPayTime" readonly id="startPayTime" class="AS-inp"
										value="${cashOrderDTO.startPayTime}"/>
									</li>
									
								</ul>
							</div>
							<div class="w50pre fl">
								<ul class="ddBar">
									<li>
										<span class="ddTitle"><font>用户邮箱：</font></span>
										<input type="text" name="cashOrderDTO.email" value="${cashOrderDTO.email}" id="email"/>
									</li>
									<li>
										<span class="ddTitle"><font>支付状态：</font></span>
										<select name="cashOrderDTO.trxStatus" id="trxStatus">
											<option value="">--请选择--</option>
											<option value="INIT" <c:if test="${cashOrderDTO.trxStatus=='INIT'}">selected="selected"</c:if>>未支付</option>
											<option value="SUCCESS" <c:if test="${cashOrderDTO.trxStatus=='SUCCESS'}">selected="selected"</c:if>>支付成功</option>
											<option value="CANCEL" <c:if test="${cashOrderDTO.trxStatus=='CANCEL'}">selected="selected"</c:if>>已取消</option>
										</select>
									</li>
									<li>
										<span class="ddTitle"><font>下单结束时间：</font></span>
										<input type="text" name="cashOrderDTO.endTime" readonly="readonly" id="endTime"
										class="AS-inp" value="${cashOrderDTO.endTime}"/>
									</li>
									<li>
										<span class="ddTitle"><font>支付结束时间：</font></span>
										<input type="text" name="cashOrderDTO.endPayTime" readonly="readonly" id="endPayTime"
										class="AS-inp" value="${cashOrderDTO.endPayTime}"/>
									</li>
								</ul>
							</div>
							<div class="w50pre fl" style="text-align: center;">
								<ul class="ddBar">
									<li>
										<input class="am-btn am-btn-danger ml10" type="button" onclick="goPage(1)" value="查询">
										<input class="am-btn am-btn-danger ml10" type="button" onclick="cancel()" value="清空">
										<input class="am-btn am-btn-danger ml10" type="button" onclick="cashOrderExcel()" value="导出Excel">
									</li>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
						<div class="mt10 clearfix">
							<p class="fl czBtn"></p>
							<p class="fr c_666"><span>订单列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
						</div>
					</caption>
					<thead> 	 	 	 	 	  	 			 	 	  	 	
						<tr> 	 	 	 	 	
							<th width="18%"><span>订单编号</span></th>
                            <th><span>用户email</span></th>
                            <th><span>原始金额</span></th>
                            <th><span>实付金额</span></th>
                            <th><span>下单时间</span></th>
                            <th><span>支付状态</span></th>
                            <th><span>支付类型</span></th>
                            <th><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
					<c:if test="${cashOrderDTOs.size()>0}">
					<c:forEach  items="${cashOrderDTOs}" var="cashOrder" varStatus="status">
						<tr>
							<td>${cashOrder.requestId }</td>
							<td>${cashOrder.email }</td>
							<td>${cashOrder.orderAmount }</td>
							<td>${cashOrder.amount }</td>
							<td><fmt:formatDate value="${cashOrder.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>
								<c:if test="${cashOrder.trxStatus=='INIT'}">未支付</c:if>
								<c:if test="${cashOrder.trxStatus=='SUCCESS'}">已支付</c:if>
								<c:if test="${cashOrder.trxStatus=='CANCEL'}">已取消</c:if>
						    </td>
							<td>
								<c:if test="${cashOrder.payType=='ALIPAY'}">支付宝</c:if>
								<c:if test="${cashOrder.payType=='WEIXIN'}">微信</c:if>
							    <c:if test="${cashOrder.payType=='KUAIQIAN'}">快钱</c:if>
							    <c:if test="${cashOrder.payType=='CARD'}">课程卡</c:if>
							    <c:if test="${cashOrder.payType=='INTEGRAL'}">积分兑换</c:if>
							    <c:if test="${cashOrder.payType=='FREE'}">免费赠送</c:if>
							    <c:if test="${cashOrder.payType=='ACCOUNT'}">账户余额</c:if>
							</td>
							<td>
								<a class="ml10 btn smallbtn btn-y" href="${cxt}/admin/cashOrder/detail/${cashOrder.id}" >查看</a>
							</td>
						</tr>
					</c:forEach>
					</c:if>
					<c:if test="${cashOrderDTOs.size()==0||cashOrderDTOs==null}">
						<tr>
							<td align="center" colspan="16">
								<div class="tips">
								<span>还没有充值订单！</span>
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
		</div><!-- /commonWrap -->
	</div>
</body>
</html>


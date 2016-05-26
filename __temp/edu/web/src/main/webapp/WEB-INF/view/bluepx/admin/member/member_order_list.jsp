<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
$(function(){
	$( "#startTime,#endTime,#startPayTime,#endPayTime" ).datepicker(
   		{regional:"zh-CN",
   		changeMonth: true,
   		dateFormat:"yy-mm-dd "
   	});
});
	function cancel(){
		$("#email").val("");
		$("#startTime").val("");
		$("#endTime").val("");
		$("#startPayTime").val("");
		$("#endPayTime").val("");
		$("#trxStatus").val("");
		$("#memberType").val(0);
		$("#requestId").val("");
	}
	function memberOrderExcel(){
		$("#searchForm").prop("action","${ctx}/admin/memberorder/export");
		$("#searchForm").submit();
		$("#searchForm").prop("action","${ctx}/admin/memberorder/memberorders");
	}
</script>
</head>
<body>
<!-- 内容 开始  -->
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>会员订单管理</span> &gt; <span>会员订单列表</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/memberorder/memberorders" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
				<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
					<caption>
						<div class="capHead">
							<div class="w50pre fl">
								<ul class="ddBar">
									<li>
										<span class="ddTitle"><font>下单开始时间：</font></span>
										<input type="text" name="queryMemberOrder.startTime" readonly id="startTime" class="AS-inp"
										value="${queryMemberOrder.startTime}"/>
									</li>
									<li>
										<span class="ddTitle"><font>下单结束时间：</font></span>
										<input type="text" name="queryMemberOrder.endTime" readonly="readonly" id="endTime"
										class="AS-inp" value="${queryMemberOrder.endTime}"/>
									</li>
									<li>
										<span class="ddTitle"><font>支付开始时间：</font></span>
										<input type="text" name="queryMemberOrder.startPayTime" readonly id="startPayTime" class="AS-inp"
										value="${queryMemberOrder.startPayTime}"/>
									</li>
									<li>
										<span class="ddTitle"><font>支付结束时间：</font></span>
										<input type="text" name="queryMemberOrder.endPayTime" readonly="readonly" id="endPayTime"
										class="AS-inp" value="${queryMemberOrder.endPayTime}"/>
									</li>
								</ul>
							</div>
							<div class="w50pre fl">
								<ul class="ddBar">
									<li>
										<span class="ddTitle"><font>用户邮箱：</font></span>
										<input type="text" name="queryMemberOrder.email" value="${queryMemberOrder.email}" id="email"/>
									</li>
									<li>
										<span class="ddTitle"><font>订单编号：</font></span>
										<input type="text" name="queryMemberOrder.requestId" value="${queryMemberOrder.requestId}" id="requestId"/>
									</li>
									<li>
										<span class="ddTitle"><font>支付状态：</font></span>
										<select name="queryMemberOrder.trxStatus" id="trxStatus">
											<option value="">--请选择--</option>
											<option value="INIT" <c:if test="${queryMemberOrder.trxStatus=='INIT'}">selected="selected"</c:if>>未支付</option>
											<option value="SUCCESS" <c:if test="${queryMemberOrder.trxStatus=='SUCCESS'}">selected="selected"</c:if>>支付成功</option>
										</select>
									</li>
									<li>
										<span class="ddTitle"><font>会员类型：</font></span>
										<select name="queryMemberOrder.memberType" id="memberType">
											<option value="0">--请选择--</option>
											<c:forEach items="${memberTypes }" var="memberType">
												<option value="${memberType.id }" <c:if test="${memberType.id==queryMemberOrder.memberType}">selected="selected"</c:if>>${memberType.title }</option>
											</c:forEach>
										</select>
									</li>
									
								</ul>
							</div>
							<div class="w50pre fl" style="text-align: center;">
								<ul class="ddBar">
									<li>
										<input class="btn btn-danger ml10" type="button" onclick="goPage(1)" value="查询">
										<input class="btn btn-danger ml10" type="button" onclick="cancel()" value="清空">
										<input class="btn btn-danger ml10" type="button" onclick="memberOrderExcel()" value="导出Excel">
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
                            <th><span>优惠金额</span></th>
                            <th><span>实付金额</span></th>
                            <th><span>下单时间</span></th>
                            <th><span>会员类型</span></th>
                            <th><span>开通天数</span></th>
                            <th><span>支付状态</span></th>
                            <th><span>支付类型</span></th>
                            <th><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
					<c:if test="${memberOrderDTOs.size()>0}">
					<c:forEach  items="${memberOrderDTOs}" var="memberOrder" varStatus="status">
						<tr>
							<td>${memberOrder.requestId }</td>
							<td>${memberOrder.email }</td>
							<td>${memberOrder.orderAmount }</td>
							<td>${memberOrder.couponAmount }</td>
							<td>${memberOrder.amount }</td>
							<td><fmt:formatDate value="${memberOrder.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${memberOrder.memberTitle}</td>
							<td>${memberOrder.memberDays }</td>
							<td>
								<c:if test="${memberOrder.trxStatus=='INIT'}">未支付</c:if>
								<c:if test="${memberOrder.trxStatus=='SUCCESS'}">已支付</c:if>
						    </td>
							<td>
								<c:if test="${memberOrder.payType=='ALIPAY'}">支付宝</c:if>
								<c:if test="${memberOrder.payType=='WEIXIN'}">微信</c:if>
							    <c:if test="${memberOrder.payType=='KUAIQIAN'}">快钱</c:if>
							    <c:if test="${memberOrder.payType=='CARD'}">课程卡</c:if>
							    <c:if test="${memberOrder.payType=='INTEGRAL'}">积分兑换</c:if>
							    <c:if test="${memberOrder.payType=='FREE'}">免费赠送</c:if>
							    <c:if test="${memberOrder.payType=='ACCOUNT'}">账户余额</c:if>
							</td>
							<td>
								<a class="ml10 btn smallbtn btn-y" href="${cxt}/admin/member/detail/${memberOrder.id}" >查看</a>
							</td>
						</tr>
					</c:forEach>
					</c:if>
					<c:if test="${memberOrderDTOs.size()==0||memberOrderDTOs==null}">
						<tr>
							<td align="center" colspan="16">
								<div class="tips">
								<span>还没有会员订单！</span>
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


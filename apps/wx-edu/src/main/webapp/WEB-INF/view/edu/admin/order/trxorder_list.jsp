<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>订单列表</title>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	//导出
	function orderExcel(){
		$("#searchForm").prop("action","${cxt}/admin/order/export");
		$("#searchForm").submit();
		$("#searchForm").prop("action","${ctx}/admin/order/orderPageResult");
	}
	$(function() {
		$("#payType").val("${queryTrxorder.payType}");
		$("#trxStatus").val("${queryTrxorder.trxStatus}");
	})

	//审核订单
	function verifyOrder(orderId) {
		if (orderId != null&&orderId!='') {
			dialogFun("订单列表","确定要审核吗?",2,"javascript:_verifyOrder("+orderId+")");
		}
	}
	function _verifyOrder(orderId){
		$.ajax({
			url : "${ctx}/admin/order/verify",
			data : {
				"trxorder.id" : orderId
			},
			dataType : "json",
			type : "post",
			async : false,
			success : function(result) {
				if (result.success == true) {
					window.location.reload();
				}
			},
			error : function(error) {
				alert("error");
			}
		});
	}
	function closethis() {
		$(".modal").removeClass("in").remove();
		$(".modal-backdrop").removeClass("in").remove();
	}
	function restrat(){
		$("#requestId,#email,#nickname,#mobile,#startPayTime,#endPayTime,#startCreateTime,#endCreateTime").val("");
		$("#payType").val("");
		$("#trxStatus").val("");
		$('#searchForm select').each(function() {
			var _this = $(this);
			_this.find('option').eq(0).attr('selected', true);
		});
	}

	//*退费 ， 只有状态为success 才可以退费 
	function refund(id, status) {
		if (status != null && status != "") {
			if (status == "INIT") {
				dialogFun("订单列表","该课程暂未付款,不能退款",0);
				return false;
			}
			if (status == "REFUND") {
				dialogFun("订单列表","该课程已经退款，不能重复申请",0);
				return false;
			}
			if (status == "CANCEL") {
				dialogFun("订单列表","该课程已经关闭",0);
				return false;
			} else {
				dialogFun("订单列表","您确定要退费，款项退回到网站学员账号中？",2,"javascript:_refund("+id+")");
			}
		}
	}

	function _refund(id){
		$.ajax({
			url : "${ctx}/admin/order/refund",
			data : {
				"trxorder.id" : id
			},
			dataType : "json",
			type : "post",
			success : function(result) {
				if (result.success) {
					window.location.reload();
				}
			},
			error : function(error) {
				//alert("error");
			}
		});
	}
</script>
</head>
<body>
<!-- 公共右侧样式 -->
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">订单管理</strong> / <small>订单列表</small></div>
</div>
<hr>
<!-- 公共右侧标题样式 结束-->
<form action="${ctx}/admin/order/orderPageResult" class="am-form" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					订单编号
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text"class="am-input-sm"  id="requestId" type="text" name="queryTrxorder.requestId" value="${queryTrxorder.requestId}" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					下单开始时间
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" readonly class="form-datetime-lang am-form-field" id="startCreateTime" name="queryTrxorder.startCreateTime" value="<fmt:formatDate value='${queryTrxorder.startCreateTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-u-end am-text-left">
				<div class="am-u-sm-4 am-text-right">
					下单结束时间
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" readonly class="form-datetime-lang am-form-field"  name="queryTrxorder.endCreateTime" id="endCreateTime" value="<fmt:formatDate value='${queryTrxorder.endCreateTime}' pattern='yyyy-MM-dd HH:mm:ss' />" />
				</div>
			</div>
			<div class="mt20 clear"></div>

			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					用户名
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text"class="am-input-sm" type="text" name="queryTrxorder.nickname" value="${queryTrxorder.nickname}" id="nickname" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					付款开始时间
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" readonly class="form-datetime-lang am-form-field" name="queryTrxorder.startPayTime" id="startPayTime" value="<fmt:formatDate value='${queryTrxorder.startPayTime}' pattern='yyyy-MM-dd HH:mm:ss' />" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-u-end am-text-left">
				<div class="am-u-sm-4 am-text-right">
					付款结束时间
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" readonly class="form-datetime-lang am-form-field"  name="queryTrxorder.endPayTime" id="endPayTime" value="<fmt:formatDate value='${queryTrxorder.endPayTime}' pattern='yyyy-MM-dd HH:mm:ss' />" />
				</div>
			</div>
			<div class="mt20 clear"></div>

			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					用户邮箱
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text"class="am-input-sm" type="text" id="email" name="queryTrxorder.email" value="${queryTrxorder.email}" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					支付类型
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select id="payType" name="queryTrxorder.payType" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="">---请选择---</option>
						<option value="ALIPAY">支付宝</option>
						<option value="KUAIQIAN">快钱</option>
						<option value="ACCOUNT">账户余额</option>
						<option value="CARD">课程卡</option>
						<option value="FREE">赠送</option>
						<option value="INTEGRAL">积分</option>
						<option value="USERCARD">学员卡</option>
					</select>
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					订单状态
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select id="trxStatus" name="queryTrxorder.trxStatus" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="">---请选择---</option>
						<option value="INIT">未付款</option>
						<option value="SUCCESS">已付款</option>
						<option value="REFUND">已退款</option>
						<option value="CANCEL">已取消</option>
					</select>
				</div>
			</div>
			<div class="mt20 clear"></div>

			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					手机号
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm"  name="queryTrxorder.mobile" value="${queryTrxorder.mobile}" id="mobile" onkeyup="this.value=this.value.replace(/\D/g,'')" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-5 am-text-left">
				<div class="am-u-sm-12 am-u-end  am-text-center">
					<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">
						<span class="am-icon-search"></span> 查询
					</button>
					<button type="button" class="am-btn am-btn-danger" onclick="restrat()">
						清空
					</button>
					<button type="button" class="am-btn am-btn-primary" onclick="orderExcel()">
						导出Excel
					</button>
				</div>
			</div>
			<div class="mt20 clear"></div>
		</div>
	</div>
</form>
<div class="mt20">
	<div class="doc-example">
		<div class="am-scrollable-horizontal">
			<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
				<thead>
				<tr>
					<th class="table-title">ID</th>
					<th>用户名</th>
					<th>电话号码</th>
					<th>用户邮箱</th>
					<th>下单时间</th>
					<th>支付时间</th>
					<th>原始价</th>
					<th>优惠金额</th>
					<th>实际支付</th>
					<th>订单编号</th>
					<th>交易状态</th>
					<th>支付类型</th>
					<th class="table-title">操作</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${orderList.size()>0}">
					<c:forEach items="${orderList}" var="order">
						<tr id="rem${order.id }">
							<td  >${order.id}</td>
							<td  >${order.nickname}</td>
							<td >${order.mobile}</td>
							<td >${order.email}</td>
							<td ><fmt:formatDate value="${order.createTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${order.payTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td >${order.orderAmount}</td>
							<td >${order.couponAmount}</td>
							<td>${order.amount}</td>
							<td >${order.requestId}</td>
							<td  class="payState${order.id}">
								<c:if test="${order.trxStatus=='INIT'}">
									未支付
								</c:if> <c:if test="${order.trxStatus=='SUCCESS'}">
								已支付
							</c:if> <c:if test="${order.trxStatus=='REFUND'}">
								退款
							</c:if> <c:if test="${order.trxStatus=='CANCEL'}">
								取消
							</c:if>
							</td>
							<td  >
								<c:if test="${order.payType=='ALIPAY'}">
									支付宝
								</c:if><c:if test="${order.payType=='WEIXIN'}">
								微信
							</c:if> <c:if test="${order.payType=='KUAIQIAN'}">
								块钱
							</c:if> <c:if test="${order.payType=='CARD'}">
								课程卡
							</c:if> <c:if test="${order.payType=='FREE'}">
								赠送
							</c:if> <c:if test="${order.payType=='INTEGRAL'}">
								积分
							</c:if> <c:if test="${order.payType=='ACCOUNT'}">
								账户
							</c:if>
								<c:if test="${order.payType=='USERCARD'}">
									学员卡
								</c:if>
							</td>
							<td>
								<c:if test="${order.trxStatus=='INIT'}">
									<a class="am-btn am-btn-danger" href="javascript:void(0)" onclick="verifyOrder('${order.id}')">审核</a>
								</c:if> <c:if test="${order.trxStatus=='SUCCESS'}">
								<a class="am-btn am-btn-warning" href="javascript:void(0)" onclick="refund('${order.id}','${order.trxStatus}')">退费</a>
								</c:if>
								<a class="am-btn am-btn-primary" href="${ctx}/admin/order/orderinfo/${order.id}">详情</a>
								<a class="am-btn am-btn-success" href="${ctx}/admin/order/orderDetailList?requestId=${order.requestId}">查看流水</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${orderList.size()==0||orderList==null}">
					<tr>
						<td colspan="16">
							<div data-am-alert=""
								 class="am-alert am-alert-secondary mt20 mb50">
								<center>
									<big> <i class="am-icon-frown-o vam"
											 style="font-size: 34px;"></i> <span class="vam ml10">还没有订单数据！</span></big>
								</center>
							</div>
						</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
		<!-- /pageBar begin -->
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
		<!-- /pageBar end -->
	</div>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>流水详情</title>
<script type="text/javascript">
	/**
	 * 课程延期 订单状态成功  流水状态过期  才可以进行延期
	 * @param orderState detailState
	 */
	function delayCourse(em) {
		var orderState = '${queryTrxorderDetail.trxStatus}';
		if(orderState == "INIT"){
			alert("该课程暂未付款!");
			return false;
		}else{
			$("#hideSpan").css("display", "block");
			$(em).hide();
		}
	}
	function clickStopTIme() {
		var stopTime = document.getElementById('loseTime').value;
		if (stopTime == "") {
			alert("延期时间不能为空！");
			return false;
		}
		var daoqishijian = document.getElementById('daoqishijian').value;
		if (stopTime < daoqishijian) {
			alert('延期时间必须大于到期时间！');
			return false;
		}
		var detailId = '${queryTrxorderDetail.id}';
		if (detailId == null || detailId == '') {
			alert("订单信息错误");
			return false;
		}
		if(confirm("确定延期吗?")){
			$.ajax({
				url : "${ctx}/admin/order/delayorder",
				data : {
					"trxorderDetail.id" : detailId,
					"trxorderDetail.authTime" : stopTime
				},
				dataType : "json",
				type : "post",
				async : false,
				success : function(result) {
					if (result.success == true) {
						alert("已延期");
						$("#hideSpan").hide();
						$(".delaybtn").show();
						window.location.reload();
					}
				},
				error : function(error) {
					alert("error");
				}

			});
		}
	}
	function clickStopTImequxiao(obj){
		$("#hideSpan").hide();
		$(".delaybtn").show();
		$("#loseTime").val("");
	}
	function changeCou(orderId) {
		window.open('${ctx}/admin/order/courselist?orderDetailId=' + orderId,
		'newwindow',
		'toolbar=no,scrollbars=yes,resizable=no,top=200,left=300,width=920,height=550');
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">流水管理</strong> / <small>流水详情</small></div>
</div>
<hr>
<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">流水信息</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form class="am-form">
					<div class="am-g am-margin-top am-form-group">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							流水号
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" value="${queryTrxorderDetail.id}" disabled class="am-input-sm">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							用户邮箱
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" value="${queryTrxorderDetail.email}" disabled class="am-input-sm">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							课程名
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" value="${queryTrxorderDetail.courseName}" disabled class="am-input-sm">
						</div>
						<div class="am-hide-sm-only am-u-md-4">
							<a class="am-btn am-btn-primary" href="javascript:void(0)" onclick="changeCou(${queryTrxorderDetail.id})">换课</a>
						</div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							订单编号
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" value="${queryTrxorderDetail.requestId}" disabled class="am-input-sm">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							订单过期时间
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" disabled value="<fmt:formatDate value="${queryTrxorderDetail.loseAbsTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss" />" class="am-input-sm">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							课程过期时间
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" disabled value="<fmt:formatDate value="${queryTrxorderDetail.authTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss" />" class="am-input-sm">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							下单时间
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" disabled value="<fmt:formatDate value="${queryTrxorderDetail.createTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss" />" class="am-input-sm">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							原价
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" disabled value="${queryTrxorderDetail.sourcePrice}" class="am-input-sm">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							售价
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" disabled value="${queryTrxorderDetail.currentPrice}" class="am-input-sm">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							订单状态
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" disabled value="<c:if test="${queryTrxorderDetail.trxStatus=='SUCCESS'}">支付成功</c:if>
							<c:if test="${queryTrxorderDetail.trxStatus=='INIT'}">未支付</c:if>
							<c:if test="${queryTrxorderDetail.trxStatus=='REFUND'}">已退款</c:if>
							<c:if test="${queryTrxorderDetail.trxStatus=='CANCEL'}">已取消</c:if>" class="am-input-sm">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							课程状态
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" disabled value="<c:if test="${queryTrxorderDetail.authStatus=='SUCCESS'}">支付成功</c:if>
							<c:if test="${queryTrxorderDetail.authStatus=='INIT'}">未支付</c:if>
							<c:if test="${queryTrxorderDetail.authStatus=='REFUND'}">已退款</c:if>
							<c:if test="${queryTrxorderDetail.authStatus=='CLOSED'}">已关闭</c:if>
							<c:if test="${queryTrxorderDetail.authStatus=='LOSED'}">已过期</c:if>" class="am-input-sm">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							描述
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<textarea rows="10" disabled cols="100" readonly >${queryTrxorderDetail.description}</textarea>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>

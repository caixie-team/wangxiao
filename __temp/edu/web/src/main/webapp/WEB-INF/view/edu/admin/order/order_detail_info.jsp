<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>流水详情</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	$(function(){
		$( "#loseTime" ).datetimepicker(
	    		{regional:"zh-CN",
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd ",
	    		timeFormat: "HH:mm:ss"
	    		});
	})
		 
	/**
	 * 课程延期 订单状态成功  流水状态过期  才可以进行延期
	 * @param orderState detailState
	 */
	function delayCourse(em) {
		var orderState = '${queryTrxorderDetail.trxStatus}';
	/* 	var detailState = '${queryTrxorderDetail.authStatus}'
		if (orderState=='SUCCESS'&&detailState =='LOSED') {
			$("#hideSpan").css("display", "block");
			$(em).hide();
		} else if (orderState == "INIT") {
			alert("该课程暂未付款!");
			return false;
		} /* else {
			alert("该课程暂未过期!");
			return false;
		} */
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
		window
				.open('${ctx}/admin/order/courselist?orderDetailId=' + orderId,
						'newwindow',
						'toolbar=no,scrollbars=yes,resizable=no,top=200,left=300,width=920,height=550');
	}
</script>

</head>
<body>
		<form action="${ctx}/admin/teacher/add" method="post" id="addTeacherForm">
			<input type="hidden" name="teacher.picPath" id="imagesUrl" /> <input type="hidden" id="daoqishijian" value="<fmt:formatDate value="${queryTrxorderDetail.authTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em>&nbsp;<span>流水管理</span> &gt; <span>流水详情</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
							<th align="left" colspan="2"><span>流水信息<tt class="c_666 ml20 fsize12" ></tt>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;流水号</td>
							<td>${queryTrxorderDetail.id}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;用户邮箱</td>
							<td>${queryTrxorderDetail.email}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;课程名</td>
							<td><span>${queryTrxorderDetail.courseName}</span>&nbsp;&nbsp;&nbsp;&nbsp; <input style="margin-left: 75px;" class="btn btn-danger " type="button" name="courseName" onclick="changeCou(${queryTrxorderDetail.id})" value="换课" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;订单编号</td>
							<td>${queryTrxorderDetail.requestId}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;订单过期时间</td>
							<td><fmt:formatDate value="${queryTrxorderDetail.loseAbsTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;课程过期时间</td>
							<td><fmt:formatDate value="${queryTrxorderDetail.authTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;&nbsp; <input class="btn btn-danger delaybtn" type="button" name="delay" onclick="delayCourse(this)" value="延期" />
								<dd id="hideSpan" style="display: none">
									<font>过期时间:</font> <input  readonly="readonly" id="loseTime" type="text" name="queryTrxorderDetail.authTime" value="" style="width: 140px;" /> <a href="####" onclick="clickStopTIme()">提交</a> &nbsp;&nbsp; <a href="####" onclick="clickStopTImequxiao(this)">取消</a>
								</dd></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;下单时间</td>
							<td><fmt:formatDate value="${queryTrxorderDetail.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;原价</td>
							<td>${queryTrxorderDetail.sourcePrice}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;售价</td>
							<td>${queryTrxorderDetail.currentPrice}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;订单状态</td>
							<td><span id="trxStatusId"> <c:if test="${queryTrxorderDetail.trxStatus=='SUCCESS'}">
				  支付成功
				</c:if> <c:if test="${queryTrxorderDetail.trxStatus=='INIT'}">
				未支付
				</c:if> <c:if test="${queryTrxorderDetail.trxStatus=='REFUND'}">
				已退款
				</c:if> <c:if test="${queryTrxorderDetail.trxStatus=='CANCEL'}">
				已取消
				</c:if>
							</span></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;课程状态</td>
							<td><span id="authStatusId"> <c:if test="${queryTrxorderDetail.authStatus=='SUCCESS'}">
				  支付成功
				</c:if> <c:if test="${queryTrxorderDetail.authStatus=='INIT'}">
				  未支付
				</c:if> <c:if test="${queryTrxorderDetail.authStatus=='REFUND'}">
				已退款
				</c:if> <c:if test="${queryTrxorderDetail.authStatus=='CLOSED'}">
				已关闭
				</c:if> <c:if test="${queryTrxorderDetail.authStatus=='LOSED'}">
				已过期
				</c:if>
							</span></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;描述</td>
							<td>${queryTrxorderDetail.description}</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
</body>
</html>

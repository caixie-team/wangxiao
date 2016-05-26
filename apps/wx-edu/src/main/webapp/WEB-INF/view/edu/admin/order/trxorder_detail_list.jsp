<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>流水列表</title>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	function orderDetailExcel(){
		$("#searchForm").prop("action","${cxt}/admin/orderDetails/export");
		$("#searchForm").submit();
		$("#searchForm").prop("action","${ctx}/admin/order/orderDetailList");
	}
	function clean() {
		$("#requestId,#email,#courseId,#startCreateTime,#startPayTime,#startAuthTime,#endCreateTime,#endPayTime,#endAuthTime,#nickname,#mobile").val('');
		$("#authStatus").val("");
		$("#trxStatus").val("");
		$('#searchForm select').each(function() {
			var _this = $(this);
			_this.find('option').eq(0).attr('selected', true);
		});
	}
	$(function() {
		$("#authStatus").val("${trxorderDetail.authStatus}");
		$("#trxStatus").val("${trxorderDetail.trxStatus}");
	})

	/**
	 *关闭课程 
	 *@param detailId
	 */
	function closeCourseConfirm(detailId) {
		dialogFun("流水列表","确认关闭该课程吗?",2,"${ctx}/admin/order/closeCourse?detailId="+ detailId);
	}
</script>
</head>
<body>
<!-- 公共右侧样式 -->
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">流水管理</strong> / <small>流水列表</small></div>
</div>
<hr>
<!-- 公共右侧标题样式 结束-->
<form action="${ctx}/admin/order/orderDetailList" class="am-form" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					订单编号
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text"class="am-input-sm"  id="requestId" type="text" name="trxorderDetail.requestId" value="${trxorderDetail.requestId}" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					下单开始时间
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" readonly class="form-datetime-lang am-form-field" id="startCreateTime" name="trxorderDetail.startCreateTime" value="<fmt:formatDate value='${trxorderDetail.startCreateTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-u-end am-text-left">
				<div class="am-u-sm-4 am-text-right">
					下单结束时间
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" readonly class="form-datetime-lang am-form-field"  name="trxorderDetail.endCreateTime" id="endCreateTime" value="<fmt:formatDate value='${trxorderDetail.endCreateTime}' pattern='yyyy-MM-dd HH:mm:ss' />" />
				</div>
			</div>
			<div class="mt20 clear"></div>

			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					课程ID
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text"class="am-input-sm" onkeyup="this.value=this.value.replace(/\D/g,'')" type="text" name="trxorderDetail.courseId" value="${trxorderDetail.courseId}" id="courseId" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					有效期开始
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" readonly class="form-datetime-lang am-form-field" name="trxorderDetail.startAuthTime" id="startAuthTime" value="<fmt:formatDate value='${trxorderDetail.startAuthTime}' pattern='yyyy-MM-dd HH:mm:ss' />" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-u-end am-text-left">
				<div class="am-u-sm-4 am-text-right">
					有效期结束
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" readonly class="form-datetime-lang am-form-field"  name="trxorderDetail.endAuthTime" id="endAuthTime" value="<fmt:formatDate value='${trxorderDetail.endAuthTime}' pattern='yyyy-MM-dd HH:mm:ss' />" />
				</div>
			</div>
			<div class="mt20 clear"></div>

			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					用户名
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text"class="am-input-sm" type="text" name="trxorderDetail.nickname" value="${trxorderDetail.nickname}" id="nickname" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					付款开始时间
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" readonly class="form-datetime-lang am-form-field" name="trxorderDetail.startPayTime" id="startPayTime" value="<fmt:formatDate value='${trxorderDetail.startPayTime}' pattern='yyyy-MM-dd HH:mm:ss' />" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-u-end am-text-left">
				<div class="am-u-sm-4 am-text-right">
					付款结束时间
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" readonly class="form-datetime-lang am-form-field"  name="trxorderDetail.endPayTime" id="endPayTime" value="<fmt:formatDate value='${trxorderDetail.endPayTime}' pattern='yyyy-MM-dd HH:mm:ss' />" />
				</div>
			</div>
			<div class="mt20 clear"></div>

			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					用户邮箱
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text"class="am-input-sm" type="text" id="email" name="trxorderDetail.email" value="${trxorderDetail.email}" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					支付类型
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select id="authStatus" name="trxorderDetail.authStatus" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="">---请选择---</option>
						<option value="INIT">未付款</option>
						<option value="SUCCESS">支付成功</option>
						<option value="REFUND">已退款</option>
						<option value="CLOSED">已关闭</option>
						<option value="LOSED">已过期</option>
					</select>
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					订单状态
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select id="trxStatus" name="trxorderDetail.trxStatus" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
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
					<input type="text" class="am-input-sm"  name="trxorderDetail.mobile" value="${trxorderDetail.mobile}" id="mobile" onkeyup="this.value=this.value.replace(/\D/g,'')" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-5 am-text-left">
				<div class="am-u-sm-12 am-u-end  am-text-center">
					<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">
						<span class="am-icon-search"></span> 查询
					</button>
					<button type="button" class="am-btn am-btn-danger" onclick="clean()">
						清空
					</button>
					<button type="button" class="am-btn am-btn-primary" onclick="orderDetailExcel()">
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
						<th class="table-title">用户名</th>
						<th class="table-title">电话号码</th>
						<th class="table-title">用户邮箱</th>
						<th class="table-title">下单时间</th>
						<th class="table-title">课程过期时间</th>
						<th class="table-title">支付时间</th>
						<th class="table-title">原始价</th>
						<th class="table-title">销售价</th>
						<th class="table-title">课程名</th>
						<th class="table-title">订单编号</th>
						<th class="table-title">课程状态</th>
						<th class="table-title">订单状态</th>
						<th class="table-title">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${orderDetailList.size()>0}">
					<c:forEach items="${orderDetailList}" var="orderDetail">
						<tr id="rem${orderDetail.id }">
							<td>${orderDetail.id}</td>
							<td>${orderDetail.userName}</td>
							<td>${orderDetail.mobile}</td>
							<td>${orderDetail.email}</td>
							<td><fmt:formatDate value="${orderDetail.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${orderDetail.authTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${orderDetail.payTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${orderDetail.sourcePrice}</td>
							<td>${orderDetail.currentPrice}</td>
							<td>${orderDetail.courseName}</td>
							<td>${orderDetail.requestId }</td>
							<td>
								<c:if test="${orderDetail.authStatus=='INIT'}">未付款</c:if>
								<c:if test="${orderDetail.authStatus=='SUCCESS'}">支付成功</c:if>
								<c:if test="${orderDetail.authStatus=='REFUND'}">已退款</c:if>
								<c:if test="${orderDetail.authStatus=='CLOSED'}">已关闭</c:if>
								<c:if test="${orderDetail.authStatus=='LOSED'}">已过期</c:if>
							</td>
							<td>
								<c:if test="${orderDetail.trxStatus=='INIT'}">未付款</c:if>
								<c:if test="${orderDetail.trxStatus=='SUCCESS'}">已付款</c:if>
								<c:if test="${orderDetail.trxStatus=='REFUND'}">已退款</c:if>
								<c:if test="${orderDetail.trxStatus=='CLOSED'}">已关闭</c:if>
								<c:if test="${orderDetail.trxStatus=='CANCEL'}">已取消</c:if>
							</td>
							<td>
								<a class="am-btn am-btn-primary" href="${ctx}/admin/order/detailinfo?detailId=${orderDetail.id}" >详情</a>
								<c:if test="${orderDetail.authStatus!='CLOSED' }">
									<a class="am-btn am-btn-danger" href="javascript:void(0)" onclick="closeCourseConfirm('${orderDetail.id}')">关闭课程</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${orderDetailList.size()==0||orderDetailList==null}">
					<tr>
						<td colspan="16">
							<div data-am-alert=""
								 class="am-alert am-alert-secondary mt20 mb50">
								<center>
									<big> <i class="am-icon-frown-o vam"
											 style="font-size: 34px;"></i> <span class="vam ml10">还没有订单流水数据！</span></big>
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
<!-- 表格样式 二   结束-->
</body>
</html>

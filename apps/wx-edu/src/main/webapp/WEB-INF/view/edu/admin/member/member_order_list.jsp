<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>会员订单记录</title>
	<script type="text/javascript">
		function cancel(){
			$("#email").val("");
			$("input[name='queryMemberOrder.startTime']:first").attr("value", "");
			$("input[name='queryMemberOrder.endTime']:first").attr("value", "");
			$("input[name='queryMemberOrder.startPayTime']:first").attr("value", "");
			$("input[name='queryMemberOrder.endPayTime']:first").attr("value", "");
			$("#trxStatus").val("");
			$("#trxStatus").find('option').eq(0).attr('selected', true);
			$("#memberType").val(0);
			$("#memberType").find('option').eq(0).attr('selected', true);
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
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">会员订单管理</strong> / <small>会员订单列表</small></div>
	</div>
	<hr/>
	<form action="${ctx}/admin/memberorder/memberorders" name="searchForm" id="searchForm" method="post" class="am-form">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<div class="mt20 am-padding admin-content-list">
			<div class="am-tab-panel am-fade am-active am-in">
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">下单开始时间</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="queryMemberOrder.startTime"  data-am-datepicker="{format: 'yyyy-mm-dd'}" readonly="readonly" id="startTime" class="AS-inp" value="${queryMemberOrder.startTime}"/>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						下单结束时间
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="queryMemberOrder.endTime"  data-am-datepicker="{format: 'yyyy-mm-dd'}" readonly="readonly" id="endTime" class="AS-inp" value="${queryMemberOrder.endTime}"/>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">支付开始时间</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="queryMemberOrder.startPayTime"  data-am-datepicker="{format: 'yyyy-mm-dd'}" readonly="readonly"id="startPayTime" class="AS-inp" value="${queryMemberOrder.startPayTime}"/>
					</div>
				</div>
				<div class="mt20 clear"></div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">支付结束时间</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="queryMemberOrder.endPayTime"  data-am-datepicker="{format: 'yyyy-mm-dd'}" readonly="readonly" id="endPayTime" class="AS-inp" value="${queryMemberOrder.endPayTime}"/>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">用户邮箱</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="queryMemberOrder.email" value="${queryMemberOrder.email}" id="email"/>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">订单编号</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="queryMemberOrder.requestId" value="${queryMemberOrder.requestId}" id="requestId"/>
					</div>
				</div>
				<div class="mt20 clear"></div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">支付状态</div>
					<div class="am-u-sm-8 am-u-end">
						<select name="queryMemberOrder.trxStatus" id="trxStatus" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
							<option value="">--请选择--</option>
							<option value="INIT" <c:if test="${queryMemberOrder.trxStatus=='INIT'}">selected="selected"</c:if>>未支付</option>
							<option value="SUCCESS" <c:if test="${queryMemberOrder.trxStatus=='SUCCESS'}">selected="selected"</c:if>>支付成功</option>
						</select>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">会员类型</div>
					<div class="am-u-sm-8 am-u-end">
						<select name="queryMemberOrder.memberType" id="memberType" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
							<option value="0">--请选择--</option>
							<c:forEach items="${memberTypes }" var="memberType">
								<option value="${memberType.id }" <c:if test="${memberType.id==queryMemberOrder.memberType}">selected="selected"</c:if>>${memberType.title }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-12 am-u-end  am-text-center">
						<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">
							<span class="am-icon-search"></span> 搜索
						</button>
						<button type="button" class="am-btn am-btn-danger" onclick="cancel()">
							清空
						</button>
						<button type="button" class="am-btn am-btn-secondary" onclick="memberOrderExcel()">
							导出Excel
						</button>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</form>
	<div class="mt20">
		<div class="doc-example">
			<div class="am-scrollable-horizontal am-scrollable-vertical">
				<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
						<tr>
							<th style="text-align:center;" width="18%"><span>订单编号</span></th>
							<th style="text-align:center;"><span>用户email</span></th>
							<th style="text-align:center;"><span>原始金额</span></th>
							<th style="text-align:center;"><span>优惠金额</span></th>
							<th style="text-align:center;"><span>实付金额</span></th>
							<th style="text-align:center;"><span>下单时间</span></th>
							<th style="text-align:center;"><span>会员类型</span></th>
							<th style="text-align:center;"><span>开通天数</span></th>
							<th style="text-align:center;"><span>支付状态</span></th>
							<th style="text-align:center;"><span>支付类型</span></th>
							<th style="text-align:center;"><span>操作</span></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty memberOrderDTOs}">
							<c:forEach items="${memberOrderDTOs}" var="memberOrder" varStatus="status">
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
										<c:if test="${memberOrder.payType=='YEEPAY'}">易宝</c:if>
									</td>
									<td>
										<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="window.open('${ctx}/admin/member/detail/${memberOrder.id}')">
											<span class="am-icon-search"></span> 查看
										</button>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty memberOrderDTOs }">
							<tr>
								<td align="center" colspan="16">
									<div class="am-alert am-alert-secondary mt20 mb50" data-am-alert="">
										<center><big>
											<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
											<span class="vam ml10">还没有会员订单！</span>
										</big></center>
									</div>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</body>
</html>


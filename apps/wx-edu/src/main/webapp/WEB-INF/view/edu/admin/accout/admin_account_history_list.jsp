<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户账户历史信息</title>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	$(function() {
		$("#actHistoryType").val("${queryUserAccounthistory.actHistoryType}");
		$("#bizType").val("${queryUserAccounthistory.bizType}");
	})
	function clean(){
		$("#startTime,#endTime,#actHistoryType,#bizType").val("");

		$("#searchForm select").each(function(){
			var _this = $(this);
			_this.find('option').eq(0).attr('selected',true);
		});
	}
</script>
</head>
<body>
<!-- 公共右侧样式 -->
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">学员账户管理</strong> / <small>学员历史信息列表</small></div>
</div>
<hr>
<div class="mt20 am-padding admin-content-list">
	<form action="${ctx}/admin/accout/detailed_list" class="am-form" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
		<input type="hidden" name="queryUserAccounthistory.userId" value="${queryUserAccounthistory.userId}"></input>
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					交易类型
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select name="queryUserAccounthistory.actHistoryType" id="actHistoryType" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="">--请选择--</option>
						<option value="SALES">消费</option>
						<option value="REFUND">退款</option>
						<option value="VMLOAD">充值卡充值</option>
						<option value="CASHLOAD">现金充值</option>
						<option value="ADMINLOAD">后台充值</option>
						<option value="ADMINREFUND">后台扣款</option>
					</select>
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					操作开始日期
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="form-datetime-lang am-form-field" readonly name="queryUserAccounthistory.startTime" value="<fmt:formatDate value="${queryUserAccounthistory.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /> "  id="startTime" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-u-end am-text-left">
				<div class="am-u-sm-4 am-text-right">
					操作结束日期
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="form-datetime-lang am-form-field" readonly name="queryUserAccounthistory.endTime" value="<fmt:formatDate value="${queryUserAccounthistory.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /> "  id="endTime" />
				</div>
			</div>
			<div class="mt20 clear"></div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					业务类型
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select name="queryUserAccounthistory.bizType" id="bizType" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="">--请选择--</option>
						<option value="COURSE">课程订单</option>
						<option value="MEMBER">会员订单</option>
						<option value="BOOK">图书订单</option>
						<option value="CARDLOAD">充值卡订单</option>
						<option value="ADMIN">后台操作</option>
					</select>
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					&nbsp;
				</div>
				<div class="am-u-sm-8 am-u-end">
					<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">查询</button>
					<button type="button" class="am-btn am-btn-danger" onclick="clean()">清空</button>
				</div>
			</div>
			<div class="mt20 clear"></div>
		</div>
	</form>
</div>
<div class="am-g">
	<div class="mt20">
		<table class="am-table am-table-striped am-table-hover table-main">
			<thead>
			<tr>
				<th width="13%"><span>当前余额</span></th>
				<th width="13%"><span>cash余额</span></th>
				<th width="13%"><span>vm余额</span></th>
				<th width="13%"><span>交易金额</span></th>
				<th width="14%"><span>交易类型</span></th>
				<th width="15%"><span>业务类型</span></th>
				<th width="19%"><span>操作时间</span></th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${userHisoty.size()>0}">
				<c:forEach items="${userHisoty}" var="user">
					<tr>
						<td>${user.balance}</td>
						<td>${user.cashAmount}</td>
						<td>${user.vmAmount}</td>
						<td>${user.trxAmount}</td>
						<td>
							<c:if test="${user.actHistoryType =='SALES'}">
								消费
							</c:if>
							<c:if test="${user.actHistoryType =='REFUND'}">
								退款
							</c:if>
							<c:if test="${user.actHistoryType =='VMLOAD'}">
								充值卡充值
							</c:if>
							<c:if test="${user.actHistoryType =='CASHLOAD'}">
								现金充值
							</c:if>
							<c:if test="${user.actHistoryType =='ADMINLOAD'}">
								后台充值
							</c:if>
							<c:if test="${user.actHistoryType =='ADMINREFUND'}">
								后台扣款
							</c:if>
						</td>
						<td>
							<c:if test="${user.bizType=='COURSE'}">
								课程订单
							</c:if>
							<c:if test="${user.bizType=='MEMBER'}">
								会员订单
							</c:if>
							<c:if test="${user.bizType=='BOOK'}">
								图书订单
							</c:if>
							<c:if test="${user.bizType=='CARDLOAD'}">
								充值卡订单
							</c:if>
							<c:if test="${user.bizType=='ADMIN'}">
								后台操作
							</c:if>
						</td>
						<td><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="both" /></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${userHisoty.size()==0||userHisoty==null}">
				<tr>
					<td colspan="9">
						<div data-am-alert=""
							 class="am-alert am-alert-secondary mt20 mb50">
							<center>
								<big> <i class="am-icon-frown-o vam"
										 style="font-size: 34px;"></i> <span class="vam ml10">还没有用户账户历史信息！</span></big>
							</center>
						</div>
					</td>
				</tr>
			</c:if>
			</tbody>
		</table>
		<!-- /pageBar begin -->
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
		<!-- /pageBar end -->
	</div>
</div>
</body>
</html>

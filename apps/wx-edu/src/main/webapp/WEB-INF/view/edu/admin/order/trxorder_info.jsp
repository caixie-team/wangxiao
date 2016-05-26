<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>订单详情</title>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">订单管理</strong> / <small>订单详情</small></div>
</div>
<hr>
<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form class="am-form">
					<fieldset disabled>
						<div class="am-g am-margin-top am-form-group">
							<label class="am-u-sm-4 am-u-md-2 am-text-right">
								订单ID
							</label>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="${order.id}" class="am-input-sm">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label class="am-u-sm-4 am-u-md-2 am-text-right">
								订单编号
							</label>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="${order.requestId}" class="am-input-sm">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label class="am-u-sm-4 am-u-md-2 am-text-right">
								用户邮箱
							</label>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="${order.email}" class="am-input-sm">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label class="am-u-sm-4 am-u-md-2 am-text-right">
								下单时间
							</label>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="<fmt:formatDate value="${order.createTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss" />" class="am-input-sm">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label class="am-u-sm-4 am-u-md-2 am-text-right">
								支付时间
							</label>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="<fmt:formatDate value="${order.payTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss" />" class="am-input-sm">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label class="am-u-sm-4 am-u-md-2 am-text-right">
								原始价
							</label>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="${order.orderAmount}" class="am-input-sm">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label class="am-u-sm-4 am-u-md-2 am-text-right">
								优惠金额
							</label>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="${order.couponAmount}" class="am-input-sm">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<c:if test="${order.couponCode!=null}">
							<div class="am-g am-margin-top am-form-group">
								<label class="am-u-sm-4 am-u-md-2 am-text-right">
									优惠券编码
								</label>
								<div class="am-u-sm-8 am-u-md-4">
									<input type="text" value="${order.couponCode}" class="am-input-sm">
								</div>
								<div class="am-hide-sm-only am-u-md-6"></div>
							</div>
						</c:if>
						<div class="am-g am-margin-top am-form-group">
							<label class="am-u-sm-4 am-u-md-2 am-text-right">
								实际支付
							</label>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="${order.amount}" class="am-input-sm">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label class="am-u-sm-4 am-u-md-2 am-text-right">
								支付类型
							</label>
							<div class="am-u-sm-8 am-u-md-4">
								<c:set value="" var="payType" />
								<c:if test="${order.payType=='ALIPAY'}">
									<c:set value="支付宝" var="payType" />
								</c:if>
								<c:if test="${order.payType=='WEIXIN'}">
									<c:set value="微信" var="payType" />
								</c:if>
								<c:if test="${order.payType=='KUAIQIAN'}">
									<c:set value="快钱" var="payType" />
								</c:if>
								<c:if test="${order.payType=='CARD'}">
									<c:set value="课程卡" var="payType" />
								</c:if>
								<c:if test="${order.payType=='FREE'}">
									<c:set value="赠送" var="payType" />
								</c:if>
								<c:if test="${order.payType=='INTEGRAL'}">
									<c:set value="积分" var="payType" />
								</c:if>
								<c:if test="${order.payType=='ACCOUNT'}">
									<c:set value="账户" var="payType" />
								</c:if>
								<c:if test="${order.payType=='USERCARD'}">
									<c:set value="学员卡" var="payType" />
								</c:if>
								<c:if test="${order.payType=='YEEPAY'}">
									<c:set value="易宝" var="payType" />
								</c:if>
								<c:if test="${order.payType=='IOS'}">
									<c:set value="苹果内购" var="payType" />
								</c:if>
								<input type="text" value="${payType}" class="am-input-sm"/>
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label class="am-u-sm-4 am-u-md-2 am-text-right">
								订单状态
							</label>
							<div class="am-u-sm-8 am-u-md-4">
								<c:set value="" var="trxStatus" />
								<c:if test="${order.trxStatus=='SUCCESS'}">
									<c:set value="支付成功" var="trxStatus" />
								</c:if>
								<c:if test="${order.trxStatus=='INIT'}">
									<c:set value="未支付" var="trxStatus" />
								</c:if>
								<c:if test="${order.trxStatus=='REFUND'}">
									<c:set value="已退款" var="trxStatus" />
								</c:if>
								<c:if test="${order.trxStatus=='CANCEL'}">
									<c:set value="已取消" var="trxStatus" />
								</c:if>
								<input type="text" value="${trxStatus}" class="am-input-sm">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>

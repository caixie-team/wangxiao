<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>会员订单详情</title>
<script type="text/javascript" src="${ctximg}/static/common/jquery.bigcolorpicker.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery.bigcolorpicker.css?v=${v}" /> 
</head>
<body >
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">会员订单管理</strong> / <small>会员订单详情</small>
		</div>
	</div>
	<hr/>
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
				<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;订单编号</div>
				<div class="am-u-sm-8 am-u-md-4">
				<input type="text" value="${memberOrderDTO.requestId }" readonly="readonly" class="{required:true}"/>
				</div>
				<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
			</div>
			
		 <div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;用户email</div>
			<div class="am-u-sm-8 am-u-md-4">
			<input type="text" value="${memberOrderDTO.email }" readonly="readonly" class="{required:true}"/>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		 <div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;原始金额</div>
			<div class="am-u-sm-8 am-u-md-4">
			<input type="text" value="${memberOrderDTO.orderAmount }" readonly="readonly" class="{required:true}"/>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		 <div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;优惠金额</div>
			<div class="am-u-sm-8 am-u-md-4">
			<input type="text" value="${memberOrderDTO.couponAmount }" readonly="readonly" class="{required:true}"/>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		 <div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;优惠券编码</div>
			<div class="am-u-sm-8 am-u-md-4">
			<input type="text" value="${memberOrderDTO.couponCode }" readonly="readonly" class="{required:true}"/>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;实付金额</div>
			<div class="am-u-sm-8 am-u-md-4">
			<input type="text" value="${memberOrderDTO.amount }"/>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;下单时间</div>
			<div class="am-u-sm-8 am-u-md-4">
			<input type="text" value='<fmt:formatDate value="${memberOrderDTO.createTime}" type="both" pattern="yyyy-MM-dd"/>' readonly="readonly" />
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;会员类型</div>
			<div class="am-u-sm-8 am-u-md-4">
			<fieldset disabled="disabled" style="width: 600px">
			<select disabled="disabled" data-am-selected="{btnWidth: '25%', btnSize: 'sm', btnStyle: 'secondary'}">
								<option value="0">--请选择--</option>
								<c:forEach items="${memberTypes }" var="memberType">
									<option value="${memberType.id }" <c:if test="${memberType.id==memberOrderDTO.memberType }">selected="selected"</c:if>>${memberType.title }</option>
								</c:forEach>
							</select>
			</fieldset>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;开通天数</div>
			<div class="am-u-sm-8 am-u-md-4">
			<input type="text" value="${memberOrderDTO.memberDays }" readonly="readonly" class="{required:true}"/>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;支付状态</div>
			<div class="am-u-sm-8 am-u-md-4">
				<c:if test="${memberOrderDTO.trxStatus=='INIT'}"><input type="text" value="未支付" readonly="readonly" class="{required:true}"/></c:if>
				<c:if test="${memberOrderDTO.trxStatus=='SUCCESS'}"><input type="text" value="已支付" readonly="readonly" class="{required:true}"/></c:if>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;支付时间</div>
			<div class="am-u-sm-8 am-u-md-4">
				<input type="text" value='<fmt:formatDate value="${memberOrderDTO.payTime}" type="both" pattern="yyyy-MM-dd"/>' readonly="readonly" />
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
			<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;支付类型</div>
			<div class="am-u-sm-8 am-u-md-4">
				<c:if test="${memberOrderDTO.payType=='ALIPAY'}"><input type="text" value="支付宝" readonly="readonly" class="{required:true}"/></c:if>
			   	<c:if test="${memberOrderDTO.payType=='WEIXIN'}"><input type="text" value="微信" readonly="readonly" class="{required:true}"/></c:if>
			    <c:if test="${memberOrderDTO.payType=='KUAIQIAN'}"><input type="text" value="快钱" readonly="readonly" class="{required:true}"/></c:if>
			    <c:if test="${memberOrderDTO.payType=='CARD'}"><input type="text" value="课程卡" readonly="readonly" class="{required:true}"/></c:if>
			    <c:if test="${memberOrderDTO.payType=='INTEGRAL'}"><input type="text" value="积分兑换" readonly="readonly" class="{required:true}"/></c:if>
			    <c:if test="${memberOrderDTO.payType=='FREE'}"><input type="text" value="免费赠送" readonly="readonly" class="{required:true}"/></c:if>
			    <c:if test="${memberOrderDTO.payType=='ACCOUNT'}"><input type="text" value="账户余额"  readonly="readonly" class="{required:true}"/></c:if>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		</fieldset>
		</form>
</div>
</div>
</div>
</div>
</body>
</html>

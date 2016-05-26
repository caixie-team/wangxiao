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
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>会员订单管理</span> &gt; <span>会员订单详情</span> </h4>
	</div>
		<!-- /tab4 begin -->
		<div class="mt20">
			<div class="commonWrap">
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
				<thead>
					<tr>
						<th align="left" colspan="2"><span>会员订单基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
					</tr>
				</thead>
				<tbody>
					
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;订单编号 </td>
						<td>
							<input type="text" value="${memberOrderDTO.requestId }" readonly="readonly" class="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;用户email </td>
						<td>
							<input type="text" value="${memberOrderDTO.email }" readonly="readonly" class="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;原始金额</td>
						<td>
							<input type="text" value="${memberOrderDTO.orderAmount }" readonly="readonly" class="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;优惠金额</td>
						<td>
							<input type="text" value="${memberOrderDTO.couponAmount }" readonly="readonly" class="{required:true}"/>
						</td>
					</tr>
					<c:if test="${memberOrderDTO.couponCode!=null}">
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;优惠券编码</td>
						<td>
							<input type="text" value="${memberOrderDTO.couponCode }" readonly="readonly" class="{required:true}"/>
						</td>
					</tr>
					</c:if>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;实付金额</td>
						<td>
							<input type="text" value="${memberOrderDTO.amount }" readonly="readonly" class="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;下单时间</td>
						<td>
							<input type="text" value='<fmt:formatDate value="${memberOrderDTO.createTime}" type="both" pattern="yyyy-MM-dd"/>' readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;会员类型</td>
						<td>
							<select disabled="disabled">
								<option value="0">--请选择--</option>
								<c:forEach items="${memberTypes }" var="memberType">
									<option value="${memberType.id }" <c:if test="${memberType.id==memberOrderDTO.memberType }">selected="selected"</c:if>>${memberType.title }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;开通天数</td>
						<td>
							<input type="text" value="${memberOrderDTO.memberDays }" readonly="readonly" class="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;支付状态</td>
						<td>
							<c:if test="${memberOrderDTO.trxStatus=='INIT'}"><input type="text" value="未支付" readonly="readonly" class="{required:true}"/></c:if>
							<c:if test="${memberOrderDTO.trxStatus=='SUCCESS'}"><input type="text" value="已支付" readonly="readonly" class="{required:true}"/></c:if>
						</td>
					</tr>
					<c:if test="${memberOrderDTO.trxStatus=='SUCCESS'}">
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;支付时间</td>
						<td>
							<input type="text" value='<fmt:formatDate value="${memberOrderDTO.payTime}" type="both" pattern="yyyy-MM-dd"/>' readonly="readonly" />
						</td>
					</tr>
					</c:if>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;支付类型</td>
						<td>
								<c:if test="${memberOrderDTO.payType=='ALIPAY'}"><input type="text" value="支付宝" readonly="readonly" class="{required:true}"/></c:if>
							    <c:if test="${memberOrderDTO.payType=='KUAIQIAN'}"><input type="text" value="快钱" readonly="readonly" class="{required:true}"/></c:if>
							    <c:if test="${memberOrderDTO.payType=='CARD'}"><input type="text" value="课程卡" readonly="readonly" class="{required:true}"/></c:if>
							    <c:if test="${memberOrderDTO.payType=='INTEGRAL'}"><input type="text" value="积分兑换" readonly="readonly" class="{required:true}"/></c:if>
							    <c:if test="${memberOrderDTO.payType=='FREE'}"><input type="text" value="免费赠送" readonly="readonly" class="{required:true}"/></c:if>
							    <c:if test="${memberOrderDTO.payType=='ACCOUNT'}"><input type="text" value="账户余额"  readonly="readonly" class="{required:true}"/></c:if>
						</td>
					</tr>
				</tbody>
			</table>
			</div>
		</div>
		<!-- /tab4 end -->
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>充值订单详情</title>

</head>
<body >
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>充值订单管理</span> &gt; <span>充值订单详情</span> </h4>
	</div>
		<!-- /tab4 begin -->
		<div class="mt20">
			<div class="commonWrap">
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
				<thead>
					<tr>
						<th align="left" colspan="2"><span>充值订单基本属性<tt class="c_666 ml20 fsize12"></tt></span></th>
					</tr>
				</thead>
				<tbody>
					
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;订单编号 </td>
						<td>
							<input type="text" value="${cashOrderDTO.requestId }" readonly="readonly" class="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;用户email </td>
						<td>
							<input type="text" value="${cashOrderDTO.email }" readonly="readonly" class="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;原始金额</td>
						<td>
							<input type="text" value="${cashOrderDTO.orderAmount }" readonly="readonly" class="{required:true}"/>
						</td>
					</tr>
					
					
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;实付金额</td>
						<td>
							<input type="text" value="${cashOrderDTO.amount }" readonly="readonly" class="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;下单时间</td>
						<td>
							<input type="text" value='<fmt:formatDate value="${cashOrderDTO.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly" />
						</td>
					</tr>
					
					
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;支付状态</td>
						<td>
							<c:if test="${cashOrderDTO.trxStatus=='INIT'}"><input type="text" value="未支付" readonly="readonly" class="{required:true}"/></c:if>
							<c:if test="${cashOrderDTO.trxStatus=='SUCCESS'}"><input type="text" value="已支付" readonly="readonly" class="{required:true}"/></c:if>
							<c:if test="${cashOrderDTO.trxStatus=='CANCEL'}"><input type="text" value="已取消" readonly="readonly" class="{required:true}"/></c:if>
						</td>
					</tr>
					<c:if test="${cashOrderDTO.trxStatus=='SUCCESS'}">
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;支付时间</td>
						<td>
							<input type="text" value='<fmt:formatDate value="${cashOrderDTO.payTime}" type="both" pattern="yyyy-MM-dd"/>' readonly="readonly" />
						</td>
					</tr>
					</c:if>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;支付类型</td>
						<td>
								<c:if test="${cashOrderDTO.payType=='ALIPAY'}"><input type="text" value="支付宝" readonly="readonly" class="{required:true}"/></c:if>
								<c:if test="${cashOrderDTO.payType=='WEIXIN'}"><input type="text" value="微信" readonly="readonly" class="{required:true}"/></c:if>
							    <c:if test="${cashOrderDTO.payType=='KUAIQIAN'}"><input type="text" value="快钱" readonly="readonly" class="{required:true}"/></c:if>
							    <c:if test="${cashOrderDTO.payType=='CARD'}"><input type="text" value="课程卡" readonly="readonly" class="{required:true}"/></c:if>
							    <c:if test="${cashOrderDTO.payType=='INTEGRAL'}"><input type="text" value="积分兑换" readonly="readonly" class="{required:true}"/></c:if>
							    <c:if test="${cashOrderDTO.payType=='FREE'}"><input type="text" value="免费赠送" readonly="readonly" class="{required:true}"/></c:if>
							    <c:if test="${cashOrderDTO.payType=='ACCOUNT'}"><input type="text" value="账户余额"  readonly="readonly" class="{required:true}"/></c:if>
						</td>
					</tr>
				</tbody>
			</table>
			</div>
		</div>
		<!-- /tab4 end -->
</body>
</html>

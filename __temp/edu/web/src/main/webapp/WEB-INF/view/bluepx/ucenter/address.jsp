<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>收货地址</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.errorStyle.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/localization/messages_zh.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/additional-methods.min.js?v=${v}"></script>
<script type="text/javascript">
	$().ready(function() {
		//initFormValid();
		initArea(1, 0);
		initArea($("#sel_province").find("option:eq(0)").val(), 1);
		$("#addressForm").validate();
	});
	function initArea(id, index) {
		var parentId = 1;
		if (id != null && id != 0 && !isNaN(id)) {
			parentId = id;
		}
		//只有第一个下拉框查询一级
		if (index != 0) {
			if (parentId == 1) {
				return;
			}
		}
		$.ajax({
			url : baselocation + "/area/ajax/parentid",
			data : {
				"parentId" : parentId
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if (result == null || result.entity == null) {
					return;
				}
				var provinces = result.entity;
				var html = '';
				for (var i = 0; i < provinces.length; i++) {
					html += "<option value='" + provinces[i].id + "'>"
							+ provinces[i].areaName + "</option>";
				}
				if (index == 0) {
					$(html).appendTo("#provinceId");
				} else if (index == 1) {
					$("#cityId").html("");
					$("#townId").html("");
					$("<option value='0'>--------</option>" + html).appendTo(
							"#cityId");
					$("<option value='0'>--------</option>").appendTo(
							"#townId");
				} else {
					try {
						$("#townId")[0].innerHTML = "";
						$("#townId").html(
								"<option value='0'>--------</option>" + html);
					} catch (e) {
					}
					;
				}
			},
			error : function(error) {
				alert('error' + error.responseText);
			}
		});
	}
	function xuanzhongxian() {
		$("#duihao").html("");
		$("#duihao")
				.append(
						'<tt class="o-pass"><em class="icon18 vam disIb">&nbsp;</em></tt>');
	}

	//地址提交
	function saveAddress() {
		//判断是否选择省县市
		var provinceId = $("#provinceId").val();
		var cityId = $("#cityId").val();
		var townId = $("#townId").val();
		if(provinceId=="0"||cityId=="0"){
			dialog('提示',"请正确选择省市县",13);
			return;
		}
		$("#addressForm").submit();
	}
	//修改地址
	function initUpdate(id,receiver,address,postCode,mobile,provinceId,cityId,townId){
		$("#id").val(id);
		$("#receiver").val(receiver);
		$("#address").val(address);
		$("#postCode").val(postCode);
		$("#mobile").val(mobile);
		$("#provinceId").val(provinceId);
		$("#provinceId").change();
		$("#cityId").val(cityId);
		$("#cityId").change();
		$("#townId").val(townId);
		$("#townId").change();
	}
</script>
</head>
<body>
	<!-- 中间内容 -->
	<article class="u-m-c-w837 u-m-center">
		<section class="u-m-c-wrap">
			<!-- 开始 -->
			<!-- /u-m-c-head -->
			<section class="u-m-c-head">
				<ul class="fl u-m-c-h-txt">
					<li><strong><a href="${ctx}/uc/order" class="whiteCol">订单管理</a></strong></li>
					<%-- <li><strong><a href="${ctx}/bookOrder/myBookOrderList" class="whiteCol">图书订单</a></strong></li> --%>
					<li class="current"><strong><a href="${ctx}/uc/address" class="grayCol">收货地址</a></strong></li>
					<li><strong><a href="${ctx}/uc/card"
							class="grayCol">课程卡管理</a></strong></li>
				</ul>
				<div class="clear"></div>
			</section>
			<!-- /u-m-c-head -->
			<section class="line1">
				<div class="mt30 pl20 pr20">
					<h6 class="hLh30">
						<span class="c-4e fsize14">一、确认收货地址</span>
					</h6>
					<section class="u-add-list">
						<ol>
							<c:forEach items="${userAddressList}" var="address">
								<li><span class="address-attr"> <c:if test="${address.isFirst==1}">
											<tt class="vam mr30 c-orange">[常用地址]</tt>
										</c:if> <c:if test="${address.isFirst!=1}">
											<a href="${ctx}/uc/address/common/${address.id}" title="设为常用地址" class="vam mr30">[设为常用地址]</a>
										</c:if>  <a
										href="javascript:initUpdate('${address.id}', 
                              									'${address.receiver}',
                              									'${address.address}',
                              									'${address.postCode}',
                              									'${address.mobile}',
                              									'${address.provinceId}',
                              									'${address.cityId}',
                              									'${address.townId}')"
										title="修改" class="u-address-set"> <em class="icon-2-14">&nbsp;</em>
									</a> <a href="${ctx}/uc/address/del/${address.id}" title="删除" class="u-address-delete"> <em class="icon-2-14">&nbsp;</em>
									</a>
								</span> <input type="radio" id="add-1" name="" checked="checked" value="" placeholder="" style="margin: 0;"> <label for="add-1"
									class="vam">${address.receiver}&nbsp;&nbsp;${address.provinceStr} , ${address.cityStr} , ${address.townStr} , ${address.address}</label></li>
							</c:forEach>
						</ol>
					</section>
					<form action="${ctx}/uc/address/add" id="addressForm" name="addressForm" method="post">
						<input type="hidden" name="userAddress.id" value="0" id="id">
						<input type="hidden" name="userAddress.isFirst" value="2" id="isFirst">
						<div class="mt30 addRessInpt pl20 pb50 line3">
							<ul class="loginInpt">
								<li><label class="vam"><em class="c-orange mr5">*</em><font class="fsize14 c-666">收&nbsp;货&nbsp;人：</font></label> <input
									type="text" name="userAddress.receiver" id="receiver" value="" class="lTxt required"></li>
								<li class="mt15"><label class="vam"><em class="c-orange mr5">*</em><font class="fsize14 c-666">地&nbsp;&nbsp;&nbsp;&nbsp;址：</font></label>
									<select name="userAddress.provinceId" id="provinceId" onchange="initArea(this.value, 1)">
										<option value="0">--请选择省--</option>
								</select> <select id="cityId" name="userAddress.cityId" onchange="initArea(this.value, 2)">
										<option value="0">--请选择市--</option>
								</select> <select id="townId" onchange="xuanzhongxian()" name="userAddress.townId">
										<option value="0">--请选择县--</option>
								</select></li>
								<li class="mt15"><label class="vam"><em class="c-orange mr5">*</em><font class="fsize14 c-666">街道地址：</font></label> <input
									type="text" name="userAddress.address" id="address" value="" class="lTxt required" style="width: 386px;"></li>
								<li class="mt15"><label class="vam"><em class="c-orange mr5">*</em><font class="fsize14 c-666">邮政编码：</font></label> <input
									type="text"  name="userAddress.postCode" id="postCode" maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')"
									value="" class="lTxt required"> </li>
								<li class="mt15"><label class="vam"><em class="c-orange mr5">*</em><font class="fsize14 c-666">手&nbsp;&nbsp;&nbsp;&nbsp;机：</font></label>
									<input type="text" name="userAddress.mobile" id="mobile"
									onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="11" value="" class="lTxt required mobile"></li>
								<li class="mt20"><span class="disIb fsize14 grayCol w80">&nbsp;&nbsp;</span> <span class="uCommInput">
								</span></li>
							</ul>
						</div>
					</form>
				</div>
				<div class="tac mt20">
					<label class="u-a-set-btn"><input type="button" onclick="saveAddress();" value="保 存" name="saveAddrBtn"></label>
				</div>
			</section>
			<!-- 结束 -->
		</section>
	</article>
	<!-- 中间内容结束 -->
</body>
</html>

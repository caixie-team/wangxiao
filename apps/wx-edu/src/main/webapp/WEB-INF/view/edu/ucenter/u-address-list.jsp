<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>收货地址</title>
</head>
<body>
<div class="">
	<!-- 公共左侧目录区 结束 -->
	<article>
		<header class="uc-com-title">
			<span>收货地址</span>
		</header>
		<div class="i-box">
			<section  class="mt30 line2">
			<!-- 	<div>
					<h6 class="hLh30"> <span class="c-4e fsize14">一、确认收货地址</span> </h6>
				</div> -->
				<c:if test="${empty userAddressList}">
					<div class="mt40">
					<!-- /无数据提示 开始-->
					<section class="no-data-wrap">
						<em class="no-data-ico">&nbsp;</em> <span
							class="c-666 fsize14 ml10 vam">您还没有收货地址
							<a class="c-orange" title="" href="${ctx}/uc/goUserAddress">去添加收货地址</a>
							</span>
					</section>
					<!-- /无数据提示 结束-->
					</div>
				</c:if>
				<c:if test="${not empty userAddressList}">
				<section class="u-add-list">
					<ol>
					<c:forEach items="${userAddressList}" var="address">
						<li>
							<input type="radio" class="vam" placeholder="" value="" checked="checked" name="" id="add-1">
							<label class="vam" for="add-1">${address.receiver}&nbsp;&nbsp;${address.provinceStr} , ${address.cityStr} ,${address.townStr} , ${address.address}</label>
							<span class="address-attr">
							<c:if test="${address.isFirst==1}">
								<tt class="vam mr30 c-orange">[常用地址]</tt>
							</c:if>
							<c:if test="${address.isFirst!=1}">
								<a href="${ctx}/uc/address/common/${address.id}" title="设为常用地址" class="vam mr30">[设为常用地址]</a>
							</c:if>
								<a href="javascript:initUpdate('${address.id}',
												'${address.receiver}',
												'${address.address}',
												'${address.postCode}',
												'${address.mobile}',
												'${address.provinceId}',
												'${address.cityId}',
												'${address.townId}')" title="修改" class="u-address-set">
									<em class="icon14">&nbsp;</em>
								</a>
								<a href="${ctx}/uc/address/del/${address.id}" title="删除" class="u-address-delete">
									<em class="icon14">&nbsp;</em>
								</a>
							</span>
						</li>
						</c:forEach>
					</ol>
				</section>
				<form action="${ctx}/uc/address/add" id="addressForm" name="addressForm" method="post">
				<input type="hidden" name="userAddress.id" value="0" id="id">
				<input type="hidden" name="userAddress.isFirst" value="2" id="isFirst">
					<div class="mt30 addRessInpt p-ml20 pb50 line3 mb15">
						<ul class="loginInpt">
							<li>
								<label class="vam"><em class="c-orange mr5">*</em><font class="fsize14 c-666">收&nbsp;货&nbsp;人：</font></label>
								<input type="text" class="lTxt required" value="" id="receiver" name="userAddress.receiver">
							</li>
							<li class="mt15">
								<label class="vam"><em class="c-orange mr5">*</em><font class="fsize14 c-666">地&nbsp;&nbsp;&nbsp;&nbsp;址：</font></label>
								<select name="userAddress.provinceId" id="provinceId" onchange="initArea(this.value, 1)">
									<option value="0">--请选择省--</option>
								</select>
								<select id="cityId" name="userAddress.cityId" onchange="initArea(this.value, 2)">
								<option value="0">--请选择市--</option> </select>
								<select id="townId" onchange="xuanzhongxian()" name="userAddress.townId">
								<option value="0">--请选择县--</option> </select>
							</li>
							<li class="mt15">
								<label class="vam">
									<em class="c-orange mr5">*</em>
									<font class="fsize14 c-666">街道地址：</font>
								</label>
								<input type="text"  class="lTxt required" value="" id="address" name="userAddress.address">
							</li>
							<li class="mt15">
								<label class="vam">
									<em class="c-orange mr5">*</em>
									<font class="fsize14 c-666">邮政编码：</font>
								</label>
								<input type="text" class="lTxt required" value=""  maxlength="10" id="postCode" name="userAddress.postCode" onkeyup="this.value=this.value.replace(/\D/g,'')">
							</li>
							<li class="mt15">
								<label class="vam">
									<em class="c-orange mr5">*</em>
									<font class="fsize14 c-666">手&nbsp;&nbsp;&nbsp;&nbsp;机：</font>
								</label>
								<input type="text" class="lTxt required mobile" value="" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')" id="mobile" name="userAddress.mobile">
							</li>
						</ul>
					</div>
				</form>
			</section>
			<div class="tac mt20">
				<label class="u-a-set-btn">
					<input type="button" name="saveAddrBtn" value="保 存" onclick="saveAddress()">
				</label>
			</div>
			</c:if>
		</div>
	</article>
</div>
	<script>
		$().ready(function() {
		//initFormValid();
		initArea(1, 0);
		initArea($("#sel_province").find("option:eq(0)").val(), 1);
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
		
		if($("#receiver").val()==null || $("#receiver").val()==''){
			dialogFun('提示',"请输入收货人",0);
			return;
		}
		if(provinceId=="0"||cityId=="0"){
			dialogFun('提示',"请正确选择省市县",0);
			return;
		}
		if($("#address").val()==null || $("#address").val()==''){
			dialogFun('提示',"请输入街道地址",0);
			return;
		}
		if($("#postCode").val()==null || $("#postCode").val()==''){
			dialogFun('提示',"请输入邮编",0);
			return;
		}
		if($("#mobile").val()==null || $("#mobile").val()==''){
			dialogFun('提示',"请输入手机号",0);
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
</body>
</html>
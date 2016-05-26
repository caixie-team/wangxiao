<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>积分兑换</title>
</head>
<body>
<div class="">
	<article>
		<header class="uc-com-title">
			<span>积分兑换</span>
		</header>
		<div class="i-box">
			<div>
				<section class="integral-list pb50 line2">
					<input type="hidden" id="giftIdHidden" value="${giftId}"/>
					<ul class="clearfix">
						<li>
							<section class="inte-l-img">
								<a class="giftWrap" title="" href="javascript:void(0)">
									<c:if test="${gift.courseId==0 }">
									<img src="<%=staticImageServer %>${gift.logo}" width="178" height="133"  />
									</c:if>
									<c:if test="${gift.courseId!=0 }">
									<img src="<%=staticImageServer %>${gift.courseLogo}" width="178" height="133"  />
									</c:if>
								</a>
							</section>
							<h6 class="hLh30 of unFw line2">
								<tt class="c-blue">${gift.name}</tt>
							</h6>
							<div class="of">
								<span class="fl c-red mt5">所需积分：${gift.score}</span>
							</div>
						</li>
					</ul>
				</section>
				<section class="u-add-list">
					<ol>
					<c:forEach items="${userAddressList}" var="address">
						<li>
							<input type="radio" class="vam" placeholder="" <c:if test="${address.isFirst==1}">checked="checked"</c:if> value="${address.id}" checked="checked" name="address" id="add-1">
							<label class="vam" for="add-1">${address.receiver}&nbsp;&nbsp;${address.provinceStr} , ${address.cityStr} ,${address.townStr} , ${address.address}</label>
						</li>
					</c:forEach>
					</ol>
				</section>
				<section class="mt40 pb20 line2">
					<span class="vam ml50">
						<label class="u-a-set-btn">
							<input type="button" onclick="exchangeIntegral()" value="确认兑换">
						</label>
					</span>
				</section>
			</div>
		</div>
	</article>
</div>
<script>
	function exchangeIntegral(){
		var giftId=$("#giftIdHidden").val();
		var addressId=$('input[name="address"]:checked').val();
		if(addressId==null||addressId==0){
			dialogFun('提示','请选择收货地址',0);
			return;
		}
		$.ajax({
			url:"${ctx}/uc/exchange/"+giftId+"/"+addressId,
			dataType:"json",
			type:"post",
			async:false,
			success:function(result){
				if(result.success){
					dialogFun("提示信息",result.message,5,'/uc/mygift');

				}else{
					dialogFun("提示信息",result.message,0);
					return;
				}
			}
		});
	}
</script>
</body>
</html>
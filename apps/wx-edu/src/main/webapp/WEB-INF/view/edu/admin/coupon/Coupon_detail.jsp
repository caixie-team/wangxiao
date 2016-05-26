<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>查看优惠券</title>

<script type="text/javascript">
	var type='${couponDTO.type}';
	$(function(){
		changeType(type);//初始化优惠券类型
	});
	function changeType(val){
		if(val==1){
			$("#reduceTr").css("display","none");
			$("#preferentialTr").css("display","");
		}else{
			$("#preferentialTr").css("display","none");
			$("#reduceTr").css("display","");
		}
	}
	function daochuBianMa(id){
   		var codeSize='${couponDTO.couponCodes.size()}';
	   	if(codeSize>0){
	   		window.location.href="${ctx}/admin/coupon/exportcode?queryCouponCode.couponId="+id;
	   		return true;
	   	}else{
	   		dialogFun('优惠券详情','此优惠券无优惠编码，不能进行导出！',0);
	   		return false;
	   	}
   	}
	function wasteBianMa(id){
   		var codeSize='${couponDTO.couponCodes.size()}';
	   	if(codeSize>0){
	   		$.ajax({
	   			url:"${ctx}/admin/coupon/wastecoupon/"+id,
	   			type:"post",
	   			dataType:"json",
	   			success:function(result){
	   				if(result.message=='true'){
						dialogFun('优惠券详情',"作废成功",0);
	   				}
	   			}
	   		});
	   		
	   	}else{
	   		dialogFun('优惠券详情','此优惠券无优惠编码，不能进行作废！',0);
	   	}
   	}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">优惠券管理</strong> / <small>查看优惠券</small>
		</div>
	</div>
	<hr />
	<div class="mt20">
		<div  class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="#tab1">基本信息</a></li>
			</ul>
			<div class="am-tabs-bd">
				<div id="tab1" class="am-tab-panel am-fade am-active am-in">
					<form class="am-form">
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">优惠券名称</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" class="am-input-sm" value="${couponDTO.title}" readonly="readonly"
									required/>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">优惠券类型</div>
							<div class="am-u-sm-8 am-u-md-4">
								<c:if test="${couponDTO.type==1 }">
									<input type="text" class="am-input-sm" value="折扣券" readonly="readonly" />
								</c:if>
								<c:if test="${couponDTO.type==2 }">
									<input type="text" class="am-input-sm" value="定额券" readonly="readonly" />
								</c:if>
								<c:if test="${couponDTO.type==3 }">
									<input type="text"　class="am-input-sm" value="会员定额券" readonly="readonly" />
								</c:if>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>

						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">次数限制</div>
							<div class="am-u-sm-8 am-u-md-4">
								<c:if test="${couponDTO.useType==1 }">
									<input class="am-input-sm" type="text" value="无限" readonly="readonly"/>
								</c:if>
								<c:if test="${couponDTO.useType==2 }">
									<input class="am-input-sm" type="text" value="正常" readonly="readonly"/>
								</c:if>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>

						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">使用限额</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" class="am-input-sm" value="${couponDTO.limitAmount}"
									readonly="readonly" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>

						<div id="preferentialTr" class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">优惠折扣</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" class="am-input-sm" value="${couponDTO.amount}" readonly="readonly"/>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">该优惠券折扣额，如2折优惠券，则选择“2.0</div>
						</div>
						<div id="reduceTr" class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">优惠金额</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" class="am-input-sm" value="${couponDTO.amount}" readonly="readonly"/>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">该优惠券金额，如5元优惠券，则请输入“5”</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">适用范围</div>
							<div class="am-u-sm-8 am-u-md-4">
								<c:choose>
									<c:when
										test="${couponDTO.subjectName!=null&&couponDTO.subjectName!=''}">
										<input type="text" class="am-input-sm" value="单选项目" readonly="readonly"/>
									</c:when>
									<c:when
										test="${couponDTO.courses!=null&&couponDTO.courses.size()>0}">
										<input type="text" class="am-input-sm" value="多选课程" readonly="readonly"/>
									</c:when>
									<c:otherwise>
										<input type="text" class="am-input-sm" value="所有课程" readonly="readonly"/>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">使用起始日期</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" class="am-input-sm" value='<fmt:formatDate value="${couponDTO.startTime}" type="both" pattern="yyyy-MM-dd hh:ss:mm"/>' readonly="readonly" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">使用截止日期</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" class="am-input-sm" value="<fmt:formatDate value="${couponDTO.endTime}" type="both" pattern="yyyy-MM-dd hh:ss:mm"/>" readonly="readonly" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">生成优惠编码数量</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" class="am-input-sm" value="${couponDTO.totalNum}" readonly="readonly"/>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">必须是数字，数字不得大于1000</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">备注</div>
							<div class="am-u-sm-8 am-u-md-4">
								<textarea rows="3" cols="80" readonly="readonly">${couponDTO.info}</textarea>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">必须是数字，数字不得大于1000</div>
						</div>
						<c:if
							test="${couponDTO.couponCodes!=null&&couponDTO.couponCodes.size()>0}">
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">优惠券编码</div>
								<div class="am-u-sm-8 am-u-md-4">
									<textarea id="couponCode" style="font-size: 9" readonly="readonly" rows="8" cols="35"></textarea>
									<button class="am-btn am-btn-danger" type="submit"
										onclick="daochuBianMa(${couponDTO.id})">导出优惠码</button>
									<button class="am-btn am-btn-danger" type="button"
										onclick="wasteBianMa(${couponDTO.id})">作废优惠码</button>
									<script type="text/javascript">
										 var textareaHtml="";
										 <c:forEach items="${couponDTO.couponCodes}" var="codeString" varStatus="status">
											 textareaHtml=textareaHtml+'${status.count}、'+'${codeString}'+'\n';
										 </c:forEach>
										$("#couponCode").html(textareaHtml);
									</script>
								</div>
								<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
							</div>
						</c:if>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
							<div class="am-u-sm-8 am-u-md-4">
								<button class="am-btn am-btn-danger" type="button"
									onclick="window.history.go(-1)">返回</button>
							</div>
							<div class="am-u-sm-12 am-u-md-6"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

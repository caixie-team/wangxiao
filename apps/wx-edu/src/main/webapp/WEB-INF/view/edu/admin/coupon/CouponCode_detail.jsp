<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>查看优惠券编码</title>

<script type="text/javascript">
	var type = '${couponDTO.type}';
	$(function() {
		changeType(type);//初始化优惠券类型
	});
	function changeType(val) {
		if (val == 1) {
			$("#reduceTr").css("display", "none");
			$("#preferentialTr").css("display", "");
		} else {
			$("#preferentialTr").css("display", "none");
			$("#reduceTr").css("display", "");
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
	<!-- /tab4 begin -->
	<div class="mt20">
		<div class="mt20">
			<div  class="am-tabs">
				<ul class="am-tabs-nav am-nav am-nav-tabs">
					<li class="am-active"><a href="#tab1">基本信息</a></li>
				</ul>
				<div class="am-tabs-bd">
					<div id="tab1" class="am-tab-panel am-fade am-active am-in">
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">优惠券编码</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="${couponCode.couponCode}"
									readonly="readonly" class="{required:true}" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">优惠券名称</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="${couponDTO.title}"
									readonly="readonly" class="{required:true}" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>

						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">类型</div>
							<div class="am-u-sm-8 am-u-md-4">
								<c:if test="${couponDTO.type==1 }">
									<input type="text" value="折扣券" readonly="readonly"
										class="{required:true}" />
								</c:if>
								<c:if test="${couponDTO.type==2 }">
									<input type="text" value="定额券" readonly="readonly"
										class="{required:true}" />
								</c:if>
								<c:if test="${couponDTO.type==3 }">
									<input type="text" value="会员定额券" readonly="readonly"
										class="{required:true}" />
								</c:if>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>

						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">次数限制</div>
							<div class="am-u-sm-8 am-u-md-4">
								<c:if test="${couponDTO.useType==1 }">
									<input type="text" value="无限" readonly="readonly"
										class="{required:true}" />
								</c:if>
								<c:if test="${couponDTO.type==2 }">
									<input type="text" value="正常" readonly="readonly"
										class="{required:true}" />
								</c:if>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">'无限'优惠券在有效期内可以被任意多人使用多次，'正常'优惠券使用一次后不能继续使用</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">状态</div>
							<div class="am-u-sm-8 am-u-md-4">
								<c:if test="${couponCode.status==1 }">
									<input type="text" value="未使用" readonly="readonly"
										class="{required:true}" />
								</c:if>
								<c:if test="${couponCode.status==2 }">
									<input type="text" value="已使用" readonly="readonly"
										class="{required:true}" />
								</c:if>
								<c:if test="${couponCode.status==3 }">
									<input type="text" value="过期" readonly="readonly"
										class="{required:true}" />
								</c:if>
								<c:if test="${couponCode.status==4 }">
									<input type="text" value="作废" readonly="readonly"
										class="{required:true}" />
								</c:if>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>

						<c:if test="${couponCode.status==2 }">
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">订单编号</div>
								<div class="am-u-sm-8 am-u-md-4">
									<input type="text" value="${couponCode.requestId }"
										readonly="readonly" class="{required:true}" />
								</div>
								<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
							</div>
						</c:if>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">使用限额</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="${couponDTO.limitAmount}"
									readonly="readonly"
									class="{required:true,number:true,min:0,max:1000}" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">只有订单总金额达到这个数的订单才能使用此折扣优惠券，如300元，则请输入“300”</div>
						</div>
						<div id="preferentialTr" class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">优惠折扣</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="${couponDTO.amount}"
									readonly="readonly"
									class="{required:true,number:true,min:0,max:1000}" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">该优惠券折扣额，如2折优惠券，则选择“2.0”</div>
						</div>

						<div id="reduceTr" class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">优惠金额</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" value="${couponDTO.amount}"
									readonly="readonly"
									class="{required:true,number:true,min:0,max:1000}" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">该优惠券金额，如5元优惠券，则请输入“5”</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">适用范围</div>
							<div class="am-u-sm-8 am-u-md-4">
								<c:choose>
									<c:when
										test="${couponDTO.subjectName!=null&&couponDTO.subjectName!=''}">
										<input type="text" value="单选项目" readonly="readonly"
											class="{required:true}" />
									</c:when>
									<c:when
										test="${couponDTO.courses!=null&&couponDTO.courses.size()>0}">
										<input type="text" value="多选课程" readonly="readonly"
											class="{required:true}" />
									</c:when>
									<c:otherwise>
										<input type="text" value="所有课程" readonly="readonly"
											class="{required:true}" />
									</c:otherwise>
								</c:choose>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">使用起始日期</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text"
									value='<fmt:formatDate value="${couponDTO.startTime}" type="both" pattern="yyyy-MM-dd hh:ss:mm"/>'
									readonly="readonly" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">使用截止日期</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text"
									value="<fmt:formatDate value="${couponDTO.endTime}" type="both" pattern="yyyy-MM-dd hh:ss:mm"/>"
									readonly="readonly" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
							<div class="am-u-sm-8 am-u-md-4">
								<button class="am-btn am-btn-danger" type="button"
									onclick="window.history.go(-1)">返回</button>
							</div>
							<div class="am-u-sm-12 am-u-md-6"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /tab4 end -->
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>优惠券编码列表</title>

<script type="text/javascript">
function cancel(){
	$("#type").val(-1);
	$("#useType").val(-1);
	$("#status").val(-1);
	$("#couponId").val("");
	$("#requestId").val("");
	$("#couponCode").val("");
	$("#searchForm select").each(function(){
		$(this).find('option').eq(0).attr("selected",true);
	});
}
     

function allCheck(cb)
{
	$("input[name=ids]:checkbox").prop('checked', cb.checked);
}
function useBatch(){
	var userId = "${userId}";
	var codeIds = $("input[name='ids']:checked");
	var check=false;
	var ids = "";
	for(var i=0;i<codeIds.length;i++){
		check=true;
		ids +=codeIds[i].value;
		if(i!=codeIds.length-1){
			ids +=",";
		}
	}
	if(!check){
		dialogFun("赠送优惠券","请选择要赠送的优惠码",0);
		return;
	}
	$.ajax({
		url : "${ctx}/admin/user/giveCoupons",
		data : {"ids" : ids,"userId" : userId},
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.success){
				dialogFun("赠送优惠券","赠送成功！",5,"${ctx}/admin/user/UserMsg/"+userId);
			}else{
				dialogFun("赠送优惠券","系统出错！",0);
			}
		}
	})
	
}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">优惠券编码管理</strong> / <small>优惠券编码列表</small></div>
</div>
<hr>
<form action="${ctx}/admin/user/toCouponCodes/${userId}" name="searchForm" id="searchForm" method="post" class="am-form">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-4">
				<div class="am-u-sm-4 am-text-right">
					优惠券ID
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryCouponCode.couponId" id="couponId" value="${queryCouponCode.couponId}" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4">
				<div class="am-u-sm-4 am-text-right">
					订单编号
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" name="queryCouponCode.requestId" id="requestId" value="${queryCouponCode.requestId}" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4">
				<div class="am-u-sm-4 am-text-right">
					优惠券编码
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" name="queryCouponCode.couponCode" id="couponCode" value="${queryCouponCode.couponCode}" />
				</div>
			</div>
			<div class="mt20 clear"></div>

			<div class="am-g am-margin-top am-u-sm-4">
				<div class="am-u-sm-4 am-text-right">
					次数限制
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select  name="queryCouponCode.useType" id="useType" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}" style="display: none;">
						<option value="-1">--请选择--</option>
						<option value="1" <c:if test="${queryCouponCode.useType==1 }">selected="selected"</c:if>>无限</option>
						<option value="2" <c:if test="${queryCouponCode.useType==2 }">selected="selected"</c:if>>正常</option>
					</select>
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4">
				<div class="am-u-sm-4 am-text-right">
					优惠类型
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select  name="queryCouponCode.type" id="type" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}" style="display: none;">
						<option value="-1">--请选择--</option>
						<option value="1" <c:if test="${queryCouponCode.type==1 }">selected="selected"</c:if>>折扣券</option>
						<option value="2" <c:if test="${queryCouponCode.type==2 }">selected="selected"</c:if>>定额券</option>
						<option value="3" <c:if test="${queryCouponCode.type==3 }">selected="selected"</c:if>>会员定额券</option>
					</select>
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4">
				<div class="am-u-sm-4 am-text-right">
					编码状态
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select  name="queryCouponCode.status" id="status" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}" style="display: none;">
						<option value="-1">--请选择--</option>
						<option value="1" <c:if test="${queryCouponCode.status==1 }">selected="selected"</c:if>>未使用</option>
						<option value="2" <c:if test="${queryCouponCode.status==2 }">selected="selected"</c:if>>已使用</option>
						<option value="3" <c:if test="${queryCouponCode.status==3 }">selected="selected"</c:if>>过期</option>
						<option value="4" <c:if test="${queryCouponCode.status==4 }">selected="selected"</c:if>>作废</option>
					</select>
				</div>
			</div>
			<div class="mt20 clear"></div>
		</div>
	</div>
</form>
<div class="mt20">
	<div class="am-g">
		<div class="am-u-md-6">
			<div class="am-btn-toolbar">
				<div class="am-btn-group am-btn-group-xs">
					<button class="am-btn am-btn-success" type="button" onclick="useBatch()"><span class="am-icon-plus"></span> 赠送优惠券</button>
				</div>
			</div>
		</div>
		<div class="am-u-sm-12 am-u-md-3">
			<div class="am-input-group am-input-group-sm">
				<span class="am-input-group-btn">
					<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">查询</button>
					<button type="button" class="am-btn am-btn-primary" onclick="cancel()">清空</button>
					<button type="button" class="am-btn am-btn-secondary" onclick="history.go(-1)">返回</button>
				</span>
			</div>
		</div>
	</div>
</div>
<div class="mt20">
	<div class="doc-example">
		<div class="am-scrollable-horizontal am-scrollable-vertical">
			<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
				<thead>
					<tr>
						<th width="10%">
							<label class="am-checkbox">
								<input type="checkbox" onclick="allCheck(this)" data-am-ucheck/> 全选
							</label>
						</th>
						<th><span>名称</span></th>
						<th><span>优惠券编码</span></th>
						<th><span>类型</span></th>
						<th><span>限额</span></th>
						<th><span>次数限制</span></th>
						<th><span>有效期</span></th>
						<th><span>状态</span></th>
						<th><span>创建时间</span></th>
						<th><span>创建人</span></th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${not empty couponCodeDTOList }">
				<c:forEach  items="${couponCodeDTOList}" var="couponCodeDTO" >
					<tr>
						<td>
							<label class="am-checkbox">
								<input type="checkbox" name="ids" value="${couponCodeDTO.id}" data-am-ucheck/>
							</label>
						</td>
						<td>${couponCodeDTO.title}</td>
						<td>${couponCodeDTO.couponCode}</td>
						<td>
							<c:if test="${couponCodeDTO.type==1 }">折扣券</c:if>
							<c:if test="${couponCodeDTO.type==2 }">定额券</c:if>
							<c:if test="${couponCodeDTO.type==3 }">会员定额券</c:if>
						</td>
						<td>${couponCodeDTO.limitAmount }</td>
						<td>
							<c:if test="${couponCodeDTO.useType==1 }">无限</c:if>
							<c:if test="${couponCodeDTO.useType==2 }">正常</c:if>
						</td>
						<td>
							<fmt:formatDate value="${couponCodeDTO.startTime}" type="both"  pattern="yyyy-MM-dd"/>~<fmt:formatDate value="${couponCodeDTO.endTime}" type="both"  pattern="yyyy-MM-dd"/>
						</td>
						<td>
							<c:if test="${couponCodeDTO.status==1 }">未使用</c:if>
							<c:if test="${couponCodeDTO.status==2 }">已使用</c:if>
							<c:if test="${couponCodeDTO.status==3 }">过期</c:if>
							<c:if test="${couponCodeDTO.status==4 }">作废</c:if>
						</td>
						<td>
							<fmt:formatDate value="${couponCodeDTO.createTime}" type="both"  pattern="yyyy-MM-dd"/>
						</td>
						<td>
							${couponCodeDTO.optuserName }
						</td>
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${empty couponCodeDTOList }">
						<tr>
							<td colspan="16">
								<div data-am-alert=""
									 class="am-alert am-alert-secondary mt20 mb50">
									<center>
										<big> <i class="am-icon-frown-o vam"
												 style="font-size: 34px;"></i> <span class="vam ml10">还没有优惠券编码！</span></big>
									</center>
								</div>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</body>
</html>

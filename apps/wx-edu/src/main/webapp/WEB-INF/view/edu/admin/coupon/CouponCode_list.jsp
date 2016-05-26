<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>优惠券编码列表</title>

<script type="text/javascript">
function cancel(){
	$("#type").val(-1);
	$("#type").find('option').eq(0).attr('selected', true);
	$("#useType").val(-1);
	$("#useType").find('option').eq(0).attr('selected', true);
	$("#status").val(-1);
	$("#status").find('option').eq(0).attr('selected', true);
	$("#couponId").val("");
	$("#requestId").val("");
	$("#couponCode").val("");
}
function allCheck(cb)
{
    $("input[name=ids]:checkbox").attr('checked', cb.checked);
}       
function waste(id){
	$.ajax({
		url:"${cxt}/admin/couponcode/waste",
		type:"post",
		data:{"ids":id},
		dataType:"json",
		success:function(result){
			if(result.message=="true"){
				dialogFun("优惠券","已作废",5,"javascript:goPage(1)");
			}
		}
	});
}
function allCheck(cb)
{
	$("input[name=ids]").prop('checked', cb.checked);
}
function wasteBatch(){
	var codeIds = document.getElementsByName("ids");
	
	var num=0;
	var ids ='';
	for(var i=0;i<codeIds.length-1;i++){
		if(codeIds[i].checked==true){
			num++;
			ids +=codeIds[i].value;
			if(i!=codeIds.length-1){
				ids +=",";
			}
		}
	}
	if(num==0){
		dialogFun("优惠券","请选择要作废的优惠码",0);
		return;
	}
	$.ajax({
		url:"${cxt}/admin/couponcode/waste",
		type:"post",
		data:{"ids":ids},
		dataType:"json",
		success:function(result){
			if(result.message=="true"){
				dialogFun("优惠券","已作废",5,"javascript:goPage(1)");
			}
		}
	});
}
//导出
function couponExcel(){
	$("#searchForm").prop("action","${ctx}/admin/coupon/exportcode");
	$("#searchForm").submit();
	$("#searchForm").prop("action","${ctx}/admin/couponcode/page");
}
//查看优惠券编码
function couponDetail(couponId){
	window.location.href="${cxt}/admin/couponcode/detail/"+couponId;
}

</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">优惠券编码管理</strong> / <small>优惠券编码列表</small>
		</div>
	</div>
	<hr />
	<div class="mt20 am-padding admin-content-list">
		<form class="am-form" action="${ctx}/admin/couponcode/page"
			name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage"
				value="${page.currentPage}" />
			<div class="am-tab-panel am-fade am-active am-in">
				<div class="am-g am-margin-top am-u-sm-4">
					<div class="am-u-sm-4 am-text-right">优惠券ID</div>
					<div class="am-u-sm-8 am-u-end">
						<input class="am-input-sm" type="text" name="queryCouponCode.couponId" id="couponId" onkeyup="this.value=this.value.replace(/\D/g,'')" value="${queryCouponCode.couponId}" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4">
					<div class="am-u-sm-4 am-text-right">订单编号</div>
					<div class="am-u-sm-8 am-u-end">
						<input class="am-input-sm" type="text"
							name="queryCouponCode.requestId" id="requestId"
							value="${queryCouponCode.requestId}" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4">
					<div class="am-u-sm-4 am-text-right">编码</div>
					<div class="am-u-sm-8 am-u-end">
						<input class="am-input-sm" type="text"
							name="queryCouponCode.couponCode" id="couponCode"
							value="${queryCouponCode.couponCode}" />
					</div>
				</div>
				<div class="mt20 clear"></div>
				<div class="am-g am-margin-top am-u-sm-4">
					<div class="am-u-sm-4 am-text-right">次数限制</div>
					<div class="am-u-sm-8 am-u-end">
						<select
							data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}"
							name="queryCouponCode.useType" id="useType">
							<option value="-1">请选择</option>
							<option value="1"
								<c:if test="${queryCouponCode.useType==1 }">selected="selected"</c:if>>无限</option>
							<option value="2"
								<c:if test="${queryCouponCode.useType==2 }">selected="selected"</c:if>>正常</option>
						</select>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4">
					<div class="am-u-sm-4 am-text-right">优惠类型</div>
					<div class="am-u-sm-8 am-u-end">
						<select
							data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}"
							name="queryCouponCode.type" id="type">
							<option value="-1">--请选择--</option>
							<option value="1"
								<c:if test="${queryCouponCode.type==1 }">selected="selected"</c:if>>折扣券</option>
							<option value="2"
								<c:if test="${queryCouponCode.type==2 }">selected="selected"</c:if>>定额券</option>
							<option value="3"
								<c:if test="${queryCouponCode.type==3 }">selected="selected"</c:if>>会员定额券</option>
						</select>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4">
					<div class="am-u-sm-4 am-text-right">编码状态</div>
					<div class="am-u-sm-8 am-u-end">
						<select
							data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}"
							name="queryCouponCode.status" id="status">
							<option value="-1">--请选择--</option>
							<option value="1"
								<c:if test="${queryCouponCode.status==1 }">selected="selected"</c:if>>未使用</option>
							<option value="2"
								<c:if test="${queryCouponCode.status==2 }">selected="selected"</c:if>>已使用</option>
							<option value="3"
								<c:if test="${queryCouponCode.status==3 }">selected="selected"</c:if>>过期</option>
							<option value="4"
								<c:if test="${queryCouponCode.status==4 }">selected="selected"</c:if>>作废</option>
						</select>
					</div>
				</div>
				<div class="mt20 clear"></div>
			</div>
		</form>
	</div>
	<div class="mt20">
		<div class="am-g">
			<div class="am-u-md-6">
				<div class="am-btn-toolbar">
					<div class="am-btn-group am-btn-group-xs">
						<button class="am-btn am-btn-success" type="button"
							onclick="wasteBatch()" title="批量作废">
							<span class="am-icon-plus"></span> 批量作废
						</button>
					</div>
				</div>
			</div>
			<div class="am-u-sm-12 am-u-md-4">
				<div class="am-input-group am-input-group-sm">
					<button type="button" class="am-btn am-btn-warning"
						onclick="goPage(1)">查询</button>
					<button type="button" class="am-btn am-btn-danger"
						onclick="cancel()">清空</button>
					<button type="button" class="am-btn am-btn-primary"
						onclick="couponExcel()">导出Excel</button>
				</div>
			</div>
		</div>
	</div>

	<div class="mt20">
		<div class="doc-example">
			<div class="am-scrollable-horizontal am-scrollable-vertical">
				<table
					class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
						<tr>
							<th width="5%"><span>
								<label class="am-checkbox">
									<input type="checkbox" onclick="allCheck(this)" data-am-ucheck/>&nbsp;全选
								</label>
							</span></th>
							<th><span>名称</span></th>
							<th><span>优惠券编码</span></th>
							<th><span>类型</span></th>
							<th><span>限额</span></th>
							<th><span>次数限制</span></th>
							<th><span>有效期</span></th>
							<th><span>状态</span></th>
							<th><span>创建时间</span></th>
							<th><span>创建人</span></th>
							<th class="table-title"><span>操作</span></th>
						</tr>
					</thead>

					<tbody id="tabS_02" align="center">
						<c:if test="${couponCodeDTOList.size()>0}">
							<c:forEach items="${couponCodeDTOList}" var="couponCodeDTO">
								<tr>
									<td>
										<label class="am-checkbox">
											<input type="checkbox" name="ids" value="${couponCodeDTO.id}" data-am-ucheck/>
										</label>
									</td>
									<td>${couponCodeDTO.title}</td>
									<td>${couponCodeDTO.couponCode}</td>
									<td><c:if test="${couponCodeDTO.type==1 }">折扣券</c:if> <c:if
											test="${couponCodeDTO.type==2 }">定额券</c:if> <c:if
											test="${couponCodeDTO.type==3 }">会员定额券</c:if></td>
									<td>${couponCodeDTO.limitAmount }</td>
									<td><c:if test="${couponCodeDTO.useType==1 }">无限</c:if> <c:if
											test="${couponCodeDTO.useType==2 }">正常</c:if></td>
									<td><fmt:formatDate value="${couponCodeDTO.startTime}"
											type="both" pattern="yyyy-MM-dd" />~<fmt:formatDate
											value="${couponCodeDTO.endTime}" type="both"
											pattern="yyyy-MM-dd" /></td>
									<td><c:if test="${couponCodeDTO.status==1 }">未使用</c:if> <c:if
											test="${couponCodeDTO.status==2 }">已使用</c:if> <c:if
											test="${couponCodeDTO.status==3 }">过期</c:if> <c:if
											test="${couponCodeDTO.status==4 }">作废</c:if></td>
									<td><fmt:formatDate value="${couponCodeDTO.createTime}"
											type="both" pattern="yyyy-MM-dd" /></td>
									<td>${couponCodeDTO.optuserName }</td>

									<td>
										<button
											class="am-btn am-btn-default am-btn-xs am-text-secondary"
											onclick="couponDetail(${couponCodeDTO.id})">
											<span class="am-icon-pencil-square-o"></span>查看
										</button> <c:if test="${couponCodeDTO.status==1 }">
											<button
												class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"
												onclick="waste(${couponCodeDTO.id})">
												<span class=""></span>作废
											</button>
										</c:if> <c:if test="${couponCodeDTO.status!=1 }">
											<button
												class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only am-btn am-btn-default  am-active">
												<span class=""></span>作废
											</button>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if
							test="${couponCodeDTOList.size()==0||couponCodeDTOList==null}">
							<tr>
								<td colspan="11">
									<div data-am-alert=""
										class="am-alert am-alert-secondary mt20 mb50">
										<center>
											<big> <i class="am-icon-frown-o vam"
												style="font-size: 34px;"></i> <span class="vam ml10">还没有任务信息！</span></big>
										</center>
									</div>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<!-- /pageBar begin -->
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			<!-- /pageBar end -->
		</div>
	</div>
</body>
</html>

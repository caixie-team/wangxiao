<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>优惠券列表</title>
<link rel="stylesheet" type="text/css"
	href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
function cancel(){
	$("#type").val(-1);
	$("#keyWord").val("");
	$("#keyWordType").val(-1);
	$("#useType").val(-1);
	$("#isCouponCode").val(-1);
	/* 清空下拉列表用 */
	$("#type").find('option').eq(0).attr('selected', true);
	$("#keyWordType").find('option').eq(0).attr('selected', true);
	$("#useType").find('option').eq(0).attr('selected', true);
	$("#isCouponCode").find('option').eq(0).attr('selected', true);
	/* 清空时间用 */
	$("#startCreateTime:first").attr("value","");
	$("#endCreateTime:first").attr("value","");
}
//查看优惠券
function couponView(couponId){
	window.location.href="${cxt}/admin/coupon/detail/"+couponId
}
//生成编号
function generated(couponId){
	window.location.href="${cxt}/admin/coupon/createcode/"+couponId
}
       
</script>
</head>
<body>
	<!-- 内容 开始  -->
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">优惠券管理</strong> / <small>优惠券列表</small>
		</div>
	</div>
	<hr />
	<div class="mt20 am-padding admin-content-list">
		<form action="${ctx}/admin/coupon/page" name="searchForm"
			id="searchForm" method="post" class="am-form">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage"
				value="${page.currentPage}" />
			<!-- /tab1 begin-->

			<div class="am-tab-panel am-fade am-active am-in">
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">关键字</div>
					<div class="am-u-sm-4 am-u-end">
						<input class="am-input-sm" type="text" name="queryCoupon.keyWord"
							id="keyWord" value="${queryCoupon.keyWord}" />
					</div>
					<div class="am-u-sm-4 am-u-end">
						<select
							data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}"
							name="queryCoupon.keyWordType" id="keyWordType"
							style="width: 100px">
							<option value="-1">请选择</option>
							<option value="1"
								<c:if test="${queryCoupon.keyWordType==1 }">selected="selected"</c:if>>优惠券名称</option>
							<option value="2"
								<c:if test="${queryCoupon.keyWordType==2 }">selected="selected"</c:if>>适用项目</option>
							<option value="3"
								<c:if test="${queryCoupon.keyWordType==3 }">selected="selected"</c:if>>适用课程</option>
						</select>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-6 am-u-end am-text-left">
					<div class="am-u-sm-4 am-text-right">创建开始时间</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" data-am-datepicker="{format: 'yyyy-mm-dd'}"
							readonly class="am-input-sm" id="startCreateTime"
							name="queryCoupon.startCreateTime"
							value="${queryCoupon.startCreateTime}" />
					</div>
				</div>
				<div class="mt20 clear"></div>

				<div class="am-g am-margin-top am-u-sm-6 am-u-end am-text-left">
					<div class="am-u-sm-4 am-text-right">创建结束时间</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" data-am-datepicker="{format: 'yyyy-mm-dd'}"
							readonly name="queryCoupon.endCreateTime" class="am-input-sm"
							id="endCreateTime" value="${queryCoupon.endCreateTime}" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">优惠类型</div>
					<div class="am-u-sm-8 am-u-end">
						<select
							data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}"
							name="queryCoupon.type" id="type">
							<option value="-1">请选择</option>
							<option value="1"
								<c:if test="${queryCoupon.type==1 }">selected="selected"</c:if>>折扣券</option>
							<option value="2"
								<c:if test="${queryCoupon.type==2 }">selected="selected"</c:if>>定额券</option>
							<option value="3"
								<c:if test="${queryCoupon.type==3 }">selected="selected"</c:if>>会员定额券</option>
						</select>
					</div>
				</div>

				<div class="mt20 clear"></div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">次数限制</div>
					<div class="am-u-sm-8 am-u-end">
						<select
							data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}"
							name="queryCoupon.useType" id="useType">
							<option value="-1">请选择</option>
							<option value="1"
								<c:if test="${queryCoupon.useType==1 }">selected="selected"</c:if>>无限</option>
							<option value="2"
								<c:if test="${queryCoupon.useType==2 }">selected="selected"</c:if>>正常</option>
						</select>
					</div>
				</div>


				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">生成编码</div>
					<div class="am-u-sm-8 am-u-end">
						<select
							data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}"
							name="queryCoupon.isCouponCode" id="isCouponCode">
							<option value="-1">请选择</option>
							<option value="1"
								<c:if test="${queryCoupon.isCouponCode==1 }">selected="selected"</c:if>>未生成</option>
							<option value="2"
								<c:if test="${queryCoupon.isCouponCode==2 }">selected="selected"</c:if>>已生成</option>
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
						<a class="am-btn am-btn-success" href="${cxt}/admin/coupon/doadd"><em
							class="am-icon-plus">&nbsp;</em>新建优惠卷</a>
					</div>
				</div>
			</div>
			
			<div class="am-u-sm-12 am-u-md-4">
				<div class="am-input-group am-input-group-sm">
					<button type="button" class="am-btn am-btn-warning"
						onclick="goPage(1)">查询</button>
					<button type="button" class="am-btn am-btn-danger"
						onclick="cancel()">清空</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 表格样式 三   开始-->
	<div class="mt20">
		<div class="doc-example">
			<div class="am-scrollable-horizontal">
				<table
					class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
						<tr>
							<th><span>ID</span></th>
							<th><span>名称</span></th>
							<th><span>类型</span></th>
							<th><span>使用限额</span></th>
							<th><span>次数限制</span></th>
							<th><span>有效期</span></th>
							<th><span>生成量/使用量</span></th>
							<th><span>生成编码</span></th>
							<th><span>创建时间</span></th>
							<th><span>创建人</span></th>
							<th><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:if test="${couponDTOList.size()>0}">
							<c:forEach items="${couponDTOList}" var="coupon">
								<tr>
									<td>${coupon.id }</td>
									<td>${coupon.title }</td>
									<td><c:if test="${coupon.type==1 }">折扣券</c:if> <c:if
											test="${coupon.type==2 }">定额券</c:if> <c:if
											test="${coupon.type==3 }">会员定额券</c:if></td>
									<td>${coupon.limitAmount }</td>
									<td><c:if test="${coupon.useType==1 }">无限</c:if> <c:if
											test="${coupon.useType==2 }">正常</c:if></td>
									<td><fmt:formatDate value="${coupon.startTime}"
											type="both" pattern="yyyy-MM-dd" />~<fmt:formatDate
											value="${coupon.endTime}" type="both" pattern="yyyy-MM-dd" />
									</td>
									<td>${coupon.totalNum}/${coupon.userNum}</td>
									<td><c:if test="${coupon.isCouponCode==0 }">未生成</c:if> <c:if
											test="${coupon.isCouponCode==1 }">已生成</c:if></td>

									<td><fmt:formatDate value="${coupon.createTime}"
											type="both" pattern="yyyy-MM-dd" /></td>
									<td>${coupon.optuserName }</td>
									<td>
										<button
											class="am-btn am-btn-default am-btn-xs am-text-secondary"
											onclick="couponView(${coupon.id})">
											<span class="am-icon-pencil-square-o"></span>查看
										</button> <c:if test="${coupon.isCouponCode==0 }">
											<button
												class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"
												onclick="generated(${coupon.id})">
												<span class=""></span>生成编码
											</button>
										</c:if> <c:if test="${coupon.isCouponCode==1 }">
											<button
												class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only am-btn am-btn-default  am-active">
												<span class=""></span>已生成
											</button>
										</c:if>
									</td>

								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${couponDTOList.size()==0||couponDTOList==null}">
							<tr>
								<td align="center" colspan="16">
									<div data-am-alert=""
										class="am-alert am-alert-secondary mt20 mb50">
										<center>
											<big><i class="am-icon-frown-o vam"
												style="font-size: 34px;"></i> <span class="vam ml10">还没有优惠券！</span>
											</big>
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
	<!-- 内容 结束 -->
</body>
</html>

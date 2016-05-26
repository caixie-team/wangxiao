<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>促销卡列表</title>
<link rel="stylesheet" type="text/css"
	href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
<link rel="stylesheet" type="text/css"
	href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-timepicker-addon.css?v=${v}" />
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	
	function cancel() {
		$("#createUser").val('');
		$("#cardName").val('');
		$("#trxStatus").val(0);
		$("#trxStatus").find("option").eq(0).attr("selected",true);
		$("#payType").val(0);
		$("#payType").find("option").eq(0).attr("selected",true);
		$("#cardNode").val('');
		$("#cardId").val('');
		$("#email").val('');
		/* 清空时间用 */
		$("#startCreateTime:first").attr("value","");
		$("#endCreateTime:first").attr("value","");
	}
	//关闭课程卡
	function cLoseCard(cardId, index) {
		if(confirm('是否确认作废该课程卡')){
		$.ajax({
			url : "${ctx}/admin/card/closecard?id=" + cardId,
			post : "post",
			success : function(result) {
				window.location.reload();
			},
			error : function(error) {
				alert(error.responseText);
			}
		})
		}
	}
	
	//excel导出
	function exportExcel(){
		document.searchForm.action="/admin/card/exportCard";
		$("#searchForm").submit();
		document.searchForm.action="/admin/card/cardlist";
	}
	
</script>
</head>
<body>
	<div class="admin-content">
		<div class="am-margin">
			<div class="am-cf">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">线下卡管理</strong> / <small>卡号导出</small>
				</div>
			</div>
			<hr />
			<div class="mt20 am-padding admin-content-list">
				<form action="${ctx}/admin/card/cardlist" name="searchForm"
					id="searchForm" method="post" class="am-form">
					<input id="pageCurrentPage" type="hidden" name="page.currentPage"
						value="${page.currentPage}" />
					<div class="am-tab-panel am-fade am-active am-in">
						<div class="am-g am-margin-top am-u-sm-4">
							<div class="am-u-sm-4 am-text-right">创建人</div>
							<div class="am-u-sm-8 am-u-end">
								<input class="am-input-sm" id="createUser" type="text"
									name="queryCardCode.createUser"
									value="${queryCardCode.createUser}" />
							</div>
						</div>
						<div class="am-g am-margin-top am-u-sm-4">
							<div class="am-u-sm-4 am-text-right">课程卡名称</div>
							<div class="am-u-sm-8 am-u-end">
								<input class="am-input-sm" type="text" id="cardName"
									name="queryCardCode.name" value="${queryCardCode.name}" />
							</div>
						</div>
						<div class="am-g am-margin-top am-u-sm-4">
							<div class="am-u-sm-4 am-text-right">创建开始时间</div>
							<div class="am-u-sm-8 am-u-end">
								<input class="am-input-sm"
									data-am-datepicker="{format: 'yyyy-mm-dd'}" readonly 
									type="text" id="startCreateTime" name="queryCardCode.beginTime"
									value="<fmt:formatDate value='${queryCardCode.beginTime}' pattern='yyyy-MM-dd' />" />
							</div>
						</div>
						<div class="mt20 clear"></div>
						<div class="am-g am-margin-top am-u-sm-4">
							<div class="am-u-sm-4 am-text-right">主课程卡id</div>
							<div class="am-u-sm-8 am-u-end">
								<input class="am-input-sm" type="text" id="cardId" onkeyup="this.value=this.value.replace(/\D/g,'')" name="queryCardCode.cardId" value="${queryCardCode.cardId}" />
									
							</div>
						</div>
						<div class="am-g am-margin-top am-u-sm-4">
							<div class="am-u-sm-4 am-text-right">生成编码</div>
							<div class="am-u-sm-8 am-u-end">
								<input class="am-input-sm" type="text" id="cardNode"
									name="queryCardCode.cardNode" value="${queryCardCode.cardNode}" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
							</div>
						</div>
						<div class="am-g am-margin-top am-u-sm-4">
							<div class="am-u-sm-4 am-text-right">课程卡类型</div>
							<div class="am-u-sm-8 am-u-end">
								<select id="payType" name="queryCardCode.type"
									data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
									<option value="0">--请选择--</option>
									<option value="1"
										<c:if test="${queryCardCode.type==1 }">selected="selected"</c:if>>课程卡</option>
									<option value="2"
										<c:if test="${queryCardCode.type==2 }">selected="selected"</c:if>>充值卡</option>
									<option value="3"
										<c:if test="${queryCardCode.type==3 }">selected="selected"</c:if>>学员卡</option>
								</select>
							</div>
						</div>
						<div class="mt20 clear"></div>
						<div class="am-g am-margin-top am-u-sm-4">
							<div class="am-u-sm-4 am-text-right">课程卡状态</div>
							<div class="am-u-sm-8 am-u-end">
								<select id="trxStatus" name="queryCardCode.status"
									data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
									<option value="">---请选择---</option>
									<option value="INIT"
										<c:if test="${queryCardCode.status=='INIT'}">selected="selected"</c:if>>未使用</option>
									<option value="USED"
										<c:if test="${queryCardCode.status=='USED'}">selected="selected"</c:if>>已使用</option>
									<option value="OVERDUE"
										<c:if test="${queryCardCode.status=='OVERDUE'}">selected="selected"</c:if>>已过期</option>
									<option value="CLOSE"
										<c:if test="${queryCardCode.status=='CLOSE'}">selected="selected"</c:if>>已作废
									</option>
								</select>
							</div>
						</div>
						<div class="am-g am-margin-top am-u-sm-4">
							<div class="am-u-sm-4 am-text-right">创建结束时间</div>
							<div class="am-u-sm-8 am-u-end">
								<input class="am-input-sm" type="text"
									data-am-datepicker="{format: 'yyyy-mm-dd'}" readonly
									name="queryCardCode.endTime" id="endCreateTime"
									value="<fmt:formatDate value='${queryCardCode.endTime}' pattern='yyyy-MM-dd' />" />
							</div>
						</div>
						<div class="am-g am-margin-top am-u-sm-4">
							<div class="am-u-sm-4 am-text-right">用户邮箱</div>
							<div class="am-u-sm-8 am-u-end">
								<input class="am-input-sm" type="text"
									name="queryCardCode.email" id="email"
									value='${queryCardCode.email}' />
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
								<a class="am-btn am-btn-success" href="javascript:exportExcel()"><em
									class="">&nbsp;</em>导出</a>
							</div>
						</div>
					</div>

					<div class="am-u-sm-12 am-u-md-4">
						<div class="am-input-group am-input-group-sm">
							<button type="button" class="am-btn am-btn-warning"
								onclick="goPage(1)">
								<span class="am-icon-search"></span> 查询
							</button>
							<button type="button" class="am-btn am-btn-danger"
								onclick="cancel()">清空</button>

							<button type="button" class="am-btn am-btn-danger"
								onclick="exportExcel()">导出Excel</button>
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
									<th width="5%"><span>ID</span></th>
									<th>卡名称</th>
									<th>类型</th>
									<th>金额</th>
									<th>有效期</th>
									<th>生成编码</th>
									<th>密码</th>
									<th>创建时间</th>
									<th>创建人</th>
									<th>订单编号</th>
									<th>用户邮箱</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody align="center">
								<c:if test="${!empty cardCodeList}">
									<c:forEach items="${cardCodeList}" var="card" varStatus="count">
										<tr>
											<td>${card.id}</td>
											<td>${card.name }</td>
											<td><c:if test="${card.type==1}">课程卡</c:if> <c:if
													test="${card.type==2 }">充值卡</c:if> <c:if
													test="${card.type==3 }">学员卡</c:if></td>
											<td>${card.money}</td>
											<td><fmt:formatDate value="${card.beginTime}"
													type="both" pattern="yyyy-MM-dd  " />~<fmt:formatDate
													value="${card.endTime}" type="both" pattern="YYYY-MM-dd " /></td>
											<td>${card.cardCode}</td>
											<td>${card.cardCodePassword}</td>
											<td><fmt:formatDate value="${card.createTime}"
													type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${card.createUser}</td>
											<td>${card.requestId}</td>
											<td>${card.email}</td>
											<td><span id="status${count.count}"> <c:if
														test="${card.status=='INIT'}">
												未使用
										</c:if> <c:if test="${card.status=='USED'}">
											已使用
										</c:if> <c:if test="${card.status=='OVERDUE'}">
											已过期
										</c:if> <c:if test="${card.status=='CLOSE'}">
											已关闭
										</c:if>
											</span></td>
											<td>
												<div class="am-btn-toolbar">
													<div class="am-btn-group am-btn-group-xs">
														<c:if test="${card.status=='INIT'}">
															<button
																class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"
																onclick="cLoseCard(${card.id},${count.count})">
																<span class="am-icon-trash-o"></span>作废
															</button>
														</c:if>
													</div>
												</div>
											</td>

										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty cardCodeList}">
									<tr>
										<td align="center" colspan="13">
											<div data-am-alert=""
												class="am-alert am-alert-secondary mt20 mb50">
												<center>
													<big><i class="am-icon-frown-o vam"
														style="font-size: 34px;"></i> <span class="vam ml10">还没有优惠券！</span>
													</big>
												</center>
											</div>
										</td></c:if>
							</tbody>
						</table>
					</div>
					<!-- /pageBar begin -->
					<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					<!-- /pageBar end -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>

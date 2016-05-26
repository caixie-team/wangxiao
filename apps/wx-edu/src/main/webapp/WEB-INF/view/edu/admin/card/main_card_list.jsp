<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>促销卡列表</title>
<script type="text/javascript">

	function cancel() {
		$("#createUser").val('');
		$("#cardName").val('');
		$("#trxStatus").val(0);
		$("#payType").val(0);
		$("#payType").find("option").eq(0).attr("selected",true);
		//清空时间
		$("#startCreateTime:first").attr("value","");
		$("#endCreateTime:first").attr("value","");
		}
	
	function isavalibleDialog(cardId){
		var str = "此作废,会将该组下的所有的课程卡作废";
		dialogFun("学员列表",str,2,'javascript:cLoseCard('+cardId+')');
	}
	//关闭课程卡
	function cLoseCard(cardId) {
		$.ajax({
			url : "${ctx}/admin/card/closemaincard/"+ cardId,
			type : "post",
			dataType:"json",
			async:false,
			success : function(result) {
			}
		})
		
	}
	
	//课程卡详情页面
	function detailsCard(mianCardId){
		window.location.href="/admin/card/cardlist?queryCardCode.cardId="+mianCardId;
	}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">线下卡管理</strong> / <small>线下卡列表</small>
		</div>
	</div>
	<hr />
	<div class="mt20 am-padding admin-content-list">
		<form action="${ctx}/admin/card/mainlist" name="searchForm"
			id="searchForm" method="post" class="am-form">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage"
				value="${page.currentPage}" />
			<div class="am-tab-panel am-fade am-active am-in">
				<div class="am-g am-margin-top am-u-sm-6">
					<div class="am-u-sm-4 am-text-right">创建人</div>
					<div class="am-u-sm-8 am-u-end">
						<input class="am-input-sm" id="createUser" type="text"
							name="queryMainCard.createUser"
							value="${queryMainCard.createUser}" />
					</div>
				</div>

				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">创建开始时间</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" data-am-datepicker="{format: 'yyyy-mm-dd'}"
							readonly class=" am-input-sm" id="startCreateTime"
							name="queryMainCard.beginTime"
							value="<fmt:formatDate value='${queryMainCard.beginTime}' pattern='yyyy-MM-dd'/>" />
					</div>
				</div>
				<div class="mt20 clear"></div>
				<div class="am-g am-margin-top am-u-sm-6 ">
					<div class="am-u-sm-4 am-text-right">课程卡类型</div>
					<div class="am-u-sm-8 am-u-end">
						<select
							data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}"
							id="payType" name="queryMainCard.type">
							<option value="0">--请选择--</option>
							<option value="1"
								<c:if test="${queryMainCard.type==1 }">selected="selected"</c:if>>课程卡</option>
							<option value="2"
								<c:if test="${queryMainCard.type==2 }">selected="selected"</c:if>>充值卡</option>
							<option value="3"
								<c:if test="${queryMainCard.type==3 }">selected="selected"</c:if>>学员卡</option>
						</select>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-6">
					<div class="am-u-sm-4 am-text-right">创建结束时间</div>
					<div class="am-u-sm-8 am-u-end">
						<input class="am-input-sm" type="text"
							data-am-datepicker="{format: 'yyyy-mm-dd'}" readonly
							name="queryMainCard.endTime" id="endCreateTime"
							value="<fmt:formatDate value='${queryMainCard.endTime}' pattern='yyyy-MM-dd' />" />
					</div>
				</div>
				<div class="mt20 clear"></div>
			</div>
		</form>
	</div>
	
	<div class="mt20">
		<div class="am-g">
			<div class="am-u-md-6"></div>
			<div class="am-u-sm-12 am-u-md-4">
				<div class="am-input-group am-input-group-sm">
					<button type="button" class="am-btn am-btn-warning"
						onclick="goPage(1)">
						<span class="am-icon-search"></span> 查询
					</button>
					<button type="button" class="am-btn am-btn-danger"
						onclick="cancel()">清空</button>
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
							<th width="8%"><span>ID</span></th>
							<th><span>卡名称</span></th>
							<th><span>类型</span></th>
							<th><span>金额</span></th>
							<th><span>有效期</span></th>
							<th><span>创建时间</span></th>
							<th><span>课程名称</span></th>
							<th><span>生成卡个数</span></th>
							<th><span>创建人</span></th>
							<th><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:if test="${!empty mainCardList}">
							<c:forEach items="${mainCardList}" var="card" varStatus="count">
								<tr>
									<td>${card.id}</td>
									<td>${card.name }</td>
									<td><c:if test="${card.type==1}">课程卡</c:if> <c:if
											test="${card.type==2 }">充值卡</c:if> <c:if
											test="${card.type==3 }">学员卡</c:if></td>
									<td>${card.money}</td>
									<td><fmt:formatDate value="${card.beginTime}" type="both"
											pattern="yyyy-MM-dd  " />~<fmt:formatDate
											value="${card.endTime}" type="both" pattern="YYYY-MM-dd " /></td>
									<td><fmt:formatDate value="${card.createTime}" type="both"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${card.courseName}</td>
									<td>${card.num}</td>
									<td>${card.createUser}</td>
									<td>
										<button
											class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"
											onclick="isavalibleDialog(${card.id})">
											<span class="am-icon-trash-o"></span>作废
										</button>
										<button
											class="am-btn am-btn-default am-btn-xs am-text-secondary"
											onclick="detailsCard(${card.id})">
											<span class="am-icon-pencil-square-o"></span>详情
										</button>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty mainCardList}">
							<tr>
								<td align="center" colspan="16">
									<div data-am-alert=""
										class="am-alert am-alert-secondary mt20 mb50">
										<center>
											<big><i class="am-icon-frown-o vam"
												style="font-size: 34px;"></i> <span class="vam ml10">还没有课程卡！</span>
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
			<!-- /commonWrap -->
			<!-- 内容 结束 -->
		</div>
	</div>
</body>
</html>

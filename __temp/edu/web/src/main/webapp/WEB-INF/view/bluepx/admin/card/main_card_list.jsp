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
	$(function() {
		$("#startCreateTime,#endCreateTime").datetimepicker({
			regional : "zh-CN",
			changeMonth : true,
			changeYear : true,
			dateFormat : "yy-mm-dd",
			timeFormat : 'HH:mm:ss'
		})
	})
	function cancel() {
		$("#createUser").val('');
		$("#cardName").val('');
		$("#startCreateTime").val('');
		$("#endCreateTime").val('');
		$("#trxStatus").val(0);
		$("#payType").val(0);

	}
	//关闭课程卡
	function cLoseCard(cardId) {
		if(confirm('此作废,会将该组下的所有的课程卡作废')){
		$.ajax({
			url : "${ctx}/admin/card/closemaincard/"+ cardId,
			post : "post",
			success : function(result) {

			},
			error : function(error) {
			}
		})
		alert("操作成功！");
		}
	}
	
	//课程卡详情页面
	function detailsCard(mianCardId){
		window.location.href="/admin/card/cardlist?queryCardCode.cardId="+mianCardId;
	}
</script>
</head>
<body>
	<form action="${ctx}/admin/card/mainlist" name="searchForm"
		id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage"
			value="${page.currentPage}" />
		<!-- 内容 开始  -->
		<div class="page_head">
			<h4>
				<em class="icon14 i_01"></em>&nbsp;<span>线下卡管理</span> &gt; <span>线下卡列表</span>
			</h4>
		</div>
		<div class="mt20">
			<div class="commonWrap">
				<table cellspacing="0" cellpadding="0" border="0" width="100%"
					class="commonTab01">
					<caption>
						<div class="capHead">
							<div class="w50pre fl">
								<ul class="ddBar">
									<li><span class="ddTitle"><font>创建人：</font></span><input
										id="createUser" type="text" name="queryMainCard.createUser"
										value="${queryMainCard.createUser}" class="ml10" /></li>
									<li><span class="ddTitle"><font>创建开始时间：</font></span> <input
										type="text" id="startCreateTime"
										name="queryMainCard.beginTime"
										value="<fmt:formatDate value='${queryMainCard.beginTime}' pattern='yyyy-MM-dd HH:mm:ss' /> "
										class="ml10" /></li>
								</ul>
							</div>
							<div class="w50pre fl">
								<ul class="ddBar">
									<li><span class="ddTitle"><font>课程卡类型：</font></span> <select
										id="payType" name="queryMainCard.type" class="ml10">
											<option value="0">--请选择--</option>
											<option value="1"
												<c:if test="${queryMainCard.type==1 }">selected="selected"</c:if>>课程卡</option>
											<option value="2"
												<c:if test="${queryMainCard.type==2 }">selected="selected"</c:if>>充值卡</option>
											<option value="3"
												<c:if test="${queryMainCard.type==3 }">selected="selected"</c:if>>学员卡</option>
									</select></li>
									<li><span class="ddTitle"><font>创建结束时间：</font></span> <input
										type="text" name="queryMainCard.endTime" id="endCreateTime"
										value="<fmt:formatDate value='${queryMainCard.endTime}' pattern='yyyy-MM-dd HH:mm:ss' />"
										class="ml10" /></li>
								</ul>
							</div>
							<div class="w50pre fl" style="text-align: center;">
								<ul class="ddBar">
									<li>
										<input type="button" onclick="goPage(1)" value="查询" class="btn btn-danger ml10" /> 
										<input type="button" value="清空" class="btn btn-danger ml10" onclick="cancel()" />
									</li>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
						<div class="mt10 clearfix">
							<p class="fl czBtn">
								<%-- <span class="ml10"><a href="${cxt}/admin/card/tocreatecard"><em class="icon14 new">&nbsp;</em>新建课程卡</a></span> --%>
							</p>
							<p class="fr c_666"><span>虚拟卡列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
						</div>
					</caption>
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
									<td><c:if test="${card.type==1}">课程卡</c:if> 
										<c:if test="${card.type==2 }">充值卡</c:if>
										<c:if test="${card.type==3 }">学员卡</c:if></td>
									<td>${card.money}</td>
									<td><fmt:formatDate value="${card.beginTime}" type="both" pattern="yyyy-MM-dd  " />~<fmt:formatDate value="${card.endTime}" type="both" pattern="YYYY-MM-dd " /></td>
									<td><fmt:formatDate value="${card.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${card.courseName}</td>
									<td>${card.num}</td>
									<td>${card.createUser}</td>
									<td>
											<a class="ml10 btn smallbtn btn-y" href="javascript:cLoseCard(${card.id})">作废</a>
											<a class="ml10 btn smallbtn btn-y" href="javascript:detailsCard(${card.id})">详情</a>
										</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty mainCardList}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
										<span>还没有课程卡！！</span>
									</div>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<!-- /pageBar begin -->
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
				<!-- /pageBar end -->
			</div>
			<!-- /commonWrap -->
		</div>
		<!-- 内容 结束 -->
	</form>
</body>
</html>

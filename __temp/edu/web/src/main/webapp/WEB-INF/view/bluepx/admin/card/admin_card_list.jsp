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
		
		var mainCardId = '${queryCardCode.cardId}';
	})
	function cancel() {
		$("#createUser").val('');
		$("#cardName").val('');
		$("#startCreateTime").val('');
		$("#endCreateTime").val('');
		$("#trxStatus").val(0);
		$("#payType").val(0);
		$("#cardNode").val('');
		$("#cardId").val(0);
		$("#email").val('');

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
	<form action="${ctx}/admin/card/cardlist" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
		<!-- 内容 开始  -->
		<div class="page_head">
			<h4>
				<em class="icon14 i_01"></em>&nbsp;<span>线下卡管理</span> &gt; <span>生成编码列表</span>
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
										id="createUser" type="text" name="queryCardCode.createUser"
										value="${queryCardCode.createUser}" class="ml10" /></li>
									<li><span class="ddTitle"><font>课程卡名称：</font></span><input
										type="text" id="cardName" name="queryCardCode.name"
										value="${queryCardCode.name}" class="ml10" /></li>
									<li><span class="ddTitle"><font>创建开始时间：</font></span>
										<input type="text" id="startCreateTime" name="queryCardCode.beginTime"
										value="<fmt:formatDate value='${queryCardCode.beginTime}' pattern='yyyy-MM-dd HH:mm:ss' /> "
										class="ml10" /></li>
										
									<li><span class="ddTitle"><font>主课程卡id：</font></span>
										<input type="text" id="cardId" name="queryCardCode.cardId" class="ml10" value="${queryCardCode.cardId}"/>
									</li>
									<li><span class="ddTitle"><font>生成编码：</font></span>
										<input type="text" id="cardNode" name="queryCardCode.cardNode" class="ml10" value="${queryCardCode.cardNode}"/>	
									</li>
								</ul>
							</div>
							<div class="w50pre fl">
								<ul class="ddBar">
									<li><span class="ddTitle"><font>课程卡类型：</font></span> <select
										id="payType" name="queryCardCode.type" class="ml10">
											<option value="0">--请选择--</option>
											<option value="1"
												<c:if test="${queryCardCode.type==1 }">selected="selected"</c:if>>课程卡</option>
											<option value="2"
												<c:if test="${queryCardCode.type==2 }">selected="selected"</c:if>>充值卡</option>
											<option value="3"
												<c:if test="${queryCardCode.type==3 }">selected="selected"</c:if>>学员卡</option>
									</select></li>
									<li><span class="ddTitle"><font>课程卡状态：</font></span> <select
										id="trxStatus" name="queryCardCode.status" class="ml10">
											<option value="">---请选择---</option>
											<option value="INIT"
												<c:if test="${queryCardCode.status=='INIT'}">selected="selected"</c:if>>未使用</option>
											<option value="USED"
												<c:if test="${queryCardCode.status=='USED'}">selected="selected"</c:if>>已使用</option>
															<option value="OVERDUE"
												<c:if test="${queryCardCode.status=='OVERDUE'}">selected="selected"</c:if>>已过期</option>
															<option value="CLOSE"
												<c:if test="${queryCardCode.status=='CLOSE'}">selected="selected"</c:if>>已作废</option>
									</select></li>
									<li><span class="ddTitle"><font>创建结束时间：</font></span> <input
										type="text" name="queryCardCode.endTime" id="endCreateTime"
										value="<fmt:formatDate value='${queryCardCode.endTime}' pattern='yyyy-MM-dd HH:mm:ss' />"
										class="ml10" /></li>
								
									<li><span class="ddTitle"><font>用户邮箱：</font></span> <input
										type="text" name="queryCardCode.email" id="email"
											value='${queryCardCode.email}' 
										class="ml10" /></li>	
								</ul>
							</div>
							<div class="w50pre fl" style="text-align: center;">
								<ul class="ddBar">
									<li>
										<input type="button" onclick="goPage(1)" value="查询" class="btn btn-danger ml10" /> 
										<input type="button" value="清空" class="btn btn-danger ml10" onclick="cancel()" />
										<input type="button" onclick="exportExcel()" class="btn btn-danger ml10" value="导出Excel" />
									</li>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
						<div class="mt10 clearfix">
							<p class="fl czBtn">
								<%-- <span class="ml10"><a
									href="${cxt}/admin/card/tocreatecard"><em
										class="icon14 new">&nbsp;</em>新建课程卡</a></span> --%> <span class="ml10"><a
									href="javascript:exportExcel()"><em class="icon14 new">&nbsp;</em>导出</a></span>
							</p>
							<p class="fr c_666">
								<span>线下卡列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span>
							</p>
						</div>
					</caption>
					<thead>
						<tr>
							<th width="8%"><span>ID</span></th>
							<th><span>卡名称</span></th>
							<th><span>类型</span></th>
							<th><span>金额</span></th>
							<th><span>有效期</span></th>
							<th><span>生成编码</span></th>
							<th><span>密码</span></th>
							<th><span>创建时间</span></th>
							<th><span>创建人</span></th>
							<th><span>订单编号</span></th>
							<th><span>用户邮箱</span></th>
							<th><span>状态</span></th>
							<th><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:if test="${!empty cardCodeList}">
							<c:forEach items="${cardCodeList}" var="card" varStatus="count">
								<tr>
									<td>${card.id}</td>
									<td>${card.name }</td>
									<td><c:if test="${card.type==1}">课程卡</c:if> 
										<c:if test="${card.type==2 }">充值卡</c:if>
										<c:if test="${card.type==3 }">学员卡</c:if></td>
									<td>${card.money}</td>
									<td><fmt:formatDate value="${card.beginTime}" type="both"
											pattern="yyyy-MM-dd  " />~<fmt:formatDate
											value="${card.endTime}" type="both" pattern="YYYY-MM-dd " /></td>
									<td>${card.cardCode}</td>
									<td>${card.cardCodePassword}</td>
									<td><fmt:formatDate value="${card.createTime}" type="both"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${card.createUser}</td>
							<td>${card.requestId}</td>
									<td>${card.email}</td>
									<td><span id="status${count.count}"> <c:if test="${card.status=='INIT'}">
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
									<c:if test="${card.status=='INIT'}">
											<a class="ml10 btn smallbtn btn-y"
												href="javascript:cLoseCard(${card.id},${count.count})">作废</a>
										</c:if></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty cardCodeList}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
										<span>还没有虚拟卡！！</span>
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

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<script type="text/javascript">
function cancel(){
	$("#title").val("");
}
function allCheck(th){
	$("input[name='ids']:checkbox").prop('checked',th.checked);
}

//存放数据的数组
var myArrayMoveStock = new Array();
//将小页面被选中的入库明细信息带到大页面
function selectList() {
	if (initArray()) {
		//调用父页面的方法
		window.close();
	}
}

function initArray() {
	var qstChecked = $(".ids:checked");
	if (qstChecked.length == 0) {
		dialogFun("提示","请选择图文素材",0);
		return;
	}
	qstChecked.each(function() {
		toParentsValue($(this).val());
	});
	opener.getImageList(myArrayMoveStock);
	quxiao();
}
//把选中的一条记录放到数组中
function toParentsValue(obj) {
	myArrayMoveStock.push(obj);
}
function quxiao() {
	window.close();
}
</script>
</head>
<body>
	<!-- 内容 开始  -->
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">微信管理</strong> / <small>微信图文素材列表</small>
		</div>
	</div>
	<hr />
	<!-- /tab1 begin-->
	<div class="mt20 admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<form action="${ctx}/admin/weixin/imagepage" name="searchForm"
				id="searchForm" method="post">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage"
					value="${page.currentPage}" />
				<div class="mt20 am-padding">

					<div class="am-g am-margin-top am-u-sm-12 am-text-left">
						<div class="am-u-sm-2 am-text-right">标题</div>
						<div class="am-u-sm-4 am-u-end">
							<input class="am-form-field" type="text" name="queryWeixinReply.title" id="title"
								value="${queryWeixinReply.title}" />
						</div>
						<div class="am-u-sm-4 am-u-end am-text-center">
							<input type="button" class="am-btn am-btn-danger" value="查询"
								onclick="goPage(1)" /> <input type="button"
								class="am-btn am-btn-danger" value="清空" onclick="cancel()" />
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
			</form>

		</div>
	</div>
	<div class="mt20">

		<div class="am-scrollable-horizontal am-scrollable-vertical">
			<table
				class="am-table am-table-bordered am-table-striped am-text-nowrap">
				<thead>
					<tr>
						<th>
						<label class="am-checkbox">
						<input data-am-ucheck type="checkbox"
								onclick="allCheck(this)" />全选
						</label>
						</th>
						<th><span>ID</span></th>
						<th><span>关键字</span></th>
						<th><span>标题</span></th>
						<th><span>创建时间</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
					<c:if test="${weixinReplys.size()>0}">
						<c:forEach items="${weixinReplys}" var="weixinReply">
							<tr>
								<td>
								<label class="am-checkbox">
								<input data-am-ucheck type="checkbox" class="ids" name="ids"
									value="${weixinReply.id}" />
								</label>
									</td>
								<td>${weixinReply.id }</td>
								<td>${weixinReply.keyword }</td>
								<td><font color="red">${weixinReply.title }</font></td>
								<td><fmt:formatDate value="${weixinReply.createTime}"
										type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${weixinReplys.size()==0||weixinReplys==null}">
						<tr>
							<td align="center" colspan="16">
								<div class="tips">
									<span>还没有微信图文素材！</span>
								</div>
							</td>
						</tr>
					</c:if>
					<tr>
						<td align="center" colspan="5"><a
							class="am-btn am-btn-danger" title="确定"
							href="javascript:selectList()">确定</a> <a
							class="am-btn am-btn-danger" title="返 回"
							href="javascript:window.close();">取消</a></td>
					</tr>
				</tbody>
			</table>
			<!-- /pageBar begin -->
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			<!-- /pageBar end -->
		</div>
		<!-- /commonWrap -->
	</div>
</body>
</html>

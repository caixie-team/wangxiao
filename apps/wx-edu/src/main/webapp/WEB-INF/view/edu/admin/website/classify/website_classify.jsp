<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类管理</title>
<script type="text/javascript">
function selectRedis(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
function cleanEmpty(memId){
	if(memId==0||memId==''){
		alert("该Redis-key不存在");
		return;
	}
	var judge=confirm("确定清空缓存吗？");
	if(judge==true){
	$.ajax({
		url:"${ctx}/admin/websitemem/removeMemKey",
		type:"post",
		dataType:"json",
		data:{"id":memId},
		success:function(result){
			if(result.success){
				alert("清除成功");
				return;
			}else{
				alert("请刷新重试");
				return;
			}
		}
	});
	}
}
 function deladmin(id){
		var judge=confirm("确定删除吗？");
		if(judge==true){
		$.ajax({
			url:"${ctx}/admin/websiteClassify/del",
			type:"post",
			dataType:"json",
			data:{"id":id},
			cache:true,
			async:false,
			success:function(result){
				if(result.success){
					alert("删除成功");
					$("#rem"+id).remove();
				}else{
					alert("请刷新重试");
					return;
				}
			}
		});
		}
	} 
 $(function(){
	 $("#type").val("${websiteClassify.type}");
 })
 function clean(){
	$("#name").val("");
	$("#type").val("");
	$('#searchForm select').each(function() {
		var _this = $(this);
		_this.find('option').eq(0).attr('selected', true);
	});
}
</script>
</head>
<body  >
<!-- 公共右侧样式 -->
	<div class="am-cf">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">分类管理</strong> / <small>网站分类列表</small></div>
    </div>
    <hr/>
<div class="mt20 am-padding admin-content-list">
	<form action="${ctx}/admin/websiteClassify/list" class="am-form" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					关键词
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" name="websiteClassify.name" value="${websiteClassify.name}" id="name" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					类型
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select name="websiteClassify.type" id="type" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
					    <option value="">--请选择--</option>
						<option value="article" >资讯</option>
						<option value="teacher" >讲师</option>
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
					<button class="am-btn am-btn-success" type="button" onclick="window.location.href='${ctx}/admin/websiteClassify/toadd'" title="添加"><span class="am-icon-plus"></span>添加</button>
				</div>
			</div>
		</div>
		<div class="am-u-sm-12 am-u-md-4">
			<div class="am-input-group am-input-group-sm">
				<button type="button" class="am-btn am-btn-warning" onclick="selectRedis()">查询</button>
				<button type="button" class="am-btn am-btn-danger" onclick="clean()">清空</button>
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
							<th width="3%"><span>ID</span></th>
							<th width="8%"><span>关键词</span></th>
							<th width="8%"><span>说明</span></th>
							<th width="10%"><span>所属类型</span></th>
							<th width="10%"><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:forEach items="${webClassifies}" var="webClassifies">
							<tr id="rem${webClassifies.id }">
								<td>${webClassifies.id}</td>
								<td>${webClassifies.name}</td>
								<td>${webClassifies.explain}</td>
								<td>
									<c:if test="${webClassifies.type=='article'}">资讯</c:if>
									<c:if test="${webClassifies.type=='teacher'}">讲师</c:if>
								</td>
								<td class="c_666 czBtn" align="center">
								<a class="btn smallbtn btn-y" href="${ctx}/admin/websiteClassify/toupdate/${webClassifies.id}"
									title="修改">修改</a>
								<a class="btn smallbtn btn-y" onclick="deladmin(${webClassifies.id})" href="javascript:void(0)"
									title="删除">删除</a>
									</td>
							</tr>
						</c:forEach>
						<c:if test="${empty webClassifies}">
							<tr>
							<td colspan="16">
									<div data-am-alert=""
										 class="am-alert am-alert-secondary mt20 mb50">
										<center>
											<big> <i class="am-icon-frown-o vam"
													 style="font-size: 34px;"></i> <span class="vam ml10">还没有分类信息！</span></big>
										</center>
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
		</div>
</div>

</body>
</html>
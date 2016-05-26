<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>微信回复列表</title>
<script type="text/javascript">
function clean(){
	$("#keyword").val("");
	$("#msgType").val("-1");
	$("#msgType").find('option').eq(0).attr('selected', true);
	
}
function delReply(id,msgType){
	dialogFun("微信素材列表","确定删除吗？",2,"javascript:delReplys("+id+","+msgType+")")
}       
function delReplys(id,msgType){
	$.ajax({
		url:"${cxt}/admin/weixin/del",
		data:{"weixinReply.id":id,"weixinReply.msgType":msgType},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.message=='true'){
				dialogFun("微信素材列表","删除成功？",5,window.location.href);
			}
		}
	});
}
$(function(){
	$("#msgType").val("${queryWeixinReply.msgType}");
})
</script>
</head>
<!-- 内容 开始  -->
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">微信管理</strong> /<small>微信素材列表</small></div>
</div>
<hr/>
<div class="mt20 am-padding admin-content-list">
	<div class="am-tab-panel am-fade am-active am-in">
		<form action="${ctx}/admin/weixin/page" class='am-form' name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<div class="am-g am-margin-top am-u-sm-6 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					类型：
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select name="queryWeixinReply.msgType" id="msgType" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="-1">--请选择--</option>
						<option value="1" >文本</option>
						<option value="2" >图文</option>
						<option value="3" >多图文</option>
					</select>
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-6 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					关键字：
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text"  class="am-input-sm"   name="queryWeixinReply.keyword" id="keyword" value="${queryWeixinReply.keyword}"/>
				</div>
			</div>
			<div class="mt20 clear"></div>
		</form>
	</div>
</div>
<div class="mt20">
	<div class="am-g">
		<div class="am-u-md-6">
			<div class="am-btn-toolbar">
				<div class="am-btn-group am-btn-group-xs">
					<a class="am-btn am-btn-success" title="新建文本" href="${cxt}/admin/weixin/doadd/1"><span class="am-icon-plus"></span> 新建文本</a>
					<a class="am-btn am-btn-success" title="新建图文" href="${cxt}/admin/weixin/doadd/2"><span class="am-icon-plus"></span> 新建图文</a>
					<a class="am-btn am-btn-success" title="新建多图文" href="${cxt}/admin/weixin/doadd/3"><span class="am-icon-plus"></span> 新建多图文</a>
				</div>
			</div>
		</div>
		<div class="am-u-sm-12 am-u-md-3">
			<div class="am-input-group am-input-group-sm">
				<span class="am-input-group-btn">
					<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">查询</button>
					<button type="button" class="am-btn am-btn-primary" onclick="clean()">清空</button>
				</span>
			</div>
		</div>
	</div>
</div>
<div class="mt20">
	<div class="am-scrollable-horizontal am-scrollable-vertical">
		<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
			<thead>
				<tr>
					<th  width="8%"><span>ID</span></th>
					<th><span>关键字</span></th>
					<th><span>图文标题</span></th>
					<th><span>类型</span></th>
					<th><span>封面url</span></th>
					<th><span>创建时间</span></th>
					<th><span>操作</span></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty weixinReplys }">
					<c:forEach  items="${weixinReplys}" var="weixinReply" >
						<tr>
							<td>${weixinReply.id }</td>
							<td>${weixinReply.keyword }</td>
							<td>${weixinReply.title }<c:if test="${weixinReply.msgType==1 }">--</c:if></td>
							<td>
								<c:if test="${weixinReply.msgType==1 }">文本</c:if>
								<c:if test="${weixinReply.msgType==2 }">图文</c:if>
								<c:if test="${weixinReply.msgType==3 }">多图文</c:if>
							</td>
							<td>${weixinReply.imageUrl }<c:if test="${weixinReply.msgType==1 }">--</c:if></td>
							<td>
								<fmt:formatDate value="${weixinReply.createTime}" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								<a class="am-btn am-btn-warning" href="${cxt}/admin/weixin/doupdate/${weixinReply.id}">修改</a>
								<a class="am-btn am-btn-danger" href="javascript:delReply(${weixinReply.id},${weixinReply.msgType })">删除</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty weixinReplys }">
					<tr>
						<td colspan="16">
							<div data-am-alert=""
								 class="am-alert am-alert-secondary mt20 mb50">
								<center>
									<big> <i class="am-icon-frown-o vam"
											 style="font-size: 34px;"></i> <span class="vam ml10">还没有微信素材！</span></big>
								</center>
							</div>
						</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</div>
<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</body>
</html>
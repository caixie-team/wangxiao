<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>礼品管理</title>
<script type="text/javascript">
function selectIntegral(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
function delGift(id){
	dialogFun("礼品管理","确定删除该礼品吗？",2,"javascript:delGiftAjax("+id+")");
}
function delGiftAjax(id){
		$.ajax({
			url:"${ctx}/admin/user/deletegift",
			data:{"id":id},
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.success){
					$("#rem"+id).remove();
					dialogFun("礼品管理",result.message,5,"");
					closeFun();
				}else{
					dialogFun("礼品管理",result.message,6,"");
					closeFun();
					return;
				}
			}
		});
}
function cleanIntegral(){
	$("#name").val('');
}
function toAdd(){
  window.location.href="${ctx}/admin/user/toaddgift";	
}

function updategift(id){
	window.location.href="${ctx }/admin/user/toupdategift/"+id;	
}

</script>
</head>
<body  >
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">礼品管理</strong> / <small>礼品管理</small>
		</div>
	</div>
	<hr/>
	<div class="capHead">
		<form action="${ctx}/admin/user/giftlist" method="post" id="searchForm" class="am-form">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<div class="mt20 am-padding admin-content-list">
				<div class="am-tab-panel am-fade am-active am-in">
					<div class="am-g am-margin-top am-u-sm-5">
						<div class="am-u-sm-4 am-text-right">
							<span><font>礼品名称：</font></span>
						</div>
						<div class="am-u-sm-8">
							<input type="text" name="userIntegralGift.name" id="name" value="${userIntegralGift.name}"/>
						</div>
					</div>
					<div class="mt20">
						<div class="am-g">
							<div class="am-u-md-6">
								<div class="am-btn-toolbar">
									<div class="am-btn-group am-btn-group-xs">
										&nbsp;
									</div>
								</div>
							</div>
							<div class="am-u-sm-12 am-u-md-3">
								<div class="am-input-group am-input-group-sm">
									<span class="am-input-group-btn">
										<button type="button" class="am-btn am-btn-warning" onclick="selectIntegral()">
											<span class="am-icon-search"></span> 搜索
										</button>
										<input type="button"  class="am-btn am-btn-danger" value="清空" name="" onclick="cleanIntegral()"/>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="clearfix"></div>
	<div class="mt10 clearfix">
		<button class="am-btn am-btn-success" title="新增" type="button" onclick="toAdd()">
			<span class="am-icon-plus"></span>新建礼品
		</button>
	</div>
	<div class="mt20"></div>
	<table class="am-table am-table-striped am-table-hover table-main am-table-bordered">
		<thead>
			<tr>
				<th style="text-align:center;" width="5%"><span>ID</span></th>
				<th style="text-align:center;"><span>礼品名称</span></th>
				<th style="text-align:center;"><span>使用积分</span></th>
				<th style="text-align:center;"><span>物品图片</span></th>
				<th style="text-align:center;"><span>创建时间</span></th>
				<th style="text-align:center;"><span>更新时间</span></th>
				<th style="text-align:center;"><span>操作</span></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty userIntegralGiftList}">
				<c:forEach items="${userIntegralGiftList}" var="ig">
					<tr id="rem${ig.id}" style="text-align:center;">
						<td>${ig.id}</td>
						<td>${ig.name}</td>
						<td>${ig.score}</td>
						<c:if test="${ig.courseId!=0}">
							<td>${ig.courseLogo}</td>
						</c:if>
						<c:if test="${ig.courseId==0}">
							<td>${ig.logo}</td>
						</c:if>
						<td><fmt:formatDate value="${ig.createTime }" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><fmt:formatDate value="${ig.updateTime }" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td class="c_666 czBtn" align="center">
							<button class="am-btn am-btn-default am-btn-xs am-text-secondary" title="修改" type="button" onclick="updategift(${ig.id})">
								<span class="am-icon-pencil-square-o"></span>修改
							</button>
							<button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" title="删除" type="button" onclick="delGift(${ig.id})">
								<span class="am-icon-trash-o"></span>删除
							</button>
						</td>
					</tr>
				</c:forEach>

			</c:if>
			<c:if test="${empty userIntegralGiftList }">
				<tr>
					<td align="center" colspan="16">
						<div class="am-alert am-alert-secondary mt20 mb50" data-am-alert="">
							<center><big>
								<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
								<span class="vam ml10">还没有礼品信息！</span>
							</big></center>
						</div>
					</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</body>
</html>
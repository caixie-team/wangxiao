<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>岗位列表</title>

<script type="text/javascript">
		function delUserGroup(id){
			dialogFun("岗位列表","是否要删除？",2,"javascript:delUserGroipQuery("+id+")");
		}
		function delUserGroipQuery(id){
			$.ajax({
				url:"${cxt}/admin/usergroup/delquery/"+id,
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.message=="true"){
						closeFun();
						dialogFun("推荐课程列表","删除成功",5,window.location.href);
					}
				}
			});
		}
		//查询
		function submitSearch(){
			$("#pageCurrentPage").val(1);
			$("#searchForm").submit();
		}
		//清空查询条件
		function submitclear(){
			$("#name").val('');
		}
		//修改
		function update(id){
			window.location.href='${ctx}/admin/userGroup/toUpdate/'+id;
		}
		//岗位课程
		function course(id){
			window.location.href='${ctx}/admin/cou/list?queryCourse.groupId='+id+'&reqType=sys';
		}
		// 岗位学员
		function user(id){
			window.location.href='${ctx}/admin/usergroup/getUserListByGroupId/'+id;
		}
	</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">岗位管理</strong> / <small>岗位列表</small></div>
	</div>
	<div class="mt20" />
	<form action="${ctx}/admin/usergroup/querylist" method="post" id="searchForm" name="searchForm" class="am-form">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
		<div class="mt20 am-padding admin-content-list">
			<div class="am-tab-panel am-fade am-active am-in">
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						名称
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="userGroup.name" value="${userGroup.name}" id="name" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						&nbsp;
					</div>
					<div class="am-u-sm-8 am-u-end">
						<button type="button" class="am-btn am-btn-warning" onclick="submitSearch()">查询</button>
						<button type="button" class="am-btn am-btn-primary" onclick="submitclear()">清空</button>
					</div>
				</div>
				<div class="mt20 clear"></div>
			</div>
		</div>
	</form>
	<div class="am-g">
		<div class="mt20 am-scrollable-horizontal">
			<div class="mt10">
				<table  class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
					<tr>
						<th width="10%">ID</th>
						<th width="20%">名称</th>
						<th width="30%">描述</th>
						<th width="40%">操作</th>
					</tr>
					</thead>
					<tbody>
					<c:if test="${not empty userGroupList}">
						<c:forEach items="${userGroupList}" var="group">
							<tr>
								<td>${group.id }</td>
								<td>${group.name }</td>
								<td>${group.description }</td>
								<td>
									<button class="am-btn am-btn-warning" type="button" onclick="delUserGroup(${group.id})">删除</button>
									<button class="am-btn am-btn-primary" type="button" onclick="update(${group.id})">修改</button>
									<button class="am-btn am-btn-danger" type="button" onclick="course(${group.id})">岗位课程</button>
									<button class="am-btn am-btn-primary" type="button" onclick="user(${group.id })">岗位学员</button>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty userGroupList}">
						<tr>
							<td colspan="4">
								<div data-am-alert=""
									 class="am-alert am-alert-secondary mt20 mb50">
									<center>
										<big> <i class="am-icon-frown-o vam"
												 style="font-size: 34px;"></i> <span class="vam ml10">还没有岗位信息！</span></big>
									</center>
								</div>
							</td>
						</tr>
					</c:if>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			</div>
		</div>
	</div>
</body>
</html>

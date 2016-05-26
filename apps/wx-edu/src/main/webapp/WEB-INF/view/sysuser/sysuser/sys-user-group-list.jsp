<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>岗位列表</title>
	<script type="text/javascript">
		function delUserGroup(id){
			dialogFun("员工列表","删除成功",2,"javascript:delUserGroups("+id+")");
			
				
		}
	function delUserGroups(id){
		$("#groupId").val(id);
		$("#updateGroupForm").submit();
	}
	</script>
</head>
<body>
	<form action="${ctx}/admin/usergroup/update" id="updateGroupForm" hidden="" method="post">
		<input name="userGroup.id"  id="groupId">
		<input name="userGroup.status" value="1" >
	</form>

	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">岗位管理</strong> / <small>岗位列表</small></div>
	</div>
	<hr>
	<div class="am-g">
		<div class="mt20 am-scrollable-horizontal">
			<div class="mt10">
				<form action="${ctx}/admin/usergroup/querylist" name="searchForm" id="searchForm" method="post">
					<table class="am-table am-table-bd am-table-striped admin-content-table">
						<thead>
						<tr>
							<th>ID</th><th>名称</th><th>描述</th><th>操作</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<c:if test="${not empty userGroupList}">
								<c:forEach items="${userGroupList}" var="group">
									<tr>
										<td>${group.id }</td>
										<td>${group.name }</td>
										<td>${group.description }</td>
										<td>
											<button class="am-btn am-btn-primary" type="button" onclick="window.location.href='/admin/cou/list?queryCourse.groupId=${group.id}&reqType=sys'" >岗位课程</button>
											<button class="am-btn am-btn-secondary" type="button" onclick="window.location.href='/admin/usergroup/getUserListByGroupId/${group.id }?type=sys'" >岗位员工</button>
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
														 style="font-size: 34px;"></i> <span class="vam ml10">还没有相关信息！</span></big>
											</center>
										</div>
									</td>
								</tr>
							</c:if>
						</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

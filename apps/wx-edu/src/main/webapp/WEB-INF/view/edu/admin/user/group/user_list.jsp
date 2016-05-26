<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>学员列表</title>
<script type="text/javascript">
function clean(){
	$("#nickname,#mobile,#email").val("");
	$("#userId").val("");
}

function addNewUser(){
	var groupid = "${groupId}";
	window.open('${cxt}/admin/usergroup/toAddUserForGroup/'+groupid+'/1',
		+'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=100,left=200,width=1300,height=800'
	);
}
function delUser(groupId){
	var userIds ='';
    $("input[name='userIds']:checked").each(function () {
    	userIds +=this.value +",";
    });
	if(userIds==''){
		dialogFun("学员列表","未选中要删除的学员",0);
		return;
	}
    //ajax移除学员
	dialogFun("学员列表","是否确认要删除这些学员？",2,"javascript:delAll('"+userIds+"',"+groupId+")");
    	
}


function submitSearch(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}

function addGroupReload(){
	goPage(1);
}

function deleteUserGroupMiddle(userId,groupId) {
	dialogFun("学员列表","是否确认要删除学员？",2,"javascript:del("+userId+","+groupId+")");
	
}
function del(userId,groupId){
	$.ajax({
		url : "${ctx}/admin/usergroup/deleteUserGroupMiddle",
		data : {
			"userId" : userId,
			"groupId" : groupId
		},
		async:false,
		type : "post",
		dataType : "json",
		success : function(result) {
			if (result.success) {
				goPage(1);
			} else {
				setTimeout(function(){
					dialogFun("学员列表","删除失败",0);
				},300);
			}
		}
	});
}
function delAll(userIds,groupId){
	$.ajax({
		url : "${ctx}/admin/usergroup/batchdeleteUserGroupMiddle",
		data : {
			"userIds" : userIds,
			"groupId" : groupId
		},
		async:false,
		type : "post",
		dataType : "json",
		success : function(result) {
			if (result.message == 'success') {
				goPage(1);
			} else {
				setTimeout(function(){
					dialogFun("学员列表","删除失败",0);
				},300);
			}
		}
	});
}

function allCheck(th) {
	$("input[name='userIds']:checkbox").prop('checked', th.checked);
}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">岗位管理</strong> / ${userGroup.name } / <small>岗位学员</small></div>
	</div>
	<hr/>
	<form action="${ctx}/admin/usergroup/getUserListByGroupId/${groupId }?type=sys" class="am-form" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<input type="hidden" name="type" value="${type }">
		<div class="mt20 am-padding admin-content-list">
			<div class="am-tab-panel am-fade am-active am-in">
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						学员ID
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="user.id" value="${user.id}" id="userId" onkeyup="this.value=this.value.replace(/\D/g,'')" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						邮箱
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="user.email" value="${user.email}" id="email" />
					</div>
				</div>
				<div class="mt20 clear"></div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						姓名
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="user.nickname" value="${user.nickname}" id="nickname" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						手机号
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="user.mobile" value="${user.mobile}" id="mobile" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
					</div>
				</div>
				<div class="mt20 clear"></div>
			</div>
		</div>
	</form>
		<div class="mt20">
			<div class="am-g">
				<div class="am-u-md-6">
					<div class="am-btn-toolbar">
						<div class="am-btn-group am-btn-group-xs">
							<button class="am-btn am-btn-success" type="button" title="添加新学员" onclick="addNewUser()"><span class="am-icon-plus"></span> 添加新学员</button>
							<button class="am-btn am-btn-success" type="button" title="删除学员" onclick="delUser(${groupId})"><span class="am-icon-trash-o"></span> 删除学员</button>
						</div>
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-3">
					<div class="am-input-group am-input-group-sm">
						<span class="am-input-group-btn">
							<button type="button" class="am-btn am-btn-warning" onclick="submitSearch()">查询</button>
							<button type="button" class="am-btn am-btn-primary" onclick="clean()">清空</button>
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="am-g">
			<div class="mt20 am-scrollable-horizontal">
				<div class="mt10">
					<table class="am-table am-table-bd am-table-striped admin-content-table">
						<thead>
							<tr>
								<th>
									<label class="am-checkbox">
										<input type="checkbox" value="" onclick="allCheck(this)" data-am-ucheck>
										全选
									</label>
								</th>
								<th>学员ID</th>
								<th>姓名</th>
								<th>邮箱</th>
								<th>手机</th>
								<th>注册时间</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${userListByGroupId.size()>0}">
								<c:forEach items="${userListByGroupId}" var="user">
									<tr>
										<td>
											<label class="am-checkbox">
												<input type="checkbox" name="userIds" value="${user.id}" data-am-ucheck>
											</label>
										<td>${user.id }</td>
										<td>${user.nickname }</td>
										<td>${user.email }</td>
										<td>${user.mobile }</td>
										<td><fmt:formatDate value="${user.createdate }" type="both" /></td>
										<td id="isavalible${user.id}">
											<c:if test="${user.isavalible==0 }">正常</c:if>
											<c:if test="${user.isavalible==1 }">禁用</c:if>
										</td>
										<td>
											<button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="deleteUserGroupMiddle(${user.id},${groupId})"><span class="am-icon-trash-o"></span> 删除学员</button>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${userListByGroupId.size()==0||userListByGroupId==null}">
								<tr>
									<td colspan="12">
										<div data-am-alert=""
											 class="am-alert am-alert-secondary mt20 mb50">
											<center>
												<big> <i class="am-icon-frown-o vam"
														 style="font-size: 34px;"></i> <span class="vam ml10">还没有学员信息！</span></big>
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

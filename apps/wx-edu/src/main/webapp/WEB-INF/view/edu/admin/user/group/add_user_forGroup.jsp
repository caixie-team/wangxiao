<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>学员列表</title>
<script type="text/javascript">
function submitSearch(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}

function addSubmit() {
	var groupId = "${groupId}";
	var imgIds = document.getElementsByName("ids");
	var ids = '';
	for (var i = 0; i < imgIds.length; i++) {
		if (imgIds[i].checked == true) {
			ids += imgIds[i].value + ",";
		}
	}
	$.ajax({
		url : "${ctx}/admin/usergroup/addGroupDetail",
		data : {
			"ids" : ids,
			"groupId" : groupId
		},
		async:false,
		type : "post",
		dataType : "json",
		success : function(result) {
			if (result.message == 'success') {
				
				dialogFun("学员列表 ","添加成功",1);
				window.opener.addGroupReload();
				window.close();
			} else {
				
				dialogFun("学员列表 ","添加失败",0);
				window.close();
			}
		}
	});
}

function allCheck(th) {
	$("input[name='ids']:checkbox").prop('checked', th.checked);
}

function clean(){
	$("#nickname").val("");
	$("#userId").val(0);
}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">学员管理</strong> / <small>学员列表</small></div>
	</div>
	<hr/>
	<form action="${ctx}/admin/usergroup/toAddUserForGroup/${groupId }/${notInGroup}" name="searchForm" id="searchForm" method="post" class="am-form">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
		<div class="mt20 am-padding admin-content-list">
			<div class="am-tab-panel am-fade am-active am-in">
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						学员ID
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="user.id" value="${user.id}" id="userId" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						昵称
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="user.nickname" value="${user.nickname}" id="nickname" />
					</div>
				</div>
				<div class="mt20 clear"></div>

				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					&nbsp;
				</div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						&nbsp;
					</div>
					<div class="am-u-sm-8 am-u-end">
						<button type="button" class="am-btn am-btn-danger" onclick="submitSearch()">查询</button>
						<button type="button" class="am-btn am-btn-danger" onclick="clean()">清空</button>
					</div>
				</div>
				<div class="mt20 clear"></div>
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
							<th width="8%">ID</th>
							<th width="8%">昵称</th>
							<th>邮箱</th>
							<th>手机</th>
							<th>注册时间</th>
							<th>状态</th>
						</tr>
						</thead>
						<tbody>
						<c:if test="${userListByCondition.size()>0}">
							<c:forEach items="${userListByCondition}" var="user">
								<tr>
									<td>
										<label class="am-checkbox">
											<input type="checkbox" name="ids" value="${user.id}" data-am-ucheck>
										</label>
									</td>
									<td>${user.id }</td>
									<td>${user.nickname }</td>
									<td>${user.email }</td>
									<td>${user.mobile }</td>
									<td><fmt:formatDate value="${user.createdate }" type="both" /></td>
									<td id="isavalible${user.id}">
										<c:if test="${user.isavalible==0 }">正常</c:if>
										<c:if test="${user.isavalible==1 }">禁用</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${userListByCondition.size()==0||userListByCondition==null}">
							<tr>
								<td colspan="16">
									<div data-am-alert=""
										 class="am-alert am-alert-secondary mt20 mb50">
										<center>
											<big> <i class="am-icon-frown-o vam"
													 style="font-size: 34px;"></i> <span class="vam ml10">还没有用户数据！</span></big>
										</center>
									</div>
								</td>
							</tr>
						</c:if>
						<tr>
							<td colspan="16" align="center">
								<button class="am-btn am-btn-danger" title="提 交" onclick="addSubmit()">确定</button>
								<button class="am-btn am-btn-danger" title="返 回" onclick="window.close()">取消</button>
							</td>
						</tr>
						</tbody>
					</table>
					<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
				</div>
			</div>
		</div>
	</form>
</body>
</html>

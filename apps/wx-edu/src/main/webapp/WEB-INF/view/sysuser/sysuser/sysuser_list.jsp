<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>员工列表</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">
	$(function (){
		$('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});
	})

var setting = {
		view:{
			showLine: true,
			showIcon: true,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable: true,
				idKey:'groupId',
				pIdKey:'parentGroupId',
				rootPid:''
			},
			key:{
				name:'groupName',
				title:'groupName'
			}
		},
		callback: {
			onClick: treeOnclick
		}
	};
	
	var treedata=${groupList};
	
	function treeOnclick(e,treeId, treeNode) {
		$("#userGroupId").val(treeNode.groupId);
		$("#userGroupName").val(treeNode.groupName);
		$("#distree").hide();
	}
	
	$().ready(function() {
		$.fn.zTree.init($("#ztreedemo"), setting, treedata);
		if($("#userGroupId").val()!="" && $("#userGroupId").val()!=0){
			var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
			var treeNode=zTree.getNodeByParam("groupId",$("#userGroupId").val(),null);
			if(treeNode!=null){
				$("#userGroupName").val(treeNode.groupName);
			}
		}
	});

		 //冻结、解冻
		 function freeze(userId,status){
		 var id= "#freeze"+userId;
		 var tdid="#tdstatus"+userId;
		 		var ss = $(id).html();		 		
		 		var vv = null;
		 		if(ss=='冻结'){
		 			vv = '解冻';
		 			tt="冻结";
		 		}else{
		 			vv = '冻结';
		 			tt="正常";
		 			ss='解冻';
		 		}
		 		dialogFun("提示","确定"+ss+"该用户吗?",2,"javascript:freezes("+userId+","+status+")");
            }
		 
		 
		 function freezes(userId,status){
			 var id= "#freeze"+userId;
			 var tdid="#tdstatus"+userId;
			 		var ss = $(id).html();		 		
			 		var vv = null;
			 		if(ss=='冻结'){
			 			vv = '解冻';
			 			tt="冻结";
			 		}else{
			 			vv = '冻结';
			 			tt="正常";
			 			ss='解冻';
			 		}
			  $.ajax({
                  url:"${ctx}/admin/user/freezeUser",
                  data:{"user.id":userId,"isavalible":status},
                  dataType:"json",
                  success:function(msg){
                  	if(msg.result=='success'){
                  		 $(id).html(vv);
                         $(tdid).html(tt);
                         closeFun();
                         window.location.reload();
                  	}else{
                  		dialogFun("提示",msg.result,0);
                  		
                  	}
                  }
              });
			 
		 }
			//搜索
			function search(){
				$("#pageCurrentPage").val(1);
				$("#searchForm").submit();
			}
			
			
			function submitclear(){
				$("#userGroupId").val(0);
				$("#userGroupName").val("");
				$("#nickname").val("");
				$("#realname").val("");
			}
			//清空专业的查询条件时同时清除考点
			function subject_cleantreevalue(){
				hideSubjectMenu();
				$("#subjectId").val(0);
				$("#userGroupName").val("");
			}
			function showSubjectMenu() {
				var cityObj = $("#userGroupName");
				var cityOffset = $("#userGroupName").offset();
				$("#distree").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
				$("body").bind("mousedown", onBodyDown);
			}
			function hideSubjectMenu() {
				$("#distree").fadeOut("fast");
				$("body").unbind("mousedown", onBodyDown);
			}
			function onBodyDown(event) {
				if (!(event.target.id == "userGroupName" || event.target.id == "distree" || $(event.target).parents("#distree").length>0)) {
					hideSubjectMenu();
				}
			}
			
			
			
	function nameandnickname(){
		var name = $('#nick').val();
		$("#nickname").val(name);
		$("#realname").val(name);
		var nickname = $('#nickname').val();
		var realname = $('#realname').val();
	}
			
    </script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">员工管理</strong> / <small>员工列表</small>
		</div>
	</div>
	<div class="mt20">
		<div class="mt20 am-padding admin-content-list">
				<div class="am-tab-panel am-fade am-active am-in">
				<form action="${ctx}/admin/user/listAllUser" class="am-form" name="searchForm" id="searchForm" method="post">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" /> 
				<input type="hidden" name="user.sysGroupId" id="userGroupId" value="${user.sysGroupId}" />
					<div class="am-g am-margin-top am-u-sm-6 am-text-left">
						<div class="am-u-sm-4 am-text-right">用户名</div>
						<div class="am-u-sm-8 am-u-end">
							<input type="text" name="user.nickname" id="nickname" class="am-input-sm" value="${user.nickname}" />
						</div>
					</div>
					<div class="am-g am-margin-top am-u-sm-6 am-text-left">
						<div class="am-u-sm-4 am-text-right">真实姓名</div>
						<div class="am-u-sm-8 am-u-end">
							<input type="text" name="user.realname" id="realname" class="am-input-sm" value="${user.realname}" />
						</div>
					</div>
					<div class="am-g am-margin-top am-u-sm-6 am-text-left">
						<div class="am-u-sm-4 am-text-right">部门</div>
						<div class="am-u-sm-8 am-u-end">
							<div id="doc-dropdown-justify-js" style="width: 200px">
								<div class="am-dropdown" id="doc-dropdown-js">
									<input type="text" class="am-input-sm am-dropdown-toggle"
										id="userGroupName" value="" readonly="readonly" />
									<!-- <input  type="text" class="am-input-sm" id="userGroupName" value="" readonly="readonly" onclick="showZtree()"/> -->
									<div id="distree" class="am-dropdown-content"
										style="overflow: auto; max-height: 300px;">
										<div id="ztreedemo" class="ztree"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input class="am-btn am-btn-warning" type="button" onclick="search()" value="查询" />
							<input class="am-btn am-btn-danger" type="button" value="清空" onclick="submitclear()" />
						</div>
					</div>
				</form>
			</div>
		</div>
		<%--<div class="mt20">--%>
			<%--<div class="am-g">--%>
				<%--<div class="am-u-md-6">--%>
					<%--<div class="am-btn-toolbar">--%>
						<%--<div class="am-btn-group am-btn-group-xs">--%>
							<%--<span>--%>
								<%--<a class="am-btn am-btn-success" href="${ctx}/admin/user/toAddUser" title="新建用户">新建用户</a>--%>
							<%--</span>--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="mt20" />
		<div class="am-scrollable-horizontal am-scrollable-vertical">
			<table
				class="am-table am-table-bordered am-table-striped am-text-nowrap">
				<thead>
					<tr>
						<th><span>ID</span></th>
						<th><span>用户名</span></th>
						<th><span>真实姓名</span></th>
						<th><span>所属部门</span></th>
						<th><span>所属岗位</span></th>
						<th><span>状态</span></th>
						<th><span>联系电话</span></th>
						<th><span>创建时间</span></th>
						<th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_01">
					<c:if test="${ empty list}">
						<c:forEach items="${userList }" var="user">
							<tr bgcolor="#FFFFFF" align="center">
								<td>${user.id }</td>
								<td class="yx2"><c:out value="${user.nickname }" /></td>
								<td class="yx2"><c:out value="${user.realname }" /></td>
								<td class="yx2">${user.sysGroupName}</td>
								<td class="yx2">${user.groupnames}</td>
								<td class="yx2" id="tdstatus${user.id}"><c:if
										test="${user.isavalible==0 }">正常</c:if> <c:if
										test="${user.isavalible==1 }">冻结</c:if></td>
								<td class="yx2"><c:out value="${user.mobile }" /></td>
								<td class="yx2"><fmt:formatDate value="${user.createdate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>

								<td class="yx2 lineheight180"><a
									class="am-btn am-btn-warning"
									href='${ctx}/admin/user/toEditUser?user.id=<c:out value="${user.id }"/>'>修改</a>
									<c:if test="${user.isavalible==0 }">
										<a class="am-btn am-btn-primary" id="freeze${user.id}"
											href='#' onclick="freeze(${user.id},${user.isavalible});">冻结</a>
									</c:if> <c:if test="${user.isavalible==1 }">
										<a class="am-btn am-btn-primary" href='#'
											id="freeze${user.id}"
											onclick="freeze(${user.id},${user.isavalible});">解冻</a>
									</c:if> <a class="am-btn am-btn-primary"
									href='${ctx}/admin/user/toUpdateUserPwd?user.id=<c:out value="${user.id }"/>'>修改密码</a>
									<a class="am-btn am-btn-success"
									href='${ctx}/admin/sys/loginlog/${user.id }'>查询登陆日志</a> <!--
								<a href="loginLog!getByUserId?queryLoginLogCondition.currentPage=1&queryLoginLogCondition.userId=<c:out value="userId"/>">查看登录日志</a>
								--></td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${userList.size()==0||userList==null}">
						<tr>
							<td colspan="12">
								<div data-am-alert=""
									class="am-alert am-alert-secondary mt20 mb50">
									<center>
										<big> <i class="am-icon-frown-o vam"
											style="font-size: 34px;"></i> <span class="vam ml10">还没有员工信息！</span></big>
									</center>
								</div>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<!-- /pageBar begin -->
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		<!-- /pageBar end -->

	</div>
</body>
</html>

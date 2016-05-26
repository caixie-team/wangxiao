<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>学员列表</title>
	<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
	<script type="text/javascript"src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}"></script>
	<script type="text/javascript">
		function isavalibleDialog(id,isavalible){
			var str = "";
			if(isavalible==1){str= "是否禁用该用户";}
			if(isavalible==0){str= "是否恢复该用户";}
			dialogFun("学员列表",str,2,'javascript:updateIsavalible('+id+','+isavalible+')');
		}
		function updateIsavalible(id,isavalible){
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"${ctx}/admin/user/updateIsavalible",
				data:{"user.id":id,"user.isavalible":isavalible},
				async:false,
				success:function(result){
					if(result.success){
						if(isavalible==1){
							$(".attr"+id).attr("onclick","isavalibleDialog("+id+",0,this)");
							$(".attr"+id).html("恢复");
							$(".attr"+id).attr("title","恢复");
							$("#isavalible"+id).html("禁用");
							//dialogFun("学员列表","禁用成功",1);
						}else{
							$(".attr"+id).attr("onclick","isavalibleDialog("+id+",1,this)");
							$(".attr"+id).html("禁用");
							$(".attr"+id).attr("title","禁用");
							$("#isavalible"+id).html("正常");
							//dialogFun("学员列表","恢复成功",1);
						}
					}
				}
			});
		}
		function clean(){
			$("#userId,#email,#mobile,#nickname,#startDate,#endDate").val("");
			$("#userGroupId").val(0);
			$("#userGroupName").val("");
			$("#registerFrom").val("");
			$('#searchForm select').each(function() {
				var _this = $(this);
				_this.find('option').eq(0).attr('selected', true);
			});
		}
		function userExcel(){
			$("#searchForm").prop("action","${ctx}/admin/user/export");
			$("#searchForm").submit();
			$("#searchForm").prop("action","${ctx}/admin/user/list");
		}
		//去选
		function getUserGroupByUserId(userId){
			window.location.href="${ctx}/admin/user/getuserGroupId/"+userId;
		}
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
			$("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
		}
		$().ready(function() {
			$('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});
			$.fn.zTree.init($("#ztreedemo"), setting, treedata);
			if($("#userGroupId").val()!="" && $("#userGroupId").val()!=0){
				var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
				var treeNode=zTree.getNodeByParam("groupId",$("#userGroupId").val(),null);
				if(treeNode!=null){
					$("#userGroupName").val(treeNode.groupName);
				}
			}
			$("#registerFrom").val('${user.registerFrom}');
		});
		function sendMessage(id){
			dialogFun("发送消息","",3,"",id);
		}
	</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">学员管理</strong> / <small>学员列表</small></div>
</div>
<hr />
<div class="mt20 am-padding admin-content-list">
	<div class="am-tab-panel am-fade am-active am-in">
		<form action="${ctx}/admin/user/list" class="am-form" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<input type="hidden" name="user.sysGroupId" id="userGroupId" value="${user.sysGroupId}"/>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					学员ID
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="number" class="am-input-sm" name="user.id" value="${user.id }" id="userId" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					开始时间
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="form-datetime-lang am-form-field am-input-sm" readonly name="user.startDate" value="${user.startDate }" id="startDate" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-u-end am-text-left">
				<div class="am-u-sm-4 am-text-right">
					结束时间
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="form-datetime-lang am-form-field am-input-sm" readonly name="user.endDate"  value="${user.endDate }" id="endDate" />
				</div>
			</div>
			<div class="mt20 clear"></div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					用户名
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text"class="am-input-sm"  name="user.nickname" value="${user.nickname}"  id="nickname" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					手机
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" pattern="^1[3-9]\d{9}$" maxlength="11" class="am-input-sm" name="user.mobile" value="${user.mobile}" id="mobile" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-u-end am-text-left">
				<div class="am-u-sm-4 am-text-right">
					邮箱
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="email"class="am-input-sm" name="user.email" value="${user.email}"  id="email" />
				</div>
			</div>
			<div class="mt20 clear"></div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					账号来源
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select name="user.registerFrom" id="registerFrom" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="">请选择</option>
						<option value="registerFrom">注册用户</option>
						<option value="adminFrom">后台开通用户</option>
						<option value="userCardFrom">课程卡用户</option>
						<option value="appFrom">app注册用户</option>
						<option value="qqFrom">QQ用户</option>
						<option value="weixinFrom">微信用户</option>
						<option value="weiboFrom">新浪微博用户</option>
						<option value="mobileFrom">微站注册用户</option>
					</select>
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-u-end am-text-left">
				<div class="am-u-sm-4 am-text-right">
					所属部门
				</div>
				<div class="am-u-sm-8 am-u-end">
					<div id="doc-dropdown-justify-js" style="width: 200px ">
						<div class="am-dropdown" id="doc-dropdown-js">
							<input type="text" class="am-input-sm am-dropdown-toggle" id="userGroupName" value="" readonly="readonly"/>
							<div class="am-dropdown-content" style="overflow: auto; max-height: 300px;">
								<div id="ztreedemo" class="ztree"></div>
							</div>
						</div>
					</div>
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
					<a class="am-btn am-btn-success" type="button" href="${ctx}/admin/user/toOneOpen" title="新建用户"><span class="am-icon-plus"></span> 新建用户</a>
					<a class="am-btn am-btn-success" type="button" href="${ctx}/admin/user/toBatchOpen" title="批量新建用户"><span class="am-icon-plus"></span> 批量新建用户</a>
				</div>
			</div>
		</div>
		<div class="am-u-sm-12 am-u-md-4">
			<div class="am-input-group am-input-group-sm">
				<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">查询</button>
				<button type="button" class="am-btn am-btn-danger" onclick="clean()">清空</button>
				<button type="button" class="am-btn am-btn-primary" onclick="userExcel()">导出Excel</button>
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
						<th width="5%"><span>ID</span></th>
						<th width="8%"><span>用户名</span></th>
						<th width="12%"><span>邮箱</span></th>
						<th width="12%"><span>手机</span></th>
						<th width="11%"><span>所属部门</span></th>
						<th width="12%"><span>注册时间</span></th>
						<th width="10%"><span>来源</span></th>
						<th width="10%"><span>状态</span></th>
						<th width="20%"><span>操作</span></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty list }">
						<c:forEach  items="${list}" var="user" >
							<tr>
								<td>${user.id }</td>
								<td>${user.nickname }</td>
								<td>${user.email }</td>
								<td>${user.mobile }</td>
								<td>${user.sysGroupName }</td>
								<td><fmt:formatDate value="${user.createdate }" type="both"/></td>
								<td>
									<c:if test="${user.registerFrom=='registerFrom' }">注册用户</c:if>
									<c:if test="${user.registerFrom=='mobileFrom' }">微站注册用户</c:if>
									<c:if test="${user.registerFrom=='adminFrom' }">后台开通用户</c:if>
									<c:if test="${user.registerFrom=='userCardFrom' }">课程卡用户</c:if>
									<c:if test="${user.registerFrom=='appFrom' }">app注册用户</c:if>
									<c:if test="${user.registerFrom=='qqFrom' }">QQ用户</c:if>
									<c:if test="${user.registerFrom=='weixinFrom' }">微信用户</c:if>
									<c:if test="${user.registerFrom=='weiboFrom' }">新浪微博用户</c:if>
								</td>
								<td id="isavalible${user.id}">
									<c:if test="${user.isavalible==0 }">正常</c:if>
									<c:if test="${user.isavalible==1 }">禁用</c:if>
								</td>
								<td>
									<button type="button" class="am-btn am-btn-primary" onclick="getUserGroupByUserId(${user.id})"><span class="am-icon-pencil-square-o"></span> 学员权限</button>
									<a type="button" class="am-btn am-btn-primary" href="${ctx}/admin/user/toupdatepwd/${user.id}"><span class="am-icon-pencil-square-o"></span> 修改密码</a>
									<div data-am-dropdown="" class="am-dropdown">
										<button data-am-dropdown-toggle="" class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
										<ul class="am-dropdown-content">
											<li><a href="${ctx}/admin/user/UserMsg/${user.id}">学员信息</a></li>
											<li><a href="javascript:void(0)" onclick="sendMessage('${user.id}')">发送消息</a></li>
											<li><a href="${ctx}/admin/user/toUserMovie/${user.id}">学员课程</a></li>
											<li>
												<c:if test="${user.isavalible==0}">
													<a class="attr${user.id}" href="javascript:void(0)" onclick="isavalibleDialog('${user.id}',1)">禁用</a>
												</c:if>

												<c:if test="${user.isavalible==1}">
													<a class="attr${user.id}" href="javascript:void(0)" onclick="isavalibleDialog('${user.id}',0)">恢复</a>
												</c:if>
											</li>
										</ul>
									</div>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty list }">
						<tr>
							<td colspan="9">
								<div data-am-alert="" class="am-alert am-alert-secondary mt20 mb50">
									<center>
										<big>
											<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
											<span class="vam ml10">还没有任务信息！</span>
										</big>
									</center>
								</div>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
	</div>
</div>
</body>
</html>

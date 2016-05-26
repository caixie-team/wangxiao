<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>修改员工</title>
	<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
	<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
	<script type="text/javascript">
		var _nickName = '${user.nickname }';
		var _mobile = '${user.mobile }';
		var _email = '${user.email }';
		$(function(){
			$('#updateForm').validator({
				validate: function(validity) {
					validity.valid = false;
					// 用户名
					if ($(validity.field).is('#nickname')) {
						var _this = $(validity.field);
						var _error = _this.parent().next();
						if(isNotEmpty(_nickName)&&_nickName==_this.val()){
							_error.html("");
							validity.valid = true;
							return validity;
						}
						if(isEmpty(_this.val())){
							_error.html("用户名不能为空");
							return validity;
						}
						return $.ajax({
							url:'${ctx}/existsNickName?nickName='+_this.val(),
							dataType: 'json',
						}).then(function(data) {
							if(!data){
								_error.html("");
								validity.valid = true;
							}else{
								_error.html("用户名已存在");
							}
							return validity;
						}, function() {
							_error.html("系统异常");
							return validity;
						});
					}
					// 真实姓名
					else if($(validity.field).is('#realname')){
						var _this = $(validity.field);
						var _error = _this.parent().next();
						if(isNotEmpty(_this.val())){
							_error.html("");
							validity.valid = true;
						}else{
							_error.html("真实姓名不能为空");
						}
						return validity;
					}
					// 邮箱
					else if($(validity.field).is('#email')){
						var _this = $(validity.field);
						var _error = _this.parent().next();
						if(isNotEmpty(_email)&&_email==_this.val()){
							_error.html("");
							validity.valid = true;
							return validity;
						}
						if(isEmpty(_this.val())){
							_error.html("请输入邮箱");
							return validity;
						}
						if(!isEmail(_this.val())){
							_error.html("邮箱不合法");
							return validity;
						}
						return $.ajax({
							url:'${ctx}/existsEmail?email='+_this.val(),
							dataType: 'json',
						}).then(function(data) {
							if(!data){
								_error.html("");
								validity.valid = true;
							}else{
								_error.html("邮箱已存在");
							}
							return validity;
						}, function() {
							_error.html("系统异常");
							return validity;
						});
					}
					// 验证mobile
					else if($(validity.field).is('#mobile')){
						var _this = $(validity.field);
						var _error = _this.parent().next();
						if(isNotEmpty(_mobile)&&_mobile==_this.val()){
							_error.html("");
							validity.valid = true;
							return validity;
						}
						if(isEmpty(_this.val())){
							_error.html("请输入手机号");
							return validity;
						}
						if(!isMobile(_this.val())){
							_error.html("手机号不合法");
							return validity;
						}
						return $.ajax({
							url:'${ctx}/existsMobile?mobile='+_this.val(),
							dataType: 'json',
						}).then(function(data) {
							if(!data){
								_error.html("");
								validity.valid = true;
							}else{
								_error.html("手机号已存在");
							}
							return validity;
						}, function() {
							_error.html("系统异常");
							return validity;
						});
					}
					// 部门
					else if($(validity.field).is('#userGroupName')){
						var _this = $(validity.field);
						var _error = _this.parent().parent().parent().next()
						if(isNotEmpty(_this.val())){
							_error.html("");
							validity.valid = true;
						}else{
							_error.html("部门不能为空");
						}
						return validity;
					}
					// 岗位
					else if($(validity.field).is('input[name="groupId"]')){
						var _this = $(validity.field);
						var _error = _this.parent().parent().parent().next()
						var i = $("input[name='groupId']:checked").length;
						if(i>0){
							_error.html("");
							validity.valid = true;
						}else{
							_error.html("至少选择一个岗位");
						}
						return validity;
					}
					// 权限
					else if($(validity.field).is('input[name="roleId"]')){
						var _this = $(validity.field);
						var _error = _this.parent().parent().parent().next()
						var i = $("input[name='roleId']:checked").length;
						if(i>0){
							_error.html("");
							validity.valid = true;
						}else{
							_error.html("至少选择一个角色权限");
						}
						return validity;
					}
					// 密码
					else if($(validity.field).is('#password')){
						var _this = $(validity.field);
						var _error = _this.parent().next();
						if(_this.val().length>=6&&_this.val().length<=20) {
							_error.html("");
							validity.valid = true;
						}else{
							_error.html("请输入6-20位密码");
						}
						return validity;
					}
					// 确认密码
					else if($(validity.field).is('#confirmPassword')){
						var _this = $(validity.field);
						var _error = _this.parent().next();
						if(_this.val().length>=6&&_this.val().length<=20){
							if(_this.val()==$('#password').val()){
								_error.html("");
								validity.valid = true;
							}else{
								_error.html("两次输入密码不相同");
							}
						}else{
							_error.html("请输入6-20位确认密码");
						}
						return validity;
					}
				},
				submit: function() {
					var formValidity = this.isFormValid();
					$.when(formValidity).then(function() {
						checkSubmit();
					}, function() {});
					return false;
				}
			});
		});
		function checkSubmit(){
			var artIds=document.getElementsByName("groupId");
			var checked=true;
			$(artIds).each(function(){
				if($(this).prop("checked")){
					checked=false;
				}
			})

			var groups = '';
			$("input[name='groupId']:checked").each(function() {
				groups += this.value + ",";
			});
			$("#hiddenGroupIds").val(groups);

			var roleId = '';
			$("input[name='roleId']:checked").each(function() {
				roleId += this.value + ",";
			});
			$("#hiddenRoleIds").val(roleId);
			$("#updateForm").submit();
		}
		//ztree start
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
			$.fn.zTree.init($("#ztreedemo"), setting, treedata);
			$('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});
		});
	</script>
</head>
<body>
<!-- 公共右侧样式 -->
<div class="am-cf">
  <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">员工管理</strong> / <small>修改员工 </small></div>
</div>
<hr/>
<!-- 公共右侧标题样式 结束-->
<div class="mt20">
	<div class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">基本信息(<i class="am-text-danger">*</i>为必填项)</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in ">
				<form name="updateForm" action="${ctx}/admin/user/editUser" id="updateForm" method="post" class="am-form" >
					<input name="hiddenGroupIds" type="hidden" id="hiddenGroupIds" />
					<input name="hiddenRoleIds" type="hidden" id="hiddenRoleIds" />
					<input type="hidden" name="user.id" value="${user.id }"/>
					<input type="hidden" name="user.isavalible" id="user.status" value="${user.isavalible }"/>
					<input type="hidden" name="user.sysGroupId" id="userGroupId" value="${user.sysGroupId }"/>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 用户名
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" name="user.nickname" class="am-input-sm" id="nickname" value="${user.nickname }"/>
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					 </div>
					 <div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 真实姓名
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" name="user.realname" class="am-input-sm" value="${user.realname }" id="realname"/>
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					 </div>
					 <div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 邮箱
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" class="am-input-sm" name="user.email" id="email" value="${user.email }" />
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					 </div>
					 <div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 手机号
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" class="am-input-sm" name="user.mobile" value="${user.mobile }" id="mobile"/>
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 所属部门
						</div>
						<div class="am-u-sm-8 am-u-md-6 am-form-horizontal">
							<div id="doc-dropdown-justify-js" style="width: 200px">
								<div class="am-dropdown" id="doc-dropdown-js">
									<input type="text" value="${user.sysGroupName }" placeholder="" class="am-input-sm am-dropdown-toggle" id="userGroupName" readonly="readonly"/>
									<div class="am-dropdown-content" style="overflow: auto; max-height: 300px;">
										<div id="ztreedemo" class="ztree" ></div>
									</div>
								</div>
							</div>
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 所属岗位
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<c:forEach var="group" items="${userGroupList}" varStatus="count">
								<div class="am-u-sm-6 am-u-md-6 am-u-end">
									<label class="am-checkbox">
										<c:if test="${group.check!=1}">
											<input type="checkbox" id="groupIdCheck" name="groupId" value="${group.id}" data-am-ucheck/>&nbsp;&nbsp;&nbsp;&nbsp;${group.name}
										</c:if>
										<c:if test="${group.check==1}">
											<input type="checkbox" id="groupIdCheck" checked="checked" name="groupId" value="${group.id}" data-am-ucheck/>&nbsp;&nbsp;&nbsp;&nbsp;${group.name}
										</c:if>
									</label>
								</div>
							</c:forEach>
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 角色权限
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<c:forEach var="role" items="${roleList}" varStatus="count">
							<div class="am-u-sm-6 am-u-md-6 am-u-end">
								<label class="am-checkbox">
								<c:if test="${role.check==1}">
									<input type="checkbox" data-am-ucheck checked="checked" name="roleId" value="${role.roleId}" />&nbsp;&nbsp;&nbsp;&nbsp;${role.roleName}
								</c:if>
								<c:if test="${role.check!=1}">
									<input type="checkbox" data-am-ucheck name="roleId" value="${role.roleId}" />&nbsp;&nbsp;&nbsp;&nbsp;${role.roleName}
								</c:if>
								</label>
							</div>
							</c:forEach>
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>
					<div class="am-g am-margin-top">
						 <div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						 <div class="am-u-sm-8 am-u-md-4">
							 <button class="am-btn am-btn-secondary" type="submit">提交</button>
							 <button class="am-btn am-btn-danger" type="button" onclick="history.go(-1)">返回</button>
						 </div>
						 <div class="am-u-sm-12 am-u-md-6"></div>
					 </div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>


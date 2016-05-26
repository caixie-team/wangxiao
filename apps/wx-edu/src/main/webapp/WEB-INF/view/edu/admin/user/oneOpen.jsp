<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加学员</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript"src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">
	$(function(){
		$('#addForm').validator({
			validate: function(validity) {
				validity.valid = false;
				// 用户名
				if ($(validity.field).is('#nickname')) {
					var _this = $(validity.field);
					var _error = _this.parent().next();
					if(isEmpty(_this.val())){
						_error.html("请输入用户名");
						return validity;
					}
					// 异步操作必须返回 Deferred 对象
					return $.ajax({
						url: '${ctx}/existsNickName?nickName='+_this.val(),
						dataType: 'json',
					}).then(function(data) {
						if(data){
							_error.html("用户名已存在");
						}else{
							_error.html("");
							validity.valid = true;
						}
						return validity;
					}, function() {
						_error.html("系统异常");
						return validity;
					});
				}
				// 手机号
				else if ($(validity.field).is('#mobile')) {
					var _this = $(validity.field);
					var _error = _this.parent().next();
					if(isEmpty(_this.val())){
						_error.html("请输入手机号");
						return validity;
					}
					if(!isMobile(_this.val())){
						_error.html("请输入正确的手机号");
						return validity;
					}
					// 异步操作必须返回 Deferred 对象
					return $.ajax({
						url:'${ctx}/existsMobile?mobile='+_this.val(),
						dataType: 'json',
					}).then(function(data) {
						if(data){
							_error.html("手机号已存在");
						}else{
							_error.html("");
							validity.valid = true;
						}
						return validity;
					}, function() {
						_error.html("系统异常");
						return validity;
					});
				}
				// 邮箱
				else  if ($(validity.field).is('#email')) {
					var _this = $(validity.field);
					var _error = _this.parent().next();
					if(isEmpty(_this.val())){
						_error.html("请输入邮箱");
						return validity;
					}
					if(!isEmail(_this.val())){
						_error.html("请输入正确的邮箱");
						return validity;
					}
					// 异步操作必须返回 Deferred 对象
					return $.ajax({
						url:'${ctx}/existsEmail?email='+_this.val(),
						dataType: 'json',
					}).then(function(data) {
						if(data){
							_error.html("邮箱已存在");
						}else{
							_error.html("");
							validity.valid = true;
						}
						return validity;
					}, function() {
						return validity;
					});
				}
				// 部门
				else if($(validity.field).is('#userGroupName')){
					var _this = $(validity.field);
					var _error = _this.parent().parent().parent().next()
					if(isNotEmpty(_this.val())) {
						_error.html("");
						validity.valid = true;
					}else{
						_error.html("请选择部门");
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
				}else if($(validity.field).is('#confirmPassword')){
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
					submitAddUser();
				}, function() {});
				return false;
			}
		});
	});

	//注册新用户
	function submitAddUser() {
		var level=$('input[name="userForm.level"]:checked').val();
		var groups ='';
		$("input[name='groupId']:checked").each(function () {
			groups +=this.value +",";
		});
		$("#groupIds").val(groups);
		$.ajax({
				url : baselocation + "/admin/user/open/oneuser",
				data : { "userForm.email":$("#email").val(),
						 "userForm.nickName":$("#regnickName").val(),
						 "userForm.mobile":$("#mobile").val(),
						 "userForm.password":$("#password").val(),
						 "userForm.groupIds":$("#groupIds").val(),
						 "userForm.confirmPassword":$("#againPassword").val(),
						 "userForm.level":level,
						 "userForm.sysGroupId":$("#userGroupId").val()
						},
				type : "post",
				dataType : "json",
				cache : false,
				async : false,
				success : function(result) {
					if(result.success==true) {
						dialogFun("添加学员","开通成功",1);
					}else{
						dialogFun("添加学员",result.message,0);
					}
				},
				error : function(error) {
				}
			});
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
	$(function(){
		$('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});
		$.fn.zTree.init($("#ztreedemo"), setting, treedata);
	})
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">开通学员账户</strong> / <small>单个开通账户</small></div>
</div>
<hr>
<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">基本信息(<i class="am-text-danger">*</i>为必填项)</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form class="am-form" id="addForm">
					<input name="userForm.groupIds" type="hidden" id="groupIds">
					<input type="hidden" name="userForm.sysGroupId" id="userGroupId"/>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 用户名
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" class="am-input-sm" required placeholder="请填写用户名" name="userForm.nickname" id="nickname">
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 手机号
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" class="am-input-sm" required placeholder="请填写手机号" name="userForm.mobile" id="mobile">
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 邮箱
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="email" class="am-input-sm" required placeholder="请填写邮箱" name="userForm.email" id="email">
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>

					<%-- 默认学员 --%>
					<input type="hidden" name="userForm.level" value="0"/>
					<%--<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 级别
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<label class="am-radio-inline">
								<input type="radio" name="userForm.level" value="0" data-am-ucheck onchange="levelTypeChange()"/>
								学员
							</label>
							<label class="am-radio-inline">
								<input type="radio" name="userForm.level" checked value="1" data-am-ucheck onchange="levelTypeChange()"/>
								员工
							</label>
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>--%>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 所属部门
						</div>
						<div class="am-u-sm-8 am-u-md-6 am-form-horizontal">
							<div id="doc-dropdown-justify-js" style="width: 200px">
							  <div class="am-dropdown" id="doc-dropdown-js">
							    <input type="text" class="am-input-sm am-dropdown-toggle" id="userGroupName" required readonly="readonly"/>
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
										<input type="checkbox" name="groupId"  value="${group.id}" data-am-ucheck/>${group.name}
									</label>
								</div>
							</c:forEach>
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 密码
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="password" class="am-input-sm" required placeholder="请填写密码" minlength="6" maxlength="20" name="userForm.password" id="password" >
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 确认密码
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="password" class="am-input-sm" required placeholder="请填写确认密码" minlength="6" maxlength="20" data-equal-to="#password" name="userForm.confirmPassword" id="confirmPassword">
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						<div class="am-u-sm-8 am-u-md-6">
							<button class="am-btn am-btn-danger" type="submit">提交</button>
							<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返回</button>
						</div>
						<div class="am-u-sm-12 am-u-md-4"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
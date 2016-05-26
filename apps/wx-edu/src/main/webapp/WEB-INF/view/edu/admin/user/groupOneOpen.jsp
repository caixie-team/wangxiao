<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
	<script type="text/javascript"src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<title>添加员工</title>
<script type="text/javascript">
	$(function() {
		$("input[name='groupId']").click(function(){
			var groups="";
			$("input[name='groupId']:checked").each(function () {
				groups +=this.value +",";
			});
			$("#groupIds").val(groups);
		});

		$('#openuser').validator({
			validate: function(validity) {
				validity.valid = false;
				if ($(validity.field).is('#regMobile')) {
					//验证手机正则
					if(!isMobile($("#regMobile").val())){
						return validity;
					}
					// 异步操作必须返回 Deferred 对象
					return $.ajax({
						url: '${ctx}/admin/user/LoginMobile',
						type: 'post',
						data: {'queryUserCondition.searchKey':$("#regMobile").val()},
						dataType: 'json'
					}).then(function(data) {
						if(data){
							validity.valid = true;
						}
						return validity;
					}, function() {
						return validity;
					});
				}else if ($(validity.field).is('#regnickName')) {
					if(isEmpty($("#regnickName").val())){
						return validity;
					}
					// 异步操作必须返回 Deferred 对象
					return $.ajax({
						url: '${ctx}/admin/user/LoginName',
						type: 'post',
						data: {'queryUserCondition.searchKey':$("#regnickName").val()},
						dataType: 'json'
					}).then(function(data) {
						if(data){
							validity.valid = true;
						}
						return validity;
					}, function() {
						return validity;
					});
				}else if ($(validity.field).is('#regEmail')) {
					//验证邮箱正则
					if(!isEmail($("#regEmail").val())){
						return validity;
					}
					// 异步操作必须返回 Deferred 对象
					return $.ajax({
						url: '${ctx}/admin/user/LoginEmail',
						type: 'post',
						data: {'queryUserCondition.searchKey':$("#regEmail").val()},
						dataType: 'json'
					}).then(function(data) {
						if(data){
							validity.valid = true;
						}
						return validity;
					}, function() {
						return validity;
					});
				}else if($(validity.field).is('#regpassword')){
					if($('#regpassword').val().length>=6&&$('#regpassword').val().length<=20) {
						validity.valid = true;
					}
					return validity;
				}else if($(validity.field).is('#againPassword')){
					if($('#againPassword').val().length>=6&&$('#againPassword').val().length<=20){
						if($('#againPassword').val()==$('#regpassword').val()){
							validity.valid = true;
						}
					}
					return validity;
				}else if($(validity.field).is('input[name="groupId"]')){
					$("input[name='groupId']").each(function(){
						if($(this).prop("checked")){
							validity.valid = true;
							return validity;
						}
					});
					return validity;
				}else {
					if(isNotEmpty($(validity.field).val())){
						validity.valid = true;
					}
					return validity;
				}
			},
			submit: function() {
				var formValidity = this.isFormValid();

				$.when(formValidity).then(function() {
					submitAddUser();
				}, function() {

				});
				return false;
			}
		});
	});
		//注册新用户 
	 	function submitAddUser() {
			var sysUserId = '${sysUserId}';
			$.ajax({
				url : baselocation + "/admin/user/open/oneuser",
				data : { "userForm.email":$("#regEmail").val(),
						 "userForm.nickName":$("#regnickName").val(),
						 "userForm.realname":$("#regRealName").val(),
						 "userForm.mobile":$("#regMobile").val(),
						 "userForm.password":$("#regpassword").val(),
						 "userForm.groupIds":$("#groupIds").val(),
						 "userForm.sysUserId":sysUserId,
						 "userForm.sysGroupId":$("#userGroupId").val()
						},
				type : "post",
				dataType : "json",
				cache : false,
				async : false,
				success : function(result) {
					if(result.success) {
						dialogFun("添加员工","开通成功",1);
						$("#regMobile,#regnickName,#regEmail,#regpassword,#regMobileError,#nickNameError,#emailError,#againPassword").val('');
						$("input[name='groupId']:checked").each(function () {
							$(this).attr("checked",false);
						});
					}else {
						dialog('注册提示',result.message,1);
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

		$().ready(function() {
			$('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});
			$.fn.zTree.init($("#ztreedemo"), setting, treedata);
		});


	$(function() {
		var $form = $('#openuser');
		var $tooltip = $('<div id="vld-tooltip">提示信息！</div>');
		$tooltip.appendTo(document.body);

		$form.validator();

		var validator = $form.data('amui.validator');

		$form.on('focusin focusout', '.am-form-error input', function(e) {
			if (e.type === 'focusin') {
				var $this = $(this);
				var offset = $this.offset();
				var msg = $this.data('foolishMsg') || validator.getValidationMessage($this.data('validity'));

				$tooltip.text(msg).show().css({
					left: offset.left + 10,
					top: offset.top + $(this).outerHeight() + 10
				});
			} else {
				$tooltip.hide();
			}
		});
	});
</script>
</head>
<body>

<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">岗位管理</strong> / <small>员工添加</small></div>
</div>
<hr>

<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in am-scrollable-vertical">
				<form id="openuser" name="openuser" class="am-form">
					<input type="hidden" name="userForm.sysGroupId" id="userGroupId"/>

					<div class="am-g am-margin-top am-form-group">
						<label for="regMobile" class="am-u-sm-4 am-u-md-2 am-text-right am-text-he">
							手机号
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input class="am-input-sm" type="text" name="userForm.mobile" id="regMobile" maxlength="11" required placeholder="请填写正确的手机号码" data-foolish-msg="请填写正确的手机号码！"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6 am-text-danger" id="regMobileError">必填</div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="regnickName" class="am-u-sm-4 am-u-md-2 am-text-right am-text-he">
							用户名
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input class="am-input-sm" type="text" name="userForm.nickname" id="regnickName" required placeholder="请填写用户名" data-foolish-msg="请填写用户名！"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6 am-text-danger" id="nickNameError">必填</div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="regRealName" class="am-u-sm-4 am-u-md-2 am-text-right am-text-he">
							真实姓名
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input class="am-input-sm" type="text" name="userForm.realname" id="regRealName" required placeholder="请填写真实姓名" data-foolish-msg="请填写真实姓名！"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6 am-text-danger" id="RealNameError">必填</div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label class="am-u-sm-4 am-u-md-2 am-text-right am-text-he">
							邮箱
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input class="am-input-sm" type="text" name="userForm.email" id="regEmail" required placeholder="请填写正确的邮箱地址" data-foolish-msg="请填写正确的邮箱地址！"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6 am-text-danger" id="emailError">必填</div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="userGroupName" class="am-u-sm-4 am-u-md-2 am-text-right am-text-he">
							所属部门
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<div id="doc-dropdown-justify-js" style="width: 200px">
								<div class="am-dropdown" id="doc-dropdown-js">
									<input type="text" class="am-input-sm am-dropdown-toggle" id="userGroupName" value="" readonly="readonly" required placeholder="请选择所属部门" data-foolish-msg="请选择所属部门！"/>
									<div class="am-dropdown-content" style="overflow: auto; max-height: 300px;">
										<div id="ztreedemo" class="ztree"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="am-hide-sm-only am-u-md-6 am-text-danger">必填</div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="groupIds" class="am-u-sm-4 am-u-md-2 am-text-right am-text-he">
							所属岗位
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input name="userForm.groupIds" type="hidden" id="groupIds">
							<c:forEach var="group" items="${userGroupList}" varStatus="count">
							<div class="am-u-sm-6 am-u-md-6 am-u-end">
								<label class="am-checkbox-inline">
									<input type="checkbox" name="groupId" value="${group.id}" data-am-ucheck> ${group.name}
								</label>
							</div>
							</c:forEach>
						</div>
						<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="regpassword" class="am-u-sm-4 am-u-md-2 am-text-right am-text-he">
							密码
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input class="am-input-sm" type="password" maxlength="20" name="userForm.password" id="regpassword" required placeholder="请填写密码" data-foolish-msg="请填写密码！"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6 am-text-danger">必填</div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="againPassword" class="am-u-sm-4 am-u-md-2 am-text-right am-text-he">
							确认密码
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input class="am-input-sm" type="password" maxlength="20" name="userForm.password" id="againPassword" required placeholder="请输入确认密码" data-foolish-msg="两次输入密码不同"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6 am-text-danger">必填</div>
					</div>

					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-4 am-text-right am-text-he">
							&nbsp;
						</label>
						<div class="am-u-sm-8 am-u-md-8">
							<button type="submit" class="am-btn am-btn-danger" <%--onclick="submitAddUser()"--%>>提交</button>
							<button href="javascript:history.go(-1);" title="返回" class="am-btn am-btn-danger">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
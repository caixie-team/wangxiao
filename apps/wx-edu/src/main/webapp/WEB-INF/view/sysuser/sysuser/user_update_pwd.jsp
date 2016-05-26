<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户密码修改</title>
	<script type="text/javascript">
		$(function(){
			$('#updateForm').validator({
				validate: function(validity) {
					validity.valid = false;
					// 密码
					if($(validity.field).is('#password')){
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
						$("#updateForm").submit();
					}, function() {});
					return false;
				}
			});
		})
	</script>
</head>
<body >
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户管理</strong> / <small>密码修改</small></div>
</div>		
<div class="mt20">
	<div class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">密码修改(<i class="am-text-danger">*</i>为必填项)</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form action="${ctx}/admin/user/updatePwd" name="updateForm" class="am-form" id="updateForm" method="post" >
					<input type="hidden" value="${user.id}" name="user.id"/>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 用户名
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							${user.nickname}
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 新密码
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="password" id="password" name="user.password" maxlength="20" class="am-input-sm" />
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i> 确认密码
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="password" id="confirmPassword" maxlength="20" class="am-input-sm" />
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

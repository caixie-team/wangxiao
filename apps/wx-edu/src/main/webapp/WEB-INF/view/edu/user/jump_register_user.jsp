<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>绑定用户</title>
</head>
<body>
<div class="container">
	<div class="bind-box">
		<h3><span class="c-333 fsize28">绑定账号</span></h3>
		<div class="third-part-bind">
			<div class="thi-p-pic">
				<img src="${userForm.photo }">
			</div>
			<h5 class="mt20"><span class="c-333 fsize20">${userForm.nickName }</span></h5>
			<p class="hLh30 c-666 fsize16 mt10">您正在使用<span class="c-blue ml5 mr5">
			<c:if test="${userForm.profileType=='QQ' }">
			QQ账号
			</c:if>
			<c:if test="${userForm.profileType=='SINA' }">
			新浪微博
			</c:if>
			<c:if test="${userForm.profileType=='WEIXIN' }">
			微信帐号
			</c:if>
			</span>登录账号</p>
		</div>
		<div class="bind-info-box">
			<p class="mt50 fsize14 c-666">已有账号，<a class="c-master" href="${ctx}/user/toBinding?nickName=${userForm.nickName}&profileType=${userForm.profileType}&value=${userForm.value}&photo=${userForm.photo}">绑定已有账号</a>。没有账号，注册新账号</p>
			<div class="bind-title">
				<section id="bind-title">
					<a  class="current" title="手机注册" href="javascript:;" onclick="changeRegisterType(this)">手机注册</a>
					<a title="邮箱注册" href="javascript:;" onclick="changeRegisterType(this)">邮箱注册</a>
				</section>
			</div>
			<form id="openRegister" method="post">
				<input type="hidden" id="profileType" name="userForm.profileType" value="${userForm.profileType }">
				<input type="hidden" id="value" name="userForm.value" value="${userForm.value }">
				<input type="hidden" id="nickName" name="userForm.nickName" value="${userForm.nickName }">
				<input type="hidden" id="photo" name="userForm.photo" value="${userForm.photo }">
				<input type="hidden" id="regType" name="regType" value="mobile">
				<section class="regis-box mt30" id="regis-box">
					<article>
						<div class="type_mobile">
							<label> <span class="fsize16 c-666">手机号</span> </label>
						</div>
						<div class="mt10 type_mobile">
							<input type="text" name="userForm.mobile" value=""  class="u-bind-input" id="mobile"  onblur="mobileCheck()" maxlength="11">
							<p class="c-red undis" id="mobile_error">请输入正确的手机号</p>
						</div>
						<div class="type_email undis">
							<label> <span class="fsize16 c-666">邮箱</span> </label>
						</div>
						<div class="mt10 type_email undis">
							<input type="text" name="userForm.email" value=""  class="u-bind-input" id="email" onblur="emailCheck()">
							<p class="c-red undis" id="email_error">请输入邮箱</p>
						</div>
						<div class="mt20">
							<label> <span class="fsize16 c-666">验证码</span> </label>
						</div>
						<div class="mt10 type_mobile">
							<div>
							<input type="text" name="randomCode" value=""  class="u-bind-input vam" id="randomCode" style="width: 62%;display: inline-block;" onblur="phoneCheckCode()">
							<a href="javascript:;" onclick="getPhoneCheckCode(this)" class="yzm-get">获取验证码</a>
							</div>
							<p class="c-red undis" id="randomCode_error">手机验证码</p>
						</div>
						<div class="mt10 undis type_email">
							<div>
							<input type="text" name="randomCodeEmail" value=""  class="u-bind-input vam" id="randomCodeEmail" style="width: 62%;display: inline-block;">
							<a href="javascript:;" onclick="getEmailCheckCode(this)" class="yzm-get">获取验证码</a>
							</div>
							<p class="c-red undis" id="randomCodeEmail_error">邮箱验证码不正确</p>
						</div>
						<div class="mt20">
							<label> <span class="fsize16 c-666" id="passwordError">密码</span> </label>
						</div>
						<div class="mt10">
							<input type="password" name="userForm.password" value=""  class="u-bind-input" id="password" onblur="passCheck()">
							<p class="c-red undis">密码</p>
						</div>
						<div class="mt20">
							<label> <span class="fsize16 c-666">确认密码</span> </label>
						</div>
						<div class="mt10">
							<input type="password" name="userForm.confirmPassword" value=""  class="u-bind-input" id="confirmPassword" onblur="passConfirmCheck()">
							<p class="c-red undis" id="confirmPasswordError">两次输入的密码不一致</p>
						</div>
						<div class="mt10"> 
							<label> 
								<input type="checkbox" id="nxbAgreement" class="vam" checked="checked" name="nxbAgreement"> 
								<span class="c-999 fsize12 vam">同意并阅读<a href="" class="c-blue">《用户注册协议》</a></span>
							</label> 
						</div>
						<div class="mt50 tac bind-btn"><a onclick="bindingRegister()" href="javascript:void(0)" class="bm-lr-btn disIb" title="立即注册" >确认注册</a></div>
					</article>
				</section>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctximg}/static/edu/js/login/binding.js"></script>
<script type="text/javascript">
		$(function() {
			cardChange("#bind-title>a","#regis-box>article","current"); //注册方式切换
		});
</script>
</body>
</html>

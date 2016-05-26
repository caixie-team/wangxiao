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
		<h3><span class="c-666 fsize28">绑定账号</span></h3>
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
			</span>登录</p>
		</div>
		<div class="bind-info-box">
			<p class="mt50 fsize14 c-666">已有账号，绑定已有账号。没有账号，<a class="c-master" href="${ctx}/user/toRegisterUser?nickName=${userForm.nickName}&profileType=${userForm.profileType}&value=${userForm.value}&photo=${userForm.photo}">注册新账号</a></p>
			<form id="openLogin" method="post">
				<input type="hidden" id="profileType" name="userForm.profileType" value="${userForm.profileType }">
				<input type="hidden" id="value" name="userForm.value" value="${userForm.value }">
				<input type="hidden" id="nickName" name="userForm.nickName" value="${userForm.nickName }">
				<input type="hidden" id="photo" name="userForm.photo" value="${userForm.photo }">
				<div class="mt30">
					<div>
						<label> <span class="fsize16 c-666">Email/手机号</span> </label>
					</div>
					<div class="mt10">
						<input type="text" name="userForm.email" value=""  class="u-bind-input" id="email">
					</div>
					<div class="mt20">
						<label> <span class="fsize16 c-666">密码</span> </label>
					</div>
					<div class="mt10">
						<input type="password" name="userForm.password" value=""  class="u-bind-input" id="password">
					</div>
					<div class="mt50 tac bind-btn"><a onclick="binding()" href="javascript:void(0)" class="bm-lr-btn disIb" title="确认绑定" >确认绑定</a></div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctximg}/static/edu/js/login/binding.js"></script>
</body>
</html>

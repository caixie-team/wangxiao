<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>注册</title>
	
</head>
<body>
<script src="${ctximg}/static/mobile/js/login/register.js" type="text/javascript"></script>
	<!-- logo && slogan begin -->
	<section class="sp-lo-sl lr-lo-sl">
		<h1 class="sp-lo"><span>&nbsp;</span></h1>
		<h2 class="sp-sl"><p id="slogan-txt" class="">传播知识的力量</p></h2>
	</section>
	<!-- logo && slogan end -->
	<section class="lr-main">
		<div class="lr-ul">
			<form id="regForm"  method="post" >
			<ul>
				<li id="email">
					<label><input type="text" name="userForm.email" onclick="clearError('email')" id="regEmail" value="" onblur="emailCheck()" placeholder="请输入邮箱地址" required="required"></label>
					<em class="error-em" id="emailError"></em> <!-- 教验错误状态给li添加class="error" -->
				</li>
				<li id="pwd">
					<label><input type="password" name="userForm.password" onclick="clearError('pwd')" onblur="passCheck()" id="regPwd" value="" placeholder="请输入密码" required="required"></label>
					<em class="error-em" id="pwdError"></em> <!-- 教验错误状态给li添加class="error" -->
				</li>
				<li id="confirmPwd">
					<label><input type="password" value="" name="confirmPassword" id="cusPwdConfirm" onclick="clearError('confirmPwd')" placeholder="再输入一次" required="required" onblur="passConfirmCheck()"></label>
					<em class="error-em" id="confirmPwdError"></em> <!-- 教验错误状态给li添加class="error" -->
				</li>
				<li id="mobile">
					<label><input type="text" name="userForm.mobile" onclick="clearError('mobile')" value="" id="regMobile" onblur="mobileCheck()" placeholder="请输入手机号码" required="required"></label>
					<em class="error-em" id="mobileError"></em> <!-- 教验错误状态给li添加class="error" -->
				</li>
				<li class="vCode" id="code">
					<label><input type="text" name="randomCode" id="randomcode" onclick="clearError('code')" value="" placeholder="验证码" required="required"></label>
					<span class="vCodeImg"><img src="${ctx}/ran/random" height="45" width="100" border="0" alt="" onclick="this.src='${ctx}/ran/random?random='+Math.random();"></span>
					<em class="error-em" id="codeError"></em> <!-- 教验错误状态给li添加class="error" -->
				</li>
				<li>
					<section class="lr-btn">
						<a href="javascript:void(0)" onclick="register()" title="">注 册</a>
					</section>
				</li>
			</ul>
			</from>
		</div>
		<div class="clearfix ulr-link">
			<span class="fr"><a href="/" title="">首页</a>|<a href="/mobile/login" title="">已有账号，去登录</a></span>
			<span class="fl"><input type="checkbox" name="t268xueAgreement" value="" checked="checked" class="xy-ok"><a href="javascript:void(0)" title="" style="margin-left: 0;vertical-align:middle;">同意用户协议</a></span>
		</div>
		<!-- /login register -->
		<%-- <section class="o-lr-box">
			<h5><span>使用第三方账号快捷登录</span></h5>
			<table width="100%" border="0" class="o-lr-tab">
				<tbody>
					<tr>
						<td width="50%" align="center" valign="middle">
							<span><a href="javascript:void(0)" onclick="oauthLogin('QQ')" title=""><img src="${ctximg }/static/mobile/img/qq-lr.png" width="18" height="18" alt="qq登录"><em>QQ账号</em></a></span>
						</td>
						<td width="50%" align="center" valign="middle">
							<span><a href="javascript:void(0)" onclick="oauthLogin('SINA')" title=""><img src="${ctximg }/static/mobile/img/wb-lr.png" width="18" height="18" alt="新浪微博"><em>新浪微博</em></a></span>
						</td>
					</tr>
				</tbody>
			</table>
		</section> --%>
		<!-- /other login style -->
	</section>

	<section class="s-l-bg" id="s-l-bg"></section><!-- /start-login-bg -->
	<script type="text/javascript">
	    window.onload = function() {slFun()};
	    window.onresize = function() {slFun()};
	    function slFun() {
	    	var sH = document.body.scrollHeight,
	    		slH = document.getElementById("s-l-bg");
	    	slH.style.height = sH + "px";
	    }
	</script>
</body>
</html>
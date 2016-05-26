<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>登录</title>
	
</head>
<body>
	<script src="${ctximg}/static/mobile/js/login/login.js" type="text/javascript"></script>
	<!-- logo && slogan begin -->
	<section class="sp-lo-sl lr-lo-sl">
		<h1 class="sp-lo"><span>&nbsp;</span></h1>
		<h2 class="sp-sl"><p id="slogan-txt" class="">传播知识的力量</p></h2>
	</section>
	<!-- logo && slogan end -->
	<section class="lr-main">
		<div class="lr-ul">
			<ul>
				<li id="email">
					<label><input type="text" name="" value="" id="userEmail" onclick="clearError(this)" placeholder="手机号/邮箱" required="required"></label>
					<em class="error-em">×</em> <!-- 教验错误状态给li添加class="error" -->
				</li>
				<li id="pwd">
					<label><input type="password" name="" value="" id="password" onclick="clearError(this)" placeholder="密 码" required="required"></label>
					<em class="error-em">×</em> <!-- 教验错误状态给li添加class="error" -->
				</li>
				<li>
					<section class="lr-btn">
						<a href="javascript:void(0)" onclick="pageLogin(2,'')" title="">登 录</a>
					</section>
				</li>
			</ul>
		</div>
		<div class="clearfix ulr-link">
			<span class="fr"><a href="/" title="">首页</a>|<a href="/mobile/register" title="">还没有账号，去注册</a></span>
			<span class="fl"><!-- <a href="" title="">忘记密码？</a> --></span>
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
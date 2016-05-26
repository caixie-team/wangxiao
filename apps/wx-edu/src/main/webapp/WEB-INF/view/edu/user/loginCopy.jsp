<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>登录</title>
</head>
<body>
<div class="bg-fa of" >
	<section class="container">
		<!-- login register box begin -->
		<div class="lr-box">
			<article class="lr-e-box">
				<section class="lr-e-wrap">
					<aside class="lr-bird">
						<span class="lr-b-icon"></span>
					</aside>
					<header class="comm-title"> 
						<h2 class="tac clearfix"> 
							<span class="c-333">欢迎登录</span> 
						</h2> 
					</header>
					<section class="lr-ul-wrap">
						<ul class="bm-lr-ul">
							<li>
								<div class="bm-lr-jy-box undis" id="login_account_div">
									<section class="hLh30 ml10 mr10 pr">
										<big onclick="$(this).parent().parent('.bm-lr-jy-box').hide();" title="关闭" class="bm-close">x</big>
										<div class="DT-arrow"><em>◆</em><span>◆</span></div>
										<em class="icon18 login-err-icon"></em><span class="fsize12 vam ml5" id="login_account_error">请输入正确的邮箱/手机号！</span>
									</section>
								</div>
								<label><em class="icon18 ml5 user-icon"></em>
								<input type="text" placeholder="邮箱/手机号" id="userEmail" onkeyup="enterSubmit(event,'pageLogin(2)')">
								</label>
							</li>
							<li>
								<div class="bm-lr-jy-box undis" id="login_password_div">
									<section class="hLh30 ml10 mr10 pr">
										<big onclick="$(this).parent().parent('.bm-lr-jy-box').hide();" title="关闭" class="bm-close">x</big>
										<div class="DT-arrow"><em>◆</em><span>◆</span></div>
										<em class="icon18 login-err-icon"></em><span class="fsize12 vam ml5" id="login_password_error">请输入正确的密码！</span>
									</section>
								</div>
								<label><em class="icon18 ml5 pwd-icon"></em>
								<input onkeyup="enterSubmit(event,'pageLogin(2)')"  type="password" id="userPassword" name="" value="" placeholder="密码">
								</label>
							</li>
						</ul>
						<div class="hLh30 mt10">
							<span class="fr"><a href="${ctx}/register" title="" class="c-orange fsize12">没有账号？去注册</a></span>
							<span>
								<label>
								<input type="checkbox" name="autoThirty" checked="checked" id="autoThirty">
								<small class="c-999 fsize12">自动登录</small></label>
								<small class="ml10"><a href="${ctx}/front/forget_passwd" title="" class="c-999 fsize12">忘记密码？</a></small>
							</span>
						</div>
						<div class="mt10"><a title="立即登录" class="bm-lr-btn" href="javascript:;" onclick="pageLogin(2)">立即登录</a></div>
						<div class="mt20">
							<section class="lr-kj-title">
								<span class="c-666 fsize16">快捷登录</span>
							</section>
							<section>
								<ul class="lr-kj-ul">
									<li class="qq-kj"><a href="javascript:oauthLogin('QQ')" title="QQ登录"></a></li>
									<li class="wx-kj"><a href="javascript:oauthLogin('WEIXIN')" title="微信登录"></a></li>
									<li class="wb-kj"><a href="javascript:oauthLogin('SINA')" title="微博登录"></a></li>
								</ul>
								<div class="clear"></div>
							</section>
						</div>
					</section>
				</section>
			</article>
		</div>
		<!-- login register box end -->
	</section>
</div>
<script type="text/javascript" src="${ctximg}/static/edu/js/login/login.js"></script>
<script>
		$(function() {
			birdFun(); //切换鸟
		})
		//切换鸟
		var birdFun = function() {
			$(".bm-lr-ul input[type='password']").focusin(function() {
				$(".lr-b-icon").css("background-image","url(${ctx}/static/nxb/web/img/ce.png)");
			}).focusout(function() {
				$(".lr-b-icon").css("background-image","url(${ctx}/static/nxb/web/img/oe.png)");
			})
		}
	</script>
</body>
</html>

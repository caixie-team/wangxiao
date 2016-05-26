<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>注册</title>
</head>
<body>
<div class="bg-fa of">
	<section class="container">
		<!-- login register box begin -->
		<div class="lr-box lr-reg-box">
			<article class="lr-e-box">
				<section class="lr-e-wrap">
					<aside class="lr-bird">
						<span class="lr-b-icon"></span>
					</aside>
					<header class="comm-title"> 
						<h2 class="tac clearfix"> 
							<span class="c-333">新用户注册</span> 
						</h2> 
					</header>
					<div class="lr-reg-title">
						<section id="lr-reg-title" class="c-tab-title"> 
							<a class="current" title="手机注册" href="javascript:void(0)">手机注册</a> 
							<a title="邮箱注册" href="javascript:void(0)">邮箱注册</a>
						</section>
					</div>
					<div class="clear"></div>
					<div id="lr-reg-cont">
						<section class="lr-ul-wrap">
							<ul class="bm-lr-ul">
								<li class="type_mobile">
									<div class="bm-lr-jy-box undis" id="register_phone_div">
										<section class="hLh30 ml10 mr10 pr">
											<big onclick="$(this).parent().parent('.bm-lr-jy-box').hide();" title="关闭" class="bm-close">x</big>
											<div class="DT-arrow"><em>◆</em><span>◆</span></div>
											<em class="icon18 login-err-icon"></em><span class="fsize12 vam ml5" id="register_phone_error">请输入正确的手机号！</span>
										</section>
									</div>
									<label><em class="icon18 ml5 user-icon"></em><input placeholder="手机" type="text" name="userForm.mobile" id="regMobile"  onblur="mobileCheck()" maxlength="11"/></label>
								</li>
								<li class="lr-yzm-li type_mobile">
									<div class="bm-lr-jy-box undis" id="mobile_check_code_div">
										<section class="hLh30 ml10 mr10 pr">
											<big onclick="$(this).parent().parent('.bm-lr-jy-box').hide();" title="关闭" class="bm-close">x</big>
											<div class="DT-arrow"><em>◆</em><span>◆</span></div>
											<em class="icon18 login-err-icon"></em><span class="fsize12 vam ml5" id="mobile_check_code_error">请输入动态验证码！</span>
										</section>
									</div>
									<label>
										<aside class="lr-yzm-btn fr">
											<a href="javascript:void(0)" onclick="getPhoneCheckCode(this)" title="获取证码">获取验证码</a>
										</aside>
										<em class="icon18 ml5 pwd-icon"></em><input id="checkCode" onblur="phoneCheckCode()" maxlength="6" type="text" placeholder="动态验证码" name="">
									</label>
								</li>
								<li class="type_email undis">
									<div class="bm-lr-jy-box undis" id="register_email_div">
										<section class="hLh30 ml10 mr10 pr" >
											<big onclick="$(this).parent().parent('.bm-lr-jy-box').hide();" title="关闭" class="bm-close">x</big>
											<div class="DT-arrow"><em>◆</em><span>◆</span></div>
											<em class="icon18 login-err-icon"></em><span class="fsize12 vam ml5" id="register_email_error">请输入正确的邮箱账号！</span>
										</section>
									</div>
									<label><em class="icon18 ml5 user-icon"></em><input type="text" onblur="emailCheck()" id="regEmail" placeholder="邮箱账号" name=""></label>
								</li>
								<li class="lr-yzm-li type_email undis">
									<div class="bm-lr-jy-box undis" id="email_check_code_div">
										<section class="hLh30 ml10 mr10 pr">
											<big onclick="$(this).parent().parent('.bm-lr-jy-box').hide();" title="关闭" class="bm-close">x</big>
											<div class="DT-arrow"><em>◆</em><span>◆</span></div>
											<em class="icon18 login-err-icon"></em><span class="fsize12 vam ml5" id="email_check_code_error">请输入邮箱验证码！</span>
										</section>
									</div>
									<label>
										<aside class="lr-yzm-btn fr">
											<a href="javascript:void(0)" onclick="getEmailCheckCode(this)" title="获取验证码">获取验证码</a>
										</aside>
										<em class="icon18 ml5 pwd-icon"></em><input type="text" onblur="emailCheckCode()" id="checkCodeEmail" placeholder="邮箱验证码" name="">
									</label>
								</li>
								<li>
									<div class="bm-lr-jy-box undis" id="pwd_div">
										<section class="hLh30 ml10 mr10 pr">
											<big onclick="$(this).parent().parent('.bm-lr-jy-box').hide();" title="关闭" class="bm-close">x</big>
											<div class="DT-arrow"><em>◆</em><span>◆</span></div>
											<em class="icon18 login-err-icon"></em><span class="fsize12 vam ml5" id="regPwdError">请输入密码！</span>
										</section>
									</div>
									<label><em class="icon18 ml5 pwd-icon"></em><input type="password" id="regPwd" onblur="passCheck()" placeholder="设置密码" name=""></label>
								</li>
								<li>
									<div class="bm-lr-jy-box undis" id="confirm_div">
										<section class="hLh30 ml10 mr10 pr">
											<big onclick="$(this).parent().parent('.bm-lr-jy-box').hide();" title="关闭" class="bm-close">x</big>
											<div class="DT-arrow"><em>◆</em><span>◆</span></div>
											<em class="icon18 login-err-icon"></em><span class="fsize12 vam ml5" id="cusPwdConfirmError">两次输入的密码不一致！</span>
										</section>
									</div>
									<label><em class="icon18 ml5 pwd-icon"></em><input type="password" id="cusPwdConfirm" onblur="passConfirmCheck()" placeholder="再输入一次" name=""></label>
								</li>
							</ul>
							<div class="hLh30 mt10">
								<span class="fr"><a href="${ctx}/login" title="" class="c-orange fsize12">已有账号！去登录</a></span>
								<span>
									<label><input type="checkbox" checked="checked" name="abcAgreement" value=""><small class="c-999 fsize12">同意并阅读</small><a href="" title="《用户注册协议》" class="c-blue">《用户注册协议》</a></label>
								</span>
							</div>
							<div class="mt10"><a title="立即注册" class="bm-lr-btn" href="javascript:void(0)" onclick="register()">立即注册</a></div>
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
						</section>  <!-- 手机号注册 结束 -->
					</div>
				</section>
			</article>
		</div>
		<!-- login register box end -->
	</section>
</div>
<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/login/register.js?v=<%=version%>"></script>
<script type="text/javascript">
		$(function() {
			birdFun(); //切换鸟
			cardChange("#lr-reg-title>a","#lr-reg-cont>section","current"); //注册方式切换
			goTop();
		});
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

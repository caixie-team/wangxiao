<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>新会员注册</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8"/> 
	<meta name="author" content="试一试(www.XXX.com)"/> 
	<meta name="keywords" content="XXX在线,在线教育,网校搭建,网校,网络教育,远程教育,云网校,在线学习,在线考试" />
	<meta name="description" content="XXX是一家专注“在线教育平台”的互联网公司，在国内属顶级在线教育解决方案提供商中的领跑者。为大、中型客户提供领先的在线教育平台方案服务。"/> 
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" /> 
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/edu/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/edu/css/page-style.css"/>
	<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/pageJs.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/webutils.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/web_top.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/emailList.js"></script>
	<script type="text/javascript" src="${ctximg}/static/edu/js/login/login.js"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/login/register.js?v=<%=version%>"></script>

<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=1147929021" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/jquery.validate.js"></script>
    <!--[if lt IE 9]><script src="${ctximg}/static/common/html5.js"></script><![endif]-->
<script type="text/javascript">
	$(function(){
		//删除绑定cookie信息
		//DeleteCookie(usercookiekey);
		DeleteCookie("oauthlogin");
		DeleteCookie("openNickName");
	});
	</script>
</head>
<body class="W-body">
	<!-- /注册 -->
	<div>
		<section class="w1000">
			<div class="pathwray">
				<ol class="clearfix c-master f-fM fsize14">
					<li><a href="/" title="首页" class="c-master">首页</a> &gt;</li>
					<li><span>新会员注册</span></li>
				</ol>
			</div>
			<div class="l-r-wrap clearfix">
				<aside class="fr w300">
					<div class="l-r-link">
						<h3 class="unFw"><font class="fsize18 f-fM c-333">已有账号，直接登录</font></h3>
						<ol class="l-r-l-list mt30">
							<li>名师高清视频课</li>
							<li>经典试题在线练习</li>
							<li>同步学情诊断</li>
						</ol>
						<section class="mt30">
							<div class="tac">
								<a href="${ctx}/login"  title="去登录" class="l-r-r-btn">去登录</a>
							</div>
						</section>
					</div>
				</aside>
				<article class="fl w650">
					<section class="comm-shadow-1">
						<div class="comm-title-2">
							<section class="clearfix">
								<span class="fr"><font class="c-999">注册后即可尊享免费课程</font></span>
								<span class="fl"><font class="fsize18 f-fM c-red">新会员注册</font></span>
							</section>
						</div>
					</section>
					<section class="mt40">
						 
						<div class="ml50">
							<ul class="l-r-w-Inpt">
								<form id="regForm" name="regForm" method="post" >
								<li class="mt30">
									<label class="vam">邮&nbsp;&nbsp;&nbsp;箱：</label>
									<input  type="text" name="userForm.email"  id="regEmail" class="lTxt" style="+margin-left: -50px;" onblur="emailCheck()"/>
									<!-- <input type="button" value="验证" onclick="emailyz()" class="l-r-r-btn tac youxiang" style="font-size: 12px;width: 40px;padding: 0 10px;"/> -->
									<span class="vam ml10"><tt class="o-wrong" id="emailError"></tt></span>
								</li>
								<li class="mt30">
									<label class="vam">密&nbsp;&nbsp;&nbsp;码：</label>
									<input onkeyup="gohsData('regPwdError')" type="password" name="userForm.password" value="" id="regPwd"  class="lTxt" />
									<span class="vam ml10"><tt class="o-wrong" id="regPwdError"></tt></span>
								</li>
								<li class="mt30">
									<label class="vam">确认密码：</label>
									<input onkeyup="gohsData('cusPwdConfirmError')" type="password" id="cusPwdConfirm" name="userForm.confirmPassword" class="lTxt" />
									<span class="vam ml10"><tt class="o-wrong" id="cusPwdConfirmError"></tt></span>
								</li>
								<!-- <li class="mt30 youxiang">
									<label class="vam">邮箱验证：</label>
									<input  type="text" name=""  id="emailVali" class="lTxt" onblur="Vali()" style="vertical-align:middle;width:98px;"/>
									<span class="vam ml10"><tt class="o-wrong" id="emailValiEorr"></tt></span>
									
								</li> -->
								<li class="mt30">
									<label class="vam">手机号码：</label>
									<input onkeyup="gohsData('regMobileError')" type="text" name="userForm.mobile" id="regMobile" class="lTxt" onblur="mobileCheck()" />
									<!-- <input type="button" value="验证" onclick="phoneyanzheng()" class="l-r-r-btn tac shouji" style="font-size: 12px;width: 40px;padding: 0 10px;"/> -->
									<span class="vam ml10"><tt class="o-wrong" id="regMobileError"></tt></span>
								</li>
							<!-- 	<li class="mt30 shouji">
									<label class="vam">手机验证：</label>
									<input  type="text" style="vertical-align:middle;width:98px;" name="regMobile1" id="regMobile1"  class="lTxt" onblur="yanzheng1()">
									<span class="vam ml10"><tt class="o-wrong" id="regMobile1Error"></tt>
								</li> -->
								<li class="mt30">
									<label class="vam">验&nbsp;证&nbsp;码：</label>
									<input style="vertical-align:middle;width:98px;" onkeyup="gohsData('randomcodeError')" type="text" name="randomCode" id="randomcode" maxlength="4" class="lTxt yzm" />
									<img border="0" id="img" style="vertical-align:middle" src="${ctx}/ran/random" alt="验证码，点击图片更换" onclick="this.src='${ctx}/ran/random'" />
									<span class="vam ml10"><tt class="o-wrong" id="randomcodeError"></tt></span>
								</li>
								<li class="mt30">
									<label class="vam">&nbsp;</label>
									<span class="inpCb"><input type="checkbox" id="forget" class="c-icon" checked="checked" value="" name="t268xueAgreement" /><tt class="vam c-999 ml10" for="forget">我已同意并阅读</tt><a href="#" class="c-blue" title="用户注册协议">《用户注册协议》</a></span>
									<span class="vam ml10"><tt class="o-wrong" id="randomcodeError"></tt></span>
								</li>
								<li class="mt15">
									<label class="vam">&nbsp;</label>
									<span class="login-btn"><input type="button" onclick="register()" value="注 册" / ></span> 
								</li>
								<li class="mt30">
									<label class="vam">&nbsp;</label>
									<span class="sf-login ml20">
										<a href="javascript:oauthLogin('QQ')" class="vam" title="QQ登录"><img src="${ctximg}/static/edu/images/open/qq.png" width="32" height="32" class="vam" alt="QQ登录" /><p>QQ登录</p></a>
										<a href="javascript:oauthLogin('SINA')" class="vam ml30" title="新浪微博登录"><img src="${ctximg}/static/edu/images/open/sina.png" width="32" height="32" class="vam" alt="新浪微博" /><p>新浪微博</p></a>
									</span>
								</li> 
							</ul>
						</div>
						</form>
					</section>
				</article>
			</div>
		</section>
	</div>
<script type="text/javascript" src="${ctximg}/static/common/emailList.js"></script>
<script type="text/javascript">

$("#regEmail").mailAutoComplete({
    boxClass: "out_box", 
    listClass: "list_box", 
    focusClass: "focus_box", 
    markCalss: "mark_box", 
    autoClass: false								  
});
</script>

	<!-- /注册 结束 -->
</body>
</html>

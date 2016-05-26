<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>用户登录 · 注册-${websitemap.web.company}-${websitemap.web.title}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8" /> 
	<meta name="author" content="${websitemap.web.author}"/>
	<meta name="keywords" content="${websitemap.web.keywords}" />
	<meta name="description" content="${websitemap.web.description}"/> 
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/edu/css/common.css">
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/edu/css/page-style.css">
	<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/pageJs.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/webutils.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/web_top.js?v=<%=version%>"></script>
	<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=1147929021" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="${ctximg}/static/common/emailList.js"></script>
	<script type="text/javascript" src="${ctximg}/static/edu/js/login/login.js"></script>
    <script type="text/javascript" src="${ctximg}/static/edu/js/login/register.js?v=<%=version%>"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/jquery.validate.js"></script>
	<!--[if lt IE 9]><script src="${ctximg}/static/common/html5.js"></script><![endif]-->
	<style type="text/css">.ness-wrap {display: none;}</style>
	<script type="text/javascript">
		var baselocation = "${ctx}";
		var buyURL = baselocation+"/buy";
		var usercookiekey="<%=usercookiekey%>";
		var mydomain="<%=mydomain%>";
		var keImageUploadUrl="<%=keImageUploadUrl%>";//kindeditor中使用的路径需要2个参数来区分项目和模块
		var uploadSimpleUrl="<%=uploadSimpleUrl%>";//单独的上传按钮使用的路径
		var staticImageServer="<%=staticImageServer%>";//返回路径
		var staticImageServer="<%=staticImageServer%>";//图片服务器
//		$(function() {
//			var msg=getParameter("msg");
//			if(msg!=null && msg!='' && msg=='other'){
//				dialog('提示信息',"您的帐号在其他地点登录，请重新登录",1);
//			}
//            var from=getParameter("from");
//            if(from=='exam'){
//                $(".fromflag").attr("style",'background: url("/static/edu/images/page/rl-b-bg-2.jpg") no-repeat scroll 50% 0 #fbfbfb;');
//                $(".fromflagtitle").html("考试系统 — 登录 · 注册");
//            }
//            if(from=='sns'){
//                $(".fromflag").attr("style",'background: url("/static/edu/images/page/shequ.jpg") no-repeat scroll 50% 0 #fbfbfb;');
//                $(".fromflagtitle").html("教育社区系统 — 登录 · 注册");
//            }
//		});
        $(function(){
            var pageUrl = window.location;
            if(pageUrl=='${ctx}/register'){
                $("#goregister").click();
            }
            DeleteCookie("oauthlogin");
            DeleteCookie("openNickName");

        });
    </script>
</head>
<body class="W-body">
	<!-- /登录 头 -->
	<div class="rl-header">
		<section class="w1000">
			<aside class="rl-tel"><span class="c-master fsize20 f-fM">Tel：${websitemap.web.phone}</span></aside>
			<h1 class="of unFw">
				<a href="/" title="${websitemap.web.company}" class="logo-2013"><img src="<%=staticImageServer %>${logomap.logo.url}" width="143" height="90" /></a>
				<div class="rl-subTitle"><span class="c-master fsize20 f-fM fromflagtitle">网校系统 — 登录 · 注册</span></div>
			</h1>
			<div class="clear"></div>
		</section>
	</div>
	
	<!-- /登录 -->
	<div class="rl-wrap-bg fromflag" >
		<section class="w1000">
			<div class="rl-r-box">
				<section class="mt50">
					<div class="rl-r-tab-title">
						<ul id="lr-title">
							<li class="current"><a href="javascript: void(0);">用户登录 / <small>UserLogin</small></a></li>
							<li><a href="javascript: void(0);" id="goregister">新用户注册 / <small>UserRegister</small></a></li>
						</ul>
					</div>
					<div id="lr-cont" class="rl-r-tab-cont">
						<!-- 登录 开始 -->
						<section>
							<div class="rl-r-tab-c-l">
								<div class="hLh20 of undis">
									<span class="enwErrorMsg">
										<tt class="o-wrong" id="requestErrorID"><%--<em class="icon18 vam disIb newIcon18Cs"></em>此账户不存在！--%></tt>
									</span>
								</div>
								<ol>
									<li>
										<label>
                                            <%--<input type="text" name="" value="" placeholder="请输入邮箱/手机号">--%>
                                            <input type="text" placeholder="请输入邮箱/手机号" id="userEmail" onkeyup="enterSubmit(event,'pageLogin(2)')">
                                        </label>
										<span class="rl-jy-span enwErrorMsg">
											<tt class="o-wrong" id="userNameError"><%--<em class="icon18 vam disIb newIcon18Cs"></em>账户错误！--%></tt>
										</span>
									</li>
									<li>
										<label>
                                            <%--<input type="password" name="" value="" placeholder="请输入密码">--%>
                                            <input onkeyup="enterSubmit(event,'pageLogin(2)')"  type="password" id="userPassword" name="" value="" class="lTxt" placeholder="请输入您的密码">
                                        </label>
										<span class="rl-jy-span enwErrorMsg">
											<tt class="o-wrong" id="passwordError"><%--<em class="icon18 vam disIb newIcon18Cs"></em>密码错误！--%></tt>
										</span>
									</li>
									<li class="pt10">
										<span class="inpCb ml10 hand">
											<input type="checkbox" name="autoThirty" checked="checked" class="c-icon" id="autoThirty"><tt for="forget" class="vam c-999 ml10">30天内自动登录</tt>
										</span>
										<span class="ml50"><a href="${ctx}/front/forget_passwd" title="忘记密码？" class="c-orange"><u>忘记密码？</u></a></span>
									</li>
								</ol>
								<section class="rl-login-btn mt20 tac"><a href="javascript:void(0)" onclick="pageLogin(2)" title="登录">登 录</a></section>
							</div>
							<div class="rl-r-tab-c-r">
								<div class="rl-r-t-c-r-box" id="thirdLogin">
									<section class="tac"><span class="c-333">—— 快捷登录 ——</span></section>
									<section class="mt10">
										<a href="javascript:oauthLogin('QQ')" class="vam" title="QQ账号登录"><img src="${ctximg}/static/edu/images/open/qq.png" width="18" height="18" class="vam" alt="QQ登录" /> <span class="vam">QQ账号登录</span></a>
									</section>
									<section class="mt10">
										<a href="javascript:oauthLogin('SINA')" class="vam" title="新浪微博登录"><img src="${ctximg}/static/edu/images/open/sina.png" width="18" height="18" class="vam" alt="新浪微博" /> <span class="vam">新浪微博登录</span></a>
									</section>
								</div>
							</div>
							<div class="clear"></div>
						</section>
						<!-- 登录 结束 -->
						<!-- 注册 开始 -->

						<section class="undis">
                            <form id="regForm"  method="post" >
							<div class="rl-r-tab-c-l">
								<div class="hLh20 of undis">
									<span class="enwErrorMsg">
										<tt class="o-wrong" id=""><!-- <em class="icon18 vam disIb newIcon18Cs"></em>此账户不存在！--></tt>
									</span>
								</div>
								<ol>
									<li>
										<label>
                                            <input  type="text" name="userForm.email" placeholder="邮箱" id="regEmail"  onblur="emailCheck()" onkeyup="gohsData('emailError')"/>
                                        </label>
										<span class="rl-jy-span enwErrorMsg">
											<tt class="o-wrong" id="emailError"><%--<em class="icon18 vam disIb newIcon18Cs"></em>此邮箱已注册！--%></tt>
										</span>
									</li>
									<li>
										<label>
                                            <input onkeyup="gohsData('regPwdError')" placeholder="请输入密码" type="password" name="userForm.password" id="regPwd" onblur="passCheck()" maxlength="18"/>
                                        </label>
										<span class="rl-jy-span enwErrorMsg">
											<tt class="o-wrong" id="regPwdError"><%--<em class="icon18 vam disIb newIcon18Cs"></em>密码至少6位！--%></tt>
										</span>
									</li>
									<li>
										<label>
                                            <input onkeyup="gohsData('cusPwdConfirmError')" type="password" placeholder="请再输入一次" id="cusPwdConfirm" name="userForm.confirmPassword" onblur="passConfirmCheck()" maxlength="18"/>
                                        </label>
										<span class="rl-jy-span enwErrorMsg">
											<tt class="o-wrong" id="cusPwdConfirmError"></tt>
										</span>
									</li>
									<li>
										<label>
                                            <input onkeyup="gohsData('regMobileError')" placeholder="请输入手机号码" type="text" name="userForm.mobile" id="regMobile"  onblur="mobileCheck()" maxlength="11"/>
                                        </label>
										<span class="rl-jy-span enwErrorMsg">
											<tt class="o-wrong" id="regMobileError"></tt>
										</span>
									</li>
									<li>
										<label style="width: 100px;">
                                            <!-- <input type="password" name="" value="" placeholder="验证码"> -->
                                            <input placeholder="验证码" onkeyup="gohsData('randomcodeError')" type="text" name="randomCode" id="randomcode" maxlength="4" />
                                        </label>
										<img border="0" onclick="this.src='${ctx}/ran/random?v='+Math.random()" alt="验证码，点击图片更换" src="${ctx}/ran/random" style="left: 110px;" id="img">
										<span class="rl-jy-span enwErrorMsg">
											<tt class="o-wrong" id="randomcodeError"></tt>
										</span>
									</li>
								</ol>
								<div class="mt20 pr">
									<section class="rl-login-btn tac"><a href="javascript:void(0)" onclick="register()"  title="注 册">注 册</a></section>
									<aside class="lr-u-xy">
										<span class="inpCb hand">
                                            <input type="checkbox" name="t268xueAgreement" checked="checked"  class="c-icon" id="forget"><tt for="forget" class="vam c-999 ml10">同意并阅读</tt><a title="用户注册协议" class="c-blue" href="#">《用户注册协议》</a></span>
									</aside>
								</div>
							</div>
							<div class="rl-r-tab-c-r">
								<div class="rl-r-t-c-r-box">
									<section class="tac"><span class="c-333">—— 快捷登录 ——</span></section>
									<section class="mt10">
										<a href="javascript:oauthLogin('QQ')" class="vam" title="QQ账号登录"><img src="${ctximg}/static/edu/images/open/qq.png" width="18" height="18" class="vam" alt="QQ登录" /> <span class="vam">QQ账号登录</span></a>
									</section>
									<section class="mt10">
										<a href="javascript:oauthLogin('SINA')" class="vam" title="新浪微博登录"><img src="${ctximg}/static/edu/images/open/sina.png" width="18" height="18" class="vam" alt="新浪微博" /> <span class="vam">新浪微博登录</span></a>
									</section>
								</div>
							</div>
							<div class="clear"></div>
                            </form>
						</section>

						<!-- 注册 结束 -->
					</div>
				</section>
			</div>
			<div class="clear"></div>
		</section>
	</div>
	
	<!-- app二维码入口 开始 -->
	<div class="pt50 pb50" style="background: #f2f2f2;">
		<section class="w1000">
			<div class="tac mt20">
				<%--<span class="c-333 fsize24">移动App新视界，超凡Pad新体验</span>--%>
			</div>
			<div class="mt20 tac">
				<%--<span class="c-666 fsize14">高清流畅，海量课程</span>--%>
				<%--<span class="c-666 fsize14 ml20">极速离线下载，畅快自由学习</span>--%>
				<%--<span class="c-666 fsize14 ml20">相关资讯，一览无余</span>--%>
				<%--<span class="c-666 fsize14 ml20">现在即可下载它，让你的学习飞起来~~~</span>--%>
			</div>
			<div class="mt50 tac">
				<div class="ios-and-dl mr20">
					<%--<img src="${ctx}/static/edu/images/page/ios.jpg" width="140" height="140" class="dis" />--%>
					<%--<p>IOS版扫描下载</p>--%>
				</div>
				<div class="ios-and-dl ml20">
					<%--<img src="${ctx}/static/edu/images/page/android.jpg" width="140" height="140" class="dis" />--%>
					<%--<p>Android版扫描下载</p>--%>
				</div>
			</div>
		</section>
	</div>
	<!-- app二维码入口 结束 -->
	<!-- 公共底引入 -->
	<jsp:include page="/WEB-INF/layouts/edu/footer.jsp"/>
	<!-- 公共底引入 -->
	<script type="text/javascript" src="${ctximg}/static/common/placeholder.js"></script>
	<script type="text/javascript">
		cardChange("#lr-title>li" , "#lr-cont>section" , "current");//登录注册切换
		
	    $("#userEmail").mailAutoComplete({
	        boxClass: "out_box", //外部box样式
	        listClass: "list_box", //默认的列表样式
	        focusClass: "focus_box", //列表选样式中
	        markCalss: "mark_box", //高亮样式
	        autoClass: false,//不使用默认的样式
	        textHint: true//提示文字自动隐藏
	    });
	</script>
	<!-- /登录 结束 -->
</body>
</html>

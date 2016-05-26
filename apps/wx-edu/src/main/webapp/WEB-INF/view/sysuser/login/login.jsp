<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>登陆-${websitemap.web.company}-${websitemap.web.title}</title>
<style type="text/css">
/*=============commonalityStyle=============*/
	body,ul,ol,li,p,h1,h2,h3,h4,h5,h6,form,fieldset,img,div,span,em,img,label {margin:0;padding:0;border:0;}
	body{background:#fff ;font:12px/20px 'SimHei','Arial','Helvetica Neue';}
	html,body {height: 100%;width: 100%;}
	ul,ol,li{list-style:none}
	.clearfix:after {content: '';display: block;height: 0;clear: both;overflow: hidden;}
	.clearfix {display: inline-block;}
	* html .clearfix {height: 1%;}
	.clearfix {display: block;}
	a{text-decoration:none;}
	a,img{border:0 none;}
	input,button{vertical-align:middle;}
	.ml10 {margin-left: 10px;}
	.wrap {width: 100%;overflow: hidden;position: relative;}
	<%-- .loginWrap {background: url(/static/common/admin/images/loginWrapBg.png) no-repeat 50% 50%;height: 630px;width: 844px;position: absolute;top: 50%;left: 50%;margin: -315px 0 0 -422px;}
	.loginIner {overflow: hidden;position: relative;width: 388px;padding: 8px 19px 0 437px;}
	.loginLogo h1 {height: 216px;text-align: center;}
	.loginLogo h1 a {background: url(<%=staticImageServer %>${logomap.logo.url}) no-repeat 50% 50%;display: block;height: 100%;text-indent: -99999px;width: 100%;} --%>
	.loginMain {height: 322px;position: relative;}
	.loginFoot {height: 65px;text-align: center;}
	.lfTxt {color: #075273;font: 16px/65px 'Microsoft JhengHei','SimHei','Arial';}
	.loginMain ul {display: block;padding: 50px 24px 0;}
	.loginMain ul li {margin-bottom: 25px;}
	.loginMain ul li label,.loginMain ul li span {display: inline-block;vertical-align: middle;}
	.loginMain ul li label {color: #3982A3;width: 65px;text-align: right;padding-right: 15px;font: 20px/30px 'SimHei','Arial';}
	.loginMain ul li span input {background: #215770;border: 1px solid #02334A;border-radius: 6px;color: #3982A3;font-size: 18px;font-family: 'SimHei';width: 241px;height: 30px;line-height: 30px;padding: 3px 8px;}
	.loginMain ul li span.vCode input {width: 116px;}
	.loginMain center {padding-top: 10px;}
	.subWrap {border: 1px solid #255F7A;border-radius: 6px;display: inline-block;}
	.loginBtn {background: -webkit-gradient(linear, left top, left bottom, from(#255C75), to(#215770));background: -moz-linear-gradient(top, #255C75, #215770);background: -ms-linear-gradient(top, #255C75, #215770);filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#255C75', endColorstr='#215770')\9;border: none;border-radius: 4px;color: #CCE2EC;cursor: default;height: 40px;font: 22px/36px 'SimHei','Arial';width: 160px;}
	.loginBtn:active {background: -webkit-gradient(linear, left top, left bottom, from(#255C75), to(#215770));background: -moz-linear-gradient(top, #2B6681, #215770);background: -ms-linear-gradient(top, #255C75, #215770);filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#255C75', endColorstr='#215770')\9;}
	.tipsWrap {background: url(/static/common/admin/images/icon1.png) no-repeat 10px center;border: 1px solid #EC6060;text-align: center;height: 30px;line-height: 30px;width: 88%;position: absolute;top: 10px;left: 50%;margin-left: -44%;}
	.tipsTxt {color: #EC6060;}
	.log-container {bottom: 0; height: 100%;left: 0;min-width: 1200px; position: relative; right: 0;top: 0;}
	.c-right {background: #fff;bottom: 0;border-left: 1px solid #eee;min-width: 559px; height: 100%;position: absolute;right: 0; top: 0;}
	.c-left {height: 100%; margin: 0 560px 0 0;position: relative;background: #fff url("/static/common/admin/images/login-bg.jpg") no-repeat center 0;}
	.c-logo { position: absolute;left: 100px; top: 80px;}
	.logo a img {display: block; max-width: 428px;}
	.viceTitle { padding: 10px 0 0 30px;}
	.viceTitle span { font: 28px/50px "SimHei";vertical-align: middle; color:#e94c2a; }
	.viceTitle span.vt-txt { color: #083865; margin-left: 10px;}
	.cr-wrap {margin: 11.8em 67px 0;}
	h2 {font-size: 24px;font-weight: normal;line-height: 30px;}
	.crTitle span { font: 34px/48px "SimHei";vertical-align: middle;display: inline-block;}
	.crTitle span.ct-orange, .viceTitle span.vt-line { color: #e94907;}
	.crTitle span.ct-gray {color: #083865;font-size: 32px;}
	.crTitle span.poin { font: 10px/30px "SimHei","Microsoft YaHei"; margin: 0 15px;color: #666;}
	.loginWrap {margin-top: 5em;}
	.loginWrap h6 {font: 14px/30px "Microsoft YaHei";color: #083865;margin-bottom: 10px;}
	.loginWrap li { margin-bottom: 45px;position: relative;}
	.loginWrap li label {display: block;margin-bottom: 5px;}
	.loginWrap li input { border: 1px solid #e2e2e2; color: #888;font: 16px/50px "Microsoft YaHei","SimHei";height: 50px;padding: 0 10px;width: 400px;}
	.loginWrap li input:focus {border-color: #e94907;box-shadow: 0 0 3px rgba(233, 73, 7, 0.3);}
	.loginWrap li span { margin-left: 10px;vertical-align: middle;}
	.lw-btnWrap { clear: both;margin-top: 20px; overflow: hidden;}
	.btn-01 { color: #fff; cursor: pointer; line-height: 50px; width: 100%;height: 50px;text-align:center;display: block;background-color: #e94c2a;font-size: 24px; font-family: "simHei";border: none;}
	.btn-01:hover { background-color: #d73d1c; text-decoration: none;}
	.error-info {position: absolute; left:0; top: 54px;display: none;}
	.error-info span {color: #ea7b68; font: 14px/28px "Microsoft YaHei"; vertical-align: middle;}
	.error-info em { width: 14px; height: 14px;display: inline-block; vertical-align: middle; background: url("/static/common/admin/images/admin-icon.png") no-repeat -168px -166px;}
	.undis {display: none;}
	.loginWrap li .yzcode-la { border: 1px solid #e2e2e2; border-right: none;background-color: #e6e6e6;}
	.loginWrap li .yzcode-la input {border: none;}
	.c-vcodeimg {background-color: #e6e6e6;display: inline-block;width: 50%;height: 50px; border-right: 1px solid #e2e2e2;+display: inline;}
	.c-img-fresh {width:20px; height: 20px; display: inline-block;vertical-align: middle; background: url("/static/common/admin/images/admin-icon.png") no-repeat -164px -194px;margin: 0 20px;}
	.c-vcodeimg img {vertical-align: middle;height: 50px;}
	@media (min-width: 992px) and (max-width: 1300px) {
		.c-left {background-size: 120% ;background-position: -80px 110px}
		.c-logo a img { width: 186px; height: 65px;}
	}
</style>

<!--  jquery core -->
<script src="${ctximg}/static/common/jQueryValidate/lib/jquery.js?v=${v}" type="text/javascript"></script>
	<script src="${ctximg}/static/common/jQueryValidate/lib/jquery.metadata.js?v=${v}" type="text/javascript"></script>
	<script src="${ctximg}/static/common/jQueryValidate/jquery.validate.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript">
	var emailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ ;
	$(document).ready(function(){
	placeholderFun();
	var errorMsg = '${msg}';
	var type = '${type}';
	var username = '${username}';
	if(type!=null && $.trim(type.toString())!=''){
		if(type=='username'){
			$("#userNameErrorDiv").show();
			$("#userNameError").html(errorMsg);
		}else if(type=='password'){
			$("#passwordErrorDiv").show();
			$("#passwordError").html(errorMsg);
			$("#username").val(username);
		}else if(type=='checkCode'){
			$("#randomCodeErrorDiv").show();
			$("#randomCodeError").html(errorMsg);
			$("#username").val(username);
		}else if(type=='other'){
			alert(errorMsg);
		}
	}else{
		$("#userNameErrorDiv").hide();
		$("#passwordErrorDiv").hide();
		$("#randomCodeErrorDiv").hide();
	}

	$("#username").blur(function(){
		if($(this).val()==''||!emailReg.test($(this).val())){
			$("#userNameError").html("请输入正确的邮箱地址!");
			$("#userNameErrorDiv").show();
		}else{
			$("#userNameError").html("");
			$("#userNameErrorDiv").hide();
		}
	});
	$("#password").blur(function(){
		if($(this).val()==''){
			$("#passwordError").html("密码不能为空");
			$("#passwordErrorDiv").show();
		}else{
			$("#passwordError").html("");
			$("#passwordErrorDiv").hide();
		}
	});
	$("#randomCode").blur(function(){
		if($(this).val()==''){
			$("#randomCodeError").html("验证码不能为空");
			$("#randomCodeErrorDiv").show();
		}else{
			$("#randomCodeError").html("");
			$("#randomCodeErrorDiv").hide();
		}
	});
});
function enterSubmit(event) {
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
	if (keyCode == 13) {
		if($("#username").val()==''||!emailReg.test($("#username").val())){
			$("#userNameError").html("请输入正确的邮箱地址!");
			$("#userNameErrorDiv").show();
			return;
		}else if($("#password").val()==''){
			$("#passwordError").html("密码不能为空");
			$("#passwordErrorDiv").show();
			return;
		}else if($("#randomCode").val()==''){
			$("#randomCodeError").html("验证码不能为空");
			$("#randomCodeErrorDiv").show();
			return;
		}
		return;
		$("#loginForm").submit();
		return false;
	}
}
	function changePicture(){
		$("#pic").attr("src","${ctx}/ran/adminrandom?random="+Math.random());
	}
	function placeholderFun() {
		  //判断浏览器是否支持placeholder属性
		  supportPlaceholder='placeholder'in document.createElement('input');

		  //当浏览器不支持placeholder属性时，调用placeholder函数
		  if(!supportPlaceholder){
		    $("input").not("input[type='password']").each(//把input绑定事件 排除password框  
		          function(){  
		              if($(this).val()=="" && $(this).attr("placeholder")!=""){  
		                  $(this).val($(this).attr("placeholder"));  
		                  $(this).focus(function(){  
		                      if($(this).val()==$(this).attr("placeholder")) $(this).val("");  
		                  });  
		                  $(this).blur(function(){  
		                      if($(this).val()=="") $(this).val($(this).attr("placeholder"));  
		                  });  
		              }  
		      });  
		      //对password框的特殊处理
		      var pwdField    = $("input[type=password]");
		      pwdField.each(function() {
		          var _this = $(this),
		              index = _this.index(),
		              pwdVal = _this.attr('placeholder');
		          _this.after('<input id="pwdPlaceholder'+index+'" type="text" value='+pwdVal+' autocomplete="off" />');
		          var pwdFieldColn = _this.next();  
		          pwdFieldColn.show();  
		          _this.hide();
		            
		          pwdFieldColn.focus(function(){  
		              pwdFieldColn.hide();  
		              _this.show();  
		              _this.focus();  
		          });  
		            
		          _this.blur(function(){  
		              if(_this.val() == '') {  
		                  pwdFieldColn.show();  
		                  _this.hide();  
		              }  
		          });
		      });
		  }
		}
</script>

</head>
<body id="login-bg"> 
<div class="log-container">
	<div class="c-right">
		<div class="cr-wrap">
			<h2 class="crTitle">
				<span class="ct-gray poin">●</span>
				<span class="ct-gray">在线教育学习系统</span>
			</h2>
			<div class="loginWrap">
				<h6>使用您的企业账号登入</h6>
				<form action="${ctx}/admin/sys/login" id="loginForm" name="loginForm" method="post" style="margin: 0px;+width: 425px;">
					<ul class="loginInp">
						<li>
							<label><input type="text" id="username" name="user.email" placeholder="请输入您的用户名"></label>
							<div class="error-info" id="userNameErrorDiv"><em></em><span id="userNameError"></span></div>
						</li>
						<li>
							<label><input type="password" id="password" name="user.password" placeholder="请输入您的密码"></label>
							<div class="error-info" id="passwordErrorDiv"><em></em><span id="passwordError"></span></div>
						</li>
						<li>
							<label class="yzcode-la"><input type="text" id="randomCode" onkeyup="enterSubmit(event)" type="text" name="randomCode" style="width: 45%;+display: inline" placeholder="请输入验证码"><div class="c-vcodeimg"><em class="c-img-fresh" onclick="changePicture()"></em><img id="pic" src="${ctx}/ran/adminrandom" alt="验证码，点击图片更换" onclick="this.src='${ctx}/ran/adminrandom?random='+Math.random();"></div></label>
							<div class="error-info" id="randomCodeErrorDiv"><em></em><span id="randomCodeError"></span></div>
						</li>
					</ul>
					<div class="lw-btnWrap">
						<label ><input type="submit" name="" value="登 &nbsp;录" class="btn-01" ></label>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="c-left">
		<h1 class="c-logo">
			<a title="在线教育学习系统" href="/"><img width="237" height="83" alt="在线教育学习系统" src="/static/common/admin/images/new-logo.png"></a>
			<div class="viceTitle">
				<div><span class="vt-line">&mdash;&mdash;</span><span class="vt-txt">在线教育学习系统</span></div>
			</div>
		</h1>
	</div>
</div>

</body>
</html>


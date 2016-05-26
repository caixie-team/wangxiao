<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!doctype html>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>移动端App -${websitemap.web.company}-${websitemap.web.title}</title>
	<meta name="author" content="${websitemap.web.author}"/>
	<meta name="keywords" content="${websitemap.web.keywords}" />
	<meta name="description" content="${websitemap.web.description}"/>
	<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0'/>
	<meta name="apple-mobile-web-app-capable" content="yes">
  	<meta name="apple-mobile-web-app-status-bar-style" content="black">
  	<style type="text/css">
  		/*!
		Theme Name:
		Author URI:${ctx}
		Version:1.1
		Author:westdrug
		Description:
		Update @ 2015-01-19 14:00
		*/
		html,body {height: 100%;width: 100%;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%;}
		body,ul,ol,li,p,h1,h2,h3,h4,h5,h6,form,fieldset,img,div,menu,dl,dt,dd,article,aside,details,figcaption,figure,footer,header,hgroup,nav,section{border: 0;padding: 0;margin: 0;}
		body{font:1.4rem/150% "Hiragino Sans GB",'Microsoft YaHei','Arial';word-break:break-all;word-wrap:break-word;}
		ul,ol,li{list-style:none}
		a{text-decoration:none;-webkit-transition: color 400ms, background-color 400ms;transition: color 400ms, background-color 400ms;-webkit-tap-highlight-color: rgba(0,0,0,0);-moz-tap-highlight-color: rgba(0,0,0,0);-o-tap-highlight-color: rgba(0,0,0,0);}
		a:hover{text-decoration:none;}a:focus{outline:none;-moz-outline:none;}
		a:active{outline:none;blr:expression(this.onFocus=this.blur())}
		a,img{border:0 none;}
		img{max-width:100%;-webkit-transition: all .4s ease-in-out;-moz-transition: all .4s ease-in-out;-o-transition: all .4s ease-in-out;transition: all .4s ease-in-out;}
		input,select,button{vertical-align:middle;-webkit-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;}
		h1,h2,h3,h4,h5,h6 {font-weight: normal;}
		h1 {font-size: 3.6rem;line-height: 4.5rem;}
		h2 {font-size: 2.4rem;line-height: 3.0rem;}
		h3 {font-size: 1.8rem;line-height: 2.2rem;}
		h4 {font-size: 1.6rem;line-height: 2.0rem;}
		h5 {font-size: 1.4rem;line-height: 1.8rem;}
		h6 {font-size: 1.2rem;line-height: 1.6rem;}
		article, aside, details, figcaption, figure, footer, header, hgroup, menu, nav, section, .dis{display: block;}
		.clearfix:after{ content: '';display: block;height: 0;clear: both;visibility: hidden; }
		.clearfix{display: inline-block;}
		* html .clearfix{height: 1%;}
		.clearfix{display: block;}
		.clear {clear: both;font-size: 0;content: " ";line-height: 1px;height: 1px;_height: 1%;margin-top: -1px;}
		.fl {float: left;}.fr {float: right;}.undis {display: none;}
		.header {background: #2f75bb;box-shadow: 0 1px 3px rgba(0,0,0,.2);padding: 0 15px;overflow: visible;overflow: hidden;height: 50px;}
		.jt-logo {float: left;font-size: 1.0rem;height: 50px;line-height: 50px;color: #fff;}
		.jt-logo img {display: block;height: 44px;margin-top: 3px;}
		.j-main {background: url(/static/edu/downapp/images/banner.jpg) no-repeat 50% 0;min-height: 200px;position: relative;overflow: hidden;}
		.slogan-top td {height: 200px;padding: 0;margin: 0;}
		.slogan-top .sLogan {display: inline-block;width: 100px;height: 104px;}
		.slogan-top h2 {padding-left: 10px;text-align: left;font-size: 1.8rem;font-family:"Hiragino Sans GB",'Microsoft YaHei';color: #111;}
		.slogan-top h2 p {font-size: 0.8rem;}
		.ts-bottom {background: rgba(255,255,255,.6);height: 70px;text-align: center;overflow: hidden;}
		.ts-bottom span,.ts-bottom tt {font: 1.2rem/70px "Hiragino Sans GB",'Microsoft YaHei';color: #111;}
		.ts-bottom tt {color: #f8a643;}
		.jt-btn-wrap {background: #f2f2f2;padding: 30px 0;text-align: center;}
		.jt-btn-wrap a {background: #f8a643;border-radius: 3px;display: inline-block;width: 80%;height: 50px;text-align: center;font: 1.4rem/50px 'Hiragino Sans GB','Microsoft YaHei';color: #fff;}
		.jt-btn-wrap p {margin-top: 10px;font-size: 0.8rem;}
		.j-foot {margin-top: 20px;}
		.j-foot td {padding: 0 10px;color: #666;font-size: .4rem;text-align: center;}
		.j-foot td * {vertical-align: middle;}
		.b-obg {background: rgba(0,0,0,.8);display: none;position: fixed;height: 100%;width: 100%;top: 0;right: 0;bottom: 0;left: 0;z-index: 9998;}
		.wx-tips {display: none;position: absolute;right: 18px;top: -5px;z-index: 9999;}
  	</style>
</head>
<body>
	<!-- /header begin-->
	<article class="header">

	</article>
	<!-- /header end-->
	<section class="j-main">
		<div class="slogan-top">
			<table width="100%" border="0">
				<tbody>
					<tr>
						<td width="30%" align="right" valign="middle"><img src="/static/edu/downapp/images/logo-s.png" class="sLogan" width="100" height="104" alt=""></td>
						<td width="70%" align="left" valign="middle"><h2><p>下载你的学习app，从这里开始</p></h2></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="ts-bottom">
			<span>您的系统属于</span><tt id="sys-name">正在辨认...</tt>
		</div>
	</section>
	<div class="jt-btn-wrap">
		<a href="javascript:brower()" title="" id="sys-btn">？下载</a>
		<p><span>版本信息：V5.2.0</span>&nbsp;<span>发布日期：2015.01.20</span></p>
	</div>
	<table width="100%" border="0" class="j-foot">
		<tbody>
			<tr>
				<td width="50%" align="" valign="top"><img src="/static/edu/downapp/images/phone.png" height="20" alt="电话"><span>电话：400-0620-268</span></td>
				<td width="50%" align="" valign="top"><img src="/static/edu/downapp/images/qq.png" height="20" alt="QQ客服"><span>QQ：2115733249</span></td>
			</tr>
			<tr>
				<td width="50%" align="" valign="top"><img src="/static/edu/downapp/images/weixin.png" height="20" alt="微信"><span>微信：${websitemap.web.company}</span></td>
				<td width="50%" align="" valign="top"><img src="/static/edu/downapp/images/sina.png" height="20" alt="微博"><span>微博：${websitemap.web.company}</span></td>
			</tr>
		</tbody>
	</table>
	<!-- /j-main end -->
	<!-- weixin shadow -->
	<div id="weixin_1" class="b-obg">&nbsp;</div>
	<div id="weixin_2" class="wx-tips"><img src="/static/edu/downapp/images/wx_tips.png" width="240" height="58" /></div>
	<script type="text/javascript">
		var sysName = document.getElementById("sys-name");
		var sysBtn = document.getElementById("sys-btn");

		if (/MicroMessenger/i.test(navigator.userAgent)){
        			sysName.innerHTML = "微信平台";
        			sysBtn.innerHTML = "请在浏览器下载";
        			sysBtn.setAttribute("href","javascript:brower()");
        }else
		if (/android/i.test(navigator.userAgent)){
		    sysName.innerHTML = "Android平台";
		    sysBtn.innerHTML = "Android下载";
		    sysBtn.setAttribute("href","${ctx}/appdownloadAnrioid");
		}else   if (/ipad|iphone|mac/i.test(navigator.userAgent)){
		    sysName.innerHTML = "iOS平台";
		    sysBtn.innerHTML = "iOS下载";
		    sysBtn.setAttribute("href","${ctx}/static/edu/downapp/YouhuApp.apk");
		}else
		{
        window.location.href="${ctx}/appdownload";
        }
		function brower(){
			document.getElementById("weixin_1").style.display="block";
			document.getElementById("weixin_2").style.display="block";
		}
	</script>
</body>
</html>
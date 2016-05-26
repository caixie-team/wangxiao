<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>移动端App —${websitemap.web.company}-${websitemap.web.title}</title>
	<meta name="author" content="${websitemap.web.author}"/>
	<meta name="keywords" content="${websitemap.web.keywords}" />
	<meta name="description" content="${websitemap.web.description}"/>
	<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0'/>
	<meta name="apple-mobile-web-app-capable" content="yes">
  	<meta name="apple-mobile-web-app-status-bar-style" content="black">
  	<style type="text/css">
		html,body {height: 100%;width: 100%;}
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
		.banner{width:100%;height:615px;background: url(/static/edu/downapp/images/web-images/pc-banner.jpg) no-repeat 50% 0;padding-top:45px; }
		.main{width: 1000px;margin:0 auto;height:615px;}
		.logo{height:140px;padding-left: 40px;}
		.nr{height:385px;padding-top:90px;}
		.ewm{margin-left:310px;overflow: hidden;}
		.ewm img,.ewm span{float:left;}
		.ewm span h3{font:40px/66px 'Microsoft YaHei'; color:#000; margin-left:25px;}
		.ewm span h4{font:19px/66px 'Microsoft YaHei'; color:#000; margin-left:25px;}
		.xt{margin-left:310px;margin-top:50px;overflow: hidden;clear: both;}
		.xt a{position:relative; display:block;width:195px;height:55px;}
		.and{margin-left:60px;float:left;}
		.apple{float:left;}
		.inf-bg{background:url(/static/edu/downapp/images/web-images/apple-dj.png) no-repeat 0 0;display: none;position: absolute;left:0;top:0;right: 0;bottom: 0;z-index: 98;}
		.apple:hover .inf-bg{display: block;}
		.in-bg{background:url(/static/edu/downapp/images/web-images/and-dj.png) no-repeat 0 0;display: none;position: absolute;left:0;top:0;right: 0;bottom: 0;z-index: 98;}
		.and:hover .in-bg{display: block;}
		.footer{width: 950px;text-align: center;margin:0 auto; margin-top:100px; }
		.footer ul li{height: 30px;line-height:30px; color:#333;font:12px 'Microsoft YaHei';}
		.footer ul li a{color:#333; margin-right:10px;font:12px 'Microsoft YaHei';}
		.ml{margin-left:10px; }
		.footer ul li span{margin-left:10px; }
  	</style>
</head>
<body>
	<div class="banner">
		<div class="main">
			<div class="logo">
				<a href="" title="${websitemap.web.company}">
					<img src="<%=staticImageServer %>${logomap.logo.url}" width="180" title="${websitemap.web.company}" />
				</a>
			</div>
			<div class="nr">
				<div class="ewm">
					<img src="/static/edu/downapp/images/web-images/erweim.jpg">
					<span>
						<h3>传播知识的力量，从这里开始</h3>
						<h4>下载你的学习STYLE，体验自由、丰富、便捷的在线学习乐趣</h4>
					</span>
				</div>
				<div class="xt">
					<a href="${ctx}/app/268Education.ipa" title="iOS下载" class="apple">
						<img src="/static/edu/downapp/images/web-images/apple.png">
						<div class="inf-bg"></div>
					</a>
					<a href="${ctx}/app/268EduApp.apk" title="Android下载" class="and">
						<img src="/static/edu/downapp/images/web-images/and.png">
						<div class="in-bg"></div>
					</a>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="footer">
		<ul>
			<li>

					<c:forEach items="${navigatemap.TAB}" var="tabNavigate">
					<a target="_blank"  class="ml" href="${tabNavigate.url}" title="${tabNavigate.name}" <c:if test="${tabNavigate.newPage==0}">target="_blank"</c:if>>${tabNavigate.name}</a>|
				</c:forEach>

					<span>热线电话：${websitemap.web.phone} </span>
			</li>
			<li>
				${websitemap.web.copyright}
			</li>
		</ul>
	</div>
</body>
</html>
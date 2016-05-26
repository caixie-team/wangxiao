<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
<title>教师详情</title>
</head>
<body>
	<div class="">
		<!-- /global MOBILE header begin-->
		<div class="h-mobile-mask"></div>
		<!-- /global MOBILE header end-->
		<div class="bg-fa of">
			<!-- /课程列表 开始 -->
			<section class="container mb30">
				<section class="path-wrap txtOf hLh30">
					<a class="c-999 fsize14" title="" href="${ctx}">首页</a> \ <a class="c-999 fsize14" title="" href="${ctx}/front/teacherlist">教师列表</a> \ <span class="c-333 fsize14">${teacher.name }</span>
				</section>
				<div class="i-box clearfix mt20">
					<div class="t-info-box">
						<div class="t-i-pic">
							<c:if test="${not empty teacher.picPath }">
								<a><img width="150" height="150" src="<%=staticImageServer%>${teacher.picPath}" class="img-responsive"></a>
							</c:if>
							<c:if test="${empty teacher.picPath }">
								<a><img width="150" height="150" src="<%=staticImageServer%>/static/nxb/web/img/pic/tea-img.jpg" class="img-responsive"></a>
							</c:if>
						</div>
						<div class="t-i-desc">
							<h3 class="hLh30">
								<span class="fsize24 c-333">${teacher.name }&nbsp;
									<c:if test="${teacher.isStar==0}">高级讲师</c:if>
									<c:if test="${teacher.isStar==1}">首席讲师</c:if>
								</span>
							</h3>
							<section class="mt5">
								<span class="fsize14 c-333">${teacher.education}</span>
							</section>
							<div class="mt5">
								<div class="c-share pr of" style="width: 256px;">
									<div class="disIb">
										<em class="icon14 vam"></em>
										<tt class="ml5 fsize14 c-666 vam f-fM">分享</tt>
									</div>
									<div class="bdsharebuttonbox bdshare-button-style0-16" id="bdshare" data-bd-bind="1456112747602" style="right: -160px;">
										<a data-cmd="more" class="bds_more" href="#"></a><a data-cmd="qzone" class="bds_qzone" href="#" title="分享到QQ空间"></a><a data-cmd="tsina" class="bds_tsina" href="#" title="分享到新浪微博"></a><a data-cmd="tqq" class="bds_tqq" href="#" title="分享到腾讯微博"></a><a data-cmd="renren" class="bds_renren" href="#" title="分享到人人网"></a><a data-cmd="weixin" class="bds_weixin" href="#" title="分享到微信"></a>
									</div>
									<script>
										window._bd_share_config = {
											"common" : {
												"bdSnsKey" : {},
												"bdText" : "",
												"bdMini" : "2",
												"bdMiniList" : false,
												"bdPic" : "",
												"bdStyle" : "0",
												"bdSize" : "16"
											},
											"share" : {}
										};
										with (document)
											0[(getElementsByTagName('head')[0] || body)
													.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
													+ ~(-new Date() / 36e5)];
									</script>
								</div>
							</div>
						</div>
						<div class="t-class-box">
							<span class="c-666 fsize18 disIb f-fA"> <tt class="c-orange fsize34 f-fH">${teacherCourseNum }</tt></br> 课程
							</span>
						</div>
					</div>
				</div>
				<div class="mt30">
					<div class="clearfix t-info-box1">
						<%--<div class="fl col-75">
								<div class="mr20">--%>
						<div class="fl">
							<div>
								<div class="c-title-box">
									<ol class="js-tap clearfix">
										<li class="current"><a href="javascript:;" title="">介绍</a></li>
										<li><a href="javascript:;" title="">线上课程</a></li>
										<!-- <li><a href="javascript:;" title="">线下课程</a></li> -->
									</ol>
								</div>
								<div class="i-box c-cou-box">
									<%-- 讲师介绍 --%>
									<div class="c-r-cbox">
										${teacher.career}
									</div>
									<%-- 讲师课程 --%>
									<div class="c-r-cbox">

									</div>
								</div>
							</div>
						</div>
						<%--<div class="fr col-25">--%>
							<%--<div class="i-box1 t-desc-wrap">--%>
								<%--<header class="com-title-box">--%>
									<%--<span class="fsize18 c-333">讲师介绍</span>--%>
								<%--</header>--%>
								<%--<section class="pl20 pr20 pb20 c-tea-desc">--%>
									<%--<p class="mt20 c-666">${teacher.career}</p>--%>
								<%--</section>--%>
							<%--</div>--%>
						<%--</div>--%>
					</div>
				</div>
			</section>
		</div>
	</div>
	<script>
		var teacherId = ${teacher.id};
		$(function() {
			effect();//讲师列表的课程名显示
			cardChange(".js-tap>li", ".c-cou-box .c-r-cbox", "current",function(index){
				if(index==1){
					ajaxPage("/ajax/teacherCourseList/"+teacherId,"",1,function(result){
						$(".c-r-cbox").eq(index).html(result);
					});
				}
			});//选项卡切换
		})
		//分享
		/*$(".c-share").hover(function() {
			$(this).stop().animate({"width" : "265px"}, 200);
			$(this).children("#bdshare").stop().animate({"right" : "0"}, 200);
		}, function() {
			$(this).stop().animate({"width" : "56px"}, 200);
			$(this).children("#bdshare").stop().animate({"right" : "-160px"}, 200);
		});*/
	</script>
</body>
</html>
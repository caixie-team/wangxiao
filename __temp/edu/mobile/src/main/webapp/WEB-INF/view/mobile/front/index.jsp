<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
</head>
<body>
	
	<div id="st-container" class="st-container">
		<!-- /左侧隐藏目录菜单 begin -->
		<jsp:include page="/WEB-INF/view/mobile/common/left_menu.jsp"/>
		<!-- /左侧隐藏目录菜单 end -->
		<div class="st-pusher">
			<div class="m-ptb54">
				<!-- /全局头部位置 header -->
				<header id="header">
					<section class="h-wrap">
						<div class="menu-btn" id="st-trigger-effects">
							<svg id="svg1" data-effect="st-effect-4" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 1024 1024" enable-background="new 0 0 32 32" xml:space="preserve">
							  <path fill="#ffffff" d="M112 572l800 0 0-48-800 0 0 48ZM112 332l800 0 0-48-800 0 0 48ZM112 76l800 0 0-48-800 0 0 48Z" transform="translate(0, 812) scale(1, -1)"/>
							</svg>
						</div>
						<!-- menu-btn -->
						<h2 class="commHeadTitle"><span>首页</span></h2>
						<!-- commHeadTitle -->
						<div class="ht-r">
							<aside class="search-icon">
								<svg x="0px" y="0px" width="26px" height="26px" viewBox="0 0 1024 1024" enable-background="new 0 0 26 26" xml:space="preserve">
								  <path fill="#ffffff" d="M439.323 24.522c-49.28 0-97.098 9.657-142.127 28.7-43.48 18.389-82.523 44.706-116.044 78.226s-59.841 72.561-78.231 116.039c-19.043 45.028-28.7 92.845-28.7 142.123 0 49.284 9.656 97.105 28.7 142.133 18.389 43.479 44.71 82.52 78.232 116.039 33.519 33.515 72.563 59.833 116.044 78.219 45.027 19.040 92.846 28.695 142.126 28.695 49.28 0 97.099-9.654 142.127-28.695 43.481-18.386 82.526-44.704 116.045-78.221 33.521-33.518 59.842-72.56 78.231-116.039 19.042-45.029 28.7-92.849 28.7-142.133 0-49.278-9.657-97.095-28.702-142.123-18.389-43.477-44.708-82.52-78.229-116.039s-72.564-59.839-116.044-78.227c-45.028-19.043-92.847-28.698-142.128-28.698zM439.323 701.483c-171.976 0-311.888-139.906-311.888-311.875s139.912-311.874 311.888-311.874 311.888 139.907 311.888 311.874-139.912 311.875-311.888 311.875zM890.678-144.735c-22.122 0-42.904 8.562-58.542 24.115l-157.464 152.645 37.039 38.206 157.888-153.059c5.607-5.607 13.092-8.693 21.078-8.693 7.983 0 15.465 3.087 21.071 8.691 5.616 5.622 8.716 13.127 8.716 21.123 0 7.981-3.092 15.463-8.703 21.068l-0.302 0.307-152.746 157.563 38.208 37.035 152.612-157.427c15.571-15.638 24.145-36.423 24.145-58.549 0-22.196-8.626-43.055-24.287-58.736-15.668-15.666-36.515-24.288-58.714-24.288zM281.015 231.245c-42.34 42.321-65.653 98.584-65.644 158.421 0.008 59.818 23.319 116.075 65.638 158.408l37.635-37.62c-66.607-66.63-66.61-175.001-0.007-241.577l-37.621-37.632z" transform="translate(0, 812) scale(1, -1)"/>
								</svg>
							</aside>
							<aside class="record-icon" onclick="window.location.href='/mobile/uc/course/study'">
								<svg x="0px" y="0px" width="32px" height="32px" viewBox="0 0 1024 1024" enable-background="new 0 0 32 32" xml:space="preserve">
								  <path fill="#ffffff" d="M512 629.333c-188.512 0-341.333-152.821-341.333-341.333s152.821-341.333 341.333-341.333c188.515 0 341.333 152.821 341.333 341.333s-152.819 341.333-341.333 341.333zM512-10.667c-164.947 0-298.667 133.717-298.667 298.667 0 164.947 133.72 298.667 298.667 298.667s298.667-133.72 298.667-298.667c0-164.949-133.72-298.667-298.667-298.667zM512.541 458.307h-42.667v-213.336h213.331v42.667h-170.664z" transform="translate(0, 812) scale(1, -1)"/>
								</svg>
							</aside>
							<div class="clear"></div>
						</div>
						<!-- /ht-r -->
					</section>
					
				</header>
				<!-- /全局头部位置 header -->

				<!-- body main -->
				<section class="comm-main animated fadeIn">
					<!-- 首页幻灯片 begin -->
					<article>
						<div class="sliderWrap">
							<section id="swipeBox" class="swipe">
								<ul class="swipeWrap">
									<c:forEach var="image" items="${websiteImages.mobileIndexCenterBanner}">
										<li>
											<a target="_blank" href="${image.linkAddress}">
												<img src="<%=staticImageServer%>${image.imagesUrl}" />
											</a>
										</li>
									</c:forEach>
								</ul>
								<div class="pager">
									<section id="pager" class="pageWrap">
										<span>
											<c:forEach items="${websiteImages.mobileIndexCenterBanner}" varStatus="status2">
												<em data-tab="${status2.index }" <c:if test="${status2.index==0}">class="on"</c:if>>&nbsp;</em> 
											</c:forEach>
										</span>
										<h6 id="s-txt" class="s-txt">
											<c:forEach var="image2" items="${websiteImages.mobileIndexCenterBanner}" varStatus="status3">
												<a href=${image2.linkAddress }"" title="" data-tab="${status3.index }" <c:if test="${status3.index==0}">class="on"</c:if>>${image2.title }</a>
											</c:forEach>
										</h6>
									</section>
							    </div>
							</section>
						<!-- /swipe end -->
						</div>
					</article>
					<!-- 首页幻灯片 end -->

					<article id="aCoursesList">
						<div class="i-box">
							<section class="tjc-box">
								<div class="tjc-list">
									<dl class="clearfix">
										<dt>
											<div class="cc-wrap">
												<section class="title-head-01">
													<svg width="60px" height="60px" viewBox="0 0 1024 1024" enable-background="new 0 0 60 60" xml:space="preserve">
													  <path fill="#f4ffad" d="M404.152736 488.989305 626.829282 703.086451l-36.32123 37.777395c-6.962575 7.241938-19.416212 6.566556-27.815509-1.509377L370.431727 554.501368c-8.399297-8.075933-9.56382-20.49273-2.601244-27.735691L404.152736 488.989305zM666.720827 217.947216c6.962575-7.241938 19.416212-6.566556 27.815509-0.537236l192.259792 184.853102c8.399297 10.122545 9.56382 22.539342 2.601244 29.782304l-51.281967 53.336766L615.43886 271.283981 666.720827 217.947216zM822.523113 499.527804l-222.689933-214.102362-180.152523 187.378378 222.689933 214.102362 180.152523-187.378378ZM510.525416 322.10546l-10.810207 11.373025c-16.555048 17.417695-39.028898 12.787234-56.447617-3.767813L85.128843 18.012627c-30.384008-28.878724-20.046568-60.109006-1.951445-79.146595 0 0 37.359886-41.782615 79.636758-1.599428l341.027024 328.715627C521.257851 282.53728 527.080463 304.687766 510.525416 322.10546zM876.53953 108.471872 529.048281 108.471872c-9.611915 0-17.404392-7.792477-17.404392-17.404392l0-29.147854c0-9.611915 7.792477-17.404392 17.404392-17.404392l347.491249 0c9.611915 0 17.404392 7.792477 17.404392 17.404392L893.943922 91.06748C893.943922 100.679395 886.151445 108.471872 876.53953 108.471872zM941.106058-19.100642l-474.814076 0c-9.611915 0-17.404392-7.792477-17.404392-17.404392l0-29.489638c0-9.611915 7.792477-17.404392 17.404392-17.404392l474.814076 0c9.611915 0 17.404392 7.792477 17.404392 17.404392l0 29.489638C958.51045-26.893118 950.717973-19.100642 941.106058-19.100642z" transform="translate(0, 812) scale(1, -1)"/>
													</svg>
													<tt>小编<br />推荐</tt>
												</section>
											</div>
										</dt>
										<c:forEach items="${mapCourseList.index_course_9}" var="courseRecommend">
										<dd>
											<div class="cc-wrap">
												<a href="/mobile/course/info/${courseRecommend.id}" title="" class="cc-list-1">
													<img xSrc="<%=staticImageServer %>${courseRecommend.mobileLogo}" src="${ctximg}/static/mobile/img/sprite.gif" alt="">
													<section class="cc-l-title">
														<p>${courseRecommend.name}</p>
													</section>
												</a>
											</div>
										</dd>
										</c:forEach>
									</dl>
								</div>
							</section>
						</div>
						<!-- /小编推荐课程 -->
						<!-- 直播推荐 -->
						<div class="i-box">
							<section class="tjc-box index-live-box">
								<div class="index-live">
									<dl>
										<dt><a href="/mobile/live/list" title="直播播报">直播播报</a></dt>
										<dd>
											<ol class="i-live-list">
												<c:forEach items="${mapCourseList.index_course_8}" var="live">
												<li>
													<c:if test="${live.beginTimeNum==0&&live.endTimeNum==0}">
														<span class="live-state live-state-over"><em>已结束</em></span>
													</c:if>
													<c:if test="${live.beginTimeNum==0&&live.endTimeNum!=0}">
														<span class="live-state live-state-ing"><em>直播中</em></span>
													</c:if>
													<c:if test="${live.beginTimeNum!=0&&live.endTimeNum!=0}">
														<span class="live-state live-state-undis"><em>未开始</em></span>
													</c:if>
													<a href="" title="">${live.name }</a>
												</li>
												
												</c:forEach>
											</ol>
										</dd>
									</dl>
									<div class="clear"></div>
								</div>
							</section>
						</div>
						<!-- /直播推荐 -->
						
						<div class="i-box">
							<section class="tjc-box">
								<div class="tjc-list hotPc-list">
									<dl class="clearfix">
										<dt>
											<div class="cc-wrap">
												<section class="title-head-01 title-head-02">
													<svg width="60px" height="60px" viewBox="0 0 1024 1024" enable-background="new 0 0 60 60" xml:space="preserve">
													  <path fill="#e14848" d="M321.024-212c-68.224 142.016-31.872 223.36 20.544 300.032 57.408 83.968 72.256 167.04 72.256 167.04 0 0 45.184-58.688 27.072-150.528 79.744 88.768 94.848 230.272 82.752 284.48 180.288-126.016 257.344-398.848 153.536-601.088 552.384 312.512 137.408 780.16 65.152 832.832 24.064-52.672 28.672-141.824-20.032-185.152-82.368 312.256-285.952 376.256-285.952 376.256 24.064-161.024-87.296-337.152-194.688-468.736-3.776 64.192-7.808 108.544-41.536 169.984-7.552-116.672-96.704-211.776-120.896-328.64-32.704-158.272 24.512-274.176 241.728-396.608z" transform="translate(0, 812) scale(1, -1)"/>
													</svg>
													<tt>热播<br />课程</tt>
												</section>
											</div>
										</dt>
										<c:forEach items="${mapCourseList.index_course_10}" var="courseHot">
										<dd>
											<div class="cc-wrap">
												<a href="/mobile/course/info/${courseHot.id}" title="" class="cc-list-1 cc-list-2">
													<img xSrc="<%=staticImageServer %>${courseHot.mobileLogo}" src="${ctximg}/static/mobile/img/sprite.gif" alt="">
													<section class="cc-l-title">
														<p>${courseHot.playcount}次播放</p>
													</section>
												</a>
												<section class="cc-name-box">
													<h6><a href="/mobile/course/info/${courseHot.id}" title="">${courseHot.name }</a></h6>
												</section>
											</div>
										</dd>
										</c:forEach>
									</dl>
								</div>
							</section>
						</div>
						<!-- /热门课程 -->
					</article>

				</section>
				<!-- body main -->

			</div>
			
			<!-- 全局底部导航菜单 begin -->
			<jsp:include page="/WEB-INF/view/mobile/common/button_menu.jsp"/>
			<!-- 全局底部导航菜单 end -->
		</div>
	</div>
	
	
	<script src="${ctximg }/static/mobile/js/classie.js" type="text/javascript"></script>
	<script src="${ctximg }/static/mobile/js/sidebarEffects.js" type="text/javascript"></script>
	<script src="${ctximg}/static/mobile/js/swipe.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			posFun(); //定位上TopBar与下menuBar的位置
			gnFun(); //当前底部导航图标与字体变色
			searchFun();
			scrollLoad(); //滚动响应加载课程图片
			showLoginInfo();
		})
		//幻灯片调用方法
		var slider =
		  Swipe(document.getElementById('swipeBox'), {
		    auto: 5000,
		    continuous: true,
		    callback: function(pos) {
		      var i = bullets.length;
		      var j = sTxts.length;
		      while (i--) {
		        bullets[i].className = ' ';
		        sTxts[i].className = ' ';
		      }
		      bullets[pos].className = 'on';
		      sTxts[pos].className = 'on';
		    }
		  });
		var bullets = document.getElementById('pager').getElementsByTagName('em');
		var sTxts = document.getElementById('s-txt').getElementsByTagName('a');
		
	</script>
</body>
</html>
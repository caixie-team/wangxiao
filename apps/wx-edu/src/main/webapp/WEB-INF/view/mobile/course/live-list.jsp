<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	
	<title>直播</title>
	
</head>
<body>
	<div id="st-container" class="st-container">
		<!-- /左侧隐藏目录菜单 end -->
		<jsp:include page="/WEB-INF/view/mobile/common/left_menu.jsp"/>
		<!-- /左侧隐藏目录菜单 begin -->
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
						<h2 class="commHeadTitle"><span>直播</span></h2>
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
				<section class="comm-main">

					<article>

						<!-- 直播列表 开始 -->
						<div>
							<section class="live-search-box">
								<ol class="clearfix">
									<li <c:if test="${queryCourse.status==0&&queryCourse.isPay==-1}">class="current"</c:if>>
										<a href="javascript:void(0)" onclick="clickSearch('clear', 0)">全部</a>
									</li>
									<li <c:if test="${queryCourse.isPay==0}">class="current"</c:if>>
										<a href="javascript:void(0)" onclick="clickSearch('isPay', 0)">免费</a>
									</li>
									<li <c:if test="${queryCourse.isPay==1}">class="current"</c:if>>
										<a href="javascript:void(0)" onclick="clickSearch('isPay', 1)">收费</a>
									</li>
									<li <c:if test="${queryCourse.status==2}">class="current"</c:if>>
										<a href="javascript:void(0)" onclick="clickSearch('status', 2)">进行中</a>
									</li>
									<li <c:if test="${queryCourse.status==1}">class="current"</c:if>>
										<a href="javascript:void(0)" onclick="clickSearch('status', 1)">未开始</a>
									</li>
								</ol>
							</section>
							<!-- /live-condition-search -->
							<section class="animated fadeInRight">
								<c:if test="${courseList==null||courseList.size()==0 }">
								<!-- /无数据公共提示 开始 -->
								<div class="undataBox">
									<span class="undata-icon">&nbsp;</span>
									<span>暂无直播</span>
								</div>
								<!-- /无数据公共提示 结束 -->
								</c:if>
								<div class="oc-live-wrap">
									<article class="oc-live-list" id="liveContent">
										<c:if test="${courseList!=null&&courseList.size()>0 }">
											<c:forEach items="${courseList }" var="live">
												<!-- 已结束 -->
												<c:if test="${live.beginTimeNum==0&&live.endTimeNum==0}">
													<dl class="livend">
														<dt>
															<aside class="r-tip-bott">
																<span>已结束</span>
															</aside>
															<div class="oc-l-timer">
																<section class="oc-l-t-d">
																	<span><fmt:formatDate value="${live.liveBeginTime }" pattern="HH:mm"/>-<fmt:formatDate value="${live.liveEndTime }" pattern="HH:mm"/></span>
																</section>
																<section class="oc-l-t-y-m">
																	<em>&nbsp;</em><span><fmt:formatDate value="${live.liveBeginTime }" pattern="yy-MM-dd"/>开课</span>
																</section>
																<div class="clear"></div>
															</div>
														</dt>
														<dd>
															<h2 class="oc-live-title">
																<span>${live.name }</span>
															</h2>
															<section class="oc-live-body">
																<h5 class="oc-l-t-name">讲师：
																	<c:forEach items="${live.teacherList }" var="teacher">
																		${teacher.name}&nbsp;&nbsp;
																	</c:forEach>
																</h5>
																<div class="comm-oc-live-btn">
																	<a href="javascript:void(0)" class="e-live-btn">已结束</a>
																</div>
															</section>
														</dd>
													</dl>
												</c:if>
												
		                                   		<!-- 直播中 -->
				                                <c:if test="${live.beginTimeNum==0&&live.endTimeNum!=0}">
				                                	<dl class="liveing">
														<dt>
															<aside class="r-tip-bott">
																<span>直播中</span>
															</aside>
															<div class="oc-l-timer">
																<section class="oc-l-t-d">
																	<span><fmt:formatDate value="${live.liveBeginTime }" pattern="HH:mm"/>-<fmt:formatDate value="${live.liveEndTime }" pattern="HH:mm"/></span>
																</section>
																<section class="oc-l-t-y-m">
																	<em>&nbsp;</em><span><fmt:formatDate value="${live.liveBeginTime }" pattern="yy-MM-dd"/>开课</span>
																</section>
																<div class="clear"></div>
															</div>
														</dt>
														<dd>
															<h2 class="oc-live-title">
																<span>${live.name }</span>
															</h2>
															<section class="oc-live-body">
																<h5 class="oc-l-t-name">讲师：
																	<c:forEach items="${live.teacherList }" var="teacher">
																		${teacher.name}&nbsp;&nbsp;
																	</c:forEach>
																</h5>
																<h6 class="oc-l-time">距离直播结束：${live.endMin }分钟</h6>
																<div class="comm-oc-live-btn">
																	<a href="javascript:playLive(${live.id})" title="" class="i-live-btn">进入直播</a>
																</div>
															</section>
														</dd>
													</dl>
				                                </c:if>
				                                <!-- 未开始-->
				                                <c:if test="${live.beginTimeNum!=0&&live.endTimeNum!=0}">
				                                	<!-- 免费-->
				                                	<c:if test="${live.isPay==0}">
				                                	 	<dl class="goLive">
															<dt>
																<aside class="r-tip-bott">
																	<span>未开始</span>
																</aside>
																<div class="oc-l-timer">
																	<section class="oc-l-t-d">
																		<span><fmt:formatDate value="${live.liveBeginTime }" pattern="HH:mm"/>-<fmt:formatDate value="${live.liveEndTime }" pattern="HH:mm"/></span>
																	</section>
																	<section class="oc-l-t-y-m">
																		<em>&nbsp;</em><span><fmt:formatDate value="${live.liveBeginTime }" pattern="yy-MM-dd"/>开课</span>
																	</section>
																	<div class="clear"></div>
																</div>
															</dt>
															<dd>
																<h2 class="oc-live-title">
																	<span>${live.name }</span>
																</h2>
																<section class="oc-live-body">
																	<h5 class="oc-l-t-name">讲师：
																		<c:forEach items="${live.teacherList }" var="teacher">
																			${teacher.name}&nbsp;&nbsp;
																		</c:forEach>
																	</h5>
																	<h6 class="oc-l-time">直播开始：${live.begin.day}天${live.begin.hour}时${live.begin.min}分</h6>
																	<div class="comm-oc-live-btn">
																		<a href="" title="" class="e-live-btn">未开始</a>
																	</div>
																</section>
															</dd>
														</dl>
				                                	</c:if>
				                                	<!-- 收费-->
				                                	<c:if test="${live.isPay>0}">
				                                	  	<dl class="goLive chare-live">
															<dt>
																<aside class="r-tip-bott">
																	<c:if test="${live.begin.day==0 }"><span>今天</span></c:if>
																	<c:if test="${live.begin.day>0 }"><span>${ live.begin.day}天后</span></c:if>
																</aside>
																<div class="oc-l-timer">
																	<section class="oc-l-t-d">
																		<span><fmt:formatDate value="${live.liveBeginTime }" pattern="HH:mm"/>-<fmt:formatDate value="${live.liveEndTime }" pattern="HH:mm"/></span>
																	</section>
																	<section class="oc-l-t-y-m">
																		<em>&nbsp;</em><span></em><span><fmt:formatDate value="${live.liveBeginTime }" pattern="yy-MM-dd"/>开课</span>
																	</section>
																	<div class="clear"></div>
																</div>
															</dt>
															<dd>
																<h2 class="oc-live-title">
																	<span>${live.name }</span>
																</h2>
																<section class="oc-live-body">
																	<h5 class="oc-l-t-name">讲师：
																		<c:forEach items="${live.teacherList }" var="teacher">
																			${teacher.name}&nbsp;&nbsp;
																		</c:forEach>
																	</h5>
																	<h6 class="oc-l-time">时间：${live.begin.day}天${live.begin.hour}时${live.begin.min}分</h6>
																	<div class="oc-l-ks-price">
																		<ol>
																			<li class="b-line"><tt>${live.lessionnum}课时</tt></li>
																			<li><tt>${live.subjectName }</tt></li>
																		</ol>
																		<aside class="ks-price-num">
																			<small>￥</small>
																			<big>${live.currentprice }</big>
																		</aside>
																	</div>
																	<div class="comm-oc-live-btn">
																		<a href="/mobile/live/order/add?courseId=${live.id }" title="" class="i-live-btn">立即购买</a>
																	</div>
																</section>
															</dd>
														</dl>
				                                	</c:if>
				                                </c:if>
											</c:forEach>
										</c:if>
									</article>
								</div>
							</section>
							<!-- /live-list -->
						</div>
						<!-- 直播列表 结束 -->
						<!-- 加载更多 开始 -->
						<section class="onload-more" style="display: none">
							<a href="javascript: void(0)" title="加载更多...">
								<img src="${ctximg }/static/mobile/img/loading.gif" />
								<span>正在努力加载中...</span>
							</a>
						</section>
						<!-- 加载更多 结束 -->
					</article>

				</section>
				<!-- body main -->
				
			</div>
			<!-- 全局底部导航菜单 begin -->
			<jsp:include page="/WEB-INF/view/mobile/common/button_menu.jsp"/>
			<!-- 全局底部导航菜单 end -->
			<form id="searchForm" action="${ctx}/mobile/live/list" method="post">
				<input type="hidden" value="${page.totalPageSize}" id="totalPageSize"/>
				<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
				<input type="hidden" id="hiddenStatus" name="queryCourse.status" value="${queryCourse.status}"/>
				<input type="hidden" id="hiddenIsPay" name="queryCourse.isPay" value="${queryCourse.isPay}"/>
			</form>
		</div>
	</div>
	<script src="${ctximg }/static/mobile/js/classie.js" type="text/javascript"></script>
	<script src="${ctximg }/static/mobile/js/sidebarEffects.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			posFun(); //定位上TopBar与下menuBar的位置
			gnFun(); //当前底部导航图标与字体变色
			searchFun();
			$("#pageCurrentPage").val(1);
			//加载分页
			$(window).bind('scroll',function(){
				var sTop = document.documentElement.scrollTop + document.body.scrollTop;
				var sHeight = document.documentElement.scrollHeight;
				var windowHeight = $(this).height();
				//当滚动到最底下时加载数据
				if(sHeight==(sTop+windowHeight)){
					var totalPageSize=parseInt($("#totalPageSize").val());//总页数
					var nextPage=parseInt($("#pageCurrentPage").val())+1;//下一页
					
					if(nextPage<=totalPageSize){
						$("#pageCurrentPage").val(nextPage);
						$(".onload-more").show();
						$.ajax({
							url:'/mobile/live/ajax/list',
							data:{"queryCourse.status":$("#hiddenStatus").val(),"queryCourse.isPay":$("#hiddenIsPay").val(),"page.currentPage":nextPage},
							type:"post",
							dataType:"text",
							success:function(result){
								$("#liveContent").append(result);
							}
						});
						$(".onload-more").hide();
					}
					
				}
			});
		})
		function clickSearch(type, id) {
	        //点击搜索时要把当前页码设置为1
	        $("#pageCurrentPage").val(1);
	    	if (type == 'status') {
	    		$("#hiddenStatus").val(id);
	    		$("#hiddenIsPay").val(-1);
	    	} else if (type == 'isPay') {
	    		$("#hiddenIsPay").val(id);
	    		$("#hiddenStatus").val(0);
	    	} else if (type == 'clear') {//清空
	    		$("#hiddenStatus").val(0);
	    		$("#hiddenIsPay").val(-1);
	    	}
	    	$("#searchForm").submit();
	    }
		function playLive(id){
			$.ajax({
				url:"/mobile/playLive/"+id,
				data:{},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.success){
						if(result.entity==''){
							window.location='/mobile/live/order/add?courseId='+id;
						}else{
							window.location=result.entity;
						}
					}else{
						alert(result.message);
					}
				}
			});
		}
	</script>
</body>
</html>
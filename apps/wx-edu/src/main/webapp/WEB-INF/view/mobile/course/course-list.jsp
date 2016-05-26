<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>课程</title>
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
						<h2 class="commHeadTitle"><span>课程</span></h2>
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

					<article id="aCoursesList">
						<section class="c-sort-title">
							<div class="c-sort-t-fixed">
								<aside class="fr c-sort-title-r">
									<span class="cs-txt" id="all-sort-btn">
										<svg width="24px" height="24px" viewBox="0 0 1024 1024" enable-background="new 0 0 24 24" xml:space="preserve">
										  <path fill="#0A59C9" d="M317.498368 329.259776c-43.9552 0-85.306368 17.140736-116.435968 48.264192-26.799104 26.804224-44.0064 80.437248-51.147776 159.412224-5.108736 56.500224-3.21536 105.228288-3.131392 107.275264l0.765952 18.865152 18.865152 0.766976c0.403456 0.016384 10.052608 0.401408 25.63584 0.401408 30.296064 0 75.758592-1.409024 120.29952-8.123392 58.812416-8.865792 98.318336-24.09472 120.776704-46.557184 31.059968-31.059968 48.19968-72.328192 48.259072-116.200448 0.060416-43.892736-16.978944-85.133312-47.977472-116.125696C402.468864 346.30016 361.304064 329.259776 317.498368 329.259776zM187.364352 623.273728c-0.096256-18.997248 0.364544-49.918976 3.383296-83.07712 6.09792-66.988032 20.046848-114.473984 39.277568-133.7088 23.391232-23.386112 54.457344-36.267008 87.473152-36.267008 32.86528 0 63.744 12.77952 86.949888 35.984384 23.246848 23.241728 36.02432 54.175744 35.979264 87.104512-0.045056 32.953344-12.923904 63.954944-36.263936 87.294976-10.88 10.881024-36.697088 25.788416-97.916928 35.016704-42.032128 6.336512-85.2992 7.665664-114.195456 7.665664C190.419968 623.286016 188.85632 623.280896 187.364352 623.273728zM707.16928 329.262848c-43.803648 0-84.960256 17.035264-115.885056 47.967232-30.998528 30.998528-48.039936 72.242176-47.983616 116.132864 0.057344 43.8784 17.200128 85.151744 48.272384 116.216832 22.453248 22.458368 61.952 37.687296 120.751104 46.552064 44.536832 6.715392 89.991168 8.12544 120.280064 8.12544 15.588352 0 25.229312-0.385024 25.631744-0.401408l18.86208-0.768 0.768-18.86208c0.082944-2.046976 1.982464-50.778112-3.1232-107.282432-7.13728-78.980096-24.348672-132.617216-51.156992-159.419392C792.47872 346.404608 751.133696 329.262848 707.16928 329.262848zM832.603136 623.29728c-28.888064 0-72.14592-1.330176-114.172928-7.666688-61.205504-9.228288-87.018496-24.133632-97.895424-35.013632-23.351296-23.346176-36.233216-54.350848-36.2752-87.30624-0.041984-32.929792 12.73856-63.868928 35.989504-87.118848 23.189504-23.195648 54.057984-35.968 86.920192-35.968 33.019904 0 64.0768 12.877824 87.450624 36.262912 19.288064 19.283968 33.253376 66.922496 39.327744 134.144 2.987008 33.063936 3.44064 63.76448 3.342336 82.655232C835.79904 623.293184 834.235392 623.29728 832.603136 623.29728zM192.009216-65.175808c-0.003072 0-0.002048 0-0.006144 0-15.552512 0-25.184256 0.72192-25.586688 0.738304l-18.8672 0.935936-0.765952 18.951168c-0.083968 2.046976-1.977344 50.814976 3.131392 107.314176 7.140352 78.971904 24.347648 132.62848 51.142656 159.433728 31.13472 31.127552 72.485888 48.278528 116.441088 48.278528 43.805696 0 84.970496-17.035264 115.91168-47.976448 30.997504-30.984192 48.03584-72.220672 47.976448-116.117504-0.060416-43.875328-17.199104-85.141504-48.26112-116.20352-22.464512-22.463488-61.98272-38.03136-120.815616-46.896128C267.766784-63.428864 222.304256-65.175808 192.009216-65.175808zM187.364352-23.866624c1.476608-0.008192 3.024896-0.350208 4.639744-0.350208 0.001024 0 0.004096 0 0.006144 0 28.894208 0 72.159232 1.668096 114.197504 8.001536 61.247488 9.228288 87.073792 24.306688 97.95584 35.188736 23.340032 23.340032 36.21888 54.426624 36.263936 87.382016 0.045056 32.930816-12.731392 63.905792-35.976192 87.140352-23.207936 23.207936-54.086656 36.00896-86.951936 36.00896-33.015808 0-64.082944-12.869632-87.476224-36.25984-19.227648-19.23584-33.1776-66.88768-39.274496-133.874688C187.72992 26.217216 187.268096-4.868352 187.364352-23.866624zM832.567296-65.175808c-0.002048 0-0.004096 0-0.006144 0-30.28992 0-75.744256 1.747968-120.271872 8.464384-58.780672 8.865792-98.269184 24.264704-120.722432 46.727168-31.067136 31.053824-48.20992 72.410112-48.267264 116.291584-0.05632 43.892736 16.985088 85.180416 47.985664 116.179968 30.922752 30.9248 72.076288 47.977472 115.876864 47.977472 43.966464 0 85.313536-17.130496 116.425728-48.256 26.805248-26.804224 44.015616-80.4352 51.151872-159.410176 5.105664-56.4992 3.207168-105.223168 3.124224-107.26912l-0.768-18.860032-18.861056-1.104896C857.832448-64.45184 848.17408-65.175808 832.567296-65.175808zM707.163136 229.525248c-32.86016 0-63.725568-12.770304-86.914048-35.95776-23.24992-23.24992-36.031488-54.191104-35.989504-87.122944 0.041984-32.958464 12.923904-63.961088 36.27008-87.297024 10.881024-10.886144 36.685824-26.129408 97.867776-35.356672 42.017792-6.337536 85.2736-8.005632 114.162688-8.005632 0.002048 0 0.004096 0 0.006144 0 1.642496 0 3.218432 0.342016 4.718592 0.350208 0.098304 18.996224-0.361472 50.086912-3.377152 83.245056-6.093824 66.983936-20.045824 114.555904-39.28576 133.79584C771.245056 216.561408 740.186112 229.525248 707.163136 229.525248z" transform="translate(0, 812) scale(1, -1)"/>
										</svg>
									</span>
								</aside>
								<aside class="fl">
									<span class="cs-txt" style="margin: 2px 0 -3px;">
										<svg width="18px" height="18px" viewBox="0 0 1024 1024" enable-background="new 0 0 18 18" xml:space="preserve">
										  <path fill="#0A59C9" d="M70.485843 621.890174l883.027291 0 0-20.466124-883.027291 0 0 20.466124ZM70.485843 310.233062l571.369156 0 0-20.466124-571.369156 0 0 20.466124ZM70.485843-1.423027l311.656089 0 0-20.466124-311.656089 0 0 20.466124Z" transform="translate(0, 812) scale(1, -1)"/>
										</svg>
									</span>
									<span class="cs-txt">课程分类检索</span>
								</aside>
								<div class="clear"></div>
							</div>
						</section>

						<!-- 检索条件精简模式 -->
						<div class="i-box c-sort-Jj-wrap">
							<section class="tjc-box c-sort-box" style="overflow: visible">
								<ul class="c-sort-Jj">
									<li>
										<a href="javascript: void(0)" class="csjj" title=""><em>按专业</em><em class="Jj-ico"></em></a>
										<section class="c-sort-sub">
											<div class="c-sort-sub-elem">
												<a href="javascript:void(0)"  onclick="clickSearch('subject',0)"  <c:if test="${queryCourse.subjectId==null||queryCourse.subjectId==0 }">class="current"</c:if>>全部</a>
												<c:forEach items="${subjectList }" var="subject">
													<a href="javascript:void(0)" onclick="clickSearch('subject',${subject.subjectId })"  <c:if test="${queryCourse.subjectId==subject.subjectId }">class="current"</c:if>>${subject.subjectName }</a>
												</c:forEach>
											</div>
										</section>
									</li>
									
									<li>
										<a href="javascript: void(0)" class="csjj" title=""><em>按讲师</em><em class="Jj-ico"></em></a>
										<section class="c-sort-sub">
											<div class="c-sort-sub-elem">
												<a href="javascript:void(0)"  onclick="clickSearch('teacher',0)"  <c:if test="${queryCourse.teacherId==null||queryCourse.teacherId==0 }">class="current"</c:if>>全部</a>
												<c:forEach items="${teacherList }" var="teacher">
													<a href="javascript:void(0)" onclick="clickSearch('teacher',${teacher.id })"  <c:if test="${queryCourse.teacherId==teacher.id }">class="current"</c:if>>${teacher.name }</a>
												</c:forEach>
											</div>
										</section>
									</li>
									<li>
										<a href="javascript: void(0)" class="csjj" title=""><em>按价格</em><em class="Jj-ico"></em></a>
										<section class="c-sort-sub">
											<div class="c-sort-sub-elem">
												<a href="javascript:void(0)" onclick="clickSearch('isPay',-1)"  <c:if test="${queryCourse.isPay==-1 }">class="current"</c:if>>全部</a>
												<a href="javascript:void(0)" onclick="clickSearch('isPay',0)"  <c:if test="${queryCourse.isPay==0 }">class="current"</c:if>>免费课程</a>
												<a href="javascript:void(0)" onclick="clickSearch('isPay',1)"  <c:if test="${queryCourse.isPay==1 }">class="current"</c:if>>收费课程</a>
											</div>
										</section>
									</li>
								</ul>
								<div class="clear"></div>
								<!-- /精简模式一二级 -->
							</section>
						</div>
						<!-- 检索条件精简模式 -->
						
						<div class="i-box">
							<section class="tjc-box c-sort-box">
								<div class="c-sort">
									<span class="cs-txt-2">检索：<tt>“<c:if test="${condition!='' }">${condition}</c:if><c:if test="${condition=='' }">全部</c:if>”</tt>有${page.totalResultSize }条结果！</span>
								</div>
							</section>
						</div>
						
						<!-- /课程一二级分类 -->
						<c:if test="${courseList==null||courseList.size()==0 }">
						<!-- /无数据公共提示 开始 -->
						<div class="undataBox">
							<span class="undata-icon">&nbsp;</span>
							<span>暂无课程</span>
						</div>
						<!-- /无数据公共提示 结束 -->
						</c:if>
						
						<!-- 课程列表 开始 -->
						<div class="i-box" id="sort-u-list">
							<c:if test="${courseList!=null&&courseList.size()>0 }">
							<section class="tjc-box">
								<div class="courese-list-wrap">
									<ul id="courseContent">
										<c:forEach items="${courseList }" var="course">
										<li>
											<a href="/mobile/course/info/${course.id}" title="">
												<section class="c-l-pic">
													<img xSrc="<%=staticImageServer%>${course.logo}" src="/static/mobile/img/sprite.gif" alt="">
												</section>
												<h2 class="courese-l-title">
													${course.name }
												</h2>
												<section class="c-l-attr">
													<span>
														<em class="p-num-ico">&nbsp;</em>
														<tt>${course.viewcount}</tt>
													</span>
													<span>
														<em class="clock-num-ico">&nbsp;</em>
														<tt>${course.lessionnum }时</tt>
													</span>
												</section>
												<section class="c-price">
													<span class="cs-txt">价格：</span>
													<span>￥${course.currentprice}</span>
												</section>
											</a>
										</li>
										</c:forEach>
									</ul>
								</div>
							</section>
							</c:if>
						</div>
						
						<!-- 课程列表 结束 -->
						<!-- 加载更多 开始 -->
						<section class="onload-more" style="display: none">
							<a href="javascript: void(0)" title="加载更多...">
								<img src="/static/mobile/img/loading.gif" />
								<span>正在努力加载中...</span>
							</a>
						</section>
						<!-- 加载更多 结束 -->
					</article>

				</section>
				<!-- body main -->

				<!-- 检索条件详细模式 -->
				<div class="c-sort-Xx-wrap" style="display: none;">
					
				</div>
				<!-- 检索条件详细模式 -->

			</div>
			<!-- 全局底部导航菜单 begin -->
			<jsp:include page="/WEB-INF/view/mobile/common/button_menu.jsp"/>
			<!-- 全局底部导航菜单 end -->
		</div>
		<form id="searchForm" action="${ctx}/mobile/course/list" method="post">
			<input type="hidden" value="${page.totalPageSize}" id="totalPageSize"/>
			<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
			<input type="hidden" id="hiddenSubjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId}"/>
			<input type="hidden" id="hiddenTeacherId" name="queryCourse.teacherId" value="${queryCourse.teacherId}"/> 
			<input type="hidden" id="hiddenIsPay" name="queryCourse.isPay" value="${queryCourse.isPay}"/>
		</form>
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
			csFun();	//课程分类检索链接层固定
			csSub(); //课程分类精简模式二级下拉
			allSort(); //点击显示全部课程分类层
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
							url:'/mobile/course/ajax/list',
							data:{"queryCourse.teacherId":$("#hiddenTeacherId").val(),"queryCourse.subjectId":$("#hiddenSubjectId").val(),"queryCourse.isPay":$("#hiddenIsPay").val(),"page.currentPage":nextPage},
							type:"post",
							dataType:"text",
							success:function(result){
								$("#courseContent").append(result);
							}
						});
						$(".onload-more").hide();
					}
					
				}
			});
		})
	    // cs-t fixed absolute
	    var csFun = function() {
	    	var ltEle = $(".c-sort-Jj");
	    	function ltFun() {
	    		var sTop = document.documentElement.scrollTop + document.body.scrollTop,
	    			suP = $("#sort-u-list").offset().top;
	    		if (sTop > 100) {
	    			ltEle.addClass("fixed-csj");
	    		} else {
	    			ltEle.removeClass("fixed-csj");
	    		};
	    	}
	    	$(window).bind("scroll" , ltFun);
	    	ltFun();
	    }
	    //cs-Jj sub hide and show
	    var csSub = function() {
	    	var csLi = $(".c-sort-Jj>li");
	    	csLi.each(function() {
	    		var _this = $(this);
	    		_this.click(function() {
	    			if (!_this.hasClass("current")) {
		    			_this.addClass("current").siblings().removeClass("current");
		    		} else {
		    			_this.removeClass("current");
		    		};
	    		})
	    	})
	    }
	    //show all sort box
	    var allSort = function() {
	    	var _ast = $("#all-sort-btn"),
	    		_asb = $(".c-sort-Xx-wrap"),
	    		_asbc ="";
	    	_ast.click(function() {
	    		
	    		$.ajax({
	    			url:'/mobile/course/ajax/condition',
	    			type:"post",
	    			data:{"queryCourse.teacherId":$("#hiddenTeacherId").val(),"queryCourse.subjectId":$("#hiddenSubjectId").val(),"queryCourse.isPay":$("#hiddenIsPay").val()},
	    			dataType:'text',
	    			async:false,
	    			success:function(result){
	    				_asb.html(result);
	    			}
	    		});
	    		_asb.show().addClass("animated0d8s bounceInUp");
	    		$("html,body").css("overflow" , "hidden");
	    		_asbc = _asb.find(".cst-close");
	    		_asbc.click(function() {
	    			
	    			_asb.addClass("bounceInDownS");
	    			setTimeout(function() {
		                $("#i-box").remove();
		            }, 800);
	    			_asb.hide().removeClass("animated0d8s bounceInUp bounceInDownS");
		            $("html,body").css("overflow" , "");
	    		})
	    	});
	    }
	    function clickSearch(type, id) {
	        //点击搜索时要把当前页码设置为1
	        $("#pageCurrentPage").val(1);
	    	if (type == 'subject') {
	    		$("#hiddenSubjectId").val(id);
	    	} else if (type == 'teacher') {
	    		$("#hiddenTeacherId").val(id);
	    	} else if (type == 'isPay') {
	    		$("#hiddenIsPay").val(id);
	    	} else if (type == 'clear') {//清空
	    		$("#hiddenTeacherId").val(0);
	    		$("#hiddenSubjectId").val(0);
	    		$("#hiddenIsPay").val(-1);
	    	}
	    	$("#searchForm").submit();
	    }
	</script>
</body>
</html>
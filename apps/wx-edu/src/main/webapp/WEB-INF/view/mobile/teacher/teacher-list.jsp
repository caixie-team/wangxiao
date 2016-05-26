<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>名家讲师</title>
	
</head>
<body>
	<div class="m-ptb54">
		<header id="header">
			<section class="h-wrap">
				<div class="menu-btn">
					<a href="/mobile/index" title="" class="go-history">&nbsp;</a>
				</div>
				<h2 class="commHeadTitle"><span>名家讲师</span></h2>
			</section>
		</header>
		<!-- /header -->
		<div>
			<!-- body main -->
			<section class="animated fadeIn">
				<c:if test="${teacherList==null||teacherList.size()==0 }">
				<!-- 无数据时提示 开始 -->
				<div class="undataBox">
					<span class="undata-icon">&nbsp;</span>
					<span>还没有讲师，小编正在整理中...</span>
				</div>
				<!-- 无数据时提示 结束 -->
				</c:if>
				
				<div class="i-box" style="margin-bottom: -50px;">
					<div id="aCoursesList" class="news-list">
						<section class="tjc-box">
							<section class="c-sort">
								<section class="tearch-list">
									<c:if test="${teacherList!=null||teacherList.size()>0 }">
									<div class="v-teacher-list">
										<ul id="teacherContent">
											<c:forEach items="${teacherList }" var="teacher">
											<li>
												<div class="oIconDesc" onclick="window.location.href='/mobile/teacher/${teacher.id}'">
													<aside class="oIcon">
														<span class="sm-u-head"><img xSrc="<%=staticImageServer %>${teacher.picPath }" src="/static/mobile/img/sprite.gif" width="55" height="41"></span>
													</aside>
													<h2 class="oIconName">${teacher.name }</h2>
													<section class="oIconTxt">${teacher.education } </section>
												</div>
											</li>
											</c:forEach>
										</ul>
									</div>
									</c:if>
								</section>
							</section>
						</section>
						<!-- 名家讲师列表 -->
						
						<input type="hidden" value="${page.totalPageSize}" id="totalPageSize"/>
						<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
						
					</div>

					<section>
						<section class="onload-more" style="display: none">
							<a title="加载更多..." href="javascript: void(0)">
								<img src="${ctximg }/static/mobile/img/loading.gif">
								<span>正在努力加载中...</span>
							</a>
						</section>
					</section>
				</div>
			
			</section>
			<!-- body main -->
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			posFun(); //定位上TopBar与下menuBar的位置
			scrollLoad(); //滚动响应加载资讯图片
			$("#pageCurrentPage").val(1);
		})
		//加载分页
			$(window).bind('scroll',function(){
				var sTop = document.documentElement.scrollTop + document.body.scrollTop;
				var sHeight = document.documentElement.scrollHeight;
				var windowHeight = $(this).height();
				//当滚动到最底下时加载数据
				if(sHeight==(sTop+windowHeight)){
					var totalPageSize=parseInt($("#totalPageSize").val());//总页数
					var nextPage=parseInt($("#pageCurrentPage").val())+1;//下一页
					$("#pageCurrentPage").val(nextPage);
					if(nextPage<=totalPageSize){
						$(".onload-more").show();
						$.ajax({
							url:'/mobile/teacher/ajax/list',
							type:"post",
							data:{"page.currentPage":nextPage},
							dataType:"text",
							success:function(result){
								$("#teacherContent").append(result);
							}
						});
						$(".onload-more").hide();
					}
					
				}
			});
	</script>
</body>
</html>
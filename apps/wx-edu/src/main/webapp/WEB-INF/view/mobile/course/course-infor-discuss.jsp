<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>课程详情</title>
	
</head>
<body>
	

							<input type="hidden" value="${page.totalPageSize}" id="totalPageSize"/>
							<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
							<div class="ask-wrap">
								<!-- 讨论区 开始 -->
								<div class="news-box" id="assessContent">
									<c:forEach items="${courseAssessList }" var="assess">
									<c:if test="${assess.userId!=userId }">
									<!-- /左侧对话气泡开始 -->
									<article class="news-art news-art-l">
										<aside class="oIcon-b">
											<span class="sm-u-head">
												<c:if test="${assess.avatar!=null&& assess.avatar!=''}">
													<img width="50" height="50" src="<%=staticImageServer%>${assess.avatar }">
												</c:if>
												<c:if test="${assess.avatar==null|| assess.avatar==''}">
													<img width="50" height="50" src="/static/mobile/img/avatar-boy.gif">
												</c:if>
											</span>
											<p class="sm-u-name">
												<c:if test="${assess.nickname==null||assess.nickname==''}">
													${assess.email }
												</c:if>
												<c:if test="${assess.nickname!=null&&assess.nickname!=''}">
													${assess.nickname }
												</c:if>
											</p>
										</aside>
										<section class="news-art-txt-wrap">
											<div class="n-l-triangle-css3 n-l-transform"></div>
											<section class="n-a-t-w-txt">
												${assess.content }
											</section>
										</section>
									</article>
									<!-- /左侧对话气泡结束 -->
									</c:if>
									<c:if test="${assess.userId==userId }">
									<!-- /右侧对话气泡开始 -->
									<article class="news-art news-art-r">
										<aside class="oIcon-b">
											<span class="sm-u-head">
												<c:if test="${assess.avatar!=null&& assess.avatar!=''}">
													<img width="50" height="50" src="<%=staticImageServer%>${assess.avatar }">
												</c:if>
												<c:if test="${assess.avatar==null||assess.avatar==''}">
													<img width="50" height="50" src="/static/mobile/img/avatar-boy.gif">
												</c:if>
											</span>
											<p class="sm-u-name">
												<c:if test="${assess.nickname==null||assess.nickname==''}">
													${assess.email }
												</c:if>
												<c:if test="${assess.nickname!=null&&assess.nickname!=''}">
													${assess.nickname }
												</c:if>
											</p>
										</aside>
										<section class="news-art-txt-wrap">
											<div class="n-r-triangle-css3 n-r-transform"></div>
											<section class="n-a-t-w-txt">
												${assess.content }
											</section>
										</section>
									</article>
									<!-- /右侧对话气泡结束 -->
									</c:if>
									</c:forEach>
								</div>
								<!-- 讨论区 结束 -->
							</div>
							<!-- /讨论输入框开始 -->
							<div class="ask-inp-box">
								<section class="ask-inp-elem">
									<div class="ask-inp">
										<textarea id="content"></textarea>
									</div>
									<aside class="ask-btn">
										<button onclick="addAssess()">发送</button>
									</aside>
								</section>
							</div>
							<!-- /讨论输入框结束 -->
							
		<script type="text/javascript">
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
						$.ajax({
							url:'/mobile/course/ajax/more/assess',
							data:{"courseId":$("#hiddenCourseId").val(),"page.currentPage":nextPage},
							type:"post",
							dataType:"text",
							success:function(result){
								$("#assessContent").append(result);
							}
						});
					}
					
				}
			});
		</script>
						
</body>
</html>
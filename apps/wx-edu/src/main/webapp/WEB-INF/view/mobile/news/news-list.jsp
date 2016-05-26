<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>新闻资讯</title>
</head>
<body>
	<div class="m-ptb54">
		<header id="header">
			<section class="h-wrap">
				<div class="menu-btn">
					<a href="/mobile/index" class="go-history">&nbsp;</a>
				</div>
				<h2 class="commHeadTitle"><span>新闻资讯</span></h2>
			</section>
		</header>
		<!-- /header -->
		<div>
			<!-- body main -->
			<section class="animated fadeIn">
				<div>
					<div class="v-card-title-box">
						<div class="v-card-title" style="position: absolute;">
							<ul class="col-3" id="item-l-ul">
								<li <c:if test="${type==0 }">class="current"</c:if>><a href="javascript:clickSearch(0,this)" title="">全部</a></li>
								<li <c:if test="${type==1 }">class="current"</c:if>><a href="javascript:clickSearch(1,this)" title="">资讯</a></li>
								<li <c:if test="${type==2 }">class="current"</c:if>><a href="javascript:clickSearch(2,this)" title="">公告</a></li>
							</ul>
						</div>
					</div>
				</div>
				<c:if test="${articleList==null||articleList.size()==0 }">
				<!-- 无数据时提示 开始 -->
				<div class="undataBox">
					<span class="undata-icon">&nbsp;</span>
					<span>还没有相关数据，小编正在整理中...</span>
				</div>
				<!-- 无数据时提示 结束 -->
				</c:if>
				
				<div class="i-box" style="margin-bottom: -50px;">
					<div id="aCoursesList" class="news-list">
						<c:if test="${articleList!=null||articleList.size()>0 }">
						<section class="tjc-box">
							<section class="c-sort">
								<ul class="news-list-ul" id="newsContent">
									<c:forEach items="${articleList}" var="article">
									<li>
										<a href="/mobile/article/${article.id }" >
											<img xsrc="<%=staticImageServer %>${article.picture}" src="${ctximg }/static/mobile/img/sprite.gif" width="100" height="50" alt="">
											<p>${article.title}</p>
										</a>
									</li>
									</c:forEach>
								</ul>
							</section>
						</section>
						</c:if>
						<!-- 新闻公告列表 -->
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
			<form id="searchForm" action="${ctx}/mobile/article/list" method="post">
				<input type="hidden" value="${page.totalPageSize}" id="totalPageSize"/>
				<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
				<input type="hidden" id="hiddenType" name="type" value="${type}"/>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			posFun(); //定位上TopBar与下menuBar的位置
			scrollLoad(); //滚动响应加载资讯图片
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
					$("#pageCurrentPage").val(nextPage);
					if(nextPage<=totalPageSize){
						$(".onload-more").show();
						$.ajax({
							url:'/mobile/article/ajax/list',
							data:{"type":$("#hiddenType").val(),"page.currentPage":nextPage},
							type:"post",
							dataType:"text",
							success:function(result){
								$("#newsContent").append(result);
							}
						});
						$(".onload-more").hide();
					}
					
				}
			});
		})
		function clickSearch(type,object) {
			if($(object).parent().attr("class")=='current'){
				return;
			}
	        //点击搜索时要把当前页码设置为1
	        $("#pageCurrentPage").val(1);
	    	$("#hiddenType").val(type);
	    	$("#searchForm").submit();
	    }
	</script>
</body>
</html>
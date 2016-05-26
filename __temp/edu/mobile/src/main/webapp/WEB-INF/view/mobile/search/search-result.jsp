<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>搜索</title>
	
</head>
<body>
				<!-- 课程 -->
				<section>
					<section class="v-card-txt-title">
						<span>课程</span>
					</section>
					<article class="v-c-txt">
						<c:if test="${courses==null||courses.size()==0 }">
						<!-- 搜索无数据时提示 -->
						<div class="undataBox">
							<span class="undata-icon">&nbsp;</span>
							<span>暂无数据</span>
						</div>
						<!-- 搜索无数据时提示 -->
						</c:if>
						<c:if test="${courses!=null&&courses.size()>0}">
						<ol class="v-c-kcb">
							<c:forEach items="${courses }" var="course">
							<li>
								<span class="list-pointer">&nbsp;</span>
								<section class="v-c-kcb-lis" onclick="window.location.href='/mobile/course/info/${course.id}'">
									<p>${course.name} </p>
								</section>
							</li>
							</c:forEach>
						</ol>
						</c:if>
					</article>
				</section>
				<!-- 直播-->
				<section>
					<section class="v-card-txt-title">
						<span>直播</span>
					</section>
					<article class="v-c-txt">
						
							<c:if test="${lives==null||lives.size()==0}">
							<!-- 搜索无数据时提示 -->
							<div class="undataBox">
								<span class="undata-icon">&nbsp;</span>
								<span>暂无数据</span>
							</div>
							<!-- 搜索无数据时提示 -->
							</c:if>
							<c:if test="${lives!=null&&lives.size()>0}">
								<ol class="v-c-kcb">
								<c:forEach items="${lives }" var="live">
								<li>
									<span class="list-pointer">&nbsp;</span>
									<section class="v-c-kcb-lis">
										<p>${live.name}</p>
									</section>
								</li>
								</c:forEach>
								</ol>
							</c:if>
						
					</article>
				</section>
				<!-- 资讯 -->
				<section>
					<section class="v-card-txt-title">
						<span>资讯</span>
					</section>
					<article class="v-c-txt">
						<c:if test="${articles==null||articles.size()==0}">
							<!-- 搜索无数据时提示 -->
							<div class="undataBox">
								<span class="undata-icon">&nbsp;</span>
								<span>暂无数据</span>
							</div>
							<!-- 搜索无数据时提示 -->
						</c:if>
						<c:if test="${articles!=null&&articles.size()>0}">
						<ol class="v-c-kcb">
							<c:forEach items="${articles }" var="article">
							<li>
								<span class="list-pointer">&nbsp;</span>
								<section class="v-c-kcb-lis"  onclick="window.location.href='/mobile/article/${article.id }'">
									<p>${article.title }</p>
								</section>
							</li>
							</c:forEach>
						</ol>
						</c:if>
					</article>
				</section>
				<!-- 资讯 -->
			
</body>
</html>

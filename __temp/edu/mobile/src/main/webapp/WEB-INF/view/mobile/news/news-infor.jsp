<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>新闻资讯详情</title>
</head>
<body>
	<div class="m-ptb54">
		<header id="header">
			<section class="h-wrap">
				<div class="menu-btn">
					<a href="/mobile/article/list?type=0" class="go-history">&nbsp;</a>
				</div>
				<h2 class="commHeadTitle"><span>新闻资讯</span></h2>
			</section>
		</header>
		<!-- /header -->
		<div>
			<!-- body main -->
			<section class="animated fadeIn">
				<div class="i-box" style="margin-bottom: -50px;">
					<div>
						<section class="newsInfor">
							<h2 class="ni-title">
								${article.title}
							</h2>
							<section class="ni-attr">
								<p><span>分类：</span><tt><c:if test="${article.type==1 }">资讯</c:if><c:if test="${article.type==2 }">公告</c:if></tt></p>
								<p><span>时间：</span><tt></span><tt><fmt:formatDate value="${article.createTime }" pattern="yyyy-MM-dd HH:mm"/></tt></p>
								<p><span>浏览：</span><tt>${article.clickTimes }人</tt></p>
							</section>
							<div class="ni-txt">
								${article.content }
							</div>
							<footer class="ni-foot">
								<c:if test="${downArticle!=null}">
									<a href="/mobile/article/${downArticle.id}" class="fr">下一篇 &gt;</a>
								</c:if>
								<c:if test="${upArticle!=null}">
									<a href="/mobile/article/${upArticle.id }" class="fl">&lt; 上一篇</a>
								</c:if>
							</footer>
						</section>
						<!-- 新闻公告详情 -->
					</div>
				</div>
			
			</section>
			<!-- body main -->
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			posFun(); //定位上TopBar与下menuBar的位置
		})
	</script>
</body>
</html>
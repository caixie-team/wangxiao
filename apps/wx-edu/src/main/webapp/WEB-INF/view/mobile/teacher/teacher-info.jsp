<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>讲师详情</title>
	
</head>
<body>
	<div class="m-ptb54">
		<header id="header">
			<section class="h-wrap">
				<div class="menu-btn">
					<a href="/mobile/teacher/list" title="" class="go-history">&nbsp;</a>
				</div>
				<h2 class="commHeadTitle"><span>名家讲师</span></h2>
			</section>
		</header>
		<!-- /header -->
		<div>
			<!-- body main -->
			<section class="animated fadeIn">
				<div class="i-box" style="margin-bottom: -50px;">
					<div id="aCoursesList" class="news-list">
						<section class="tjc-box">
							<section class="c-sort">
								<section class="tearch-list tearch-infor">
									<div class="v-teacher-list">
										<ul>
											<li>
												<div class="oIconDesc">
													<aside class="oIcon">
														<span class="sm-u-head"><img src="<%=staticImageServer %>${teacher.picPath}" width="55" height="41"></span>
													</aside>
													<h2 class="oIconName">${teacher.name }</h2>
													<h2 class="oIconeTit"><c:if test="${teacher.isStar==0 }">高级讲师</c:if><c:if test="${teacher.isStar==1 }">首席讲师</c:if></h2>
													<h3 class="oIconeTit">${teacher.education }</h3>
												</div>
												<section class="oIconTxt">${teacher.career } </section>
											</li>
										</ul>
									</div>
								</section>
							</section>
						</section>
						<!-- 名家讲师列表 -->
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
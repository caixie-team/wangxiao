<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
	<title>我的小组话题</title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/discuss/group.js?v=<%=version%>"></script>
	<style type="text/css">
	 .likes {
	float: left;
	margin-right: 19px;
	padding: 8px 0;
	width: 48px;
	line-height: 1.3;
	text-align: center;
	font-size: 13px;
	color: #ca6445;
	background: #fae9da;						
	</style>
	<script type="text/javascript">
	$(function(){
		ajaxPage("/dis/ajax/myarticle","",1,callback);
		});
	function callback(result){
		$("#myarticle").html(result);
		};
	</script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
				<section class="W-main-c fl">
					<div class="W-main-cc">
						<section class="W-main-center">
							<div class="pl20 pr20">
								<section class="mt10">
									<header class="comm-title-1">
										<ul class="clearfix">
											<li class="one current">
												<a title="小组话题" href="javascript:void(0)">小组话题</a>
												<%-- <span class="c-green disIb mt10">共有${fn:length(myDisArticle)}篇话题</span> --%>
												<div class="ct-tabarrow-bg">&nbsp;</div>
											</li>
										</ul>
									</header>
								</section>
								<div>
								<div id="myarticle">
									
								</div>
							</div>
							</div>
						</section>
						<jsp:include page="/WEB-INF/view/sns/discuss/right.jsp"></jsp:include>
					</div>
					
				</section>
				<!-- 主体区域 -->
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
<title>我的关注</title>
<script src="${ctximg}/static/sns/js/friend/friend.js?v=<%=version%>"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript"
	src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
</head>
<body class="W-body">
	<!-- 主体 开始-->
			<section class="W-main-c fl">
				<div class="W-main-cc">
					<section class="W-main-center W-main-c-r">
						<div class="pl20">
							<section class="mt10 pr20">
								<header class="comm-title-1">
									<ul class="clearfix">
										<li class="myattention"><a title="我的关注"
											href="javascript:myattention()">我的关注</a>
											</li>
											<li class="myfans"><a title="我的学友"
											href="javascript:myfans()">我的学友</a>
											</li>
											<li class="myblack"><a title="我的黑名单"
											href="javascript:myblack()">我的黑名单</a>
											</li>
											<li class="visitor"><a title="最近访客"
											href="javascript:visitor()">最近访客</a>
											</li>
									</ul>
								</header>
							</section>
							<section class="myF-list-box clearfix friHtml">
							</section>
						</div>
					</section>
				</div>
			</section>
</body>

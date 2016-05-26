<%@ page import="com.atdld.os.sns.constants.SnsConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--
    Service Support:   try    http://www.268xue.com
-->

<%@ include file="/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>-${websitemap.web.company}-${websitemap.web.title}</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="author" content="${websitemap.web.author}"/>
    <meta name="keywords" content="${websitemap.web.keywords}"/>
    <meta name="description" content="${websitemap.web.description}"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
	<link href="${ctximg}/static/sns/css/commonality-css.css?v=<%=version%>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/webutils.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/commonJs.js?v=<%=version%>"></script>
	<!--[if lt IE 9]><script src="${ctximg}/static/common/html5.js"></script><![endif]-->
	<!--[if lt IE 7]>
	<script src="${ctximg}/static/common/ie_png.js"></script>
	<script type="text/javascript">try { if(ie_png){ie_png.fix('.icon12,.icon16,.icon18,.icon22,.icon26,.ct-tabarrow-bg,.gt-btn,.gt-logo a,.gt-nav li.current a,.Prompt img,.ui-icon')};}catch(e){}</script>
	<![endif]-->
	<!-- chat -->
	<script src="${ctximg}/static/sns/js/header/header.js?v=<%=version%>" type="text/javascript"></script>
	<script type="text/javascript">
		var mydomain ="<%=mydomain%>";//主站域
		var usercookiekey="<%=usercookiekey%>";//用户key,只存key，不存实际信息
		var baselocation = "${ctx}";
		var baselocationweb = "${ctxweb}";
		var baselocationexam = "${ctxexam}";
		var imagesPath="${ctximg}";
		var keImageUploadUrl="<%=keImageUploadUrl%>";//kindeditor中使用的路径需要2个参数来区分项目和模块
		var uploadSimpleUrl="<%=uploadSimpleUrl%>";//单独的上传按钮使用的路径
		var disFaceUpUrl="<%=disFaceUpUrl%>";//小组头像上传路径
        var staticImageServer="<%=staticImageServer%>";//上传后返回路径
		var uploadServerUrl="<%=uploadServerUrl%>";
		var uid="${uid}";
		var max_text_length=<%=SnsConstants.MAX_TEXT_LENGTH%>;//博文，建议的最多次文字数量
	</script>

</head>
<body class="">
	<div class="W-wraper">
		<jsp:include page="/WEB-INF/layouts/sns/header.jsp"></jsp:include>
		<!-- default主体 开始-->
			<!-- 社区引导页 开始 -->
			<div class="mt20 pt20">
				<div class="W-main">
					<section><img src="${ctx}/static/sns/images/yd-banner-1.jpg" alt="分享知识 交流经验 互动问答 快乐学习" width="1000" height="120" class="dis" /></section>
					<div class="mt20 pt20">
						<article class="YD-l">
							<section class="mr50">
								<div class="YD-title">
									<span class="fr mt5"><a href="" class="c-888 fresh-btn"><em class="icon12">&nbsp;</em> 换一换</a></span>
									<h3 class="fl"><span class="fsize20 f-fM c-orange">推荐小组</span></h3>
								</div>
								<div class="mt20 pt10">
									<ul class="tjTeam-YD">
										<li>
											<a href="" class="tjTeam-pic"><img src="http://static.268xue.com/upload/eduplatform_268/default/dis/1.jpg" width="90" height="90" /></a>
											<div class="mt10 of">
												<span class="fr"><tt class="c-bbb">1245人加入</tt></span>
												<a href="" class="fsize16 c-b-green">英语小组</a>
											</div>
											<div class="mt10">
												<div class="cj-c-desc">近日在twitter上看到的，是一个对TED大会上所展现出来的不同演讲风格的一个概括，并且提到了在TED大会发表演讲的十条黄金法则</div>
											</div>
										</li>
										<li>
											<a href="" class="tjTeam-pic"><img src="http://static.268xue.com/upload/eduplatform_268/default/dis/2.jpg" width="90" height="90" /></a>
											<div class="mt10 of">
												<span class="fr"><tt class="c-bbb">1245人加入</tt></span>
												<a href="" class="fsize16 c-b-green">英语小组</a>
											</div>
											<div class="mt10">
												<div class="cj-c-desc">近日在twitter上看到的，是一个对TED大会上所展现出来的不同演讲风格的一个概括，并且提到了在TED大会发表演讲的十条黄金法则</div>
											</div>
										</li>
										<li>
											<a href="" class="tjTeam-pic"><img src="http://static.268xue.com/upload/eduplatform_268/default/dis/3.jpg" width="90" height="90" /></a>
											<div class="mt10 of">
												<span class="fr"><tt class="c-bbb">1245人加入</tt></span>
												<a href="" class="fsize16 c-b-green">英语小组</a>
											</div>
											<div class="mt10">
												<div class="cj-c-desc">近日在twitter上看到的，是一个对TED大会上所展现出来的不同演讲风格的一个概括，并且提到了在TED大会发表演讲的十条黄金法则</div>
											</div>
										</li>
										<li>
											<a href="" class="tjTeam-pic"><img src="http://static.268xue.com/upload/eduplatform_268/default/dis/4.jpg" width="90" height="90" /></a>
											<div class="mt10 of">
												<span class="fr"><tt class="c-bbb">1245人加入</tt></span>
												<a href="" class="fsize16 c-b-green">英语小组</a>
											</div>
											<div class="mt10">
												<div class="cj-c-desc">近日在twitter上看到的，是一个对TED大会上所展现出来的不同演讲风格的一个概括，并且提到了在TED大会发表演讲的十条黄金法则</div>
											</div>
										</li>
										<li>
											<a href="" class="tjTeam-pic"><img src="http://static.268xue.com/upload/eduplatform_268/default/dis/5.jpg" width="90" height="90" /></a>
											<div class="mt10 of">
												<span class="fr"><tt class="c-bbb">1245人加入</tt></span>
												<a href="" class="fsize16 c-b-green">英语小组</a>
											</div>
											<div class="mt10">
												<div class="cj-c-desc">近日在twitter上看到的，是一个对TED大会上所展现出来的不同演讲风格的一个概括，并且提到了在TED大会发表演讲的十条黄金法则</div>
											</div>
										</li>
										<li>
											<a href="" class="tjTeam-pic"><img src="http://static.268xue.com/upload/eduplatform_268/default/dis/6.jpg" width="90" height="90" /></a>
											<div class="mt10 of">
												<span class="fr"><tt class="c-bbb">1245人加入</tt></span>
												<a href="" class="fsize16 c-b-green">英语小组</a>
											</div>
											<div class="mt10">
												<div class="cj-c-desc">近日在twitter上看到的，是一个对TED大会上所展现出来的不同演讲风格的一个概括，并且提到了在TED大会发表演讲的十条黄金法则</div>
											</div>
										</li>
									</ul>
									<div class="clear"></div>
								</div>
							</section>
						</article>
						<aside class="YD-r">
							<div class="YD-title">
								<span class="fr mt5"><a href="" class="c-888 fresh-btn"><em class="icon12">&nbsp;</em> 换一换</a></span>
								<h3 class="fl"><span class="fsize20 f-fM c-orange">最新动态</span></h3>
							</div>
							<div class="mt20">
								<ol class="new-dt-YD">
									<li>
										<a href="" title="小组" class="fl mr10"><img src="/static/sns/images/n-yd-ico-1.jpg" alt="小组" width="50" height="50" class="dis" /></a>
										<p><span class="c-888">今天 12:22</span></p>
										<div class="mt5 cj-c-txt">
											<a href="" class="c-555">人生就像一杯茶，不会苦一辈子，但总会苦一阵子。慢慢来品，幸福的生活总在后面</a>
										</div>
									</li>
									<li>
										<a href="" title="好友" class="fl mr10"><img src="/static/sns/images/n-yd-ico-2.jpg" alt="好友" width="50" height="50" class="dis" /></a>
										<p><span class="c-888">今天 12:22</span></p>
										<div class="mt5 cj-c-txt">
											<a href="" class="c-555">人生就像一杯茶，不会苦一辈子，但总会苦一阵子。慢慢来品，幸福的生活总在后面</a>
										</div>
									</li>
									<li>
										<a href="" title="课程" class="fl mr10"><img src="/static/sns/images/n-yd-ico-3.jpg" alt="课程" width="50" height="50" class="dis" /></a>
										<p><span class="c-888">今天 12:22</span></p>
										<div class="mt5 cj-c-txt">
											<a href="" class="c-555">人生就像一杯茶，不会苦一辈子，但总会苦一阵子。慢慢来品，幸福的生活总在后面</a>
										</div>
									</li>
									<li>
										<a href="" title="考试" class="fl mr10"><img src="/static/sns/images/n-yd-ico-4.jpg" alt="考试" width="50" height="50" class="dis" /></a>
										<p><span class="c-888">今天 12:22</span></p>
										<div class="mt5 cj-c-txt">
											<a href="" class="c-555">人生就像一杯茶，不会苦一辈子，但总会苦一阵子。慢慢来品，幸福的生活总在后面</a>
										</div>
									</li>
									<li>
										<a href="" title="博文" class="fl mr10"><img src="/static/sns/images/n-yd-ico-5.jpg" alt="博文" width="50" height="50" class="dis" /></a>
										<p><span class="c-888">今天 12:22</span></p>
										<div class="mt5 cj-c-txt">
											<a href="" class="c-555">人生就像一杯茶，不会苦一辈子，但总会苦一阵子。慢慢来品，幸福的生活总在后面</a>
										</div>
									</li>
								</ol>
							</div>
						</aside>
						<div class="clear"></div>
					</div>
				</div>
				
				<div class="YD-question">
					<div class="W-main">
						<article class="YD-l">
							<section class="mr50">
								<div class="YD-title">
									<span class="fr mt5"><a href="" class="c-888 fresh-btn"><em class="icon12">&nbsp;</em> 换一换</a></span>
									<h3 class="fl"><span class="fsize20 f-fM c-blue">热门问答</span></h3>
								</div>
								<div class="mt20 pt10">
									<section class="hot-q-YD">	
										<dl>
											<dt>
												<a href="">
													<img src="http://static.268xue.com/upload/eduplatform_268/default/avatar/10.jpg" height="60" width="60">
													<p class="mt5"><span class="c-bbb"> 1@gmai</span></p>
												</a>
											</dt>
											<dd>
												<div class="hot-q-w">
													<tt class="icon22 wenIco">&nbsp;</tt>
													<a href="" class="c-blue article-q-l-link-txt" title="所以很简单的事物，那些能很容易在早期的网络上 And so very simple things -- of-the-shelf technologies">所以很简单的事物，那些能很容易在早期的网络上 And so very simple things -- of-the-shelf technologies</a>
												</div>
												<div class="mt10 hot-q-d of">
													<i class="icon12 oComment-icon mr5">&nbsp;</i>
													<p class="c-888 fl">将对面试过程进行全面的分析，帮助你正确全面的认识面试过程；从而让你从千军万马中脱颖而出</p>
												</div>
												<aside class="hot-q-num">
													<div class="clearfix">
														<section class="h-q-n-c">
															<div class="pl10 pr10" title="回答：9123">
																<span class="fsize20 c-blue">9123</span>
																<p class="c-blue">回答</p>
															</div>
														</section>
														<section class="h-q-n-l">
															<div class="pl10 pr10 v-line" title="浏览：636">
																<span class="fsize20 c-blue">636</span>
																<p class="c-blue">浏览</p>
															</div>
														</section>
													</div>
												</aside>
											</dd>
										</dl>
										<dl>
											<dt>
												<a href="">
													<img src="http://static.268xue.com/upload/eduplatform_268/default/avatar/8.jpg" height="60" width="60">
													<p class="mt5"><span class="c-bbb"> 1@gmai</span></p>
												</a>
											</dt>
											<dd>
												<div class="hot-q-w">
													<tt class="icon22 wenIco">&nbsp;</tt>
													<a href="" class="c-blue article-q-l-link-txt" title="所以很简单的事物，那些能很容易在早期的网络上 And so very simple things -- of-the-shelf technologies">所以很简单的事物，那些能很容易在早期的网络上 And so very simple things -- of-the-shelf technologies</a>
												</div>
												<div class="mt10 hot-q-d of">
													<i class="icon12 oComment-icon mr5">&nbsp;</i>
													<p class="c-888 fl">将对面试过程进行全面的分析，帮助你正确全面的认识面试过程；从而让你从千军万马中脱颖而出</p>
												</div>
												<aside class="hot-q-num">
													<div class="clearfix">
														<section class="h-q-n-c">
															<div class="pl10 pr10" title="回答：9123">
																<span class="fsize20 c-blue">9123</span>
																<p class="c-blue">回答</p>
															</div>
														</section>
														<section class="h-q-n-l">
															<div class="pl10 pr10 v-line" title="浏览：636">
																<span class="fsize20 c-blue">636</span>
																<p class="c-blue">浏览</p>
															</div>
														</section>
													</div>
												</aside>
											</dd>
										</dl>
										<dl>
											<dt>
												<a href="">
													<img src="http://static.268xue.com/upload/eduplatform_268/default/avatar/4.jpg" height="60" width="60">
													<p class="mt5"><span class="c-bbb"> 1@gmai</span></p>
												</a>
											</dt>
											<dd>
												<div class="hot-q-w">
													<tt class="icon22 wenIco">&nbsp;</tt>
													<a href="" class="c-blue article-q-l-link-txt" title="所以很简单的事物，那些能很容易在早期的网络上 And so very simple things -- of-the-shelf technologies">所以很简单的事物，那些能很容易在早期的网络上 And so very simple things -- of-the-shelf technologies</a>
												</div>
												<div class="mt10 hot-q-d of">
													<i class="icon12 oComment-icon mr5">&nbsp;</i>
													<p class="c-888 fl">将对面试过程进行全面的分析，帮助你正确全面的认识面试过程；从而让你从千军万马中脱颖而出</p>
												</div>
												<aside class="hot-q-num">
													<div class="clearfix">
														<section class="h-q-n-c">
															<div class="pl10 pr10" title="回答：9123">
																<span class="fsize20 c-blue">9123</span>
																<p class="c-blue">回答</p>
															</div>
														</section>
														<section class="h-q-n-l">
															<div class="pl10 pr10 v-line" title="浏览：636">
																<span class="fsize20 c-blue">636</span>
																<p class="c-blue">浏览</p>
															</div>
														</section>
													</div>
												</aside>
											</dd>
										</dl>
										<dl>
											<dt>
												<a href="">
													<img src="http://static.268xue.com/upload/eduplatform_268/default/avatar/6.jpg" height="60" width="60">
													<p class="mt5"><span class="c-bbb"> 1@gmai</span></p>
												</a>
											</dt>
											<dd>
												<div class="hot-q-w">
													<tt class="icon22 wenIco">&nbsp;</tt>
													<a href="" class="c-blue article-q-l-link-txt" title="所以很简单的事物，那些能很容易在早期的网络上 And so very simple things -- of-the-shelf technologies">所以很简单的事物，那些能很容易在早期的网络上 And so very simple things -- of-the-shelf technologies</a>
												</div>
												<div class="mt10 hot-q-d of">
													<i class="icon12 oComment-icon mr5">&nbsp;</i>
													<p class="c-888 fl">将对面试过程进行全面的分析，帮助你正确全面的认识面试过程；从而让你从千军万马中脱颖而出</p>
												</div>
												<aside class="hot-q-num">
													<div class="clearfix">
														<section class="h-q-n-c">
															<div class="pl10 pr10" title="回答：9123">
																<span class="fsize20 c-blue">9123</span>
																<p class="c-blue">回答</p>
															</div>
														</section>
														<section class="h-q-n-l">
															<div class="pl10 pr10 v-line" title="浏览：636">
																<span class="fsize20 c-blue">636</span>
																<p class="c-blue">浏览</p>
															</div>
														</section>
													</div>
												</aside>
											</dd>
										</dl>
										<dl>
											<dt>
												<a href="">
													<img src="http://static.268xue.com/upload/eduplatform_268/default/avatar/6.jpg" height="60" width="60">
													<p class="mt5"><span class="c-bbb"> 1@gmai</span></p>
												</a>
											</dt>
											<dd>
												<div class="hot-q-w">
													<tt class="icon22 wenIco">&nbsp;</tt>
													<a href="" class="c-blue article-q-l-link-txt" title="所以很简单的事物，那些能很容易在早期的网络上 And so very simple things -- of-the-shelf technologies">所以很简单的事物，那些能很容易在早期的网络上 And so very simple things -- of-the-shelf technologies</a>
												</div>
												<div class="mt10 hot-q-d of">
													<i class="icon12 oComment-icon mr5">&nbsp;</i>
													<p class="c-888 fl">将对面试过程进行全面的分析，帮助你正确全面的认识面试过程；从而让你从千军万马中脱颖而出</p>
												</div>
												<aside class="hot-q-num">
													<div class="clearfix">
														<section class="h-q-n-c">
															<div class="pl10 pr10" title="回答：9123">
																<span class="fsize20 c-blue">9123</span>
																<p class="c-blue">回答</p>
															</div>
														</section>
														<section class="h-q-n-l">
															<div class="pl10 pr10 v-line" title="浏览：636">
																<span class="fsize20 c-blue">636</span>
																<p class="c-blue">浏览</p>
															</div>
														</section>
													</div>
												</aside>
											</dd>
										</dl>
									</section>
								</div>
							</section>
						</article>
						<aside class="YD-r">
							<div class="YD-title">
								<h3 class="fl"><span class="fsize20 f-fM c-blue">问答排行</span></h3>
							</div>
							<div class="pt10 hot-q-rank">
								<section class="cj-comment-list">
									<dl>
										<dt><span class="cjc-1 mt5">&nbsp;</span></dt>
										<dd>
											<a href="" class="c-555">在建立Cassandra数据模型的时候，要求对数据的读取需求进可能的清晰，然后利用反范式的设计方式来实现快速的读取，原则就</a>
											<p class="of"><span class="fr c-bbb">提问者：李大型</span><span class="c-orange">回答：12312</span></p>
										</dd>
									</dl>
									<dl>
										<dt><span class="cjc-2 mt5">&nbsp;</span></dt>
										<dd>
											<a href="" class="c-555">在建立Cassandra数据模型的时候，要求对数据的读取需求进可能的清晰，然后利用反范式的设计方式来实现快速的读取，原则就</a>
											<p class="of"><span class="fr c-bbb">提问者：李大型</span><span class="c-orange">回答：12312</span></p>
										</dd>
									</dl>
									<dl>
										<dt><span class="cjc-3 mt5">&nbsp;</span></dt>
										<dd>
											<a href="" class="c-555">在建立Cassandra数据模型的时候，要求对数据的读取需求进可能的清晰，然后利用反范式的设计方式来实现快速的读取，原则就</a>
											<p class="of"><span class="fr c-bbb">提问者：李大型</span><span class="c-orange">回答：12312</span></p>
										</dd>
									</dl>
									<dl>
										<dt><span class="mt5">4</span></dt>
										<dd>
											<a href="" class="c-555">在建立Cassandra数据模型的时候，要求对数据的读取需求进可能的清晰，然后利用反范式的设计方式来实现快速的读取，原则就</a>
											<p class="of"><span class="fr c-bbb">提问者：李大型</span><span class="c-orange">回答：12312</span></p>
										</dd>
									</dl>
									<dl>
										<dt><span class="mt5">5</span></dt>
										<dd>
											<a href="" class="c-555">在建立Cassandra数据模型的时候，要求对数据的读取需求进可能的清晰，然后利用反范式的设计方式来实现快速的读取，原则就</a>
											<p class="of"><span class="fr c-bbb">提问者：李大型</span><span class="c-orange">回答：12312</span></p>
										</dd>
									</dl>
									<dl>
										<dt><span class="mt5">6</span></dt>
										<dd>
											<a href="" class="c-555">在建立Cassandra数据模型的时候，要求对数据的读取需求进可能的清晰，然后利用反范式的设计方式来实现快速的读取，原则就</a>
											<p class="of"><span class="fr c-bbb">提问者：李大型</span><span class="c-orange">回答：12312</span></p>
										</dd>
									</dl>
									<dl>
										<dt><span class="mt5">7</span></dt>
										<dd>
											<a href="" class="c-555">在建立Cassandra数据模型的时候，要求对数据的读取需求进可能的清晰，然后利用反范式的设计方式来实现快速的读取，原则就</a>
											<p class="of"><span class="fr c-bbb">提问者：李大型</span><span class="c-orange">回答：12312</span></p>
										</dd>
									</dl>
									<dl>
										<dt><span class="mt5">8</span></dt>
										<dd>
											<a href="" class="c-555">在建立Cassandra数据模型的时候，要求对数据的读取需求进可能的清晰，然后利用反范式的设计方式来实现快速的读取，原则就</a>
											<p class="of"><span class="fr c-bbb">提问者：李大型</span><span class="c-orange">回答：12312</span></p>
										</dd>
									</dl>
									<dl>
										<dt><span class="mt5">9</span></dt>
										<dd>
											<a href="" class="c-555">在建立Cassandra数据模型的时候，要求对数据的读取需求进可能的清晰，然后利用反范式的设计方式来实现快速的读取，原则就</a>
											<p class="of"><span class="fr c-bbb">提问者：李大型</span><span class="c-orange">回答：12312</span></p>
										</dd>
									</dl>
									<dl>
										<dt><span class="mt5">10</span></dt>
										<dd>
											<a href="" class="c-555">在建立Cassandra数据模型的时候，要求对数据的读取需求进可能的清晰，然后利用反范式的设计方式来实现快速的读取，原则就</a>
											<p class="of"><span class="fr c-bbb">提问者：李大型</span><span class="c-orange">回答：12312</span></p>
										</dd>
									</dl>
								</section>
							</div>
						</aside>
						<div class="clear"></div>
					</div>
				</div>
				
			</div>
			<!-- 社区引导页 结束 -->
		<!-- default主体 结束-->
		<jsp:include page="/WEB-INF/layouts/sns/footer.jsp"></jsp:include>
	</div>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>图书详情</title>
<script type="text/javascript">
$(function() {
	$(".expandClass").click(function() {
		$(".expandClass").removeClass('current');
		$(this).addClass('current');
		var id = $(this).attr('lang');
		if (id != 'house') {
			$(".publicClass").hide();
			$("#" + id).css('display', '');
		}
	});
});
</script>
</head>
<body>
	<!-- /图书介绍 -->
	<div class="pr">
		<section class="bookInfor">
			<article>
				<div class="w1000">
					<div class="mt15">
						<div class="pathwray unBr">
							<ol class="clearfix c-4e f-fM fsize14">
								<li><a class="c-4e" title="首页" href="/">首页</a> &gt;</li>
								<!-- <li><a class="c-fff" title="图书" href="${ctx}/front/homepage!showbookList.action?querybookCondition.currentPage=1">图书</a> &gt;</li> -->
								<li><a class="c-4e" title="图书" href="${ctx}/book/list">图书</a> &gt;</li>
								<li><span>${book.bookName}</span></li>
							</ol>
						</div>
						<section class="clearfix">
							<div class="fl c-play unBg" id="">
									<a class="" href="javascript:void(0)" title="">
									<c:choose>
									<c:when test="${not empty book.bookImg}">
									<img src="<%=staticImageServer%>${book.bookImg}" height="300" width="400" alt="">
									</c:when>
									<c:otherwise>
									<img src="/static/images/default_goods.jpg" height="300" width="400" alt="">
									</c:otherwise>
									</c:choose>
									</a>
									 <!-- 播放  -->
								<!-- 	<a href="javascript:void(0)"  title="播放" class="c-p-btn pa lookngVedio">&nbsp;</a> -->
							</div>
							<div class="fl">
								<article class="c-attr">
									<div>
										<h3 class="hLh30 of unFw"><font class="fsize20 f-fM">${book.bookName}</font></h3>
										<div class="s-c-desc pt10 pb10">
											<p class="c-666">${book.remarks}</p>
										</div>
										<div class="mt20 pr pb20">
											<span class="fsize16 c-4e f-fM vam"><tt class="vam f-fM">图书价格：</tt><tt class="c-red fsize20 vam f-fG">￥${book.nowPrice}</tt> 元</span>
											<c:if test="${book.nowPrice==0}">
											<span class="free-icon">&nbsp;</span>
											</c:if>
											<span class="c-red vam ml10">[折扣：${book.rebatePrice }折]</span>
											<span class="c-999 vam ml5">[定价：<s>${book.price }</s>]</span>
										</div>
									</div>
									<div class="pt10" style="border-top: 1px dotted #e2e2e2">
										<section class="mt20">
											<div class="of hLh20">
												<span class="vam" title="作者："><tt class="vam c-4e fsize16 f-fM">作者：</tt></span>
												<span class="vam ml10 teac-wrap fsize16 f-fM">
												${book.author }
												</span>
											</div>
										</section>
										<section class="mt20 c-4e fsize16 f-fM">
											<span class="vam">ISBN：</span>
											<span class="vam ml10">${book.isbn}</span>
										</section>
										<section class="mt20 c-4e fsize16 f-fM">
											<span class="vam">出版时间：</span>
											<span class="vam ml10"><fmt:formatDate value="${book.publishTime}" type="both"  pattern="YYYY-MM-dd"/><!-- <tt class="ml30 f-fM c-yellow">（在此之前可反复观看）</tt> --></span>
										</section>
										<section class="clearfix mt30">
											<div class="fl">
													<a href="${book.payUrl}"  target="_blank" class="green-btn bookBuyBtn">立即购买</a>
													<font class="f-fM c-4e fsize12 ml5">（如有疑问请拨打）${websitemap.web.phone}</font>
											</div>
											<div class="fr mt10">
												<div id="bdshare" class="bdsharebuttonbox bdshare_t bds_tools get-codes-bdshare">
													<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
													<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
													<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
													<a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
													<a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
													<a href="#" class="bds_more" data-cmd="more"></a>
												</div>
												<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"24"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
											</div>
										</section>
									</div>
								</article>
							</div>
							<div id="scrollbar" class="dragger_container"><div class="scroller dragger ui-draggable" id="scroller"></div></div>
						</section>
						
					</div>
				</div>
			</article>
		</section>
		<section class="mb50 mt20">
			<div class="w1000">
				 <aside class="fr w300" style="width: 200px;">
					<div>
						<section>
							<h3 class="of a-title unFw">
							<!-- 
							<tt class="fr">
								<a class="c-666 fsize12" title="更多" href=""><u>更多&gt;&gt;</u></a>
							</tt> -->
							<font class="c-333 f-fM fsize16">最新图书推荐</font></h3>
						</section>
						<section class="tjBookList">
							<ol>
							<c:forEach items="${bookList}" var="sameBookList">
									<li>
											<a href="${ctx}/book/info/${sameBookList.bookId }">
												<img width="100" height="100" alt="${sameBookList.bookName }" src="<%=staticImageServer%>${sameBookList.bookImg }">
											</a>
											<p class="hLh20 of"><a href="" class="c-999">${sameBookList.bookName }</a></p>
											<p><strong class="c-red f-fM fsize16">￥${sameBookList.nowPrice }</strong><span class="c-999">（${sameBookList.rebatePrice }折）</span></p>
									</li>
								</c:forEach>
							</ol>
						</section>
					</div>
					<!-- /文章排行 结束 -->
				</aside>
				<article class="fl w650" style="width: 770px;">
					<section class="bookInforTab articleListTitle">
						<h5 class="fl ml20">
							<a lang="sellSuggest" target="_self" href="javascript:void(0)" class="expandClass current">图书介绍</a>
							<a lang="sellMlId" target="_self" href="javascript:void(0)" class="expandClass">图书目录</a>
						</h5>
						<div class="clear"></div><!-- /clear float -->
					</section>
					<div id="sellSuggest" class="publicClass">
						<section class="comm-shadow-1">
							<div class="comm-title-1">
								<section>
									<span class="fl"><font class="fsize16 f-fM c-brow">图书介绍</font></span>
								</section>
							</div>
						</section>
						<!-- /图书介绍内容 -->
						<section class="pt20 pl20 pr10 c-i-kc-js">
							<c:choose>
							<c:when test="${book.bookInfo!=null && book.bookInfo!=''}">
							${book.bookInfo}
							</c:when>
							<c:otherwise>
							<section class="comm-tips-1">
							<p>
							<em class="vam c-tips-1">&nbsp;</em>
							<font class="c-999 fsize12 vam">图书内容小编正在积极整理中 . . .</font>
							</p>
							</section>
							</c:otherwise>
							</c:choose>	
						</section>
						<!-- /图书介绍内容 -->
					</div>
					<!-- 图书目录 -->
					<div id="sellMlId" class="publicClass" style="display: none;">
						<section class="comm-shadow-1">
							<div class="comm-title-1">
								<section>
									<span class="fl"><font class="fsize16 f-fM c-brow">图书目录</font></span>
								</section>
							</div>
						</section>
						<!-- /图书介绍内容 -->
						<section class="mlClass">
							<c:if test="${empty book.directory }">
							<section class="comm-tips-1">
								
								<p>
									<em class="vam c-tips-1">&nbsp;</em>
									<font class="c-999 fsize12 vam">图书目录小编正在积极整理中 . . .</font>
								</p>
							</section>
							</c:if>
							<c:if test="${not empty book.directory }">
													<section class="pt20 pl20 pr10 c-i-kc-js">
													${book.directory}
						</section>	
							</c:if>
						</section>
					</div>
					<!-- 图书目录 ，结束-->
					<!-- /图书介绍内容 结束-->
					<div  id="booklYDId" class="publicClass" style="display: none;">
						<section class="comm-shadow-1">
							<div class="comm-title-1">
								<section>
									<span class="fl"><font class="fsize16 f-fM c-brow">图书咨询</font></span>
								</section>
							</div>
						</section>
						<section id="comm_tips_section_id">
							<div class="mt20 mb50 pl20 pr20">
							<div class="comment-box">
								<span class="c-q-img-1">
								<img id="userImgId" src="" height="60" width="60" alt="">
								<!-- <img width="60" height="60" alt="" src="/images/pics/9.jpg"> --></span>
								<section class="c-box-wrap pr">
									<em class="icon16 c-q-sj">&nbsp;</em>
									<textarea rows="" id="dyId" cols="75"></textarea>
								</section>
								<section class="tar mt5">
							 	<a href="javascript:void(0)" onclick="addReview('dyId',${book.bookId})" title="我要提问" class="question-btn">我要提问</a>
								</section>
							</div>
								<section class="comment-question">
									<dl id="dayiListId">
									</dl>
							</section>
							</div>
						</section>
					</div>
					<!-- /图书咨询内容 结束-->
					<div id="bookComment" class="publicClass" style="display: none;">
						<section class="comm-shadow-1">
							<div class="comm-title-1">
								<section>
									<span class="fl"><font class="fsize16 f-fM c-brow">图书评论</font></span>
								</section>
							</div>
						</section>
						<section class="pl20 pr20">
							<div class="mt20">
								<ul class="c-comment-list">
								</ul>
							</div>
						</section>
					</div>
					<!-- /图书评论内容 结束-->
					<%--<div class="mt50">
						<section class="comm-shadow-1">
							<div class="comm-title-2">
								<section>
									<span class="fl"><font class="fsize16 f-fM c-master">同类图书推荐</font></span>
								</section>
							</div>
						</section>
						<section>
							<ul class="c-c-l">
							<c:forEach items="${bookList}" var="bk">
							<c:if test="${bk.bookSubjectid!=book.bookSubjectid}">
								<li>
									<section class="fl c-c-img">
										<c:choose>
										<c:when test="${not empty bk.bookImg}">
										<img src="<%=staticImageServer%>${bk.bookImg}" height="300" width="400"; alt="">
										</c:when>
										<c:otherwise>
										<img src="/static/images/default_goods.jpg" height="300" width="400"; alt="">
										</c:otherwise>
									</c:choose>
									</section>
									<h4 class="hLh20 of unFw"><a class="c-666 fsize18 f-fM" title="${bk.bookName}" href="<%=contextPath %>/book/book!queryBookDetail.action?queryBookCondition.bookId=${bk.bookId}">${bk.bookName}</a></h4>
									<div class="s-c-desc pt10 pb10">
										<p class="c-999">${bk.remarks}</p>
									</div>
									<div class="of mt15">
										<section class="fr c-999 mt10">
											<span>价格：${bk.nowPrice}￥</span>
											<span class="ml5"><tt class="vam">浏览量：${book.browseNum}</tt><b title="浏览量：${book.browseNum}" class="star-1-3 vam">&nbsp;</b></span>
										</section>
										<section class="fl c-c-teacher">
											<dl class="of">
												<dt><font class="c-666">作者：${bk.author }</font></dt>
											</dl>
										</section>
									</div>
								</li>
								</c:if>
							</c:forEach>
							</ul>
						</section>
					</div> --%>
					<!-- /相关图书推荐 结束-->
				</article>
				<section class="clear"></section>
			</div>
		</section>
	</div>
	<!-- /图书介绍 结束 -->
</body>
</html>
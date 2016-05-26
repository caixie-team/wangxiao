<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>${course.name }</title>
</head>
<body>
<div class="bg-fa of">
	<section class="container">
		<section class="path-wrap txtOf hLh30">
			<a class="c-999 fsize14" title="" href="/">首页</a> \
			<a class="c-999 fsize14" title="" href="${ctx}/front/showLiveList">直播列表</a>
			\ <span class="c-333 fsize14">${course.name}</span>
		</section>
		<div class="i-box clearfix">
			<article class="c-v-pic-wrap">
				<section class="p-h-video-box pr">
					<c:choose>
						<c:when test="${not empty course.logo}">
							<img class="dis c-v-pic" alt="${course.name}" src="<%=staticImageServer%>/${course.logo}">
						</c:when>
						<c:otherwise>
							<img class="dis c-v-pic" alt="${course.name}" src="<%=staticImageServer%>/${courseimagemap.courseimage.url}">
						</c:otherwise>
					</c:choose>
				</section>
			</article>
			<article class="fl c-a-wrap">
				<div class="c-attr-box">
					<h3 class="hLh30 of unFw mt5">
						<font class="c-333 fsize24 f-fM">${course.name}</font>
					</h3>
					<div class="i-q-txt pt10 pb10 mt5 i-q-txt2">
						<p class="c-999 fsize14">${course.title}</p>
					</div>
					<div class="clearfix mt20">
						<p><span class="c-orange fsize28 vam"><span class="fsize16 c-666">价格：</span><span class="c-orange fsize16">￥</span>${course.currentprice}</span>
							<span class="ml20 c-666 fsize16 vam">${course.buycount}</span><span class="fsize16 c-999 vam">人已购买</span></p>
					</div>
					<div class="mt20">
						<p class="fsize16 c-666">讲师：
							<c:forEach items="${course.teacherList }" var="tea" varStatus="index">
								<c:if test="${index.index==0}">
									<span class="c-blue1 hand" onclick="window.location.href='${ctx}/front/teacher/${tea.id}'">${tea.name }</span>
								</c:if>
								<c:if test="${index.index!=0}">
									<span class="c-blue1 hand ml10" onclick="window.location.href='${ctx}/front/teacher/${tea.id}'">${tea.name }</span>
								</c:if>
							</c:forEach>
						</p>
					</div>
					<div class="mt20">
						<p class="fsize16 c-666">&nbsp;</p>
					</div>
					<div class="mt20 clearfix">
						<div class="fl learn-btn">
							<%-- 正在直播 --%>
							<c:if test="${course.beginTimeNum==0&&course.endTimeNum!=0}">
								<c:choose>
									<%-- 可以观看 --%>
									<c:when test="${isPlay}">
										<a href="${ctx}/front/liveplay/${course.id }" title="立即观看" class="bm-lr-btn">进入直播</a>
									</c:when>
									<%-- 不可观看 --%>
									<c:otherwise>
										<a href="javascript:void(0)" title="直播已开始" class="bm-lr-btn">直播已开始</a>
									</c:otherwise>
								</c:choose>
							</c:if>
							<%-- 未开始 --%>
							<c:if test="${course.beginTimeNum!=0}">
								<c:choose>
									<%-- 可以观看 --%>
									<c:when test="${isPlay}">
										<a href="javascript:void(0)" title="未开始" class="bm-lr-btn format-date" data-diff="${course.beginTimeNum }"></a>
									</c:when>
									<%-- 不可观看 --%>
									<c:otherwise>
										<%-- 免费课程 --%>
										<c:if test="${course.isFree}">
											<a href="javascript:void(0)" title="预约直播" onclick="reseve('${course.id}')" class="bm-lr-btn">预约直播</a>
										</c:if>
										<%-- 收费课程 --%>
										<c:if test="${!course.isFree}">
											<a href="javascript:void(0)" title="立即购买${isFree}" onclick="buySellType('${course.id}')" class="bm-lr-btn">立即购买</a>
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:if>
							<%-- 已结束 --%>
							<c:if test="${course.beginTimeNum==0&&course.endTimeNum==0}">
								<c:choose>
									<%-- 可以观看 --%>
									<c:when test="${isPlay}">
										<a href="javascript:doPlay(${course.id })" title="观看回放" class="bm-lr-btn">
											观看回放
										</a>
									</c:when>
									<%-- 不可观看 --%>
									<c:otherwise>
										<a href="javascript:void(0)" title="直播已结束" class="bm-lr-btn">
											直播已结束
										</a>
									</c:otherwise>
								</c:choose>
							</c:if>
						</div>
						<div class="fl c-ml20">
							<span class="fsize12 c-666">
								&nbsp;
							</span>

							<div class="mt10 pr">
								<div class="fl">
									<c:if test="${alreadyFavorite}">
										<a class="c-cou-btn sc-already" href="javascript:void(0)"><em class="icon14 vam"></em><tt class="ml5 fsize14 c-666 vam f-fM">已收藏</tt></a>
									</c:if>
									<c:if test="${!alreadyFavorite}">
										<a class="c-cou-btn" onclick="house('${course.id}')"><em class="icon14 vam"></em><tt class="ml5 fsize14 c-666 vam f-fM">收藏</tt></a>
									</c:if>
								</div>
								<div class="fl ml15" id="c-share">
									<a class="c-cou-btn c-share" >
										<em class="icon14 vam"></em><tt class="ml5 fsize14 c-666 vam f-fM">分享</tt></a>
									<div class="share-box undis">
										<div class="bdsharebuttonbox bdshare-button-style0-16" id="bdshare" data-bd-bind="1455678358863" style="right: -160px;"><a data-cmd="more" class="bds_more" href="#"></a><a data-cmd="qzone" class="bds_qzone" href="#" title="分享到QQ空间"></a><a data-cmd="tsina" class="bds_tsina" href="#" title="分享到新浪微博"></a><a data-cmd="tqq" class="bds_tqq" href="#" title="分享到腾讯微博"></a><a data-cmd="renren" class="bds_renren" href="#" title="分享到人人网"></a><a data-cmd="weixin" class="bds_weixin" href="#" title="分享到微信"></a></div>
										<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
		<div class="mt20 mb20">
			<div class="clearfix">
				<div class="fl col-75">
					<div class="mr20">
						<div class="c-title-box">
							<ol class="js-tap clearfix">
								<li class="expandClass" lang="info"><a href="javascript:void(0)" title="直播介绍">直播介绍</a></li>
								<li class="expandClass" lang="assess"><a href="javascript:void(0)" title="直播评论">直播评论(${course.commentcount})</a></li>
								<li class="expandClass" lang="answer"><a href="javascript:void(0)" title="直播咨询">直播咨询</a></li>
							</ol>
						</div>
						<div>
							<%-- 直播介绍 --%>
							<div class="i-box publicClass">
								<div class="mt10 i-q-t3">
									<c:choose>
										<c:when test="${not empty course.context}">${course.context}</c:when>
										<c:otherwise>
											<section class="mt30 mb30 tac">
												<em class="no-data-ico cTipsIco">&nbsp;</em>
												<span class="c-666 fsize14 ml10 vam">直播简介小编正在积极整理中~</span>
											</section>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<%-- 直播评论 --%>
							<div class="i-box publicClass undis">

							</div>
							<%-- 课程咨询 --%>
							<div class="i-box publicClass undis">

							</div>
						</div>
					</div>
				</div>
				<div class="fr col-25 mt-20">
					<div class="i-box">
						<c:forEach items="${course.teacherList}" var="teacher" begin="0" end="2">
							<div class="c-teacher tac speci-inv-t">
								<div class="b-l-pic">
									<a href="${ctx}/front/teacher/${teacher.id}">
										<c:if test="${not empty teacher.picPath}">
											<img src="<%=staticImageServer%>${teacher.picPath}">
										</c:if>
										<c:if test="${empty teacher.picPath}">
											<img src="${ctx}/static/common/images/user_default.png">
										</c:if>
									</a>
								</div>
								<div class="mt15"><p class="c-333 fsize16">讲师：<a href="${ctx}/front/teacher/${teacher.id}" class="c-333">${teacher.name}</a></p></div>
								<div class="c-tea-desc mt10"><p>${teacher.career}</p></div>
							</div>
						</c:forEach>
					</div>
					<div class="i-box mt20">
						<div class="mb10"><span class="fsize16 c-333">学习此课的人（<span id="studyNum">0</span>）</span></div>
						<section class="c-learn-body">
							<c:choose>
								<c:when test="${empty userExpandList}">
									<div class="mt40">
										<!-- /无数据提示 开始-->
										<section class="no-data-wrap">
											<em class="no-data-ico cTipsIco">&nbsp;</em>
											<span class="c-666 fsize14 ml10 vam">当前还没有人学过此课程...</span>
										</section>
									</div>
								</c:when>
								<c:otherwise>
									<c:set value="0" var="studyNum" />
									<c:forEach items="${userExpandList}" var="userExpand" varStatus="index">
										<c:set value="${studyNum+1}" var="studyNum" />
										<span title="${userExpand.nickname!=null?userExpand.nickname:userExpand.email}">
											<c:if test="${not empty userExpand.avatar}">
												<img src="<%=staticImageServer%>${userExpand.avatar}">
											</c:if>
											<c:if test="${empty userExpand.avatar}">
												<img src="${ctx}/static/common/images/user_default.png">
											</c:if>
											<tt class="c-999 txtOf">${userExpand.nickname!=null?userExpand.nickname:userExpand.email}</tt>
										</span>
									</c:forEach>
									<script type="text/javascript">
										var studyNum = '${studyNum}';
										$("#studyNum").html(studyNum!=''?studyNum:0);
									</script>
								</c:otherwise>
							</c:choose>
						</section>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/course/course.js"></script>
<script type="text/javascript" src="${ctximg }/static/common/jquery.countdown.js"></script>
<script type="text/javascript">
	// 课程id
	var courseId = '${course.id}';
	// 过期类型
	var loseType='${course.losetype }';
	// 过期时间
	var loseTimeTime = '${course.loseAbsTime }';
	$(function(){
		//倒计时
		$('.format-date').countdown({
			tmpl : '<span>%{h}</span>时<span>%{m}</span>分<span>%{s}</span>秒',
			end : function(){

			}
		});
	})
</script>
</body>
</html>
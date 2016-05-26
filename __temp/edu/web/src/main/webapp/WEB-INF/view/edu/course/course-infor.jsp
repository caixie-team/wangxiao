<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>${course.name }</title>
<link rel="stylesheet" type="text/css" href="<%=imagesPath%>/static/common/ztree/courseinfo/zTreeStyle.css">
<script type="text/javascript" src="<%=imagesPath%>/static/common/ztree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/course/course.js"></script>
<script type="text/javascript" src="<%=imagesPath%>/kindeditor/kindeditor-all.js?v=<%=version%>"></script>

	<script type="text/javascript" src="${ctximg}/static/common/webutils.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/web_top.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/pageJs.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/header_msg.js?v=<%=version%>"></script>
	<!--项目共用-->
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/edu/css/common.css">




<script type="text/javascript">
var courseId='${course.id}';
//课程类型
var sellType = '${course.sellType}';
//直播代码
var freeurl = '${course.freeurl}';

var currentprice = '${course.currentprice}';
currentprice= parseFloat(currentprice).toFixed(2);
var currentCourseId='${currentCourseId}';//当前展开的课程目录的课程id
$(function (){
	kpointInit(currentCourseId);
	//课程详情节点树初始化加载
	$(".expandClass").click(function (){
 		$(".expandClass").removeClass('current');
 		$(this).addClass('current');
 		var id=$(this).attr('lang');
 		if(id!='house'){
 			$(".publicClass").hide();
 			$("#"+id).css('display','');
 		}
 	});
	
});

//购买不同状态判断不同的购买方式
function buySellType(id){
	var loseType='${course.losetype }';
    var loseTimeTime = '<fmt:formatDate value="${course.loseAbsTime }" type="both"/>';
    loseTimeTime = new Date(loseTimeTime.replace("-", "/").replace("-", "/"));
    //非直播课程直接购买
    if(loseType=='0'){
    	var nowDAte = new Date();
        if(loseTimeTime>nowDAte){
            BuyNow('${course.id}');
        }else{
            dialog('提示',"对不起，课程已过期!",1);
        }
    }else{
    	BuyNow('${course.id}');
    }
}
</script>
<style type="text/css">
.dialog-ele-1 {background: #fff;}
</style>
</head>
<body>
	<!-- /课程介绍 -->
	<div class="pr">
		<section class="course-infor-wrap" style="height: 597px;">
			<article class="c-i-w-bg" style="height: 597px;">
				<div class="w1000">
					<div class="pl20 pr20 mt15">
						<div class="pathwray unBr">
							<ol class="clearfix c-fff f-fM fsize14">
								<li>
									<a class="c-fff" title="首页" href="/">首页</a>
									&gt;
								</li>
								<li>
									<a class="c-fff" title="课程" href="${ctx}/front/showcoulist">课程</a>
									&gt;
								</li>
								<li>
									<span>${course.name}</span>
								</li>
							</ol>
						</div>
						<section class="clearfix mt20">
							<div class="fl c-play" id="vedioSpace">
								<aside class="pr of">
									<a class="lookngVedio" href="javascript:void(0)" title="播放">
										<c:choose>
											<c:when test="${not empty course.logo}">
												<img src="<%=staticImageServer%>${course.logo}" height="300" width="400"  alt="">
											</c:when>
											<c:otherwise>
												<img src="<%=imagesPath%>/static/edu/images/default/default_goods.jpg" height="300" width="400"  alt="">
											</c:otherwise>
										</c:choose>
									</a>
									<!-- 播放  -->
									<a href="javascript:openListFree()" title="播放" class="c-p-btn pa lookngVedio">&nbsp;</a>
								</aside>
							</div>
							<div class="fl">
								<article class="c-attr">
									<div>
										<h3 class="hLh30 of unFw">
											<font class="c-fff fsize24 f-fM">${course.name}</font>
										</h3>
										<div class="s-c-desc pt10 pb10 mt5">
											<p class="c-fff">${course.title}</p>
										</div>
										<div class="mt15 pr pb20 c-line-1">
											<span class="fsize16 c-fff f-fM vam">
												<em class="cost icon16 vam">&nbsp;</em>
												<tt class="vam f-fM ml5">课程价格：</tt>
												<tt class="c-yellow fsize24 vam f-fG">￥${course.currentprice}</tt>
												元
											</span>
											<c:if test="${course.currentprice==0}">
												<span class="free-icon">&nbsp;</span>
											</c:if>
											<span class="ml20">
												<c:if test="${course.memberTypes.size()>0&&salemap.sale.verifyMember=='ON'}">
													<a href="${ctx}/member/alltype/${course.memberTypes[0].id}" 
													title="<c:forEach items="${course.memberTypes}" var="cm" varStatus="index">${cm.title }<c:if test="${index.index!=course.memberTypes.size()-1}">&nbsp;</c:if></c:forEach>免费观看" 
													class="ktHY-btn">
														开通${course.memberTypes[0].title}免费观看
													</a>
												</c:if>
											</span>
										</div>
									</div>
									<div class="c-line-2 pt10">
										<section class="mt10">
											<div class="of hLh20">
												<span class="vam" title="讲师：">
													<tt class="vam c-fff fsize16 f-fM">讲师：</tt>
												</span>
												<span class="vam ml10 teac-wrap fsize16 f-fM">
													<c:forEach items="${course.teacherList }" var="tea">
														<span class="c-666" style="color: #FFFFFF;" title="${tea.name }"><a href="/front/teacher/${tea.id }" >${tea.name }</a> </span>
													</c:forEach>
												</span>
											</div>
										</section>
                                        <section class="mt10 c-fff fsize16 f-fM">
                                            <span class="vam">播放：</span>
                                            <span class="vam">${course.playcount}次</span>
                                            <span class="vam ml10">&nbsp;评论数：</span>
                                            <span class="vam">${course.commentcount}</span>
                                            <span class="vam ml10">购买数：</span>
                                            <span class="vam">${course.buycount}</span>
                                        </section>
										<section class="mt10 c-fff fsize16 f-fM">
											<span class="vam">课时：</span>
											<span class="vam ml10">${course.lessionnum}节课</span>
										</section>
                                        <%--非直播课程显示有效期--%>
                                        <c:if test="${course.sellType!='LIVE'}">
										<section class="mt10 c-fff fsize16 f-fM">
											<span class="vam">有效期：</span>
											<c:if test="${course.losetype==0 }">
												<span class="vam ml10">
													<fmt:formatDate value="${course.loseAbsTime }" />
													<tt class="ml30 f-fM c-yellow">（在此之前可反复观看）</tt>
												</span>
											</c:if>
											<c:if test="${course.losetype==1 }">
												<span class="vam ml10">
													从购买之日起${course.loseTime }天
													<tt class="ml30 f-fM c-yellow">（在此之间可反复观看）</tt>
												</span>
											</c:if>
										</section>
                                        </c:if>
                                        <%--直播课程显示开始直播时间--%>
                                        <c:if test="${course.sellType=='LIVE'}">
                                            <section class="mt10 c-fff fsize16 f-fM">
                                                <span class="vam">直播开始时间：</span>
												<span class="vam ml10">
													<fmt:formatDate value="${course.liveBeginTime}" type="both"/>
												</span>
                                            </section>
                                        </c:if>
										<section class="clearfix mt30">
											<div class="fl">
												<input type="hidden" value="${course.isPay}" id="courseIsPay"/>
												<c:if test="${course.isPay>0&&isok==false&&salemap.sale.verifyCourse=='ON'}">
													<a href="javascript:void(0)" title="立即购买" onclick="buySellType('${course.id}')" class="buy-btn">
														<font class="c-master tac">立即购买</font>
														<input type="hidden" value="no" id="playPower">
													</a>
												</c:if>
												<c:if test="${course.isPay<=0||isok==true }">
													<a href="javascript:doPlay(${course.id })" title="立即观看" class="buy-btn lookngVedio">
														<font class="c-master tac">立即观看</font>
														<input type="hidden" value="yes" id="playPower">
													</a>
												</c:if>
											</div>
											<div class="fr">
												<p class="mt30 mr20">
													<font class="f-fM c-fff fsize12">（如有疑问请拨打）${websitemap.web.phone}</font>
												</p>
											</div>
										</section>
									</div>
								</article>
							</div>
							<div id="scrollbar" class="dragger_container">
								<div class="scroller dragger ui-draggable" id="scroller"></div>
							</div>
						</section>
						<section class="mt50">
							<ul class="c-play-nav clearfix">
								<li class="current expandClass" lang="sellSuggest">
									<a href="javascript:void(0)" title="课程介绍" class="c-p-n-txt">
										<em class="icon18 c-js">&nbsp;</em>
										<tt class="vam f-fM ml5">课程介绍</tt>
									</a>
								</li>
								<li class="expandClass" lang="sellMlId">
									<a href="javascript:void(0)" title="课程目录" class="c-p-n-txt">
										<em class="icon18 c-ml">&nbsp;</em>
										<tt class="vam f-fM ml5">课程目录</tt>
									</a>
								</li>
								<li class="expandClass" lang="sellWayComment">
									<a href="javascript:void(0)" onclick="assessSellWay('${course.id}')" title="课程评论" class="c-p-n-txt">
										<em class="icon18 c-dy">&nbsp;</em>
										<tt class="vam f-fM ml5">课程评论</tt>
									</a>
								</li>
								<li class="expandClass" lang="sellZixun">
									<a href="javascript:void(0)" onclick="consultationSellWay('${course.id}')" title="课程咨询" class="c-p-n-txt">
										<em class="icon18 c-dy">&nbsp;</em>
										<tt class="vam f-fM ml5">课程咨询</tt>
									</a>
								</li>
								<li class="expandClass" lang="house">
									<a href="javascript:void(0)" title="收藏课程" onclick="house('${course.id}')" class="c-p-n-txt">
										<em class="icon18 c-sc">&nbsp;</em>
										<tt class="vam f-fM ml5">收藏课程</tt>
									</a>
								</li>
								<li class="c-share pr">
									<a href="javascript:void(0)" title="分享" class="c-p-n-txt">
										<em class="icon18 c-fx">&nbsp;</em>
										<tt class="vam f-fM">分享</tt>
									</a>
									<!-- <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
										<a class="bds_qzone"></a>
										<a class="bds_tsina"></a>
										<a class="bds_tqq"></a>
										<a class="bds_renren"></a>
										<a class="bds_t163"></a>
										<span class="bds_more">更多</span>
									</div>
									<script type="text/javascript" id="bdshare_js" data="type=tools&amp;mini=1&amp;uid=6543352"></script>
									<script type="text/javascript" id="bdshell_js"></script>
									<script type="text/javascript">
									document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
									</script> -->
									<div id="bdshare" class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a title="分享到QQ空间" href="#" class="bds_qzone" data-cmd="qzone"></a><a title="分享到新浪微博" href="#" class="bds_tsina" data-cmd="tsina"></a><a title="分享到腾讯微博" href="#" class="bds_tqq" data-cmd="tqq"></a><a title="分享到人人网" href="#" class="bds_renren" data-cmd="renren"></a><a title="分享到微信" href="#" class="bds_weixin" data-cmd="weixin"></a></div>
									<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
								</li>
							</ul>
						</section>
					</div>
				</div>
			</article>
		</section>
		<section class="mb50">
			<div class="w1000">
				<aside class="fr w300">
					<div class="mt30">
						<section>
							<h3 class="of a-title unFw">
								<span class="c-333 f-fM fsize18">购买此课的人</span>
								<!-- <small class="vam fsize12 c-666">（1586）</small> -->
							</h3>
						</section>
										<c:choose>
											<c:when test="${userExpandList==null}">
												<section class="comm-tips-1">
													<p>
														<em class="vam c-tips-1">&nbsp;</em> <font class="c-999 fsize12 vam">还没有人学过此课程...</font>
													</p>
												</section>
											</c:when>
											<c:otherwise>
											<section class="buy-cin-list">
											<c:forEach items="${userExpandList}" var="userExpand" varStatus="index">
												<c:choose>
													<c:when test="${not empty userExpand.avatar}">
														<span title="${userExpand.nickname}">
													   <img src="<%=staticImageServer%>${userExpand.avatar}" width="30" height="30" class="buy-cin-list" />
												            <c:choose>
																<c:when test="${not empty userExpand.nickname}">
																	<tt class="hLh20 c-666">${userExpand.nickname}</tt>
																</c:when>
																<c:otherwise>
																	<tt class="hLh20 c-666">${userExpand.email}</tt>
																</c:otherwise>
															</c:choose>
													   </span>
													</c:when>
													<c:otherwise>
														<span title="${userExpand.email}">
													    <img src="<%=imagesPath%>/static/common/images/user_default.jpg" width="30" height="30" class="disIb" />
										               		<c:choose>
																<c:when test="${not empty userExpand.nickname}">
																	<tt class="hLh20 c-666">${userExpand.nickname}</tt>
																</c:when>
																<c:otherwise>
																	<tt class="hLh20 c-666">${userExpand.email}</tt>
																</c:otherwise>
															</c:choose>
														</span>
													</c:otherwise>
												</c:choose>
											</c:forEach>
											</section>
											</c:otherwise>											
									   </c:choose>									
					</div>
					<!-- /已学习过的人 结束 -->
					<div class="mt30">
						<section>
							<h3 class="of a-title unFw">
								<tt class="fr">
									<a class="c-666 fsize12" title="更多" href="${ctx}/front/articlelist/1">
										<u>更多&gt;&gt;</u>
									</a>
								</tt>
								<font class="c-333 f-fM fsize18">资讯排行</font>
							</h3>
						</section>
						<section class="article-list-1">
							<ol>
								<c:forEach items="${articleListOrderclickTimes}" var="article" varStatus="index">
									<li>
										<tt class="order b-master c-fff">
											<font class="f-fM fsize16">${index.index+1}</font>
										</tt>
										<a title="${article.title}" href="${ctx}/front/toArticle/${article.id}">${article.title}</a>
									</li>
								</c:forEach>
							</ol>
						</section>
					</div>
					<!-- /文章排行 结束 -->
				</aside>
				<article class="fl w650">
					<div id="sellSuggest" class="publicClass">
						<section class="comm-shadow-1">
							<div class="comm-title-1">
								<section>
									<span class="fl">
										<font class="fsize16 f-fM c-brow">课程介绍</font>
									</span>
								</section>
							</div>
						</section>
						<!-- /课程介绍内容 -->
						<section class="pt20 pr10 c-i-kc-js">
							<c:choose>
								<c:when test="${not empty course.context}">${course.context}</c:when>
								<c:otherwise>
									<section class="comm-tips-1">
										<p>
											<em class="vam c-tips-1">&nbsp;</em> <font class="c-999 fsize12 vam">课程内容小编正在积极整理中 . . .</font>
										</p>
									</section>
								</c:otherwise>
							</c:choose>
						</section>
						<!-- /课程介绍内容 -->
					</div>
					<!-- 课程目录 开始-->
					<div id="sellMlId" class="publicClass" style="display: none;">
						<section class="comm-shadow-1">
							<div class="comm-title-1">
								<section>
									<span class="fl">
										<font class="fsize16 f-fM c-brow">课程目录</font>
									</span>
								</section>
							</div>
						</section>
						<section class="mlClass">
							<c:if test="${empty coursePackageList }">
								<section class="comm-tips-1">
									<p>
										<em class="vam c-tips-1">&nbsp;</em> <font class="c-999 fsize12 vam">课程目录小编正在积极整理中 . . .</font>
									</p>
								</section>
							</c:if>
							
							<div class="zTreeMenuWrap">
								<c:forEach items="${ coursePackageList }" var="packageCourse" varStatus="index">
									<!-- 是课程树多个时候显示课程名称，否则直接显示课程目录 -->
									<c:if test="${coursePackageList.size()>1}">
										<div class="comm-title-2" onclick="kpointInit(${packageCourse.id})">
											<section>
												<c:if test="${index.index==0 }">
													<span id="flztreeopen_${packageCourse.id}" class="fl zTreeOpen">
														<em class="icon18 bcIcon"></em>
														<font class="fsize16 f-fM c-333 vam ml5">${packageCourse.name}</font>
													</span>
												</c:if>
												<c:if test="${index.index>0 }">
													<span id="flztreeopen_${packageCourse.id}" class="fl">
														<em class="icon18 bcIcon"></em>
														<font class="fsize16 f-fM c-333 vam ml5">${packageCourse.name}</font>
													</span>
												</c:if>
											</section>
										</div>
									</c:if>
									<section class="mt10">
											 <div class="ztree" id="courseTree_${packageCourse.id}"></div>
									</section>
								</c:forEach>
							</div>
						</section>
					</div>
					<!-- 课程目录 ，结束-->
					<!-- 课程评论 开始-->
					<div id="sellWayComment" class="publicClass" style="display: none;">
						<section class="comm-shadow-1">
							<div class="comm-title-1">
								<section>
									<span class="fl">
										<font class="fsize16 f-fM c-brow">课程评论</font>
									</span>
								</section>
							</div>
						</section>
						<section class="pl20 pr20 ajaxHtml1">
							<!-- 课程评论分页HTML -->
						</section>
					</div>
					<!-- 课程评论 结束-->
					<!-- 课程咨询 开始-->
					<div id="sellZixun" class="publicClass" style="display: none;">
						<section class="comm-shadow-1">
							<div class="comm-title-1">
								<section>
									<span class="fl">
										<font class="fsize16 f-fM c-brow">课程咨询</font>
									</span>
								</section>
							</div>
						</section>
						<div class="comment-box">
									<span class="c-q-img-1">
										<c:if test="${empty user.avatar}">
											<img id="userImgId" src="/static/common/images/user_default.jpg" height="60" width="60" >
										</c:if>
										<c:if test="${not empty user.avatar}">
											<img id="userImgId" src=" <%=staticImageServer %>${user.avatar }" height="60" width="60" >
										</c:if>
									</span>
									<section class="c-box-wrap pr">
										<em class="icon16 c-q-sj">&nbsp;</em>
										<textarea rows="" id="dyId" cols="75"></textarea>
									</section>
									<section class="tar mt5">
										<a href="javascript:void(0)" onclick="addReview(${course.id})" title="提交评论" class="question-btn">提交咨询</a>
									</section>
						</div>
								
						<section class="ajaxHtml2">
							<div class="mt10 ml10">
											<section class="question-list lh-bj-list pr" id="problemUlListId">
											
											</section>
							</div>
						</section>
					</div>
					<!-- 课程咨询 结束-->
						
					<div class="mt50">
						<section class="comm-shadow-1">
							<div class="comm-title-2">
								<section>
									<span class="fl">
										<font class="fsize16 f-fM c-master">同类课程推荐</font>
									</span>
								</section>
							</div>
						</section>
						<section>
							<ul class="c-c-l">
								<c:forEach items="${courseList}" var="course">
									<li>
										<section class="fl c-c-img">
											<c:choose>
												<c:when test="${!empty course.logo&&course.logo!=''}">
													<img src="<%=staticImageServer%>${course.logo}" height="116" width="154" alt="${course.name}">
												</c:when>
												<c:otherwise>
													<img src="<%=imagesPath%>/static/edu/images/default/default_goods.jpg" height="116" width="154" alt="${course.name}">
												</c:otherwise>
											</c:choose>
										</section>
										<h4 class="hLh20 of unFw">
											<a class="c-666 fsize18 f-fM" title="${course.name}"
												href="${ctx}/front/couinfo/${course.id}">${course.name}</a>
										</h4>
										<div class="s-c-desc pt10 pb10">
											<p class="c-999">${course.title}</p>
										</div>
										<div class="of mt15">
											<section class="fr c-999 mt10">
												<span>价格：${course.currentprice}￥</span>
												<span class="ml5">
													<tt class="vam">浏览量：${course.viewcount}</tt>
													<b title="浏览量：${course.pageViewcount}" class="star-1-3 vam">&nbsp;</b>
												</span>
											</section>
											<section class="fl c-c-teacher">
												<dl class="of">
													<dt><font class="c-666">讲师：</font></dt>
													<c:forEach items="${course.teacherList}" var="teacher">
													<a class="c-666" href="${ctx }/front/teacher/${teacher.id}">${teacher.name}</a>&nbsp; 
													</c:forEach>
												</dl>
											</section>
										</div>
									</li>
								</c:forEach>
							</ul>
						</section>
					</div>
					<!-- /相关课程推荐 结束-->
				</article>
				<section class="clear"></section>
			</div>
		</section>
	</div>
	<!-- /课程介绍 结束 -->
	<div class="undis" id="smallDiv">
	<div class="bg-shadow-infor"></div>
	<div class="dialog-shadow-infor" style="top: 50%; margin-left: -320px;margin-top: -240px;"><div class="dialog-ele-infor"><h4 class="d-s-head-infor pr" style="width: 628px;"><a class="dClose icon16 pa" title="关闭" onclick="$('#smallDiv').hide();$('#smallPlayer').html('');" href="javascript:void(0)">&nbsp;</a><span class="d-s-head-txt-infor" id="playTitleTwo"></span></h4><div style="" class="pt20 pb20 pl20 pr20 of" id="">

	<div class="d-tips-55">
			<div style="left: 50%;top: 50%;margin: -20px 0 0 -50px;width: 100px;height: 40px;z-index: 5;" id="loadingDiv" class="tac pa">
				<span class="v-loading-gif ">
					<img width="50" height="10" src="/static/edu/images/page/v-loading.gif">
				</span>
				<aside class="mt10"><span class="c-333">努力加载中...</span></aside>
			</div>
			<div id="smallPlayer" style="width: 612px; height: 440px;z-index: 9;" class="pa">
			
			</div>
		</div>
	</div></div></div>
	</div>
</body>
</html>
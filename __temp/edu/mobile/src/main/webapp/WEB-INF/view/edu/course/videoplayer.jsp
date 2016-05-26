<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<title>视频播放学习大厅-${websitemap.web.company}-${websitemap.web.company}</title>
	<meta name="author" content="${websitemap.web.author}" />
	<meta name="keywords" content="${websitemap.web.keywords}" />
	<meta name="description" content="${websitemap.web.description}" />
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/edu/css/common.css">
	<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/webutils.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/web_top.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/pageJs.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/header_msg.js?v=<%=version%>"></script>
	<!--项目共用-->
	<link rel="stylesheet" type="text/css" href="<%=imagesPath%>/static/edu/css/learnHall.css">
	<link rel="stylesheet" type="text/css" href="<%=imagesPath%>//static/common/ztree/videoplayer/zTreeStyle.css">
	<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/vedio/swfobject.js"></script>
	<script type="text/javascript" src="<%=imagesPath%>/static/common/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/vedio/video.js"></script>
	<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/vedio/note.js"></script>
	<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/vedio/assess.js"></script>
	<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/vedio/problem.js"></script>
	<script type="text/javascript" src="<%=imagesPath%>/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
	<!--[if lt IE 9]><script src="<%=imagesPath%>/static/common/html5.js"></script><![endif]-->
	
	<script type="text/javascript">
		var baselocation = "${ctx}";
		var buyURL = baselocation+"/buy";
		var usercookiekey="<%=usercookiekey%>";
		var mydomain="<%=mydomain%>";
		var keImageUploadUrl="<%=keImageUploadUrl%>";//kindeditor中使用的路径需要2个参数来区分项目和模块
		var uploadSimpleUrl="<%=uploadSimpleUrl%>";//单独的上传按钮使用的路径
		var staticImageServer="<%=staticImageServer%>";//返回路径
		var courseId = "${course.id}";//课程id
		var currentCourseId=${currentCourseId};//当前展开的课程目录的课程id
		var currentKpointId='${kpointId}';//直接播放该节点
		var countPlayTimeOut='15';//播放后记录播放次数的延时

	</script>
</head>
<body>
	<header id="lh-header">
	
		<section class="lh-head">
			<div class="lh-h plr35 clearfix">
				<section class="fr mt20">
					<ol class="lh-right-link">
						<li><em class="icon24 lh-goback">&nbsp;</em><a href="${ctx }/uc/home" title="" class="vam ml5">返回个人中心</a>|</li>
						<li><span class="u-h-name cusName" ></span>|</li>
						<li class="pr"><tt id="msgCountId" class="tip-news pa undis" title=""></tt><a href="${ctx }/uc/letter" id="aelement">消息</a>|</li>
						<li class="lh-r-l-last"><a href="javascript:exit()"  title="退出">退出</a></li>
					</ol>
					<div class="clear"></div>
				</section>
				<section class="fl mt5">
					<span class="lh-logo"><a href="/" title="${websitemap.web.company}-学习大厅"><img src="<%=staticImageServer %>${logomap.logo.url}" class="logo-2013" width="83" height="52" alt="${websitemap.web.company}-学习大厅"></a></span>
					<span class="vice-logo">&nbsp;</span>
				</section>
			</div>
		</section>
	</header>
	<!-- /lh-header -->
	<section id="lh-play-wrap">
		<div class="plr35 lh-umain">
			<article class="pr">
				<aside class="lh-right-wrap">
					<section class="lh-r-w-head">
						<!-- <div class="lh-h-NumPlay"><span class="c-fff fsize12 f-fM"><tt class="fsize16 f-fM palycount" ></tt> 播放</span></div> -->
						<ol>
							<li id="lh-r-li-course" class="unBg current"><em class="icon30 kc-menu">&nbsp;</em><a href="javascript: void(0)" title="课程目录">课程目录</a><i class="icon24 kc-icon">&nbsp;</i></li>
							<li id="lh-r-li-about"  onclick="aboutCourse()"><em class="icon30 kc-note">&nbsp;</em><a href="javascript: void(0)" title="关于课程">关于课程</a><i class="icon24 kc-icon">&nbsp;</i></li>
						</ol>
						<div class="clear"></div>
					</section>
					<div id="lh-r-body" class="lh-r-body">
						<!-- /关于课程 开始-->
						<section id="aoutCourseSectionId" class="dis">
							<div class="mt10 mb10 ml10 mr10">
								<section>
									<ol id="sup-chageCard-title" class="sup-chageCard-title">
									<li class="current"><a class="kcmu" onclick="changeicurrent()" href="javascript: void(0)" title="课程目录">目录</a></li>
									<li><a class="xxpj" onclick="if($(this).parent('li').hasClass('current')){return false;}getCourse('xxpj',2);" href="javascript: void(0)" title="学习笔记">笔记</a></li>
									<li><a class="gswd" onclick="if($(this).parent('li').hasClass('current')){return false;}getCourse('gswd',2)" href="javascript: void(0)" title="课程答疑">答疑</a></li>
									<li><a class="gsbr" onclick="if($(this).parent('li').hasClass('current')){return false;}getCourse('gsbr',2)" href="javascript: void(0)" title="课程评论">评论</a></li>
									</ol>
									<div class="clear"></div>
								</section>
								<div id="sup-chageCard-cont">
									<section class="dis">
										<div class="mt10 tac bf-num" id="bofang">
										</div>
										<!-- /课程目录 开始-->
										<div class="ml10 lh-menu-wrap">
											<menu id="lh-menu" class="lh-menu mr10 mb10">
												<!-- 如果list》1代表是套餐课程，显示套餐下的课程名称 -->
												<c:if test="${coursePackageList.size()>1}">
													<c:forEach items="${coursePackageList }" var="course" varStatus="index">
														<div class="tcTabWrap">
															<a href="javascript: void(0)" onclick="displaycourseTree(${course.id})"><em class="lh-menu-i-1 icon24 mr5"><font>${index.index+1 }</font></em>${course.name}</a>
														</div>
														<ul class="ztree" id="courseTree_${course.id}"></ul>
													</c:forEach>
												</c:if>
												<c:if test="${coursePackageList.size()==1}">
													<c:forEach items="${coursePackageList }" var="course" varStatus="index">
														<ul class="ztree" id="courseTree_${course.id}"></ul>
													</c:forEach>
												</c:if>
											</menu>
										</div>
										<!-- /课程目录 结束-->
									</section>
									
									<!--  课程讲义结束-->
									<section class="undis">
										<div class="mt10 ml10 mr10">
											<h4 class="publishTitle publishTitle-1">随手记录学习感悟！</h4>
											<section class="note-textarea noteTextArea" >
												<textarea name="notesContext" onkeyup="$('#notContextId').hide();" id="notesContextId"></textarea>
												<section class="mt5 clearfix">
													<span class="fr">
														<tt class="mr5 c-orange" style="display: none;" id="notContextId">您还没有输入笔记</tt>
														<a href="javascript: void(0)" onclick="addNotest()" title="保存" class="bc-btn">保存</a>
													</span>
												</section>
											</section>
										</div>
									</section>
									<section class="undis">
										<div class="mt10 ml10 mr10">
											<h4 class="publishTitle publishTitle-2">提交疑惑，大家来解答！</h4>
											<section class="note-textarea">
												<textarea name="" onkeydown="$('#notProblemTTId').hide();" id="problemContextTextareaId"></textarea>
											</section>
											<section class="mt5 clearfix">
												<span class="fr">
													<tt class="mr5 c-orange" style="display: none;" id="notProblemTTId">请输入您的问题内容！</tt>
													<a href="javascript: void(0)" onclick="addProblemFun()" title="提交问题" class="bc-btn">提交问题</a>
												</span>
											</section>
										</div>
										<div class="mt10 ml10 mr10">
											<h5 class="bj-list-title of">
												<span id="hudongSapnId1" class="current"><a href="javascript:void(0)" onclick="if($(this).parent('span').hasClass('current')){return false;}getCourse('gswd',2)" title="互动答疑">互动答疑</a></span>
												<span>|</span>
												<span id="hudongSapnId2"><a href="javascript:void(0)" onclick="if($(this).parent('span').hasClass('current')){return false;}getCourse('gswd',1)" title="精华互动">精华互动</a></span>
											</h5>
										</div>
										<div class="mt10 ml10">
											<section class="question-list lh-bj-list pr">
												<section id="notProblemId" class="comm-tips-1" style="display: none;">
													<p>
														<em class="c-tips-2 icon24">&nbsp;</em>
														<font class="c-999 fsize12 vam">还没有互动问题！</font>
													</p>
												</section>
												<ul class="pr10" id="problemUlListId">
												</ul>
											</section>
										</div>
									</section>
									<section class="undis">
										<div class="mt10 ml10 mr10">
											<h4 class="publishTitle publishTitle-3">提交疑惑，大家来解答！</h4>
											<section class="note-textarea">
												<textarea name="" onkeydown="$('#notAssessInfoId').hide();" id="assessInofContextId"></textarea>
											</section>
											<section class="mt5 clearfix">
												<span class="fr">
													<tt id="notAssessInfoId" style="display: none;" class="mr5 c-orange">请输入您对课程的评论！</tt>
													<a href="javascript: void(0)" onclick="addAssess()" title="发表评论" class="bc-btn">发表评论</a>
												</span>
											</section>
										</div>
										<div class="mt10 ml10 mr10">
											<h5 class="bj-list-title of">
												<span class="current"><a href="javascript:void(0)" title="">评论列表</a></span>
											</h5>
										</div>
										<div class="mt10 ml10">
											<section class="lh-bj-list pr">
												<ul class="pr10 assessajaxHtml" id="assessListUlId">
												</ul>
											</section>
										</div>
									</section>
								</div>
							</div>
						</section>
						<!-- /关于课程 结束-->
					</div>
				</aside>
				<section class="lh-play-box">
					<div class="clearfix lh-p-b-title">
						<section class="course-up fl">
							<em class="icon14 c-u">&nbsp;</em><samp id="upLast"></samp>
						</section>
						<section class="course-down fr">
							<samp id="downLast"></samp><em class="icon14 c-d">&nbsp;</em>
						</section>
						<section class="curse-playing">
							<span><em class="mr10 icon30">&nbsp;</em><big id="titleIdBig"></big></span>
						</section>
					</div>
					<div class="lh-play-body pr">
						<span class="v-loading-gif undis"><img src="<%=imagesPath%>/static/edu/images/page/v-loading.gif" width="50" height="10"  /></span>
						<aside id="o-c-btn" class="o-c-btn">
							<a href="javascript: void(0)" title="展开">展开</a>
						</aside>
						<section id="vedio" class="flash-play-wrap of pr">
						</section>
					</div>
				</section>
			</article>
		</div>
	</section>

	<!-- /lh-play-wrap -->

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html> <!--<![endif]-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>试听</title>
	<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/nxb/web/css/reset.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/nxb/web/css/global.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/nxb/web/css/play-learn-nxb.css">

</head>
<body>
<div id="N-course-box" class="NXB-cbox">
	<article class="NXB-pc-wrap">
		<div class="N-hiderightbtn"><a href="javascript: void(0)" id="N-hideRightBtn" title="收起" ><em class="icon18"></em></a></div>
		<div>
			<div class="N-pr-loading"></div>
			<a href="${ctx}/uc/home" title="返回个人中心" class="cl-back"><em class="icon14"></em><small class="fsize12 c-333 ml5">个人中心</small></a>
			<div class="N-l-title">
				<section class="N-l-t-box">
					<h3 class="N-l-t-title">
						<span class="N-l-tt-z">第<small classs="fsize14" id="chapterTip">1</small>章</span>
						<span class="N-l-tt-j">小节<big id="kpointTip">2</big></span>
						<span class="N-l-tt-x">
							[<c:if test="${kpoint.courseType=='VIDEO'}">视频</c:if>
							<c:if test="${kpoint.courseType=='GRAPHIC'}">图文</c:if>
							<c:if test="${kpoint.courseType=='MP3'}">MP3</c:if>
							<c:if test="${kpoint.courseType=='PDF'}">PDF</c:if>]
							${kpoint.name}
						</span>
					</h3>
					<aside class="N-L-tt-up-dw">
						<a href="javascript:void(0)" title="上一节" class="nlt-up"><em class="icon12"></em> 上一节</a>
						<a href="javascript:void(0)" title="下一节" class="nlt-dw">下一节 <em class="icon12"></em></a>
					</aside>
				</section>
			</div>
			<div class="pl-flash-box" style="overflow:auto;background-color: white;" id="vedioBox">
			</div>
		</div>
		<!-- left area element -->
	</article>
</div>
<!-- /左侧播放区 结束-->
<div id="N-toolBar-box" class="NXB-cTool">
	<div class="nct-bo">
		<section class="n-ctInfo">
			<div class="n-c-attr-box">
				<h3 class="hLh30 txtOf">
					<a class="c-333 fsize14" title="" href="${ctx}/front/couinfo/${course.id}">${course.name}</a>
				</h3>
				<div class="hLh20 txtOf">
					<aside class="fl col-50" title="播放量：${course.playcount}"><em class="icon12 c-play-num"></em><small class="fsize12 vam c-master2">${course.playcount}</small></aside>
					<aside class="fl col-50" title="评论数：${course.commentcount}"><em class="icon12 c-review-num"></em><small class="fsize12 vam c-master2">${course.commentcount}</small></aside>
				</div>
				<p class="hLh30 txtOf fsize12">
					<span class="c-999" title="<c:forEach items="${course.teacherList}" var="teacher">${teacher.name}&nbsp;&nbsp;
					</c:forEach>">讲师：</span>
					<c:forEach items="${course.teacherList}" var="teacher">
						<a href="${ctx}/front/teacher/${teacher.id}" class="c-999" target="_blank">${teacher.name}</a>
					</c:forEach>
				</p>
			</div>
			<a href="${ctx}/front/couinfo/${course.id}" title="${course.name}" class="n-c-pic">
				<c:choose>
					<c:when test="${not empty course.logo}">
						<img width="134" height="75"  alt="${course.name}" src="<%=staticImageServer%>/${course.logo}">
					</c:when>
					<c:otherwise>
						<img width="134" height="75"  alt="${course.name}" src="<%=staticImageServer%>/${courseimagemap.courseimage.url}">
					</c:otherwise>
				</c:choose>
			</a>
		</section>
		<section class="n-ctTabs">
			<a href="javascript:void(0)" lang="n-ct-cont-menu" class="current" title="目录">试听目录</a>
		</section>
		<section class="n-ctCont-box">
			<article class="n-ct-cont-menu">
				<div class="n-ct-of">
					<c:if test="${empty kpointList}">
						<section class="mt30 mb30 tac">
							<em class="no-data-ico cTipsIco">&nbsp;</em>
							<span class="c-666 fsize14 ml10 vam">课程目录小编正在积极整理中~</span>
						</section>
					</c:if>
					<c:if test="${not empty kpointList}">
						<c:if test="${course.sellType=='COURSE'}">
							<c:set value="0" var="chapterIndex" />
							<c:set value="0" var="kpointIndex" />
							<c:set value="start" var="liType"/>
							<c:forEach items="${kpointList}" var="kpoint">
								<c:if test="${kpoint.type==1}">
									<c:if test="${liType=='end'}">
										<c:set value="start" var="liType"/>
										</ul>
										<div class="c-white-box"></div>
										</div>
									</c:if>
									<c:set value="${chapterIndex+1}" var="chapterIndex" />
									<div class="cou-info-menu">
										<div class="chapter-name txtOf">第${chapterIndex}章<span class="ml5">${kpoint.name}</span></div>
									</div>
								</c:if>
								<c:if test="${kpoint.type==0}">
									<c:set value="${kpointIndex+1}" var="kpointIndex" />
									<c:if test="${liType=='start'}">
										<div class="chap-seclist pr">
										<ul>
									</c:if>
									<c:set value="end" var="liType"/>
									<li parentIndex="${chapterIndex}" kpointIndex="${kpointIndex}" lang="${kpoint.id}" <c:if test="${kpoint.isfree==1}">onclick="auditionKpoint('${kpoint.id}')"</c:if>>
										<div class="c-p-wrap">
											<div class="c-blue-dot"><tt></tt></div>
											<a class="c-p-title" href="javascript:void(0)"><c:if test="${kpoint.isfree==1}">(试听)</c:if>第${kpointIndex}节 <span class="ml5">${kpoint.name}</span></a>
											<c:if test="${kpoint.courseType=='VIDEO'||empty kpoint.courseType}">
												<a class="play-icon-box" title="视频播放" href="javascript:void(0)">
													<small title="时长：${kpoint.courseMinutes}分${kpoint.courseSeconds}秒" class="vam fsize12 c-ccc">${kpoint.courseMinutes}分${kpoint.courseSeconds}秒</small>
													<em class="icon16 ml5"></em>
												</a>
											</c:if>
											<c:if test="${kpoint.courseType=='GRAPHIC'}">
												<a class="play-icon-box image-icon-box" title="图文播放" href="javascript:void(0)">
													<small title="时长：${kpoint.courseMinutes}分${kpoint.courseSeconds}秒" class="vam fsize12 c-ccc">${kpoint.courseMinutes}分${kpoint.courseSeconds}秒</small>
													<em class="icon16 ml5"></em>
												</a>
											</c:if>
											<c:if test="${kpoint.courseType=='PDF'}">
												<a class="play-icon-box wd-icon-box" title="PDF播放" href="javascript:void(0)">
													<small title="时长：${kpoint.courseMinutes}分${kpoint.courseSeconds}秒" class="vam fsize12 c-ccc">${kpoint.courseMinutes}分${kpoint.courseSeconds}秒</small>
													<em class="icon16 ml5"></em>
												</a>
											</c:if>
											<c:if test="${kpoint.courseType=='MP3'}">
												<a class="play-icon-box audio-icon-box" title="音频播放" href="javascript:void(0)">
													<small title="时长：${kpoint.courseMinutes}分${kpoint.courseSeconds}秒" class="vam fsize12 c-ccc">${kpoint.courseMinutes}分${kpoint.courseSeconds}秒</small>
													<em class="icon16 ml5"></em>
												</a>
											</c:if>
										</div>
									</li>
								</c:if>
							</c:forEach>
						</c:if>
					</c:if>
				</div>
			</article>
			<!-- /目录 区域 结束 -->
		</section>
	</div>
</div>
<!-- /右侧工具区 结束-->
<!--[if lt IE 9]><script src="js/html5.js"></script><![endif]-->
<script src="${ctx}/static/common/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/common/webutils.js"></script>
<script type="text/javascript">
	var baselocation = "${ctx}";
	var currentCourseId='${currentCourseId}';//课程id
	var currentKpointId='${kpoint.id}';//课时Id
	$(function() {
		_browserRedirect();

		if(currentCourseId!=''&&currentKpointId!=''){
			getPlayerHtml(currentCourseId,currentKpointId);
		}
		$("[lang="+currentKpointId+"]").addClass("current");
		var parentIndex = $("[lang="+currentKpointId+"]").attr("parentIndex");
		var kpointIndex = $("[lang="+currentKpointId+"]").attr("kpointIndex");
		$("#chapterTip").html(parentIndex);
		$("#kpointTip").html(kpointIndex);

		$(".chap-seclist ul>li").mousemove(function(){
			var _this = $(this);
			if(_this.attr("lang")!=currentKpointId){
				_this.addClass("current");
			}

		});
		$(".chap-seclist ul>li").mouseout(function(){
			var _this = $(this);
			if(_this.attr("lang")!=currentKpointId){
				_this.removeClass("current");
			}
		});
	})

	function auditionKpoint(id){
		window.location.href=baselocation+'/front/auditionKpoint/'+currentCourseId+"?kpointId="+id;
	}
	// 获得播放器的html
	function getPlayerHtml(courseId,kpointId) {
		$.ajax({
			url : baselocation + "/front/ajax/getKopintHtml",
			data : {
				"kpointId" : kpointId,
				"courseId" : courseId
			},
			type : "post",
			dataType : "text",
			async:false,
			success : function(result) {
				$("#vedioBox").html(result);
			}
		});
	}

	//右侧工具条关闭打开
	var nhBtn = $("#N-hideRightBtn"),
			nxbBox = $("#N-course-box"),
			ntlBox = $("#N-toolBar-box"),
			oTime = 220;
	var rBarFun = function() {  //  PC SHOW
		nhBtn.click(function() {
			var _this = $(this);
			if (!_this.hasClass("open")) {
				nxbBox.animate({"right": "0"}, oTime);
				ntlBox.animate({"width": "0"}, oTime);
				_this.addClass("open").attr("title","打开");
			} else {
				nxbBox.animate({"right": "320px"}, oTime);
				ntlBox.animate({"width": "320px"}, oTime);
				_this.removeClass("open").attr("title","收起");
			};
		})
	}
	var mRbarFun = function() { // MOB SHOW
		var mWid = $(window).width(),
				mRT = $(".n-ctInfo"),
				nhBtnM = $('<div class="N-hiderightbtn" style="right: inherit;left: -18px;"><a href="javascript: void(0)" id="MN-hideRightBtn" title="收起"><em class="icon18"></em></a></div>');
		nhBtn.addClass("open");
		nhBtn.click(function() {
			var _this = $(this);
			if (_this.hasClass("open")) {
				nxbBox.animate({"right": mWid+"px"}, oTime);
				ntlBox.animate({"width": (mWid-20)+"px"}, oTime);
				_this.removeClass("open").attr("title","打开");
			};
			mRT.append(nhBtnM);
			$("#MN-hideRightBtn").click(function() {
				nxbBox.animate({"right": "0"}, oTime);
				ntlBox.animate({"width": "0"}, oTime);
				_this.addClass("open").attr("title","收起");
				nhBtnM.remove();
			})
		});
	}
	//各端显示结构控制
	function _browserRedirect() {
		var sUserAgent = navigator.userAgent.toLowerCase();
		var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
		var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
		var bIsMidp = sUserAgent.match(/midp/i) == "midp";
		var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
		var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
		var bIsAndroid = sUserAgent.match(/android/i) == "android";
		var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
		var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
		if (bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {   // mob show
			mRbarFun();
			$(".footwrap").hide();
		} else {
			rBarFun(); // pc show
		}
	}
</script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<c:choose>
	<c:when test="${courseKpoint.courseType=='GRAPHIC'}">
			${courseText}
	</c:when>
	<c:when test="${courseKpoint.courseType=='PDF'}">
		<c:if test="${not empty courseKpoint.url}">
			<input type="hidden" id="pdfurl" value="${courseKpoint.url}" />
		</c:if>
		<c:if test="${empty courseKpoint.url}">
			<input type="hidden" id="pdfurl" value="<%=staticImageServer%>${pdfUrl}" />
		</c:if>

		<div id="pdf">
			<a id="viewerPlaceHolder" style="margin: 0 auto; height: 1000px; display: block"></a>
		</div>
	</c:when>
	<c:when test="${courseKpoint.courseType=='MP3'}">
		<c:if test="${not empty courseKpoint.url}">
			<audio id="playerLabel" src="${courseKpoint.url}" controls="controls" type="audio/mp3"></audio>
		</c:if>
		<c:if test="${empty courseKpoint.url}">
			<audio id="playerLabel" src="<%=staticImageServer %>${mp3Url}" controls="controls" type="audio/mp3"></audio>
		</c:if>
	</c:when>
	<c:when test="${courseKpoint.courseType=='VIDEO' and courseKpoint.videotype=='CC'}">
		<%-- cc视频 --%>
		<script src='http://p.bokecc.com/player?vid=${courseKpoint.videourl}&siteid=${ccwebsitemap.cc.ccappID}&autoStart=true&width=100%&height=100%&playerid=51A2AD3118ACAD37&playertype=1'type='text/javascript'></script>
	</c:when>
	<c:when test="${courseKpoint.courseType=='VIDEO' and courseKpoint.videotype=='FIVESIX'}">
		<%-- 56视频 --%>
		<iframe src="${courseKpoint.videourl}" width="100%" height="100%"
			frameborder="0" scrolling="no"></iframe>
	</c:when>
	<c:when test="${courseKpoint.courseType=='VIDEO' and courseKpoint.videotype=='LETV'}">
		<%-- 乐视云--%>
		<embed src="http://yuntv.letv.com/bcloud.swf" wmode="transparent"
			allowFullScreen="true" quality="high" width="100%" height="100%"
			align="middle" allowScriptAccess="always"
			flashvars="uu=${websiteLetvmap.letv.user_unique }&vu=${courseKpoint.videourl}&auto_play=1&gpcflag=1&width=1024&height=768&extend=0&share=0"
			type="application/x-shockwave-flash"></embed>
	</c:when>
	<c:when test="${courseKpoint.courseType=='VIDEO' and courseKpoint.videotype=='BAOLI'}">
		<%-- 保利--%>
		<c:if test="${isMoblie==true}">
			<div id='plv_${courseKpoint.videourl}'></div>
			<script>
				var player = polyvObject('#plv_${courseKpoint.videourl}').videoPlayer({
					'width':'100%',
					'height':'0',
					'vid' : '${courseKpoint.videourl}'
				});
				</script>
		</c:if>
		<c:if test="${isMoblie==false}">
			<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
				width="100%" height="100%" id="polyvplayer${courseKpoint.videourl}">
				<PARAM NAME=movie VALUE="http://player.polyv.net/videos/player.swf">
				<param name="allowscriptaccess" value="always">
				<param name="wmode" value="Transparent">
				<param name="flashvars" value="vid=${courseKpoint.videourl}" />
				<param name="allowFullScreen" value="true" />
				<EMBED src="http://player.polyv.net/videos/player.swf" width="100%"
					height="100%" TYPE="application/x-shockwave-flash"
					allowscriptaccess="always" wmode="Transparent"
					name="polyvplayer${courseKpoint.videourl}" allowFullScreen="true"
					flashvars="vid=${courseKpoint.videourl}" /></EMBED>
			</OBJECT>
		</c:if>
	</c:when>
	
	
	
	<c:when test="${courseKpoint.courseType=='VIDEO' and courseKpoint.videotype=='SWF'}">
		<%-- SWF/IFRAME --%>
		<iframe src="${courseKpoint.videourl}" width="100%" height="100%"
			frameborder="0" scrolling="no"></iframe>
	</c:when>
	<c:when test="${courseKpoint.courseType=='VIDEO' and courseKpoint.videotype=='mp4flv'}">
		<%-- 本地视频 --%>
		<div id="vedio" style="width: 100%; height: 100%;"></div>
		<script type="text/javascript"
			src="/static/common/ckplayer/ckplayer.js" charset="utf-8"></script>
		<c:if test="${isMoblie==true}">
			<script type="text/javascript">
		                var flashvars={
		                    f:'/static/common/ckplayer/m3u8.swf',
		                    a:'${m3u8_360}',
		                    c:0,
		                    s:4,
		                    lv:0,//注意，如果是直播，需设置lv:1
		                    b:0,
		                    p:1,//默认播放
		                    g:'${watchTime}',//默认开始播放时间秒
		                    //i:'/static/ckplayer6.6/temp/1.jpg',//默认底图
		                    liuchang: encodeURIComponent('${m3u8_270}'),
		                    biaoqing: encodeURIComponent('${m3u8_360}'),
		                    gaoqing: encodeURIComponent('${m3u8_720}'),
		                    loaded:'loadedHandler'
		                };
		                var video=['${mp4_360}'];
		                CKobject.embed('/static/common/ckplayer/ckplayer.swf','vedio','ckplayer_a1','100%','100%',false,flashvars,video);
		            </script>
		</c:if>
		<c:if test="${isMoblie==false}">
			<script type="text/javascript">	
		                var flashvars={
		                    f:'/static/common/ckplayer/m3u8.swf',
		                    a:'${m3u8_360}',
		                    c:0,
		                    s:4,
		                    lv:0,//注意，如果是直播，需设置lv:1
		                    b:0,
		                    p:1,//默认播放
		                    g:'${watchTime}',//默认开始播放时间秒
		                    //i:'/static/ckplayer6.6/temp/1.jpg',//默认底图
		                    liuchang: encodeURIComponent('${m3u8_270}'),
		                    biaoqing: encodeURIComponent('${m3u8_360}'),
		                    gaoqing: encodeURIComponent('${m3u8_720}'),
		                    loaded:'loadedHandler'
		                };
		                CKobject.embed('/static/common/ckplayer/ckplayer.swf','vedio','ckplayer_a1','100%','100%',false,flashvars);
		            </script>
		</c:if>
	</c:when>
		<c:otherwise>
		<!-- 错误类型的 先用iframe承接 -->
		<iframe src="${courseKpoint.videourl}" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>
	</c:otherwise>
</c:choose>
<script src="${ctximg}/static/edu/build/mediaelement-and-player.min.js"></script>
<link rel="stylesheet" href="${ctximg}/static/edu/build/mediaelementplayer.min.css" />
<script type="text/javascript" src="${ctximg}/static/common/flexpaper/flexpaper_flash.js"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/front/vedio/swfobject.js"></script>
<script type="text/javascript">
	var time = 300000;// 延时长度(单位：毫秒)
	var courseId = ${courseKpoint.courseId};
	var kpointId = ${courseKpoint.id};
	var timer;
	$(function(){
		$(window).load(function(){
			timer = setInterval(function(){oneMinuteAjax();},time);
			setTimeout(function(){addPlayTimes();},time);
		});

	});
	// 每5分钟更新一次数据
	function oneMinuteAjax(){
		$.ajax({
			url:"${ctx}/ajax/updatePlayTime",
			type:"post",
			data:{"kpointId":kpointId,"courseId":courseId},
			dataType:"json",
			success:function(result){

			}
		});
	}
	// 增加播放次数
	function addPlayTimes(){
		$.ajax({
			url :  baselocation + "/course/playertimes",
			data : {
				"kpointId" : kpointId,
				"courseId" : courseId
			},
			type : "post",
			dataType : "text",
			async:false,
			success : function(result) {
			}
		});
	}
	$(function(){
		var pdfUrl = $("#pdfurl").val();
		var fp = new FlexPaperViewer(
				'FlexPaperViewer',
				'viewerPlaceHolder', {
					config : {
						SwfFile : escape(pdfUrl),//需要使用Flexpaper打开的文档
						Scale : 1.2, 					//初始化缩放比例，参数值应该是大于零的整数
						ZoomTransition : 'easeOut',		//Flexpaper中缩放样式，它使用和Tweener一样的样式，默认参数值为easeOut.其他可选值包括: easenone, easeout, linear, easeoutquad
						ZoomTime : 0.5,					//从一个缩放比例变为另外一个缩放比例需要花费的时间，该参数值应该为0或更大。
						ZoomInterval : 0.2,				//缩放比例之间间隔，默认值为0.1，该值为正数。
						FitPageOnLoad : false,			//初始化得时候自适应页面，与使用工具栏上的适应页面按钮同样的效果
						FitWidthOnLoad : false,			//初始化的时候自适应页面宽度，与工具栏上的适应宽度按钮同样的效果
						FullScreenAsMaxWindow : false,	//当设置为true的时候，单击全屏按钮会打开一个flexpaper最大化的新窗口而不是全屏，当由于flash播放器因为安全而禁止全屏，而使用flexpaper作为独立的flash播放器的时候设置为true是个优先选择
						ProgressiveLoading : false,		//当设置为true的时候，展示文档时不会加载完整个文档，而是逐步加载，但是需要将文档转化为9以上的flash版本（使用pdf2swf的时候使用-T 9 标签）。
						MinZoomSize : 0.2,				//设置最大的缩放比例
						MaxZoomSize : 5,				//最小的缩放比例
						SearchMatchAll : true,			//设置为true的时候，单击搜索所有符合条件的地方高亮显示
						InitViewMode : 'Portrait',		//设置启动模式如"Portrait" or "TwoPage" or "SinglePage"
						ViewModeToolsVisible : true,	//工具栏上是否显示样式选择框
						ZoomToolsVisible : true,		//工具栏上是否显示缩放工具
						NavToolsVisible : true,			//工具栏上是否显示导航工具
						CursorToolsVisible : true,		//工具栏上是否显示光标工具
						SearchToolsVisible : true,		//工具栏上是否显示搜索
						localeChain: 'zh_CN'			//中文
					}
				}
		);
	});


	var playmp3 = new MediaElementPlayer('#playerLabel',{
		translations:['es','ar','yi','zh-cn'],
		features: ['playpause','progress','current','duration','tracks','volume','fullscreen'],
		success: function (mediaElement, domObject) {
			mediaElement.addEventListener('timeupdate', function(e) {

			}, false);
			mediaElement.play();
		},
		error: function () {

		}
	});
	playmp3.pause();
	if(${courseKpoint.courseType=='MP3'}){
		playmp3.play();

	}


</script>
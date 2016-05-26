<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
<title>文库详情</title>
	<script type="text/javascript" src="${ctximg}/static/common/webutils.js"></script>
	<script type="text/javascript" src="${ctximg}/static/common/flexpaper/flexpaper_flash.js"></script>
	<script type="text/javascript" src="${ctximg}/static/common/PDFObject/pdfobject.min.js"></script>
	<script type="text/javascript">
		var type = '${library.type}';
		var pdfUrl = '${library.pdfUrl}';
		$(function(){

			if('${type}'=='pdf'){
				$("#pdfDiv").show().siblings().hide();
				window.onload = function() {
					new PDFObject({url: '<%=staticImageServer%>'+pdfUrl}).embed("pdfDiv"); //PDF文件的地址
				}

			}else if('${type}'=='swf'){

				$("#flashPdf").show().siblings().hide();
				var fp = new FlexPaperViewer(
						'FlexPaperViewer',
						'viewerPlaceHolder', { config : {
							SwfFile : escape('<%=staticImageServer%>'+pdfUrl),//需要使用Flexpaper打开的文档
							Scale : 1.2, 						//初始化缩放比例，参数值应该是大于零的整数
							ZoomTransition : 'easeOut',		//Flexpaper中缩放样式，它使用和Tweener一样的样式，默认参数值为easeOut.其他可选值包括: easenone, easeout, linear, easeoutquad
							ZoomTime : 0.5,					//从一个缩放比例变为另外一个缩放比例需要花费的时间，该参数值应该为0或更大。
							ZoomInterval : 0.2,				//缩放比例之间间隔，默认值为0.1，该值为正数。
							FitPageOnLoad : false,				//初始化得时候自适应页面，与使用工具栏上的适应页面按钮同样的效果
							FitWidthOnLoad : false,			//初始化的时候自适应页面宽度，与工具栏上的适应宽度按钮同样的效果
							FullScreenAsMaxWindow : false,		//当设置为true的时候，单击全屏按钮会打开一个flexpaper最大化的新窗口而不是全屏，当由于flash播放器因为安全而禁止全屏，而使用flexpaper作为独立的flash播放器的时候设置为true是个优先选择
							ProgressiveLoading : false,		//当设置为true的时候，展示文档时不会加载完整个文档，而是逐步加载，但是需要将文档转化为9以上的flash版本（使用pdf2swf的时候使用-T 9 标签）。
							MinZoomSize : 0.2,					//设置最大的缩放比例
							MaxZoomSize : 5,					//最小的缩放比例
							SearchMatchAll : true,				//设置为true的时候，单击搜索所有符合条件的地方高亮显示
							InitViewMode : 'Portrait',			//设置启动模式如"Portrait" or "TwoPage" or "SinglePage"
							ViewModeToolsVisible : true,		//工具栏上是否显示样式选择框
							ZoomToolsVisible : true,			//工具栏上是否显示缩放工具
							NavToolsVisible : true,			//工具栏上是否显示导航工具
							CursorToolsVisible : true,			//工具栏上是否显示光标工具
							SearchToolsVisible : true,			//工具栏上是否显示搜索
							localeChain: 'zh_CN'//中文
						}}
				);
				}else{
					$("#pdfDiv").show().siblings().hide();
					$("#pdfDiv").html(${flibrary.content});
				}

		});
	</script>
</head>
<body>
	<div class="mb50">
		<section class="library-info-box">
			<section class="path-wrap txtOf hLh30">
				<a class="c-999 fsize14" title="" href="/">首页</a> 
				 \ <a href="${ctx}/library/list" title="文库" class="c-999 fsize14">文库</a>
				 \ <span class="c-666 fsize14">${library.name}</span>
			</section>
			<div class="mt30">
				<section class="clearfix">
					<article class="">
						<section class="pb20 line3">
							<h2 class="tac unFw">
								<c:if test="${library.type==1 }">
									<img width="16" height="18" class="vam" src="${ctximg}/static/edu/images/library/pdf.png" title="PDF阅读">
								</c:if>
								<c:if test="${library.type==2 }">
								<img width="16" height="18" class="vam" src="${ctximg}/static/edu/images/library/pic.png" title="PIC阅读">
								</c:if>
								<font class="fsize24 c-333 f-fM vam" >${library.name}</font>
							</h2>
							<div class="mt5 mb5 tac">
								<span title='上传时间' class="c-999 vam disIb"><em class="icon14 a-time mr5">&nbsp;</em><fmt:formatDate value="${library.addTime}" type="both"/> </span>
								<span title="查看人数${library.browseNum}人" class="disIb c-999 vam ml20"><em class="icon14 a-read mr5">&nbsp;</em>${library.browseNum}人</span>
							</div>
						</section>
						<div>
							<div class="mt10 c-666">
								<div id="pdfDiv" style="margin:0 auto;height:1000px;">
									您的浏览器没有Adobe或不支持PDF<a href="<%=staticImageServer%>${flibrary.pdfUrl}">点击下载</a>
								</div>
								<div id="flashPdf">
									<a id="viewerPlaceHolder" style="margin:0 auto;height:1000px;display:block" ></a>
								</div>
							</div>
						</div>
					</article>
				</section>
			</div>
		</section>
	</div>
</body>
</html>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
 
<title>我要提问</title>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/ucenter/u_suggest.js?v=<%=version%>"></script>
<script type="text/javascript">
window.onload=function(){//编辑器初始化
	initKindEditor('suggestContent','744px','350px');//加载编辑器
	checkLogin();
}
</script>
</head>
<body>
			<article class="u-m-c-w837 u-m-center">
				<form action="" id="addProblem">
				<section class="u-m-c-wrap">
					<!-- /u-m-c-head -->
					<section class="u-m-c-head">
						<ul class="fl u-m-c-h-txt">
							<li class="current"><a href="" title="">我要提问</a></li>
						</ul>
						<div class="clear"></div>
					</section>
					<!-- /u-m-c-head -->
					<section class="line1">
						<div class="pl15 pr15">
							<section class="mt40 ml30 mr30">
								<div class="clearfix">
									<div>
										<em class="fw-icon icon-2-24 mr5">&nbsp;</em>
										<tt class="vam fsize18 c-666 f-fM">问题标题</tt>
									</div>
								</div>
								
								<div class="title-area-outter"> 
									<span class="Q-verify" id="spanf"><font style="font-size: 12px;color:#bbb">标题长度不得多于24个字符</font></span>
								   
									<textarea placeholder="请在这里输入您的问题标题" name="sugSuggest.title" id="title"></textarea> 
									<!--  <span class="count" id="pblTitle-count"><b>0</b>/49</span> -->
								</div>
								
								<h4 class="mt30 mb10"><font class="fsize14 c-4e">问题内容</font></h4>
								<div class="edui-default">
									<textarea  id="suggestContent" name="sugSuggest.content"></textarea>
								</div>
								<div class="mt10">
									<div class="u-q-sort-list-wrap mt5 fl">
										<samp id="sampSubjectId">
										<input type="hidden" value="-1" id="subjectById"/>
									</div>
									<div class="tar">
										 <span class="vam c-red mr10" id="spanf1"></span>
										 <span id="spanf2" class="vam c-red mr10"></span> 
										 <a class="q-submit-btn" title="提交问题" href="javascript:void(0)" onclick="submitwushi();" style="background-color: #46B300" >提交问题</a>
									</div>
								</div>
								<div class="clear"></div>
							</section>
						</div>
					</section>
				</section>
				</form>
			</article>
	<!-- /u-main end -->
</body>
</html>

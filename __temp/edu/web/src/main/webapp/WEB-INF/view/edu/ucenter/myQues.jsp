<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的问答</title>
<%-- <script type="text/javascript" src="${ctx}/static/edu/js/ucenter/u_suggest.js"></script> --%>
<script type="text/javascript">
$(function (){
	ajaxPage("/uc/ajax/questlist","&falg=mysug",1,mysugResult);
});
function getProblem(obj,status){
	if($(obj).parent("li").hasClass("current")){
		return; 
	}
	if(status=='mysug'){
		ajaxPage("/uc/ajax/questlist","&falg=mysug",1,mysugResult);
	}else{
		ajaxPage("/uc/ajax/questlist","&falg=mysughd",1,mysugResult);
	}
	$(obj).parent("li").siblings().removeClass("current");
	$(obj).parent("li").addClass("current");
}
function mysugResult(result){
	$(".questlist").html(result);
}
</script>
</head>
<body>
			<article class="u-m-c-w837 u-m-center">
				<section class="u-m-c-wrap">
					<!-- /u-m-c-head -->
					<section class="u-m-c-head">
						<ul class="fl u-m-c-h-txt">
							<li class="current"><a href="javascript:void(0)" title="">我的问答</a></li>
						</ul>
						<div class="clear"></div>
					</section>
					<!-- /u-m-c-head -->
					<section class="line1">
						<!-- /u-m-sub-head -->
						<div class="u-m-sub-head">
							<ol class="clearfix pl10">
								<li class="current"><a href="javascript:void(0)"  onclick="getProblem(this,'mysug')" id="location1">我的提问</a></li>
								<li><a href="javascript:void(0)" onclick="getProblem(this,'mysughd')" id="location0">我的回答</a></li>
							</ol>
						</div>
						<!-- /u-m-sub-head -->
						<div class="pl15 pr15 questlist">
							
							
						</div>
					</section>
				</section>
	</article>
	<!-- /u-main end -->
</body>
</html>

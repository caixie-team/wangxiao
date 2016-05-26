<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>问题列表</title>
<%-- <script type="text/javascript" src="${ctx}/static/edu/js/ucenter/u_suggest.js"></script> --%>
<script type="text/javascript">
$(function (){
	ajaxPage("/uc/ajax/questlist","&falg=solved",1,solveResult);
});
function getProblem(obj,status){
	if($(obj).parent("li").hasClass("current")){
		return; 
	}
	if(status=='solved'){
		ajaxPage("/uc/ajax/questlist","&falg=solved",1,solveResult);
	}else{
		ajaxPage("/uc/ajax/questlist","&falg=unsolved",1,solveResult);
	}
	$(obj).parent("li").siblings().removeClass("current");
	$(obj).parent("li").addClass("current");
}
function solveResult(result){
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
							<li class="current"><a href="javascript:void(0)" title="">问题列表</a></li>
						</ul>
						<div class="clear"></div>
					</section>
					<!-- /u-m-c-head -->
					<section class="line1">
						<!-- /u-m-sub-head -->
						<div class="u-m-sub-head">
							<ol class="clearfix pl10">
								<li class="current"><a href="javascript:void(0)"  onclick="getProblem(this,'solved')" id="location1">已解决的问题</a></li>
								<li><a href="javascript:void(0)" onclick="getProblem(this,'unsolved')" id="location0">未解决的问题</a></li>
							</ol>
						</div>
						<!-- /u-m-sub-head -->
						<div class="pl10 pr10 questlist">
							
							
						</div>
					</section>
				</section>
				
	</section>
	</article>
	<!-- /u-main end -->
</body>
</html>

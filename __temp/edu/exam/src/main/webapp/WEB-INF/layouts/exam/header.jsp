<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!-- <div class="e-bg "><img src="" id="weekImages"></div> --><!-- /bg -->
<script>
//如果登陆了 显示头部用户信息
$(function(){
    if(!isLogin()){
        $(".userInfo").hide();
    }
});

</script>
<header class="e-header">
	<section class="e-h-line png">
		<section class="header png">
			<section class="w1000">
				<div class="clearfix">
					<section class="fr">
						<ol class="fl top-r-link clearfix userInfo">
							<li><em class="icon14 e-user png">&nbsp;</em><font class="fsize14 f-fM ml5 vam" id="userName"></font></li>
							<li><a target="_blank" href="${ctxweb}/uc/uinfo" title="个人设置" class="vam">设置</a></li>
							<li class="kg-wrap pr">
								<span class="k-btn"><em class="icon14 k-down png">&nbsp;</em><font class="fsize14 f-fM ml5 vam">我考过</font></span>
								<section class="kg" id="paperRecordList">
								</section>
							</li>
							<li><a href="javascript:exit();" title="退出" class="vam">退出</a></li>
						</ol>
						<!-- <div class="clear"></div> -->
					</section>
					<section class="fl l-s-wrap">
						<h1 class="e-logo"><a href="/index" title="厚博教育" class="png"><img src="http://static.ihoubo.cn/upload/grow-edu/websiteLogo/20151121/1448108864949705029.png" height="78" width="128" class="dis" /></a></h1>
						<div class="fl subNav">
							<ol class="clearfix">
								<li class="poiter-item pr">
									<a href="javascript:void(0)" class="a-subNav" id="subjectName" title="点击切换专业">项目</a>/
									<section class="poin-item-sub-nav" id="subjectAllShow">
										<div class="p-i-s-n-list clearfix" >
										</div>
									</section>
								</li>
								<%--  <li><a href="${ctx}/quest/toQuestionitemList" title="" class="a-subNav subjectName"></a>/</li> --%>
								<li id="lianxiyumokao"><a href="${ctx}/quest/toQuestionitemList" title="题库练习" class="a-subNav">题库练习</a>/</li>
								<li id="nenglipinggu"><a href="${ctx}/paper/competentAssessment" title="能力评估" class="a-subNav">能力评估</a>/</li>
								<li id="wodelianxi"><a href="${ctx}/paper/toExamPaperRecordList" title="我的题库" class="a-subNav">我的题库</a>/</li>
								<li><a href="${ctxweb}/uc/home" title="网校" class="a-subNav">网校</a>/</li>
                            </ol>
						</div>
					</section>
					<!-- <div class="clear"></div> -->
				</div>
			</section>
		</section>
	</section>
</header>

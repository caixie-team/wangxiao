<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<script type="text/javascript">
$(function(){//获得在线咨询信息 	
	$.ajax({
		url:baselocation+"/ajax/websiteProfile/online",
		data:{},
		dataType:"json",
		type:"post",
		success:function(result){
			var websitemap=result.entity;//获得map
			if(websitemap!=null&&websitemap!=''){
				if(websitemap.online.onlineKeyWord=='ON'){
					$("#onlineConsultBox").show();
				}
				$(".onlineC-item1").children("a").attr("href",websitemap.online.onlineUrl);
				$(".winxinGz").children().attr("src",staticImageServer+websitemap.online.onlineImageUrl);	
			}
		}
	})
})
</script>
<!-- / 帮助 -->
	<div class="ness-wrap">
		<section class="w1000">
			<ul class="ness clearfix">
				<li>
					<span class="n-icon n-i-1">&nbsp;</span>
					<h4 class="hLh30 of unFw"><font class="fsize20 c-999 f-fM">独家课程</font></h4>
					<div class="mt10">
						<p class="c-999">各行业及企业掌门人、商业领袖讲授创业人生企业管理，品牌营销、新趋势等独家课程。</p>
					</div>
				</li>
				<li>
					<span class="n-icon n-i-2">&nbsp;</span>
					<h4 class="hLh30 of unFw"><font class="fsize20 c-999 f-fM">答疑解惑</font></h4>
					<div class="mt10">
						<p class="c-999">各行业及企业掌门人、商业领袖讲授创业人生企业管理，品牌营销、新趋势等独家课程。</p>
					</div>
				</li>
				<li>
					<span class="n-icon n-i-3">&nbsp;</span>
					<h4 class="hLh30 of unFw"><font class="fsize20 c-999 f-fM">课程分析</font></h4>
					<div class="mt10">
						<p class="c-999">各行业及企业掌门人、商业领袖讲授创业人生企业管理，品牌营销、新趋势等独家课程。</p>
					</div>
				</li>
				<li>
					<span class="n-icon n-i-4">&nbsp;</span>
					<h4 class="hLh30 of unFw"><font class="fsize20 c-999 f-fM">自定义课程</font></h4>
					<div class="mt10">
						<p class="c-999">各行业及企业掌门人、商业领袖讲授创业人生企业管理，品牌营销、新趋势等独家课程。</p>
					</div>
				</li>
				<li>
					<span class="n-icon n-i-5">&nbsp;</span>
					<h4 class="hLh30 of unFw"><font class="fsize20 c-999 f-fM">移动课程</font></h4>
					<div class="mt10">
						<p class="c-999">各行业及企业掌门人、商业领袖讲授创业人生企业管理，品牌营销、新趋势等独家课程。</p>
					</div>
				</li>
				<li>
					<span class="n-icon n-i-6">&nbsp;</span>
					<h4 class="hLh30 of unFw"><font class="fsize20 c-999 f-fM">专题活动</font></h4>
					<div class="mt10">
						<p class="c-999">各行业及企业掌门人、商业领袖讲授创业人生企业管理，品牌营销、新趋势等独家课程。</p>
					</div>
				</li>
			</ul>
		</section>
	</div>
	<!-- / 帮助 结束 -->
<!-- /尾部-->
	<footer>
		<section class="f-link-wrap">
			<div class="w1000">
				<div class="fLink">
					<dl class="clearfix" id="linkFriend">
						<dt>
							<span class="vam c-888">友情链接：</span>
						</dt>
						<c:forEach items="${navigatemap.FRIENDLINK}" var="friendLinkNavigate">
	       					<dd><a href="${friendLinkNavigate.url}" title="${friendLinkNavigate.name}" <c:if test="${friendLinkNavigate.newPage==0}">target="_blank"</c:if>>${friendLinkNavigate.name}</a></dd>
						</c:forEach>
					</dl>
				</div><!-- /fLink -->
			</div>
		</section>
		<section class="foot-link">
			<div class="w1000">
				<div class="pt10 pb20">
					<section class="mt20 mb10">
						<ul class="tac">
							<li id="linkBottom">
								<c:forEach items="${navigatemap.TAB}" var="tabNavigate">
									<a target="_blank"  class="mr10 ml5" href="${tabNavigate.url}" title="${tabNavigate.name}" <c:if test="${tabNavigate.newPage==0}">target="_blank"</c:if>>${tabNavigate.name}</a>|
								</c:forEach>
								<span class="ml10"> 24小时服务热线：${websitemap.web.phone} Email：${websitemap.web.email}  工作时间：${websitemap.web.workTime} </span>
							</li>
							<li class="mt5"><font class="fsize12">${websitemap.web.copyright}</font></li>
						</ul>
					</section>
				</div>
			</div>
		</section>
	</footer>
	<!-- /尾部 结束-->
	<!-- 在线咨询 开始 -->
	<div class="onlineConsultBox" style="display: none" id="onlineConsultBox">
		<div class="onlineC-item">
			<ul>
				<li>
					<div class="onlineC-item3">
						<a href="/front/to_free_back" target="_blank" title="意见反馈" class="onlineC-ico">
							<span class="onlineC-ico5">&nbsp;</span>
							<p>意见反馈</p>
						</a>
					</div>
				</li>
			</ul>
			<div class="onlineC-item4" title="回到顶部 ^" id="goTop">
				<a href="javascript: void(0)" title="关注我们" class="onlineC-ico">
					<span class="onlineC-ico4">&nbsp;</span>
					<p>返回顶部</p>
				</a>
			</div>
		</div>
	</div> 
	<!-- 在线咨询 结束 -->

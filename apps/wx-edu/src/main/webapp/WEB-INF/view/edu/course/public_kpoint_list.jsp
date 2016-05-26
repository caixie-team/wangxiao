<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<%--课程--%>
<div class="mt15">
	<c:if test="${empty kpointList}">
		<section class="mt30 mb30 tac">
			<em class="no-data-ico cTipsIco">&nbsp;</em>
			<span class="c-666 fsize14 ml10 vam">课程目录小编正在积极整理中~</span>
		</section>
	</c:if>
	<c:if test="${not empty kpointList}">
		<c:set value="0" var="chapterIndex" />
		<c:set value="0" var="kpointIndex" />
		<c:set value="start" var="liType"/>
		<c:forEach items="${kpointList}" var="kpoint" varStatus="index">
			<c:if test="${kpoint.type==1}">
				<c:if test="${liType=='end'}">
					<c:set value="start" var="liType"/>
					</ul>
					<div class="c-white-box"></div>
					</div>
				</c:if>
				<c:set value="${chapterIndex+1}" var="chapterIndex" />
				<div class="cou-info-menu">
					<div class="chapter-name txtOf">第${chapterIndex }章 <span class="ml10">${kpoint.name}</span></div>
				</div>
			</c:if>
			<c:if test="${kpoint.type==0}">
				<c:set value="${kpointIndex+1}" var="kpointIndex" />
				<c:if test="${liType=='start'}">
					<div class="chap-seclist pr">
					<ul>
				</c:if>
				<c:set value="end" var="liType"/>
				<li onclick="changeKpoint('${kpoint.id}')">
					<div class="c-p-wrap">
						<div class="c-blue-dot"><tt></tt></div>
						<a href="javascript:void(0)"  class="c-p-title txtOf">第${kpointIndex }节 <span class="ml10">${kpoint.name}</span></a>
						<c:if test="${kpoint.courseType=='VIDEO'}">
							<a href="javascript:void(0)"  title="视频播放" class="play-icon-box">
								<small class="vam fsize12 c-ccc" title="时长：${kpoint.courseMinutes}分${kpoint.courseSeconds}秒">${kpoint.courseMinutes}分${kpoint.courseSeconds}秒</small>
								<em class="icon16 ml5"></em>
							</a>
						</c:if>
						<c:if test="${kpoint.courseType=='GRAPHIC'}">
							<a href="javascript:void(0)"  title="图文播放" class="play-icon-box image-icon-box">
								<small class="vam fsize12 c-ccc" title="时长：${kpoint.courseMinutes}分${kpoint.courseSeconds}秒">${kpoint.courseMinutes}分${kpoint.courseSeconds}秒</small>
								<em class="icon16 ml5"></em>
							</a>
						</c:if>
						<c:if test="${kpoint.courseType=='PDF'}">
							<a href="javascript:void(0)" title="PDF播放" class="play-icon-box wd-icon-box">
								<small class="vam fsize12 c-ccc" title="时长：${kpoint.courseMinutes}分${kpoint.courseSeconds}秒">${kpoint.courseMinutes}分${kpoint.courseSeconds}秒</small>
								<em class="icon16 ml5"></em>
							</a>
						</c:if>
						<c:if test="${kpoint.courseType=='MP3'}">
							<a href="javascript:void(0)" title="音频播放" class="play-icon-box audio-icon-box">
								<small class="vam fsize12 c-ccc" title="时长：${kpoint.courseMinutes}分${kpoint.courseSeconds}秒">${kpoint.courseMinutes}分${kpoint.courseSeconds}秒</small>
								<em class="icon16 ml5"></em>
							</a>
						</c:if>
					</div>
				</li>
			</c:if>
		</c:forEach>
	</c:if>
</div>
<script type="text/javascript">
	$(function(){
		$(".chap-seclist ul>li").mousemove(function(){
			$(this).addClass("current");
		});
		$(".chap-seclist ul>li").mouseout(function(){
			$(this).removeClass("current");
		});
	})
</script>
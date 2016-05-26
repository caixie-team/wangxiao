<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<c:choose>
	
	<c:when test="${videotype=='CC'}"> <%-- cc视频 --%>
        <script src='http://p.bokecc.com/player?vid=${videourl}&siteid=${ccwebsitemap.cc.ccappID}&autoStart=true&width=100%&height=100%&playerid=51A2AD3118ACAD37&playertype=1' type='text/javascript'></script>
	</c:when>
	<c:when test="${videotype=='FIVESIX'}"> <%-- 56视频 --%>
        <iframe src="${videourl}" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>
	</c:when>
	<c:when test="${videotype=='LETV'}"> <%-- 乐视云--%> 
		<embed src="http://yuntv.letv.com/bcloud.swf" wmode="transparent" allowFullScreen="true" quality="high"  width="100%" height="100%" align="middle" allowScriptAccess="always" flashvars="uu=${websiteLetvmap.letv.user_unique }&vu=${videourl}&auto_play=1&gpcflag=1&width=1024&height=768&extend=0&share=0" type="application/x-shockwave-flash"></embed>
	</c:when>
	<c:when test="${videotype=='SWF'}"> <%-- SWF/IFRAME --%>
		<iframe src="${videourl}" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>
	</c:when>
	<c:when test="${videotype=='mp4flv'}"><%-- 本地视频 --%> 
		<script src="${ctx}/static/common/jwplayer/flowplayer-3.2.4.min.js"></script>
		<script type="text/javascript">
		    $(function() {  
		    	flowplayer("player", "${ctx}/static/common/jwplayer/flowplayer.commercial.swf");
		    });  
		</script>  
		<a id="player" href="<%=staticImageServer%>${videourl}" style="display:block;width:100%;height:100%;"  ></a>
	</c:when>
	<%-- 没有权限 --%>
	<c:when test="${isok==false}">false</c:when>
	<c:otherwise>
		<!-- 错误类型的 先用iframe承接 -->
		<iframe src="${videourl}" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>
	</c:otherwise>
</c:choose>

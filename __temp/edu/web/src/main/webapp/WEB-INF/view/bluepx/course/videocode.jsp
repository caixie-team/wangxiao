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
	<c:when test="${videotype=='BAOLI'}"> <%-- 保利--%> 
		
		<c:if test="${isMoblie==true}">
		<div id='plv_${videourl}'></div>
		<script>
		var player = polyvObject('#plv_${videourl}').videoPlayer({
			'width':'100%',
			'height':'0',
			'vid' : '${videourl}'
		});
		</script>
		</c:if>
		<c:if test="${isMoblie==false}">
		<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%" id="polyvplayer${videourl}">
			<PARAM NAME=movie VALUE="http://player.polyv.net/videos/player.swf"><param name="allowscriptaccess" value="always">
			<param name="wmode" value="Transparent"><param name="flashvars" value="vid=${videourl}" />
			<param name="allowFullScreen" value="true" />
			<EMBED src="http://player.polyv.net/videos/player.swf" width="100%" height="100%"  TYPE="application/x-shockwave-flash" allowscriptaccess="always" wmode="Transparent" name="polyvplayer${videourl}" allowFullScreen="true" flashvars="vid=${videourl}"/></EMBED>
		</OBJECT>
		</c:if>
	</c:when>
	<c:when test="${videotype=='SWF'}"> <%-- SWF/IFRAME --%>
		<iframe src="${videourl}" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>
	</c:when>
	<c:when test="${videotype=='mp4flv'}"><%-- 本地视频 --%>
        <div id="vedio" style="width: 100%;height: 100%;"></div>
        <script type="text/javascript" src="/static/common/ckplayer/ckplayer.js" charset="utf-8"></script>
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
		<iframe src="${videourl}" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>
	</c:otherwise>
</c:choose>

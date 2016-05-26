<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<div class="l-person-info  showavatar">
    <section class="l-p-i-tx">
			<span><a href="" class="avatarhref"><img src="/static/common/images/user_default.jpg" class="avatar"  height="150" width="150" ></a>
            </span>
    </section>
    <section class="l-p-i-name">
        <a href="" title="" class="cusshowname csName"></a>
        <a href="${ctxweb}/uc/avatar#uPositionr"  class="uSet">设置</a>
       <%--<div >关注：<span class="attentionNum">0</span></div>
        <div >粉丝：<span class="fansNum">0</span></div>
        <div >观点：<span class="weiBoNum">0</span></div>--%>
    </section>
</div>
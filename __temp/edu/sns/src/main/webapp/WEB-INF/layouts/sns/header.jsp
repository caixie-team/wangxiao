<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!-- 全局固定头部 开始-->
<div class="globel-top-nav">
    <header class="gt-header pr">
        <div class="gt-logo">
            <a href="/" title="厚博科技"><img src="http://static.ihoubo.cn/upload/grow-edu/websiteLogo/20151121/1448108864949705029.png" height="46" width="73" ></a>
        </div>
        <nav class="gt-nav">
            <ul>
                <c:forEach items="${navigatemap.SNS}" var="snsNavigate">
                    <li><a href="${snsNavigate.url}" title="${snsNavigate.name}" <c:if test="${snsNavigate.newPage==0}">target="_blank"</c:if>>${snsNavigate.name}</a></li>
                </c:forEach>
            </ul>
        </nav>
        <div class="gt-search">
            <input type="text" name=""  onkeyup="headerEnterSubmit(event)" placeholder="大家都在搜索..." onFocus="javascript:$(this).keyup();" class="gt-input searchKey">
            <a href="javascript:submitsearchkey('weibo')" title="" target="_top" class="gt-btn sear"></a>
            <section class="gt-topmenulist gt-topmenulist-search" id="searchCus" tabindex="0">
            </section>
            <form action="${ctx}/search" method="post" id="search">
                <input type="hidden"  name="search.tab" id="searchDaotab">
                <input type="hidden" value="" name="search.keyword" id="searchDaokeyword">
            </form>
        </div>
        <div class="gt-person">
            <aside class="gt-setting">
                <a href="" title="" class="gt-name cusShortshowname"></a>
            </aside>
            <aside class="gt-setting">
                <a href="javascript:void(0)"
                   title="" class="gt-mail unReadNum"><i class="icon16">&nbsp;</i></a>
                <div class="gt-subtopmenulist">
                    <span class="gt-bgW">&nbsp;</span>
                    <ul class="gt-txt-tml">
                        <li><a href="${ctx}/friend?falg=myfans" title="查看粉丝" class="unreadFansNum">查看粉丝</a></li>
                        <li><a href="${ctx}/letter" title="查看站内信" class="letterNum">查看站内信</a></li>
                       <%-- <li><a href="${ctx}/letter/sys" title="查看系统通知" class="systemNum">查看系统通知</a></li>--%>
                        <li><a href="${ctx}/friend" title="我的关注" class="">我的关注</a></li>
                        <li><a href="${ctx}/friend?falg=myblack" title="我的黑名单" class="">我的黑名单</a></li>
                        <li><a href="${ctx}/friend?falg=visitor" title="最近访客" class="">最近访客</a></li>
                    </ul>
                </div>
            </aside>
            <aside class="gt-setting">
                <a href="javascript:void(0)" title="设置" class="gt-set"><i class="icon16">&nbsp;</i></a>
                <div class="gt-subtopmenulist">
                    <span class="gt-bgW">&nbsp;</span>
                    <ul class="gt-txt-tml">
                        <li><a href="${ctxweb}/uc/uinfo#uPosition" target="_blank" title="个人信息">个人信息</a></li>
                        <li><a href="${ctxweb}/uc/avatar#uPositionr" target="_blank" title="头像设置">头像设置</a></li>
                        <li><a href="${ctxweb}/uc/course#uPosition" target="_blank" title="我的课程">我的课程</a></li>
                        <li><a href="${ctxweb}/front/showcoulist" target="_blank" title="选课中心">选课中心</a></li>
                        <li><a href="${ctxexam}/quest/toQuestionitemList" target="_blank" title="考试中心">考试中心</a></li>
                        <li><a href="${ctxweb}/uc/order#uPosition" target="_blank" title="订单积分">订单积分</a></li>
                    </ul>
                    <div class="gn-func">
                        <a href="javascript:exit()" title="退出" class="c-blue">退出</a>
                    </div>
                </div>
            </aside>
        </div>
    </header>
</div>
<!-- 全局固定头部 结束-->

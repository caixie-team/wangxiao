<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!-- 全局公共头部+导航 开始 -->
<header class="NXB-header">
    <div class="NXB-container">
        <!-- logo end -->
        <h1 id="logo">
            <a title="xxx公司" href="/"> <img alt="xxx公司" src="<%=staticImageServer%>/${logomap.logo.url}">
            </a>
            <span class="vt-txt">—— 在线教育学习系统</span>
        </h1>
        <!-- logo end -->
        <aside class="h-top-r">
            <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
                <li><a href="${ctx}/uc/letter"><span class="am-icon-envelope-o"></span> 收件箱 <span
                        class="am-badge am-badge-warning msg-red undis"></span></a></li>
                <li data-am-dropdown="" class="am-dropdown"><a href="javascript:;" data-am-dropdown-toggle=""
                                                               class="am-dropdown-toggle"> <span
                        class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
                </a>
                    <ul class="am-dropdown-content">
                        <li><a href="${ctx}/uc/home"><span class="am-icon-user"></span> 资料</a></li>
                        <li><a href="${ctx}/uc/uinfo"><span class="am-icon-cog"></span> 设置</a></li>
                        <li><a href="/admin/sys/logout"><span class="am-icon-power-off"></span> 退出</a></li>
                    </ul>
                </li>
                <li class="am-hide-sm-only"><a id="admin-fullscreen" href="javascript:;"><span
                        class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
            </ul>
        </aside>
        <div class="clear"></div>
    </div>
</header>
<nav class="NXB-nav">
    <div class="NXB-container">
        <ul class="NXB-nav-ul fl">
            <li><a href="${weburl}/uc/home">我要学习</a></li>
            <c:forEach items="${topTabList}" var="tfunction" varStatus="status">
                <c:if test="${tfunction.parentFunctionId==0 && tfunction.functionTypeId==2}">
                    <c:if test="${status.index<8}">
                        <c:if test="${tfunction.functionUrlType==1 or tfunction.functionUrlType==0}">
                            <li>
                                <a href="${ctx}/admin/func/showchild?parentId=${tfunction.functionId}"> ${tfunction.functionName }</a>
                            </li>
                        </c:if>
                        <c:if test="${tfunction.functionUrlType==2 }">
                            <li>
                                <a href="${ctxsns}/admin/func/showchild?parentId=${tfunction.functionId}"> ${tfunction.functionName }</a>
                            </li>
                        </c:if>
                        <c:if test="${tfunction.functionUrlType==3 }">
                            <li>
                                <a href="${ctxexam}/admin/func/showchild?parentId=${tfunction.functionId}"> ${tfunction.functionName }</a>
                            </li>
                        </c:if>
                    </c:if>
                </c:if>
            </c:forEach>
            <c:if test="${topTabList.size()>=8}">
                <li>
                    <div data-am-dropdown="" class="am-dropdown">
                        <button data-am-dropdown-toggle="" class="am-btn am-btn-primary am-dropdown-toggle">
                            更多 <span class="am-icon-caret-down"></span>
                        </button>
                        <ul class="am-dropdown-content">
                            <c:forEach items="${topTabList}" var="tfunction" varStatus="status">
                                <c:if test="${status.index>=8}">
                                    <c:if test="${tfunction.functionUrlType==1 or tfunction.functionUrlType==0}">
                                        <li>
                                            <a href="${ctx}/admin/func/showchild?parentId=${tfunction.functionId}">${tfunction.functionName }</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${tfunction.functionUrlType==2}">
                                        <li>
                                            <a href="${ctxsns}/admin/func/showchild?parentId=${tfunction.functionId}">${tfunction.functionName }</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${tfunction.functionUrlType==3}">
                                        <li>
                                            <a href="${ctxexam}/admin/func/showchild?parentId=${tfunction.functionId}">${tfunction.functionName }</a>
                                        </li>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                </li>
            </c:if>
        </ul>
        <aside class="nav-r fr mt5">
            <a href="/uc/home" title="我要学习"><span class="am-icon-map-signs"></span>我要学习</a>
        </aside>
        <div class="clear"></div>
    </div>
</nav>
<script type="text/javascript">
    $(function () {
        queryUnReadNum();
    });

    function queryUnReadNum() {//查询未读消息
        $.ajax({
            type: "POST",
            dataType: "json",
            url: baselocation + "/letter/queryUnReadLetter",
            cache: true,
            async: false,
            success: function (result) {
                var letter = result.entity;
                if (letter == null) {
                    return;
                }
                //未读消息数
                var unReadNum = letter.unReadNum;
                if (unReadNum > 0) {
                    $(".msg-red").html(unReadNum);
                    $(".msg-red").show();
                } else {
                    $(".msg-red").hide();
                }
            }
        });
    }
</script>
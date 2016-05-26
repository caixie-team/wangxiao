<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>

<div id="admin-offcanvas" class="admin-sidebar am-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
        <ul id="collapase-nav-1" class="NXB-l-menu am-list admin-sidebar-list">
            <c:forEach items="${leftTabList}" varStatus="status" var="tabList">
                <li class="am-panel"><a
                        data-am-collapse="{parent: '#collapase-nav-1', target: '#left-${tabList[0].functionId}'}"
                        class="am-collapsed">${tabList[0].functionName}<i
                        class="am-icon-angle-right am-fr am-margin-right"></i></a>
                    <ul id="left-${tabList[0].functionId}" class="am-list admin-sidebar-sub am-collapse">
                        <c:forEach items="${tabList}" var="tfunction" varStatus="tfstatus">
                            <c:if test="${tfstatus.index>0 && tfunction.parentFunctionId!=0 && tfunction.functionTypeId==2 && tfunction.functionUrl!=null &&  tfunction.functionUrl!=''}">
                                <li><a href="${tfunction.functionUrl}"><span
                                        class="${tfunction.icon} am-margin-left-sm"></span> ${tfunction.functionName}
                                </a></li>
                            </c:if>
                            <c:if test="${tfstatus.index>0 && tfunction.parentFunctionId!=0 && tfunction.functionTypeId==1 && tfunction.functionUrl!=null &&  tfunction.functionUrl!=''}">
                                <li style="display: none;"><a href="${tfunction.functionUrl}"><span
                                        class="${tfunction.icon} am-margin-left-sm"></span> ${tfunction.functionName}
                                </a></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

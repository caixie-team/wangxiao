<%--
  Created by IntelliJ IDEA.
  Date: 2016/2/1
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/base.jsp"%>
<table class="am-table am-table-striped am-table-hover table-main">
    <thead>
    <tr>
        <th class="table-title am-text-center" width="15%">时间</th>

        <th class="table-title am-text-center" width="17%">注册用户数量</th>
        <th class="table-title am-text-center" width="17%">后台开通用户数量</th>
        <th class="table-title am-text-center" width="17%">课程卡用户数量</th>

        <th class="table-title am-text-center" width="17%">app注册用户数量</th>
        <th class="table-title am-text-center" width="17%">微站注册用户数量</th>
    </tr>
    </thead>
    <tbody>
    <tbody>
    <c:set var="t_registerNum" value="0"></c:set>
    <c:set var="t_adminNum" value="0"></c:set>
    <c:set var="t_userCardNum" value="0"></c:set>

    <c:set var="t_appNum" value="0"></c:set>
    <c:set var="t_mobileNum" value="0"></c:set>
    <c:forEach items="${statistics }" var="statistic" varStatus="index">
        <c:set var="t_registerNum" value="${t_registerNum+statistic.registerNum}"></c:set>
        <c:set var="t_adminNum" value="${t_adminNum+statistic.adminNum}"></c:set>
        <c:set var="t_userCardNum" value="${t_userCardNum+statistic.userCardNum}"></c:set>

        <c:set var="t_appNum" value="${t_appNum+statistic.appNum}"></c:set>
        <c:set var="t_mobileNum" value="${t_mobileNum+statistic.mobileNum}"></c:set>
        <tr align="center">
            <td>${year}年
                <c:if test="${month==null||month=='' }">${statistics.size()-index.index }月</c:if>
                <c:if test="${month!=null&&month!='' }">${month }月${statistics.size()-index.index }日</c:if>
            </td>
            <td>${statistic.registerNum}</td>
            <td>${statistic.adminNum}</td>
            <td>${statistic.userCardNum}</td>

            <td>${statistic.appNum}</td>
            <td>${statistic.mobileNum}</td>
        </tr>
    </c:forEach>
    <tr align="center">
        <td >合计 </td>
        <td>${t_registerNum}</td>
        <td>${t_adminNum}</td>
        <td>${t_userCardNum}</td>

        <td>${t_appNum}</td>
        <td>${t_mobileNum}</td>
    </tr>
    </tbody>
</table>
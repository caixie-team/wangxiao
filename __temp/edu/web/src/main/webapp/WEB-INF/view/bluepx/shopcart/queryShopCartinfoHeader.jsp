<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<span class="pa white-bg">&nbsp;</span>
<c:if test="${empty shopcartList}">
    <div class="tac">
        <em class="shop-ico icon30">&nbsp;</em>
        <font class="c-999 fsize12 vam">购物车暂无课程，建议你去<a href="${ctx}/front/showcoulist"  title="选课" class="c-master ml5">选课</a></font>
    </div>
</c:if>
<c:set var="totalMoney" value="0"></c:set>
<c:if test="${!empty shopcartList}">
<div>

    <h6 class="hLh30 line2 unFw"><span class="f-fM c-333 fsize12">购物车列表</span></h6>
    <div class="addScar-elem">
        <c:forEach items="${shopcartList }" var="sc">
        <c:set var="totalMoney" value="${totalMoney+sc.course.currentprice }"></c:set>
        <p class="addScar-list shopcartlist32">
            <a href="${ctx}/front/couinfo/${sc.course.id}" class="dis fl mr10">
            <c:choose>
                <c:when test="${not empty sc.course.logo}">
                    <img src="<%=staticImageServer%>${sc.course.logo}" width="50" height="38">
                </c:when>
                <c:otherwise>
                    <img src="<%=imagesPath%>/static/edu/images/default/default_goods.jpg" width="50" height="38">
                </c:otherwise>
            </c:choose>
            </a>
            <span class="a-s-l-c-name">
                <a href="${ctx}/front/couinfo/${sc.course.id}" class="c-4e fsize12 f-fM">${sc.course.name}</a></span>
                <span class="a-s-l-c-attr">
                <tt class="c-red dis">￥${sc.course.currentprice}</tt>
                <a href="javascript:deleteCartId(${sc.id},${sc.goodsid },${sc.type})"><u class="c-blue f-fM fsize12">删除</u></a>
                </span>
        </p>
        </c:forEach>
    </div>
    <div class="addScar-foot">
        <p class="tar">
            <span class="c-4e f-fM">共<tt class="c-red f-fM">${shopcartList.size()}</tt>件商品</span>
            <span class="c-4e f-fM ml10">共计：<tt class="c-red f-fM">￥${totalMoney}</tt></span>
        </p>
        <p class="mt5 tac">
            <a href="${ctx}/shopcart?command=queryShopCart" class="goScarBtn">立即结算</a>
        </p>
    </div>
</div>
</c:if>
